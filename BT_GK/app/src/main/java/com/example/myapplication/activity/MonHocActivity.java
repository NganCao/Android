package com.example.myapplication.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.data.DBManager_Lop;
import com.example.myapplication.model.MonHoc;

public class MonHocActivity extends AppCompatActivity {

    private Toolbar toolbarMH;
    private EditText edt_maMH, edt_tenMH, edt_hocKyMH;
    private Button btn_NhapMH;
    private ListView lv_MH;

    private DBManager_Lop dbManager_monHoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon_hoc);

        dbManager_monHoc = new DBManager_Lop(this);

        AnhXa();
        ActionBar();
        setEventButtonNhap();
    }

    private void setEventButtonNhap() {
        btn_NhapMH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MonHoc monHoc = createMonHoc();
                if (edt_maMH.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Mã môn học không được trống", Toast.LENGTH_LONG).show();
                    return;
                }
                if (edt_tenMH.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Tên môn học không được trống", Toast.LENGTH_LONG).show();
                    return;
                }
                if (edt_hocKyMH.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Học kỳ không được trống", Toast.LENGTH_LONG).show();
                    return;
                }
                // gọi phương thức nhập môn học
                dbManager_monHoc.insertMonHoc(monHoc);

            }
        });
    }

    private void ActionBar() {
        toolbarMH.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbarMH.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MonHocActivity.this, MainActivity.class));
            }
        });
    }

    private void AnhXa() {
        toolbarMH = (Toolbar) findViewById(R.id.toolbarMonHoc);
        edt_maMH = (EditText) findViewById(R.id.edittextMaMH);
        edt_tenMH = (EditText) findViewById(R.id.edittextTenMH);
        edt_hocKyMH = (EditText) findViewById(R.id.edittextHocKyMH);
        btn_NhapMH = (Button) findViewById(R.id.buttonNhapMH);
        lv_MH = (ListView) findViewById(R.id.listviewMonHoc);

        edt_maMH.setFocusable(true);
    }

    private MonHoc createMonHoc(){
        String maMonHoc = edt_maMH.getText().toString();
        String tenMonHoc = edt_tenMH.getText().toString();
        String hocKyMonHoc = edt_hocKyMH.getText().toString();
        MonHoc monHoc = new MonHoc(maMonHoc, tenMonHoc, Integer.parseInt(hocKyMonHoc));
        Log.d("layform: ", monHoc.getMaMH());
        return monHoc;
    }
}
