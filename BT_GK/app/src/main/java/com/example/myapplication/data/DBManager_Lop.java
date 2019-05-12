package com.example.myapplication.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.myapplication.model.Lop;
import com.example.myapplication.model.MonHoc;

import java.util.ArrayList;

public class DBManager_Lop extends SQLiteOpenHelper {

    private static String DB_NAME = "dbSinhVien.db";
    private static int DB_VERSION = 1;

    private static final String TB_LOPS = "tbLop";
    private static final String COL_LOP_MA = "lop_ma";
    private static final String COL_LOP_TEN = "lop_ten";

    private static String TABLE_MONHOC = "tbMonHoc";
    private static String COL_MA_MONHOC = "ma_monhoc";
    private static String COL_TEN_MONHOC = "ten_monhoc";
    private static String COL_HOCKY_MONHOC = "hocky_monhoc";

    private Context context;
    private String SQLQuery = "CREATE TABLE " + TB_LOPS + " (" +
            COL_LOP_MA + " TEXT PRIMARY KEY NOT NULL, " +
            COL_LOP_TEN + " TEXT)";

    private static String SQLQueryMonHoc = "CREATE TABLE " + TABLE_MONHOC
            + " (" + COL_MA_MONHOC + " TEXT PRIMARY KEY NOT NULL, "
            + COL_TEN_MONHOC + " TEXT, "
            + COL_HOCKY_MONHOC + " INTEGER)";

    public DBManager_Lop(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLQuery);
        db.execSQL(SQLQueryMonHoc);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TB_LOPS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MONHOC);


        // create new tables
        onCreate(db);
    }

    public void addLop(Lop lop){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_LOP_MA, lop.getMaLop());
        values.put(COL_LOP_TEN, lop.getTenLop());

        db.insert(TB_LOPS, null, values);
        db.close();
    }

    public ArrayList<Lop> getAllLop(){
        ArrayList<Lop> list = new ArrayList<>();

        String query = "SELECT * FROM " + TB_LOPS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            do{
                Lop lop = new Lop();
                lop.setMaLop(cursor.getString(0));
                lop.setTenLop(cursor.getString(1));
                lop.setSelected(false);
                list.add(lop);
            }while (cursor.moveToNext());
        }
        db.close();

        return list;
    }

    public int updateLop(Lop lop){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_LOP_MA, lop.getMaLop());
        values.put(COL_LOP_TEN, lop.getTenLop());

        return db.update(TB_LOPS, values, COL_LOP_MA + "=?", new String[]{String.valueOf(lop.getMaLop())});

    }

    public int searchMaLop(String malop){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("select * from " + TB_LOPS + " where lop_ma =?", new String[]{malop});
        if (c.moveToFirst()){
            return 1;
        }
        return 0;

    }
    public int deleteLop(String maLop){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TB_LOPS, COL_LOP_MA + "=?", new String[]{maLop});
    }

    public void insertMonHoc(MonHoc monHoc){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_MA_MONHOC, monHoc.getMaMH());
        values.put(COL_TEN_MONHOC, monHoc.getTenMH());
        values.put(COL_HOCKY_MONHOC, monHoc.getHocKyMH());

        db.insert(TABLE_MONHOC, null, values);
        db.close();
        Log.d("insertMonhoc: ", "thanh cong");
    }

    public ArrayList<MonHoc> getListMonHoc(){
        ArrayList<MonHoc> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_MONHOC;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            do{
                MonHoc monHoc = new MonHoc();
                monHoc.setMaMH(cursor.getString(0));
                monHoc.setTenMH(cursor.getString(1));
                monHoc.setHocKyMH(cursor.getInt(2));
                list.add(monHoc);
            }while (cursor.moveToNext());
        }
        return list;
    }

    public int updateMonHoc(MonHoc monHoc){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put(COL_MA_MONHOC, monHoc.getMaMH());
        values.put(COL_TEN_MONHOC, monHoc.getTenMH());
        values.put(COL_HOCKY_MONHOC, monHoc.getHocKyMH());

        return db.update(TABLE_MONHOC, values, COL_MA_MONHOC + "=?", new String[]{String.valueOf(monHoc.getMaMH())});
    }

    public int deleteMonHoc(String maMonhoc){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_MONHOC, COL_MA_MONHOC + "=?", new String[]{maMonhoc});
    }

    public int searchMonHoc(String maMonhoc){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_MONHOC + " WHERE " + COL_MA_MONHOC + " =?", new String[]{maMonhoc});
        if (cursor.moveToFirst()){
            return 1;
        }
        return 0;
    }
}
