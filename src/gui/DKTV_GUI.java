package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import connectDB.ConnectDB;
import dao.KhachHang_DAO;
import entity.KhachHang;

public class DKTV_GUI extends JFrame implements ActionListener{
	private JTextField txtfromHoTenKH;
	private JTextField txtfromSDTKH;
	private JTextField txtfromEmailKH;
	private JButton btnformDKTV;
	private KhachHang_DAO KH_dao;
	private JFrame fDKTV;
	public DKTV_GUI() {
		// TODO Auto-generated constructor stub
		super();
		ConnectDB.getInstance().connect();
		KH_dao = new KhachHang_DAO();
	}
	
	
	public void DKTVActions() {
		fDKTV = new JFrame();
		fDKTV.setVisible(true);
		fDKTV.setTitle("Bến An Long - Đăng ký thành viên");
		fDKTV.setSize(600, 600);
		fDKTV.setLocationRelativeTo(null);
		Box pDKTV = Box.createVerticalBox();
		fDKTV.add(pDKTV);
		
		JPanel pLbDKTV = new JPanel();
		pDKTV.add(pLbDKTV);
		pLbDKTV.setBackground(new Color(97, 79, 69));
		JLabel lbDKTV;
		pLbDKTV.add(lbDKTV = new JLabel("ĐĂNG KÝ THÀNH VIÊN"));
		lbDKTV.setFont(new Font(getName(), Font.BOLD, 30));
		lbDKTV.setForeground(Color.WHITE);
		lbDKTV.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
		
		JPanel pRow1 = new JPanel();
		pDKTV.add(pRow1);
		pRow1.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
		JLabel lbRow1;
		pRow1.add(lbRow1 = new JLabel("Họ tên khách hàng: "));
		lbRow1.setFont(new Font(getName(), Font.BOLD, 16));
		pRow1.add(txtfromHoTenKH = new JTextField(20));
		txtfromHoTenKH.setFont(new Font(getName(), Font.PLAIN, 16));
		
		pDKTV.add(Box.createVerticalStrut(20));
		JPanel pRow2 = new JPanel();
		pDKTV.add(pRow2);
		JLabel lbRow2;
		pRow2.add(lbRow2 = new JLabel("Số điện thoại: "));
		lbRow2.setPreferredSize(lbRow1.getPreferredSize());
		lbRow2.setFont(lbRow1.getFont());
		pRow2.add(txtfromSDTKH = new JTextField(20));
		txtfromSDTKH.setFont(txtfromHoTenKH.getFont());
		
		pDKTV.add(Box.createVerticalStrut(20));
		JPanel pRow3 = new JPanel();
		pDKTV.add(pRow3);
		JLabel lbRow3;
		pRow3.add(lbRow3 = new JLabel("Email: "));
		lbRow3.setPreferredSize(lbRow1.getPreferredSize());
		lbRow3.setFont(lbRow1.getFont());
		pRow3.add(txtfromEmailKH = new JTextField(20));
		txtfromEmailKH.setFont(txtfromHoTenKH.getFont());
		
		pDKTV.add(Box.createVerticalStrut(20));
		JPanel pRow4 = new JPanel();
		pDKTV.add(pRow4);
		pRow4.add(btnformDKTV = new JButton("Đăng Ký"));
		btnformDKTV.setFont(lbRow1.getFont());
		btnformDKTV.setFocusPainted(false);
		btnformDKTV.setBackground(new Color(97, 79, 69));
		btnformDKTV.setForeground(Color.WHITE);
		btnformDKTV.setPreferredSize(new Dimension(200, 60));
		btnformDKTV.addActionListener(this);
	}

	
	//	--------- kiểm tra thông tin trang đăng ký thành viên 
	private boolean KiemTraDKTV(JTextField txtHT, JTextField txtSDT, JTextField txtEmail) {
		// TODO Auto-generated method stub
		String HoTenKH = txtHT.getText().trim();
		String sdtKH = txtSDT.getText().trim();
		String emailKH = txtEmail.getText().trim();

		if (HoTenKH.isEmpty()) {
			JOptionPane.showMessageDialog(this, "ERROR : Tên không được để rỗng!");
			txtHT.requestFocus();
			return false;
		}
		if (!(sdtKH.matches("[0-9]{10}"))) {
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

	//	------------ thêm khách hàng 
	public void themKH() {

		if (KiemTraDKTV(txtfromHoTenKH, txtfromSDTKH, txtfromEmailKH)) {
			String HoTenKH = txtfromHoTenKH.getText().trim();
			String sdtKH = txtfromSDTKH.getText().trim();
			String emailKH = txtfromEmailKH.getText().trim();

			KhachHang kh = new KhachHang(sdtKH, HoTenKH, emailKH, 0);
			try {
				if (KH_dao.insertKH(kh)) {
					JOptionPane.showMessageDialog(this, "Thêm khách hàng thành công ");
					
					fDKTV.dispose();
				} else
					JOptionPane.showMessageDialog(this, "Khách hàng đã tồn tại");
			} catch (Exception e) {
				// TODO: handle exception

			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if (source.equals(btnformDKTV)) {
			themKH();
		}
	}
	
	
}
