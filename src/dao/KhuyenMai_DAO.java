package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.KhachHang;
import entity.KhuyenMai;

public class KhuyenMai_DAO {
	ArrayList<KhuyenMai> dsKM;
	
	public KhuyenMai_DAO() {
		// TODO Auto-generated constructor stub
		dsKM = new ArrayList<KhuyenMai>();
	}
	
	public ArrayList<KhuyenMai> getAllKM() {
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM KhuyenMai");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maKM = rs.getString("maKM");
				String tenKM = rs.getString("tenKM");
				LocalDate ngayADKM = rs.getTimestamp("ngayADKM").toLocalDateTime().toLocalDate();
				LocalDate ngayKTKM = rs.getTimestamp("ngayKTKM").toLocalDateTime().toLocalDate();
				double giaTriKM = rs.getDouble("giaTriKM");
				KhachHang_DAO kh_dao = new KhachHang_DAO();
				KhachHang khKM = kh_dao.getKH(rs.getString("sdtKH"));
				KhuyenMai km = new KhuyenMai(maKM, tenKM, ngayADKM, ngayKTKM, giaTriKM, khKM);
				dsKM.add(km);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsKM;
	}
	
	public boolean insertKM(KhuyenMai km) {
		int n = 0;
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("INSERT INTO KhuyenMai VALUES(?, ?, ?, ?, ?, ?)");
			stmt.setString(1, km.getMaKM());
			stmt.setString(2, km.getTenKM());
			stmt.setDate(3, Date.valueOf(km.getNgayADKM()));
			stmt.setDate(4, Date.valueOf(km.getNgayKTKM()));
			stmt.setDouble(5, km.getGiaTriKM());
			stmt.setString(6, km.getKhachHangKM().getSdtKH());
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public boolean deleteKM(String maKM) {
		int n = 0;
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("DELETE FROM KhuyenMai WHERE maKM=?");
			stmt.setString(1, maKM);
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public boolean updateKM(KhuyenMai km) {
		int n = 0;
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("UPDATE KhuyenMai SET tenKM=?, ngayADKM=?, ngayKTKM=?, giaTriKM=?, sdtKH=? WHERE maKM=?");
			stmt.setString(1, km.getTenKM());
			stmt.setDate(2, Date.valueOf(km.getNgayADKM()));
			stmt.setDate(3, Date.valueOf(km.getNgayKTKM()));
			stmt.setDouble(4, km.getGiaTriKM());
			stmt.setString(5, km.getKhachHangKM().getSdtKH());
			stmt.setString(6, km.getMaKM());
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public KhuyenMai getKM(String maKM) {
		KhuyenMai km = null;
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM KhuyenMai WHERE maKM=?");
			stmt.setString(1, maKM);
			ResultSet rs = stmt.executeQuery();
		    if (rs.next()) {
				String tenKM = rs.getString("tenKM");
				LocalDate ngayADKM = rs.getTimestamp("ngayADKM").toLocalDateTime().toLocalDate();
				LocalDate ngayKTKM = rs.getTimestamp("ngayKTKM").toLocalDateTime().toLocalDate();
				double giaTriKM = rs.getDouble("giaTriKM");
				KhachHang_DAO kh_dao = new KhachHang_DAO();
				KhachHang khKM = kh_dao.getKH(rs.getString("sdtKH"));
				km = new KhuyenMai(maKM, tenKM, ngayADKM, ngayKTKM, giaTriKM, khKM);
		    }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return km;
	}
	
	public String createMaKM() {
		String newMaKM = null;
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT MAX(maKM) AS maxMaKM FROM KhuyenMai");
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
                String maxMaKM = rs.getString("maxMaKM");
                // Kiểm tra nếu đã có mã khuyến mãi trong database
                if (maxMaKM != null) {
                    // Giả sử mã khuyến mãi có dạng "KM001", "KM002", ...
                    String prefix = maxMaKM.substring(0, 2); 					// Lấy phần "KM"
                    int numericPart = Integer.parseInt(maxMaKM.substring(2)); 	// Lấy phần số
                    // Tạo mã khuyến mãi mới bằng cách tăng phần số lên 1
                    newMaKM = prefix + String.format("%03d", numericPart + 1);
                } else {
                    // Nếu chưa có mã khuyến mãi nào, bắt đầu từ "KM001"
                    newMaKM = "KM001";
                }
            }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return newMaKM;
	}
	
	public ArrayList<KhuyenMai> getKhuyenMaiHet() {
	    ArrayList<KhuyenMai> KMhet = new ArrayList<>();
	    try {
	        Connection con = ConnectDB.getInstance().getConnection();
	        CallableStatement stmt = con.prepareCall("{CALL sp_GetKhuyenMaiHet(?)}");
//	        PreparedStatement stmt = con.prepareStatement("SELECT * FROM KhuyenMai WHERE ngayKTKM < ?");
	        stmt.setDate(1, Date.valueOf(LocalDate.now())); 
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            String maKM = rs.getString("maKM");
	            String tenKM = rs.getString("tenKM");
	            LocalDate ngayADKM = rs.getDate("ngayADKM").toLocalDate();
	            LocalDate ngayKTKM = rs.getDate("ngayKTKM").toLocalDate();
	            double giaTriKM = rs.getDouble("giaTriKM");
	            KhachHang_DAO kh_dao = new KhachHang_DAO();
	            KhachHang khKM = kh_dao.getKH(rs.getString("sdtKH"));
	            
	            KhuyenMai km = new KhuyenMai(maKM, tenKM, ngayADKM, ngayKTKM, giaTriKM, khKM);
	            KMhet.add(km);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return KMhet;
	}
	
	public ArrayList<KhuyenMai> getKhuyenMaiCon() {
	    ArrayList<KhuyenMai> KMhet = new ArrayList<>();
	    try {
	        Connection con = ConnectDB.getInstance().getConnection();
	        CallableStatement stmt = con.prepareCall("{CALL sp_GetKhuyenMaiCon(?)}");
	        stmt.setDate(1, Date.valueOf(LocalDate.now())); 
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            String maKM = rs.getString("maKM");
	            String tenKM = rs.getString("tenKM");
	            LocalDate ngayADKM = rs.getDate("ngayADKM").toLocalDate();
	            LocalDate ngayKTKM = rs.getDate("ngayKTKM").toLocalDate();
	            double giaTriKM = rs.getDouble("giaTriKM");
	            KhachHang_DAO kh_dao = new KhachHang_DAO();
	            KhachHang khKM = kh_dao.getKH(rs.getString("sdtKH"));
	            KhuyenMai km = new KhuyenMai(maKM, tenKM, ngayADKM, ngayKTKM, giaTriKM, khKM);
	            KMhet.add(km);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return KMhet;
	}

}
