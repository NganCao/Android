package com.th.thuhien.plantshop.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.th.thuhien.plantshop.R;

public class KetquaDathangActivity extends AppCompatActivity {

    TextView txtname, txtemail, txtsdt, txtdiachi;
    Button btnOK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ketqua_dathang);

        setControl();
        setEvent();
    }

    private void setEvent() {

        Intent intent = this.getIntent();
        String name  = intent.getStringExtra("ten");
        String email = intent.getStringExtra("email");
        String sdt = intent.getStringExtra("sdt");
        String diachi = intent.getStringExtra("diachi");

        txtname.setText(name);
        txtemail.setText(email);
        txtsdt.setText(sdt);
        txtdiachi.setText(diachi);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(KetquaDathangActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    private void setControl() {
        txtname = (TextView) findViewById(R.id.txtEmail);
        txtemail = (TextView) findViewById(R.id.txtName);
        txtsdt = (TextView) findViewById(R.id.txtPhone);
        txtdiachi = (TextView) findViewById(R.id.txtDiachi);

        btnOK = (Button) findViewById(R.id.btnOK);
    }
}
