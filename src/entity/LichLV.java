package entity;

import java.util.Objects;

public class LichLV {
	private String maLichLV;
	private NhanVien nvLichLV;
	private CaLamViec caLVLichLV;
	
	public LichLV(String maLichLV, NhanVien nvLichLV, CaLamViec caLVLichLV) {
		super();
		this.maLichLV = maLichLV;
		this.nvLichLV = nvLichLV;
		this.caLVLichLV = caLVLichLV;
	}

	public LichLV(String maLichLV) {
		super();
		this.maLichLV = maLichLV;
	}

	public String getMaLichLV() {
		return maLichLV;
	}

	public void setMaLichLV(String maLichLV) {
		this.maLichLV = maLichLV;
	}

	public NhanVien getNvLichLV() {
		return nvLichLV;
	}

	public void setNvLichLV(NhanVien nvLichLV) {
		this.nvLichLV = nvLichLV;
	}

	public CaLamViec getCaLVLichLV() {
		return caLVLichLV;
	}

	public void setCaLVLichLV(CaLamViec caLVLichLV) {
		this.caLVLichLV = caLVLichLV;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maLichLV);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LichLV other = (LichLV) obj;
		return Objects.equals(maLichLV, other.maLichLV);
	}
}
