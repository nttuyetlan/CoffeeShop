package entity;

public class ChiTietHD {
	private HoaDon hoaDonCTHD;
	private SanPham sanPhamCTHD;
	private int soLuongSP;
	private double thanhTien;
	
	public ChiTietHD(HoaDon hoaDonCTHD, SanPham sanPhamCTHD, int soLuongSP, double thanhTien) {
		super();
		this.hoaDonCTHD = hoaDonCTHD;
		this.sanPhamCTHD = sanPhamCTHD;
		this.soLuongSP = soLuongSP;
		this.thanhTien = thanhTien;
	}

	public HoaDon getHoaDonCTHD() {
		return hoaDonCTHD;
	}

	public void setHoaDonCTHD(HoaDon hoaDonCTHD) {
		this.hoaDonCTHD = hoaDonCTHD;
	}

	public SanPham getSanPhamCTHD() {
		return sanPhamCTHD;
	}

	public void setSanPhamCTHD(SanPham sanPhamCTHD) {
		this.sanPhamCTHD = sanPhamCTHD;
	}

	public int getSoLuongSP() {
		return soLuongSP;
	}

	public void setSoLuongSP(int soLuongSP) {
		this.soLuongSP = soLuongSP;
	}

	public double getThanhTien() {
		return thanhTien;
	}

	public void setThanhTien(double thanhTien) {
		this.thanhTien = thanhTien;
	}
}
