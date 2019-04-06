package com.th.thuhien.thongtincanhan;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class NhanThongTinActivity extends AppCompatActivity {

    TextView tvTen, tvCmnd, tvBangCap, tvSoThich, tvBoSung;
    Button btnDong;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhan_thong_tin);

        // anh xa
        tvTen = (TextView) findViewById(R.id.textTen);
        tvCmnd = (TextView) findViewById(R.id.textCmnd);
        tvBangCap = (TextView) findViewById(R.id.textBangCap);
        tvSoThich = (TextView) findViewById(R.id.textSoThich);
        tvBoSung = (TextView) findViewById(R.id.textBoSung);
        btnDong = (Button) findViewById(R.id.buttonDong);

        Intent intent = getIntent();
        tvTen.setText(intent.getStringExtra("name"));
        tvCmnd.setText(intent.getStringExtra("cmnd"));
        tvBangCap.setText(intent.getStringExtra("bangcap"));
        tvSoThich.setText(intent.getStringExtra("sothich"));
        tvBoSung.setText(intent.getStringExtra("bosung"));

        btnDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
