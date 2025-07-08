package entity;

import java.time.LocalTime;
import java.util.Objects;

public class CaLamViec {
	private String maCaLV;
	private String tenCaLV;
	private LocalTime gioVaoLam;
	private LocalTime gioKetThuc;
	private double luongTheoGio;
	
	public CaLamViec(String maCaLV, String tenCaLV, LocalTime gioVaoLam, LocalTime gioKetThuc, double luongTheoGio) {
		super();
		this.maCaLV = maCaLV;
		this.tenCaLV = tenCaLV;
		this.gioVaoLam = gioVaoLam;
		this.gioKetThuc = gioKetThuc;
		this.luongTheoGio = luongTheoGio;
	}

	public CaLamViec(String maCaLV) {
		super();
		this.maCaLV = maCaLV;
	}

	public String getMaCaLV() {
		return maCaLV;
	}

	public void setMaCaLV(String maCaLV) {
		this.maCaLV = maCaLV;
	}

	public String getTenCaLV() {
		return tenCaLV;
	}

	public void setTenCaLV(String tenCaLV) {
		this.tenCaLV = tenCaLV;
	}

	public LocalTime getGioVaoLam() {
		return gioVaoLam;
	}

	public void setGioVaoLam(LocalTime gioVaoLam) {
		this.gioVaoLam = gioVaoLam;
	}

	public LocalTime getGioKetThuc() {
		return gioKetThuc;
	}

	public void setGioKetThuc(LocalTime gioKetThuc) {
		this.gioKetThuc = gioKetThuc;
	}

	public double getLuongTheoGio() {
		return luongTheoGio;
	}

	public void setLuongTheoGio(double luongTheoGio) {
		this.luongTheoGio = luongTheoGio;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maCaLV);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CaLamViec other = (CaLamViec) obj;
		return Objects.equals(maCaLV, other.maCaLV);
	}
}
