package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    ImageButton btnLop, btnMonhoc, btnSV, btnDiem;
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
        btnSV = (ImageButton) findViewById(R.id.btnSV);
        btnDiem = (ImageButton) findViewById(R.id.btnDiem);
    }

    public void setEvent(){

        btnLop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnMonhoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnSV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DSSV.class);
                startActivity(intent);
            }
        });

        btnDiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DSDiem.class);
                startActivity(intent);
            }
        });
    }
}
