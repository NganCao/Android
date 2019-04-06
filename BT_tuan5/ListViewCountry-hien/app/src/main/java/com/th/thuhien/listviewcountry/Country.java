package com.th.thuhien.listviewcountry;

public class Country {
    public String hinh;
    public String ten;
    public int danSo;

    public Country() {    }

    public Country(String hinh, String ten, int danSo) {
        this.hinh = hinh;
        this.ten = ten;
        this.danSo = danSo;
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

    public int getDanSo() {
        return danSo;
    }

    public void setDanSo(int danSo) {
        this.danSo = danSo;
    }

    @Override
    public String toString() {
        return this.ten + "(Population: " + this.danSo + ")";
    }
}
