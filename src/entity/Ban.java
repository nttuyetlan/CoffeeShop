package entity;

import java.util.Objects;

public class Ban {
	private String maBan;
	private int soLuongGhe;
	private String loaiGhe;
	private String khuVuc;
	
	public Ban(String maBan, int soLuongGhe, String loaiGhe, String khuVuc) {
		super();
		this.maBan = maBan;
		this.soLuongGhe = soLuongGhe;
		this.loaiGhe = loaiGhe;
		this.khuVuc = khuVuc;
	}

	public Ban(String maBan) {
		super();
		this.maBan = maBan;
	}

	public String getMaBan() {
		return maBan;
	}

	public void setMaBan(String maBan) {
		this.maBan = maBan;
	}

	public int getSoLuongGhe() {
		return soLuongGhe;
	}

	public void setSoLuongGhe(int soLuongGhe) {
		this.soLuongGhe = soLuongGhe;
	}

	public String getLoaiGhe() {
		return loaiGhe;
	}

	public void setLoaiGhe(String loaiGhe) {
		this.loaiGhe = loaiGhe;
	}

	public String getKhuVuc() {
		return khuVuc;
	}

	public void setKhuVuc(String khuVuc) {
		this.khuVuc = khuVuc;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maBan);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ban other = (Ban) obj;
		return Objects.equals(maBan, other.maBan);
	}
}
