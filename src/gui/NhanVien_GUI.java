package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.text.JTextComponent;

import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.SanPham_DAO;
import entity.NhanVien;
import entity.SanPham;

public class NhanVien_GUI extends JFrame implements ActionListener {
	private JButton btnMenu;
	private JButton btnQLBan;
	private JButton btnLSHoaDon;
	private JButton btnDKTV;
	private JButton btnTTNV;
	private JButton btnDangXuat;
	private JPanel pTTNV;
	private JPanel pLSHoaDon;
	private JTextField txtfromHoTenKH;
	private JTextField txtfromSDTKH;
	private JTextField txtfromEmailKH;
	private JButton btnformDKTV;
	private JTextField txtMaNV;
	private JTextField txtNgayVaoLV;
	private JTextField txtTenNV;
	private JTextField txtCaLV;
	private JLabel lbLogo;
	private JButton btnTimHD;
	private JButton btnFileHD;
	private DefaultTableModel tbModelHD;
	private JTable tableHD;
	private JTextField txtTongHD;
	private JButton btnXemCTHD;
	private JSplitPane pBanHangDatBan;
	private BanHangDatBan_GUI banHangDatBan_GUI;
	private CardLayout cardMain;
	private JPanel cardPanelMain;
	private JTextField txtEmailNV;
	private JTextField txtGioVaoLam;
	private JTextField txtSDTNV;
	private JTextField txtGioKT;
	private JTextField txtCVNV;
	private JTextField txtNgayLV;
	private LSHoaDon_GUI hoaDon_GUI;
	private TaiKhoan_GUI pTTNV_GUI;
	private NhanVien nv;

	public NhanVien_GUI(NhanVien nv) {
		super("Bến An Long - Nhân viên");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		this.nv = nv;
		
		//================Main West================
		JPanel pWest = new JPanel();
		add(pWest, BorderLayout.WEST);
		pWest.setLayout(new BorderLayout());
		JPanel pWCenter = new JPanel();
		pWest.add(pWCenter, BorderLayout.CENTER);
		pWCenter.setLayout(new BoxLayout(pWCenter, BoxLayout.Y_AXIS));
		pWCenter.setBackground(new Color(97, 79, 69));
		ImageIcon imgLogo = new ImageIcon("img//Logo_NV.jpg");
		Image imgLogoScale = imgLogo.getImage().getScaledInstance((int) (imgLogo.getIconWidth()*0.45),(int) (imgLogo.getIconHeight()*0.8), Image.SCALE_SMOOTH);
		ImageIcon imgLogoScaleIcon = new ImageIcon(imgLogoScale);
		pWCenter.add(lbLogo = new JLabel(imgLogoScaleIcon));
		btnMenu = addButton("Bán Hàng", new Dimension(imgLogoScale.getWidth(lbLogo), 75), new Color(97, 79, 69), 18);
		btnQLBan = addButton("Đặt Bàn", new Dimension(imgLogoScale.getWidth(lbLogo), 75), new Color(97, 79, 69), 18);
		btnLSHoaDon = addButton("Hóa Đơn", new Dimension(imgLogoScale.getWidth(lbLogo), 75), new Color(97, 79, 69), 18);
		btnDKTV = addButton("Đăng Ký Thành Viên", new Dimension(imgLogoScale.getWidth(lbLogo), 75), new Color(97, 79, 69), 18);
		btnTTNV = addButton("Tài Khoản", new Dimension(imgLogoScale.getWidth(lbLogo), 75), new Color(97, 79, 69), 18);
		btnDangXuat = addButton("Đăng Xuất", new Dimension(imgLogoScale.getWidth(lbLogo), 75), new Color(97, 79, 69), 18);
		btnDangXuat.setPreferredSize(new Dimension(imgLogoScale.getWidth(lbLogo), 75));
		btnMenu.setBackground(Color.WHITE);
		btnMenu.setForeground(Color.BLACK);
		pWCenter.add(btnMenu);
		pWCenter.add(btnQLBan);
		pWCenter.add(btnLSHoaDon);
		pWCenter.add(btnDKTV);
		pWCenter.add(btnTTNV);
		pWest.add(btnDangXuat, BorderLayout.SOUTH);
		
		
		//================Main Center================
		cardMain = new CardLayout();
		cardPanelMain = new JPanel(cardMain);
		add(cardPanelMain, BorderLayout.CENTER);
		banHangDatBan_GUI = new BanHangDatBan_GUI(nv);
		pBanHangDatBan = banHangDatBan_GUI.crBanHangDatBan();
		cardPanelMain.add(pBanHangDatBan, "BanHangDatBan");
		hoaDon_GUI = new LSHoaDon_GUI();
		pLSHoaDon = hoaDon_GUI.createLSHoaDon();
		cardPanelMain.add(pLSHoaDon, "LSHoaDon");
		pTTNV_GUI = new TaiKhoan_GUI(nv);
		pTTNV = pTTNV_GUI.createTTNV();
		cardPanelMain.add(pTTNV, "TTNV");
		
		btnDangXuat.addActionListener(this);
		btnDKTV.addActionListener(this);
		btnLSHoaDon.addActionListener(this);
		btnMenu.addActionListener(this);
		btnQLBan.addActionListener(this);
		btnTTNV.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if(source.equals(btnMenu)) {
			btnDangXuat.setBackground(new Color(97, 79, 69));
			btnDangXuat.setForeground(Color.WHITE);
			btnDKTV.setBackground(new Color(97, 79, 69));
			btnDKTV.setForeground(Color.WHITE);
			btnLSHoaDon.setBackground(new Color(97, 79, 69));
			btnLSHoaDon.setForeground(Color.WHITE);
			btnQLBan.setBackground(new Color(97, 79, 69));
			btnQLBan.setForeground(Color.WHITE);
			btnTTNV.setBackground(new Color(97, 79, 69));
			btnTTNV.setForeground(Color.WHITE);
			cardMain.show(cardPanelMain, "BanHangDatBan");
			banHangDatBan_GUI.getCard_BanHangDatBan().show(banHangDatBan_GUI.getCardP_BanHangDatBan(), "Menu");
		} else if(source.equals(btnDangXuat)) {
			btnMenu.setBackground(new Color(97, 79, 69));
			btnMenu.setForeground(Color.WHITE);
			btnDKTV.setBackground(new Color(97, 79, 69));
			btnDKTV.setForeground(Color.WHITE);
			btnLSHoaDon.setBackground(new Color(97, 79, 69));
			btnLSHoaDon.setForeground(Color.WHITE);
			btnQLBan.setBackground(new Color(97, 79, 69));
			btnQLBan.setForeground(Color.WHITE);
			btnTTNV.setBackground(new Color(97, 79, 69));
			btnTTNV.setForeground(Color.WHITE);
			dangXuatActions();
		} else if(source.equals(btnDKTV)) {
			btnDangXuat.setBackground(new Color(97, 79, 69));
			btnDangXuat.setForeground(Color.WHITE);
			btnMenu.setBackground(new Color(97, 79, 69));
			btnMenu.setForeground(Color.WHITE);
			btnLSHoaDon.setBackground(new Color(97, 79, 69));
			btnLSHoaDon.setForeground(Color.WHITE);
			btnQLBan.setBackground(new Color(97, 79, 69));
			btnQLBan.setForeground(Color.WHITE);
			btnTTNV.setBackground(new Color(97, 79, 69));
			btnTTNV.setForeground(Color.WHITE);
			DKTV_GUI dky = new DKTV_GUI();
			dky.DKTVActions();
		} else if(source.equals(btnLSHoaDon)) {
			btnDangXuat.setBackground(new Color(97, 79, 69));
			btnDangXuat.setForeground(Color.WHITE);
			btnDKTV.setBackground(new Color(97, 79, 69));
			btnDKTV.setForeground(Color.WHITE);
			btnMenu.setBackground(new Color(97, 79, 69));
			btnMenu.setForeground(Color.WHITE);
			btnQLBan.setBackground(new Color(97, 79, 69));
			btnQLBan.setForeground(Color.WHITE);
			btnTTNV.setBackground(new Color(97, 79, 69));
			btnTTNV.setForeground(Color.WHITE);
			cardMain.show(cardPanelMain, "LSHoaDon");
		} else if(source.equals(btnQLBan)) {
			btnDangXuat.setBackground(new Color(97, 79, 69));
			btnDangXuat.setForeground(Color.WHITE);
			btnDKTV.setBackground(new Color(97, 79, 69));
			btnDKTV.setForeground(Color.WHITE);
			btnLSHoaDon.setBackground(new Color(97, 79, 69));
			btnLSHoaDon.setForeground(Color.WHITE);
			btnMenu.setBackground(new Color(97, 79, 69));
			btnMenu.setForeground(Color.WHITE);
			btnTTNV.setBackground(new Color(97, 79, 69));
			btnTTNV.setForeground(Color.WHITE);
			cardMain.show(cardPanelMain, "BanHangDatBan");
			banHangDatBan_GUI.getCard_BanHangDatBan().show(banHangDatBan_GUI.getCardP_BanHangDatBan(), "Ban");
		} else if(source.equals(btnTTNV)) {
			btnDangXuat.setBackground(new Color(97, 79, 69));
			btnDangXuat.setForeground(Color.WHITE);
			btnDKTV.setBackground(new Color(97, 79, 69));
			btnDKTV.setForeground(Color.WHITE);
			btnLSHoaDon.setBackground(new Color(97, 79, 69));
			btnLSHoaDon.setForeground(Color.WHITE);
			btnQLBan.setBackground(new Color(97, 79, 69));
			btnQLBan.setForeground(Color.WHITE);
			btnMenu.setBackground(new Color(97, 79, 69));
			btnMenu.setForeground(Color.WHITE);
			cardMain.show(cardPanelMain, "TTNV");
		}
	}
	
	public void dangXuatActions() {
		int luaChon = JOptionPane.showConfirmDialog(this, "Có muốn đăng xuất không?", "Chú ý",JOptionPane.YES_NO_OPTION);
		if(luaChon == JOptionPane.YES_OPTION) {
			setVisible(false);
			new DangNhap_GUI().setVisible(true);
		}
	}
	
	public JButton addButton(String title, Dimension size, Color color, int fontSize) {
		JButton button = new JButton(title);
		button.setMaximumSize(size);
		button.setPreferredSize(size);
		button.setBackground(color);
		button.setForeground(Color.WHITE);
		button.setFocusPainted(false);
		button.setFont(new Font(getName(), Font.BOLD, fontSize));
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				button.setBackground(color);
				button.setForeground(Color.WHITE);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				button.setBackground(Color.WHITE);
				button.setForeground(Color.BLACK);
			}
		});
		return button;
	}
}
