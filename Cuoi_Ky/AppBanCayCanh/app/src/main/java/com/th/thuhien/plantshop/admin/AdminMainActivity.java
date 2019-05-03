package com.th.thuhien.plantshop.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.th.thuhien.plantshop.R;

public class AdminMainActivity extends AppCompatActivity {

    ImageButton btn_AdminMenu, btn_AdminSanPham;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        AnhXa();
        EventButtonMenu();
    }

    private void EventButtonMenu() {
        btn_AdminMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminMainActivity.this, AdminMenuActivity.class);
                startActivity(intent);
            }
        });
    }

    private void AnhXa() {
        btn_AdminMenu = (ImageButton) findViewById(R.id.imgbuttonAdminMenu);
        btn_AdminSanPham = (ImageButton) findViewById(R.id.imgbuttonAdminSanPham);
    }
}
