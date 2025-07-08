package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.NhanVien_DAO;
import dao.TaiKhoan_DAO;
import entity.NhanVien;
import entity.TaiKhoan;

public class QLNhanVien_GUI extends JFrame implements ActionListener, MouseListener {
	private JTextField txtMaNV;
	private JComboBox<String> cbChucVuNV;
	private JTextField txtTenNV;
	private JTextField txtDiaChiNV;
	private JTextField txtSoCCCDNV;
	private JTextField txtSDTNV;
	private DefaultTableModel tbModelNV;
	private JTable tbNV;
	private JTextField txtTimNV;
	private JButton btnTimNV;
	private JButton btnThemNV;
	private JButton btnXoaNV;
	private JButton btnXoaRongNV;
	private JButton btnSuaNV;
	private NhanVien_DAO NV_dao;
	private JFrame jfThemNV;
	private JTextField txtFormMa;
	private JTextField txtFormTen;
	private JTextField txtFormCCCD;
	private JTextField txtFormDiaChi;
	private JTextField txtFormSDT;
	private JComboBox<String> cbformChucVuNV;
	private JButton btnformThemNV;
	private JTextField txtEmailNV;
	private JTextField txtNgayLV;
	private JTextField txtFormEmail;
	private JDateChooser dateChooser;
	private TaiKhoan_DAO TK_dao;
	private JFrame jfXoaNV;
	private JTextField txtFormXoaMa;
	private JTextField txtFormXoaChucVu;
	private JTextField txtFormXoaTen;
	private JTextField txtFormXoaNgayLV;
	private JButton btnformXoaNV;
	private JFrame jfSuaNV;
	private JTextField txtFormSuaMa;
	private JComboBox<String> cbformSuaChucVuNV;
	private JTextField txtFormSuaTen;
	private JTextField txtFormSuaDiaChi;
	private JTextField txtFormSuaCCCD;
	private JTextField txtFormSuaSDT;
	private JTextField txtFormSuaEmail;
	private JButton btnformSuaNV;
	private JDateChooser dateChooserSua;

	public QLNhanVien_GUI() {
		// TODO Auto-generated constructor stub
		super();
		NV_dao = new NhanVien_DAO();
		TK_dao = new TaiKhoan_DAO();
	}

	public JPanel createQLNhanVien() {
		JPanel pQLNhanVien = new JPanel(new BorderLayout());

		JPanel pNorth = new JPanel();
		pQLNhanVien.add(pNorth, BorderLayout.NORTH);
		pNorth.setBackground(new Color(97, 79, 69));
		pNorth.setBorder(BorderFactory.createEmptyBorder(18, 0, 18, 0));
		JLabel lbQLNhanVien;
		pNorth.add(lbQLNhanVien = new JLabel("Quản Lý Nhân Viên"));
		lbQLNhanVien.setForeground(Color.WHITE);
		lbQLNhanVien.setFont(new Font(getName(), Font.BOLD, 30));

		JPanel pCenter = new JPanel(new BorderLayout());
		pQLNhanVien.add(pCenter, BorderLayout.CENTER);

		JPanel pCNorth = new JPanel();
		pCNorth.setLayout(new BoxLayout(pCNorth, BoxLayout.Y_AXIS));
		pCenter.add(pCNorth, BorderLayout.NORTH);
		pCNorth.add(Box.createVerticalStrut(20));
		JPanel pRow0 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pCNorth.add(pRow0);
		JLabel lbRow0;
		pRow0.add(lbRow0 = new JLabel("Thông tin nhân viên"));
		lbRow0.setFont(new Font(getName(), Font.BOLD, 24));
		//------Mã nhân viên + Chức vụ 
		pCNorth.add(Box.createVerticalStrut(20));
		JPanel pRow1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pCNorth.add(pRow1);
		JLabel lbRow1;
		pRow1.add(lbRow1 = new JLabel("   Mã nhân viên: "));
		lbRow1.setFont(new Font(getName(), Font.PLAIN, 18));
		pRow1.add(txtMaNV = new JTextField(25));
		txtMaNV.setPreferredSize(new Dimension(txtMaNV.getWidth(), 30));
		txtMaNV.setFont(new Font(getName(), Font.PLAIN, 14));
		txtMaNV.setEditable(false);

		JLabel lbRow11;
		pRow1.add(lbRow11 = new JLabel("   Chức vụ: "));
		lbRow11.setFont(new Font(getName(), Font.PLAIN, 16));
		String[] listChucVu = { "Nhân viên bán hàng", "Nhân viên pha chế", "Quản lý" };
		pRow1.add(cbChucVuNV = new JComboBox<String>(listChucVu));
		cbChucVuNV.setPreferredSize(txtMaNV.getPreferredSize());
		cbChucVuNV.setFont(new Font(getName(), Font.BOLD, 14));
		//------Họ tên + Địa chỉ
		pCNorth.add(Box.createVerticalStrut(20));
		JPanel pRow2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pCNorth.add(pRow2);
		JLabel lbRow2;
		pRow2.add(lbRow2 = new JLabel("   Họ tên: "));
		lbRow2.setFont(new Font(getName(), Font.PLAIN, 16));
		pRow2.add(txtTenNV = new JTextField(25));
		txtTenNV.setPreferredSize(txtMaNV.getPreferredSize());
		txtTenNV.setFont(txtMaNV.getFont());
		txtTenNV.setEditable(false);

		JLabel lbRow21;
		pRow2.add(lbRow21 = new JLabel("   Địa chỉ: "));
		lbRow21.setFont(new Font(getName(), Font.PLAIN, 16));
		pRow2.add(txtDiaChiNV = new JTextField(25));
		txtDiaChiNV.setPreferredSize(txtMaNV.getPreferredSize());
		txtDiaChiNV.setFont(txtMaNV.getFont());
		txtDiaChiNV.setEditable(false);
		//------CCCD + SĐT
		pCNorth.add(Box.createVerticalStrut(20));
		JPanel pRow3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pCNorth.add(pRow3);
		JLabel lbRow3;
		pRow3.add(lbRow3 = new JLabel("   Số CCCD: "));
		lbRow3.setFont(new Font(getName(), Font.PLAIN, 16));
		pRow3.add(txtSoCCCDNV = new JTextField(25));
		txtSoCCCDNV.setPreferredSize(txtMaNV.getPreferredSize());
		txtSoCCCDNV.setFont(txtMaNV.getFont());
		txtSoCCCDNV.setEditable(false);

		JLabel lbRow31;
		pRow3.add(lbRow31 = new JLabel("   Số điện thoại: "));
		lbRow31.setFont(new Font(getName(), Font.PLAIN, 16));
		pRow3.add(txtSDTNV = new JTextField(25));
		txtSDTNV.setPreferredSize(txtMaNV.getPreferredSize());
		txtSDTNV.setFont(txtMaNV.getFont());
		txtSDTNV.setEditable(false);
		//------Email, ngày bắt đầu 
		pCNorth.add(Box.createVerticalStrut(20));
		JPanel pRow4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pCNorth.add(pRow4);
		JLabel lbRow4;
		pRow4.add(lbRow4 = new JLabel("   Email: "));
		lbRow4.setFont(new Font(getName(), Font.PLAIN, 16));
		pRow4.add(txtEmailNV = new JTextField(25));
		txtEmailNV.setPreferredSize(txtMaNV.getPreferredSize());
		txtEmailNV.setFont(txtMaNV.getFont());
		txtEmailNV.setEditable(false);

		JLabel lbRow41;
		pRow4.add(lbRow41 = new JLabel("   Ngày bắt đầu làm việc : "));
		lbRow41.setFont(new Font(getName(), Font.PLAIN, 16));
		pRow4.add(txtNgayLV = new JTextField(25));
		txtNgayLV.setPreferredSize(txtMaNV.getPreferredSize());
		txtNgayLV.setFont(txtMaNV.getFont());
		txtNgayLV.setEditable(false);

		lbRow1.setPreferredSize(lbRow41.getPreferredSize());
		lbRow11.setPreferredSize(lbRow41.getPreferredSize());
		lbRow2.setPreferredSize(lbRow41.getPreferredSize());
		lbRow21.setPreferredSize(lbRow41.getPreferredSize());
		lbRow3.setPreferredSize(lbRow41.getPreferredSize());
		lbRow31.setPreferredSize(lbRow41.getPreferredSize());
		lbRow4.setPreferredSize(lbRow41.getPreferredSize());
		//	----- table 	
		pCNorth.add(Box.createVerticalStrut(20));
		JPanel pLbDSNV = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pCNorth.add(pLbDSNV);
		JLabel lbDSNV;
		pLbDSNV.add(lbDSNV = new JLabel("Danh sách nhân viên"));
		lbDSNV.setFont(new Font(getName(), Font.BOLD, 24));
		pCNorth.add(Box.createVerticalStrut(20));
		String[] cols = { "Mã nhân viên", "Họ tên", "Chức vụ", "Số CCCD", "Địa chỉ", "Số điện thoại", "Email",
				"Ngày bắt đầu làm việc" };
		tbModelNV = new DefaultTableModel(cols, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tbNV = new JTable(tbModelNV);
		JTableHeader tbHeaderNV = tbNV.getTableHeader();
		tbHeaderNV.setFont(new Font(getName(), Font.BOLD, 16));
		tbHeaderNV.setBackground(new Color(97, 79, 69));
		tbHeaderNV.setForeground(Color.WHITE);
		JScrollPane jScrNV;
		pCenter.add(jScrNV = new JScrollPane(tbNV), BorderLayout.CENTER);
		tbNV.addMouseListener(this);
		//---------- south	
		JSplitPane spRow5 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		pCenter.add(spRow5, BorderLayout.SOUTH);
		spRow5.setDividerSize(1);
		spRow5.setEnabled(false);
		spRow5.setResizeWeight(0.4);

		JPanel pLeft = new JPanel();
		spRow5.add(pLeft);
		pLeft.setBackground(new Color(97, 79, 69));
		pLeft.setBorder(BorderFactory.createEmptyBorder(17, 0, 17, 0));
		JLabel lbRow5;
		pLeft.add(lbRow5 = new JLabel("Mã nhân viên cần tìm: "));
		lbRow5.setFont(new Font(getName(), Font.BOLD, 16));
		lbRow5.setForeground(Color.WHITE);
		pLeft.add(txtTimNV = new JTextField(25));
		txtTimNV.setPreferredSize(txtMaNV.getPreferredSize());
		txtTimNV.setFont(txtMaNV.getFont());
		pLeft.add(btnTimNV = new JButton("Tìm"));
		btnTimNV.setPreferredSize(new Dimension(70, 30));
		btnTimNV.setFont(lbRow5.getFont());
		btnTimNV.setFocusPainted(false);

		JPanel pRight = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
		spRow5.add(pRight);
		pRight.setBackground(new Color(97, 79, 69));
		pRight.setBorder(BorderFactory.createEmptyBorder(17, 0, 17, 0));
		pRight.add(btnThemNV = new JButton("Thêm"));
		btnThemNV.setPreferredSize(new Dimension(80, 30));
		btnThemNV.setFont(lbRow5.getFont());
		btnThemNV.setFocusPainted(false);
		pRight.add(btnXoaNV = new JButton("Xóa"));
		btnXoaNV.setPreferredSize(new Dimension(70, 30));
		btnXoaNV.setFont(lbRow5.getFont());
		btnXoaNV.setFocusPainted(false);
		pRight.add(btnXoaRongNV = new JButton("Xóa rỗng"));
		btnXoaRongNV.setPreferredSize(new Dimension(105, 30));
		btnXoaRongNV.setFont(lbRow5.getFont());
		btnXoaRongNV.setFocusPainted(false);
		pRight.add(btnSuaNV = new JButton("Sửa"));
		btnSuaNV.setPreferredSize(new Dimension(70, 30));
		btnSuaNV.setFont(lbRow5.getFont());
		btnSuaNV.setFocusPainted(false);

		btnSuaNV.addActionListener(this);
		btnThemNV.addActionListener(this);
		btnTimNV.addActionListener(this);
		btnXoaNV.addActionListener(this);
		btnXoaRongNV.addActionListener(this);

		DocDuLieuNhanVienDatabase();
		return pQLNhanVien;
	}

	// --------- form thêm thành viên 
	private void themNVAction() {
		// TODO Auto-generated method stub
		jfThemNV = new JFrame();
		jfThemNV.setVisible(true);
		jfThemNV.setTitle("Bến An Long - Thêm nhân viên");
		jfThemNV.setSize(800, 500);
		jfThemNV.setLocationRelativeTo(null);

		Box pformThemNV = Box.createVerticalBox();
		jfThemNV.add(pformThemNV);

		JPanel pLbThemNV = new JPanel();
		pformThemNV.add(pLbThemNV);
		pLbThemNV.setBackground(new Color(97, 79, 69));
		JLabel lbThemNV;
		pLbThemNV.add(lbThemNV = new JLabel("THÊM NHÂN VIÊN"));
		lbThemNV.setFont(new Font(getName(), Font.BOLD, 27));
		lbThemNV.setForeground(Color.WHITE);
		lbThemNV.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

		pformThemNV.add(Box.createVerticalStrut(20));
		JPanel pRow1 = new JPanel();
		pformThemNV.add(pRow1);
		JLabel lbRow1 = new JLabel("Mã nhân viên: ");
		pRow1.add(lbRow1);
		pRow1.add(txtFormMa = new JTextField(15));
		lbRow1.setFont(new Font(getName(), Font.BOLD, 15));
		txtFormMa.setFont(new Font(getName(), Font.PLAIN, 15));
		txtFormMa.setEditable(false);
		txtFormMa.setText(NV_dao.createMaNV());
		pRow1.add(Box.createHorizontalStrut(10));
		JLabel lbRow6 = new JLabel("Chức vụ: ");
		pRow1.add(lbRow6);
		lbRow6.setFont(lbRow1.getFont());
		String[] listChucVu = { "Nhân viên bán hàng", "Nhân viên pha chế", "Quản lý" };
		pRow1.add(cbformChucVuNV = new JComboBox<String>(listChucVu));
		cbformChucVuNV.setFont(new Font(getName(), Font.BOLD, 14));

		pformThemNV.add(Box.createVerticalStrut(5));
		JPanel pRow2 = new JPanel();
		pformThemNV.add(pRow2);
		JLabel lbRow2 = new JLabel("Họ tên: ");
		pRow2.add(lbRow2);
		pRow2.add(txtFormTen = new JTextField());
		lbRow2.setFont(lbRow1.getFont());
		txtFormTen.setFont(txtFormMa.getFont());

		pRow2.add(Box.createHorizontalStrut(10));
		JLabel lbRow7 = new JLabel("Địa chỉ: ");
		pRow2.add(lbRow7);
		pRow2.add(txtFormDiaChi = new JTextField());
		lbRow7.setFont(lbRow1.getFont());
		txtFormDiaChi.setFont(txtFormMa.getFont());

		pformThemNV.add(Box.createVerticalStrut(5));
		JPanel pRow3 = new JPanel();
		pformThemNV.add(pRow3);
		JLabel lbRow3 = new JLabel("CCCD: ");
		pRow3.add(lbRow3);
		pRow3.add(txtFormCCCD = new JTextField());
		lbRow3.setFont(lbRow1.getFont());
		txtFormCCCD.setFont(txtFormMa.getFont());

		pRow3.add(Box.createHorizontalStrut(10));
		JLabel lbRow5 = new JLabel("Số điện thoại: ");
		pRow3.add(lbRow5);
		pRow3.add(txtFormSDT = new JTextField());
		lbRow5.setFont(lbRow1.getFont());
		txtFormSDT.setFont(txtFormMa.getFont());

		pformThemNV.add(Box.createVerticalStrut(5));
		JPanel pRow4 = new JPanel();
		pformThemNV.add(pRow4);
		JLabel lbRow4 = new JLabel("Email: ");
		pRow4.add(lbRow4);
		pRow4.add(txtFormEmail = new JTextField());
		lbRow4.setFont(lbRow1.getFont());
		txtFormEmail.setFont(txtFormMa.getFont());
		pRow4.add(Box.createHorizontalStrut(10));
		JLabel lbNgayBD;
		pRow4.add(lbNgayBD = new JLabel("Ngày bắt đầu làm việc  :"));
		lbNgayBD.setFont(lbRow1.getFont());
		// Tạo JDateChooser
		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd-MM-yyyy");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		dateChooser.setDate(calendar.getTime()); // java.util.Date selectedDate = dateChooser.getDate(); LẤY GIÁ TRỊ
													// TRONG Ô NGÀY

		pRow4.add(dateChooser);

		pformThemNV.add(Box.createVerticalStrut(10));
		JPanel pRow7 = new JPanel();
		pformThemNV.add(pRow7);
		pRow7.add(btnformThemNV = new JButton("Thêm nhân viên"));
		btnformThemNV.setFont(lbRow1.getFont());
		btnformThemNV.setFocusPainted(false);
		btnformThemNV.setBackground(new Color(97, 79, 69));
		btnformThemNV.setForeground(Color.WHITE);
		btnformThemNV.setPreferredSize(new Dimension(200, 50));

		lbRow5.setPreferredSize(lbNgayBD.getPreferredSize());
		lbRow2.setPreferredSize(lbNgayBD.getPreferredSize());
		lbRow3.setPreferredSize(lbNgayBD.getPreferredSize());
		lbRow4.setPreferredSize(lbNgayBD.getPreferredSize());
		lbRow6.setPreferredSize(lbNgayBD.getPreferredSize());
		lbRow7.setPreferredSize(lbNgayBD.getPreferredSize());
		lbRow1.setPreferredSize(lbNgayBD.getPreferredSize());
		cbformChucVuNV.setPreferredSize(txtFormMa.getPreferredSize());
		txtFormCCCD.setPreferredSize(txtFormMa.getPreferredSize());
		txtFormDiaChi.setPreferredSize(txtFormMa.getPreferredSize());
		txtFormSDT.setPreferredSize(txtFormMa.getPreferredSize());
		txtFormTen.setPreferredSize(txtFormMa.getPreferredSize());
		dateChooser.setPreferredSize(txtFormMa.getPreferredSize());
		txtFormEmail.setPreferredSize(txtFormMa.getPreferredSize());

		btnformThemNV.addActionListener(this);

	}

	private void xoaRong() {
		// TODO Auto-generated method stub
		txtMaNV.setText("");
		txtTenNV.setText("");
		txtDiaChiNV.setText("");
		txtSoCCCDNV.setText("");
		txtSDTNV.setText("");
		txtTimNV.setText("");
		cbChucVuNV.setSelectedIndex(0);
		txtEmailNV.setText("");
		txtNgayLV.setText("");

	}

	//	--------- kiểm tra thông tin trang đăng ký thành viên 
	private boolean KiemTraDKTV(JTextField txtHT, JTextField txtCCCD, JTextField txtDiaChi, JTextField txtSDT,
			JTextField txtEmail) {

		// TODO Auto-generated method stub
		String HoTen = txtHT.getText().trim();
		String sdt = txtSDT.getText().trim();
		String DiaChi = txtDiaChi.getText().trim();
		String CCCD = txtCCCD.getText().trim();
		String emailKH = txtEmail.getText().trim();
		if (HoTen.isEmpty()) {
			JOptionPane.showMessageDialog(this, "ERROR : Tên không được để rỗng!");
			txtHT.requestFocus();
			return false;
		}
		if (!(CCCD.matches("[0-9]{12}"))) {
			JOptionPane.showMessageDialog(this, "ERROR : CCCD không được để rỗng, phải 12 chữ số ");
			txtCCCD.requestFocus();
			return false;
		}
		if (DiaChi.isEmpty()) {
			JOptionPane.showMessageDialog(this, "ERROR : Địa chỉ không rỗng ");
			txtDiaChi.requestFocus();
			return false;

		}

		if (!(sdt.matches("[0-9]{10}"))) {
			JOptionPane.showMessageDialog(this, "ERROR : Số điện thoại không được để rỗng, phải 10 chữ số ");
			txtSDT.requestFocus();
			return false;

		}
		if (!(emailKH.length() > 0 && emailKH.matches("^[\\w.-]+@([\\w-]+\\.)+[\\w]{2,4}$" + ""))) {
			JOptionPane.showMessageDialog(this, "ERROR : Email không được để rỗng,Email phải đúng định dạng ");
			txtEmail.requestFocus();
			return false;

		}

		return true;
	}

	private void themNV() {
		// TODO Auto-generated method stub
		if (KiemTraDKTV(txtFormTen, txtFormCCCD, txtFormDiaChi, txtFormSDT, txtFormEmail)) {
			String maNV = txtFormMa.getText().trim();
			String HoTen = txtFormTen.getText().trim();
			String sdt = txtFormSDT.getText().trim();
			String CCCD = txtFormCCCD.getText().trim();
			String DC = txtFormDiaChi.getText().trim();
			String Email = txtFormEmail.getText().trim();
			String ChucVu = (String) cbformChucVuNV.getSelectedItem();
			//----------tạo tk mã : mã nv ; mk : mã+ 3 số cuối sdt 
			String mkTaiKhoan = maNV + sdt.substring(sdt.length() - 3);
			TaiKhoan tkNV = new TaiKhoan(maNV, mkTaiKhoan);
			TK_dao.insertTK(tkNV);
			Date selectedDate = dateChooser.getDate();
			LocalDate localDate = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			
			NhanVien nv = new NhanVien(maNV, HoTen, DC, CCCD, sdt, Email, localDate, ChucVu, 0.0, tkNV);

			try {
				if (NV_dao.insertNV(nv)) {
					JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công ");
					DocDuLieuNhanVienDatabase();
					jfThemNV.dispose();
				} else
					JOptionPane.showMessageDialog(this, "Không thể thêm nhân viên");
			} catch (Exception e) {
				// TODO: handle exception

			}
		}
	}

	private void DocDuLieuNhanVienDatabase() {
		// TODO Auto-generated method stub
		tbModelNV.getDataVector().removeAllElements();
		NhanVien_DAO ds = new NhanVien_DAO();

		List<NhanVien> list = ds.getAllNV();
		for (NhanVien s : list) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			String dateString = s.getNgayBatDauLV().format(formatter);
			String[] rowData = { s.getMaNV(), s.getTenNV(), s.getChucVuNV(), s.getCccdNV(), s.getDiaChiNV(),
					s.getSdtNV(), s.getEmailNV(), dateString };
			tbModelNV.addRow(rowData);
		}
		tbNV.setModel(tbModelNV);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = tbNV.getSelectedRow();
		txtMaNV.setText(tbNV.getValueAt(row, 0).toString());
		txtTenNV.setText(tbNV.getValueAt(row, 1).toString());
		cbChucVuNV.setSelectedItem(tbNV.getValueAt(row, 2).toString());
		txtSoCCCDNV.setText(tbNV.getValueAt(row, 3).toString());
		txtDiaChiNV.setText(tbNV.getValueAt(row, 4).toString());
		txtSDTNV.setText(tbNV.getValueAt(row, 5).toString());
		txtEmailNV.setText(tbNV.getValueAt(row, 6).toString());
		txtNgayLV.setText(tbNV.getValueAt(row, 7).toString());

	}

	public void actionTim() {
		String timMa = txtTimNV.getText();
		if (!(timMa.isEmpty())) {
			for (int i = 0; i < tbNV.getRowCount(); i++) {
				if (tbNV.getValueAt(i, 0).toString().equals(timMa)) {
					tbNV.setRowSelectionInterval(i, i);
					mouseClicked(null);
					tbNV.scrollRectToVisible(tbNV.getCellRect(i, 0, true));
					txtTimNV.setText("");
					return;
				}
			}
			JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên: " + timMa);
		}

		JOptionPane.showMessageDialog(this, "Nhập mã nhân viên cần tìm ");
	}

	private void xoaNVAction() {
		// TODO Auto-generated method stub
		if (txtMaNV.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Chọn nhân viên cần xóa");
			return;
		}
		jfXoaNV = new JFrame();
		jfXoaNV.setVisible(true);
		jfXoaNV.setTitle("Bến An Long - Xóa nhân viên");
		jfXoaNV.setSize(800, 400);
		jfXoaNV.setLocationRelativeTo(null);

		Box pformXoaNV = Box.createVerticalBox();
		jfXoaNV.add(pformXoaNV);

		JPanel pLbXoaNV = new JPanel();
		pformXoaNV.add(pLbXoaNV);
		pLbXoaNV.setBackground(new Color(97, 79, 69));
		JLabel pLXoaNV;
		pLbXoaNV.add(pLXoaNV = new JLabel("XÓA NHÂN VIÊN"));
		pLXoaNV.setFont(new Font(getName(), Font.BOLD, 27));
		pLXoaNV.setForeground(Color.WHITE);
		pLXoaNV.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

		pformXoaNV.add(Box.createVerticalStrut(20));
		JPanel pRow1 = new JPanel();
		pformXoaNV.add(pRow1);
		JLabel lbRow1 = new JLabel("Mã nhân viên: ");
		pRow1.add(lbRow1);
		pRow1.add(txtFormXoaMa = new JTextField(15));
		lbRow1.setFont(new Font(getName(), Font.BOLD, 15));
		txtFormXoaMa.setFont(new Font(getName(), Font.PLAIN, 15));
		txtFormXoaMa.setEditable(false);
		txtFormXoaMa.setText(txtMaNV.getText());
		pRow1.add(Box.createHorizontalStrut(10));
		JLabel lbRow6 = new JLabel("Chức vụ: ");
		pRow1.add(lbRow6);
		pRow1.add(txtFormXoaChucVu = new JTextField(15));
		lbRow6.setFont(lbRow1.getFont());
		txtFormXoaChucVu.setFont(txtFormXoaMa.getFont());
		txtFormXoaChucVu.setEditable(false);
		txtFormXoaChucVu.setText(cbChucVuNV.getSelectedItem().toString());

		pformXoaNV.add(Box.createVerticalStrut(5));
		JPanel pRow2 = new JPanel();
		pformXoaNV.add(pRow2);
		JLabel lbRow2 = new JLabel("Họ tên: ");
		pRow2.add(lbRow2);
		pRow2.add(txtFormXoaTen = new JTextField(15));
		lbRow2.setFont(lbRow1.getFont());
		txtFormXoaTen.setFont(txtFormXoaMa.getFont());
		txtFormXoaTen.setEditable(false);
		txtFormXoaTen.setText(txtTenNV.getText());

		pRow2.add(Box.createHorizontalStrut(10));
		JLabel lbRow7 = new JLabel("Ngày bắt đầu làm việc : ");
		pRow2.add(lbRow7);
		pRow2.add(txtFormXoaNgayLV = new JTextField(15));
		lbRow7.setFont(lbRow1.getFont());
		txtFormXoaNgayLV.setFont(txtFormXoaMa.getFont());
		txtFormXoaNgayLV.setEditable(false);
		txtFormXoaNgayLV.setText(txtNgayLV.getText());

		pformXoaNV.add(Box.createVerticalStrut(10));
		JPanel pRow7 = new JPanel();
		pformXoaNV.add(pRow7);
		pRow7.add(btnformXoaNV = new JButton("Xóa nhân viên"));
		btnformXoaNV.setFont(lbRow1.getFont());
		btnformXoaNV.setFocusPainted(false);
		btnformXoaNV.setBackground(new Color(97, 79, 69));
		btnformXoaNV.setForeground(Color.WHITE);
		btnformXoaNV.setPreferredSize(new Dimension(200, 50));

		lbRow2.setPreferredSize(lbRow7.getPreferredSize());
		lbRow6.setPreferredSize(lbRow7.getPreferredSize());
		lbRow1.setPreferredSize(lbRow7.getPreferredSize());

		btnformXoaNV.addActionListener(this);

	}

	private void suaNVAction() {
		// TODO Auto-generated method stub
		if (txtMaNV.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Chọn nhân viên cần sửa");
			return;
		}
		jfSuaNV = new JFrame();
		jfSuaNV.setVisible(true);
		jfSuaNV.setTitle("Bến An Long - Sửa nhân viên");
		jfSuaNV.setSize(800, 500);
		jfSuaNV.setLocationRelativeTo(null);

		Box pformSuaNV = Box.createVerticalBox();
		jfSuaNV.add(pformSuaNV);

		JPanel pLbSuaNV = new JPanel();
		pformSuaNV.add(pLbSuaNV);
		pLbSuaNV.setBackground(new Color(97, 79, 69));
		JLabel lbSuaNV;
		pLbSuaNV.add(lbSuaNV = new JLabel("SỬA NHÂN VIÊN"));
		lbSuaNV.setFont(new Font(getName(), Font.BOLD, 27));
		lbSuaNV.setForeground(Color.WHITE);
		lbSuaNV.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

		pformSuaNV.add(Box.createVerticalStrut(20));
		JPanel pRow1 = new JPanel();
		pformSuaNV.add(pRow1);
		JLabel lbRow1 = new JLabel("Mã nhân viên: ");
		pRow1.add(lbRow1);
		pRow1.add(txtFormSuaMa = new JTextField(15));
		lbRow1.setFont(new Font(getName(), Font.BOLD, 15));
		txtFormSuaMa.setFont(new Font(getName(), Font.PLAIN, 15));
		txtFormSuaMa.setEditable(false);
		txtFormSuaMa.setText(txtMaNV.getText());
		pRow1.add(Box.createHorizontalStrut(10));
		JLabel lbRow6 = new JLabel("Chức vụ: ");
		pRow1.add(lbRow6);
		lbRow6.setFont(lbRow1.getFont());
		String[] listChucVu = { "Nhân viên bán hàng", "Nhân viên pha chế", "Quản lý" };
		pRow1.add(cbformSuaChucVuNV = new JComboBox<String>(listChucVu));
		cbformSuaChucVuNV.setFont(new Font(getName(), Font.BOLD, 14));
		cbformSuaChucVuNV.setSelectedItem(cbChucVuNV.getSelectedItem());

		pformSuaNV.add(Box.createVerticalStrut(5));
		JPanel pRow2 = new JPanel();
		pformSuaNV.add(pRow2);
		JLabel lbRow2 = new JLabel("Họ tên: ");
		pRow2.add(lbRow2);
		pRow2.add(txtFormSuaTen = new JTextField());
		lbRow2.setFont(lbRow1.getFont());
		txtFormSuaTen.setFont(txtFormSuaMa.getFont());
		txtFormSuaTen.setText(txtTenNV.getText());

		pRow2.add(Box.createHorizontalStrut(10));
		JLabel lbRow7 = new JLabel("Địa chỉ: ");
		pRow2.add(lbRow7);
		pRow2.add(txtFormSuaDiaChi = new JTextField());
		lbRow7.setFont(lbRow1.getFont());
		txtFormSuaDiaChi.setFont(txtFormSuaMa.getFont());
		txtFormSuaDiaChi.setText(txtDiaChiNV.getText());

		pformSuaNV.add(Box.createVerticalStrut(5));
		JPanel pRow3 = new JPanel();
		txtFormSuaDiaChi.add(pRow3);
		JLabel lbRow3 = new JLabel("CCCD: ");
		pRow3.add(lbRow3);
		pRow3.add(txtFormSuaCCCD = new JTextField());
		lbRow3.setFont(lbRow1.getFont());
		txtFormSuaCCCD.setFont(txtFormSuaMa.getFont());
		txtFormSuaCCCD.setText(txtSoCCCDNV.getText());

		pRow3.add(Box.createHorizontalStrut(10));
		JLabel lbRow5 = new JLabel("Số điện thoại: ");
		pRow3.add(lbRow5);
		pRow3.add(txtFormSuaSDT = new JTextField());
		lbRow5.setFont(lbRow1.getFont());
		txtFormSuaSDT.setFont(txtFormSuaMa.getFont());
		txtFormSuaSDT.setText(txtSDTNV.getText());

		pformSuaNV.add(Box.createVerticalStrut(5));
		JPanel pRow4 = new JPanel();
		pformSuaNV.add(pRow4);
		JLabel lbRow4 = new JLabel("Email: ");
		pRow4.add(lbRow4);
		pRow4.add(txtFormSuaEmail = new JTextField());
		lbRow4.setFont(lbRow1.getFont());
		txtFormSuaEmail.setFont(txtFormSuaMa.getFont());
		txtFormSuaEmail.setText(txtEmailNV.getText());

		pRow4.add(Box.createHorizontalStrut(10));
		JLabel lbNgayBD;
		pRow4.add(lbNgayBD = new JLabel("Ngày bắt đầu làm việc  :"));
		lbNgayBD.setFont(lbRow1.getFont());
		// Tạo JDateChooser
		dateChooserSua = new JDateChooser();
		dateChooserSua.setDateFormatString("dd-MM-yyyy");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

		Date ngayDate;
		try {
			ngayDate = dateFormat.parse(txtNgayLV.getText());
			// Đặt ngày vào JDateChooser
			dateChooserSua.setDate(ngayDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		pRow4.add(dateChooserSua);

		pformSuaNV.add(Box.createVerticalStrut(10));
		JPanel pRow7 = new JPanel();
		pformSuaNV.add(pRow7);
		pRow7.add(btnformSuaNV = new JButton("Sửa nhân viên"));
		btnformSuaNV.setFont(lbRow1.getFont());
		btnformSuaNV.setFocusPainted(false);
		btnformSuaNV.setBackground(new Color(97, 79, 69));
		btnformSuaNV.setForeground(Color.WHITE);
		btnformSuaNV.setPreferredSize(new Dimension(200, 50));

		lbRow5.setPreferredSize(lbNgayBD.getPreferredSize());
		lbRow2.setPreferredSize(lbNgayBD.getPreferredSize());
		lbRow3.setPreferredSize(lbNgayBD.getPreferredSize());
		lbRow4.setPreferredSize(lbNgayBD.getPreferredSize());
		lbRow6.setPreferredSize(lbNgayBD.getPreferredSize());
		lbRow7.setPreferredSize(lbNgayBD.getPreferredSize());
		lbRow1.setPreferredSize(lbNgayBD.getPreferredSize());
		cbformSuaChucVuNV.setPreferredSize(txtFormSuaMa.getPreferredSize());
		txtFormSuaCCCD.setPreferredSize(txtFormSuaMa.getPreferredSize());
		txtFormSuaDiaChi.setPreferredSize(txtFormSuaMa.getPreferredSize());
		txtFormSuaSDT.setPreferredSize(txtFormSuaMa.getPreferredSize());
		txtFormSuaTen.setPreferredSize(txtFormSuaMa.getPreferredSize());
		dateChooserSua.setPreferredSize(txtFormSuaMa.getPreferredSize());
		txtFormSuaEmail.setPreferredSize(txtFormSuaMa.getPreferredSize());

		btnformSuaNV.addActionListener(this);

	}

	private void SuaNV() {
		// TODO Auto-generated method stub
		if (KiemTraDKTV(txtFormSuaTen, txtFormSuaCCCD, txtFormSuaDiaChi, txtFormSuaSDT, txtFormSuaEmail)) {
			String maNV = txtFormSuaMa.getText().trim();
			String HoTen = txtFormSuaTen.getText().trim();
			String sdt = txtFormSuaSDT.getText().trim();
			String CCCD = txtFormSuaCCCD.getText().trim();
			String DC = txtFormSuaDiaChi.getText().trim();
			String Email = txtFormSuaEmail.getText().trim();
			String ChucVu = (String) cbformSuaChucVuNV.getSelectedItem();
			NhanVien nhanVien = NV_dao.getNV(maNV);
			TaiKhoan tkNV = nhanVien.getTaiKhoanNV();
			
			Date selectedDate = dateChooserSua.getDate();
			LocalDate localDate = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

			NhanVien nv = new NhanVien(maNV, HoTen, DC, CCCD, sdt, Email, localDate, ChucVu, 0.0, tkNV);

			try {
				if (NV_dao.updateNV(nv)) {
					JOptionPane.showMessageDialog(this, "Cập nhật nhân viên thành công ");
					DocDuLieuNhanVienDatabase();
					jfSuaNV.dispose();
				} else
					JOptionPane.showMessageDialog(this, "Không thể cập nhật nhân viên");
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if (source.equals(btnSuaNV)) {
			suaNVAction();
		}
		if (source.equals(btnThemNV)) {
			themNVAction();
		}
		if (source.equals(btnTimNV)) {
			actionTim();
		}
		if (source.equals(btnXoaNV)) {
			xoaNVAction();
		}
		if (source.equals(btnXoaRongNV)) {
			xoaRong();
		}
		if (source.equals(btnformThemNV)) {
			themNV();
		}
		if (source.equals(btnformXoaNV)) {
			actionFormXoa();
		}
		if (source.equals(btnformSuaNV)) {
			SuaNV();
		}
	}

	private void actionFormXoa() {
		// TODO Auto-generated method stub
		int row = tbNV.getSelectedRow();
		if (row >= 0) {
			String manvXoa = (String) tbNV.getValueAt(row, 0);

			int nhacNho = JOptionPane.showConfirmDialog(this,
					"Chắc chắn xóa nhân viên  " + txtFormXoaMa.getText() + " ?", "chú ý", JOptionPane.YES_NO_OPTION);
			if (nhacNho == JOptionPane.YES_OPTION) {
				if (NV_dao.deleteNV(manvXoa)) {
					tbModelNV.removeRow(row);
					xoaRong();
					JOptionPane.showMessageDialog(this, "Xóa thành công");
					jfXoaNV.dispose();
				} else {
					JOptionPane.showMessageDialog(this, "Không thể xóa");
				}
			}
		}
	}
}
