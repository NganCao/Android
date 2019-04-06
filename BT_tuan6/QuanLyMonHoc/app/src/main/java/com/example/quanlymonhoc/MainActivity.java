package com.example.quanlymonhoc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Monhoc> data = new ArrayList<>();
    MonhocAdapter adapter = null;
    Button btnInsert, btnDelete, btnSearch, btnUpdate;
    EditText mamh, tenmh, sotiet;
    int index = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setControl();
        setEvent();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Monhoc mh = data.get(position);
                index = position;
                mamh.setText(mh.getMa());
                tenmh.setText(mh.getTen());
                sotiet.setText(mh.getSotiet());
            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertMH();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteMH();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateMH();
            }
        });

    }

    private void setControl(){
        listView = (ListView) findViewById(R.id.listView);
        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        mamh = (EditText) findViewById(R.id.maMH);
        tenmh = (EditText) findViewById(R.id.tenMH);
        sotiet = (EditText) findViewById(R.id.Sotiet);
    }

    private void setEvent(){
        adapter = new MonhocAdapter(this, R.layout.item_row,data);
        listView.setAdapter(adapter);
    }

    private Monhoc getMonhoc(){
        Monhoc mh = new Monhoc();
        mh.setMa(mamh.getText().toString());
        mh.setTen(tenmh.getText().toString());
        mh.setSotiet(sotiet.getText().toString());
        return mh;
    }

    public void InsertMH(){
        Monhoc mh = getMonhoc();
        data.add(mh);
        adapter.notifyDataSetChanged();
    }

    public void DeleteMH(){
        if (index >=0){
            data.remove(index);
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Xoá thánh công", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Xóa không thành công",Toast.LENGTH_SHORT).show();
        }
    }

    public void updateMH(){
        String ma = mamh.getText().toString();
        String ten = tenmh.getText().toString();
        String st = sotiet.getText().toString();

        data.get(index).setMa(ma);
        data.get(index).setTen(ten);
        data.get(index).setSotiet(st);
        Toast.makeText(this, "Update success",Toast.LENGTH_SHORT).show();
        adapter.notifyDataSetChanged();
    }


}
