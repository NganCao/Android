package com.example.myapplication.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapplication.model.Lop;

import java.util.ArrayList;

public class DBManager_Lop extends SQLiteOpenHelper {

    private static String DB_NAME = "dbSinhVien.db";
    private static int DB_VERSION = 1;



    // Define table MonHoc
    private static final String TB_LOPS = "tbLop";
    //private static final String COL_MONHOC_ID = "lop_id";
    private static final String COL_LOP_MA = "lop_ma";
    private static final String COL_LOP_TEN = "lop_ten";


    private Context context;
    private String SQLQuery = "CREATE TABLE " + TB_LOPS + " (" +
            //COL_MONHOC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            COL_LOP_MA + " TEXT PRIMARY KEY NOT NULL, " +
            COL_LOP_TEN + " TEXT)";

    public DBManager_Lop(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

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
}
