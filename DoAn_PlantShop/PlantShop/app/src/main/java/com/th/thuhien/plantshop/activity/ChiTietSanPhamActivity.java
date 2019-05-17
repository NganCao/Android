package com.th.thuhien.plantshop.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.th.thuhien.plantshop.R;
import com.th.thuhien.plantshop.adapter.RecyclerViewCTHinhSpAdapter;
import com.th.thuhien.plantshop.adapter.SanPhamAdapter;
import com.th.thuhien.plantshop.model.GioHang;
import com.th.thuhien.plantshop.model.HinhSanPham;
import com.th.thuhien.plantshop.model.SanPham;
import com.th.thuhien.plantshop.ultil.HinhSanPhamService;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ChiTietSanPhamActivity extends AppCompatActivity {

    Toolbar toolbarChiTiet;
    ImageView imgChiTiet;
    TextView txtTenCT, txtGiaCT, txtMoTaCT;
    Spinner spinnerCT;
    Button btnDatMuaCT;
    RecyclerView recyclerViewHinhSp;

    ArrayList<HinhSanPham> dataHinhSp;
    RecyclerViewCTHinhSpAdapter adapter;

    // các biến lưu chi tiết sản phẩm nhận được
    int id = 0;
    String tenChiTiet = "";
    int giaChiTiet = 0;
    String hinhChiTiet = "";
    String motaChiTiet = "";
    //int idSp = 0;
    int idMenu = 0;

    //String hinh = "";
    String hinhsanpham = "https://4.bp.blogspot.com/-EfuHbKEuRF0/XNPqKPPT8bI/AAAAAAAAGUs/2EaZQ1UIYiMGOA3ma_tDrZcBabk5voiegCLcBGAs/s1600/product.png";
    String adapterHinh = "";
    //boolean test = false;

    //String mMessageReceiver = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);

        AnhXa();
        ActionToolbar();
        //NhanThongTinSanPham();
        //NhanThongTinSanPham();
        //getIncomingIntent();
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, new IntentFilter("custom-message"));
        //Toast.makeText(getApplicationContext(), "hinhnhan:" + hinh, Toast.LENGTH_LONG).show();
        ShowInfor();
        CatchEventSpiner();
        ButtonDatMua();
        ShowHinhSp();

    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menugiohang:
                Intent intent = new Intent(getApplicationContext(), GioHangActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            adapterHinh = intent.getStringExtra("hinhsp");
            ShowInfor();
            //Toast.makeText(ChiTietSanPhamActivity.this, hinh, Toast.LENGTH_SHORT).show();
        }
    };

    private void AnhXa() {
        toolbarChiTiet = (Toolbar) findViewById(R.id.toolbarChiTietSP);
        imgChiTiet = (ImageView) findViewById(R.id.imageviewChiTietSP);
        txtTenCT = (TextView) findViewById(R.id.textviewTenChiTietSP);
        txtGiaCT = (TextView) findViewById(R.id.textviewGiaChiTietSP);
        txtMoTaCT = (TextView) findViewById(R.id.textviewMoTaCTSP);
        spinnerCT = (Spinner) findViewById(R.id.spinerSoLuongCTSP);
        btnDatMuaCT = (Button) findViewById(R.id.buttonDatMuaCT);
        //recyclerViewSPCungLoai = (RecyclerView) findViewById(R.id.recyclerviewSPCungLoai);

        recyclerViewHinhSp = (RecyclerView) findViewById(R.id.recyclerviewHinhSP);
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbarChiTiet);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarChiTiet.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void NhanThongTinSanPham() {
        SanPham sanPham = (SanPham) getIntent().getSerializableExtra("thongtinsanpham");
        id = sanPham.getMaSp();
        tenChiTiet = sanPham.getTenSp();
        giaChiTiet = sanPham.getGiaSp();
        //getIncomingIntent();

        hinhChiTiet = sanPham.getHinhAnh();

//        if (!test){
//            hinhChiTiet = sanPham.getHinhAnh();
//            test = true;
//            Toast.makeText(getApplicationContext(), "test: " + String.valueOf(test), Toast.LENGTH_SHORT).show();
//        }else {
//            //LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, new IntentFilter("custom-message"));
//            getIncomingIntent();
//            hinhChiTiet = hinh;
//        }
        //Log.d("hinhsp: ", hinhChiTiet);
        motaChiTiet = sanPham.getThongTin();
        idMenu = sanPham.getMaMenu();


    }

    private void ShowInfor() {


        NhanThongTinSanPham();
        hinhsanpham = hinhChiTiet;

        if (!adapterHinh.equals("")) {
            hinhsanpham = adapterHinh;
            //Toast.makeText(getApplicationContext(), "hinhnhan:" + hinhsp, Toast.LENGTH_LONG).show();
        }

        txtTenCT.setText(tenChiTiet);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtGiaCT.setText("Giá: " + decimalFormat.format(giaChiTiet) + "Đ");
        txtMoTaCT.setText(motaChiTiet);
        Log.d("hinhdanhan:", hinhsanpham);
        Picasso.with(getApplicationContext()).load(hinhsanpham)
                .placeholder(R.drawable.product)
                .error(R.drawable.error)
                .into(imgChiTiet);

        Animation animationHinh = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alphat_scale_hinhchinh_sanpham);
        //animationHinh.setRepeatCount(Animation.INFINITE);
        findViewById(R.id.imageviewChiTietSP).startAnimation(animationHinh);
    }

    private void CatchEventSpiner() {
        Integer[] soluong = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this, R.layout.support_simple_spinner_dropdown_item, soluong);
        spinnerCT.setAdapter(arrayAdapter);
    }

    private void ButtonDatMua() {
        btnDatMuaCT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.arrayGioHang.size() > 0) {
                    int sl = Integer.parseInt(spinnerCT.getSelectedItem().toString());
                    boolean exists = false;
                    for (int i = 0; i < MainActivity.arrayGioHang.size(); i++) {
                        if (MainActivity.arrayGioHang.get(i).getIdSP() == id) {
                            MainActivity.arrayGioHang.get(i).setSoluongSP(MainActivity.arrayGioHang.get(i).getSoluongSP() + sl);
                            if (MainActivity.arrayGioHang.get(i).getSoluongSP() >= 10) {
                                MainActivity.arrayGioHang.get(i).setSoluongSP(10);
                            }
                            MainActivity.arrayGioHang.get(i).setGiaSP(giaChiTiet * MainActivity.arrayGioHang.get(i).getSoluongSP());
                            exists = true;
                        }
                    }
                    if (exists == false) {
                        int soluong = Integer.parseInt(spinnerCT.getSelectedItem().toString());
                        long giaMoi = soluong * giaChiTiet;
                        MainActivity.arrayGioHang.add(new GioHang(id, tenChiTiet, giaMoi, hinhChiTiet, soluong));
                    }
                } else {
                    int soluong = Integer.parseInt(spinnerCT.getSelectedItem().toString());
                    long giaMoi = soluong * giaChiTiet;
                    MainActivity.arrayGioHang.add(new GioHang(id, tenChiTiet, giaMoi, hinhChiTiet, soluong));
                }
                DisplayMetrics metrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(metrics);
                int height = metrics.heightPixels;
                int width = metrics.widthPixels;
                TranslateAnimation animation = new TranslateAnimation(0, 500, 0, -500);
                animation.setDuration(500);
                animation.setFillAfter(false);
                animation.setAnimationListener(new MyAnimationListener());

                imgChiTiet.startAnimation(animation);

            }
        });
    }

    private void ShowHinhSp() {
        dataHinhSp = new ArrayList<>();
        adapter = new RecyclerViewCTHinhSpAdapter(getApplicationContext(), R.layout.dong_hinh_sanpham_recyclerview, dataHinhSp);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewHinhSp.setLayoutManager(llm);
        recyclerViewHinhSp.setAdapter(adapter);

        DividerItemDecoration dividerVertical =
                new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL);
        recyclerViewHinhSp.addItemDecoration(dividerVertical);

//        HinhSanPham hinhSanPham = new HinhSanPham(1, 10, "https://1.bp.blogspot.com/-xA1DQYrcADo/XJcgoCV5NpI/AAAAAAAAF3k/f8UlgDDuYtMZzF3kF7-1JY7ITExYSgLzgCLcBGAs/s1600/banner-23.png");
//
//        dataHinhSp.add(hinhSanPham);
        dataHinhSp.add(new HinhSanPham(id, 0, hinhChiTiet));
        AsynShowHinhSp asynShowHinhSp = new AsynShowHinhSp();
        asynShowHinhSp.execute(id);
    }

    //    private void getIncomingIntent(){
//        if (getIntent().hasExtra("url_hinh")){
//            hinhsp = getIntent().getStringExtra("url_hinh");
//        }else {
//            NhanThongTinSanPham();
//        }
//    }
    private class AsynShowHinhSp extends AsyncTask<Integer, Void, List<HinhSanPham>> {

        @Override
        protected List<HinhSanPham> doInBackground(Integer... integers) {
            HinhSanPhamService hinhSanPhamService = new HinhSanPhamService();
            return hinhSanPhamService.getListHinhByMaSp(integers[0]);
        }

        @Override
        protected void onPostExecute(List<HinhSanPham> hinhSanPhams) {
            super.onPostExecute(hinhSanPhams);
            for (int i = 0; i < hinhSanPhams.size(); i++) {
                dataHinhSp.add(hinhSanPhams.get(i));
                Log.d("ghiHinh:", String.valueOf(dataHinhSp.get(i).getMaHinh()));
            }
            adapter.notifyDataSetChanged();

        }
    }

    private class MyAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationEnd(Animation animation) {
            imgChiTiet.clearAnimation();
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(imgChiTiet.getWidth(), imgChiTiet.getHeight());
            lp.setMargins(50, 100, 0, 0);
            imgChiTiet.setLayoutParams(lp);

        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

        @Override
        public void onAnimationStart(Animation animation) {
//            Intent intent = new Intent(getApplicationContext(), GioHangActivity.class);
//            startActivity(intent);
        }

    }
}
