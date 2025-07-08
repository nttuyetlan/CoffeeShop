package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import control.XuatHoaDonPDF;
import dao.ChiTietHD_DAO;
import dao.HoaDon_DAO;
import entity.Ban;
import entity.ChiTietHD;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;

public class ChiTietHoaDon_GUI extends JFrame implements ActionListener {

	private JTextField txtMaHD;
	private JTextField txtNgayLapHD;
	private JTextField txtTenNV;
	private JTextField txtSoBan;
	private DefaultTableModel tblModelHD;
	private JTable tbHD;
	private JTextField txtGiamGia;
	private JTextField txtTongTien;
	private JButton btnHuyHD;
	private JButton btnXacNhan;
	private HoaDon hd;
	private ArrayList<ChiTietHD> dsCtHD;

	public ChiTietHoaDon_GUI(HoaDon hd, ArrayList<ChiTietHD> dsCtHD) {
		// TODO Auto-generated constructor stub
		setTitle("Chi tiết hóa đơn");
		setSize(600, 650);
		setLocationRelativeTo(null);
		this.hd = hd;
		this.dsCtHD = dsCtHD;
		
		JPanel pNorth = new JPanel();
		add(pNorth, BorderLayout.NORTH);
		pNorth.setLayout(new BorderLayout());
		JPanel pDiaChi;
		pNorth.add(pDiaChi = new JPanel(), BorderLayout.NORTH);
		pDiaChi.setLayout(new BoxLayout(pDiaChi, BoxLayout.Y_AXIS));
		JPanel panel1, panel2, panel3, panel4;
		panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel1.add(new JLabel("12 Nguyễn Văn Bảo, Phường 4, Gò Vấp, Hồ Chí Minh"));
		panel2.add(new JLabel("0999999999"));
		panel3.add(new JLabel("Hóa Đơn Thanh Toán"));
		panel4.add(new JLabel("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -"));
		pDiaChi.add(Box.createVerticalStrut(10));
		pDiaChi.add(panel1);
		pDiaChi.add(Box.createVerticalStrut(-8));
		pDiaChi.add(panel2);
		pDiaChi.add(Box.createVerticalStrut(-8));
		pDiaChi.add(panel3);
		pDiaChi.add(panel4);
		pDiaChi.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JPanel pCenter = new JPanel();
		add(pCenter, BorderLayout.CENTER);
		pCenter.setLayout(new BorderLayout());
		JPanel pCNorth = new JPanel();
		pCenter.add(pCNorth, BorderLayout.NORTH);
		
		pCNorth.setLayout(new BoxLayout(pCNorth, BoxLayout.Y_AXIS));
		JPanel p1, p2, p3, p4;
		p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel lbl1, lbl2, lbl3, lbl4;
		p1.add(lbl1 = new JLabel("Mã hóa đơn:  "));
		p2.add(lbl2 = new JLabel("Nhân viên:  "));
		p3.add(lbl3 = new JLabel("Ngày lập:  "));
		p4.add(lbl4 = new JLabel("Bàn:  "));
		lbl2.setPreferredSize(lbl1.getPreferredSize());
		lbl3.setPreferredSize(lbl1.getPreferredSize());
		lbl4.setPreferredSize(lbl1.getPreferredSize());
		p1.add(txtMaHD = new JTextField(10));
		p2.add(txtTenNV = new JTextField(10));
		p3.add(txtNgayLapHD = new JTextField(10));
		p4.add(txtSoBan = new JTextField(10));
		txtMaHD.setEditable(false);
		txtMaHD.setBorder(null);
		txtTenNV.setEditable(false);
		txtTenNV.setBorder(null);
		txtNgayLapHD.setEditable(false);
		txtNgayLapHD.setBorder(null);
		txtSoBan.setEditable(false);
		txtSoBan.setBorder(null);
		pCNorth.add(Box.createVerticalStrut(10));
		pCNorth.add(p1);
		pCNorth.add(Box.createVerticalStrut(-5));
		pCNorth.add(p2);
		pCNorth.add(Box.createVerticalStrut(-5));
		pCNorth.add(p3);
		pCNorth.add(Box.createVerticalStrut(-5));
		pCNorth.add(p4);
		pCNorth.add(Box.createVerticalStrut(20));
		pCNorth.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JPanel pCCenter = new JPanel();
		pCCenter.setLayout(new BoxLayout(pCCenter, BoxLayout.Y_AXIS));
		String[] cols = {"Mã", "Tên", "SL", "Đơn giá", "Thành Tiền"};
		tblModelHD = new DefaultTableModel(cols, 0);
		tbHD = new JTable(tblModelHD);
		tbHD.setBorder(BorderFactory.createEmptyBorder());
		tbHD.getTableHeader().setBorder(BorderFactory.createEmptyBorder());
		tbHD.setShowGrid(false);
		tbHD.setRowSelectionAllowed(true);
		tbHD.setColumnSelectionAllowed(true);
		JScrollPane scroll = new JScrollPane(tbHD);
		pCCenter.add(scroll);
		JPanel pA, pB;
		pA = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pB = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblA, lblB;
		pA.add(lblA = new JLabel("Giảm giá:  "));
		pB.add(lblB = new JLabel("Tổng tiền:  "));
		pA.add(txtGiamGia = new JTextField(20));
		pB.add(txtTongTien = new JTextField(20));
		txtGiamGia.setEditable(false);
		txtGiamGia.setBorder(null);
		txtTongTien.setEditable(false);
		txtTongTien.setBorder(null);
		pCCenter.add(pA);
		pCCenter.add(pB);
		pCenter.add(pCCenter, BorderLayout.CENTER);
		
		JPanel pCSouth = new JPanel();
		pCSouth.setLayout(new BoxLayout(pCSouth, BoxLayout.Y_AXIS));
		pCenter.add(pCSouth, BorderLayout.SOUTH);
		JPanel pn1, pn2, pn3, pn4, pn5, pn6, pn7;
		pn1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pn2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pn3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pn4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pn5 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pn6 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pn7 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel label1;
		pn1.add(label1 = new JLabel("Cảm ơn Qúy khách và Hẹn gặp lại"));
		label1.setFont(new Font(getName(), Font.BOLD, 14));
		label1.setForeground(Color.red);
		JLabel label2;
		pn2.add(label2 = new JLabel("Quyền lợi khi trở thành hội viên được áp dụng"));
		JLabel label3;
		pn3.add(label3 = new JLabel("các chương trình khuyến mãi và tích điểm trên hóa đơn"));
		JLabel label4;
		pn4.add(label4  = new JLabel("Cứ 10.000 đồng => 5 điểm"));
		JLabel label5;
		pn5.add(label5  = new JLabel("Đủ 100 điểm => chiết khấu 5%"));
		JLabel label6;
		pn6.add(label6  = new JLabel("Cứ 300 điểm => chiết khấu 15%"));
		JLabel label7;
		pn7.add(label7  = new JLabel("Cứ 500 điểm => ciết khấu 25%"));
		pCSouth.add(Box.createVerticalStrut(10));
		pCSouth.add(pn1);
		pCSouth.add(Box.createVerticalStrut(10));
		pCSouth.add(pn2);
		pCSouth.add(Box.createVerticalStrut(-5));
		pCSouth.add(pn3);
		pCSouth.add(Box.createVerticalStrut(-5));
		pCSouth.add(pn4);
		pCSouth.add(Box.createVerticalStrut(-5));
		pCSouth.add(pn5);
		pCSouth.add(Box.createVerticalStrut(-5));
		pCSouth.add(pn6);
		pCSouth.add(Box.createVerticalStrut(-5));
		pCSouth.add(pn7);
		pCSouth.add(Box.createVerticalStrut(10));
		pCSouth.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JPanel pSouth = new JPanel();
		add(pSouth, BorderLayout.SOUTH);
		pSouth.add(btnHuyHD = new JButton("Hủy"));
		pSouth.add(btnXacNhan = new JButton("Xác Nhận"));
		btnHuyHD.setForeground(Color.WHITE);
		btnXacNhan.setForeground(Color.WHITE);
		btnHuyHD.setBackground(Color.RED);
		btnXacNhan.setBackground(Color.GREEN);
		btnHuyHD.setFocusPainted(false);
		btnXacNhan.setFocusPainted(false);
	}
	
	public void showHoaDon() {
		this.setVisible(true);
		if (hd != null)
			txtMaHD.setText(hd.getMaHD());
		if (hd.getNvHD() != null)
			txtTenNV.setText(hd.getNvHD().getTenNV());
		LocalDateTime ngayLapHD = LocalDateTime.now();
		String ngayLapHDS = ngayLapHD.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
		hd.setNgayLapHD(ngayLapHD);
		txtNgayLapHD.setText(ngayLapHDS);
		if (hd.getBanHD() != null)
			txtSoBan.setText(hd.getBanHD().getMaBan());
		if (dsCtHD != null)
			for (ChiTietHD ctHD : dsCtHD) {
				String[] row = {ctHD.getSanPhamCTHD().getMaSP(), ctHD.getSanPhamCTHD().getTenSP(), ctHD.getSoLuongSP() + "", ctHD.getSanPhamCTHD().getDonGiaSP() + "", ctHD.getThanhTien() + ""};
				tblModelHD.addRow(row);
			}
		txtGiamGia.setText(hd.getKmHD().getGiaTriKM() + "");
		txtTongTien.setText(hd.getThanhToanHD() + "");
		btnHuyHD.addActionListener(this);
		btnXacNhan.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if (source.equals(btnHuyHD)) {
			this.setVisible(false);
			dsCtHD.clear();
		}
		else {
			HoaDon_DAO hd_dao = new HoaDon_DAO();
			ChiTietHD_DAO ctHD_dao = new ChiTietHD_DAO();
			if (hd_dao.insertHD(hd)) {
				XuatHoaDonPDF xuatPDF = new XuatHoaDonPDF();
				for (ChiTietHD ctHD : dsCtHD)
					ctHD_dao.insertCtHD(ctHD);
				xuatPDF.generateInvoice("HoaDonPDF//" + hd.getMaHD() + ".pdf", dsCtHD, hd);
				dsCtHD.clear();
				this.setVisible(false);
			} else {
				JOptionPane.showMessageDialog(null, "Lỗi insert DB!");
			}
		}
	}
}
