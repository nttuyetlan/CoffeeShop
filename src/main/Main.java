package main;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;

import connectDB.ConnectDB;
import dao.Ban_DAO;
import dao.CaLamViec_DAO;
import dao.NhanVien_DAO;
import dao.SanPham_DAO;
import dao.TaiKhoan_DAO;
import entity.Ban;
import entity.CaLamViec;
import entity.NhanVien;
import entity.SanPham;
import entity.TaiKhoan;
import gui.DangNhap_GUI;
import gui.NhanVien_GUI;
import test.ThemKM_TEST;
import test.ThemKhachHang;
import test.ThemSP_TEST;

public class Main {
	public static void main(String[] args) {
		ConnectDB.getInstance().connect();
		new DangNhap_GUI().setVisible(true);
	}
}
