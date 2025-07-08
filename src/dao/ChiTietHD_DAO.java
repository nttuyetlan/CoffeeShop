package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.ChiTietHD;
import entity.HoaDon;
import entity.SanPham;

public class ChiTietHD_DAO {
	ArrayList<ChiTietHD> dsCtHD;
	
	public ChiTietHD_DAO() {
		// TODO Auto-generated constructor stub
		dsCtHD = new ArrayList<ChiTietHD>();
	}
	
	public ArrayList<ChiTietHD> getAllCtHD() {
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM ChiTietHD");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				HoaDon_DAO hd_dao = new HoaDon_DAO();
				HoaDon hdCtHD = hd_dao.getHD(rs.getString("maHD"));
				SanPham_DAO sp_dao = new SanPham_DAO();
				SanPham spCtHD = sp_dao.getSP(rs.getString("maSP"));
				int soLuongSPCtHD = rs.getInt("soLuongSP");
				double thanhTienCtHD = rs.getDouble("thanhTien");
				ChiTietHD ctHD = new ChiTietHD(hdCtHD, spCtHD, soLuongSPCtHD, thanhTienCtHD);
				dsCtHD.add(ctHD);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsCtHD;
	}
	
	public boolean insertCtHD(ChiTietHD ctHD) {
		int n = 0;
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("INSERT INTO ChiTietHD VALUES(?, ?, ?, ?)");
			stmt.setString(1, ctHD.getHoaDonCTHD().getMaHD());
			stmt.setString(2, ctHD.getSanPhamCTHD().getMaSP());
			stmt.setInt(3, ctHD.getSoLuongSP());
			stmt.setDouble(4, ctHD.getThanhTien());
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public boolean deleteCtHD(String maHD, String maSP) {
		int n = 0;
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("DELETE FROM ChiTietHD WHERE maHD=?, maSP=?");
			stmt.setString(1, maHD);
			stmt.setString(2, maSP);
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public boolean updateCtHD(ChiTietHD ctHD) {
		int n = 0;
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("UPDATE ChiTietHD SET soLuongSP=?, thanhTien=? WHERE maHD=?, maSP=?");
			stmt.setInt(1, ctHD.getSoLuongSP());
			stmt.setDouble(2, ctHD.getThanhTien());
			stmt.setString(3, ctHD.getHoaDonCTHD().getMaHD());
			stmt.setString(4, ctHD.getSanPhamCTHD().getMaSP());
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public ChiTietHD getCtHD(String maHD, String maSP) {
		ChiTietHD ctHD = null;
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM ChiTietHD WHERE maHD=?, maSP");
			stmt.setString(1, maHD);
			stmt.setString(2, maSP);
			ResultSet rs = stmt.executeQuery();
		    if (rs.next()) {
		    	HoaDon_DAO hd_dao = new HoaDon_DAO();
				HoaDon hdCtHD = hd_dao.getHD(rs.getString("maHD"));
				SanPham_DAO sp_dao = new SanPham_DAO();
				SanPham spCtHD = sp_dao.getSP(rs.getString("maSP"));
				int soLuongSPCtHD = rs.getInt("soLuongSP");
				double thanhTienCtHD = rs.getDouble("thanhTien");
				ctHD = new ChiTietHD(hdCtHD, spCtHD, soLuongSPCtHD, thanhTienCtHD);
		    }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ctHD;
	}
}
