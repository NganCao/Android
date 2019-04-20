package com.example.bt2_qlnv.model;

import java.io.Serializable;

public class infor implements Serializable {
    private static final long serialVersionUID =1L;
    private String ma;
    private String ten;

    public infor() {
        super();
    }

    public infor(String ma, String ten) {
        super();
        this.ma = ma;
        this.ten = ten;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    @Override
    public String toString() {
        return "infor{" +
                "ma='" + ma + '\'' +
                ", ten='" + ten + '\'' +
                '}';
    }
}
