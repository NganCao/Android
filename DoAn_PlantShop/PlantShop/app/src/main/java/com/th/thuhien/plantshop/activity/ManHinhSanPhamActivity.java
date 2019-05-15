package com.th.thuhien.plantshop.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.th.thuhien.plantshop.R;
import com.th.thuhien.plantshop.adapter.ManHinhSanPhamAdapter;
import com.th.thuhien.plantshop.model.SanPham;
import com.th.thuhien.plantshop.ultil.SanPhamService;

import java.util.ArrayList;
import java.util.List;

public class ManHinhSanPhamActivity extends AppCompatActivity {

    Toolbar toolbar_MHSP;
    ArrayList<SanPham> arrayMHSP;
    ListView lv_MHSP;
    ManHinhSanPhamAdapter manHinhSanPhamAdapter;

    // biến lưu trữ menu đã chọn
    Integer maMenu = 0;
    String tenmenu = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_san_pham);

        AnhXa();
        NhanMaMenu();
        ActionToolbar();
        ListSpTheoMenu();
        ItemListSanPham();
    }

    private void AnhXa() {
        toolbar_MHSP = (Toolbar) findViewById(R.id.toolbarMHSanPham);
        lv_MHSP = (ListView) findViewById(R.id.listviewMHSanPham);
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbar_MHSP);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(tenmenu);
        toolbar_MHSP.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void NhanMaMenu(){
        Intent intent = getIntent();
        maMenu = intent.getIntExtra("maMenu", -1);
        tenmenu = intent.getStringExtra("tenmenu");
        Log.d("giatrimamenu", maMenu + "");
        Log.d("giatritenmamenu", tenmenu + "");
    }

    private void ListSpTheoMenu(){
        arrayMHSP = new ArrayList<>();
        manHinhSanPhamAdapter = new ManHinhSanPhamAdapter(this, arrayMHSP);
        lv_MHSP.setAdapter(manHinhSanPhamAdapter);

        AsynMHSP asysnMHSP = new AsynMHSP();
        asysnMHSP.execute();
    }

    private void ItemListSanPham(){
        lv_MHSP.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ChiTietSanPhamActivity.class);
                intent.putExtra("thongtinsanpham", arrayMHSP.get(position));
                startActivity(intent);
            }
        });
    }

    private class AsynMHSP extends AsyncTask<Void, Void, List<SanPham>> {

        @Override
        protected List<SanPham> doInBackground(Void... voids) {
            SanPhamService sanPhamService = new SanPhamService();
            return sanPhamService.getSanPhamByMenu(maMenu);
        }

        @Override
        protected void onPostExecute(List<SanPham> sanPhams) {
            super.onPostExecute(sanPhams);
            Log.d("size: ", String.valueOf(sanPhams.size()));
            for (int i = 0; i < sanPhams.size(); i++){
                arrayMHSP.add(sanPhams.get(i));

            }
            Log.d("size: ", String.valueOf(arrayMHSP.size()));
            manHinhSanPhamAdapter.notifyDataSetChanged();
        }
    }
}
