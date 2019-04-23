package com.example.quanlysinhvien;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB extends SQLiteOpenHelper {
    private static String DB_NAME = "dbSinhVien.db";
    private static int DB_VERSION = 1;
    //DEFINE TABLE
    private static final String TB_LOP = "tbLop";
    private static final String COL_LOP_MALOP = "lop_malop";
    private static final String COL_LOP_TENLOP = "lop_tenlop";

    private static final String TB_MONHOC = "tbMonHoc";
    private static final String COL_MONHOC_MAMH = "monhoc_mamon";
    private static final String COL_MONHOC_TENMH = "monhoc_tenmon";
    private static final String COL_MONHOC_HOCKY = "monhoc_hocky";

    private static final String TB_SINHVIEN = "SinhVien";
    private static final String COL_SINHVIEN_MASV = "masv";
    private static final String COL_SINHVIEN_HO = "hosv";
    private static final String COL_SINHVIEN_TEN = "tensv";
    private static final String COL_SINHVIEN_PHAI = "phai";
    private static final String COL_SINHVIEN_NOISINH = "noisinh";
    private static final String COL_SINHVIEN_NGAYSINH = "ngaysing";

    public DB (Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void onUpgrade (SQLiteDatabase db, int i, int i1){
    }

    public void onCreate(SQLiteDatabase sqLiteDatabase){
    }
}
