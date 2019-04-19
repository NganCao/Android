package com.example.quanlysinhvien.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.quanlysinhvien.R;

public class MainActivity extends AppCompatActivity {

    ImageButton btnMH, btnLop, btnSV, btnDiem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setControl();
        setEvent();
    }

    private void setControl(){
        btnLop = (ImageButton) findViewById(R.id.btnLop);
        btnSV = (ImageButton) findViewById(R.id.btnSV);
        btnMH = (ImageButton) findViewById(R.id.btnMH);
        btnDiem = (ImageButton) findViewById(R.id.btnDiem);
    }

    private void setEvent (){
        btnLop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "Onclicklistener function called.", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(MainActivity.this, LopActivity.class);
                startActivity(intent);
            }
        });

        btnSV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                startActivity(intent);
            }
        });

        btnMH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                startActivity(intent);
            }
        });

        btnDiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                startActivity(intent);
            }
        });
    }
}
