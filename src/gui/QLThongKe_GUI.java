package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;

import javax.swing.BorderFactory;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import connectDB.ConnectDB;

public class QLThongKe_GUI extends JFrame implements ActionListener{
	private JComboBox<String> cbThongKe;
	private CardLayout cardlayout;
	private JPanel cardContainer;
	private JButton btnDoanhThu;
	private JButton btnSanPham;
	private CardLayout cardlayout1;
	private JPanel cardContainer1;
	private JComboBox selectTypeComboBox;
	private JComboBox selectValueComboBox;
	
	public QLThongKe_GUI() {
		// TODO Auto-generated constructor stub
		ConnectDB.getInstance().connect();
	}
	
	public JPanel createThongKe() {
		JPanel pThongKe = new JPanel(new BorderLayout());
		
		//North
		JPanel pLbThongKe = new JPanel();
		pThongKe.add(pLbThongKe, BorderLayout.NORTH);
		pLbThongKe.setBackground(new Color(97, 79, 69));
		pLbThongKe.setBorder(BorderFactory.createEmptyBorder(18, 0, 18, 0));
		JLabel lbThongKe;
		pLbThongKe.add(lbThongKe = new JLabel("Biểu Đồ Thống Kê"));
		lbThongKe.setForeground(Color.WHITE);
		lbThongKe.setFont(new Font(getName(), Font.BOLD, 30));
		
		//Center
		JPanel pCenter = new JPanel(new BorderLayout());
		pCenter.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		pThongKe.add(pCenter, BorderLayout.CENTER);
		JPanel pChon = new JPanel();
		pChon.add(btnDoanhThu = new JButton("Doanh Thu"));
		pChon.add(btnSanPham = new JButton("Sản Phẩm"));
		pCenter.add(pChon, BorderLayout.NORTH);
		btnDoanhThu.setFont(new Font(getName(), Font.BOLD, 24));
		btnSanPham.setFont(new Font(getName(), Font.BOLD, 24));
		btnDoanhThu.setFocusPainted(false);
		btnSanPham.setFocusPainted(false);
		btnDoanhThu.addActionListener(this);
		btnSanPham.addActionListener(this);
		
		cardlayout = new CardLayout();
		cardContainer = new JPanel(cardlayout);
		pCenter.add(cardContainer);
		
		JPanel pDoanhThu = new JPanel();
		pDoanhThu.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
		
		cardlayout1 = new CardLayout();
		cardContainer1 = new JPanel(cardlayout1);
		pDoanhThu.add(cardContainer1);
		
		
		DefaultCategoryDataset datasetWeek = getDataFromDatabaseWeek();
		ChartPanel chartPanelWeek = createChart(datasetWeek, "Doanh thu theo thứ trong tuần (VND)", "Thứ");
		JPanel pWeek = new JPanel();
		pWeek.add(chartPanelWeek);
        cardContainer1.add(pWeek, "Doanh Thu Thứ");
		
		DefaultCategoryDataset datasetMonth = getDataFromDataBaseMonth();
		ChartPanel chartPanelMonth = createChart(datasetMonth, "Doanh thu theo tháng (VND)", "Tháng");
		JPanel pMonth = new JPanel();
		pMonth.add(chartPanelMonth);
        cardContainer1.add(pMonth, "Doanh Thu Tháng");
        
        DefaultCategoryDataset datasetYear = getDataFromDataBaseYear();
		ChartPanel chartPanelYear = createChart(datasetYear, "Doanh thu theo năm (VND)", "Năm");
		JPanel pYear= new JPanel();
		pYear.add(chartPanelYear);
        cardContainer1.add(pYear, "Doanh Thu Năm");
        
        JPanel pSouth_DoanhThu = new JPanel();
        pDoanhThu.add(pSouth_DoanhThu);
        pSouth_DoanhThu.setBorder(BorderFactory.createEmptyBorder(60, 0, 10, 0));
        JLabel lbTKTheo;
        pSouth_DoanhThu.add(lbTKTheo = new JLabel("Thống kê theo: "));
		lbTKTheo.setFont(new Font(getName(), Font.BOLD, 18));
		String[] listTK = {"Ngày trong tuần", "Tháng", "Năm"};
		pSouth_DoanhThu.add(cbThongKe = new JComboBox<String>(listTK));
        cbThongKe.setFont(new Font(getName(), Font.BOLD, 14));
        cbThongKe.setPreferredSize(new Dimension(300, 40));
        cbThongKe.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selected = (String) cbThongKe.getSelectedItem();

                    switch (selected) {
                        case "Ngày trong tuần":
                            cardlayout1.show(cardContainer1, "Doanh Thu Thứ");
                            break;
                        case "Tháng":
                            cardlayout1.show(cardContainer1, "Doanh Thu Tháng");
                            break;
                        case "Năm":
                            cardlayout1.show(cardContainer1, "Doanh Thu Năm");
                            break;
                        default:
                            break;
                    }
                }
            }
        });
        
        
        JPanel PSanPham = new JPanel(new BorderLayout()); 
        PSanPham.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        JPanel p1 = new JPanel();
        PSanPham.add(p1, BorderLayout.CENTER);
        
        JPanel p2 = new JPanel();
        String[] options = {"Tháng", "Năm"};
        selectTypeComboBox = new JComboBox<>(options);
        selectValueComboBox = new JComboBox<>();
        selectTypeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lấy lựa chọn của selectTypeComboBox
                String selectedOption = (String) selectTypeComboBox.getSelectedItem();

                // Làm trống selectValueComboBox trước khi thêm dữ liệu mới
                selectValueComboBox.removeAllItems();

                if (selectedOption.equals("Tháng")) {
                    // Nếu chọn Tháng, thêm 12 tháng vào selectValueComboBox
                    for (int i = 1; i <= 12; i++) {
                        selectValueComboBox.addItem("Tháng " + i);
                    }
                } else if (selectedOption.equals("Năm")) {
                    // Nếu chọn Năm, thêm các năm từ 2020 đến 2024 vào selectValueComboBox
                    for (int i = 2024; i >= 2020; i--) {
                        selectValueComboBox.addItem("Năm " + i);
                    }
                }
            }
        });

        // Mặc định thêm các tháng vào selectValueComboBox khi chọn "Tháng"
        selectTypeComboBox.setSelectedIndex(0);
        selectValueComboBox.addItem("Tháng 1");  
        selectValueComboBox.setSelectedIndex(0);
        selectTypeComboBox.setPreferredSize(new Dimension(150, 30));
        p2.add(selectTypeComboBox);

        // Thêm selectValueComboBox vào cửa sổ
        selectValueComboBox.setPreferredSize(new Dimension(150, 30));
        p2.add(selectValueComboBox);
        
        selectValueComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) selectValueComboBox.getSelectedItem();
                if (selected != null) {
                    p1.removeAll();  
                    if (selectTypeComboBox.getSelectedItem().equals("Tháng")) {
                        
                        int month = Integer.parseInt(selected.split(" ")[1]);
                        
                        DefaultCategoryDataset datasetThang = getTop5CoffeeBanChayNhat(month, null);
                        ChartPanel chartPanelThang = createChartTop5SPToMonth(datasetThang, "Top 5 sản phẩm bán chạy nhất", "Tháng", month);
                        p1.add(chartPanelThang);
                        p1.revalidate();  
                        p1.repaint();     
                    } else if (selectTypeComboBox.getSelectedItem().equals("Năm")) { 
                        int year = Integer.parseInt(selected.split(" ")[1]);
                        DefaultCategoryDataset datasetNam = getTop5CoffeeBanChayNhat(null, year);
                        ChartPanel chartPanelNam = createChartTop5SPToMonth(datasetNam, "Top 5 sản phẩm bán chạy nhất", "Năm", year);
                        p1.add(chartPanelNam);
                        p1.revalidate();  
                        p1.repaint();     
                    }
                }
            }
        });
        
        selectTypeComboBox.setSelectedIndex(0); 
        selectValueComboBox.setSelectedIndex(0); 

        
        PSanPham.add(p2, BorderLayout.SOUTH);
        
        
        cardContainer.add(pDoanhThu, "Doanh Thu");
        cardContainer.add(PSanPham, "Sản phẩm");
        
		return pThongKe;
	}

	private DefaultCategoryDataset getDataFromDataBaseMonth() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			CallableStatement stmt = con.prepareCall("{CALL sp_CalculateRevenueByMonth}");
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int month = rs.getInt("Thang");
				double revenue = rs.getDouble("DoanhThu");
				
				dataset.addValue(revenue, "Doanh Thu", String.valueOf(month));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dataset;
	}
	
	private DefaultCategoryDataset getDataFromDataBaseYear() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			CallableStatement stmt = con.prepareCall("{CALL sp_CalculateRevenueByYear}");
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int year = rs.getInt("Nam");
				double revenue = rs.getDouble("DoanhThu");
				
				dataset.addValue(revenue, "Doanh Thu", String.valueOf(year));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dataset;
	}
	
	private DefaultCategoryDataset getDataFromDatabaseWeek() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			CallableStatement stmt = con.prepareCall("{CALL sp_CalculateRevenueByWeekday}");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				int weekday = rs.getInt("ThuTrongTuan");
				double revenue = rs.getDouble("DoanhThu");
				
				String weekdayStr = switch (weekday) {
					case 1 -> "Chủ nhật";
					case 2 -> "Thứ hai";
					case 3 -> "Thứ ba";
					case 4 -> "Thứ tư";
					case 5 -> "Thứ năm";
					case 6 -> "Thứ sáu";
					case 7 -> "Thứ bảy";
					default -> "Không xác định";
				};
				dataset.addValue(revenue, "Doanh Thu", weekdayStr);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dataset;
	}
	
	private ChartPanel createChart(DefaultCategoryDataset dataset, String title1, String title2) {
		DefaultCategoryDataset datasetmonth = getDataFromDataBaseMonth();
        JFreeChart barChart = ChartFactory.createBarChart(
            title1,                 
            title2,                        
            "Doanh Thu (VND)",                  
            dataset,                           
            PlotOrientation.VERTICAL,          
            true,                             
            true,                             
            false                            
        );
        CategoryPlot plot = barChart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, Color.BLUE);    
        renderer.setSeriesPaint(1, Color.RED);   
        renderer.setSeriesPaint(2, Color.GREEN);   
        plot.setBackgroundPaint(Color.LIGHT_GRAY);
        barChart.setBackgroundPaint(Color.WHITE);
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(900, 500));
        return chartPanel;
	}
	
	private DefaultCategoryDataset getTop5CoffeeBanChayNhat(Integer thang, Integer nam) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "{CALL Top5SanPhamTheoThangNam(?, ?)}";
			CallableStatement stmt = con.prepareCall(sql);
			if(nam!=null) {
				stmt.setInt(1, nam);
			}else {
				stmt.setNull(1, Types.INTEGER);
			}
			
			if(thang!=null) {
				stmt.setInt(2, thang);
			}else {
				stmt.setNull(2, Types.INTEGER);
			}
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String tenSanPham = rs.getString("tenSP");
				int tongSoLuong = rs.getInt("tongSoLuong");
				
				dataset.addValue(tongSoLuong, "Tổng số lượng", tenSanPham);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dataset;
	}
	
	private ChartPanel createChartTop5SPToMonth(DefaultCategoryDataset dataset, String title1, String title2, int thang) {
		DefaultCategoryDataset datasetmonth = getTop5CoffeeBanChayNhat(thang, null);
        JFreeChart barChart = ChartFactory.createBarChart(
            title1 + " tháng " + thang,                 
            title2,                        
            "Tổng số lượng bán",                  
            dataset,                           
            PlotOrientation.VERTICAL,          
            true,                             
            true,                             
            false                            
        );
        CategoryPlot plot = barChart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, Color.BLUE);    
        renderer.setSeriesPaint(1, Color.RED);   
        renderer.setSeriesPaint(2, Color.GREEN);   
        plot.setBackgroundPaint(Color.LIGHT_GRAY);
        barChart.setBackgroundPaint(Color.WHITE);
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(900, 500));
        return chartPanel;
	}
	
	private ChartPanel createChartTop5SPToYear(DefaultCategoryDataset dataset, String title1, String title2, int nam) {
		DefaultCategoryDataset datasetmonth = getTop5CoffeeBanChayNhat(null, nam);
        JFreeChart barChart = ChartFactory.createBarChart(
            title1 + " năm " + nam,                 
            title2,                        
            "Tổng số lượng bán",                  
            dataset,                           
            PlotOrientation.VERTICAL,          
            true,                             
            true,                             
            false                            
        );
        CategoryPlot plot = barChart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, Color.BLUE);    
        renderer.setSeriesPaint(1, Color.RED);   
        renderer.setSeriesPaint(2, Color.GREEN);   
        plot.setBackgroundPaint(Color.LIGHT_GRAY);
        barChart.setBackgroundPaint(Color.WHITE);
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(900, 500));
        return chartPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(btnDoanhThu)) {
			btnSanPham.setBackground(UIManager.getColor("Button.background"));
			btnSanPham.setForeground(Color.black);
			btnDoanhThu.setBackground(new Color(97, 79, 69));
			btnDoanhThu.setForeground(Color.WHITE);
			cardlayout.show(cardContainer, "Doanh Thu");
		}else if(o.equals(btnSanPham)) {
			btnSanPham.setBackground(new Color(97, 79, 69));
			btnSanPham.setForeground(Color.WHITE);
			btnDoanhThu.setBackground(UIManager.getColor("Button.background"));
			btnDoanhThu.setForeground(Color.black);
			cardlayout.show(cardContainer, "Sản phẩm");
		}
	}
}
