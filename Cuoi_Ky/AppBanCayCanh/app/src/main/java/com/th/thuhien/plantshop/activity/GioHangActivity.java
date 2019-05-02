package com.th.thuhien.plantshop.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.th.thuhien.plantshop.R;
import com.th.thuhien.plantshop.model.GioHang;

public class GioHangActivity extends AppCompatActivity {

    Button btnPrev, btnNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        setControl();
        setEvent();
    }

    private void setControl() {
        btnPrev = (Button) findViewById(R.id.btnPrev);
        btnNext = (Button) findViewById(R.id.btnNext);
    }

    private void setEvent(){
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent();
                finish();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GioHangActivity.this, CheckOut.class);
                startActivity(intent);
            }
        });
    }
}
