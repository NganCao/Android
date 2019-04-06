package com.example.quanlybongda;

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
        data.add(new socialNetwork(R.drawable.a1,"Pele","October 23, 1940 (age 72)",R.drawable.a));
        data.add(new socialNetwork(R.drawable.b1,"Diego Maradona","October 30, 1960 (age 52)",R.drawable.b));
        data.add(new socialNetwork(R.drawable.c1,"Johan Cruft","April 25, 1947 (age 65)",R.drawable.c));
        data.add(new socialNetwork(R.drawable.d1,"Franz Beckenbauer","September 11, 1945 (age 67)",R.drawable.d));
        data.add(new socialNetwork(R.drawable.e1,"Michel Platini","June 21, 1955 (age 57)",R.drawable.e));
        data.add(new socialNetwork(R.drawable.a2,"Ronaldo De Lima","September 22, 1976 (age 36)",R.drawable.a));
    }

}

