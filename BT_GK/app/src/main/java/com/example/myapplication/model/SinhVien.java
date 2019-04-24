package com.example.myapplication.model;

import java.io.Serializable;
import java.util.Date;

public class SinhVien extends SV_Lop implements Serializable {

    private static final long serialVersion = 1L;
    private Lop lop;
    private String ho;
    private String ten;
    private boolean phai;
    private String noisinh;
    private Date ngaysinh;

    public SinhVien() {
    }

    public SinhVien(String ma, String ho, String ten, boolean phai, String noisinh, Date ngaysinh, Lop lop) {
        super(ma);
        this.lop = lop;
        this.ho = ho;
        this.ten = ten;
        this.phai = phai;
        this.noisinh = noisinh;
        this.ngaysinh = ngaysinh;
    }

    public SinhVien(String ma, String ho, String ten, boolean phai) {
        super(ma);
        this.ho = ho;
        this.ten = ten;
        this.phai = phai;
    }

    public Lop getLop() {
        return lop;
    }

    public void setLop(Lop lop) {
        this.lop = lop;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public boolean isPhai() {
        return phai;
    }

    public void setPhai(boolean phai) {
        this.phai = phai;
    }

    public String getNoisinh() {
        return noisinh;
    }

    public void setNoisinh(String noisinh) {
        this.noisinh = noisinh;
    }

    public Date getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(Date ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
