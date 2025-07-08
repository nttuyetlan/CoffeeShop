package control;

import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import entity.Ban;
import entity.ChiTietHD;
import entity.HoaDon;
import entity.NhanVien;

public class XuatHoaDonPDF {
    
    // Hàm để tải font Unicode từ file .ttf
    private static Font getUnicodeFont(float size, int style) {
        try {
            // Đường dẫn đến file font .ttf hỗ trợ Unicode
            BaseFont baseFont = BaseFont.createFont("fonts//Roboto-Medium.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            return new Font(baseFont, size, style);
        } catch (Exception e) {
            e.printStackTrace();
            return new Font(Font.FontFamily.TIMES_ROMAN, size, style);
        }
    }

    // Hàm xuất hóa đơn ra file PDF
    public void generateInvoice(String filePath, ArrayList<ChiTietHD> dsCtHD, HoaDon hoaDon) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            // Sử dụng font Unicode
            Font titleFont = getUnicodeFont(18, Font.BOLD);
            Font headerFont = getUnicodeFont(12, Font.BOLD);
            Font textFont = getUnicodeFont(12, Font.NORMAL);

            // Tiêu đề hóa đơn
            Paragraph diaChi = new Paragraph("12 Nguyễn Văn Bảo, Phường 4, Gò Vấp, Hồ Chí Minh", headerFont);
            Paragraph sdt = new Paragraph("0999999999", headerFont);
            Paragraph title = new Paragraph("HÓA ĐƠN THANH TOÁN", titleFont);
            diaChi.setAlignment(Element.ALIGN_CENTER);
            sdt.setAlignment(Element.ALIGN_CENTER);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(diaChi);
            document.add(sdt);
            document.add(title);
            document.add(new Paragraph(" ")); // Dòng trống

            // Thông tin khách hàng
            document.add(new Paragraph("Mã hóa đơn: " + hoaDon.getMaHD(), textFont));
            document.add(new Paragraph("Nhân viên: " + hoaDon.getNvHD().getTenNV(), textFont));
            document.add(new Paragraph("Ngày lập: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")), textFont));
            document.add(new Paragraph("Bàn: " + hoaDon.getBanHD().getMaBan(), textFont));
            document.add(new Paragraph(" ")); // Dòng trống

            // Tạo bảng
            PdfPTable table = new PdfPTable(5); // 4 cột
            table.setWidthPercentage(90);
            table.setWidths(new float[]{1, 3, 1, 2, 2});

            // Thêm tiêu đề cho bảng
            table.addCell(new PdfPCell(new Phrase("Mã", headerFont)));
            table.addCell(new PdfPCell(new Phrase("Tên", headerFont)));
            table.addCell(new PdfPCell(new Phrase("Số lượng", headerFont)));
            table.addCell(new PdfPCell(new Phrase("Đơn giá (VND)", headerFont)));
            table.addCell(new PdfPCell(new Phrase("Thành tiền (VND)", headerFont)));

            // Duyệt qua danh sách sản phẩm và thêm vào bảng
            for (ChiTietHD ctHD : dsCtHD) {
                table.addCell(new PdfPCell(new Phrase(ctHD.getSanPhamCTHD().getMaSP(), textFont)));
                table.addCell(new PdfPCell(new Phrase(ctHD.getSanPhamCTHD().getTenSP(), textFont)));
                table.addCell(new PdfPCell(new Phrase(ctHD.getSoLuongSP() + "", textFont)));
                table.addCell(new PdfPCell(new Phrase(ctHD.getSanPhamCTHD().getDonGiaSP() + "", textFont)));
                table.addCell(new PdfPCell(new Phrase(ctHD.getThanhTien() + "", textFont)));
            }

            // Thêm bảng vào tài liệu
            document.add(table);

            // Giảm giá
            document.add(new Paragraph(" "));
            Paragraph khuyenMai = new Paragraph("Giảm giá: " + hoaDon.getKmHD().getGiaTriKM(), textFont);
            khuyenMai.setAlignment(Element.ALIGN_RIGHT);
            document.add(khuyenMai);
            
            // Tổng tiền
            document.add(new Paragraph(" "));
            Paragraph total = new Paragraph("Tổng cộng: " + hoaDon.getThanhToanHD(), headerFont);
            total.setAlignment(Element.ALIGN_RIGHT);
            document.add(total);

            // Đóng tài liệu
            document.close();
            System.out.println("Hóa đơn đã được tạo thành công: " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
