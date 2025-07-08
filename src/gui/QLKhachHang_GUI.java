package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

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
import javax.swing.table.JTableHeader;

import connectDB.ConnectDB;
import dao.KhachHang_DAO;
import entity.KhachHang;

public class QLKhachHang_GUI extends JFrame implements ActionListener, MouseListener {

	private JPanel pKhachHang;
	private JTextField txtTimKH;
	private JButton btnTimKHSDT;
	private JButton btnThemKH;
	private DefaultTableModel tbModelKH;
	private JTable tableKH;
	private JTextField txtHoTenKH_KhachHang;
	private JTextField txtEmaiKH_KhachHang;
	private JTextField txtSDTKH_KH;
	private JTextField txtDiemTL;
	private JButton btnxoaKH;
	private JButton btnSuaKH;
	private JTextField txtformHoTenKH;
	private JTextField txtformSDTKH;
	private JTextField txtformEmailKH;
	private JButton btnformDKTV;
	private KhachHang_DAO KH_dao;
	private JButton btnCapNhatTTKH;
	private JTextField txtHoTenCapNhat;
	private JTextField txtSDTCapNhat;
	private JTextField txtEmailCapNhat;
	private JTextField txtDiemTLCapNhat;
	private JFrame JFrameCapNhatTT;
	private JFrame fDKTV;

	public QLKhachHang_GUI() {
		// TODO Auto-generated constructor stub
		super();
		ConnectDB.getInstance().connect();
		KH_dao = new KhachHang_DAO();

	}
//	----------------------- Giao diện chính 

	public JPanel createKhachHang() {
		// TODO Auto-generated method stub

		pKhachHang = new JPanel(new BorderLayout());
		JPanel pNorth = new JPanel();
		pKhachHang.add(pNorth, BorderLayout.NORTH);
		pNorth.setBackground(new Color(97, 79, 69));
		JLabel lbQLKH;
		pNorth.add(lbQLKH = new JLabel("Quản Lý Khách Hàng"));
		lbQLKH.setFont(new Font(getName(), Font.BOLD, 30));
		lbQLKH.setForeground(Color.WHITE);
		pNorth.setBorder(BorderFactory.createEmptyBorder(18, 0, 18, 0));

		JPanel pCenter = new JPanel(new GridLayout(2, 1));
		pKhachHang.add(pCenter, BorderLayout.CENTER);
		JPanel pCTT = createThongTinKH();
		pCenter.add(pCTT);

		JPanel pCBang = createTbKH();
		pCenter.add(pCBang);

		JPanel pSound = createSound();
		pKhachHang.add(pSound, BorderLayout.SOUTH);

		DocDuLieuKhachHangDatabase();

		return pKhachHang;
	}

	private JPanel createThongTinKH() {
		JPanel pCTT = new JPanel(new BorderLayout());

		JPanel plbTT = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pCTT.add(plbTT, BorderLayout.NORTH);

		JLabel lbTTKH;
		plbTT.add(lbTTKH = new JLabel("Thông tin khách hàng "));
		lbTTKH.setFont(new Font("Arial", Font.BOLD, 20));

		JPanel pTTcenter = new JPanel();
		pTTcenter.setLayout(new BoxLayout(pTTcenter, BoxLayout.Y_AXIS));
		pCTT.add(pTTcenter, BorderLayout.CENTER);

		JPanel pRow1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pTTcenter.add(pRow1);
		JLabel lbRow1;
		pRow1.add(lbRow1 = new JLabel("Họ Tên: "));
		lbRow1.setFont(new Font("Arial", Font.BOLD, 14));
		pRow1.add(txtHoTenKH_KhachHang = new JTextField(35));
		lbRow1.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		txtHoTenKH_KhachHang.setEditable(false);
		txtHoTenKH_KhachHang.setBorder(null);

		JLabel lbRow11;
		pRow1.add(lbRow11 = new JLabel("Email : "));
		lbRow11.setFont(new Font("Arial", Font.BOLD, 14));
		pRow1.add(txtEmaiKH_KhachHang = new JTextField(35));

		JPanel pRow2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pTTcenter.add(pRow2);
		JLabel lbRow21;
		txtEmaiKH_KhachHang.setEditable(false);
		txtEmaiKH_KhachHang.setBorder(null);
		pRow2.add(lbRow21 = new JLabel("SĐT: "));
		lbRow21.setFont(new Font("Arial", Font.BOLD, 14));

		pRow2.add(txtSDTKH_KH = new JTextField(35));
		lbRow21.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		txtSDTKH_KH.setEditable(false);
		txtSDTKH_KH.setBorder(null);

		JLabel lbRow3;
		pRow2.add(lbRow3 = new JLabel("Điểm tích lũy : "));
		lbRow3.setFont(new Font("Arial", Font.BOLD, 14));
		pRow2.add(txtDiemTL = new JTextField(35));
		txtDiemTL.setEditable(false);
		txtDiemTL.setBorder(null);
		JPanel pRow3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pTTcenter.add(pRow3);
		JPanel pRow31;
		pRow3.add(pRow31 = new JPanel(new GridLayout(1, 2, 30, 0)));
		pRow31.add(btnxoaKH = new JButton("Xóa"));
		pRow31.add(btnSuaKH = new JButton("Cập Nhật"));
		pTTcenter.setBorder(BorderFactory.createEmptyBorder(20, 50, 50, 50));
		lbRow1.setPreferredSize(lbRow3.getPreferredSize());
		lbRow11.setPreferredSize(lbRow3.getPreferredSize());
		lbRow21.setPreferredSize(lbRow3.getPreferredSize());

		btnxoaKH.addActionListener(this);
		btnSuaKH.addActionListener(this);

		return pCTT;
	}

	private JPanel createTbKH() {

		JPanel pCBang = new JPanel(new BorderLayout());
		JPanel plbKH = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pCBang.add(plbKH, BorderLayout.NORTH);
		JLabel lbDSKH;
		plbKH.add(lbDSKH = new JLabel("Danh sách khách hàng"));
		lbDSKH.setFont(new Font("Arial", Font.BOLD, 20));
		lbDSKH.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
		String[] cols = { "SĐT", "Họ tên", "Email", "Điểm tích lũy" };
		tbModelKH = new DefaultTableModel(cols, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // Tất cả các ô sẽ không thể chỉnh sửa
			}
		};
		tableKH = new JTable(tbModelKH);
		JScrollPane jScrollKH;
		pCBang.add(jScrollKH = new JScrollPane(tableKH), BorderLayout.CENTER);
		tableKH.setFont(new Font("Arial", Font.PLAIN, 15));
		JTableHeader header = tableKH.getTableHeader();
		header.setBackground(new Color(97, 79, 69));
		header.setFont(new Font("Arial", Font.PLAIN, 18));
		header.setForeground(Color.WHITE);
		tableKH.addMouseListener(this);
		return pCBang;
	}

	private JPanel createSound() {
		JPanel pSound = new JPanel(new BorderLayout());
		JPanel pSound_1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pSound.add(pSound_1, BorderLayout.CENTER);
		pSound_1.setBackground(new Color(97, 79, 69));

		JLabel lbTimKH;
		pSound_1.add(lbTimKH = new JLabel("Tìm khách hàng : "));
		lbTimKH.setBorder(BorderFactory.createEmptyBorder(15, 40, 10, 0));
		lbTimKH.setForeground(Color.white);
		lbTimKH.setFont(new Font("Arial", Font.BOLD, 14));
		pSound_1.add(txtTimKH = new JTextField(20));
		pSound_1.add(btnTimKHSDT = new JButton("Tìm "));

		JPanel pSound_2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		pSound.add(pSound_2, BorderLayout.EAST);
		pSound_2.add(btnThemKH = new JButton("Thêm khách hàng "));
		pSound_2.setBackground(new Color(97, 79, 69));
		pSound_2.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10));

		btnThemKH.addActionListener(this);
		btnTimKHSDT.addActionListener(this);
		return pSound;
	}
	// ---------------- jfame đăng ký thành viên 
		public void DKTVActions() {
			fDKTV = new JFrame();
			fDKTV.setVisible(true);
			fDKTV.setTitle("Bến An Long - Đăng ký thành viên");
			fDKTV.setSize(800, 500);
			fDKTV.setLocationRelativeTo(null);
			Box pDKTV = Box.createVerticalBox();
			fDKTV.add(pDKTV);

			JPanel pLbDKTV = new JPanel();
			pDKTV.add(pLbDKTV);
			pLbDKTV.setBackground(new Color(97, 79, 69));
			JLabel lbDKTV;
			pLbDKTV.add(lbDKTV = new JLabel("ĐĂNG KÝ THÀNH VIÊN"));
			lbDKTV.setFont(new Font(getName(), Font.BOLD, 36));
			lbDKTV.setForeground(Color.WHITE);
			lbDKTV.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

			JPanel pRow1 = new JPanel();
			pDKTV.add(pRow1);
			pRow1.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
			JLabel lbRow1;
			pRow1.add(lbRow1 = new JLabel("Họ tên khách hàng: "));
			lbRow1.setFont(new Font(getName(), Font.BOLD, 18));
			pRow1.add(txtformHoTenKH = new JTextField(25));
			txtformHoTenKH.setFont(new Font(getName(), Font.PLAIN, 18));

			pDKTV.add(Box.createVerticalStrut(20));
			JPanel pRow2 = new JPanel();
			pDKTV.add(pRow2);
			JLabel lbRow2;
			pRow2.add(lbRow2 = new JLabel("Số điện thoại: "));
			lbRow2.setPreferredSize(lbRow1.getPreferredSize());
			lbRow2.setFont(lbRow1.getFont());
			pRow2.add(txtformSDTKH = new JTextField(25));
			txtformSDTKH.setFont(txtformHoTenKH.getFont());

			pDKTV.add(Box.createVerticalStrut(20));
			JPanel pRow3 = new JPanel();
			pDKTV.add(pRow3);
			JLabel lbRow3;
			pRow3.add(lbRow3 = new JLabel("Email: "));
			lbRow3.setPreferredSize(lbRow1.getPreferredSize());
			lbRow3.setFont(lbRow1.getFont());
			pRow3.add(txtformEmailKH = new JTextField(25));
			txtformEmailKH.setFont(txtformHoTenKH.getFont());

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

	// jfame cập nhật thông tin thành viên 	
		public void actionCapNhat() {
			if (txtHoTenKH_KhachHang.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Chọn khách hàng cần cập nhật");
				return;
			} 
				JFrameCapNhatTT = new JFrame();
				JFrameCapNhatTT.setVisible(true);
				JFrameCapNhatTT.setTitle("Bến An Long - Cập nhật thông tin khách hàng");
				JFrameCapNhatTT.setSize(800, 500);
				JFrameCapNhatTT.setLocationRelativeTo(null);
				Box pDKKH = Box.createVerticalBox();
				JFrameCapNhatTT.add(pDKKH);

				JPanel pLbDKKH = new JPanel();
				pDKKH.add(pLbDKKH);
				pLbDKKH.setBackground(new Color(97, 79, 69));
				JLabel lbDKTV;
				pLbDKKH.add(lbDKTV = new JLabel("CẬP NHẬT THÔNG TIN KHÁCH HÀNG"));
				lbDKTV.setFont(new Font(getName(), Font.BOLD, 30));
				lbDKTV.setForeground(Color.WHITE);
				lbDKTV.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

				JPanel pRow1 = new JPanel();
				pDKKH.add(pRow1);
				JLabel lbRow1;
				pRow1.add(lbRow1 = new JLabel("Họ tên khách hàng: "));
				lbRow1.setFont(new Font(getName(), Font.BOLD, 18));
				pRow1.add(txtHoTenCapNhat = new JTextField(20));
				txtHoTenCapNhat.setFont(new Font(getName(), Font.PLAIN, 18));
				txtHoTenCapNhat.setText(txtHoTenKH_KhachHang.getText());

				pDKKH.add(Box.createVerticalStrut(5));
				JPanel pRow2 = new JPanel();
				pDKKH.add(pRow2);
				JLabel lbRow2;
				pRow2.add(lbRow2 = new JLabel("Số điện thoại: "));
				lbRow2.setPreferredSize(lbRow1.getPreferredSize());
				lbRow2.setFont(lbRow1.getFont());
				pRow2.add(txtSDTCapNhat = new JTextField(20));
				txtSDTCapNhat.setFont(txtHoTenCapNhat.getFont());
				txtSDTCapNhat.setText(txtSDTKH_KH.getText());

				pDKKH.add(Box.createVerticalStrut(5));
				JPanel pRow3 = new JPanel();
				pDKKH.add(pRow3);
				JLabel lbRow3;
				pRow3.add(lbRow3 = new JLabel("Email: "));
				lbRow3.setPreferredSize(lbRow1.getPreferredSize());
				lbRow3.setFont(lbRow1.getFont());
				pRow3.add(txtEmailCapNhat = new JTextField(20));
				txtEmailCapNhat.setFont(txtHoTenCapNhat.getFont());
				txtEmailCapNhat.setText(txtEmaiKH_KhachHang.getText());

				pDKKH.add(Box.createVerticalStrut(5));
				JPanel pRow5 = new JPanel();
				pDKKH.add(pRow5);
				JLabel lbRow5;
				pRow5.add(lbRow5 = new JLabel("Điểm tích lũy: "));
				lbRow5.setPreferredSize(lbRow1.getPreferredSize());
				lbRow5.setFont(lbRow1.getFont());
				pRow5.add(txtDiemTLCapNhat = new JTextField(20));
				txtDiemTLCapNhat.setFont(txtHoTenCapNhat.getFont());
				txtDiemTLCapNhat.setEditable(false);
				txtDiemTLCapNhat.setText(txtDiemTL.getText());

				pDKKH.add(Box.createVerticalStrut(5));
				JPanel pRow4 = new JPanel();
				pDKKH.add(pRow4);
				pRow4.add(btnCapNhatTTKH = new JButton("Cập Nhật"));
				btnCapNhatTTKH.setFont(lbRow1.getFont());
				btnCapNhatTTKH.setFocusPainted(false);
				btnCapNhatTTKH.setBackground(new Color(97, 79, 69));
				btnCapNhatTTKH.setForeground(Color.WHITE);
				btnCapNhatTTKH.setPreferredSize(new Dimension(200, 60));

				btnCapNhatTTKH.addActionListener(this);
			
		}
//----------- xử lý 
//------------ đọc hiển thị dữ liệu ra bảng 
	private void DocDuLieuKhachHangDatabase() {
		tbModelKH.getDataVector().removeAllElements();
		KhachHang_DAO ds = new KhachHang_DAO();

		List<KhachHang> list = ds.getAllKH();
		for (KhachHang s : list) {
			String[] rowData = { s.getSdtKH(), s.getTenKH(), s.getEmailKH(), Integer.toString(s.getDiemTL()) };
			tbModelKH.addRow(rowData);
		}
		tableKH.setModel(tbModelKH);
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

		if (KiemTraDKTV(txtformHoTenKH, txtformSDTKH, txtformEmailKH)) {
			String HoTenKH = txtformHoTenKH.getText().trim();
			String sdtKH = txtformSDTKH.getText().trim();
			String emailKH = txtformEmailKH.getText().trim();

			KhachHang kh = new KhachHang(sdtKH, HoTenKH, emailKH, 0);
			try {
				if (KH_dao.insertKH(kh)) {
					JOptionPane.showMessageDialog(this, "Thêm khách hàng thành công ");
					DocDuLieuKhachHangDatabase();
					fDKTV.dispose();
				} else
					JOptionPane.showMessageDialog(this, "Khách hàng đã tồn tại");
			} catch (Exception e) {
				// TODO: handle exception

			}
		}
	}



//----------- action btn cập nhật của jfame cập nhật thông tin thành viên 
	public void suaKH() {
		if (KiemTraDKTV(txtHoTenCapNhat, txtSDTCapNhat, txtEmailCapNhat)) {
			String HoTenKH = txtHoTenCapNhat.getText().trim();
			String sdtKH = txtSDTCapNhat.getText().trim();
			String emailKH = txtEmailCapNhat.getText().trim();
			String diemTL = txtDiemTLCapNhat.getText().trim();
			String sdtKHCu = txtSDTKH_KH.getText().trim();

			KhachHang kh = new KhachHang(sdtKH, HoTenKH, emailKH, Integer.parseInt(diemTL));
			try {
				if (KH_dao.insertKH(kh)) {
					JOptionPane.showMessageDialog(this, "Cập nhật khách hàng thành công");
					KH_dao.deleteKH(sdtKHCu);
					DocDuLieuKhachHangDatabase();
					xoaTrang();
					JFrameCapNhatTT.dispose();

				} else {
					if (sdtKH.equals(sdtKHCu)) { // Sửa thành equals để so sánh nội dung chuỗi
						KH_dao.deleteKH(sdtKHCu);
						KH_dao.insertKH(kh);
						DocDuLieuKhachHangDatabase();
						JOptionPane.showMessageDialog(this, "Cập nhật khách hàng thành công");
						xoaTrang();
						JFrameCapNhatTT.dispose();
					} else {
						JOptionPane.showMessageDialog(this, "SĐT khách hàng đã tồn tại");
					}
				}
			} catch (Exception e) {
				// Xử lý ngoại lệ nếu có
				e.printStackTrace(); // In lỗi ra console để dễ debug
			}
		}
	}

//	-------- xóa khách hàng 
	public void xoaKH() {
		int row = tableKH.getSelectedRow();
		if (row >= 0) {
			String sdtKH = (String) tableKH.getValueAt(row, 0);

			int nhacNho = JOptionPane.showConfirmDialog(this,
					"Chắc chắn xóa khách hàng " + txtHoTenKH_KhachHang.getText() + " ?", "chú ý",
					JOptionPane.YES_NO_OPTION);
			if (nhacNho == JOptionPane.YES_OPTION) {
				if (KH_dao.deleteKH(sdtKH)) {
					tbModelKH.removeRow(row);
					xoaTrang();
					JOptionPane.showMessageDialog(this, "Xóa thành công");
				} else {
					JOptionPane.showMessageDialog(this, "Không thể xóa");
				}
			}
		}
	}

	public void xoaTrang() {
		txtDiemTL.setText("");
		txtSDTKH_KH.setText("");
		txtHoTenKH_KhachHang.setText("");
		txtEmaiKH_KhachHang.setText("");
	}
	//----------------- tìm 
	public void actionTim() {
		xoaTrang();
		String sdtKHTim = txtTimKH.getText();
		if ( !(sdtKHTim.isEmpty())) {
			 for (int i = 0; i < tableKH.getRowCount(); i++) {
			        if (tableKH.getValueAt(i, 0).toString().equals(sdtKHTim)) {
			        	tableKH.setRowSelectionInterval(i, i); // Chọn hàng tìm thấy
			        	mouseClicked(null);
			        	tableKH.scrollRectToVisible(tableKH.getCellRect(i, 0, true)); // Cuộn tới hàng đó
			        	txtTimKH.setText("");
			        	return;
			        }
			    }
			 JOptionPane.showMessageDialog(this, "Không tìm thấy SĐT khách hàng: " + sdtKHTim);
		}
		JOptionPane.showMessageDialog(this, "Nhập SĐT khách hàng cần tìm ");
	}
	
//---------------
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if (source.equals(btnThemKH)) {
			DKTVActions();
		}
		if (source.equals(btnformDKTV)) {
			themKH();
		}
		if (source.equals(btnTimKHSDT)) {
			actionTim();
		}
		if (source.equals(btnxoaKH)) {
			xoaKH();
		}
		if (source.equals(btnSuaKH)) {
			actionCapNhat();
		}
		if (source.equals(btnCapNhatTTKH)) {
			suaKH();
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = tableKH.getSelectedRow();
		txtSDTKH_KH.setText(tableKH.getValueAt(row, 0).toString());
		txtHoTenKH_KhachHang.setText(tableKH.getValueAt(row, 1).toString());
		txtEmaiKH_KhachHang.setText(tableKH.getValueAt(row, 2).toString());
		txtDiemTL.setText(tableKH.getValueAt(row, 3).toString());

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

}




