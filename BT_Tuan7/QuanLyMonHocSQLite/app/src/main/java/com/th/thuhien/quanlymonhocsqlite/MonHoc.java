package com.th.thuhien.quanlymonhocsqlite;

public class MonHoc {
    private int ma;
    private String ten;
    private int sotiet;

    public MonHoc() {
    }

    public MonHoc(int ma, String ten, int sotiet) {
        this.ma = ma;
        this.ten = ten;
        this.sotiet = sotiet;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getSotiet() {
        return sotiet;
    }

    public void setSotiet(int sotiet) {
        this.sotiet = sotiet;
    }
}
