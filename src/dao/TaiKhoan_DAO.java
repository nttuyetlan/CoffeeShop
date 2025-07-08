package dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.TaiKhoan;

public class TaiKhoan_DAO {
	ArrayList<TaiKhoan> dsTK;
	
	public TaiKhoan_DAO() {
		// TODO Auto-generated constructor stub
		dsTK = new ArrayList<TaiKhoan>();
	}
	
	// Phương thức băm mật khẩu bằng SHA-256
    public String hashPasswordSHA256(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());

            // Chuyển đổi mảng byte thành chuỗi dạng hex
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
	
	public ArrayList<TaiKhoan> getAllTK() {
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM TaiKhoan");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String tenTK = rs.getString("tenTK");
				String matKhau = rs.getString("matKhau");
				TaiKhoan taiKhoan = new TaiKhoan(tenTK, matKhau);
				dsTK.add(taiKhoan);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsTK;
	}
	
	public boolean insertTK(TaiKhoan tk) {
		int n = 0;
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("INSERT INTO TaiKhoan VALUES(?, ?)");
			stmt.setString(1, tk.getTenTK());
			stmt.setString(2, hashPasswordSHA256(tk.getMatKhau()));
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public boolean deleteTK(String tenTK) {
		int n = 0;
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("DELETE FROM TaiKhoan WHERE tenTK=?");
			stmt.setString(1, tenTK);
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public boolean updateTK(TaiKhoan tk) {
		int n = 0;
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("UPDATE TaiKhoan SET matKhau=? WHERE tenTK=?");
			stmt.setString(1, hashPasswordSHA256(tk.getMatKhau()));
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public TaiKhoan getTK(String tenTK) {
		TaiKhoan tk = null;
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM TaiKhoan WHERE tenTK=?");
			stmt.setString(1, tenTK);
			ResultSet rs = stmt.executeQuery();
		    if (rs.next()) {
				String matKhau = rs.getString("matKhau");
				tk = new TaiKhoan(tenTK, matKhau);
		    }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return tk;
	}
}