package com.example.bt2_qlnv.model;

public enum ChucVu {
    TruongPhong("Trưởng Phòng"),
    PhoPhong("Phó Phòng"),
    NhanVien("Nhân Viên");

    private String cv;
    ChucVu(String cv)
    {
        this.cv=cv;
    }
    public String getChucVu()
    {
        return this.cv;
    }
}
