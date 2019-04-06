package com.th.thuhien.listviewthucpham;

public class ThucAn {
    private String hinh;
    private String ten;

    public ThucAn() {
    }

    public ThucAn(String hinh, String ten) {
        this.hinh = hinh;
        this.ten = ten;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
}
