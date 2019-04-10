package com.example.quanlycauthu;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class chitiet_cauthu extends AppCompatActivity {

    ImageView icon;
    TextView name;
    EditText hang, team;
    Button btnOK, btnCancle;
    int position;
    private ListView listView;
    ArrayList<CauThu> data = new ArrayList<>();
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiet_cauthu);
        context = this;

        setControl();
        setEvent();
    }

    private void setControl(){
        icon = (ImageView) findViewById(R.id.icon);
        name = (TextView) findViewById(R.id.nameCT);
        hang = (EditText) findViewById(R.id.hangCT);
        team = (EditText) findViewById(R.id.teamCT);
        btnCancle = (Button) findViewById(R.id.btnCancle);
        btnOK = (Button) findViewById(R.id.btnOK);
    }

    private void setEvent(){

        Intent intent = getIntent();
        String imagei = intent.getExtras().getString("imageString");
        String namei = intent.getExtras().getString("name");
        String teami = intent.getExtras().getString("team");
        String rangei = intent.getExtras().getString("range");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] imageBytes = baos.toByteArray();

        //decode base64 string to image
        imageBytes = Base64.decode(imagei, Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        icon.setImageBitmap(decodedImage);
        name.setText(namei);
        hang.setText(rangei);
        team.setText(teami);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(chitiet_cauthu.this, MainActivity.class);
                Bundle bundle = new Bundle();

                String key = "100";
                String hangi = hang.getText().toString();
                String teami = team.getText().toString();

                bundle.putString("key",key);
                bundle.putString("h", hangi);
                bundle.putString("t", teami);
                intent1.putExtras(bundle);
                startActivity(intent1);
                finish();
            }
        });

                btnCancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
//                        setResult(MainActivity.RESULT_CANCELED, intent);
                        startActivity(intent);
                        finish();
                    }
                });

    }
}
