package com.th.thuhien.plantshop.model;

public class CT_DDH {
    private int maDDH;
    private int maSP;
    private int soluong;
    private int dongia;

    public CT_DDH() {
    }

    public CT_DDH(int maDDH, int maSP, int soluong, int dongia) {
        this.maDDH = maDDH;
        this.maSP = maSP;
        this.soluong = soluong;
        this.dongia = dongia;
    }

    public int getMaDDH() {
        return maDDH;
    }

    public void setMaDDH(int maDDH) {
        this.maDDH = maDDH;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getDongia() {
        return dongia;
    }

    public void setDongia(int dongia) {
        this.dongia = dongia;
    }
}
