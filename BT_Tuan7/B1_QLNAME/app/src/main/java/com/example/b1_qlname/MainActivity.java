package com.example.b1_qlname;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editId, editName;
    Button btnLoad, btnAdd, btnDel, btnUpdate, btnFind;
    TextView textView;
    final DataHandler db = new DataHandler(this, null, null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setControl();
        setEvent();
    }

    public void setControl(){
        textView = (TextView) findViewById(R.id.txt);
        editId = (EditText) findViewById(R.id.editId);
        editName = (EditText) findViewById(R.id.editName);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnLoad = (Button) findViewById(R.id.btnLoad);
        btnDel = (Button) findViewById(R.id.btnDel);
        btnFind = (Button) findViewById(R.id.btnFind);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);

    }

    public void setEvent() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                add(v);
                db.addDataHandler(createStudent());
                editId.setText("");
                editName.setText("");
            }
        });
    }

    public void add(View view){
        DataHandler db = new DataHandler(this, null, null, 1);
        int id = Integer.parseInt(editId.getText().toString());
        String name = editName.getText().toString();
        Student student = new Student(id,name);
        db.addDataHandler(student);
        editId.setText("");
        editName.setText("");
    }

    private Student createStudent (){
        int id = Integer.parseInt(editId.getText().toString());
        String name = editName.getText().toString();
        Student student = new Student(id,name);
        return student;
    }
}
