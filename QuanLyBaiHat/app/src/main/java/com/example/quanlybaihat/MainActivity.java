package com.example.quanlybaihat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import android.graphics.Color;
import java.lang.String;
import java.lang.Object;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int pos = 0;
    int save = -1;
    private ListView listView;
    ArrayList<socialNetwork> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setControl();
        setEvent();

        LinearLayout song = (LinearLayout)findViewById(R.id.song);
        final ListView listView = (ListView) findViewById(R.id.listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this, listView.getItemAtPosition(position).getClass().getName(), Toast.LENGTH_SHORT).show();
                view.setSelected(true);
            }
        });

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
////                Object clickItemObj = adapterView.getAdapter().getItem(index);
//                TextView temp = (TextView) view;
//                String value = listView.getItemAtPosition(position).toString();
//
//                for(int i=0; i<adapterView.getChildCount(); i++)
//                {
//                    if(i == position)
//                    {
//                        adapterView.getChildAt(i).setBackgroundColor(Color.BLUE);
//                    }
//                    else
//                    {
//                        adapterView.getChildAt(i).setBackgroundColor(Color.BLACK);
//                    }
//
//                }
//            }
//        });
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
        data.add(new socialNetwork(R.drawable.rihanna,"Love The Way You Lie","Rihanna","4:23"));
        data.add(new socialNetwork(R.drawable.khalid,"Self","Khalid","3:50"));
    }
}
