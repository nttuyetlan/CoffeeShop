package entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class HoaDon {
	private String maHD;
	private LocalDateTime ngayLapHD;
	private double tongTienHD;
	private double thanhToanHD;
	private double tienKhachDua;
	private double tienTraLai;
	private NhanVien nvHD;
	private KhachHang khHD;
	private Ban banHD;
	private KhuyenMai kmHD;
	
	public HoaDon(String maHD, LocalDateTime ngayLapHD, double tongTienHD, double thanhToanHD, double tienKhachDua,
			double tienTraLai, NhanVien nvHD, KhachHang khHD, Ban banHD, KhuyenMai kmHD) {
		super();
		this.maHD = maHD;
		this.ngayLapHD = ngayLapHD;
		this.tongTienHD = tongTienHD;
		this.thanhToanHD = thanhToanHD;
		this.tienKhachDua = tienKhachDua;
		this.tienTraLai = tienTraLai;
		this.nvHD = nvHD;
		this.khHD = khHD;
		this.banHD = banHD;
		this.kmHD = kmHD;
	}

	public HoaDon(String maHD) {
		super();
		this.maHD = maHD;
	}

	public String getMaHD() {
		return maHD;
	}

	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}

	public LocalDateTime getNgayLapHD() {
		return ngayLapHD;
	}

	public void setNgayLapHD(LocalDateTime ngayLapHD) {
		this.ngayLapHD = ngayLapHD;
	}

	public double getTongTienHD() {
		return tongTienHD;
	}

	public void setTongTienHD(double tongTienHD) {
		this.tongTienHD = tongTienHD;
	}

	public double getThanhToanHD() {
		return thanhToanHD;
	}

	public void setThanhToanHD(double thanhToanHD) {
		this.thanhToanHD = thanhToanHD;
	}

	public double getTienKhachDua() {
		return tienKhachDua;
	}

	public void setTienKhachDua(double tienKhachDua) {
		this.tienKhachDua = tienKhachDua;
	}

	public double getTienTraLai() {
		return tienTraLai;
	}

	public void setTienTraLai(double tienTraLai) {
		this.tienTraLai = tienTraLai;
	}

	public NhanVien getNvHD() {
		return nvHD;
	}

	public void setNvHD(NhanVien nvHD) {
		this.nvHD = nvHD;
	}

	public KhachHang getKhHD() {
		return khHD;
	}

	public void setKhHD(KhachHang khHD) {
		this.khHD = khHD;
	}

	public Ban getBanHD() {
		return banHD;
	}

	public void setBanHD(Ban banHD) {
		this.banHD = banHD;
	}

	public KhuyenMai getKmHD() {
		return kmHD;
	}

	public void setKmHD(KhuyenMai kmHD) {
		this.kmHD = kmHD;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maHD);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HoaDon other = (HoaDon) obj;
		return Objects.equals(maHD, other.maHD);
	}
}
