package com.th.thuhien.plantshop.admin;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.th.thuhien.plantshop.R;
import com.th.thuhien.plantshop.adapter.CT_DDHAdapter;
import com.th.thuhien.plantshop.model.CT_DDH;
import com.th.thuhien.plantshop.model.SanPham;
import com.th.thuhien.plantshop.ultil.CT_DDHService;
import com.th.thuhien.plantshop.ultil.SanPhamService;

import java.util.ArrayList;
import java.util.List;

public class AdminCT_DDHActivity extends AppCompatActivity {

    private Toolbar toolbarCT_DDH;
    private ListView lv_CT_DDH;

    private ArrayList<CT_DDH> ct_ddhArrayList;
    private CT_DDHAdapter ct_ddhAdapter;
    private ArrayList<String> sanPhamArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_ct__ddh);

        AnhXa();
        ActionBar();

    }

    private void ActionBar() {
        setSupportActionBar(toolbarCT_DDH);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarCT_DDH.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void AnhXa() {
        toolbarCT_DDH = (Toolbar) findViewById(R.id.toolbarCT_DDH);
        lv_CT_DDH = (ListView) findViewById(R.id.listviewCT_DDH);

        Intent intent = getIntent();
        int maddh = intent.getIntExtra("maddh", 0);
        //int ma = Integer.parseInt(maddh);

        ct_ddhArrayList = new ArrayList<>();
        sanPhamArrayList = new ArrayList<>();
        ct_ddhAdapter = new CT_DDHAdapter(ct_ddhArrayList, this);
        lv_CT_DDH.setAdapter(ct_ddhAdapter);



        AsynCT_DDH asynCT_ddh = new AsynCT_DDH();
        asynCT_ddh.execute(maddh);
    }

    private class AsynCT_DDH extends AsyncTask<Integer, Void, List<CT_DDH>>{

        @Override
        protected List<CT_DDH> doInBackground(Integer... integers) {
            CT_DDHService ct_ddhService = new CT_DDHService();
            return ct_ddhService.getListCTDDHByMaDD(integers[0]);
        }

        @Override
        protected void onPostExecute(List<CT_DDH> ct_ddhs) {
            super.onPostExecute(ct_ddhs);
            if (ct_ddhs.size() == 0){
                Toast.makeText(getApplicationContext(), "Không có sản phẩm nào", Toast.LENGTH_LONG).show();
            }
            for (int i = 0; i < ct_ddhs.size(); i++){
                ct_ddhArrayList.add(ct_ddhs.get(i));
//                AsynSanPham asynSanPham = new AsynSanPham();
//                Log.d("maspgui: ", String.valueOf(ct_ddhArrayList.get(i).getMaSP()));
//                asynSanPham.execute(ct_ddhArrayList.get(i).getMaSP());
            }
            ct_ddhAdapter.notifyDataSetChanged();
        }
    }

    private class AsynSanPham extends AsyncTask<Integer, Void, String>{

        @Override
        protected String doInBackground(Integer... integers) {
            SanPhamService sanPhamService = new SanPhamService();
            return sanPhamService.getSanPhamByMaMenu(integers[0]);
        }

        @Override
        protected void onPostExecute(String sanPham) {
            super.onPostExecute(sanPham);
            Log.d("tensanpham: ", sanPham);
            sanPhamArrayList.add(sanPham);
            Log.d("quatrinhListSp: ", String.valueOf(sanPhamArrayList.size()));
        }
    }
}
