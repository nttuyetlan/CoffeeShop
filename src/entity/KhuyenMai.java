package entity;

import java.time.LocalDate;
import java.util.Objects;

public class KhuyenMai {
	private String maKM;
	private String tenKM;
	private LocalDate ngayADKM;
	private LocalDate ngayKTKM;
	private double giaTriKM;
	private KhachHang khachHangKM;
	
	public KhuyenMai(String maKM, String tenKM, LocalDate ngayADKM, LocalDate ngayKTKM, double giaTriKM,
			KhachHang khachHangKM) {
		super();
		this.maKM = maKM;
		this.tenKM = tenKM;
		this.ngayADKM = ngayADKM;
		this.ngayKTKM = ngayKTKM;
		this.giaTriKM = giaTriKM;
		this.khachHangKM = khachHangKM;
	}

	public KhuyenMai(String maKM) {
		super();
		this.maKM = maKM;
	}

	public String getMaKM() {
		return maKM;
	}

	public void setMaKM(String maKM) {
		this.maKM = maKM;
	}

	public String getTenKM() {
		return tenKM;
	}

	public void setTenKM(String tenKM) {
		this.tenKM = tenKM;
	}

	public LocalDate getNgayADKM() {
		return ngayADKM;
	}

	public void setNgayADKM(LocalDate ngayADKM) {
		this.ngayADKM = ngayADKM;
	}

	public LocalDate getNgayKTKM() {
		return ngayKTKM;
	}

	public void setNgayKTKM(LocalDate ngayKTKM) {
		this.ngayKTKM = ngayKTKM;
	}

	public double getGiaTriKM() {
		return giaTriKM;
	}

	public void setGiaTriKM(double giaTriKM) {
		this.giaTriKM = giaTriKM;
	}

	public KhachHang getKhachHangKM() {
		return khachHangKM;
	}

	public void setKhachHangKM(KhachHang khachHangKM) {
		this.khachHangKM = khachHangKM;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maKM);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KhuyenMai other = (KhuyenMai) obj;
		return Objects.equals(maKM, other.maKM);
	}
}
