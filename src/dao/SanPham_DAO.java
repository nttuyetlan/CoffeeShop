package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.SanPham;

public class SanPham_DAO {
	ArrayList<SanPham> dsSP;
	
	public SanPham_DAO() {
		// TODO Auto-generated constructor stub
		dsSP = new ArrayList<SanPham>();
	}
	
	public ArrayList<SanPham> getAllSP() {
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM SanPham");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maSP = rs.getString("maSP");
				String tenSP = rs.getString("tenSP");
				double donGiaSP = rs.getDouble("donGiaSP");
				LocalDate ngayCapNhat = rs.getTimestamp("ngayCapNhat").toLocalDateTime().toLocalDate();
				String loaiSP = rs.getString("loaiSP");
				byte[] hinhSP = rs.getBytes("hinhSP");
				SanPham sp = new SanPham(maSP, tenSP, donGiaSP, ngayCapNhat, loaiSP, hinhSP);
				dsSP.add(sp);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsSP;
	}
	
	public boolean insertSP(SanPham sp) {
		int n = 0;
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("INSERT INTO SanPham VALUES(?, ?, ?, ?, ?, ?)");
			stmt.setString(1, sp.getMaSP());
			stmt.setString(2, sp.getTenSP());
			stmt.setDouble(3, sp.getDonGiaSP());
			stmt.setDate(4, Date.valueOf(sp.getNgayCapNhatSP()));
			stmt.setString(5, sp.getLoaiSP());
			stmt.setBytes(6, sp.getHinhSP());
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public boolean deleteSP(String maSP) {
		int n = 0;
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("DELETE FROM SanPham WHERE maSP=?");
			stmt.setString(1, maSP);
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public boolean updateSP(SanPham sp) {
		int n = 0;
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("UPDATE SanPham SET tenSP=?, donGiaSP=?, ngayCapNhat=?, loaiSP=?, hinhSP=? WHERE maSP=?");
			stmt.setString(1, sp.getTenSP());
			stmt.setDouble(2, sp.getDonGiaSP());
			stmt.setDate(3, Date.valueOf(sp.getNgayCapNhatSP()));
			stmt.setString(4, sp.getLoaiSP());
			stmt.setBytes(5, sp.getHinhSP());
			stmt.setString(6, sp.getMaSP());
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public SanPham getSP(String maSP) {
		SanPham sp = null;
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM SanPham WHERE maSP=?");
			stmt.setString(1, maSP);
			ResultSet rs = stmt.executeQuery();
		    if (rs.next()) {
				String tenSP = rs.getString("tenSP");
				double donGiaSP = rs.getDouble("donGiaSP");
				LocalDate ngayCapNhat = rs.getTimestamp("ngayCapNhat").toLocalDateTime().toLocalDate();
				String loaiSP = rs.getString("loaiSP");
				byte[] hinhSP = rs.getBytes("hinhSP");
				sp = new SanPham(maSP, tenSP, donGiaSP, ngayCapNhat, loaiSP, hinhSP);
		    }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return sp;
	}
	
	public String createMaSP() {
		String newMaSP = null;
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT MAX(maSP) AS maxMaSP FROM SanPham");
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
                String maxMaSP = rs.getString("maxMaSP");
                // Kiểm tra nếu đã có mã sản phẩm trong database
                if (maxMaSP != null) {
                    // Giả sử mã sản phẩm có dạng "SP001", "SP002", ...
                    String prefix = maxMaSP.substring(0, 2); 					// Lấy phần "SP"
                    int numericPart = Integer.parseInt(maxMaSP.substring(2)); 	// Lấy phần số
                    // Tạo mã sản phẩm mới bằng cách tăng phần số lên 1
                    newMaSP = prefix + String.format("%03d", numericPart + 1);
                } else {
                    // Nếu chưa có mã sản phẩm nào, bắt đầu từ "SP001"
                    newMaSP = "SP001";
                }
            }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return newMaSP;
	}
	
	public SanPham getSPfromTenSP(String tenSP) {
		SanPham sp = null;
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM SanPham WHERE tenSP=?");
			stmt.setString(1, tenSP);
			ResultSet rs = stmt.executeQuery();
		    if (rs.next()) {
				String maSP = rs.getString("maSP");
				double donGiaSP = rs.getDouble("donGiaSP");
				LocalDate ngayCapNhat = rs.getTimestamp("ngayCapNhat").toLocalDateTime().toLocalDate();
				String loaiSP = rs.getString("loaiSP");
				byte[] hinhSP = rs.getBytes("hinhSP");
				sp = new SanPham(maSP, tenSP, donGiaSP, ngayCapNhat, loaiSP, hinhSP);
		    }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return sp;
	}
}
