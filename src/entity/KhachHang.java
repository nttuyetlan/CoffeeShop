package entity;

import java.util.Objects;

public class KhachHang {
	private String sdtKH;
	private String tenKH;
	private String emailKH;
	private int diemTL;
	
	public KhachHang(String sdtKH, String tenKH, String emailKH, int diemTL) {
		super();
		this.sdtKH = sdtKH;
		this.tenKH = tenKH;
		this.emailKH = emailKH;
		this.diemTL = diemTL;
	}

	public KhachHang(String sdtKH) {
		super();
		this.sdtKH = sdtKH;
	}

	public String getSdtKH() {
		return sdtKH;
	}

	public void setSdtKH(String sdtKH) {
		this.sdtKH = sdtKH;
	}

	public String getTenKH() {
		return tenKH;
	}

	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}

	public String getEmailKH() {
		return emailKH;
	}

	public void setEmailKH(String emailKH) {
		this.emailKH = emailKH;
	}

	public int getDiemTL() {
		return diemTL;
	}

	public void setDiemTL(int diemTL) {
		this.diemTL = diemTL;
	}

	@Override
	public int hashCode() {
		return Objects.hash(sdtKH);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KhachHang other = (KhachHang) obj;
		return Objects.equals(sdtKH, other.sdtKH);
	}
}
