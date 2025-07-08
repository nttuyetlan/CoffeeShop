package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.CaLamViec;

public class CaLamViec_DAO {
	ArrayList<CaLamViec> dsCaLV;
	
	public CaLamViec_DAO() {
		// TODO Auto-generated constructor stub
		dsCaLV = new ArrayList<CaLamViec>();
	}
	
	public ArrayList<CaLamViec> getAllCaLV() {
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM CaLamViec");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maCaLV = rs.getString("maCaLV");
				String tenCaLV = rs.getString("tenCaLV");
				LocalTime gioVaoLam = rs.getTimestamp("gioVaoLam").toLocalDateTime().toLocalTime();
				LocalTime gioKetThuc = rs.getTimestamp("gioKetThuc").toLocalDateTime().toLocalTime();
				double luongTheoGio = rs.getDouble("luongTheoGio");
				CaLamViec caLV = new CaLamViec(maCaLV, tenCaLV, gioVaoLam, gioKetThuc, luongTheoGio);
				dsCaLV.add(caLV);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsCaLV;
	}
	
	public boolean insertCaLV(CaLamViec caLV) {
		int n = 0;
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("INSERT INTO CaLamViec VALUES(?, ?, ?, ?, ?)");
			stmt.setString(1, caLV.getMaCaLV());
			stmt.setString(2, caLV.getTenCaLV());
			stmt.setTime(3, Time.valueOf(caLV.getGioVaoLam()));
			stmt.setTime(4, Time.valueOf(caLV.getGioKetThuc()));
			stmt.setDouble(5, caLV.getLuongTheoGio());
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public boolean deleteCaLV(String maCaLV) {
		int n = 0;
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("DELETE FROM CaLamViec WHERE maCaLV=?");
			stmt.setString(1, maCaLV);
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public boolean updateCaLV(CaLamViec caLV) {
		int n = 0;
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("UPDATE CaLamViec SET tenCaLV=?, gioVaoLam=?, gioKetThuc=?, luongTheoGio=? WHERE maCaLV=?");
			stmt.setString(1, caLV.getTenCaLV());
			stmt.setTime(2, Time.valueOf(caLV.getGioVaoLam()));
			stmt.setTime(3, Time.valueOf(caLV.getGioKetThuc()));
			stmt.setDouble(4, caLV.getLuongTheoGio());
			stmt.setString(5, caLV.getMaCaLV());
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public CaLamViec getCaLV(String maCaLV) {
		CaLamViec caLV = null;
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM CaLamViec WHERE maCaLV=?");
			stmt.setString(1, maCaLV);
			ResultSet rs = stmt.executeQuery();
		    if (rs.next()) {
				String tenCaLV = rs.getString("tenCaLV");
				LocalTime gioVaoLam = rs.getTimestamp("gioVaoLam").toLocalDateTime().toLocalTime();
				LocalTime gioKetThuc = rs.getTimestamp("gioKetThuc").toLocalDateTime().toLocalTime();
				double luongTheoGio = rs.getDouble("luongTheoGio");
				caLV = new CaLamViec(maCaLV, tenCaLV, gioVaoLam, gioKetThuc, luongTheoGio);
		    }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return caLV;
	}
}