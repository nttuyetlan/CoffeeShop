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
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import com.toedter.calendar.JDateChooser;


public class NguoiQuanLy_GUI extends JFrame implements ActionListener {
	private CardLayout cardMain;
	private JPanel cardPanelMain;
	private JButton btnQLNhanVien;
	private JButton btnQLHoaDon;
	private JButton btnQLKhachHang;
	private JButton btnThongKe;
	private JButton btnQLSanPham;
	private JButton btnDangXuat;
	private JComboBox<String> cbThongKe;
	private JTextField txtMaNV;
	private JTextField txtTenNV;
	private JTextField txtDiaChiNV;
	private JTextField txtSoCCCDNV;
	private JTextField txtSDTNV;
	private JComboBox<String> cbChucVuNV;
	private DefaultTableModel tbModelNV;
	private JTable tbNV;
	private JTextField txtTimNV;
	private JButton btnTimNV;
	private JButton btnThemNV;
	private JButton btnXoaNV;
	private JButton btnXoaRongNV;
	private JButton btnSuaNV;
	private JButton btnQLKhuyenMai;
	private JTextField txtTimSP_SanPham;
	private JButton btnTimSP_SanPham;
	private JButton btnThemSP_SanPham;
	private JButton btnSapXepSP_SanPham;
	private JComboBox<String> JCBoxloai_SanPham;
	private JButton btnTimHD_HoaDon;
	private JButton btnFileHD;
	private DefaultTableModel tableModelQLHD;
	private JTable tableQLHD;
	private JTextField txtTongHD;
	private JButton btnTimKM;
	private JButton btnThemKM;
	private DefaultTableModel tblModelKM;
	private JTable tblKM;
	private JTextField txtMaKH_KhachHang;
	private JTextField txtHoTenKH_KhachHang;
	private JTextField txtEmaiKH_KhachHang;
	private JTextField txtSDTKH_KH;
	private JTextField txtDiemTL;
	private JButton btXoaKH;
	private JButton btSuaKH;
	private DefaultTableModel tbModelKH;
	private JTable tableKH;
	private JTextField txtTimKH;
	private JButton btnTimKHSDT;
	private JButton btnTimKHTen;
	private JButton btnThemKH;

	public NguoiQuanLy_GUI() {
		super("Bến An Long - Quản Lý");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		
		
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
		JLabel lbLogo;
		pWCenter.add(lbLogo = new JLabel(imgLogoScaleIcon));
		btnQLNhanVien = addButton("Nhân Viên", new Dimension(imgLogoScale.getWidth(lbLogo), 75), new Color(97, 79, 69), 18);
		btnQLSanPham = addButton("Sản Phẩm", new Dimension(imgLogoScale.getWidth(lbLogo), 75), new Color(97, 79, 69), 18);
		btnQLHoaDon = addButton("Hóa Đơn", new Dimension(imgLogoScale.getWidth(lbLogo), 75), new Color(97, 79, 69), 18);
		btnQLKhachHang = addButton("Khách Hàng", new Dimension(imgLogoScale.getWidth(lbLogo), 75), new Color(97, 79, 69), 18);
		btnQLKhuyenMai = addButton("Khuyến Mãi", new Dimension(imgLogoScale.getWidth(lbLogo), 75), new Color(97, 79, 69), 18);
		btnThongKe = addButton("Thống Kê", new Dimension(imgLogoScale.getWidth(lbLogo), 75), new Color(97, 79, 69), 18);
		btnDangXuat = addButton("Đăng xuất", new Dimension(imgLogoScale.getWidth(lbLogo), 75), new Color(97, 79, 69), 18);
		btnDangXuat.setPreferredSize(new Dimension(imgLogoScale.getWidth(lbLogo), 75));
		
		pWCenter.add(btnQLNhanVien);
		pWCenter.add(btnQLSanPham);
		pWCenter.add(btnQLHoaDon);
		pWCenter.add(btnQLKhachHang);
		pWCenter.add(btnQLKhuyenMai);
		pWCenter.add(btnThongKe);
		pWest.add(btnDangXuat, BorderLayout.SOUTH);
		
		
		//================Main Center================
		cardMain = new CardLayout();
		cardPanelMain = new JPanel(cardMain);
		add(cardPanelMain, BorderLayout.CENTER);
		JPanel pTemp = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 200));
		cardPanelMain.add(pTemp, "Temp");
		JLabel lbTemp;
		pTemp.add(lbTemp = new JLabel("<html>Chào bạn, chúc bạn một ngày làm việc vui vẻ!<br/><center>Vui lòng chọn chức năng!</center></html>"));
		lbTemp.setFont(new Font(getName(), Font.BOLD, 36));
		lbTemp.setForeground(Color.RED);
		QLNhanVien_GUI qlNhanVien = new QLNhanVien_GUI();
		JPanel pQLNhanVien = qlNhanVien.createQLNhanVien();
		cardPanelMain.add(pQLNhanVien, "QLNhanVien");
		QLThongKe_GUI qltk = new QLThongKe_GUI();
		JPanel pThongKe = qltk.createThongKe();
		cardPanelMain.add(pThongKe, "ThongKe");
		//--- thêm 
		QLSanPham_GUI qlsp = new QLSanPham_GUI();
		JPanel pSanPham = qlsp.createSanPham();
		cardPanelMain.add(pSanPham, "SanPham");
		QLKhuyenMai_GUI qlKhuyenMai = new QLKhuyenMai_GUI();
		JPanel pKhuyenMai = qlKhuyenMai.createKhuyenMai();
		cardPanelMain.add(pKhuyenMai, "KhuyenMai");
		QLHoaDon_GUI qlhd = new QLHoaDon_GUI();
		JPanel pQLHoaDon = qlhd.createHoaDon();
		cardPanelMain.add(pQLHoaDon, "QLHoaDon");
		QLKhachHang_GUI qlkh = new QLKhachHang_GUI();
		JPanel pKhachHang = qlkh.createKhachHang();
		cardPanelMain.add(pKhachHang, "KhachHang");
		
		btnQLNhanVien.addActionListener(this);
		btnQLSanPham.addActionListener(this);
		btnQLKhachHang.addActionListener(this);
		btnQLKhuyenMai.addActionListener(this);
		btnQLHoaDon.addActionListener(this);
		btnThongKe.addActionListener(this);
		btnDangXuat.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if(source.equals(btnQLNhanVien)) {
			btnDangXuat.setBackground(new Color(97, 79, 69));
			btnDangXuat.setForeground(Color.WHITE);
			btnQLHoaDon.setBackground(new Color(97, 79, 69));
			btnQLHoaDon.setForeground(Color.WHITE);
			btnQLKhachHang.setBackground(new Color(97, 79, 69));
			btnQLKhachHang.setForeground(Color.WHITE);
			btnQLSanPham.setBackground(new Color(97, 79, 69));
			btnQLSanPham.setForeground(Color.WHITE);
			btnThongKe.setBackground(new Color(97, 79, 69));
			btnThongKe.setForeground(Color.WHITE);
			btnQLKhuyenMai.setBackground(new Color(97, 79, 69));
			btnQLKhuyenMai.setForeground(Color.WHITE);
			cardMain.show(cardPanelMain, "QLNhanVien");
		} else if(source.equals(btnDangXuat)) {
			btnQLNhanVien.setBackground(new Color(97, 79, 69));
			btnQLNhanVien.setForeground(Color.WHITE);
			btnQLHoaDon.setBackground(new Color(97, 79, 69));
			btnQLHoaDon.setForeground(Color.WHITE);
			btnQLKhachHang.setBackground(new Color(97, 79, 69));
			btnQLKhachHang.setForeground(Color.WHITE);
			btnQLSanPham.setBackground(new Color(97, 79, 69));
			btnQLSanPham.setForeground(Color.WHITE);
			btnThongKe.setBackground(new Color(97, 79, 69));
			btnThongKe.setForeground(Color.WHITE);
			btnQLKhuyenMai.setBackground(new Color(97, 79, 69));
			btnQLKhuyenMai.setForeground(Color.WHITE);
			dangXuatActions();
		} else if(source.equals(btnQLHoaDon)) {
			btnDangXuat.setBackground(new Color(97, 79, 69));
			btnDangXuat.setForeground(Color.WHITE);
			btnQLNhanVien.setBackground(new Color(97, 79, 69));
			btnQLNhanVien.setForeground(Color.WHITE);
			btnQLKhachHang.setBackground(new Color(97, 79, 69));
			btnQLKhachHang.setForeground(Color.WHITE);
			btnQLSanPham.setBackground(new Color(97, 79, 69));
			btnQLSanPham.setForeground(Color.WHITE);
			btnThongKe.setBackground(new Color(97, 79, 69));
			btnThongKe.setForeground(Color.WHITE);
			btnQLKhuyenMai.setBackground(new Color(97, 79, 69));
			btnQLKhuyenMai.setForeground(Color.WHITE);
			
			cardMain.show(cardPanelMain, "QLHoaDon");
			
		} else if(source.equals(btnQLSanPham)) {
			btnDangXuat.setBackground(new Color(97, 79, 69));
			btnDangXuat.setForeground(Color.WHITE);
			btnQLHoaDon.setBackground(new Color(97, 79, 69));
			btnQLHoaDon.setForeground(Color.WHITE);
			btnQLKhachHang.setBackground(new Color(97, 79, 69));
			btnQLKhachHang.setForeground(Color.WHITE);
			btnQLNhanVien.setBackground(new Color(97, 79, 69));
			btnQLNhanVien.setForeground(Color.WHITE);
			btnThongKe.setBackground(new Color(97, 79, 69));
			btnThongKe.setForeground(Color.WHITE);
			btnQLKhuyenMai.setBackground(new Color(97, 79, 69));
			btnQLKhuyenMai.setForeground(Color.WHITE);

			cardMain.show(cardPanelMain, "SanPham");
			
		} else if(source.equals(btnThongKe)) {
			btnDangXuat.setBackground(new Color(97, 79, 69));
			btnDangXuat.setForeground(Color.WHITE);
			btnQLHoaDon.setBackground(new Color(97, 79, 69));
			btnQLHoaDon.setForeground(Color.WHITE);
			btnQLKhachHang.setBackground(new Color(97, 79, 69));
			btnQLKhachHang.setForeground(Color.WHITE);
			btnQLSanPham.setBackground(new Color(97, 79, 69));
			btnQLSanPham.setForeground(Color.WHITE);
			btnQLNhanVien.setBackground(new Color(97, 79, 69));
			btnQLKhuyenMai.setBackground(new Color(97, 79, 69));
			btnQLKhuyenMai.setForeground(Color.WHITE);
			btnQLNhanVien.setForeground(Color.WHITE);
			cardMain.show(cardPanelMain, "ThongKe");
		} else if(source.equals(btnQLKhachHang)) {
			btnDangXuat.setBackground(new Color(97, 79, 69));
			btnDangXuat.setForeground(Color.WHITE);
			btnQLHoaDon.setBackground(new Color(97, 79, 69));
			btnQLHoaDon.setForeground(Color.WHITE);
			btnThongKe.setBackground(new Color(97, 79, 69));
			btnThongKe.setForeground(Color.WHITE);
			btnQLSanPham.setBackground(new Color(97, 79, 69));
			btnQLSanPham.setForeground(Color.WHITE);
			btnQLNhanVien.setBackground(new Color(97, 79, 69));
			btnQLNhanVien.setForeground(Color.WHITE);
			btnQLKhuyenMai.setBackground(new Color(97, 79, 69));
			btnQLKhuyenMai.setForeground(Color.WHITE);
			cardMain.show(cardPanelMain, "KhachHang");
			
		}else if(source.equals(btnQLKhuyenMai)) {
			btnDangXuat.setBackground(new Color(97, 79, 69));
			btnDangXuat.setForeground(Color.WHITE);
			btnQLHoaDon.setBackground(new Color(97, 79, 69));
			btnQLHoaDon.setForeground(Color.WHITE);
			btnThongKe.setBackground(new Color(97, 79, 69));
			btnThongKe.setForeground(Color.WHITE);
			btnQLSanPham.setBackground(new Color(97, 79, 69));
			btnQLSanPham.setForeground(Color.WHITE);
			btnQLNhanVien.setBackground(new Color(97, 79, 69));
			btnQLNhanVien.setForeground(Color.WHITE);
			btnQLKhachHang.setBackground(new Color(97, 79, 69));
			btnQLKhachHang.setForeground(Color.WHITE);
			cardMain.show(cardPanelMain, "KhuyenMai");
		}
	}
	
	public void dangXuatActions() {
		int luaChon = JOptionPane.showConfirmDialog(this, "Có muốn đăng xuất không?", "Chú ý",JOptionPane.YES_NO_OPTION);
		if(luaChon == JOptionPane.YES_OPTION) {
			setVisible(false);
			new DangNhap_GUI().setVisible(true);
		}
	}
	
	private JButton addButton(String title, Dimension size, Color color, int fontSize) {
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
