package entity;

import java.time.LocalDate;
import java.util.Objects;

public class SanPham {
	private String maSP;
	private String tenSP;
	private double donGiaSP;
	private LocalDate ngayCapNhatSP;
	private String loaiSP;
	private byte[] hinhSP;
	
	public SanPham(String maSP, String tenSP, double donGiaSP, LocalDate ngayCapNhatSP, String loaiSP, byte[] hinhSP) {
		super();
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.donGiaSP = donGiaSP;
		this.ngayCapNhatSP = ngayCapNhatSP;
		this.loaiSP = loaiSP;
		this.hinhSP = hinhSP;
	}

	public SanPham(String maSP) {
		super();
		this.maSP = maSP;
	}

	public String getMaSP() {
		return maSP;
	}

	public void setMaSP(String maSP) {
		this.maSP = maSP;
	}

	public String getTenSP() {
		return tenSP;
	}

	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}

	public double getDonGiaSP() {
		return donGiaSP;
	}

	public void setDonGiaSP(double donGiaSP) {
		this.donGiaSP = donGiaSP;
	}

	public LocalDate getNgayCapNhatSP() {
		return ngayCapNhatSP;
	}

	public void setNgayCapNhatSP(LocalDate ngayCapNhatSP) {
		this.ngayCapNhatSP = ngayCapNhatSP;
	}

	public String getLoaiSP() {
		return loaiSP;
	}

	public void setLoaiSP(String loaiSP) {
		this.loaiSP = loaiSP;
	}

	public byte[] getHinhSP() {
		return hinhSP;
	}

	public void setHinhSP(byte[] hinhSP) {
		this.hinhSP = hinhSP;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maSP);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SanPham other = (SanPham) obj;
		return Objects.equals(maSP, other.maSP);
	}
}
