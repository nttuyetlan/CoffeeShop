package test;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import dao.SanPham_DAO;
import entity.SanPham;

public class ThemSP_TEST {
	public ThemSP_TEST() {
		// TODO Auto-generated constructor stub
	}
	
	public void addSP() {
		SanPham_DAO sp_dao = new SanPham_DAO();
		sp_dao.insertSP(new SanPham(sp_dao.createMaSP(), "Americano Đá", 30.5, LocalDate.now(), "CaPhe", readImgToBytes("img//AmericanoDa.jpg")));
		sp_dao.insertSP(new SanPham(sp_dao.createMaSP(), "Bạc Xĩu", 20.5, LocalDate.now(), "CaPhe", readImgToBytes("img//BacXiu.jpg")));
		sp_dao.insertSP(new SanPham(sp_dao.createMaSP(), "Espresso Đá", 10.5, LocalDate.now(), "CaPhe", readImgToBytes("img//EspressoDa.jpg")));
		sp_dao.insertSP(new SanPham(sp_dao.createMaSP(), "Americano Nóng", 20.5, LocalDate.now(), "CaPhe", readImgToBytes("img//AmericanoNong.jpg")));
	
		sp_dao.insertSP(new SanPham(sp_dao.createMaSP(), "Bánh Croissant Bơ", 100.5, LocalDate.now(), "Banh", readImgToBytes("img//BanhCroissantBo.jpg")));
		sp_dao.insertSP(new SanPham(sp_dao.createMaSP(), "Bánh Cuốn Nho", 80.4, LocalDate.now(), "Banh", readImgToBytes("img//BanhCuonNho.jpg")));
		sp_dao.insertSP(new SanPham(sp_dao.createMaSP(), "Bánh Ngọt Cà Phê", 120.5, LocalDate.now(), "Banh", readImgToBytes("img/BanhNgotCaPhe.jpg")));
		sp_dao.insertSP(new SanPham(sp_dao.createMaSP(), "Bánh Nhân Thịt", 220.5, LocalDate.now(), "Banh", readImgToBytes("img//BanhNhanThit.jpg")));
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
}
