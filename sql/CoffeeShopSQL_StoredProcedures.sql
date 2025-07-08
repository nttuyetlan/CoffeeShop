USE [QuanLyQuanCoffee]
GO

CREATE PROCEDURE sp_CalculateRevenueByMonth
AS
BEGIN
    SELECT 
        MONTH(ngayLapHD) AS Thang, 
        SUM(thanhToanHD) AS DoanhThu
    FROM HoaDon
    GROUP BY MONTH(ngayLapHD)
    ORDER BY Thang;
END
GO

CREATE PROCEDURE sp_CalculateRevenueByWeekday
AS
BEGIN 
	SELECT DATEPART(WEEKDAY, ngayLapHD) AS ThuTrongTuan,
			SUM(thanhToanHD) AS DoanhThu
	FROM HoaDon
	GROUP BY DATEPART(WEEKDAY, ngayLapHD)
	ORDER BY ThuTrongTuan;
END
GO

CREATE PROCEDURE sp_CalculateRevenueByYear
AS
BEGIN 
	SELECT YEAR(ngayLapHD) AS Nam,
			SUM(thanhToanHD) AS DoanhThu
	FROM HoaDon
	GROUP BY YEAR(ngayLapHD)
	ORDER BY Nam;
END
GO

CREATE PROCEDURE Top5SanPhamTheoThangNam
	@Year INT = NULL,
	@Month INT = NULL
AS
BEGIN
	SELECT TOP 5 p.tenSP AS tenSP, SUM(cthd.soLuongSP) AS tongSoLuong
	FROM SanPham p JOIN ChiTietHD cthd ON p.maSP = cthd.maSP JOIN HoaDon hd ON cthd.maHD = hd.maHD
	WHERE (@Year IS NULL OR YEAR(hd.ngayLapHD) = @Year) AND (@Month IS NULL OR MONTH(hd.ngayLapHD) = @Month)
	GROUP BY p.tenSP
	ORDER BY tongSoLuong DESC;
END
GO

CREATE PROCEDURE getHoaDonToNgay
	@startDate DATE,
	@endDate DATE
AS
BEGIN
	SELECT maHD FROM HoaDon
	WHERE ngayLapHD >= @startDate AND ngayLapHD <= @endDate
	ORDER BY ngayLapHD;
END
GO

CREATE PROCEDURE getSPSortByPrice
AS
BEGIN
	SELECT maSP, donGiaSP
	FROM SanPham
	ORDER BY donGiaSP DESC;
END
GO

CREATE PROCEDURE getLoaiSP
@loaiSP VARCHAR(40)
AS
BEGIN
    IF @loaiSP = 'Tất cả'
    BEGIN
        -- Nếu chọn "Tất cả", trả về tất cả sản phẩm
        SELECT maSP AS maSP
        FROM SanPham;
    END
    ELSE
    BEGIN 
        -- Nếu không phải "Tất cả", trả về sản phẩm theo loại
        SELECT maSP AS maSP
        FROM SanPham
        WHERE loaiSP = @loaiSP;
    END
END
GO

CREATE PROCEDURE sp_GetKhuyenMaiCon(@currentDate DATE)
AS
BEGIN
    SELECT * FROM KhuyenMai WHERE ngayKTKM > @currentDate;
END
GO

CREATE PROCEDURE sp_GetKhuyenMaiHet(@currentDate DATE)
AS
BEGIN
    SELECT * FROM KhuyenMai WHERE ngayKTKM < @currentDate;
END