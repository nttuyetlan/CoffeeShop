package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.toedter.calendar.JDateChooser;

import dao.KhachHang_DAO;
import dao.KhuyenMai_DAO;
import entity.KhachHang;
import entity.KhuyenMai;

public class QLKhuyenMai_GUI extends JFrame implements ActionListener{
	private JButton btnTimKM;
	private JButton btnThemKM;
	//-- tong
	private DefaultTableModel tblModelKM;
	private JTable tblKM;
	
	private JComboBox<String> cbb;
	private JButton btnChiTiet;
	private JTextField txtTimKM;
	private JTextField txtTongKM;
	private CardLayout cardlayout;
	private JPanel cardContainer;
	private JButton btnTT;
	private JButton btnDSKM;
	private TextField txtMa;
	private TextField txtTenKM;
	private JTextField txtGiaTriKM;
	private JButton btnSua;
	private JButton btnXoaKM;
	private JButton btnTimDSKM;
	//--- thông tin 
	private DefaultTableModel tblModel;
	private JTable tbl;
	
	private JTextField txtThemTenKM;
	private JTextField txtTienKM;
	private JButton btnThemLuu;
	private KhuyenMai_DAO KM_dao;
	private JDateChooser dateChooser2;
	private JDateChooser dateChooser3;
	private JFrame jfXoaKMDS;
	private JTextField txtFormXoaMa;
	private JButton btnXoa_XacNhan;
	private JTextField txtTimDSKM;
	private JDateChooser dateChooser;
	private JDateChooser dateChooser1;
	
	public QLKhuyenMai_GUI() {
		// TODO Auto-generated constructor stub
		super();
		KM_dao = new KhuyenMai_DAO();
	}
	
	public JPanel createKhuyenMai() {
		// TODO Auto-generated method stub
		JPanel pKhuyenMai = new JPanel(new BorderLayout());
		JPanel pNorth = new JPanel();
		pKhuyenMai.add(pNorth, BorderLayout.NORTH);
		pNorth.setBackground(new Color(97, 79, 69));
		JLabel lbQLKM;
		pNorth.add(lbQLKM = new JLabel("Quản Lý Khuyến Mãi"));
		lbQLKM.setFont(new Font(getName(), Font.BOLD, 30));
		lbQLKM.setForeground(Color.WHITE);
		pNorth.setBorder(BorderFactory.createEmptyBorder(18, 0, 18, 0));
			
		JPanel pCenter = new JPanel(new BorderLayout());
		pKhuyenMai.add(pCenter, BorderLayout.CENTER);
		JPanel b;
		pCenter.add(b = new JPanel(), BorderLayout.NORTH);
		b.setLayout(new BorderLayout());
		JSplitPane b1;
		b.add(b1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT), BorderLayout.NORTH);
		JPanel pLeft, pRight;
		pLeft = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 20));
		pRight = new JPanel(new FlowLayout(FlowLayout.RIGHT, 30, 20));
		b1.add(pLeft);
		b1.add(pRight);
		pLeft.add(txtTimKM = new JTextField(20));
		pLeft.add(btnTimKM = new JButton("Tìm"));
		txtTimKM.setPreferredSize(new Dimension(50,25));
		btnTimKM.setFocusPainted(false);
		btnTimKM.addActionListener(this);
		
		pLeft.add(btnChiTiet = new JButton("Xem Chi Tiết"));
		btnChiTiet.setFocusPainted(false);
		btnChiTiet.addActionListener(this);
		String[] agrs = {"Tất cả","Còn hạn khuyến mãi", "Hết hạn khuyến mãi"};
	    cbb = new JComboBox<String>(agrs);
	    pRight.add(cbb);
	    b1.setDividerSize(0);
	    cbb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	KhuyenMai_DAO ds = new KhuyenMai_DAO();
                String selectedItem = (String) cbb.getSelectedItem();
                
                if ("Tất cả".equals(selectedItem)) {
                    List<KhuyenMai> list = ds.getAllKM();
                    DocDuLieuNhanVienDatabase(tblKM, tblModelKM, list);
                } else if ("Hết hạn khuyến mãi".equals(selectedItem)) { 
                  List<KhuyenMai> listHet = ds.getKhuyenMaiHet();
                    DocDuLieuNhanVienDatabase(tblKM, tblModelKM, listHet);
                }
                else if ("Còn hạn khuyến mãi".equals(selectedItem)) { 
                    List<KhuyenMai> listCon = ds.getKhuyenMaiCon();
                      DocDuLieuNhanVienDatabase(tblKM, tblModelKM, listCon);
                  }
            }
        });
	    	
	    JPanel p2;
	    b.add(p2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 20)), BorderLayout.CENTER);
	    JLabel lbDTKH = new  JLabel("Áp dụng cho toàn bộ khách hàng là thành viên của Bến An Long");
	    p2.add(lbDTKH);
	    lbDTKH.setForeground(Color.BLUE);
	    lbDTKH.setFont(new Font(getName(), Font.ITALIC, 18));
	    	
	    	
	    Box p3;
		pCenter.add(p3 = Box.createVerticalBox(), BorderLayout.CENTER);
	    String[] headers = {"Mã khuyến mãi", "Tên khuyến mãi", "Gía trị khuyến mãi", "Từ ngày", "Đến ngày"};
	    tblModelKM = new DefaultTableModel(headers, 0);
	    JScrollPane scroll = new JScrollPane();
	    scroll.setViewportView(tblKM = new JTable(tblModelKM));
	    tblKM.setRowHeight(100);
	    tblKM.setAutoCreateRowSorter(true);
	    tblKM.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
	    tblKM.setIntercellSpacing(new Dimension(0, 0));
        tblKM.setShowVerticalLines(false); 
        tblKM.setShowHorizontalLines(false); 
        tblKM.setBackground(Color.WHITE);
	    JTableHeader tblHeader = tblKM.getTableHeader();
	    tblHeader.setBackground(new Color(97, 79, 69));
	    tblHeader.setForeground(Color.WHITE);
	    tblHeader.setPreferredSize(new Dimension(100, 35));
	    tblHeader.setFont(new Font(getName(), Font.BOLD, 16));
	    p3.add(scroll);
	    	
	    JPanel pSouth = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 27));
	    pKhuyenMai.add(pSouth, BorderLayout.SOUTH);
	    JLabel lbTongKM = new JLabel("Tổng số khuyến mãi:");
	    txtTongKM = new JTextField(5);
	    lbTongKM.setFont(new Font(getName(), Font.BOLD, 16));
	    lbTongKM.setForeground(Color.WHITE);
	    txtTongKM.setEnabled(false);
	    txtTongKM.setDisabledTextColor(Color.BLACK);; 
	    txtTongKM.setBorder(null);
	    pSouth.setBackground(new Color(97, 79, 69));
	    pSouth.add(lbTongKM);
	    pSouth.add(txtTongKM);
	    KhuyenMai_DAO ds = new KhuyenMai_DAO();

		List<KhuyenMai> list = ds.getAllKM();
	    DocDuLieuNhanVienDatabase(tblKM,tblModelKM,list);
		return pKhuyenMai;
	}
	
	private JFrame xemKM() {
		JFrame frame = new JFrame();
		frame.setTitle("Thông tin Khuyến Mãi");
		frame.setVisible(true);
		frame.setSize(700, 650);
		frame.setLocationRelativeTo(null);
		
		JPanel pLeft, pRight;
		JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		frame.add(split);
		split.setDividerLocation(0.5);
		split.setDividerSize(2);
		split.add(pLeft = new JPanel());
		split.add(pRight = new JPanel(new BorderLayout()));
		JLabel lblTitle;
		pLeft.add(lblTitle = new JLabel("Thông tin tên các khuyến mãi"));
		lblTitle.setFont(new Font(getName(), Font.BOLD, 18));
		
		JPanel p = new JPanel();
		p.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		p.add(btnTT = new JButton("Thông tin"));
		btnTT.setForeground(Color.WHITE);
		btnTT.setBackground(new Color(97, 79, 69));
		p.add(btnDSKM = new JButton("Danh sách Khuyến Mãi"));
		pRight.add(p, BorderLayout.NORTH);
		btnTT.setFocusPainted(false);
		btnDSKM.setFocusPainted(false);
		btnTT.addActionListener(this);
		btnDSKM.addActionListener(this);
		
		cardlayout = new CardLayout();
		cardContainer = new JPanel(cardlayout);
		pRight.add(cardContainer);
		
		// Thông tin
		JPanel pThongTin = new JPanel();
		Box pSua = Box.createVerticalBox();
		pThongTin.add(pSua);
		Box p1, p2, p3, p4, p5, p6;
		
		pSua.add(Box.createVerticalStrut(20));
		pSua.add(p1 = Box.createHorizontalBox());
		JLabel lbMa;
		p1.add(lbMa = new JLabel("Mã khuyến mãi:   "));
		p1.add(txtMa = new TextField(20));
		txtMa.setEnabled(false);
		txtMa.setPreferredSize(new Dimension(50, 30));
		
		pSua.add(Box.createVerticalStrut(20));
		pSua.add(p2 = Box.createHorizontalBox());
		JLabel lbTenKM;
		p2.add(lbTenKM = new JLabel("Tên khuyến mãi:"));
		lbTenKM.setPreferredSize(lbMa.getPreferredSize());
		p2.add(txtTenKM = new TextField(20));
		txtTenKM.setPreferredSize(new Dimension(50, 30));
		JLabel lbNgayBD;
		pSua.add(Box.createVerticalStrut(20));
		pSua.add(p3 = Box.createHorizontalBox());
		p3.add(lbNgayBD = new JLabel("Từ ngày:"));
		lbNgayBD.setPreferredSize(lbMa.getPreferredSize());
		dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd-MM-yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        dateChooser.setDate(calendar.getTime()); 
        dateChooser.setPreferredSize(new Dimension(50, 30));
        p3.add(dateChooser);
        
		JLabel lbNgayKT;
		pSua.add(Box.createVerticalStrut(20));
		pSua.add(p4 = Box.createHorizontalBox());
		p4.add(lbNgayKT = new JLabel("Đến ngày:"));
		lbNgayKT.setPreferredSize(lbMa.getPreferredSize());
		dateChooser1 = new JDateChooser();
        dateChooser1.setDateFormatString("dd-MM-yyyy");
        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.DATE, -1);
        dateChooser1.setDate(calendar1.getTime()); 
        dateChooser1.setPreferredSize(new Dimension(50, 30));
        p4.add(dateChooser1);
		
        pSua.add(Box.createVerticalStrut(20));
		pSua.add(p5 = Box.createHorizontalBox());
		JLabel lblGiaTriKM;
		p5.add(lblGiaTriKM = new JLabel("Gía trị KM:"));
		lblGiaTriKM.setPreferredSize(lbMa.getPreferredSize());
		p5.add(txtGiaTriKM = new JTextField(50));
		txtGiaTriKM.setPreferredSize(new Dimension(50, 30));
		
		pSua.add(Box.createVerticalStrut(50));
		pSua.add(p6 = Box.createHorizontalBox());
		p6.add(btnSua = new JButton("Cập nhật"));
		btnSua.setPreferredSize(new Dimension(50, 50));
		btnSua.setFocusPainted(false);
		btnSua.addActionListener(this);
		
		int row = tblKM.getSelectedRow();
		if (row != -1) {
			txtMa.setText(tblKM.getValueAt(row, 0).toString());
			txtTenKM.setText(tblKM.getValueAt(row, 1).toString());
			txtGiaTriKM.setText(tblKM.getValueAt(row, 2).toString());
			String tuNgay = tblKM.getValueAt(row, 3).toString();
			String denNgay =tblKM.getValueAt(row, 4).toString();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

			try {
				// Đặt ngày vào JDateChooser
				dateChooser.setDate(dateFormat.parse(tuNgay));
				dateChooser1.setDate(dateFormat.parse(denNgay));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
        // Danh sách khuyến mãi
		JPanel pDSKM = new JPanel();
		pDSKM.setLayout(new BorderLayout());
		JPanel panel = new JPanel();
		pDSKM.add(panel, BorderLayout.NORTH);
		panel.add(txtTimDSKM = new JTextField(15));
		panel.add(btnTimDSKM = new JButton("Tìm"));
		panel.add(btnThemKM = new JButton("Thêm khuyến mãi"));
		panel.add(btnXoaKM = new JButton("Xóa khuyến mãi"));
		btnThemKM.setFocusPainted(false);
		btnXoaKM.setFocusPainted(false);
		btnTimDSKM.setFocusPainted(false);
		btnThemKM.addActionListener(this);
		btnXoaKM.addActionListener(this);
		btnTimDSKM.addActionListener(this);
		panel.setBorder(BorderFactory.createEmptyBorder(20,0,20,0));
		
		String[] agrs = {"Mã khuyến mãi", "Tên khuyến mãi","Giá trị khuyến mãi ", "Ngày bắt đầu", "Ngày kết thúc"};
		tblModel = new DefaultTableModel(agrs, 0);
        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(tbl = new JTable(tblModel));
        tbl.setRowHeight(50);
        tbl.setAutoCreateRowSorter(true);
        tbl.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        JTableHeader header = tbl.getTableHeader();
        header.setFont(new Font(getName(), Font.BOLD, 12));
        header.setBackground(Color.LIGHT_GRAY);
        pDSKM.add(scroll, BorderLayout.CENTER);
        
		
		cardContainer.add(pThongTin, "Thông tin");
		cardContainer.add(pDSKM, "Danh sách khuyến mãi");
		KhuyenMai_DAO ds = new KhuyenMai_DAO();

		List<KhuyenMai> list = ds.getAllKM();
		DocDuLieuNhanVienDatabase(tbl,tblModel,list);
		
		return frame;
	}
	
	
	private void DocDuLieuNhanVienDatabase(JTable tb, DefaultTableModel model, List<KhuyenMai> list) {
		// TODO Auto-generated method stub
		model.setRowCount(0);
		if (list.size() > 0) {
			for (KhuyenMai s : list) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				String ApDungString = s.getNgayADKM().format(formatter);
				String KetThucString = s.getNgayKTKM().format(formatter);
				String[] rowData = { s.getMaKM(), s.getTenKM(), String.valueOf(s.getGiaTriKM()), ApDungString,
						KetThucString };
				model.addRow(rowData);
			}
			KhuyenMai_DAO dsKM = new KhuyenMai_DAO();
			txtTongKM.setText(Integer.toString(dsKM.getAllKM().size()));
		}
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if(source.equals(btnChiTiet))
			xemKM();
		else if(source.equals(btnTT)) {
			btnDSKM.setBackground(UIManager.getColor("Button.background"));
			btnDSKM.setForeground(Color.black);
			btnTT.setBackground(new Color(97, 79, 69));
			btnTT.setForeground(Color.WHITE);
			cardlayout.show(cardContainer, "Thông tin");
		}else if(source.equals(btnDSKM)) {
			btnDSKM.setBackground(new Color(97, 79, 69));
			btnDSKM.setForeground(Color.WHITE);
			btnTT.setBackground(UIManager.getColor("Button.background"));
			btnTT.setForeground(Color.black);
			cardlayout.show(cardContainer, "Danh sách khuyến mãi");
		}else if(source.equals(btnThemKM)) {
			themKM();
		}else if(source.equals(btnXoaKM)) {
			xoaKhuyenMaiDS();
		}else if (source.equals(btnThemLuu)) {
			ThemLuuKMMoi();
			
		}else if (source.equals(btnXoa_XacNhan)) {
			XoaKM();
			
		}else if (source.equals(btnSua)) {
			suaKM();
			
		}else if (source.equals(btnTimKM)) {
			actionTim(txtTimKM,tblKM);
			
		}else if (source.equals(btnTimDSKM)) {
			actionTim(txtTimDSKM,tbl);
			
		}
		
	}

	private void suaKM() {
		// TODO Auto-generated method stub
	//txtMa txtTenKM dateChooser dateChooser1 txtGiaTriKM
	String maKH = txtMa.getText().trim();
	String tenKMmoi = txtTenKM.getText().trim();
	String giaTri = txtGiaTriKM.getText().trim();
	if (!(giaTri.matches("-?\\d+(\\.\\d+)?"))||giaTri.isEmpty()) {
		JOptionPane.showMessageDialog(this, "ERROR : Giá trị khuyến mãi phải là số  ");
		txtTienKM.requestFocus();
		return;

	}
	if(tenKMmoi.isEmpty()) {
		JOptionPane.showMessageDialog(this, "ERROR : Tên khuyến mãi không rỗng ");
		txtTenKM.requestFocus();
		return;
	}
	Date ngayAD = dateChooser.getDate();
	LocalDate localDateAD = ngayAD.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	Date ngayKT = dateChooser1.getDate();
	LocalDate localDateKT = ngayKT.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	
	Double giaTriSo = Double.parseDouble(giaTri);
	
	KhuyenMai km = KM_dao.getKM(maKH);
		KhuyenMai khuyenmai = new KhuyenMai(maKH, tenKMmoi, localDateAD, localDateKT, giaTriSo, km.getKhachHangKM());
		try {
			KM_dao.updateKM(khuyenmai);
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(this, "Cập nhật khuyến mãi lỗi ");
		}
	
	JOptionPane.showMessageDialog(this, "Cập nhật Khuyến mãi thành công ");
	KhuyenMai_DAO ds = new KhuyenMai_DAO();

	List<KhuyenMai> list = ds.getAllKM();
	DocDuLieuNhanVienDatabase(tbl, tblModel,list);
	DocDuLieuNhanVienDatabase(tblKM, tblModelKM,list);
	}

	//---- xóa khuyến mãi DSKM
	private void xoaKhuyenMaiDS() {
		// TODO Auto-generated method stub
		int row = tbl.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(this, "ERROR : Chọn khuyến mãi cần xóa ");
			return;
		}
		jfXoaKMDS = new JFrame();
		jfXoaKMDS.setVisible(true);
		jfXoaKMDS.setTitle("Bến An Long - Xóa khuyến mãi");
		jfXoaKMDS.setSize(800, 500);
		jfXoaKMDS.setLocationRelativeTo(null);

		Box pformXoaKM = Box.createVerticalBox();
		jfXoaKMDS.add(pformXoaKM);
		
		JPanel pLbXoa = new JPanel();
		pformXoaKM.add(pLbXoa);
		pLbXoa.setBackground(new Color(97, 79, 69));
		JLabel pLXoaNV;
		pLbXoa.add(pLXoaNV = new JLabel("XÓA KHUYẾN MÃI"));
		pLXoaNV.setFont(new Font(getName(), Font.BOLD, 27));
		pLXoaNV.setForeground(Color.WHITE);
		pLXoaNV.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

		
		pformXoaKM.add(Box.createVerticalStrut(20));
		JPanel pRow1 = new JPanel();
		pformXoaKM.add(pRow1);
		JLabel lbRow1 = new JLabel("Mã Khuyến mãi : ");
		pRow1.add(lbRow1);
		pRow1.add(txtFormXoaMa = new JTextField(15));
		lbRow1.setFont(new Font(getName(), Font.BOLD, 15));
		txtFormXoaMa.setFont(new Font(getName(), Font.PLAIN, 15));
		txtFormXoaMa.setEditable(false);
		txtFormXoaMa.setText(tblKM.getValueAt(row, 0).toString());
		
		pRow1.add(Box.createHorizontalStrut(10));
		JLabel lbRow11 = new JLabel("Ngày áp dụng: ");
		pRow1.add(lbRow11);
		JTextField txtFormXoangayAD;
		pRow1.add(txtFormXoangayAD = new JTextField(15));
		lbRow11.setFont(lbRow1.getFont());
		txtFormXoangayAD.setFont(txtFormXoaMa.getFont());
		txtFormXoangayAD.setEditable(false);
		txtFormXoangayAD.setText(tblKM.getValueAt(row, 3).toString());

		pformXoaKM.add(Box.createVerticalStrut(20));
		JPanel pRow2 = new JPanel();
		pformXoaKM.add(pRow2);
		JLabel lbRow2 = new JLabel("Tên khuyến mãi: ");
		pRow2.add(lbRow2);
		JTextField txtFormXoaTen;
		pRow2.add(txtFormXoaTen = new JTextField(15));
		lbRow2.setFont(lbRow1.getFont());
		txtFormXoaTen.setFont(txtFormXoaMa.getFont());
		txtFormXoaTen.setEditable(false);
		txtFormXoaTen.setText(tblKM.getValueAt(row, 1).toString());
		
		pRow2.add(Box.createHorizontalStrut(10));
		JLabel lbRow21 = new JLabel("Ngày kết thúc: ");
		pRow2.add(lbRow21);
		JTextField txtFormXoangayKT;
		pRow2.add(txtFormXoangayKT = new JTextField(15));
		lbRow21.setFont(lbRow1.getFont());
		txtFormXoangayKT.setFont(txtFormXoaMa.getFont());
		txtFormXoangayKT.setEditable(false);
		txtFormXoangayKT.setText(tblKM.getValueAt(row, 4).toString());
		
		pformXoaKM.add(Box.createVerticalStrut(20));
		JPanel pRow3 = new JPanel();
		pformXoaKM.add(pRow3);
		JLabel lbRow3 = new JLabel("Giá trị: ");
		pRow3.add(lbRow3);
		JTextField txtFormXoaGiaTri;
		pRow3.add(txtFormXoaGiaTri = new JTextField(15));
		lbRow3.setFont(lbRow1.getFont());
		txtFormXoaGiaTri.setFont(txtFormXoaMa.getFont());
		txtFormXoaGiaTri.setEditable(false);
		txtFormXoaGiaTri.setText(tblKM.getValueAt(row, 1).toString());
		
		pRow3.add(Box.createHorizontalStrut(10));
		JLabel lbRow31 = new JLabel(" ");
		pRow3.add(lbRow31);
		Dimension size1 = lbRow2.getPreferredSize();
		Dimension size2 = txtFormXoaGiaTri.getPreferredSize();
		lbRow31.setPreferredSize(new Dimension(size1.width + size2.width, lbRow31.getHeight()));
		System.out.println(size1.width + size2.width);
		lbRow1.setPreferredSize(lbRow2.getPreferredSize());
		lbRow11.setPreferredSize(lbRow2.getPreferredSize());
		lbRow21.setPreferredSize(lbRow2.getPreferredSize());
		lbRow3.setPreferredSize(lbRow2.getPreferredSize());
		
		pformXoaKM.add(Box.createVerticalStrut(20));
		JPanel pRow4 = new JPanel();
		pformXoaKM.add(pRow4);
		pRow4.add(btnXoa_XacNhan = new JButton("Xác nhận "));
		btnXoa_XacNhan.setPreferredSize(new Dimension(100, 50));
		btnXoa_XacNhan.setFocusPainted(false);
		btnXoa_XacNhan.addActionListener(this);
	}
	private void XoaKM() {
		// TODO Auto-generated method stub
		String maKM = txtFormXoaMa.getText();
		if(KM_dao.deleteKM(maKM)) {
			KhuyenMai_DAO ds = new KhuyenMai_DAO();
			List<KhuyenMai> list = ds.getAllKM();
			DocDuLieuNhanVienDatabase(tbl, tblModel,list);
			DocDuLieuNhanVienDatabase(tblKM, tblModelKM,list);
			JOptionPane.showMessageDialog(this, "Xóa thành công");
			jfXoaKMDS.dispose();
			return;
		}
		JOptionPane.showMessageDialog(this, "Không thể xóa");
	}
	
	//---------Thêm khuyến mãi
	private Frame themKM() {
		JFrame frame = new JFrame();
		frame.setTitle("Thêm khuyến mãi");
		frame.setVisible(true);
		frame.setSize(350, 350);
		frame.setLocationRelativeTo(null);
		
		Box box = Box.createVerticalBox();
		Box box1, box2, box3, box4;
		frame.add(box, BorderLayout.CENTER);
		
		box.add(Box.createVerticalStrut(30));
		box.add(box1 = Box.createHorizontalBox());
		JLabel lbl1;
		box1.add(lbl1 = new JLabel("Tên khuyến mãi:"));
		box1.add(txtThemTenKM = new JTextField());
		
		box.add(Box.createVerticalStrut(30));
		box.add(box2 = Box.createHorizontalBox());
		JLabel lbl2;
		box2.add(lbl2 = new JLabel("Ngày bắt đầu:"));
		lbl2.setPreferredSize(lbl1.getPreferredSize());
		dateChooser2 = new JDateChooser();
        dateChooser2.setDateFormatString("dd-MM-yyyy");
        Calendar calendar2 = Calendar.getInstance();
        calendar2.add(Calendar.DATE, -1);
        dateChooser2.setDate(calendar2.getTime()); 
        box2.add(dateChooser2);
		
        box.add(Box.createVerticalStrut(30));
		box.add(box3 = Box.createHorizontalBox());
		JLabel lbl3;
		box3.add(lbl3 = new JLabel("Ngày kết thúc:"));
		lbl3.setPreferredSize(lbl1.getPreferredSize());
		dateChooser3 = new JDateChooser();
        dateChooser3.setDateFormatString("dd-MM-yyyy");
        Calendar calendar3 = Calendar.getInstance();
        calendar3.add(Calendar.DATE, -1);
        dateChooser3.setDate(calendar3.getTime()); 
        box3.add(dateChooser3);
        
        box.add(Box.createVerticalStrut(30));
		box.add(box4 = Box.createHorizontalBox());
		JLabel lbl4;
		box4.add(lbl4 = new JLabel("Gía trị KM:"));
		lbl4.setPreferredSize(lbl1.getPreferredSize());
		box4.add(txtTienKM = new JTextField(10));
		box.add(Box.createVerticalStrut(50));
		
		JPanel pSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		frame.add(pSouth, BorderLayout.SOUTH);
		pSouth.add(btnThemLuu = new JButton("Lưu"));
		btnThemLuu.setFocusPainted(false);
		btnThemLuu.addActionListener(this);
		return frame;
	}
	
	private void ThemLuuKMMoi() {
		// TODO Auto-generated method stub
		String tenKMmoi = txtThemTenKM.getText().trim();
		String giaTri = txtTienKM.getText().trim();
		if (!(giaTri.matches("-?\\d+(\\.\\d+)?"))||giaTri.isEmpty()) {
			JOptionPane.showMessageDialog(this, "ERROR : Giá trị khuyến mãi phải là số  ");
			txtTienKM.requestFocus();
			return;

		}
		if(tenKMmoi.isEmpty()) {
			JOptionPane.showMessageDialog(this, "ERROR : Tên khuyến mãi không rỗng ");
			txtThemTenKM.requestFocus();
			return;
		}
		Date ngayAD = dateChooser2.getDate();
		LocalDate localDateAD = ngayAD.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		Date ngayKT = dateChooser3.getDate();
		LocalDate localDateKT = ngayKT.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		Double giaTriSo = Double.parseDouble(giaTri);
		
		KhachHang_DAO khachHang_dao = new KhachHang_DAO();
		List<KhachHang> listKH = khachHang_dao.getAllKH();
		for (KhachHang kh : listKH) {
			String maKH = KM_dao.createMaKM();
			
			KhuyenMai khuyenmai = new KhuyenMai(maKH, tenKMmoi, localDateAD, localDateKT, giaTriSo, kh);
			try {
				KM_dao.insertKM(khuyenmai);
			} catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(this, "Thêm khuyến mãi lỗi ");
			}
			
		}
		JOptionPane.showMessageDialog(this, "Thêm Khuyến mãi thành công ");
		KhuyenMai_DAO ds = new KhuyenMai_DAO();

		List<KhuyenMai> list = ds.getAllKM();
		DocDuLieuNhanVienDatabase(tbl, tblModel,list);
		DocDuLieuNhanVienDatabase(tblKM, tblModelKM,list);
	}
	
	//----- tìm khuyến mãi 
	public void actionTim(JTextField txtTimNV, JTable tbNV ) {
		
		String timMa = txtTimNV.getText();
		if (!(timMa.isEmpty())) {
			for (int i = 0; i < tbNV.getRowCount(); i++) {
				if (tbNV.getValueAt(i, 0).toString().equals(timMa)) {
					tbNV.setRowSelectionInterval(i, i);
					tbNV.scrollRectToVisible(tbNV.getCellRect(i, 0, true));
					txtTimNV.setText("");
					return;
				}
			}
			JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên: " + timMa);
			return;
		}

		JOptionPane.showMessageDialog(this, "Nhập mã nhân viên cần tìm ");
	}
	
}
