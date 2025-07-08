package test;

import dao.KhachHang_DAO;
import entity.KhachHang;

public class ThemKhachHang {
	
	public ThemKhachHang() {
		// TODO Auto-generated constructor stub
	}
	
	public void themKH() {
		KhachHang_DAO kh_dao = new KhachHang_DAO();
		kh_dao.insertKH(new KhachHang("0999999999", "Lộc Nguyên", "sieuvip@gmail.com", 9));
	}
}
