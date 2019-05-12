package com.th.thuhien.plantshop.model;

public class DonDatHang {
    private int maDDH;
    private String tenKH;
    private String sdtKH;
    private String emailKH;
    private String diachiKH;

    public DonDatHang() {
    }

    public DonDatHang(int maDDH, String tenKH, String sdtKH, String emailKH, String diachiKH) {
        this.maDDH = maDDH;
        this.tenKH = tenKH;
        this.sdtKH = sdtKH;
        this.emailKH = emailKH;
        this.diachiKH = diachiKH;
    }

    public int getMaDDH() {
        return maDDH;
    }

    public void setMaDDH(int maDDH) {
        this.maDDH = maDDH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getSdtKH() {
        return sdtKH;
    }

    public void setSdtKH(String sdtKH) {
        this.sdtKH = sdtKH;
    }

    public String getEmailKH() {
        return emailKH;
    }

    public void setEmailKH(String emailKH) {
        this.emailKH = emailKH;
    }

    public String getDiachiKH() {
        return diachiKH;
    }

    public void setDiachiKH(String diachiKH) {
        this.diachiKH = diachiKH;
    }
}
