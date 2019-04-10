package com.example.quanlysanpham;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    EditText editId, editTen;
    Button btnAdd;
    ListView listView;
    //cặp đối tượng dùng cho Spinner
    ArrayList<Menu> arraySpinner=new ArrayList<Menu>();
    ArrayAdapter<Menu> adapterSpinner=null;
    //Cặp đối tượng dùng cho ListView
    ArrayList<Product> arrayListview=new ArrayList<Product>();
    ArrayAdapter<Product> adapterListview=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setControl();
        setEvent();
    }

    private void setControl(){
        listView = (ListView) findViewById(R.id.listView);
        spinner = (Spinner) findViewById(R.id.spin);
        editId = (EditText) findViewById(R.id.maSP);
        editTen = (EditText) findViewById(R.id.tenSP);
        btnAdd = (Button) findViewById(R.id.btnAdd);
    }

    private void setEvent(){
        adapterSpinner = new ArrayAdapter<Menu>(this,android.R.layout.simple_spinner_dropdown_item, arraySpinner);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterSpinner);

        adapterListview = new ArrayAdapter<Product>(this,android.R.layout.simple_list_item_1, arrayListview);
        listView.setAdapter(adapterListview);

        KhoiTao();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loadList(arraySpinner.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSPtheoMenu();
            }
        });
    }

    private void addSPtheoMenu(){
        Product p = new Product();
        p.setId(editId.getText()+"");
        p.setName(editTen.getText()+"");
        Menu mn = (Menu) spinner.getSelectedItem();
        mn.addProduct(p);
        //them xong update lai listview
        loadList(mn);
    }

    private void loadList(Menu mn){
        //xoa cu
        arrayListview.clear();
        //lay new
        arrayListview.addAll(mn.getListSP());
        //UPDATE
        adapterListview.notifyDataSetChanged();
    }

    private void KhoiTao(){
        Menu menu1 = new Menu("1", "SamSung");
        Menu menu2 = new Menu("2", "Iphone");
        Menu menu3 = new Menu("3", "Ipad");

        arraySpinner.add(menu1);
        arraySpinner.add(menu2);
        arraySpinner.add(menu3);
        adapterSpinner.notifyDataSetChanged();
    }
}
