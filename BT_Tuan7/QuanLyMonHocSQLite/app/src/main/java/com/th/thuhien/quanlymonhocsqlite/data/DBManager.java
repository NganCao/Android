package com.th.thuhien.quanlymonhocsqlite.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.th.thuhien.quanlymonhocsqlite.MonHoc;

import java.util.ArrayList;

public class DBManager extends SQLiteOpenHelper {

    private static String DB_NAME = "dbMonHoc.db";
    private static int DB_VERSION = 1;

    private static String TAG = "DBManager";

    // Define table MonHoc
    private static final String TB_MONHOCS = "tbMonHoc";
    private static final String COL_MONHOC_ID = "monhoc_id";
    private static final String COL_MONHOC_MA = "monhoc_ma";
    private static final String COL_MONHOC_TEN = "monhoc_ten";
    private static final String COL_MONHOC_SOTIET = "monhoc_sotiet";

    private Context context;
    private String SQLQuery = "CREATE TABLE " + TB_MONHOCS + " (" +
            //COL_MONHOC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            COL_MONHOC_MA + " INTEGER PRIMARY KEY NOT NULL, " +
            COL_MONHOC_TEN + " TEXT, " +
            COL_MONHOC_SOTIET + " INTEGER)";

    public DBManager(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
        Log.d(TAG, "DBManager: ");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLQuery);
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG,"onUpgrade: ");
    }



    public void addMonHoc(MonHoc monHoc){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_MONHOC_MA, monHoc.getMa());
        values.put(COL_MONHOC_TEN, monHoc.getTen());
        values.put(COL_MONHOC_SOTIET, monHoc.getSotiet());
        db.insert(TB_MONHOCS, null, values);
        db.close();
        Log.d(TAG, "addMonHoc successfuly");
    }

    public ArrayList<MonHoc> getAllMonHoc(){
        ArrayList<MonHoc> list = new ArrayList<>();

        String query = "SELECT * FROM " + TB_MONHOCS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            do{
                MonHoc monHoc = new MonHoc();
                monHoc.setMa(cursor.getInt(0));
                monHoc.setTen(cursor.getString(1));
                monHoc.setSotiet(cursor.getInt(2));
                list.add(monHoc);
            }while (cursor.moveToNext());
        }
        db.close();

        return list;
    }
}
