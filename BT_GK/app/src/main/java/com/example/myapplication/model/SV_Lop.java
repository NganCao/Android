package com.example.myapplication.model;

import java.io.Serializable;

public class SV_Lop implements Serializable {
    private static final long serialVersionUID = 1L;
    private String ma;

    public SV_Lop() {
    }

    public SV_Lop(String ma) {
        this.ma = ma;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    @Override
    public String toString() {
        return ma;
    }
}
