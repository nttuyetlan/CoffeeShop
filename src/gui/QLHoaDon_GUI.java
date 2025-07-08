package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.toedter.calendar.JDateChooser;

public class QLHoaDon_GUI extends JFrame{
	private JButton btnTimHD_HoaDon;
	private JButton btnFileHD;
	private DefaultTableModel tableModelQLHD;
	private JTable tableQLHD;
	private JTextField txtTongHD;
	private JButton btnXoaHD;
	public QLHoaDon_GUI() {
		// TODO Auto-generated constructor stub
	}
	public JPanel createHoaDon() {
		// TODO Auto-generated method stub
		JPanel pQLHoaDon = new JPanel();
		pQLHoaDon.setLayout(new BorderLayout());
		JPanel pNorth = new JPanel();
		pQLHoaDon.add(pNorth, BorderLayout.NORTH);
		JLabel lbTitle;
		pNorth.add(lbTitle = new JLabel("Quản Lý Hóa Đơn"));
		lbTitle.setFont(new Font(getName(), Font.BOLD, 30));
		pNorth.setBackground(new Color(97, 79, 69));
		lbTitle.setForeground(Color.white);
		lbTitle.setPreferredSize(new Dimension(300, 75));
		JPanel pCenter = new JPanel();
		pQLHoaDon.add(pCenter, BorderLayout.CENTER);
		pCenter.setLayout(new BorderLayout());
		JPanel panelTim = new JPanel();
		pCenter.add(panelTim, BorderLayout.NORTH);
		panelTim.setLayout(new BoxLayout(panelTim, BoxLayout.X_AXIS));
		JLabel lbNgayBD;
		panelTim.add(lbNgayBD = new JLabel("Từ ngày:"));
		lbNgayBD.setFont(new Font(getName(), Font.PLAIN, 14));
		// Tạo JDateChooser
        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd-MM-yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        dateChooser.setDate(calendar.getTime()); //        java.util.Date selectedDate = dateChooser.getDate();	LẤY GIÁ TRỊ TRONG Ô NGÀY
        dateChooser.setPreferredSize(new Dimension(400, 30));
        panelTim.add(dateChooser);
        JLabel lbNgayKT;
		panelTim.add(lbNgayKT = new JLabel("Đến ngày: "));
		lbNgayKT.setFont(new Font(getName(), Font.PLAIN, 14));
		// Tạo JDateChooser
        JDateChooser dateChooser2 = new JDateChooser();
        dateChooser2.setDateFormatString("dd-MM-yyyy");
        dateChooser2.setDate(new java.util.Date());
        dateChooser2.setPreferredSize(new Dimension(400, 30));
        panelTim.add(dateChooser2);
        panelTim.add(btnTimHD_HoaDon = new JButton("Lọc"));
        btnTimHD_HoaDon.setFocusPainted(false);
        panelTim.add(Box.createRigidArea(new Dimension(50, 0)));
        panelTim.add(btnXoaHD = new JButton("Xóa"));
        btnXoaHD.setFocusPainted(false);
        panelTim.add(Box.createRigidArea(new Dimension(50, 0)));
        panelTim.add(btnFileHD = new JButton("Xuất File Excel"));
        btnFileHD.setFocusPainted(false);
        lbNgayBD.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
        lbNgayKT.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
        dateChooser.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 50));
        dateChooser2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 50));
        panelTim.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        Box b = Box.createHorizontalBox();
        String[] headers = "Mã;Ngày lập;Nhân viên;Khách hàng;Tổng tiền".split(";");
        tableModelQLHD = new DefaultTableModel(headers, 0);
        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(tableQLHD = new JTable(tableModelQLHD));
        tableQLHD.setRowHeight(50);
        tableQLHD.setAutoCreateRowSorter(true);
        tableQLHD.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        JTableHeader header = tableQLHD.getTableHeader();
        header.setFont(new Font(getName(), Font.BOLD, 16));
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
		pSouth.add(txtTongHD = new JTextField(5));
		lbTongHD.setFont(new Font(getName(), Font.HANGING_BASELINE, 16));
		lbTongHD.setForeground(Color.WHITE);
		EmptyBorder padding = new EmptyBorder(21, 0, 21, 0);
        lbTongHD.setBorder(padding);
		txtTongHD.setEditable(false);
		txtTongHD.setEnabled(false);
		txtTongHD.setBorder(null);
		pQLHoaDon.add(pSouth, BorderLayout.SOUTH);
		return pQLHoaDon;
	}
	
}
