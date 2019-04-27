package com.th.thuhien.quanlymonhocsqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.th.thuhien.quanlymonhocsqlite.data.DBManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText edt_ma, edt_ten, edt_sotiet;
    Button btnSave, btnLoad;
    ListView lv_MonHoc;
    ArrayList<MonHoc> data;
    MonHocAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SetControl();

        final DBManager dbManager = new DBManager(this);
        //dbManager.hello();
        data = dbManager.getAllMonHoc();
        setAdapter();


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MonHoc monHoc = createMonHoc();
                if (monHoc != null){
                    dbManager.addMonHoc(monHoc);
                }
                data.clear();
                data.addAll(dbManager.getAllMonHoc());
                setAdapter();

            }
        });

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.clear();
                data.addAll(dbManager.getAllMonHoc());
                setAdapter();
                lv_MonHoc.setSelection(0);
            }
        });
    }

    private void SetControl() {
        edt_ma = (EditText) findViewById(R.id.edittextMaMH);
        edt_ten = (EditText) findViewById(R.id.edittextTenMH);
        edt_sotiet = (EditText) findViewById(R.id.edittextSotiet);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnLoad = (Button) findViewById(R.id.btnLoad);

        lv_MonHoc = (ListView) findViewById(R.id.listViewMonHoc);
    }

    private MonHoc createMonHoc(){
        String maMH = edt_ma.getText().toString();
        String tenMH = edt_ten.getText() + "";
        String soTietMH = edt_sotiet.getText().toString();

        MonHoc monHoc = new MonHoc(Integer.parseInt(maMH), tenMH, Integer.parseInt(soTietMH));
        return monHoc;
    }

    private void setAdapter(){
        if (adapter == null){
            adapter = new MonHocAdapter(this, R.layout.item_monhoc_listview, data);
            lv_MonHoc.setAdapter(adapter);
        }else {
            adapter.notifyDataSetChanged();
            //lv_MonHoc.setSelection(adapter.getCount()-1);
        }

    }


}
