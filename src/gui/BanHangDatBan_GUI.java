package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import connectDB.ConnectDB;
import dao.Ban_DAO;
import dao.HoaDon_DAO;
import dao.KhachHang_DAO;
import dao.KhuyenMai_DAO;
import dao.SanPham_DAO;
import entity.Ban;
import entity.ChiTietHD;
import entity.HoaDon;
import entity.KhachHang;
import entity.KhuyenMai;
import entity.NhanVien;
import entity.SanPham;

public class BanHangDatBan_GUI extends JFrame implements ActionListener {
	private Color colorMain = new Color(97, 79, 69);
	private Font fontTitleMain = new Font(getName(), Font.BOLD, 30);
	private CardLayout card_BanHangDatBan;
	private JPanel cardP_BanHangDatBan;
	private JTextField txtSdtKH;
	private JButton btnTimKH;
	private JTextField txtHoTenKH;
	private JTextField txtDiemTL;
	private JTextField txtKmKH;
	private JRadioButton rdSuDungKM;
	private JRadioButton rdSuDungDiemTL;
	private DefaultTableModel tbModelHoaDon;
	private JTable tbHoaDon;
	private JTextField txtMaBan;
	private JTextField txtTongTien;
	private JTextField txtKhuyenMai;
	private JTextField txtThanhToan;
	private JTextField txtTienKHDua;
	private JTextField txtThoiLai;
	private JButton btnThanhToan;
	private JButton btnLoaiCaPhe;
	private JButton btnLoaiBanh;
	private CardLayout cardMenuCaPheBanh;
	private JPanel cardPanelMenuCaPheBanh;
	private JTextField txtLoaiGhe;
	private JTextField txtSLGhe;
	private JTextField txtKhuVuc;
	private JCheckBox chkDatBan;
	private ArrayList<SanPham> dsSP;
	private ArrayList<Ban> dsBan;
	private ArrayList<ChiTietHD> dsCtHD;
	private HoaDon hoaDon;
	private NhanVien nv;
	private KhachHang kh;
	private KhuyenMai km;
	private Ban ban;
	private JTextField txtSoBan;
	private ArrayList<JButton> dsBtnBan;

	public BanHangDatBan_GUI(NhanVien nv) {
		// TODO Auto-generated constructor stub
		super();
		this.nv = nv;
		dsSP = new ArrayList<SanPham>();
		dsCtHD = new ArrayList<ChiTietHD>();
		dsBan = new ArrayList<Ban>();
		hoaDon = new HoaDon(new HoaDon_DAO().createMaHD());
		hoaDon.setNvHD(nv);
	}
	
	public CardLayout getCard_BanHangDatBan() {
		return card_BanHangDatBan;
	}

	public JPanel getCardP_BanHangDatBan() {
		return cardP_BanHangDatBan;
	}

	public JSplitPane crBanHangDatBan() {
		JSplitPane spBanHangDatBan = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		card_BanHangDatBan = new CardLayout();
		cardP_BanHangDatBan = new JPanel(card_BanHangDatBan);
		spBanHangDatBan.setDividerSize(1);
		spBanHangDatBan.setEnabled(false);
		spBanHangDatBan.setResizeWeight(1);
		
		//====================Giao Diện Hóa Đơn====================
		JPanel pHoaDon = new JPanel();
		pHoaDon.setLayout(new BoxLayout(pHoaDon, BoxLayout.Y_AXIS));
		JPanel pTHoaDon = new JPanel();
		pHoaDon.add(pTHoaDon);
		pTHoaDon.setBackground(colorMain);
		JLabel lbHoaDon;
		pTHoaDon.add(lbHoaDon = new JLabel("Hóa Đơn"));
		lbHoaDon.setFont(fontTitleMain);
		lbHoaDon.setForeground(Color.WHITE);
		lbHoaDon.setBorder(BorderFactory.createEmptyBorder(20, 0, 15, 0));
		//--------------Giao Diện Thông Tin Khách Hàng--------------
		JPanel pTTKH = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pHoaDon.add(pTTKH);
		JLabel lbTTKH;
		pTTKH.add(lbTTKH = new JLabel("Thông Tin Khách Hàng"));
		lbTTKH.setFont(new Font(getName(), Font.BOLD, 22));
		JPanel pTxtTTKH = new JPanel();
		pHoaDon.add(pTxtTTKH);
		
		JLabel lbSDT;
		pTxtTTKH.add(lbSDT = new JLabel("Số điện thoại: "));
		lbSDT.setFont(new Font(getName(), Font.PLAIN, 16));
		pTxtTTKH.add(txtSdtKH = new JTextField(21));
		txtSdtKH.setPreferredSize(new Dimension(100, 24));
		pTxtTTKH.add(btnTimKH = new JButton("Tìm"));
		btnTimKH.setFocusPainted(false);
		btnTimKH.setBackground(colorMain);
		btnTimKH.setForeground(Color.WHITE);
		btnTimKH.setFont(new Font(getName(), Font.BOLD, 16));
		btnTimKH.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				timKHActions();
			}
		});
		
		JPanel pHienThiTTKH = new JPanel();
		pHoaDon.add(pHienThiTTKH);
		
		JLabel lbHoTenKH;
		pHienThiTTKH.add(lbHoTenKH = new JLabel("Họ và tên: "));
		lbHoTenKH.setFont(lbSDT.getFont());
		lbHoTenKH.setPreferredSize(lbSDT.getPreferredSize());
		pHienThiTTKH.add(txtHoTenKH = new JTextField(15));
		txtHoTenKH.setPreferredSize(txtSdtKH.getPreferredSize());
		txtHoTenKH.setEditable(false);
		txtHoTenKH.setBorder(null);
		
		JLabel lbDiemTL;
		pHienThiTTKH.add(lbDiemTL = new JLabel("Điểm: "));
		lbDiemTL.setFont(lbHoTenKH.getFont());
		pHienThiTTKH.add(txtDiemTL = new JTextField(5));
		txtDiemTL.setEditable(false);
		txtDiemTL.setPreferredSize(txtSdtKH.getPreferredSize());
		txtDiemTL.setBorder(null);
		
		JLabel lbKM;
		pHienThiTTKH.add(lbKM = new JLabel("KM: "));
		lbKM.setFont(lbDiemTL.getFont());
		pHienThiTTKH.add(txtKmKH = new JTextField(5));
		txtKmKH.setPreferredSize(txtDiemTL.getPreferredSize());
		txtKmKH.setEditable(false);
		txtKmKH.setBorder(null);
		
		JPanel pApDungKM = new JPanel();
		pHoaDon.add(pApDungKM);
		pApDungKM.add(rdSuDungKM = new JRadioButton("Sử dụng khuyến mãi"));
		rdSuDungKM.setFont(new Font(getName(), Font.ITALIC, 14));
		rdSuDungKM.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				suDungKhuyenMaiActions();
			}
		});
		pApDungKM.add(rdSuDungDiemTL = new JRadioButton("Sử dụng điểm tích lũy"));
		rdSuDungDiemTL.setFont(new Font(getName(), Font.ITALIC, 14));
		rdSuDungDiemTL.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				suDungDiemTLActions();
			}
		});
		ButtonGroup grRdApDungKM = new ButtonGroup();
		grRdApDungKM.add(rdSuDungKM);
		grRdApDungKM.add(rdSuDungDiemTL);
		//--------------Giao Diện Bảng Hóa Đơn--------------
		String[] cols = {"Mã", "Tên", "Số lượng", "Đơn giá", "Thành tiền"};
		tbModelHoaDon = new DefaultTableModel(cols, 0);
		tbHoaDon = new JTable(tbModelHoaDon);
		JScrollPane jscrHoaDon = new JScrollPane(tbHoaDon);
		pHoaDon.add(jscrHoaDon);
		jscrHoaDon.setPreferredSize(new Dimension(200, 300));
		JTableHeader tbHeaderHD = tbHoaDon.getTableHeader();
		tbHeaderHD.setFont(new Font(getName(), Font.BOLD, 14));
		tbHeaderHD.setBackground(colorMain);
		tbHeaderHD.setForeground(Color.WHITE);
		//--------------Giao Diện Thông Tin Hóa Đơn--------------
		JPanel pTTHD = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pHoaDon.add(pTTHD);
		JLabel lbTTHD;
		pTTHD.add(lbTTHD = new JLabel("Thông Tin Hóa Đơn"));
		lbTTHD.setFont(new Font(getName(), Font.BOLD, 24));
		
		JPanel pSoBan = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pHoaDon.add(pSoBan);
		JLabel lbSoBan;
		pSoBan.add(lbSoBan = new JLabel("Số bàn:"));
		lbSoBan.setFont(new Font(getName(), Font.BOLD, 14));
		lbSoBan.setPreferredSize(lbTTHD.getPreferredSize());
		pSoBan.add(txtSoBan = new JTextField(15));
		txtSoBan.setFont(lbSoBan.getFont());
		txtSoBan.setBorder(null);
		txtSoBan.setEditable(false);
		
		JPanel pThanhToan = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pHoaDon.add(pThanhToan);
		JLabel lbThanhToan;
		pThanhToan.add(lbThanhToan = new JLabel("Tổng tiền:"));
		lbThanhToan.setFont(new Font(getName(), Font.BOLD, 14));
		lbThanhToan.setPreferredSize(lbTTHD.getPreferredSize());
		pThanhToan.add(txtTongTien = new JTextField(15));
		txtTongTien.setFont(lbSoBan.getFont());
		txtTongTien.setEditable(false);
		txtTongTien.setBorder(null);
		
		JPanel pKhuyenMai = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
		pHoaDon.add(pKhuyenMai);
		JLabel lbKhuyenMai;
		pKhuyenMai.add(lbKhuyenMai = new JLabel("Khuyến mãi:"));
		lbKhuyenMai.setFont(new Font(getName(), Font.BOLD, 14));
		lbKhuyenMai.setPreferredSize(lbTTHD.getPreferredSize());
		pKhuyenMai.add(txtKhuyenMai = new JTextField(15));
		txtKhuyenMai.setFont(lbSoBan.getFont());
		txtKhuyenMai.setEditable(false);
		txtKhuyenMai.setBorder(null);

		JPanel pTongTien = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pHoaDon.add(pTongTien);
		JLabel lbTongTien;
		pTongTien.add(lbTongTien = new JLabel("Phải thanh toán:"));
		lbTongTien.setFont(new Font(getName(), Font.BOLD, 14));
		lbTongTien.setPreferredSize(lbTTHD.getPreferredSize());
		pTongTien.add(txtThanhToan = new JTextField(15));
		txtThanhToan.setFont(lbSoBan.getFont());
		txtThanhToan.setEditable(false);
		txtThanhToan.setBorder(null);
		
		JPanel pTienKH = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pHoaDon.add(pTienKH);
		JLabel lbTienKH;
		pTienKH.add(lbTienKH = new JLabel("Tiền khách đưa:"));
		lbTienKH.setFont(new Font(getName(), Font.BOLD, 14));
		lbTienKH.setPreferredSize(lbTTHD.getPreferredSize());
		pTienKH.add(txtTienKHDua = new JTextField(10));
		txtTienKHDua.setFont(lbSoBan.getFont());
		txtTienKHDua.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				tienKhachDuaActions();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				tienKhachDuaActions();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				tienKhachDuaActions();
			}
		});
		
		JPanel pThoiLai = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pHoaDon.add(pThoiLai);
		JLabel lbThoiLai;
		pThoiLai.add(lbThoiLai = new JLabel("Tiền thối lại:"));
		lbThoiLai.setFont(new Font(getName(), Font.BOLD, 14));
		lbThoiLai.setPreferredSize(lbTTHD.getPreferredSize());
		pThoiLai.add(txtThoiLai = new JTextField(15));
		txtThoiLai.setEditable(false);
		txtThoiLai.setFont(lbSoBan.getFont());
		txtThoiLai.setBorder(null);
		
		JPanel pBtnTT = new JPanel();
		pHoaDon.add(pBtnTT);
		pBtnTT.add(btnThanhToan = new JButton("Thanh Toán"));
		btnThanhToan.setPreferredSize(new Dimension(250, 40));
		btnThanhToan.setBackground(colorMain);
		btnThanhToan.setForeground(Color.WHITE);
		btnThanhToan.setFont(new Font(getName(), Font.BOLD, 22));
		btnThanhToan.setFocusPainted(false);
		btnThanhToan.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				thanhToanActions();
			}
		});
		
		//------------Menu------------
		JPanel pMenu = new JPanel();
		pMenu.setLayout(new BorderLayout());
		JPanel pMenuNorth = new JPanel();
		pMenuNorth.setLayout(new BoxLayout(pMenuNorth, BoxLayout.Y_AXIS));
		pMenu.add(pMenuNorth, BorderLayout.NORTH);
		pMenuNorth.setBackground(new Color(97, 79, 69));
		JPanel pLbMenu = new JPanel();
		pMenuNorth.add(pLbMenu);
		pLbMenu.setBackground(new Color(97, 79, 69));
		JLabel lbMenu;
		pLbMenu.add(lbMenu = new JLabel("Menu"));
		lbMenu.setFont(new Font(getName(), Font.BOLD, 30));
		lbMenu.setForeground(Color.WHITE);
		JPanel pBtnLoai = new JPanel();
		pBtnLoai.setBackground(new Color(97, 79, 69));
		pMenuNorth.add(pBtnLoai);
		btnLoaiCaPhe = addButton("Cà Phê", new Dimension(145, 25), new Color(97, 79, 69), 16);
		btnLoaiBanh = addButton("Bánh Ngọt", new Dimension(145, 25), new Color(97, 79, 69), 16);
		pBtnLoai.add(btnLoaiCaPhe);
		pBtnLoai.add(btnLoaiBanh);
		btnLoaiCaPhe.setBackground(Color.WHITE);
		btnLoaiBanh.setForeground(Color.BLACK);
		btnLoaiCaPhe.addActionListener(this);
		btnLoaiBanh.addActionListener(this);
				
		JPanel pProducts = createProducts();
		pMenu.add(pProducts, BorderLayout.CENTER);
				
		// Tạo JScrollPane cho pProducts
		JScrollPane scrollPane = new JScrollPane(pProducts);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		// Thêm JScrollPane vào pMenu
		pMenu.add(scrollPane, BorderLayout.CENTER);
				
				
		//------------Đặt Bàn------------
		JPanel pBan = new JPanel();
		pBan.setLayout(new BorderLayout());
		JPanel pBanNorth = new JPanel();
		pBanNorth.setLayout(new BoxLayout(pBanNorth, BoxLayout.Y_AXIS));
		pBan.add(pBanNorth, BorderLayout.NORTH);
		JPanel plbBan = new JPanel();
		pBanNorth.add(plbBan);
		plbBan.setBackground(new Color(97, 79, 69));
		JLabel lbBan;
		plbBan.add(lbBan = new JLabel("Đặt Bàn"));
		lbBan.setFont(new Font(getName(), Font.BOLD, 30));
		lbBan.setForeground(Color.WHITE);
		lbBan.setBorder(BorderFactory.createEmptyBorder(25, 0, 10, 0));
		JPanel pBanCenter = new JPanel();
		pBanCenter = createCardBan();
		pBan.add(pBanCenter, BorderLayout.CENTER);
				
		//Card Menu - Bàn
		cardP_BanHangDatBan.add(pMenu, "Menu");
		cardP_BanHangDatBan.add(pBan, "Ban");
		spBanHangDatBan.add(cardP_BanHangDatBan);
		spBanHangDatBan.add(pHoaDon);
				
		return spBanHangDatBan;
	}
	
	private JPanel crProductPanel(SanPham sp) {
		JPanel productPanel = new JPanel();
		productPanel.setLayout(new BorderLayout());
		productPanel.setBorder(BorderFactory.createLineBorder(new Color(97, 79, 69), 1));
		productPanel.setBackground(Color.WHITE);
        
		//---------Button Chọn sản phẩm---------
		JButton btnChonSP = new JButton("Chọn");
		btnChonSP.setFocusPainted(false);
		btnChonSP.setFont(new Font(getName(), Font.BOLD, 14));
		btnChonSP.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				chonSPActions(sp);
			}
		});
		
        //---------CheckBox Hết sản phẩm---------
        JCheckBox chkHetSP = new JCheckBox("", true);
        chkHetSP.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(chkHetSP.isSelected())
					btnChonSP.setEnabled(true);
				else
					btnChonSP.setEnabled(false);
			}
		});
        
        //---------Label Tên sản phẩm---------
        JLabel nameLabel = new JLabel(sp.getTenSP());
        nameLabel.setFont(new Font(getName(), Font.BOLD, 14));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        //---------Label Hình sản phẩm---------
        // Đọc InputStream thành BufferedImage
        BufferedImage image = null;
		try {
			image = ImageIO.read(new ByteArrayInputStream(sp.getHinhSP()));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        JLabel imageLabel = new JLabel();
        ImageIcon icon = new ImageIcon(image.getScaledInstance(200, 200, Image.SCALE_SMOOTH));
        imageLabel.setIcon(icon);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        //---------Label Giá sản phẩm---------
        JLabel priceLabel = new JLabel("Giá: " + Double.toString(sp.getDonGiaSP()));
        priceLabel.setFont(new Font(getName(), Font.BOLD, 18));
        priceLabel.setForeground(Color.RED);
        priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JPanel pNorth = new JPanel();
        pNorth.add(chkHetSP);
        pNorth.add(nameLabel);
        
        JPanel pSouth = new JPanel();
        pSouth.add(priceLabel);
        pSouth.add(btnChonSP);
        
        productPanel.add(pNorth, BorderLayout.NORTH);
        productPanel.add(imageLabel, BorderLayout.CENTER);
        productPanel.add(pSouth, BorderLayout.SOUTH);
        
		return productPanel;
	}
	
	private JPanel createProducts() {
		// TODO Auto-generated method stub
		cardMenuCaPheBanh = new CardLayout();
		cardPanelMenuCaPheBanh= new JPanel(cardMenuCaPheBanh);

		JPanel pCaPhe = new JPanel(new GridLayout(0, 4));
		
		JPanel pBanh = new JPanel(new GridLayout(0, 4));
		cardPanelMenuCaPheBanh.add(pCaPhe,"CaPhe");
		cardPanelMenuCaPheBanh.add(pBanh,"Banh");
		
		btnLoaiCaPhe.setBackground(new Color(255, 255, 200));
		btnLoaiCaPhe.setForeground(Color.black);
		
		//==================LOAD SẢN PHẨM TỪ DATABASE LÊN GUI==================
		SanPham_DAO sp_dao = new SanPham_DAO();
		dsSP = sp_dao.getAllSP();
		
		for(SanPham sp : dsSP) {
			JPanel pSP = crProductPanel(sp);
			if(sp.getLoaiSP().equals("CaPhe"))
				pCaPhe.add(pSP);
			else
				pBanh.add(pSP);
		}
		
		return cardPanelMenuCaPheBanh;
	}
	
	private JPanel createCardBan() {
		JPanel card = new JPanel();
        card.setLayout(new GridLayout(2,1));
        JPanel pTTBan = new JPanel();
        JPanel pBtnBan = new JPanel();
        card.add(pTTBan);
        card.add(pBtnBan);
        
        JPanel pTTMaBan,pTTSLGhe,pTTLoaiGhe,pTTKhuVuc;
        pTTBan.setLayout(new BoxLayout(pTTBan, BoxLayout.Y_AXIS));
        JLabel lbTTCTBan;
        JPanel plbTTCTBan = new JPanel(new FlowLayout(FlowLayout.LEFT));
        plbTTCTBan.add(lbTTCTBan = new JLabel("Thông Tin Chi Tiết Bàn"));
		pTTBan.add(plbTTCTBan);
		lbTTCTBan.setFont(new Font(getName(), Font.BOLD, 18));
		lbTTCTBan.setForeground(Color.white);
		lbTTCTBan.setBorder(BorderFactory.createEmptyBorder(5, 50, 0, 0));
		plbTTCTBan.setBackground(new Color(97, 79, 69));
		plbTTCTBan.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
		pTTBan.add(pTTMaBan = new JPanel());
		pTTMaBan.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
		pTTBan.add(pTTLoaiGhe = new JPanel());
		pTTBan.add(pTTSLGhe = new JPanel());
		pTTBan.add(pTTKhuVuc = new JPanel());
		
		JLabel lbMaBan,lbLoaiGhe,lbSLGhe,lbKhuVuc;
		pTTMaBan.add(lbMaBan = new JLabel("Mã Bàn: "));
		lbMaBan.setFont(new Font(getName(), Font.BOLD, 16));
		pTTMaBan.add(txtMaBan = new JTextField(20));
		txtMaBan.setEditable(false);
		txtMaBan.setFont(new Font(getName(), Font.PLAIN, 16));
		txtMaBan.setBorder(null);
		
		pTTLoaiGhe.add(lbLoaiGhe = new JLabel("Loại Ghế: "));
		lbLoaiGhe.setFont(lbMaBan.getFont());
		pTTLoaiGhe.add(txtLoaiGhe = new JTextField(20));
		txtLoaiGhe.setEditable(false);
		txtLoaiGhe.setFont(txtMaBan.getFont());
		txtLoaiGhe.setBorder(null);
		
		pTTSLGhe.add(lbSLGhe = new JLabel("Số Lượng Ghế: "));
		lbSLGhe.setFont(lbMaBan.getFont());
		pTTSLGhe.add(txtSLGhe = new JTextField(20));
		txtSLGhe.setEditable(false);
		txtSLGhe.setFont(txtMaBan.getFont());
		txtSLGhe.setBorder(null);
		
		pTTKhuVuc.add(lbKhuVuc = new JLabel("Khu Vực : "));
		lbKhuVuc.setFont(lbMaBan.getFont());
		pTTKhuVuc.add(txtKhuVuc = new JTextField(20));
		txtKhuVuc.setEditable(false);
		txtKhuVuc.setFont(txtMaBan.getFont());
		txtKhuVuc.setBorder(null);
		
		lbMaBan.setPreferredSize(lbSLGhe.getPreferredSize());
		lbLoaiGhe.setPreferredSize(lbSLGhe.getPreferredSize());
		lbKhuVuc.setPreferredSize(lbSLGhe.getPreferredSize());
		
		chkDatBan = new JCheckBox("Trạng Thái Đặt Bàn");
		JPanel pCBBan = new JPanel();
		pCBBan.add(chkDatBan);
		chkDatBan.setFont(lbMaBan.getFont());
		pTTBan.add(pCBBan);
		
		pBtnBan.setLayout(new BoxLayout(pBtnBan, BoxLayout.Y_AXIS));
        JLabel lbBtnBan;
        JPanel plbBtnBan = new JPanel(new FlowLayout(FlowLayout.LEFT));
        plbBtnBan.add(lbBtnBan = new JLabel("Mã Bàn"));
        pTTBan.add(plbBtnBan);
		lbBtnBan.setFont(lbTTCTBan.getFont());
		lbBtnBan.setForeground(Color.white);
		lbBtnBan.setBorder(BorderFactory.createEmptyBorder(5, 50, 5, 0));
		plbBtnBan.setBackground(new Color(97, 79, 69));
		JPanel pbtnMaBan;
		pBtnBan.add(pbtnMaBan= CreateMaBan());
		
		return card;
	}
	
	private JPanel CreateMaBan() {
	    JPanel cardBan = new JPanel(new GridLayout(0, 6, 10, 10));
	    
	    
	    //==================LOAD BÀN TỪ DATABASE LÊN GUI==================
	    Ban_DAO ban_dao = new Ban_DAO();
	    dsBan = ban_dao.getAllBan();
	    dsBtnBan = new ArrayList<JButton>();
	    for (Ban ban : dsBan) {
	        JButton button = new JButton(ban.getMaBan());
	        button.setPreferredSize(new Dimension(100,50));
	        dsBtnBan.add(button);
	        button.addActionListener(new ActionListener() {	
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if (button.getBackground() != Color.RED)
						chkDatBan.setSelected(false);
					else
						chkDatBan.setSelected(true);
					chonBanActions(ban);
				}
			});
	        cardBan.add(button);
	    }

	    JScrollPane scrollPane = new JScrollPane(cardBan);
	    scrollPane.setPreferredSize(new Dimension(400, 300));
	    JPanel mainPanel = new JPanel(new BorderLayout());
	    mainPanel.add(scrollPane, BorderLayout.CENTER);
	    
	    return mainPanel;
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if(source.equals(btnLoaiCaPhe)) {
			btnLoaiBanh.setBackground(new Color(97, 79, 69));
			btnLoaiBanh.setForeground(Color.WHITE);
			cardMenuCaPheBanh.show(cardPanelMenuCaPheBanh,"CaPhe");
		} else if(source.equals(btnLoaiBanh)) {
			btnLoaiCaPhe.setBackground(new Color(97, 79, 69));
			btnLoaiCaPhe.setForeground(Color.WHITE);
			cardMenuCaPheBanh.show(cardPanelMenuCaPheBanh,"Banh");
		}
	}
	
	public void timKHActions() {
		KhachHang_DAO kh_dao = new KhachHang_DAO();
		KhuyenMai_DAO km_dao = new KhuyenMai_DAO();
		ArrayList<KhuyenMai> dsKM = km_dao.getAllKM();
		kh = kh_dao.getKH(txtSdtKH.getText());
		if (kh != null) {
			txtHoTenKH.setText(kh.getTenKH());
			txtDiemTL.setText(kh.getDiemTL() + "");
			for (KhuyenMai km_d : dsKM)
				if (km_d.getKhachHangKM().getSdtKH().equals(kh.getSdtKH())) {
					txtKmKH.setText(km_d.getGiaTriKM() + "");
					km = km_d;
					break;
				}
		} else {
			txtHoTenKH.setText("Không tìm thấy!");
			txtDiemTL.setText("");
			txtKmKH.setText("");
		}
	}
	
	public void suDungKhuyenMaiActions() {
		try {
			double km = Double.parseDouble(txtKmKH.getText());
			double tongTien = Double.parseDouble(txtTongTien.getText());
			double thanhToan = Double.parseDouble(txtThanhToan.getText());
			if (km != 0 && txtKhuyenMai.getText().equals("")) {
				txtKhuyenMai.setText(km + "");
				txtThanhToan.setText((thanhToan - km) + "");
			} else {
				txtKhuyenMai.setText("");
				txtThanhToan.setText(tongTien + "");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void suDungDiemTLActions() {
		try {
			double diemTL = Double.parseDouble(txtDiemTL.getText());
			double tongTien = Double.parseDouble(txtTongTien.getText());
			double thanhToan = Double.parseDouble(txtThanhToan.getText());
			if (diemTL != 0 && txtKhuyenMai.getText().equals("")) {
				txtKhuyenMai.setText(diemTL + "");
				txtThanhToan.setText((thanhToan - diemTL) + "");
			} else {
				txtKhuyenMai.setText("");
				txtThanhToan.setText(tongTien + "");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void chonSPActions(SanPham sp) {
		int soLuong = 0;
		double thanhTien = 0;
		double tongTien = 0;
		for (ChiTietHD ctHD : dsCtHD) {
			if (sp.getMaSP().equals(ctHD.getSanPhamCTHD().getMaSP())) {
				soLuong = ctHD.getSoLuongSP() + 1;
				thanhTien = sp.getDonGiaSP()*soLuong;
				if (!txtTongTien.getText().equals(""))
					tongTien = sp.getDonGiaSP() + Double.parseDouble(txtTongTien.getText());
				ctHD.setSoLuongSP(soLuong);
				ctHD.setThanhTien(thanhTien);
				for (int i = 0; i < tbModelHoaDon.getRowCount(); i++) {
					if (tbModelHoaDon.getValueAt(i, 0).equals(sp.getMaSP())) {
						tbModelHoaDon.setValueAt(soLuong, i, 2);
						tbModelHoaDon.setValueAt(thanhTien, i, 4);
					}
				}
				txtTongTien.setText(tongTien + "");
				txtThanhToan.setText(tongTien + "");
				break;
			}
		}
		if (soLuong == 0) {
			soLuong += 1;
			thanhTien = sp.getDonGiaSP();
			ChiTietHD ctHD = new ChiTietHD(hoaDon, sp, soLuong, sp.getDonGiaSP());
			dsCtHD.add(ctHD);
			String[] row = {sp.getMaSP(), sp.getTenSP(), soLuong + "", sp.getDonGiaSP() + "", sp.getDonGiaSP() + ""};
			tbModelHoaDon.addRow(row);
			try {
				tongTien = Double.parseDouble(txtTongTien.getText()) + thanhTien;
				txtTongTien.setText(tongTien + "");
				txtThanhToan.setText(tongTien + "");
			} catch (Exception e) {
				// TODO: handle exception
				tongTien = thanhTien;
				txtTongTien.setText(tongTien + "");
				txtThanhToan.setText(tongTien + "");
			}
		}
	}
	
	public void chonBanActions(Ban b) {
		ban = b;
		txtMaBan.setText(ban.getMaBan());
		txtKhuVuc.setText(ban.getKhuVuc());
		txtLoaiGhe.setText(ban.getLoaiGhe());
		txtSLGhe.setText(ban.getSoLuongGhe() + "");
		chkDatBan.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (chkDatBan.isSelected()) {
					txtSoBan.setText(ban.getMaBan());
					for (JButton btn : dsBtnBan) {
						if (btn.getText().equals(ban.getMaBan())) {
							btn.setBackground(Color.RED);
							break;
						}
					}
				} else {
					txtSoBan.setText("");
					for (JButton btn : dsBtnBan) {
						if (btn.getText().equals(ban.getMaBan())) {
							btn.setBackground(null);
							break;
						}
					}
				}
			}
		});
	}
	
	public void tienKhachDuaActions() {
		try {
			double tienKHDua = Double.parseDouble(txtTienKHDua.getText());
			double thanhTien = Double.parseDouble(txtThanhToan.getText());
			if (tienKHDua >= thanhTien)
				txtThoiLai.setText((tienKHDua - thanhTien) + "");
			else
				txtThoiLai.setText("");
		} catch (Exception e) {
			// TODO: handle exception
			txtThoiLai.setText("");
		}
	}
	
	public void thanhToanActions() {
		hoaDon.setBanHD(ban);
		hoaDon.setKmHD(km);
		hoaDon.setKhHD(kh);
		hoaDon.setNvHD(nv);
		hoaDon.setTongTienHD(Double.parseDouble(txtTongTien.getText()));
		hoaDon.setTienKhachDua(Double.parseDouble(txtTienKHDua.getText()));
		hoaDon.setTienTraLai(Double.parseDouble(txtThoiLai.getText()));
		hoaDon.setThanhToanHD(Double.parseDouble(txtThanhToan.getText()));
		ChiTietHoaDon_GUI ctHD_gui = new ChiTietHoaDon_GUI(hoaDon, dsCtHD);
		ctHD_gui.showHoaDon();
		xoaRongHD();
	}
	
	public void xoaRongHD() {
		txtSdtKH.setText("");
		txtHoTenKH.setText("");
		txtDiemTL.setText("");
		txtKhuyenMai.setText("");
		tbModelHoaDon.setNumRows(0);
		txtSoBan.setText("");
		txtTongTien.setText("");
		txtKhuyenMai.setText("");
		txtThanhToan.setText("");
		txtTienKHDua.setText("");
		txtThoiLai.setText("");
		HoaDon_DAO hd_dao = new HoaDon_DAO();
		hoaDon.setMaHD(hd_dao.createMaHD());
	}
}
