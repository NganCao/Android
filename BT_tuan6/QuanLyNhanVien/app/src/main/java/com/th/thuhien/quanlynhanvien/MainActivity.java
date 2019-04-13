package com.th.thuhien.quanlynhanvien;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

//public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener
public class MainActivity extends AppCompatActivity{

    ListView lv_NV;
    ArrayList<NhanVien> data = new ArrayList<>();
    NhanVienAdapter nhanVienAdapter;

    EditText edit_MaNV, edit_TenNV;
    RadioButton radioButton_nu, radioButton_nam;
    Button btnNhapNV;
    ImageButton btnXoaNV;

    int index = -1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();

//        lv_NV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this, "Item", Toast.LENGTH_LONG).show();
//            }
//        });

        lv_NV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                NhanVien nhanVien = data.get(position);
                index = position;
                edit_MaNV.setText(nhanVien.getMaNV());
                //edit_MaNV.setKeyListener(null);
                edit_MaNV.setEnabled(false);
//                edit_MaNV.setEnabled(false);
//                edit_MaNV.setInputType(InputType.TYPE_NULL);
                //edit_MaNV.setFocusable(false);
                edit_TenNV.setText(nhanVien.getTenNV());
                if (nhanVien.getGioiTinhNV().equals("nu")){
                    radioButton_nu.setChecked(true);
                    radioButton_nam.setChecked(false);
                }else {
                    radioButton_nu.setChecked(false);
                    radioButton_nam.setChecked(true);
                }
                btnNhapNV.setText("Update");
                //Toast.makeText(MainActivity.this, "Item", Toast.LENGTH_LONG).show();

                edit_TenNV.setSelection(edit_TenNV.getText().length());
                if (nhanVien.isSelected()){
                    nhanVien.setSelected(true);
                }else {
                    nhanVien.setSelected(false);
                }

                if (nhanVien.isSelected()){

                }
            }
        });

        // bắt sự kiện nút Nhập và Update nhân viên

            btnNhapNV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (edit_MaNV.getText().toString().equals("")){
                        Toast.makeText(MainActivity.this, "Chưa nhập mã NV", Toast.LENGTH_LONG).show();
                        return;
                    }
                    if (edit_TenNV.getText().toString().equals("")){
                        Toast.makeText(MainActivity.this, "Chưa nhập tên NV", Toast.LENGTH_LONG).show();
                        return;
                    }

                    NhanVien nhanVien = getNhanVien();
                    if (checkNhanVienInList(nhanVien.getMaNV())){
                        if (edit_MaNV.isEnabled() == true){
                            Toast.makeText(MainActivity.this, "Mã nhân viên đã tồn tại", Toast.LENGTH_LONG).show();
                            edit_MaNV.requestFocus();
                        }else {
                            updateNhanVien();
                            btnNhapNV.setText("Nhập NV");
                            edit_MaNV.setEnabled(true);
                            edit_MaNV.setText("");
                            edit_TenNV.setText("");
                            edit_MaNV.isFocused();
                        }

                    }else {
                        //Toast.makeText(MainActivity.this, "Nhảy tới đây", Toast.LENGTH_LONG).show();
                        insertNhanVien(nhanVien);
                        // đưa giá trị về trạng thái ban đầu
                        edit_MaNV.setText("");
                        edit_TenNV.setText("");
                        edit_MaNV.setEnabled(true);
                        edit_MaNV.requestFocus();
                    }



                }
            });

        // bắt sự kiện img Xóa
        btnXoaNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteNhanVien();
            }
        });

    }


    // tìm kiếm object trong list -> true: tìm thấy, false: không tìm thấy
    private boolean checkNhanVienInList(String manv){
        for (int i = 0; i < data.size(); i++){
            if (data.get(i).getMaNV().equals(manv)){
                return true;
            }
        }
        return false;
    }

    // khởi tạo nhân viên
    private NhanVien getNhanVien(){
        NhanVien nhanVien = new NhanVien();
        nhanVien.setMaNV(edit_MaNV.getText().toString());
        nhanVien.setTenNV(edit_TenNV.getText().toString());
        if (radioButton_nu.isChecked()){
            nhanVien.setGioiTinhNV("nu");
        }else {
            nhanVien.setGioiTinhNV("nam");
        }
        return nhanVien;
    }

    // insert nhân viên vào listview
    private void insertNhanVien(NhanVien nhanVien){
        data.add(nhanVien);
        nhanVienAdapter.notifyDataSetChanged();
    }

    //xóa nhân viên
    private void deleteNhanVien(){
        for (int i = lv_NV.getChildCount()-1; i >= 0; i--){
            View v = lv_NV.getChildAt(i);
            CheckBox checkBox = (CheckBox) v.findViewById(R.id.checkboxXoaNV);
            if (checkBox.isChecked()){
                data.remove(i);
            }
            nhanVienAdapter.notifyDataSetChanged();
        }
    }

    //update nhân viên
    private void updateNhanVien(){
        String manv = edit_MaNV.getText().toString();
        String tennv = edit_TenNV.getText().toString();
        String gioiTinhNv = "";
        if (radioButton_nu.isChecked()){
            gioiTinhNv = "nu";
        }else {
            gioiTinhNv = "nam";
        }

        data.get(index).setMaNV(manv);
        data.get(index).setTenNV(tennv);
        data.get(index).setGioiTinhNV(gioiTinhNv);
        nhanVienAdapter.notifyDataSetChanged();

        Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_LONG).show();
    }

    // chọn listview item




    private ArrayList<NhanVien> KhoiTao(){
        ArrayList<NhanVien> list = new ArrayList<>();
        NhanVien nhanVien1 = new NhanVien("ma1", "Quach Tinh", "nam");
        NhanVien nhanVien2 = new NhanVien("ma2", "Hoang Dung", "nu");
        NhanVien nhanVien3 = new NhanVien("ma3", "Hong That Cong", "nam");
        NhanVien nhanVien4 = new NhanVien("ma4", "Hoang Duoc Su", "nam");
        NhanVien nhanVien5 = new NhanVien("ma5", "Thanh Co", "nu");

        list.add(nhanVien1);
        list.add(nhanVien2);
        list.add(nhanVien3);
        list.add(nhanVien4);
        list.add(nhanVien5);

        return list;
    }


    private void AnhXa() {
        lv_NV = (ListView) findViewById(R.id.listviewDSNV);
        edit_MaNV = (EditText) findViewById(R.id.edittextMaNV);
        edit_TenNV = (EditText) findViewById(R.id.edittextTenNV);
        radioButton_nu = (RadioButton) findViewById(R.id.radiobuttonNu);
        radioButton_nam = (RadioButton) findViewById(R.id.radiobuttonNam);
        btnNhapNV = (Button) findViewById(R.id.buttonNhapNV);
        btnXoaNV = (ImageButton) findViewById(R.id.buttonXoaNV);

        data = KhoiTao();
        nhanVienAdapter = new NhanVienAdapter(this, R.layout.listview_nhanvien_row, data);
        lv_NV.setAdapter(nhanVienAdapter);

        edit_MaNV.setEnabled(true);
        edit_MaNV.setInputType(InputType.TYPE_CLASS_TEXT);
        edit_MaNV.setFocusable(true);
    }

//    @Override
//    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//        int pos = lv_NV.getPositionForView(buttonView);
//        if (pos != ListView.INVALID_POSITION){
//            NhanVien nhanVien = data.get(pos);
//            nhanVien.setSelected(isChecked);
//            listXoa.add(pos);
//        }
//    }
}
