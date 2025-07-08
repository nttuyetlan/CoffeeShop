package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;

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
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.text.JTextComponent;

import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.ChiTietHD_DAO;
import dao.HoaDon_DAO;
import entity.ChiTietHD;
import entity.HoaDon;

public class LSHoaDon_GUI extends JFrame implements ActionListener, MouseListener{
	Color color = new Color(97, 79, 69);
	private JTextField txt1;
	private JTextField txt2;
	private JTextField txt3;
	private JTextField txt4;
	private DefaultTableModel tblModel;
	private JTable tbt;
	private JTextComponent txtA;
	private JTextComponent txtB;
	private JButton btnHuyHD;
	private ArrayList<HoaDon> list;
	private HoaDon_DAO hd;
	private JDateChooser dateChooser;
	private JDateChooser dateChooser2;
	private static JButton btnTim;
	private static JButton btnFileHD;
	private static DefaultTableModel tableModelLSHD;
	private static JTable tableLSHD;
	private static JTextField txtTongHD;
	private static JButton btnxemChiTiet;
	private static JPanel pLSHoaDon;
	
	public LSHoaDon_GUI() {
		hd = new HoaDon_DAO();
	}
	
	public JPanel createLSHoaDon() {
		// TODO Auto-generated method stub
		pLSHoaDon = new JPanel();
		pLSHoaDon.setLayout(new BorderLayout());
		JPanel pNorth = new JPanel();
		pLSHoaDon.add(pNorth, BorderLayout.NORTH);
		pNorth.setPreferredSize(new Dimension(100, 84));
		
		JLabel lbTitle;
		pNorth.add(lbTitle = new JLabel("Lịch Sử Đặt Hàng"));
		lbTitle.setFont(new Font("Arial", Font.BOLD, 30));
		pNorth.setBackground(new Color(97, 79, 69));
		lbTitle.setForeground(Color.white);
		lbTitle.setPreferredSize(new Dimension(300, 75));
		
		JPanel pCenter = new JPanel();
		pLSHoaDon.add(pCenter, BorderLayout.CENTER);
		pCenter.setLayout(new BorderLayout());
		JPanel panelTim = new JPanel();
		pCenter.add(panelTim, BorderLayout.NORTH);
		panelTim.setLayout(new BoxLayout(panelTim, BoxLayout.X_AXIS));
		JLabel lbNgayBD;
		panelTim.add(lbNgayBD = new JLabel("Từ ngày:"));
		lbNgayBD.setFont(new Font("Arial", Font.PLAIN, 14));
		// Tạo JDateChooser
        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd-MM-yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        dateChooser.setDate(calendar.getTime()); //        java.util.Date selectedDate = dateChooser.getDate();	LẤY GIÁ TRỊ TRONG Ô NGÀY
        dateChooser.setPreferredSize(new Dimension(400, 30));
        panelTim.add(dateChooser);
        
        JLabel lbNgayKT;
		panelTim.add(lbNgayKT = new JLabel("Đến ngày: "));
		lbNgayKT.setFont(new Font("Arial", Font.PLAIN, 14));
		// Tạo JDateChooser
        dateChooser2 = new JDateChooser();
        dateChooser2.setDateFormatString("dd-MM-yyyy");
        dateChooser2.setDate(new java.util.Date());
        dateChooser2.setPreferredSize(new Dimension(400, 30));
        panelTim.add(dateChooser2);
        
        panelTim.add(btnTim = new JButton("Lọc"));
        btnTim.setFocusPainted(false);
        btnTim.addActionListener(this);
        
        panelTim.add(Box.createRigidArea(new Dimension(80, 0)));
        panelTim.add(btnxemChiTiet = new JButton("Xem Chi Tiết"));
        btnxemChiTiet.setFocusPainted(false);
        btnxemChiTiet.addActionListener(this);
        
        lbNgayBD.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
        lbNgayKT.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
        dateChooser.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 50));
        dateChooser2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 50));
        panelTim.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        Box b = Box.createHorizontalBox();
        String[] headers = "Mã;Ngày lập;Nhân viên;Khách hàng;Tổng tiền".split(";");
        tableModelLSHD = new DefaultTableModel(headers, 0);
        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(tableLSHD = new JTable(tableModelLSHD));
        tableLSHD.setRowHeight(50);
        tableLSHD.setAutoCreateRowSorter(true);
        tableLSHD.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        JTableHeader header = tableLSHD.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 16));
        header.setForeground(Color.WHITE);
        header.setBackground(new Color(97, 79, 69));
        header.setPreferredSize(new Dimension(header.getWidth(), 40));
        header.setReorderingAllowed(false);
        scroll.setPreferredSize(new Dimension(1345, 652));
        b.add(scroll);
        pCenter.add(b, BorderLayout.CENTER);
        
        JPanel pSouth = new JPanel();
        pSouth.setLayout(new FlowLayout(FlowLayout.LEFT));
        pSouth.setBackground(new Color(97, 79, 69));
        JLabel lbTongHD;
		pSouth.add(lbTongHD = new JLabel("Tổng số hóa đơn: "));
		pSouth.add(txtTongHD = new JTextField());
		lbTongHD.setFont(new Font("Arial", Font.HANGING_BASELINE, 16));
		lbTongHD.setForeground(Color.WHITE);
		EmptyBorder padding = new EmptyBorder(22, 0, 22, 0);
        lbTongHD.setBorder(padding);
		txtTongHD.setEditable(false);
		txtTongHD.setBorder(null);
		txtTongHD.setBackground(color);
		txtTongHD.setForeground(Color.WHITE);
		txtTongHD.setFont(new Font(getName(), Font.BOLD, 16));
		pLSHoaDon.add(pSouth, BorderLayout.SOUTH);
		
		tableLSHD.addMouseListener(this);
		loadHoaDonFromDataBase();
		return pLSHoaDon;
	}


	public JFrame chiTietHD() {
		JFrame jChitietHD = new JFrame();
		jChitietHD.setTitle("Chi tiết hóa đơn");
		jChitietHD.setVisible(true);
		jChitietHD.setSize(600,650);
		jChitietHD.setLocationRelativeTo(null);
		
		JPanel pNorth = new JPanel();
		jChitietHD.add(pNorth, BorderLayout.NORTH);
		pNorth.setLayout(new BorderLayout());
		JPanel pDiaChi;
		pNorth.add(pDiaChi = new JPanel(), BorderLayout.NORTH);
		pDiaChi.setLayout(new BoxLayout(pDiaChi, BoxLayout.Y_AXIS));
		JPanel panel1, panel2, panel3, panel4;
		panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel1.add(new JLabel("12 Nguyễn Văn Bảo, Phường 4, Quận Gò Vấp"));
		panel2.add(new JLabel("0854361859"));
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
		jChitietHD.add(pCenter, BorderLayout.CENTER);
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
		p1.add(txt1 = new JTextField(10));
		p2.add(txt2 = new JTextField(10));
		p3.add(txt3 = new JTextField(10));
		p4.add(txt4 = new JTextField(10));
		txt1.setEditable(false);
		txt1.setBorder(null);
		txt2.setEditable(false);
		txt2.setBorder(null);
		txt3.setEditable(false);
		txt3.setBorder(null);
		txt4.setEditable(false);
		txt4.setBorder(null);
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
		tblModel = new DefaultTableModel(cols, 0);
		tbt = new JTable(tblModel);
		tbt.setBorder(BorderFactory.createEmptyBorder());
		tbt.getTableHeader().setBorder(BorderFactory.createEmptyBorder());
		tbt.setShowGrid(false);
		tbt.setRowSelectionAllowed(true);
		tbt.setColumnSelectionAllowed(true);
		JScrollPane scroll = new JScrollPane(tbt);
		pCCenter.add(scroll);
		JPanel pA, pB;
		pA = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pB = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblA, lblB;
		pA.add(lblA = new JLabel("Giảm giá:  "));
		pB.add(lblB = new JLabel("Tổng tiền:  "));
		pA.add(txtA = new JTextField(10));
		pB.add(txtB = new JTextField(10));
		txtA.setEditable(false);
		txtA.setBorder(null);
		txtB.setEditable(false);
		txtB.setBorder(null);
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
		jChitietHD.add(pSouth, BorderLayout.SOUTH);
		pSouth.add(btnHuyHD = new JButton("Hủy bỏ"));
		btnHuyHD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jChitietHD.dispose();
            }
        });
		btnHuyHD.setForeground(Color.WHITE);
		btnHuyHD.setBackground(color);
		btnHuyHD.setFocusPainted(false);
		
		return jChitietHD;
	}
	
	private void loadHoaDonFromDataBase() {
		int i = 0;
		list = hd.getAllHD();
		for(HoaDon h : list) {
			String[] rowData = {h.getMaHD(), h.getNgayLapHD()+"", h.getNvHD().getTenNV(), h.getKhHD().getTenKH(), h.getThanhToanHD()+""};
			tableModelLSHD.addRow(rowData);
			i++;
		}
		tableLSHD.setModel(tableModelLSHD);
		txtTongHD.setText(Integer.toString(i));
	}
	
	private void getHoaDonFromTime() {
		java.util.Date ngayBD = dateChooser.getDate();
		java.util.Date ngayKT = dateChooser2.getDate();
		
		if (ngayBD == null || ngayKT == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn cả ngày bắt đầu và ngày kết thúc.");
            return;
        }
		if (ngayKT.before(ngayBD)) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc không được trước ngày bắt đầu.");
            return;
        }
		// Chuyển đổi java.util.Date thành java.sql.Date
        java.sql.Date sqlngayBD = new java.sql.Date(ngayBD.getTime());
        java.sql.Date sqlngayKT = new java.sql.Date(ngayKT.getTime());
        
        try {
			Connection con = ConnectDB.getInstance().getConnection();
			CallableStatement stmt = con.prepareCall("{CALL getHoaDonToNgay(?, ?)}");
			stmt.setDate(1, sqlngayBD);
			stmt.setDate(2, sqlngayKT);
			ResultSet rs = stmt.executeQuery();
			
			tableModelLSHD.setRowCount(0);
			int i = 0;
			while(rs.next()) {
				String maHD = rs.getString("maHD");
				HoaDon hoadon = hd.getHD(maHD);
				String[] dong = {hoadon.getMaHD(), hoadon.getNgayLapHD()+"", hoadon.getNvHD().getTenNV(), hoadon.getKhHD().getTenKH(), hoadon.getTongTienHD()+""};
				tableModelLSHD.addRow(dong);
				i++;
			}
			tableLSHD.setModel(tableModelLSHD);
			txtTongHD.setText(Integer.toString(i));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnTim)) {
			getHoaDonFromTime();
	    } else if (o.equals(btnxemChiTiet)){
	    	if (tableLSHD.getSelectedRow() == -1)
	    		JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn!");
	    	else {
	    		chiTietHD();
	    		int row = tableLSHD.getSelectedRow();
	    		txt1.setText(tableModelLSHD.getValueAt(row, 0).toString());
	    		txt2.setText(tableModelLSHD.getValueAt(row, 2).toString());
	    		txt3.setText(tableModelLSHD.getValueAt(row, 1).toString());
	    		HoaDon hoadon = hd.getHD(tableModelLSHD.getValueAt(row, 0).toString());
	    		txt4.setText(hoadon.getBanHD().getMaBan());
	    		
	    		ChiTietHD_DAO cthd_dao = new ChiTietHD_DAO();
	    		ArrayList<ChiTietHD> dsCTHD_dao = cthd_dao.getAllCtHD();
	    		String ma = tableModelLSHD.getValueAt(row, 0).toString();
	    		for (ChiTietHD cthd : dsCTHD_dao) {
	    			if(cthd.getHoaDonCTHD().getMaHD().equals(ma)) {
	    				String [] dong = {cthd.getSanPhamCTHD().getMaSP(), cthd.getSanPhamCTHD().getTenSP(),Integer.toString(cthd.getSoLuongSP()), Double.toString(cthd.getSanPhamCTHD().getDonGiaSP()), Double.toString(cthd.getThanhTien())};
	    				tblModel.addRow(dong);
	    			}	
	    		}
	    		tbt.setModel(tblModel);
	    		txtA.setText(Double.toString(hoadon.getKmHD().getGiaTriKM()) + "đ");
	    		txtB.setText(Double.toString(hoadon.getThanhToanHD()) + "đ");	    		
	    	}	
	    }
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
