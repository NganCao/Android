package com.th.thuhien.plantshop.admin;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.ToolbarWidgetWrapper;
import android.widget.ListView;
import android.widget.Spinner;

import com.th.thuhien.plantshop.R;
import com.th.thuhien.plantshop.adapter.ManHinhSanPhamAdapter;
import com.th.thuhien.plantshop.adapter.MenuAdapter;
import com.th.thuhien.plantshop.model.Menu;
import com.th.thuhien.plantshop.model.SanPham;
import com.th.thuhien.plantshop.ultil.MenuService;
import com.th.thuhien.plantshop.ultil.SanPhamService;

import java.util.ArrayList;
import java.util.List;

public class AdminSanPhamActivity extends AppCompatActivity {

    Toolbar toolbarAdminSanPham;
    ListView lv_AdminSanPham;
    Spinner spinnerListMenu;

    ArrayList<SanPham> data;
    ManHinhSanPhamAdapter adapter;

    ArrayList<Menu> listMenu;
    MenuAdapter menuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_san_pham);

        AnhXa();
        getListMenu();
        getListSanPham();
    }

    private void getListMenu() {
        AsynListAdminMenu asynListAdminMenu = new AsynListAdminMenu();
        asynListAdminMenu.execute();
    }

    private void getListSanPham() {
        AsynAdminListSanPham asynAdminListSanPham = new AsynAdminListSanPham();
        asynAdminListSanPham.execute();
    }

    private void AnhXa() {
        toolbarAdminSanPham = (Toolbar) findViewById(R.id.toolbarAdminSanPham);
        lv_AdminSanPham = (ListView) findViewById(R.id.listviewAdminSanPham);
        spinnerListMenu = (Spinner) findViewById(R.id.spinnerListMenu);

        data = new ArrayList<>();
        adapter = new ManHinhSanPhamAdapter(getApplicationContext(), data);
        lv_AdminSanPham.setAdapter(adapter);

        listMenu = new ArrayList<>();
        menuAdapter = new MenuAdapter(listMenu, getApplicationContext());
        spinnerListMenu.setAdapter(menuAdapter);
    }

    public class AsynAdminListSanPham extends AsyncTask<Void, Void, List<SanPham>>{

        @Override
        protected List<SanPham> doInBackground(Void... voids) {
            SanPhamService sanPhamService = new SanPhamService();
            return sanPhamService.getListSanPham();
        }

        @Override
        protected void onPostExecute(List<SanPham> sanPhams) {
            super.onPostExecute(sanPhams);
            for (int i = 0; i < sanPhams.size(); i++){
                data.add(sanPhams.get(i));
            }
            adapter.notifyDataSetChanged();
        }
    }

    public class AsynListAdminMenu extends AsyncTask<Void, Void, List<Menu>>{


        @Override
        protected List<Menu> doInBackground(Void... voids) {
            MenuService menuService = new MenuService();
            return menuService.getListMenu();
        }

        @Override
        protected void onPostExecute(List<Menu> menu) {
            super.onPostExecute(menu);
            for (int i = 0; i < menu.size(); i++){
                listMenu.add(menu.get(i));
            }
            listMenu.add(new Menu("All"));
            menuAdapter.notifyDataSetChanged();
        }
    }

}
