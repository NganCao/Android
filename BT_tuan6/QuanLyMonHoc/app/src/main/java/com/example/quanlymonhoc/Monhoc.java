package com.example.quanlymonhoc;

public class Monhoc {
    private int icon;
    private String ten, ma, sotiet;

    public Monhoc() {
    }

    public Monhoc(int icon, String ten, String ma, String sotiet) {
        this.icon = icon;
        this.ten = ten;
        this.ma = ma;
        this.sotiet = sotiet;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getSotiet() {
        return sotiet;
    }

    public void setSotiet(String sotiet) {
        this.sotiet = sotiet;
    }

    @Override
    public String toString() {
        return "Monhoc{" +
                "icon=" + icon +
                ", ten='" + ten + '\'' +
                ", ma='" + ma + '\'' +
                ", sotiet='" + sotiet + '\'' +
                '}';
    }
}
