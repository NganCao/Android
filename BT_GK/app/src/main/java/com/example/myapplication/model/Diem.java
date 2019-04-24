package com.example.myapplication.model;

import java.io.Serializable;

public class Diem implements Serializable {
    private static final long serialVersion = 1L;
    private float diem;
    private MonHoc monHoc;
    private SinhVien sinhVien;

    public Diem() {
    }

    public Diem( SinhVien sinhVien, MonHoc monHoc,float diem) {
        this.diem = diem;
        this.monHoc = monHoc;
        this.sinhVien = sinhVien;
    }

    public float getDiem() {
        return diem;
    }

    public void setDiem(float diem) {
        this.diem = diem;
    }

    public MonHoc getMonHoc() {
        return monHoc;
    }

    public void setMonHoc(MonHoc monHoc) {
        this.monHoc = monHoc;
    }

    public SinhVien getSinhVien() {
        return sinhVien;
    }

    public void setSinhVien(SinhVien sinhVien) {
        this.sinhVien = sinhVien;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
