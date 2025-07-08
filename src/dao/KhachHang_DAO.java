package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.KhachHang;

public class KhachHang_DAO {
	ArrayList<KhachHang> dsKH;
	
	public KhachHang_DAO() {
		// TODO Auto-generated constructor stub
		dsKH = new ArrayList<KhachHang>();
	}
	
	public ArrayList<KhachHang> getAllKH() {
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM KhachHang");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String sdtKH = rs.getString("sdtKH");
				String tenKH = rs.getString("tenKH");
				String emailKH = rs.getString("emailKH");
				int diemTL = rs.getInt("diemTL");
				KhachHang kh = new KhachHang(sdtKH, tenKH, emailKH, diemTL);
				dsKH.add(kh);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsKH;
	}
	
	public boolean insertKH(KhachHang kh) {
		int n = 0;
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("INSERT INTO KhachHang VALUES(?, ?, ?, ?)");
			stmt.setString(1, kh.getSdtKH());
			stmt.setString(2, kh.getTenKH());
			stmt.setString(3, kh.getEmailKH());
			stmt.setInt(4, kh.getDiemTL());
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public boolean deleteKH(String sdtKH) {
		int n = 0;
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("DELETE FROM KhachHang WHERE sdtKH=?");
			stmt.setString(1, sdtKH);
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public boolean updateKH(KhachHang kh) {
		int n = 0;
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("UPDATE KhachHang SET tenKH=?, emailKH=?, diemTL=? WHERE sdtKH=?");
			stmt.setString(1, kh.getTenKH());
			stmt.setString(2, kh.getEmailKH());
			stmt.setInt(3, kh.getDiemTL());
			stmt.setString(4, kh.getSdtKH());
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public KhachHang getKH(String sdtKH) {
		KhachHang kh = null;
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM KhachHang WHERE sdtKH=?");
			stmt.setString(1, sdtKH);
			ResultSet rs = stmt.executeQuery();
		    if (rs.next()) {
				String tenKH = rs.getString("tenKH");
				String emailKH = rs.getString("emailKH");
				int diemTL = rs.getInt("diemTL");
				kh = new KhachHang(sdtKH, tenKH, emailKH, diemTL);
		    }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return kh;
	}
}
