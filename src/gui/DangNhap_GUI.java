package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import connectDB.ConnectDB;
import dao.NhanVien_DAO;
import dao.TaiKhoan_DAO;
import entity.NhanVien;
import entity.TaiKhoan;

public class DangNhap_GUI extends JFrame implements ComponentListener, ActionListener, ItemListener {
	private JTextField txtTenDN;
	private JPasswordField txtMK;
	private JCheckBox chkHienMK;
	private JButton btnDangNhap;

	public DangNhap_GUI() {
		super("Bến An Long - Đăng nhập");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		ConnectDB.getInstance().getConnection();
		
		
		//================Layout Main================
		JSplitPane spMain = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		add(spMain);
		spMain.setDividerSize(1);
		spMain.setEnabled(false);
		
		
		//================Layout Left================
		JLabel lbLeft;
		spMain.add(lbLeft = new JLabel());
		ImageIcon imgBg = new ImageIcon("img//Background_DN.jpg");
		lbLeft.setIcon(imgBg);
		
		
		//================Layout Right================
		JPanel pRight;
		spMain.add(pRight = new JPanel());
		pRight.setLayout(new BorderLayout());
		
		//-----North Right-----
		JLabel lbLogo;
		pRight.add(lbLogo = new JLabel(), BorderLayout.NORTH);
		ImageIcon imgLogo = new ImageIcon("img//Logo_DN.png");
		lbLogo.addComponentListener(new ComponentAdapter() {
		    public void componentResized(ComponentEvent e) {
		        int width = lbLogo.getWidth();
		        Image scaledImage = imgLogo.getImage().getScaledInstance(width, (int) (imgLogo.getIconHeight()*0.7), Image.SCALE_SMOOTH);
		        ImageIcon scaledIcon = new ImageIcon(scaledImage);
		        lbLogo.setIcon(scaledIcon);
		    }
		});
		
		//-----Center Right-----
		JPanel pDangNhap = new JPanel();
		pDangNhap.setLayout(new BorderLayout());
		pRight.add(pDangNhap, BorderLayout.CENTER);
		JPanel pDNNorth = new JPanel();
		pDangNhap.add(pDNNorth, BorderLayout.NORTH);
		pDNNorth.setBackground(new Color(98, 79, 64));
		JLabel lbDN;
		pDNNorth.add(lbDN = new JLabel("Đăng Nhập"));
		lbDN.setFont(new Font(getName(), Font.BOLD, 66));
		lbDN.setForeground(Color.WHITE);
		lbDN.setBorder(BorderFactory.createEmptyBorder(30, 0, 50, 0));
		JPanel pDNCenter = new JPanel();
		pDNCenter.setLayout(new BoxLayout(pDNCenter, BoxLayout.Y_AXIS));
		pDangNhap.add(pDNCenter, BorderLayout.CENTER);
		JPanel pRow1 = new JPanel();
		pDNCenter.add(pRow1);
		pRow1.setBackground(new Color(98, 79, 64));
		JLabel lbTenDN;
		pRow1.add(lbTenDN = new JLabel("Tên đăng nhập: "));
		lbTenDN.setFont(new Font(getName(), Font.BOLD, 30));
		lbTenDN.setForeground(Color.WHITE);
		pRow1.add(txtTenDN = new JTextField(30));
		txtTenDN.setPreferredSize(new Dimension(txtTenDN.getWidth(), 50));
		txtTenDN.setFont(new Font(getName(), Font.PLAIN, 24));
		JPanel pRow2 = new JPanel();
		pDNCenter.add(pRow2);
		pRow2.setBackground(new Color(98, 79, 64));
		JLabel lbMK;
		pRow2.add(lbMK = new JLabel("Mật khẩu: "));
		lbMK.setFont(new Font(getName(), Font.BOLD, 30));
		lbMK.setForeground(Color.WHITE);
		lbMK.setPreferredSize(lbTenDN.getPreferredSize());
		pRow2.add(txtMK = new JPasswordField(30));
		txtMK.setPreferredSize(new Dimension(txtTenDN.getWidth(), 50));
		txtMK.setFont(new Font(getName(), Font.PLAIN, 24));
		txtMK.setEchoChar('\u25CF');
		JPanel pRow3 = new JPanel();
		pDNCenter.add(pRow3);
		pRow3.setBackground(new Color(98, 79, 64));
		JLabel lbTemp1;
		pRow3.add(lbTemp1 = new JLabel());
		lbTemp1.setPreferredSize(lbTenDN.getPreferredSize());
		pRow3.add(chkHienMK = new JCheckBox("Hiển thị mật khẩu"));
		chkHienMK.setBackground(new Color(98, 79, 64));
		chkHienMK.setForeground(Color.WHITE);
		chkHienMK.setFont(new Font(getName(), Font.PLAIN, 20));
		chkHienMK.setBorder(BorderFactory.createEmptyBorder(0, 0, 70, 0));
		chkHienMK.setPreferredSize(new Dimension(600, 140));
		JPanel pRow4 = new JPanel();
		pDNCenter.add(pRow4);
		pRow4.setBackground(new Color(98, 79, 64));
		pRow4.add(btnDangNhap = new JButton("Đăng nhập"));
		btnDangNhap.setPreferredSize(new Dimension(400, 70));
		btnDangNhap.setFont(new Font(getName(), Font.BOLD, 26));
		btnDangNhap.setForeground(new Color(98, 79, 64));
		pRow4.setBorder(BorderFactory.createEmptyBorder(0, 0, 130, 0));
		
		//-----South Right-----
		JPanel pSouth = new JPanel();
		pRight.add(pSouth, BorderLayout.SOUTH);
		pSouth.setBackground(new Color(108, 93, 85));
		JLabel lbSouth;
		pSouth.add(lbSouth = new JLabel("Ly cà phê, khởi đầu cho thành công!"));
		lbSouth.setFont(new Font(getName(), Font.PLAIN, 18));
		lbSouth.setForeground(Color.WHITE);
		lbSouth.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		
		
		btnDangNhap.addActionListener(this);
		chkHienMK.addItemListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if(source.equals(btnDangNhap)) {
			dangNhapActions();
		}
	}

	public void dangNhapActions() {
		String tenTK = txtTenDN.getText();
		char[] matKhau = txtMK.getPassword();
		TaiKhoan_DAO tk_dao = new TaiKhoan_DAO();
		String matKhauSHA = tk_dao.hashPasswordSHA256(new String(matKhau));
		ArrayList<TaiKhoan> dsTK = tk_dao.getAllTK();
		NhanVien nhanVien = null;
		if (tenTK.equals("admin"))
			if (new String(matKhau).toString().equals("admin")) {
				this.setVisible(false);
				new NguoiQuanLy_GUI().setVisible(true);
				return;
			}
		for (TaiKhoan tk : dsTK) {
			if (tk.getTenTK().equals(tenTK))
				if (tk.getMatKhau().equals(matKhauSHA)) {
					NhanVien_DAO nv_dao = new NhanVien_DAO();
					ArrayList<NhanVien> dsNV = nv_dao.getAllNV();
					for (NhanVien nv : dsNV) {
						if (nv.getTaiKhoanNV().getTenTK().equals(tk.getTenTK())) {
							nhanVien = nv;
							break;
						}
					}
					this.setVisible(false);
					new NhanVien_GUI(nhanVien).setVisible(true);
					return;
				}
		}
		JOptionPane.showMessageDialog(this, "Đăng nhập không thành công!");
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(chkHienMK.isSelected()) {
			txtMK.setEchoChar((char)0);
		} else {
			txtMK.setEchoChar('\u25CF');
		}
	}
	
	@Override
	public void componentResized(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
}