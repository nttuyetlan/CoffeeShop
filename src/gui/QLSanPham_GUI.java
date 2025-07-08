package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.SanPham_DAO;
import entity.SanPham;

public class QLSanPham_GUI extends JFrame implements ActionListener{
	private JTextField txtTimSP_SanPham;
	private JButton btnTimSP_SanPham;
	private JButton btnThemSP_SanPham;
	private JButton btnSapXepSP_SanPham;
	private JComboBox<String> JCBoxloai_SanPham;
	private JTextField txtTenSP;
	private JTextField txtLoaiSP;
	private JTextField txtGiaSP;
	private JButton btnAnhSP;
	private JLabel imageLabel;
	private JButton btnThemLuu;
	private JFrame frame;
	private JButton btnSua;
	private JButton btnXoa;
	private JComboBox<String> cbb;
	private JTextField txtTenSP_Sua;
	private JComboBox<String> cbb_Sua;
	private JTextField txtGiaSP_Sua;
	private ImageIcon resizedIcon;
	private SanPham_DAO sanpham_dao;
	private String path;
	private JPanel pCaPhe;
	private JButton btnCapNhat;
	private JDateChooser dateChooser_Sua;
	private String maSP_change;
	private JButton btnHuy_Sua;
	private JDateChooser dateChooser_Them;
	private JButton btnAnhSP_Sua;
	private ImageIcon resizedIcon_Sua;
	private String path_Sua;
	private boolean isNewImageSelected = false;
	private JFrame frm;


	public QLSanPham_GUI() {
		// TODO Auto-generated constructor stub
		sanpham_dao = new SanPham_DAO();
	}
	
	public JPanel createSanPham() {
		// TODO Auto-generated method stub
		JPanel pSanPham = new JPanel(new BorderLayout());
		JPanel pNorth = new JPanel();
		pSanPham.add(pNorth, BorderLayout.NORTH);
		pNorth.setBackground(new Color(97, 79, 69));
		JLabel lbQLSP;
		pNorth.add(lbQLSP = new JLabel("Quản Lý Sản Phẩm"));
		lbQLSP.setFont(new Font(getName(), Font.BOLD, 30));
		lbQLSP.setForeground(Color.WHITE);
		pNorth.setBorder(BorderFactory.createEmptyBorder(18, 0, 18, 0));
		JPanel pCenter = new JPanel(new BorderLayout());
		pSanPham.add(pCenter, BorderLayout.CENTER);
		Box b = Box.createHorizontalBox();
		pCenter.add(b, BorderLayout.NORTH);
		JPanel b1 = new JPanel(new FlowLayout(new FlowLayout().LEFT, 30, 30));
		b.add(b1);
		b1.add(txtTimSP_SanPham = new JTextField(20));
		b1.add(btnTimSP_SanPham = new JButton("Tìm sản phẩm"));
		txtTimSP_SanPham.setPreferredSize(new Dimension(50,25));
		btnTimSP_SanPham.setFocusPainted(false);
		btnTimSP_SanPham.addActionListener(this);
    	b1.add(btnThemSP_SanPham = new JButton("Thêm sản phẩm"));
    	btnThemSP_SanPham.setFocusPainted(false);
    	btnThemSP_SanPham.addActionListener(this);
    	JPanel b2 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 30, 30));
		b.add(b2);
    	b2.add(btnSapXepSP_SanPham = new JButton("Sắp xếp theo giá"));
    	btnSapXepSP_SanPham.setFocusPainted(false);
    	btnSapXepSP_SanPham.addActionListener(this);
    	String[] loaiSP = {"Tất cả", "Cà phê", "Bánh ngọt"};
    	JCBoxloai_SanPham = new JComboBox<String>(loaiSP);
    	JCBoxloai_SanPham.setPreferredSize(new Dimension(150, 30));
    	b2.add(JCBoxloai_SanPham);
    	JCBoxloai_SanPham.addItemListener(new ItemListener() {
    	    @Override
    	    public void itemStateChanged(ItemEvent e) {
    	        if (e.getStateChange() == ItemEvent.SELECTED) {
    	            String selected = (String) JCBoxloai_SanPham.getSelectedItem();
    	            if (selected.equals("Cà phê"))
    	            	selected = "CaPhe";
    	            if (selected.equals("Bánh ngọt"))
    	            	selected = "Banh";
    	            System.out.println("Selected Item: " + selected);
    	            locSP(selected);
    	        }
    	    }
    	});

    	

    	
		JPanel pCCenter = new JPanel();
		pCCenter.add(createProducts());
		JScrollPane scroll = new JScrollPane(pCCenter);
		pCenter.add(scroll, BorderLayout.CENTER);

		return pSanPham;
	}
	
	private JFrame themSP_SanPham() {
		frame = new JFrame();
		frame.setTitle("Thêm sản phẩm");
		frame.setVisible(true);
		frame.setSize(600, 600);
		frame.setLocationRelativeTo(null);
		
		JLabel lblTitle;
		JPanel panelTitle = new JPanel();
		panelTitle.add(lblTitle = new JLabel("Thêm sản phẩm"));
		frame.add(panelTitle, BorderLayout.NORTH);
		lblTitle.setFont(new Font(getName(), Font.BOLD, 18));
		
		JPanel panelCenter = new JPanel();
		frame.add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.Y_AXIS));
		panelCenter.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
		Box b1, b2, b3, b4, b5;
		panelCenter.add(Box.createVerticalStrut(10));
		panelCenter.add(b1 = Box.createHorizontalBox());
		JLabel lblTenSP;
		b1.add(lblTenSP = new JLabel("Tên:"));
		b1.add(txtTenSP = new JTextField(10));
		lblTenSP.setPreferredSize(new Dimension(50,50));
		lblTenSP.setPreferredSize(new Dimension(100, 10));
		
		panelCenter.add(Box.createVerticalStrut(10));
		panelCenter.add(b2 = Box.createHorizontalBox());
		JLabel lblLoaiSP;
		b2.add(lblLoaiSP = new JLabel("Loại:"));
		String [] header = {"Cà phê", "Bánh ngọt"};
		b2.add(cbb = new JComboBox<String>(header));
		cbb.setPreferredSize(new Dimension(10,30));
		lblLoaiSP.setPreferredSize(lblTenSP.getPreferredSize());
		
		panelCenter.add(Box.createVerticalStrut(10));
		panelCenter.add(b3 = Box.createHorizontalBox());
		JLabel lblNgayCN;
		b3.add(lblNgayCN = new JLabel("Ngày cập nhật:"));
		lblNgayCN.setPreferredSize(lblTenSP.getPreferredSize());
		dateChooser_Them = new JDateChooser();
        dateChooser_Them.setDateFormatString("dd-MM-yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 0);
        dateChooser_Them.setDate(calendar.getTime()); 
        dateChooser_Them.setPreferredSize(new Dimension(50, 30));
        b3.add(dateChooser_Them);
        
        panelCenter.add(Box.createVerticalStrut(10));
		panelCenter.add(b4 = Box.createHorizontalBox());
		JLabel lblGiaSP;
		b4.add(lblGiaSP = new JLabel("Gía tiền:"));
		lblGiaSP.setPreferredSize(lblTenSP.getPreferredSize());
		b4.add(txtGiaSP = new JTextField(10));
		
		panelCenter.add(Box.createVerticalStrut(10));
		panelCenter.add(b5 = Box.createHorizontalBox());
		b5.add(btnAnhSP = new JButton("Chọn ảnh"));
		btnAnhSP.setFocusPainted(false);
		b5.add(Box.createHorizontalStrut(20));
		imageLabel = new JLabel();
		imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		imageLabel.setPreferredSize(new Dimension(100,300));
		b5.add(imageLabel);
		btnAnhSP.addActionListener(this);
		
		JPanel panelSouth = new JPanel(new FlowLayout());
		frame.add(panelSouth, BorderLayout.SOUTH);
		panelSouth.add(btnThemLuu = new JButton("Lưu"));
		btnThemLuu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Đóng JFrame nhỏ khi nhấn nút Hủy
                frame.dispose();
            }
        });

		btnThemLuu.setFocusPainted(false);
		btnThemLuu.addActionListener(this);

		
		return frame;
	}
	
	 // Phương thức chuyển Date thành LocalDate
    private static LocalDate convertToLocalDate(Date date) {
        return date.toInstant()
                   .atZone(ZoneId.systemDefault())
                   .toLocalDate();
    }
    

 // Phương thức đọc ảnh từ đường dẫn và chuyển thành byte[]
 	public byte[] readImgToBytes(String path) {
 		byte[] imageBytes = null;
 		try {
 			imageBytes = Files.readAllBytes(Paths.get(path));
 		} catch (IOException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
 		return imageBytes;
 	}
	
	private void giaTriThem() {
		String maSP = sanpham_dao.createMaSP();
		String tenSP = txtTenSP.getText().trim();
		String loaiSP = null;
		if (cbb.getSelectedItem().toString().equals("Cà phê"))
			loaiSP = "CaPhe";
		else
			loaiSP = "Banh";
		Date localDate = dateChooser_Them.getDate();
		LocalDate ngayCN = convertToLocalDate(localDate);
		double giaTien = Double.parseDouble(txtGiaSP.getText().trim());
		byte[] hinhSP = readImgToBytes(path);
		SanPham sp = new SanPham(maSP, tenSP, giaTien, ngayCN, loaiSP, hinhSP);
		if(sanpham_dao.insertSP(sp)) {
			JOptionPane.showMessageDialog(this, "Thêm thành công!");
			loadProducts();
			// Reset lại các trường nhập liệu sau khi thêm thành công
	        txtTenSP.setText("");  
	        cbb.setSelectedIndex(0);
	        txtGiaSP.setText("");  
	        dateChooser_Them.setDate(null); 
	        path = ""; 
	        imageLabel.setIcon(null); 
		}else
			JOptionPane.showMessageDialog(this, "Thêm không thành công!");
	}
	
	// Phương thức chuyển byte[] thành ImageIcon và scale theo width, height
    public static ImageIcon convertBytesToImageIcon(byte[] imageBytes, int width, int height) {
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
            BufferedImage bufferedImage = ImageIO.read(bis);

            if (bufferedImage != null) {
                // Scale ảnh về kích thước width x height
                Image scaledImage = bufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                return new ImageIcon(scaledImage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
	
	private JPanel createProducts() {
		// TODO Auto-generated method stub
		CardLayout cardMenuCaPheBanh = new CardLayout();
		JPanel cardPanelMenuCaPheBanh = new JPanel(cardMenuCaPheBanh);

		pCaPhe = new JPanel(new GridLayout(0, 5, 12, 12));
		
		
		cardPanelMenuCaPheBanh.add(pCaPhe,"CaPhe");
		
		
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			Statement statement = con.createStatement();
			String query = "SELECT tenSP, hinhSP FROM SanPham";
			ResultSet rs = statement.executeQuery(query);
			while(rs.next()) {
				String tenSP = rs.getString("tenSP");
				byte[] imageSP = rs.getBytes("hinhSP");
				
				ImageIcon hinhSP = convertBytesToImageIcon(imageSP, 200, 200);
				
				JPanel panelCP = crProductPanel(tenSP, hinhSP);
				pCaPhe.add(panelCP);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return cardPanelMenuCaPheBanh;
	}
	
	private JPanel crProductPanel(String tenSP, ImageIcon hinhSP) {
	    JPanel productPanel = new JPanel();
	    productPanel.setLayout(new BorderLayout());
	    productPanel.setBorder(BorderFactory.createLineBorder(new Color(97, 79, 69), 1));
	    productPanel.setBackground(Color.WHITE);
	    
	    //---------Button Sửa và Xóa sản phẩm---------
	    btnSua = new JButton("Sửa");
	    btnSua.setFocusPainted(false);
	    btnSua.setFont(new Font(getName(), Font.BOLD, 14));
	    btnSua.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				suaSP();
				maSP_change = loadGiaTriSPSua(tenSP);
			}
		});
	    btnXoa = new JButton("Xóa");
	    btnXoa.setFocusPainted(false);
	    btnXoa.setFont(new Font(getName(), Font.BOLD, 14));
	    btnXoa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				xoaSanPham(tenSP);
			}
		});
	    
	    
	    //---------Label Tên sản phẩm---------
	    JLabel nameLabel = new JLabel(tenSP);
	    nameLabel.setFont(new Font(getName(), Font.BOLD, 18));
	    nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    
	    //---------Label Hình sản phẩm---------
	    JLabel imageLabel = new JLabel("");
	    if (hinhSP != null) {
//	        // Chuyển đổi BufferedImage thành ImageIcon và scale kích thước ảnh
//	        Image scaledImage = hinhSP.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
//	        ImageIcon icon = new ImageIcon(scaledImage);
	        imageLabel.setIcon(hinhSP);
	    } else {
	        imageLabel.setText("Hình ảnh không khả dụng");
	    }
	    imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    
	    JPanel pNorth = new JPanel();
	    pNorth.add(nameLabel);
	    
	    JPanel pSouth = new JPanel();
	    pSouth.add(btnSua);
	    pSouth.add(btnXoa);
	    
	    productPanel.add(pNorth, BorderLayout.NORTH);
	    productPanel.add(imageLabel, BorderLayout.CENTER);
	    productPanel.add(pSouth, BorderLayout.SOUTH);
	    
	    
	    return productPanel;
	}
	
	private void loadProducts() {
		pCaPhe.removeAll();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			Statement statement = con.createStatement();
			String query = "SELECT tenSP, hinhSP FROM SanPham";
			ResultSet rs = statement.executeQuery(query);
			while(rs.next()) {
				String tenSP = rs.getString("tenSP");
				byte[] imageSP = rs.getBytes("hinhSP");
				
				ImageIcon anhSP = convertBytesToImageIcon(imageSP, 200, 200);
				
				JPanel panelCP = crProductPanel(tenSP, anhSP);
	            pCaPhe.add(panelCP);
			}
	        pCaPhe.revalidate();
	        pCaPhe.repaint();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private JFrame suaSP() {
		frm = new JFrame();
		frm.setTitle("Sửa sản phẩm");
		frm.setVisible(true);
		frm.setSize(600, 600);
		frm.setLocationRelativeTo(null);
		
		
		JLabel lblTitle;
		JPanel panelTitle = new JPanel();
		panelTitle.add(lblTitle = new JLabel("Sửa sản phẩm"));
		frm.add(panelTitle, BorderLayout.NORTH);
		lblTitle.setFont(new Font(getName(), Font.BOLD, 18));
		
		JPanel panelCenter = new JPanel();
		frm.add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.Y_AXIS));
		Box b1, b2, b3, b4, b5;
		panelCenter.add(Box.createVerticalStrut(10));
		panelCenter.add(b1 = Box.createHorizontalBox());
		JLabel lblTenSP;
		b1.add(lblTenSP = new JLabel("Tên:"));
		b1.add(txtTenSP_Sua = new JTextField(10));
		lblTenSP.setPreferredSize(new Dimension(50,50));
		lblTenSP.setPreferredSize(new Dimension(100, 10));
		
		panelCenter.add(Box.createVerticalStrut(10));
		panelCenter.add(b2 = Box.createHorizontalBox());
		JLabel lblLoaiSP;
		b2.add(lblLoaiSP = new JLabel("Loại:"));
		String [] loai = {"Cà phê", "Bánh ngọt"};
		b2.add(cbb_Sua = new JComboBox<String>(loai));
		lblLoaiSP.setPreferredSize(lblTenSP.getPreferredSize());
		cbb_Sua.setPreferredSize(new Dimension(10,30));
		
		panelCenter.add(Box.createVerticalStrut(10));
		panelCenter.add(b3 = Box.createHorizontalBox());
		JLabel lblNgayCN;
		b3.add(lblNgayCN = new JLabel("Ngày cập nhật:"));
		lblNgayCN.setPreferredSize(lblTenSP.getPreferredSize());
		dateChooser_Sua = new JDateChooser();
        dateChooser_Sua.setDateFormatString("dd-MM-yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        dateChooser_Sua.setDate(calendar.getTime()); 
        dateChooser_Sua.setPreferredSize(new Dimension(50, 30));
        b3.add(dateChooser_Sua);
        
        panelCenter.add(Box.createVerticalStrut(10));
		panelCenter.add(b4 = Box.createHorizontalBox());
		JLabel lblGiaSP;
		b4.add(lblGiaSP = new JLabel("Giá tiền:"));
		lblGiaSP.setPreferredSize(lblTenSP.getPreferredSize());
		b4.add(txtGiaSP_Sua = new JTextField(10));
		
		panelCenter.add(Box.createVerticalStrut(10));
		panelCenter.add(b5 = Box.createHorizontalBox());
		b5.add(btnAnhSP_Sua = new JButton("Chọn ảnh"));
		btnAnhSP_Sua.setFocusPainted(false);
		/// Cần chỉnh ảnh lấy từ database lên
		imageLabel = new JLabel();
		imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		imageLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		b5.add(imageLabel);
		btnAnhSP_Sua.addActionListener(this);
		
		JPanel panelSouth = new JPanel(new FlowLayout());
		frm.add(panelSouth, BorderLayout.SOUTH);
		panelSouth.add(btnCapNhat = new JButton("Cập nhật"));
		btnCapNhat.setFocusPainted(false);
		btnCapNhat.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				capNhatGiaTriSPSua();
				frm.dispose();
			}
		});
		panelSouth.add(btnHuy_Sua = new JButton("Hủy"));
		btnHuy_Sua.setFocusPainted(false);
		btnHuy_Sua.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frm.dispose();
			}
		});
		return frm;
	}
	
	private String loadGiaTriSPSua(String tenSP) {
	    SanPham sp = sanpham_dao.getSPfromTenSP(tenSP);
		txtTenSP_Sua.setText(sp.getTenSP());
		cbb_Sua.setSelectedItem(sp.getLoaiSP());
		LocalDate ngayCN = sp.getNgayCapNhatSP();
		Date date = Date.from(ngayCN.atStartOfDay(ZoneId.systemDefault()).toInstant());
		dateChooser_Sua.setDate(date);
		txtGiaSP_Sua.setText(Double.toString(sp.getDonGiaSP()));
		ImageIcon anhSP = convertBytesToImageIcon(sp.getHinhSP(), 280, 280);
		imageLabel.setIcon(anhSP);	
		return sp.getMaSP();
	}
	
	
	private void capNhatGiaTriSPSua() {
		String maSanPham = maSP_change;
		SanPham sp = sanpham_dao.getSP(maSanPham);
		
		String tenSanPham = txtTenSP_Sua.getText();
		String loaisanpham_Sua = null;
		if (cbb_Sua.getSelectedItem().toString().equals("Cà phê"))
			loaisanpham_Sua = "CaPhe";
		else
			loaisanpham_Sua = "Banh";
		Date localDate = dateChooser_Sua.getDate();
		LocalDate ngayCNSP_Sua = convertToLocalDate(localDate);
		double giaTienSP_Sua = Double.parseDouble(txtGiaSP_Sua.getText().trim());
		byte[] hinhSP_Sua = sp.getHinhSP();
		if (isNewImageSelected == true) {
			hinhSP_Sua = readImgToBytes(path_Sua);
		}
		isNewImageSelected = false;
		SanPham sp_Sua = new SanPham(maSanPham, tenSanPham, giaTienSP_Sua, ngayCNSP_Sua, loaisanpham_Sua, hinhSP_Sua);
		if(sanpham_dao.updateSP(sp_Sua)) {
			JOptionPane.showMessageDialog(this, "Sửa sản phẩm thành công!");
			loadProducts();
		}else {
			JOptionPane.showMessageDialog(this, "Sửa sản phầm không thành công");
		}
	}
	
	private void xoaSanPham(String tenSP) {
		int i = JOptionPane.showConfirmDialog(this, "Chắc chắn muốn xóa!", "Chú ý", JOptionPane.YES_NO_OPTION);
		if (i == JOptionPane.YES_OPTION) {
			SanPham sp = sanpham_dao.getSPfromTenSP(tenSP);
			sanpham_dao.deleteSP(sp.getMaSP());
			loadProducts();
		}
	}
	
	private void timSanPham() {
		String tenSP = txtTimSP_SanPham.getText().trim();
		SanPham sp = sanpham_dao.getSPfromTenSP(tenSP);
		
		if(tenSP.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Chưa nhập tên sản phẩm!");
			return;
		}
		else if (sp != null) {
			ImageIcon hinhSP = convertBytesToImageIcon(sp.getHinhSP(), 200, 200);
			pCaPhe.removeAll();
			JPanel panel =  crProductPanel(sp.getTenSP(), hinhSP);
			pCaPhe.add(panel);
				
			// Cập nhật lại giao diện
		     pCaPhe.revalidate();
		     pCaPhe.repaint();
		}else {
			JOptionPane.showMessageDialog(this, "Không có sản phẩm!");
		}
		
	}
	
	private void locSP(String loaiSanPham) { 
	    System.out.println("Loại sản phẩm được chọn: " + loaiSanPham);
	    try {
	        Connection con = ConnectDB.getInstance().getConnection();
	        CallableStatement stmt = con.prepareCall("{CALL getLoaiSP(?)}");
	        stmt.setString(1, loaiSanPham);
	        ResultSet rs = stmt.executeQuery();
	        pCaPhe.removeAll();
	        while (rs.next()) {
	            String maSP = rs.getString("maSP");
	            SanPham sp = sanpham_dao.getSP(maSP);   
	            ImageIcon hinhSP = convertBytesToImageIcon(sp.getHinhSP(), 200, 200);
	            JPanel panel = crProductPanel(sp.getTenSP(), hinhSP);
	            pCaPhe.add(panel);  
	        }
	        pCaPhe.revalidate(); 
	        pCaPhe.repaint(); 

	    } catch (Exception e) {
	        e.printStackTrace(); 
	    }
	}
	
	private void sortSP() {
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			CallableStatement stmt = con.prepareCall("{CALL getSPSortByPrice}");
			ResultSet rs = stmt.executeQuery();
			pCaPhe.removeAll();
			while(rs.next()) {
				String maSP = rs.getString("maSP");
				double giaSP = rs.getDouble("donGiaSP");	
	            SanPham sp = sanpham_dao.getSP(maSP);   
	            ImageIcon hinhSP = convertBytesToImageIcon(sp.getHinhSP(), 200, 200);
	            JPanel panel = crProductPanel(sp.getTenSP(), hinhSP);
	            pCaPhe.add(panel);  
			}
			pCaPhe.revalidate(); 
	        pCaPhe.repaint(); 
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(btnThemSP_SanPham)) {
			themSP_SanPham();

		}else if(o.equals(btnAnhSP)) {
			// Tạo JFileChooser để chọn tệp
			JFileChooser fileChooser = new JFileChooser();
			 // Lấy thư mục làm việc hiện tại (thư mục chứa dự án)
			String currentDirectory = System.getProperty("Coffee_Java_Project");
			File projectDirectory = new File(currentDirectory, "img");
			fileChooser.setCurrentDirectory(projectDirectory);
            // Mở hộp thoại chọn tệp
            int returnValue = fileChooser.showOpenDialog(frame);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                // Lấy tệp ảnh được chọn
                File selectedFile = fileChooser.getSelectedFile();
                System.out.println("Ảnh đã chọn: " + selectedFile.getAbsolutePath());
                path = selectedFile.getAbsolutePath();
                // Tạo ImageIcon từ tệp ảnh đã chọn
                ImageIcon imageIcon = new ImageIcon(selectedFile.getAbsolutePath());
                
             // Kích thước mục tiêu cố định là 
                int targetWidth = 300;  // Chiều rộng mục tiêu
                int targetHeight = 300; // Chiều cao mục tiêu
                
                // Thay đổi kích thước ảnh
                Image img = imageIcon.getImage();
                Image resizedImage = img.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
                
                // Tạo ImageIcon mới từ ảnh đã thay đổi kích thước
                resizedIcon = new ImageIcon(resizedImage);
                
                // Đặt ảnh lên JLabel
                imageLabel.setIcon(resizedIcon);
            }
		}else if(o.equals(btnAnhSP_Sua)){
			// Tạo JFileChooser để chọn tệp
			JFileChooser fileChooser = new JFileChooser();
			// Lấy thư mục làm việc hiện tại (thư mục chứa dự án)
			String currentDirectory = System.getProperty("Coffee_Java_Project");
			File projectDirectory = new File(currentDirectory, "img");
			fileChooser.setCurrentDirectory(projectDirectory);
			// Mở hộp thoại chọn tệp
			int returnValue = fileChooser.showOpenDialog(frm);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				// Lấy tệp ảnh được chọn
				File selectedFile = fileChooser.getSelectedFile();
				System.out.println("Ảnh đã chọn: " + selectedFile.getAbsolutePath());
				path_Sua = selectedFile.getAbsolutePath();
				// Tạo ImageIcon từ tệp ảnh đã chọn
				ImageIcon imageIcon = new ImageIcon(selectedFile.getAbsolutePath());
			                
				// Kích thước mục tiêu cố định là 
				int targetWidth = 300;  // Chiều rộng mục tiêu
				int targetHeight = 300; // Chiều cao mục tiêu
			                
				// Thay đổi kích thước ảnh
				Image img = imageIcon.getImage();
				Image resizedImage = img.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
				
				// Tạo ImageIcon mới từ ảnh đã thay đổi kích thước
				resizedIcon_Sua = new ImageIcon(resizedImage);
			                
				// Đặt ảnh lên JLabel
				imageLabel.setIcon(resizedIcon_Sua);
				isNewImageSelected = true;
			}
			
		}else if(o.equals(btnThemLuu)) {
			giaTriThem();
		}else if(o.equals(btnTimSP_SanPham)) {
			timSanPham();
		}else if(o.equals(btnSapXepSP_SanPham)) {
			sortSP();
		}
	}
}
