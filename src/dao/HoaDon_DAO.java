package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.Ban;
import entity.HoaDon;
import entity.KhachHang;
import entity.KhuyenMai;
import entity.NhanVien;

public class HoaDon_DAO {
	ArrayList<HoaDon> dsHD;
	
	public HoaDon_DAO() {
		// TODO Auto-generated constructor stub
		dsHD = new ArrayList<HoaDon>();
	}
	
	public ArrayList<HoaDon> getAllHD() {
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM HoaDon");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maHD = rs.getString("maHD");
				LocalDateTime ngayLapHD = rs.getTimestamp("ngayLapHD").toLocalDateTime();
				double tongTienHD = rs.getDouble("tongTienHD");
				double thanhToanHD = rs.getDouble("thanhToanHD");
				double tienKhachDua = rs.getDouble("tienKhachDua");
				double tienTraLai = rs.getDouble("tienTraLai");
				NhanVien_DAO nv_dao = new NhanVien_DAO();
				NhanVien nvHD = nv_dao.getNV(rs.getString("maNV"));
				KhachHang_DAO kh_dao = new KhachHang_DAO();
				KhachHang khHD = kh_dao.getKH(rs.getString("sdtKH"));
				Ban_DAO ban_dao = new Ban_DAO();
				Ban banHD = ban_dao.getBan(rs.getString("maBan"));
				KhuyenMai_DAO km_dao = new KhuyenMai_DAO();
				KhuyenMai kmHD = km_dao.getKM(rs.getString("maKM"));
				HoaDon hd = new HoaDon(maHD, ngayLapHD, tongTienHD, thanhToanHD, tienKhachDua, tienTraLai, nvHD, khHD, banHD, kmHD);
				dsHD.add(hd);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsHD;
	}
	
	public boolean insertHD(HoaDon hd) {
		int n = 0;
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("INSERT INTO HoaDon VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			stmt.setString(1, hd.getMaHD());
			stmt.setTimestamp(2, Timestamp.valueOf(hd.getNgayLapHD()));
			stmt.setDouble(3, hd.getTongTienHD());
			stmt.setDouble(4, hd.getThanhToanHD());
			stmt.setDouble(5, hd.getTienKhachDua());
			stmt.setDouble(6, hd.getTienTraLai());
			stmt.setString(7, hd.getNvHD().getMaNV());
			stmt.setString(8, hd.getKhHD().getSdtKH());
			stmt.setString(9, hd.getBanHD().getMaBan());
			stmt.setString(10, hd.getKmHD().getMaKM());
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public boolean deleteHD(String maHD) {
		int n = 0;
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("DELETE FROM HoaDon WHERE maHD=?");
			stmt.setString(1, maHD);
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public boolean updateHD(HoaDon hd) {
		int n = 0;
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("UPDATE HoaDon SET ngayLapHD=?, tongTienHD=?, thanhToanHD=?, tienKhachDua=?, tienTraLai=?, maNV=?, sdtKH=?, maBan=?, maKM=? WHERE maHD=?");
			stmt.setTimestamp(1, Timestamp.valueOf(hd.getNgayLapHD()));
			stmt.setDouble(2, hd.getTongTienHD());
			stmt.setDouble(3, hd.getThanhToanHD());
			stmt.setDouble(4, hd.getTienKhachDua());
			stmt.setDouble(5, hd.getTienTraLai());
			stmt.setString(6, hd.getNvHD().getMaNV());
			stmt.setString(7, hd.getKhHD().getSdtKH());
			stmt.setString(8, hd.getBanHD().getMaBan());
			stmt.setString(9, hd.getKmHD().getMaKM());
			stmt.setString(10, hd.getMaHD());
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public HoaDon getHD(String maHD) {
		HoaDon hd = null;
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM HoaDon WHERE maHD=?");
			stmt.setString(1, maHD);
			ResultSet rs = stmt.executeQuery();
		    if (rs.next()) {
				LocalDateTime ngayLapHD = rs.getTimestamp("ngayLapHD").toLocalDateTime();
				double tongTienHD = rs.getDouble("tongTienHD");
				double thanhToanHD = rs.getDouble("thanhToanHD");
				double tienKhachDua = rs.getDouble("tienKhachDua");
				double tienTraLai = rs.getDouble("tienTraLai");
				NhanVien_DAO nv_dao = new NhanVien_DAO();
				NhanVien nvHD = nv_dao.getNV(rs.getString("maNV"));
				KhachHang_DAO kh_dao = new KhachHang_DAO();
				KhachHang khHD = kh_dao.getKH(rs.getString("sdtKH"));
				Ban_DAO ban_dao = new Ban_DAO();
				Ban banHD = ban_dao.getBan(rs.getString("maBan"));
				KhuyenMai_DAO km_dao = new KhuyenMai_DAO();
				KhuyenMai kmHD = km_dao.getKM(rs.getString("maKM"));
				hd = new HoaDon(maHD, ngayLapHD, tongTienHD, thanhToanHD, tienKhachDua, tienTraLai, nvHD, khHD, banHD, kmHD);
		    }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return hd;
	}
	
	public String createMaHD() {
		String newMaHD = null;
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT MAX(maHD) AS maxMaHD FROM HoaDon");
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
                String maxMaHD = rs.getString("maxMaHD");
                // Kiểm tra nếu đã có mã hóa đơn trong database
                if (maxMaHD != null) {
                    // Giả sử mã hóa đơn có dạng "HD001", "HD002", ...
                    String prefix = maxMaHD.substring(0, 2); 					// Lấy phần "HD"
                    int numericPart = Integer.parseInt(maxMaHD.substring(2)); 	// Lấy phần số
                    // Tạo mã hóa đơn mới bằng cách tăng phần số lên 1
                    newMaHD = prefix + String.format("%03d", numericPart + 1);
                } else {
                    // Nếu chưa có mã hóa đơn nào, bắt đầu từ "HD001"
                    newMaHD = "HD001";
                }
            }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return newMaHD;
	}
}
