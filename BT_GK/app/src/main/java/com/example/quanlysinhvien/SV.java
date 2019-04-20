package com.example.quanlysinhvien;

import java.util.Date;

public class SV {
    int icon;
    String maSV;
    String HoSV;
    String tenSV;
    String phai;
    String noisinh;
    Date ngaysinh;
    String maLop;

    public SV() {
    }

    public SV(int icon, String maSV, String hoSV, String tenSV, String maLop) {
        this.icon = icon;
        this.maSV = maSV;
        this.HoSV = hoSV;
        this.tenSV = tenSV;
        this.maLop = maLop;
    }

    public SV(int icon, String maSV, String hoSV, String tenSV, String phai, String noisinh, Date ngaysinh, String maLop) {
        this.icon = icon;
        this.maSV = maSV;
        HoSV = hoSV;
        this.tenSV = tenSV;
        this.phai = phai;
        this.noisinh = noisinh;
        this.ngaysinh = ngaysinh;
        this.maLop = maLop;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getHoSV() {
        return HoSV;
    }

    public void setHoSV(String hoSV) {
        HoSV = hoSV;
    }

    public String getTenSV() {
        return tenSV;
    }

    public void setTenSV(String tenSV) {
        this.tenSV = tenSV;
    }

    public String getPhai() {
        return phai;
    }

    public void setPhai(String phai) {
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

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    @Override
    public String toString() {
        return "SV{" +
                "icon=" + icon +
                ", maSV='" + maSV + '\'' +
                ", HoTen='" + HoSV + tenSV + '\'' +
                ", phai='" + phai + '\'' +
                ", noisinh='" + noisinh + '\'' +
                ", ngaysinh=" + ngaysinh +
                ", maLop='" + maLop + '\'' +
                '}';
    }
}
