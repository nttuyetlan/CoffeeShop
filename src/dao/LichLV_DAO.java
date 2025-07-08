package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.CaLamViec;
import entity.KhachHang;
import entity.KhuyenMai;
import entity.LichLV;
import entity.NhanVien;

public class LichLV_DAO {
	ArrayList<LichLV> dsLichLV;
	
	public LichLV_DAO() {
		// TODO Auto-generated constructor stub
		dsLichLV = new ArrayList<LichLV>();
	}
	
	public ArrayList<LichLV> getAllLichLV() {
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM LichLV");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maLichLV = rs.getString("maLichLV");
				NhanVien_DAO nv_dao = new NhanVien_DAO();
				NhanVien nvLichLV = nv_dao.getNV(rs.getString("maNV"));
				CaLamViec_DAO caLV_dao = new CaLamViec_DAO();
				CaLamViec caLVLichLV = caLV_dao.getCaLV(rs.getString("maCaLV"));
				LichLV lichLV = new LichLV(maLichLV, nvLichLV, caLVLichLV);
				dsLichLV.add(lichLV);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsLichLV;
	}
	
	public boolean insertLichLV(LichLV lichLV) {
		int n = 0;
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("INSERT INTO LichLV VALUES(?, ?, ?)");
			stmt.setString(1, lichLV.getMaLichLV());
			stmt.setString(2, lichLV.getNvLichLV().getMaNV());
			stmt.setString(3, lichLV.getCaLVLichLV().getMaCaLV());
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public boolean deleteLichLV(String maLichLV) {
		int n = 0;
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("DELETE FROM LichLV WHERE maLichLV=?");
			stmt.setString(1, maLichLV);
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public boolean updateLichLV(LichLV lichLV) {
		int n = 0;
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("UPDATE LichLV SET maNV=?, maCaLV=? WHERE maLichLV=?");
			stmt.setString(1, lichLV.getNvLichLV().getMaNV());
			stmt.setString(2, lichLV.getCaLVLichLV().getMaCaLV());
			stmt.setString(3, lichLV.getMaLichLV());
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public LichLV getLichLV(String maLichLV) {
		LichLV lichLV = null;
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM LichLV WHERE maLichLV=?");
			stmt.setString(1, maLichLV);
			ResultSet rs = stmt.executeQuery();
		    if (rs.next()) {
				NhanVien_DAO nv_dao = new NhanVien_DAO();
				NhanVien nvLichLV = nv_dao.getNV(rs.getString("maNV"));
				CaLamViec_DAO caLV_dao = new CaLamViec_DAO();
				CaLamViec caLVLichLV = caLV_dao.getCaLV(rs.getString("maCaLV"));
				lichLV = new LichLV(maLichLV, nvLichLV, caLVLichLV);
		    }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return lichLV;
	}
}
