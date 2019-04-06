package com.th.thuhien.thongtincanhan;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txtTen, txtCmnd;
    RadioButton rbTrungcap, rbCaodang, rbDaihoc;
    CheckBox cbBao, cbSach, cbCode;
    EditText txtBosung;
    Button btnGui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTen = (EditText) findViewById(R.id.edittextTen);
        txtCmnd = (EditText) findViewById(R.id.edittextCMND);
        rbTrungcap = (RadioButton) findViewById(R.id.rbtn_trungcap);
        rbCaodang = (RadioButton) findViewById(R.id.rbtn_caodang);
        rbDaihoc = (RadioButton) findViewById(R.id.rbtn_daihoc);
        cbBao = (CheckBox) findViewById(R.id.cb_docbao);
        cbSach = (CheckBox) findViewById(R.id.cb_docsach);
        cbCode = (CheckBox) findViewById(R.id.cb_doccode);
        txtBosung = (EditText) findViewById(R.id.edittextBosung);
        btnGui = (Button) findViewById(R.id.buttonGui);

        btnGui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtTen.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Tên không được rỗng", Toast.LENGTH_LONG).show();
                    return;
                }
                if (txtTen.getText().toString().length() < 3){
                    Toast.makeText(MainActivity.this, "Tên >= 3 ký tự", Toast.LENGTH_LONG).show();
                    return;
                }
                if (txtCmnd.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "CMND không được rỗng", Toast.LENGTH_LONG).show();
                    return;
                }
                if (txtCmnd.getText().toString().length() != 9){
                    Toast.makeText(MainActivity.this, "CMND phải 9 chữ số", Toast.LENGTH_LONG).show();
                    return;
                }

                // kiem tra phan Bang Cap
                String BangCap = "";
                if (rbTrungcap.isChecked()){
                    BangCap = rbTrungcap.getText().toString();
                }else if(rbCaodang.isChecked()){
                    BangCap = rbCaodang.getText().toString();
                }else {
                    BangCap = rbDaihoc.getText().toString();
                }

                // kiem tra phan So Thich
                String SoThich = "";
                if (!cbBao.isChecked() && !cbSach.isChecked() && !cbCode.isChecked()){
                    Toast.makeText(MainActivity.this, "Phải chọn sở thích", Toast.LENGTH_LONG).show();
                    return;
                }
                if(cbBao.isChecked()){
                    SoThich += cbBao.getText().toString() + "\n";
                }
                if(cbSach.isChecked()){
                    SoThich += cbSach.getText().toString() + "\n";
                }
                if(cbCode.isChecked()){
                    SoThich += cbCode.getText().toString() + "\n";
                }

                // kiem tra phan Thong tin Them
                String BoSung = "";
                if(!txtBosung.getText().toString().equals("")){
                    BoSung = txtBosung.getText().toString();

                }

                Intent intent = new Intent(MainActivity.this, NhanThongTinActivity.class);
                intent.putExtra("name", txtTen.getText().toString());
                intent.putExtra("cmnd", txtCmnd.getText().toString());
                intent.putExtra("bangcap", BangCap.toString());
                intent.putExtra("sothich", SoThich.toString());
                intent.putExtra("bosung", BoSung.toString());
                startActivity(intent);

            }
        });


    }



}
