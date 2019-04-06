package com.th.thuhien.listviewcountry;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Country> details;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();
        setEvent();
    }

    private void setEvent() {
        details = KhoiTao();
        listView.setAdapter(new CountryAdapter(this, details));
    }

    private void AnhXa() {
        listView = (ListView) findViewById(R.id.listview);
    }

    private List<Country> KhoiTao(){
        List<Country> list = new ArrayList<Country>();
        Country vietnam = new Country("flag_vietnam", "Vietnam", 98000000);
        Country usa = new Country("flag_united_states", "United States", 320000000);
        Country russia = new Country("flag_russia", "Russia", 142000000);

        list.add(vietnam);
        list.add(usa);
        list.add(russia);
        return list;
    }
}
