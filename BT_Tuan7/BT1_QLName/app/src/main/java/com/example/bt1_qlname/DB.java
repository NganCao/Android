package com.example.bt1_qlname;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DB extends SQLiteOpenHelper {

    private final String TAG = "DBManger";
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "Student.db";
    private static final String DB_TABLENAME = "Student";
    private static final String COLUMN_ID ="ID";
    private static final String COLUMN_NAME = "Name";

    public DB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STUDENTS_TABLE = "CREATE TABLE " +
                DB_TABLENAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_NAME + " TEXT )";

        //thực thi truy vấn
        db.execSQL(CREATE_STUDENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLENAME);
        onCreate(db);
    }

    public String loadData(){
        String result = "";
        String query = "SELECT * FROM " + DB_TABLENAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            int result_0 = cursor.getInt(0);
            String result_1 = cursor.getString(1);
            result += String.valueOf(result_0) + " " + result_1 + System.getProperty("line.separator");
        }
        //close cursor
        cursor.close();
        db.close();
        return result;
    }

    public void addData(Student student){
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, student.getId());
        values.put(COLUMN_NAME, student.getName());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(DB_TABLENAME, null, values);
        db.close();
        Log.i(TAG, "addData: ");
    }

    public boolean delData(int ID){
        boolean result = false;
        String query = "Select * FROM "
                + DB_TABLENAME + " WHERE "
                + COLUMN_ID + " = '"
                + String.valueOf(ID) + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Student student = new Student();
        if (cursor.moveToFirst()) {
            student.setId(Integer.parseInt(cursor.getString(0)));
            db.delete(DB_TABLENAME, COLUMN_ID + "=?",
                    new String[] {
                            String.valueOf(student.getId())
                    });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public boolean updateData(int ID, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(COLUMN_ID, ID);
        args.put(COLUMN_NAME, name);
        return db.update(DB_TABLENAME, args, COLUMN_ID + " = " + ID, null) > 0;
    }

    //tìm kiếm Student theo StudentName
//kết quả trả về là Student đầu tiên trong danh sách kết quả
    public Student findFisrtData(String studentname) {

        //chuỗi truy vấn tìm kiếm Student theo StudentName
        String query = "Select * FROM " + DB_TABLENAME
                + " WHERE " + COLUMN_NAME + " = "
                + "'" + studentname + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        // Thực thi truy vấn và gán kết quả đến đối tượng Cursor
        Cursor cursor = db.rawQuery(query, null);
        Student student = new Student();
        //trả về hàng đầu tiên trong kết quả
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            student.setId(Integer.parseInt(cursor.getString(0)));
            student.setName(cursor.getString(1));
            cursor.close();
        } else {
            student = null;
        }
        db.close();
        //trả về sinh viên đầu tiên tìm được
        return student;
    }

    //tìm kiếm Student theo StudentName
//kết quả trả về là tất cả Student trong danh sách kết quả
    public List<Student> findAllData(String studentname) {
        //chuỗi truy vấn tìm kiếm Student theo StudentName
        String query = "Select * FROM " + DB_TABLENAME
                + " WHERE " + COLUMN_NAME + " = "
                + "'" + studentname + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        //danh sách chứa tất cả các Student tìm được
        List<Student> lst =  new ArrayList<>();
        // Thực thi truy vấn và gán kết quả đến đối tượng Cursor
        Cursor cursor = db.rawQuery(query, null);
        //duyệt qua tất cả các hàng từ hàng đầu tiên
        if(cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setId(Integer.parseInt(cursor.getString(0)));
                student.setName(cursor.getString(1));
                lst.add(student);
            }while (cursor.moveToNext());
        }
        //đóng các đối tượng
        cursor.close();
        db.close();
        //trả về danh sách sinh viên tìm được
        return lst;
    }
}
