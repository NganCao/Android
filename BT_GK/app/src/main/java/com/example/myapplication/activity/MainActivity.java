package com.example.myapplication.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.myapplication.R;

public class MainActivity extends AppCompatActivity {

    ImageButton btnLop, btnMonhoc;
    //ImageButton btnSV, btnDiem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setControl();
        setEvent();
    }

    public void setControl(){
        btnLop = (ImageButton) findViewById(R.id.btnLop);
        btnMonhoc = (ImageButton) findViewById(R.id.btnMH);
//        btnSV = (ImageButton) findViewById(R.id.btnSV);
//        btnDiem = (ImageButton) findViewById(R.id.btnDiem);
    }

    public void setEvent(){

        btnLop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LopActivity.class);
                startActivity(intent);
            }
        });

        btnMonhoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DiemActivity.class);
                startActivity(intent);
            }
        });
    }
}
