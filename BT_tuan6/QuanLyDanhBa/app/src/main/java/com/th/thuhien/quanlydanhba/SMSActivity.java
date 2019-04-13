package com.th.thuhien.quanlydanhba;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SMSActivity extends AppCompatActivity {

    TextView txtPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        AnhXa();
        Intent intent = getIntent();
        txtPhone.setText("Send to: " + intent.getStringExtra("phone"));
    }

    private void AnhXa() {
        txtPhone = (TextView) findViewById(R.id.textviewPhone);
    }
}
