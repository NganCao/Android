package com.example.myapplication.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.adapter.MonHocAdapter;
import com.example.myapplication.data.DBManager_Lop;
import com.example.myapplication.model.MonHoc;

import java.util.ArrayList;

public class MonHocActivity extends AppCompatActivity {

    private Toolbar toolbarMH;
    private EditText edt_maMH, edt_tenMH, edt_hocKyMH;
    private Button btn_NhapMH;
    private ListView lv_MH;
    private ImageButton img_XoaMH;

    private ArrayList<MonHoc> monHocArrayList;
    MonHocAdapter monHocAdapter;

    private DBManager_Lop dbManager_lop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon_hoc);

        dbManager_lop = new DBManager_Lop(this);

        AnhXa();
        ActionBar();
        setEventButtonNhap();
        setEventClickItemListview();
        setEventLongClickItem();
        setEventXoaNhieuLop();
    }

    private void setEventXoaNhieuLop() {
        img_XoaMH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MonHocActivity.this);
                builder.setTitle("Hỏi xóa");
                builder.setMessage("Bạn chắc chắn muốn xóa các Môn học đã chọn?");
                //builder.setCancelable(false);
                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteNhieuMonHoc();

                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.show();
            }
        });
    }

    private void deleteNhieuMonHoc(){
        int dem = 0;
        for (int i = lv_MH.getChildCount()-1; i >= 0; i--){
            View v = lv_MH.getChildAt(i);
            CheckBox checkBox = (CheckBox) v.findViewById(R.id.checkboxMonHoc);
            if (checkBox.isChecked()){
                int result = dbManager_lop.deleteMonHoc(monHocArrayList.get(i).getMaMH());
                if (result > 0){
                    dem++;
                    //data_lop.get(i).setSelected(false);
                }
            }
        }
        uploadListMonHOc();
        Toast.makeText(getApplicationContext(), "Đã xóa " + dem + " môn học.", Toast.LENGTH_LONG).show();
    }

    private void setEventLongClickItem() {
        lv_MH.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                int result = dbManager_lop.deleteMonHoc(monHocArrayList.get(position).getMaMH());
                if (result > 0){
                    uploadListMonHOc();
                    Toast.makeText(getApplicationContext(), "Xóa thành công", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getApplicationContext(), "Xóa thất bại", Toast.LENGTH_LONG).show();
                }
                return false;
            }
        });
    }

    private void setEventClickItemListview() {
        lv_MH.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MonHoc monHoc = monHocArrayList.get(position);
                edt_maMH.setText(monHoc.getMaMH());
                edt_tenMH.setText(monHoc.getTenMH());
                edt_hocKyMH.setText(String.valueOf(monHoc.getHocKyMH()));

                edt_maMH.setEnabled(false);
                btn_NhapMH.setText("Save");
            }
        });
    }

    private void setEventButtonNhap() {
        btn_NhapMH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int search = 0;
                if (edt_maMH.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Mã môn học không được trống", Toast.LENGTH_LONG).show();
                    return;
                }
                if (edt_tenMH.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(), "Tên môn học không được trống", Toast.LENGTH_LONG).show();
                    return;
                }
                if (edt_hocKyMH.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Học kỳ không được trống", Toast.LENGTH_LONG).show();
                    return;
                }

                MonHoc monHoc = createMonHoc();
                search = dbManager_lop.searchMonHoc(edt_maMH.getText().toString());
                if (search == 1){
                    int result = dbManager_lop.updateMonHoc(monHoc);
                    if (result > 0){
                        Toast.makeText(getApplicationContext(), "Sửa thành công", Toast.LENGTH_LONG).show();
                        btn_NhapMH.setText("Nhập");
                    }
                }else {
                    // gọi phương thức nhập môn học
                    dbManager_lop.insertMonHoc(monHoc);

                }
                uploadListMonHOc();
                edt_maMH.setText("");
                edt_tenMH.setText("");
                edt_hocKyMH.setText("");
                edt_maMH.setEnabled(true);
                edt_maMH.isFocused();
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
        img_XoaMH = (ImageButton) findViewById(R.id.imagebuttonXoaMH);

        edt_maMH.isFocused();

        monHocArrayList = dbManager_lop.getListMonHoc();
        setAdapter();
    }

    private void setAdapter(){
        if (monHocAdapter == null){
            monHocAdapter = new MonHocAdapter(this, monHocArrayList);
            lv_MH.setAdapter(monHocAdapter);
        }else {
            monHocAdapter.notifyDataSetChanged();
            lv_MH.setSelection(monHocAdapter.getCount()-1);
        }
    }

    private void uploadListMonHOc(){
        monHocArrayList.clear();
        monHocArrayList.addAll(dbManager_lop.getListMonHoc());
        setAdapter();
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
