package test;

import java.time.LocalDate;

import dao.KhuyenMai_DAO;
import entity.KhachHang;
import entity.KhuyenMai;

public class ThemKM_TEST {
	
	public ThemKM_TEST() {
		// TODO Auto-generated constructor stub
	}
	
	public void themKM() {
		KhuyenMai_DAO km_dao = new KhuyenMai_DAO();
		km_dao.insertKM(new KhuyenMai(km_dao.createMaKM(), "Đồ án thành công", LocalDate.now(), LocalDate.now().plusDays(20), 20, new KhachHang("0999999999")));
	}
}
