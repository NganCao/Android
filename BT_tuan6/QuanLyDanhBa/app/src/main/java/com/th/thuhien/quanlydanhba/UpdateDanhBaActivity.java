package com.th.thuhien.quanlydanhba;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateDanhBaActivity extends AppCompatActivity {

    TextView txtDanhBaUpdate;
    EditText edt_nameUpdate, edt_phoneUpdate;
    Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_danh_ba);

        AnhXa();
        setEvent();
    }

    private void setEvent() {
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String phone = intent.getStringExtra("phone");

        //String[] temp = danhba.split("-");


        // set gia tri mac dinh duoc gui toi
        //edt_nameUpdate.setText(temp[0], TextView.BufferType.EDITABLE);
        txtDanhBaUpdate.setText(name + "-" + phone);
        edt_nameUpdate.setText(name, TextView.BufferType.EDITABLE);
        edt_phoneUpdate.setText(phone, TextView.BufferType.EDITABLE);
        edt_nameUpdate.setSelection(edt_nameUpdate.getText().length());
        //edt_nameUpdate.setText("Google is your friend.", TextView.BufferType.EDITABLE);
        //edt_nameUpdate.setText(name, TextView.BufferType.EDITABLE);
        //Toast.makeText(UpdateDanhBaActivity.this, temp[0], Toast.LENGTH_LONG).show();
        //edt_nameUpdate.setText("test");
        //edt_nameUpdate.setText("te");
//        edt_nameUpdate.setText(temp[0]);
//        edt_phoneUpdate.setText(temp[1]);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_nameUpdate.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Name không được rỗng", Toast.LENGTH_LONG).show();
                    return;
                }
                if (edt_phoneUpdate.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Phone không được rỗng", Toast.LENGTH_LONG).show();
                    return;
                }
                String trave = edt_nameUpdate.getText().toString() + "-" + edt_phoneUpdate.getText().toString();
                Intent returnIntent = new Intent();
                returnIntent.putExtra("stringTrave", trave);

                setResult(RESULT_OK, returnIntent);
                finish();
//                Intent intent1 = new Intent(UpdateDanhBaActivity.this, MainActivity.class);
//                intent1.putExtra("trave", edt_nameUpdate.getText().toString()+"-"+edt_phoneUpdate.getText().toString());
//                startActivity(intent1);
            }
        });
    }

    private void AnhXa() {
        txtDanhBaUpdate = (TextView) findViewById(R.id.textviewDanhBaUpdate);
        edt_nameUpdate = (EditText) findViewById(R.id.editUpdateName);
        edt_phoneUpdate = (EditText) findViewById(R.id.editUpdatePhone);
        btnUpdate = (Button) findViewById(R.id.buttonUpdate);

    }
}
