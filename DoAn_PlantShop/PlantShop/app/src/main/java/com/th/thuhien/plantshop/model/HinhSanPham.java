package com.th.thuhien.plantshop.model;

public class HinhSanPham {
    private int maSp;
    private int maHinh;
    private String urlHinh;

    public HinhSanPham(int maSp, int maHinh, String urlHinh) {
        this.maSp = maSp;
        this.maHinh = maHinh;
        this.urlHinh = urlHinh;
    }

    public HinhSanPham() {
    }

    public int getMaSp() {
        return maSp;
    }

    public void setMaSp(int maSp) {
        this.maSp = maSp;
    }

    public int getMaHinh() {
        return maHinh;
    }

    public void setMaHinh(int maHinh) {
        this.maHinh = maHinh;
    }

    public String getUrlHinh() {
        return urlHinh;
    }

    public void setUrlHinh(String urlHinh) {
        this.urlHinh = urlHinh;
    }
}
