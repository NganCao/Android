package com.th.thuhien.listviewthucpham;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class DrinkActivity extends AppCompatActivity {
    List<ThucAn> list;
    ListView listView;

    // Biến constant được dùng để định danh dữ liệu được truyền giữa các Activity
    public static final String EXTRA_DATA_2 = "EXTRA_DATA_2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        AnhXa();
        setEvent();
    }

    private void setEvent() {
        list = KhoiTao();
        listView.setAdapter(new ThucAnAdapter(this, list));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                ThucAn thucAn = (ThucAn) o;
                //Toast.makeText(DoUongActivity.this, "Selected " + thucAn.getTen(), Toast.LENGTH_LONG).show();
                // Tạo một Intent mới để chứa dữ liệu trả về
                Intent intent = new Intent();
                // Truyền data vào intent
                intent.putExtra(EXTRA_DATA_2, thucAn.getTen());

                // Đặt resultCode là Activity.RESULT_OK to
                // thể hiện đã thành công và có chứa kết quả trả về
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }

    private List<ThucAn> KhoiTao(){
        List<ThucAn> listDoUong = new ArrayList<ThucAn>();

        ThucAn pepsi = new ThucAn("pepsi", "Pepsi");
        ThucAn coca = new ThucAn("coca", "Coca");
        ThucAn heineken = new ThucAn("heineken", "Heineken");
        ThucAn aquafina = new ThucAn("aquafina", "Aquafina");

        listDoUong.add(pepsi);
        listDoUong.add(coca);
        listDoUong.add(heineken);
        listDoUong.add(aquafina);
        return listDoUong;
    }
    private void AnhXa() {
        listView = (ListView) findViewById(R.id.listViewDrink);
    }
}
