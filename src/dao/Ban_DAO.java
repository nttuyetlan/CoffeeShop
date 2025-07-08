package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.Ban;

public class Ban_DAO {
	ArrayList<Ban> dsBan;
	
	public Ban_DAO() {
		// TODO Auto-generated constructor stub
		dsBan = new ArrayList<Ban>();
	}
	
	public ArrayList<Ban> getAllBan() {
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM Ban");
			ResultSet rs = stmt.executeQuery();
		    while (rs.next()) {
		    	String maBan = rs.getString("maBan");
		    	int soLuongGhe = rs.getInt("soLuongGhe");
		    	String loaiGhe = rs.getString("loaiGhe");
		    	String khuVuc = rs.getString("khuVuc");
		    	Ban ban = new Ban(maBan, soLuongGhe, loaiGhe, khuVuc);
		    	dsBan.add(ban);
		    }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsBan;
	}
	
	public boolean insertBan(Ban ban) {
		int n = 0;
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("INSERT INTO Ban VALUES(?, ?, ?, ?)");
			stmt.setString(1, ban.getMaBan());
			stmt.setInt(2, ban.getSoLuongGhe());
			stmt.setString(3, ban.getLoaiGhe());
			stmt.setString(4, ban.getKhuVuc());
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public boolean deleteBan(String maBan) {
		int n = 0;
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("DELETE FROM Ban WHERE maBan=?");
			stmt.setString(1, maBan);
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public boolean updateBan(Ban ban) {
		int n = 0;
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("UPDATE Ban SET soLuongGhe=?, loaiGhe=?, khuVuc=? WHERE maBan=?");
			stmt.setInt(1, ban.getSoLuongGhe());
			stmt.setString(2, ban.getLoaiGhe());
			stmt.setString(3, ban.getKhuVuc());
			stmt.setString(4, ban.getMaBan());
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public Ban getBan(String maBan) {
		Ban ban = null;
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM Ban WHERE maBan=?");
			stmt.setString(1, maBan);
			ResultSet rs = stmt.executeQuery();
		    if (rs.next()) {
		    	int soLuongGhe = rs.getInt("soLuongGhe");
		    	String loaiGhe = rs.getString("loaiGhe");
		    	String khuVuc = rs.getString("khuVuc");
		    	ban = new Ban(maBan, soLuongGhe, loaiGhe, khuVuc);
		    }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ban;
	}
}
