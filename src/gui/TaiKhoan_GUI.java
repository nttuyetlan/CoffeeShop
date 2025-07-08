package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.CaLamViec_DAO;
import dao.LichLV_DAO;
import entity.CaLamViec;
import entity.LichLV;
import entity.NhanVien;

public class TaiKhoan_GUI extends JFrame{
	private JTextField txtMaNV;
	private JTextField txtNgayVaoLV;
	private JTextField txtTenNV;
	private JTextField txtCaLV;
	private JTextField txtEmail;
	private JTextField txtGioVaoLam;
	private JTextField txtSDT;
	private JTextField txtGioKT;
	private JTextField txtCV;
	private JTextField txtNgayLV;
	private JTextField txtMaTK;
	private JTextField txtMK;
	private NhanVien nv;
	
	public TaiKhoan_GUI(NhanVien nv) {
		// TODO Auto-generated constructor stub
		this.nv = nv;
	}
	
	public JPanel createTTNV() {
		// TODO Auto-generated method stub
		JPanel pTTNV = new JPanel();
		pTTNV.setLayout(new BoxLayout(pTTNV, BoxLayout.Y_AXIS));
		
		JPanel b1 = new JPanel();
		pTTNV.add(b1);
		b1.setBackground(new Color(97, 79, 69));
		JLabel lbTTNV;
		b1.add(lbTTNV = new JLabel("Thông Tin Nhân Viên"));
		b1.setPreferredSize(new Dimension(200, 47));
		lbTTNV.setFont(new Font(getName(), Font.BOLD, 30));
		lbTTNV.setBackground(new Color(97, 79, 69));
		lbTTNV.setForeground(Color.white);
		lbTTNV.setPreferredSize(new Dimension(330, 75));
		
		JPanel b2 = new JPanel();
		pTTNV.add(b2);
		b2.setLayout(new BoxLayout(b2, BoxLayout.Y_AXIS));
		JPanel boxA;
		b2.add(boxA = new JPanel());
		JLabel lblTTCB, lbl;
		boxA.add(lblTTCB = new JLabel("Thông tin cơ bản: "));
		boxA.add(lbl = new JLabel());
		lblTTCB.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 700));
		lblTTCB.setFont(new Font(getName(), Font.BOLD, 24));
		
		Box boxB;
		JPanel boxB1, boxB2, boxB3, boxB4, boxB5;
		JLabel lbMaNV, lbNgayVaoLV, lbTen, lbCaLV, lbEmail, lbGioVaoLam, lbSDT, lbGioKT, lbCV, lbNgayLV;
		b2.add(boxB = Box.createVerticalBox());
		boxB.add(boxB1 = new JPanel());
		boxB1.add(lbMaNV = new JLabel("Mã nhân viên:"));
		boxB1.add(txtMaNV = new JTextField(25));
		boxB1.add(lbNgayVaoLV = new JLabel("Ngày vào làm:"));
		boxB1.add(txtNgayVaoLV = new JTextField(25));
		txtMaNV.setPreferredSize(new Dimension(25, 30));
		txtNgayVaoLV.setPreferredSize(new Dimension(25, 30));
		txtMaNV.setEditable(false);
		txtNgayVaoLV.setEditable(false);
		lbMaNV.setFont(new Font(getName(), Font.BOLD, 16));
		lbNgayVaoLV.setFont(new Font(getName(), Font.BOLD, 16));
		lbMaNV.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 25));
		lbNgayVaoLV.setBorder(BorderFactory.createEmptyBorder(0, 80, 0, 25));
		
		boxB.add(boxB2 = new JPanel());
		boxB2.add(lbTen = new JLabel("Họ và tên:"));
		boxB2.add(txtTenNV = new JTextField(25));
		boxB2.add(lbCaLV = new JLabel("Ca làm việc:"));
		boxB2.add(txtCaLV = new JTextField(25));
		txtTenNV.setPreferredSize(new Dimension(25, 30));
		txtCaLV.setPreferredSize(new Dimension(25, 30));
		txtTenNV.setEditable(false);
		txtCaLV.setEditable(false);
		lbTen.setFont(new Font(getName(), Font.BOLD, 16));
		lbCaLV.setFont(new Font(getName(), Font.BOLD, 16));
		lbTen.setPreferredSize(lbMaNV.getPreferredSize());
		lbCaLV.setPreferredSize(lbNgayVaoLV.getPreferredSize());
		lbCaLV.setBorder(BorderFactory.createEmptyBorder(0, 80, 0, 20));
		
		boxB.add(boxB3 = new JPanel());
		boxB3.add(lbEmail = new JLabel("Email:"));
		boxB3.add(txtEmail= new JTextField(25));
		boxB3.add(lbGioVaoLam = new JLabel("Giờ vào làm:"));
		boxB3.add(txtGioVaoLam = new JTextField(25));
		txtGioVaoLam.setPreferredSize(new Dimension(25, 30));
		txtEmail.setPreferredSize(new Dimension(25, 30));
		txtGioVaoLam.setEditable(false);
		txtEmail.setEditable(false);
		lbGioVaoLam.setFont(new Font(getName(), Font.BOLD, 16));
		lbEmail.setFont(new Font(getName(), Font.BOLD, 16));
		lbGioVaoLam.setPreferredSize(lbNgayVaoLV.getPreferredSize());
		lbEmail.setPreferredSize(lbMaNV.getPreferredSize());
		lbGioVaoLam.setBorder(BorderFactory.createEmptyBorder(0, 80, 0, 20));
		
		boxB.add(boxB4 = new JPanel());
		boxB4.add(lbSDT = new JLabel("Số điện thoại:"));
		boxB4.add(txtSDT = new JTextField(25));
		boxB4.add(lbGioKT = new JLabel("Giờ kết thúc:"));
		boxB4.add(txtGioKT = new JTextField(25));
		txtSDT.setPreferredSize(new Dimension(25, 30));
		txtGioKT.setPreferredSize(new Dimension(25, 30));
		txtSDT.setEditable(false);
		txtGioKT.setEditable(false);
		lbSDT.setFont(new Font(getName(), Font.BOLD, 16));
		lbGioKT.setFont(new Font(getName(), Font.BOLD, 16));
		lbSDT.setPreferredSize(lbMaNV.getPreferredSize());
		lbGioKT.setPreferredSize(lbNgayVaoLV.getPreferredSize());
		lbGioKT.setBorder(BorderFactory.createEmptyBorder(0, 80, 0, 20));
		
		boxB.add(boxB5 = new JPanel());
		boxB5.add(lbCV = new JLabel("Chức vụ:"));
		boxB5.add(txtCV = new JTextField(25));
		boxB5.add(lbNgayLV = new JLabel("Ngày làm việc:"));
		boxB5.add(txtNgayLV = new JTextField(25));
		txtCV.setPreferredSize(new Dimension(25, 30));
		txtNgayLV.setPreferredSize(new Dimension(25, 30));
		txtCV.setEditable(false);
		txtNgayLV.setEditable(false);
		lbCV.setFont(new Font(getName(), Font.BOLD, 16));
		lbNgayLV.setFont(new Font(getName(), Font.BOLD, 16));
		lbCV.setPreferredSize(lbMaNV.getPreferredSize());
		lbNgayLV.setPreferredSize(lbNgayVaoLV.getPreferredSize());
		lbNgayLV.setBorder(BorderFactory.createEmptyBorder(0, 80, 0, 20));
		boxB.add(Box.createVerticalStrut(80));
		b2.add(Box.createVerticalStrut(210));
		
		
		CaLamViec caLV = null;
		LichLV_DAO lichLV_dao = new LichLV_DAO();
		ArrayList<LichLV> dsLichLV = lichLV_dao.getAllLichLV();
		for (LichLV lichLV : dsLichLV) {
			if (lichLV.getNvLichLV().getMaNV().equals(nv.getMaNV())) {
				caLV = lichLV.getCaLVLichLV();
			}
		}
		
		txtCaLV.setText(caLV.getTenCaLV());
		txtGioVaoLam.setText(caLV.getGioVaoLam().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
		txtGioKT.setText(caLV.getGioKetThuc().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
		
		txtMaNV.setText(nv.getMaNV());
		txtCV.setText(nv.getChucVuNV());
		txtEmail.setText(nv.getEmailNV());
		txtSDT.setText(nv.getSdtNV());
		txtTenNV.setText(nv.getTenNV());
		txtNgayLV.setText(nv.getNgayBatDauLV().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		txtNgayVaoLV.setText(nv.getNgayBatDauLV().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		return pTTNV;
	}
}
