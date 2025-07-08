package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.NhanVien;
import entity.TaiKhoan;

public class NhanVien_DAO {
	ArrayList<NhanVien> dsNV;
	
	public NhanVien_DAO() {
		// TODO Auto-generated constructor stub
		dsNV = new ArrayList<NhanVien>();
	}
	
	public ArrayList<NhanVien> getAllNV() {
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM NhanVien");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maNV = rs.getString("maNV");
				String tenNV = rs.getString("tenNV");
				String diaChiNV = rs.getString("diaChiNV");
				String cccdNV = rs.getString("cccdNV");
				String sdtNV = rs.getString("sdtNV");
				String emailNV = rs.getString("emailNV");
				LocalDate ngayBatDauLV = rs.getTimestamp("ngayBatDauLV").toLocalDateTime().toLocalDate();
				String chucVuNV = rs.getString("chucVuNV");
				double doanhThuNV = rs.getDouble("doanhThuNV");
				TaiKhoan_DAO tk_dao = new TaiKhoan_DAO();
				TaiKhoan tkNV = tk_dao.getTK(rs.getString("tenTK"));
				NhanVien nv = new NhanVien(maNV, tenNV, diaChiNV, cccdNV, sdtNV, emailNV, ngayBatDauLV, chucVuNV, doanhThuNV, tkNV);
				dsNV.add(nv);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsNV;
	}
	
	public boolean insertNV(NhanVien nv) {
		int n = 0;
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("INSERT INTO NhanVien VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			stmt.setString(1, nv.getMaNV());
			stmt.setString(2, nv.getTenNV());
			stmt.setString(3, nv.getDiaChiNV());
			stmt.setString(4, nv.getCccdNV());
			stmt.setString(5, nv.getSdtNV());
			stmt.setString(6, nv.getEmailNV());
			stmt.setDate(7, Date.valueOf(nv.getNgayBatDauLV()));
			stmt.setString(8, nv.getChucVuNV());
			stmt.setDouble(9, nv.getDoanhThuNV());
			stmt.setString(10, nv.getTaiKhoanNV().getTenTK());
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public boolean deleteNV(String maNV) {
		int n = 0;
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("DELETE FROM NhanVien WHERE maNV=?");
			stmt.setString(1, maNV);
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public boolean updateNV(NhanVien nv) {
		int n = 0;
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("UPDATE NhanVien SET tenNV=?, diaChiNV=?, cccdNV=?, sdtNV=?, emailNV=?, ngayBatDauLV=?, chucVuNV=?, doanhThuNV=?, tenTK=? WHERE maNV=?");
			stmt.setString(1, nv.getTenNV());
			stmt.setString(2, nv.getDiaChiNV());
			stmt.setString(3, nv.getCccdNV());
			stmt.setString(4, nv.getSdtNV());
			stmt.setString(5, nv.getEmailNV());
			stmt.setDate(6, Date.valueOf(nv.getNgayBatDauLV()));
			stmt.setString(7, nv.getChucVuNV());
			stmt.setDouble(8, nv.getDoanhThuNV());
			stmt.setString(9, nv.getTaiKhoanNV().getTenTK());
			stmt.setString(10, nv.getMaNV());
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public NhanVien getNV(String maNV) {
		NhanVien nv = null;
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM NhanVien WHERE maNV=?");
			stmt.setString(1, maNV);
			ResultSet rs = stmt.executeQuery();
		    if (rs.next()) {
				String tenNV = rs.getString("tenNV");
				String diaChiNV = rs.getString("diaChiNV");
				String cccdNV = rs.getString("cccdNV");
				String sdtNV = rs.getString("sdtNV");
				String emailNV = rs.getString("emailNV");
				LocalDate ngayBatDauLV = rs.getTimestamp("ngayBatDauLV").toLocalDateTime().toLocalDate();
				String chucVuNV = rs.getString("chucVuNV");
				double doanhThuNV = rs.getDouble("doanhThuNV");
				TaiKhoan_DAO tk_dao = new TaiKhoan_DAO();
				TaiKhoan tkNV = tk_dao.getTK(rs.getString("tenTK"));
				nv = new NhanVien(maNV, tenNV, diaChiNV, cccdNV, sdtNV, emailNV, ngayBatDauLV, chucVuNV, doanhThuNV, tkNV);
		    }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return nv;
	}
	
	public String createMaNV() {
		String newMaNV = null;
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT MAX(maNV) AS maxMaNV FROM NhanVien");
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
                String maxMaNV = rs.getString("maxMaNV");
                // Kiểm tra nếu đã có mã nhân viên trong database
                if (maxMaNV != null) {
                    // Giả sử mã nhân viên có dạng "NV001", "NV002", ...
                    String prefix = maxMaNV.substring(0, 2); 					// Lấy phần "NV"
                    int numericPart = Integer.parseInt(maxMaNV.substring(2)); 	// Lấy phần số
                    // Tạo mã nhân viên mới bằng cách tăng phần số lên 1
                    newMaNV = prefix + String.format("%03d", numericPart + 1);
                } else {
                    // Nếu chưa có mã sản phẩm nào, bắt đầu từ "NV001"
                    newMaNV = "NV001";
                }
            }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return newMaNV;
	}
}
