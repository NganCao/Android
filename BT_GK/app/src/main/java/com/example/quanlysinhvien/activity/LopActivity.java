package com.example.quanlysinhvien.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.quanlysinhvien.R;
import com.example.quanlysinhvien.adapter.LopAdapter;
import com.example.quanlysinhvien.model.Lop;

import java.util.ArrayList;
import java.util.List;

public class LopActivity extends AppCompatActivity {

    ArrayList<Lop> data;
    ListView listViewLop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lop);

        AnhXa();
        setEvent();
    }

    private void setEvent() {
        data = KhoiTao();
        listViewLop.setAdapter(new LopAdapter(this, data));
    }

    private void AnhXa() {
        listViewLop = (ListView) findViewById(R.id.listviewLop);
    }

    private ArrayList<Lop> KhoiTao(){
        ArrayList<Lop> list = new ArrayList<>();

        Lop lop1 = new Lop("D15CQCP01", "Công nghệ phần mềm");
        Lop lop2 = new Lop("D15CQMT01", "Mạng máy tính");
        Lop lop3 = new Lop("D15CQIS01", "Hệ thống thông tin");

        list.add(lop1);
        list.add(lop2);
        list.add(lop3);

        return list;
    }
}
