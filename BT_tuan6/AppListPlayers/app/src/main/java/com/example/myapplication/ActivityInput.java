package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class ActivityInput extends Activity {

    ImageView iconCT;
    Context context;
    Button btnOK,btnCancle;
    EditText hangEdit, teamEdit;
    TextView  tenCT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        setControl();
        setEvent();

//        btnOK.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                sendToMain(MainActivity.RESULT_CANCELED);
//            }
//        });
//        btnCancle.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//
//                sendToCancle(MainActivity.RESULT_CODE_SAVE2);
//            }
//        });
    }

    private void setControl(){
        iconCT = (ImageView) findViewById(R.id.icon);
        tenCT	=	(TextView)	findViewById(R.id.nameCT);
        hangEdit	=	(EditText)	findViewById(R.id.hangCT);
        teamEdit	=	(EditText)	findViewById(R.id.teamCT);
        btnOK	=	(Button) findViewById(R.id.btnOK);
        btnCancle	=	(Button) findViewById(R.id.btnCancle);
    }

    private void setEvent() {

        // nhận dữ liệu gửi về
        String[] temp;
        Intent intent = getIntent();
        String data = intent.getStringExtra("object");
        temp = data.split("-");

        String img = temp[0];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] imageBytes = baos.toByteArray();

        //decode base64 string to image
        imageBytes = Base64.decode(img, Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);

        iconCT.setImageBitmap(decodedImage);
        tenCT.setText(temp[1], TextView.BufferType.EDITABLE);
        teamEdit.setText(temp[2], TextView.BufferType.EDITABLE);
        hangEdit.setText(temp[3], TextView.BufferType.EDITABLE);

        // set con trỏ chuột nằm cuối edittext
        teamEdit.setSelection(teamEdit.getText().length());
        hangEdit.setSelection(hangEdit.getText().length());

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendItem();
            }
        });
        btnCancle.setOnClickListener(new CancelClickListener());

    }

    class CancelClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent returnIntent = new Intent();
            setResult(Activity.RESULT_CANCELED, returnIntent);
            finish();
        }
    }

    private void sendItem(){
        String data = teamEdit.getText() + "-" + hangEdit.getText();
        Intent returnIntent = new Intent();
        returnIntent.putExtra("stringTrave", data);

        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
//
//    public void sendToMain(int resultcode)
//    {
////        Intent intent=getIntent();
//        Intent send = new Intent();
////        String doi = teamCT.getText();
//        String s = teamEdit.getText() + "-" + hangEdit.getText();
//        send.putExtra("String", s);
//        setResult(resultcode, send);
//        finish();
//    }
//
//    public void sendToCancle(int resultcode)
//    {
////        Intent intent=getIntent();
//        Intent send = new Intent();
//        setResult(resultcode, send);
//        finish();
//    }
//
//    public void getData(){
//        Intent rep = getIntent();
//
//        String imagei = rep.getExtras().getString("imageString");
//        String namei = rep.getExtras().getString("name");
//        String teami = rep.getExtras().getString("team");
//        String rangei = rep.getExtras().getString("range");
//
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        byte[] imageBytes = baos.toByteArray();
//
//        //decode base64 string to image
//        imageBytes = Base64.decode(imagei, Base64.DEFAULT);
//        Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
//        iconCT.setImageBitmap(decodedImage);
//        tenCT.setText(namei);
//        hangEdit.setText(rangei);
//        teamEdit.setText(teami);
//    }

}
