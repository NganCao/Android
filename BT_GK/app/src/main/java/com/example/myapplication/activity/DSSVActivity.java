package com.example.myapplication.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.LopAdapter;
import com.example.myapplication.adapter.SinhVienAdapter;
import com.example.myapplication.data.DBManager_Lop;
import com.example.myapplication.model.SinhVien;

import java.util.ArrayList;

public class DSSVActivity extends AppCompatActivity {

    private Toolbar toolbar;
    ListView lv;
    SinhVienAdapter adapter;
    ArrayList<SinhVien> data;
    int index = -1;
    SinhVien sv_position;
    DBManager_Lop dbManager_lop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dssv);
        dbManager_lop = new DBManager_Lop(this);

        setControl();
        setEvent();

    }

    private void setEvent() {



        data = dbManager_lop.getAllSV();

//        setAdapter();
//        adapter = new SinhVienAdapter(this, data);
//        lv.setAdapter(adapter);

        registerForContextMenu(lv);

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                index = position;
                sv_position = data.get(position);
                return false;
            }
        });


    }

//    private void setAdapter() {
//        if (adapter == null){
//            adapter = new SinhVienAdapter(this ,data);
//            lv.setAdapter(adapter);
//        }else {
//            adapter.notifyDataSetChanged();
//            lv.setSelection(adapter.getCount()-1);
//        }
//    }

    private void setControl() {
//        toolbar = (Toolbar) findViewById(R.id.toolbarDSSV);
        lv = (ListView) findViewById(R.id.lvSV);

//        adapter = new SinhVienAdapter(this, R.layout.item_row_sv, data);
//        lv.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu_dssv,menu);
        menu.getItem(0).setTitle("Thêm sinh viên cho lớp " + sv_position.getMasv());
        menu.getItem(1).setTitle("Xem danh sách điểm");
        menu.getItem(2).setTitle("Chuyển lớp" + sv_position.getMasv());
        menu.getItem(3).setTitle("Xóa sinh viên");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.suaSV:
                Intent i = new Intent(DSSVActivity.this, ThemSVActivity.class);
                startActivity(i);
                break;

            case R.id.xemDiem:
                break;

            case R.id.chuyenLop:
                break;

            case R.id.xoaSV:
//                deleteLop();

                break;
        }
        return super.onContextItemSelected(item);
    }

}