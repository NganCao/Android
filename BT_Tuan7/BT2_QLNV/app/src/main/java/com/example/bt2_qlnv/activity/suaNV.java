package com.example.bt2_qlnv.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.bt2_qlnv.R;
import com.example.bt2_qlnv.model.NhanVien;

public class suaNV extends Activity{
    EditText editMa,editTen;
    RadioButton radNam;
    Button btnClear,btnSave;
    NhanVien nv=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_nv);
        setControl();
        getDefaultData();
        setEvents();
    }

    public void setControl()
    {
        editMa=(EditText) findViewById(R.id.editMaNV);
        editTen=(EditText) findViewById(R.id.editTenNV);
        radNam=(RadioButton) findViewById(R.id.radNam);
        editMa.setEnabled(false);
        editTen.requestFocus();

        btnClear=(Button) findViewById(R.id.btnxoatrang);
        btnSave=(Button) findViewById(R.id.btnluunv);
    }

    public void getDefaultData()
    {
        Intent i =getIntent();
        Bundle b=i.getBundleExtra("DATA");
        nv=(NhanVien) b.getSerializable("NHANVIEN");
        editMa.setText(nv.getMa());
        editTen.setText(nv.getTen());
        radNam.setChecked(true);
        if(nv.isGioitinh())
            radNam.setChecked(false);
    }
    public void setEvents()
    {
        btnClear.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                editTen.setText("");
                editTen.requestFocus();
            }
        });
        /**
         * bấm lưu thì đóng cửa sổ sửa này
         * và truyền dữ liệu qua màn hình cha để nó tự cập nhật
         */
        btnSave.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i =getIntent();
                nv.setTen(editTen.getText()+"");
                nv.setGioitinh(!radNam.isChecked());
                Bundle b=new Bundle();
                b.putSerializable("NHANVIEN", nv);
                i.putExtra("DATA", b);
                setResult(MainActivity.SUA_PASS, i);
                finish();
            }
        });
    }
}
