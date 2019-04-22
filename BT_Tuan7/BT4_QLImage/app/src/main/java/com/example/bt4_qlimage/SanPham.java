package com.example.bt4_qlimage;

import java.util.Arrays;

public class SanPham {
    private String tensp;
    private int gia;
    byte[] hinhanh;

    public SanPham() {
    }

    public SanPham(String tensp, int gia, byte[] hinhanh) {
        this.tensp = tensp;
        this.gia = gia;
        this.hinhanh = hinhanh;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public byte[] getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(byte[] hinhanh) {
        this.hinhanh = hinhanh;
    }

    @Override
    public String toString() {
        return "SanPham{" +
                "tensp='" + tensp + '\'' +
                ", gia=" + gia +
                ", hinhanh=" + Arrays.toString(hinhanh) +
                '}';
    }
}
