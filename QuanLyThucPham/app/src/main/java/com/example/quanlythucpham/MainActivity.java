package com.example.quanlythucpham;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    ArrayList<socialNetwork> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setControl();
        setEvent();
    }

    private void setControl(){
        listView = (ListView)findViewById(R.id.listView);
    }

    private void setEvent (){
        KhoiTao();
        socialNetworkAdapter adapter = new socialNetworkAdapter(this, R.layout.item_row,data);
        listView.setAdapter(adapter);
    }

    void KhoiTao(){
        data.add(new socialNetwork(R.drawable.strawberry,"Strawbery","It is an aggregate accessory fruit"));
        data.add(new socialNetwork(R.drawable.banana,"Banana","It is the largest herbaceous flowering plant"));
        data.add(new socialNetwork(R.drawable.orange,"Orange","Citrus fruit"));
        data.add(new socialNetwork(R.drawable.mixed,"Mixed","Mixed fruit"));
    }

}
