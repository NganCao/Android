package com.th.thuhien.listviewmau;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    ArrayList<SocialNetwork> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();
        SetEvent();
    }

    private void SetEvent() {
        KhoiTao();
        SocialNetworkAdapter socialNetworkAdapter = new SocialNetworkAdapter(this, R.layout.listview_item_row, list);
        listView.setAdapter(socialNetworkAdapter);
    }

    private void AnhXa() {
        listView = (ListView) findViewById(R.id.listviewSocial);
    }

    private void KhoiTao(){
        list.add(new SocialNetwork(R.drawable.facebook, "Facebook"));
        list.add(new SocialNetwork(R.drawable.linkedin, "Linkedin"));
        list.add(new SocialNetwork(R.drawable.msn, "MSN"));
        list.add(new SocialNetwork(R.drawable.skype, "Skype"));
        list.add(new SocialNetwork(R.drawable.yahoo, "Yahoo"));
    }
}
