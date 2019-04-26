package com.example.myapplication.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.LopAdapter;
import com.example.myapplication.data.DBManager_Lop;
import com.example.myapplication.model.Lop;

import java.util.ArrayList;

public class LopActivity extends AppCompatActivity {

    EditText edt_maLop, edt_tenLop;
    Button btnNhapLop;
    ListView lv_Lop;

    ArrayList<Lop> data_lop;
    LopAdapter lopAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lop);
        final DBManager_Lop dbManager_lop = new DBManager_Lop(this);

        setControl();

        data_lop = dbManager_lop.getAllLop();
        setAdapter();

        btnNhapLop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lop lop = createLop();
                if (lop != null){
                    dbManager_lop.addLop(lop);
                }
                data_lop.clear();
                data_lop.addAll(dbManager_lop.getAllLop());
                setAdapter();
            }
        });
    }

    private void setControl() {
        edt_maLop = (EditText) findViewById(R.id.edittextMaLop);
        edt_tenLop = (EditText) findViewById(R.id.edittextTenLop);
        btnNhapLop = (Button) findViewById(R.id.buttonNhapLop);
        lv_Lop = (ListView) findViewById(R.id.listviewLop);
    }

    private Lop createLop(){
        String maMH = edt_maLop.getText().toString();
        String tenMH = edt_tenLop.getText() + "";

        Lop lop = new Lop(maMH, tenMH);
        return lop;
    }

    private void setAdapter(){
        if (lopAdapter == null){
            lopAdapter = new LopAdapter(this, data_lop);
            lv_Lop.setAdapter(lopAdapter);
        }else {
            lopAdapter.notifyDataSetChanged();
            //lv_MonHoc.setSelection(adapter.getCount()-1);
        }

    }
}
