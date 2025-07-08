package entity;

import java.time.LocalDate;
import java.util.Objects;

public class NhanVien {
	private String maNV;
	private String tenNV;
	private String diaChiNV;
	private String cccdNV;
	private String sdtNV;
	private String emailNV;
	private LocalDate ngayBatDauLV;
	private String chucVuNV;
	private double doanhThuNV;
	private TaiKhoan taiKhoanNV;
	
	public NhanVien(String maNV, String tenNV, String diaChiNV, String cccdNV, String sdtNV, String emailNV,
			LocalDate ngayBatDauLV, String chucVuNV, double doanhThuNV, TaiKhoan taiKhoanNV) {
		super();
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.diaChiNV = diaChiNV;
		this.cccdNV = cccdNV;
		this.sdtNV = sdtNV;
		this.emailNV = emailNV;
		this.ngayBatDauLV = ngayBatDauLV;
		this.chucVuNV = chucVuNV;
		this.doanhThuNV = doanhThuNV;
		this.taiKhoanNV = taiKhoanNV;
	}

	public NhanVien(String maNV) {
		super();
		this.maNV = maNV;
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getTenNV() {
		return tenNV;
	}

	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}

	public String getDiaChiNV() {
		return diaChiNV;
	}

	public void setDiaChiNV(String diaChiNV) {
		this.diaChiNV = diaChiNV;
	}

	public String getCccdNV() {
		return cccdNV;
	}

	public void setCccdNV(String cccdNV) {
		this.cccdNV = cccdNV;
	}

	public String getSdtNV() {
		return sdtNV;
	}

	public void setSdtNV(String sdtNV) {
		this.sdtNV = sdtNV;
	}

	public String getEmailNV() {
		return emailNV;
	}

	public void setEmailNV(String emailNV) {
		this.emailNV = emailNV;
	}

	public LocalDate getNgayBatDauLV() {
		return ngayBatDauLV;
	}

	public void setNgayBatDauLV(LocalDate ngayBatDauLV) {
		this.ngayBatDauLV = ngayBatDauLV;
	}

	public String getChucVuNV() {
		return chucVuNV;
	}

	public void setChucVuNV(String chucVuNV) {
		this.chucVuNV = chucVuNV;
	}

	public double getDoanhThuNV() {
		return doanhThuNV;
	}

	public void setDoanhThuNV(double doanhThuNV) {
		this.doanhThuNV = doanhThuNV;
	}

	public TaiKhoan getTaiKhoanNV() {
		return taiKhoanNV;
	}

	public void setTaiKhoanNV(TaiKhoan taiKhoanNV) {
		this.taiKhoanNV = taiKhoanNV;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maNV);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVien other = (NhanVien) obj;
		return Objects.equals(maNV, other.maNV);
	}
}
