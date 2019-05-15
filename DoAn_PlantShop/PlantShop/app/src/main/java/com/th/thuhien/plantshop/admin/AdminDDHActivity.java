package com.th.thuhien.plantshop.admin;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.th.thuhien.plantshop.R;
import com.th.thuhien.plantshop.adapter.DonDatHangAdapter;
import com.th.thuhien.plantshop.model.DonDatHang;
import com.th.thuhien.plantshop.ultil.DDH;

import java.util.ArrayList;
import java.util.List;

public class AdminDDHActivity extends AppCompatActivity {

    Toolbar toolbarDDH;
    ListView lv_DDH;

    ArrayList<DonDatHang> donDatHangList;
    DonDatHangAdapter donDatHangAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_ddh);

        AnhXa();
        ActionBar();
        EventClickItemListview();
    }

    private void EventClickItemListview() {
        lv_DDH.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(AdminDDHActivity.this, AdminCT_DDHActivity.class);
                intent.putExtra("maddh", donDatHangList.get(position).getMaDDH());
                startActivity(intent);
            }
        });
    }

    private void ActionBar() {
        setSupportActionBar(toolbarDDH);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarDDH.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void AnhXa() {
        toolbarDDH = (Toolbar) findViewById(R.id.toolbarDDH);
        lv_DDH = (ListView) findViewById(R.id.listviewDDH);

        donDatHangList = new ArrayList<>();
        donDatHangAdapter = new DonDatHangAdapter(donDatHangList, getApplicationContext());
        lv_DDH.setAdapter(donDatHangAdapter);

        AsynListDDH asynListDDH = new AsynListDDH();
        asynListDDH.execute();
    }

    private class AsynListDDH extends AsyncTask<Void, Void, List<DonDatHang>>{

        @Override
        protected List<DonDatHang> doInBackground(Void... voids) {
            DDH ddh = new DDH();
            return ddh.getListDDH();
        }

        @Override
        protected void onPostExecute(List<DonDatHang> donDatHangs) {
            super.onPostExecute(donDatHangs);
            for (int i = 0; i < donDatHangs.size(); i++){
                donDatHangList.add(donDatHangs.get(i));
            }
            donDatHangAdapter.notifyDataSetChanged();
        }
    }
}
