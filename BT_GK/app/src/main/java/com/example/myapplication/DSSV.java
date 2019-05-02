package com.example.myapplication;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class DSSV extends AppCompatActivity {

    ListView listView;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dssv);

        setControl();
        setEvent();
    }

    public void setControl(){
        listView = (ListView) findViewById(R.id.listView);
        btnBack = (Button) findViewById(R.id.btnBack);
    }

    public void setEvent(){
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                return false;
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(DSSV.this, MainActivity.class);
////                startActivity(intent);
                showDialog();
            }
        });
    }

    public void showDialog(){
        Dialog dialog = new Dialog(DSSV.this);
        dialog.setTitle("Sửa sinh viên");
        dialog.setContentView(R.layout.activity_edit_sv);
        dialog.show();
    }
}
