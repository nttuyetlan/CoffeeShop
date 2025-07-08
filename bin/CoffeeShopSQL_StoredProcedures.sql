CREATE PROCEDURE [dbo].[sp_CalculateRevenueByMonth]
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

CREATE PROCEDURE [dbo].[sp_CalculateRevenueByWeekday]
AS
BEGIN 
	SELECT DATEPART(WEEKDAY, ngayLapHD) AS ThuTrongTuan,
			SUM(thanhToanHD) AS DoanhThu
	FROM HoaDon
	GROUP BY DATEPART(WEEKDAY, ngayLapHD)
	ORDER BY ThuTrongTuan;
END
GO

CREATE PROCEDURE [dbo].[sp_CalculateRevenueByYear]
AS
BEGIN 
	SELECT YEAR(ngayLapHD) AS Nam,
			SUM(thanhToanHD) AS DoanhThu
	FROM HoaDon
	GROUP BY YEAR(ngayLapHD)
	ORDER BY Nam;
END
GO

CREATE PROCEDURE [dbo].[Top5SanPhamTheoThangNam]
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

CREATE PROCEDURE [dbo].[getHoaDonToNgay]
	@startDate DATE,
	@endDate DATE
AS
BEGIN
	SELECT maHD FROM HoaDon
	WHERE ngayLapHD BETWEEN @startDate AND @endDate
	ORDER BY ngayLapHD;
END