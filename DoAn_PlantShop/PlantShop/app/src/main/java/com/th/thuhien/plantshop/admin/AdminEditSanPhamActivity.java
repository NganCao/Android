package com.th.thuhien.plantshop.admin;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import com.squareup.picasso.Picasso;
import com.th.thuhien.plantshop.R;
import com.th.thuhien.plantshop.activity.ChiTietSanPhamActivity;
import com.th.thuhien.plantshop.adapter.MenuAdapter;
import com.th.thuhien.plantshop.adapter.RecyclerViewCTHinhSpAdapter;
import com.th.thuhien.plantshop.model.HinhSanPham;
import com.th.thuhien.plantshop.model.Menu;
import com.th.thuhien.plantshop.model.SanPham;
import com.th.thuhien.plantshop.ultil.HinhSanPhamService;

import java.util.ArrayList;
import java.util.List;

public class AdminEditSanPhamActivity extends AppCompatActivity {

    private Toolbar toolbarAdminEditSp;
    private EditText edt_EditTenSp, edt_EditGiaSp, edt_EditThongTinSp;
    private Button btn_EditSpSave;
    private Spinner spinnerAdminEditSpMenu;
    private ImageView img_hinhMinhHoaEditSp;
    private RecyclerView recyclerViewEditHinhSp;
    private ImageButton img_cong;

    ArrayList<Menu> listMenu;
    MenuAdapter menuAdapter;

    ArrayList<HinhSanPham> listHinhSP;
    RecyclerViewCTHinhSpAdapter hinhSpAdapter;

    private int masp = 0; // mã sp nhận được
    private String hinhChiTiet = ""; // hình nhận được

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_san_pham);

        AnhXa();
        ActionToolbar();
        NhanSanPham();
        ShowHinhSp();
        ButtonEdit();
    }

    private void ButtonEdit() {

        btn_EditSpSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setEnableSanPham(true);
                btn_EditSpSave.setText("Save");
            }
        });
    }

    private void NhanSanPham() {
        SanPham sanPham = (SanPham) getIntent().getSerializableExtra("editsanpham");

        masp = sanPham.getGiaSp();
        hinhChiTiet = sanPham.getHinhAnh();

        edt_EditTenSp.setText(sanPham.getTenSp());
        edt_EditGiaSp.setText(String.valueOf(sanPham.getGiaSp()));
        edt_EditThongTinSp.setText(sanPham.getThongTin());
        spinnerAdminEditSpMenu.setSelection(sanPham.getMaMenu());
        Picasso.with(this).load(sanPham.getHinhAnh())
                .into(img_hinhMinhHoaEditSp);
        setEnableSanPham(false);
    }

    private void setEnableSanPham(boolean b){
        edt_EditTenSp.setEnabled(b);
        edt_EditGiaSp.setEnabled(b);
        edt_EditThongTinSp.setEnabled(b);

        spinnerAdminEditSpMenu.setEnabled(b);
        img_hinhMinhHoaEditSp.setEnabled(b);
        img_cong.setEnabled(b);
    }

    private void AnhXa() {
        toolbarAdminEditSp = (Toolbar) findViewById(R.id.toolbarAdminEditSanPham);
        img_hinhMinhHoaEditSp = (ImageView) findViewById(R.id.imageviewEditSP);
        edt_EditTenSp = (EditText) findViewById(R.id.edittextEditTenSp);
        edt_EditGiaSp = (EditText) findViewById(R.id.edittextEditGiaSp);
        edt_EditThongTinSp = (EditText) findViewById(R.id.edittextEditThongTinSp);
        spinnerAdminEditSpMenu = (Spinner) findViewById(R.id.spinnerEditSpMenu);
        btn_EditSpSave = (Button) findViewById(R.id.buttonEditSpSave);
        recyclerViewEditHinhSp = (RecyclerView) findViewById(R.id.recyclerviewEditHinhSP);
        img_cong = (ImageButton) findViewById(R.id.imagebuttonThemHinh);

        listMenu = new ArrayList<>();
        menuAdapter = new MenuAdapter(listMenu, getApplicationContext());
        spinnerAdminEditSpMenu.setAdapter(menuAdapter);
    }

    private void ActionToolbar(){
        setSupportActionBar(toolbarAdminEditSp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarAdminEditSp.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void ShowHinhSp() {
        listHinhSP = new ArrayList<>();
        hinhSpAdapter = new RecyclerViewCTHinhSpAdapter(getApplicationContext(), R.layout.dong_hinh_sanpham_recyclerview, listHinhSP);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewEditHinhSp.setLayoutManager(llm);
        recyclerViewEditHinhSp.setAdapter(hinhSpAdapter);

        DividerItemDecoration dividerVertical =
                new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL);
        recyclerViewEditHinhSp.addItemDecoration(dividerVertical);

//        HinhSanPham hinhSanPham = new HinhSanPham(1, 10, "https://1.bp.blogspot.com/-xA1DQYrcADo/XJcgoCV5NpI/AAAAAAAAF3k/f8UlgDDuYtMZzF3kF7-1JY7ITExYSgLzgCLcBGAs/s1600/banner-23.png");
//
//        dataHinhSp.add(hinhSanPham);
        listHinhSP.add(new HinhSanPham(masp, 0, hinhChiTiet));
        AsynEditShowHinhSp asynShowHinhSp = new AsynEditShowHinhSp();
        asynShowHinhSp.execute(masp);
    }

    private class AsynEditShowHinhSp extends AsyncTask<Integer, Void, List<HinhSanPham>> {

        @Override
        protected List<HinhSanPham> doInBackground(Integer... integers) {
            HinhSanPhamService hinhSanPhamService = new HinhSanPhamService();
            return hinhSanPhamService.getListHinhByMaSp(integers[0]);
        }

        @Override
        protected void onPostExecute(List<HinhSanPham> hinhSanPhams) {
            super.onPostExecute(hinhSanPhams);
            for (int i = 0; i < hinhSanPhams.size(); i++) {
                listHinhSP.add(hinhSanPhams.get(i));
                Log.d("ghiHinh:", String.valueOf(listHinhSP.get(i).getMaHinh()));
            }
            hinhSpAdapter.notifyDataSetChanged();

        }
    }
}
