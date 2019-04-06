package com.th.thuhien.listviewthucpham;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class FoodActivity extends AppCompatActivity {

    List<ThucAn> listData;
    ListView listView;

    // Biến constant được dùng để định danh dữ liệu được truyền giữa các Activity
    public static final String EXTRA_DATA = "EXTRA_DATA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        AnhXA();
        setEvent();
    }

    @Override
    public void onBackPressed() {

        // đặt resultCode là Activity.RESULT_CANCELED thể hiện
        // đã thất bại khi người dùng click vào nút Back.
        // Khi này sẽ không trả về data.
        setResult(Activity.RESULT_CANCELED);
        super.onBackPressed();
    }

    private void setEvent() {
        listData = KhoiTao();
        listView.setAdapter(new ThucAnAdapter(this, listData));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                ThucAn thucAn = (ThucAn) o;

                // Tạo một Intent mới để chứa dữ liệu trả về
                Intent intent = new Intent();
                // Truyền data vào intent
                intent.putExtra(EXTRA_DATA, thucAn.getTen());

                // Đặt resultCode là Activity.RESULT_OK to
                // thể hiện đã thành công và có chứa kết quả trả về
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }

    private void AnhXA() {
        listView = (ListView) findViewById(R.id.listViewFood);
    }

    private List<ThucAn> KhoiTao(){
        List<ThucAn> listDoUong = new ArrayList<ThucAn>();

        ThucAn pho = new ThucAn("pho", "Phở Hà Nội");
        ThucAn bunbo = new ThucAn("bunbo", "Bún bò Huế");
        ThucAn miquang = new ThucAn("miquang", "Mì Quảng");
        ThucAn hutieu = new ThucAn("hutieu", "Hủ tiếu Nam Vang");

        listDoUong.add(pho);
        listDoUong.add(bunbo);
        listDoUong.add(miquang);
        listDoUong.add(hutieu);
        return listDoUong;
    }

}
