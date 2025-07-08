package entity;

import java.util.Objects;

public class TaiKhoan {
	private String tenTK;
	private String matKhau;
	
	public TaiKhoan(String tenTK, String matKhau) {
		super();
		this.tenTK = tenTK;
		this.matKhau = matKhau;
	}

	public TaiKhoan(String tenTK) {
		super();
		this.tenTK = tenTK;
	}

	public String getTenTK() {
		return tenTK;
	}

	public void setTenTK(String tenTK) {
		this.tenTK = tenTK;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	@Override
	public int hashCode() {
		return Objects.hash(tenTK);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaiKhoan other = (TaiKhoan) obj;
		return Objects.equals(tenTK, other.tenTK);
	}
}
