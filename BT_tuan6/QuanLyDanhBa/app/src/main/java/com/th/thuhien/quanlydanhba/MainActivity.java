package com.th.thuhien.quanlydanhba;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText edt_name, edt_phone;
    Button btnSave;
    ListView lv_danhba;
    ArrayList<DanhBa> data;
    ArrayAdapter<DanhBa> adapter;

    int index = -1;
    DanhBa selectContact = null;

    int REQUEST_CODE_EDIT = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();
        setEvent();


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu,menu);
        menu.setHeaderTitle("Call - Sms");
        menu.getItem(0).setTitle("Call to " + selectContact.getPhone(); + "-đợi chuyển MH");
        menu.getItem(1).setTitle("Send sms to " + selectContact.getPhone() + "-đợi chuyển MH");
        menu.getItem(2).setTitle("Edit to this contact");
    }



    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch(item.getItemId())
        {
            case R.id.mnuCall:
                doMakeCall();
                break;
            case R.id.mnuSms:
                doMakeSms();
                break;
            case R.id.mnuUpdate:
                updateDanhBa();
                break;
            case R.id.mnuRemove:
                data.remove(selectContact);
                adapter.notifyDataSetChanged();
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void updateDanhBa() {
        String name = selectContact.getName();
        String phone = selectContact.getPhone();
        String danhba = name + "-" + phone;
        //Toast.makeText(MainActivity.this, danhba, Toast.LENGTH_LONG).show();

        Intent intent = new Intent(MainActivity.this, UpdateDanhBaActivity.class);
        //DanhBa danhBa = new DanhBa(name, phone);
        intent.putExtra("name", name);
        intent.putExtra("phone", phone);
        startActivityForResult(intent, REQUEST_CODE_EDIT);

//        Intent intent1 = getIntent();
//        String trave = intent1.getStringExtra("trave");
//
//        String[] temp = trave.split("-");
//
//        data.get(index).setName(temp[0]);
//        data.get(index).setPhone(temp[1]);
//
//        adapter.notifyDataSetChanged();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == REQUEST_CODE_EDIT){
            if (resultCode == RESULT_OK){
                String stringReturn = intent.getStringExtra("stringTrave");
                String[] temp = stringReturn.split("-");

                data.get(index).setName(temp[0]);
                data.get(index).setPhone(temp[1]);
                adapter.notifyDataSetChanged();
            }
            if (resultCode == RESULT_CANCELED){

            }
        }
    }

    private void doMakeSms() {
//        Intent intent = new Intent(MainActivity.this, SMSActivity.class);
//        intent.putExtra("phone",selectContact.getPhone());
//        startActivity(intent);

        Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("sms: "+selectContact.getPhone()));
        startActivity(intent1);
    }

    private void doMakeCall() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:"+selectContact.getPhone()));
        startActivity(intent);
        //finish();
    }

    private void setEvent() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_name.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Name không được rỗng", Toast.LENGTH_LONG).show();
                    return;
                }
                if (edt_phone.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Phone không được rỗng", Toast.LENGTH_LONG).show();
                    return;
                }
                insertDanhBa();
                edt_name.setText("");
                edt_phone.setText("");
            }
        });
        lv_danhba.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                index = position;
                selectContact = data.get(position);
                return false;
            }
        });
    }

    private void AnhXa() {
        edt_name = (EditText) findViewById(R.id.edittextName);
        edt_phone = (EditText) findViewById(R.id.edittextPhone);
        btnSave = (Button) findViewById(R.id.butoonSave);
        lv_danhba = (ListView) findViewById(R.id.listviewDanhBa);
        data = new ArrayList<>();
        data = KhoiTao();
        adapter = new ArrayAdapter<DanhBa>(this, android.R.layout.simple_list_item_1, data);
        lv_danhba.setAdapter(adapter);
        registerForContextMenu(lv_danhba);
    }

    private DanhBa getDanhBa(){
        DanhBa danhBa = new DanhBa();
        danhBa.setName(edt_name.getText() + "");
        danhBa.setPhone(edt_phone.getText() + "");
        return danhBa;
    }

    private void insertDanhBa(){
        DanhBa danhBa = getDanhBa();
        data.add(danhBa);
        adapter.notifyDataSetChanged();
    }

    private ArrayList<DanhBa> KhoiTao(){
        ArrayList<DanhBa> list = new ArrayList<>();

        DanhBa danhBa1 = new DanhBa("Teo", "5554");
        DanhBa danhBa2 = new DanhBa("Ty", "5556");
        DanhBa danhBa3 = new DanhBa("DrThanh", "0987773601");
        DanhBa danhBa4 = new DanhBa("Miss Nuong", "0987773603");

        list.add(danhBa1);
        list.add(danhBa2);
        list.add(danhBa3);
        list.add(danhBa4);

        return list;
    }
}
