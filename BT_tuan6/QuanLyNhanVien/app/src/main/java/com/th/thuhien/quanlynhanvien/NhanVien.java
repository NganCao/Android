package com.th.thuhien.quanlynhanvien;

public class NhanVien {
    private String maNV;
    private String tenNV;
    private String gioiTinhNV;
    private boolean selected = false;

    public NhanVien() {
    }

    public NhanVien(String maNV, String tenNV, String gioiTinhNV) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.gioiTinhNV = gioiTinhNV;
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

    public String getGioiTinhNV() {
        return gioiTinhNV;
    }

    public void setGioiTinhNV(String gioiTinhNV) {
        this.gioiTinhNV = gioiTinhNV;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
