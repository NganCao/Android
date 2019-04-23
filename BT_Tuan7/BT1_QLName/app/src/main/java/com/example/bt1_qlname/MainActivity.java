package com.example.bt1_qlname;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.security.PublicKey;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editId, editName;
    Button btnLoad, btnAdd, btnDel, btnUpdate, btnFind;
    TextView textView;
    final DB db = new DB(this, null, null, 1);

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

    public void setEvent(){
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.addData(createStudent());
                editId.setText("");
                editName.setText("");
            }
        });

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load(v);
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(v);
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update(v);
            }
        });

        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findAll(v);
            }
        });
    }

    public void add(View view){
        DB db = new DB(this, null, null, 1);
        int id = Integer.parseInt(editId.getText().toString());
        String name = editName.getText().toString();
        Student student = new Student(id,name);
        db.addData(student);
        editId.setText("");
        editName.setText("");
    }

    private Student createStudent (){
        int id = Integer.parseInt(editId.getText().toString());
        String name = editName.getText().toString();
        Student student = new Student(id,name);
        return student;
    }

    public void load(View view){
        DB db = new DB(this, null, null, 1);
        textView.setText(db.loadData());
        editId.setText("");
        editName.setText("");
    }

    public void delete(View view){
        DB db = new DB(this, null, null, 1);
        boolean result = db.delData(Integer.parseInt(
                editId.getText().toString()));
        if (result) {
            editId.setText("");
            editName.setText("");
            textView.setText("Student Deleted");
        } else
            editId.setText("No Match Found");
    }

    public void update(View view){
        DB db = new DB(this, null, null, 1);
        boolean result = db.updateData(Integer.parseInt(
                editId.getText().toString()), editName.getText().toString());
        if (result) {
            editId.setText("");
            editName.setText("");
            textView.setText("Student Updated");
        } else
            editId.setText("No Match Found");
    }

    public void find(View view) {
        DB db = new DB(this, null, null, 1);
        Student student =
                db.findFisrtData(editName.getText().toString());
        if (student != null) {
            textView.setText(String.valueOf(student.getId())
                    + " " + student.getName()
                    + System.getProperty("line.separator"));
            editId.setText("");
            editName.setText("");
        } else {
            textView.setText("No Match Found");
            editId.setText("");
            editName.setText("");
        }
    }

    public void findAll(View view) {
        DB db = new DB(this, null, null, 1);
        List<Student> lst =
                db.findAllData(editName.getText().toString());
        String studentsList = "";
        if (!lst.isEmpty()) {
            for(Student st:lst)
            {
                studentsList += String.valueOf(st.getId())
                        + " " + st.getName()
                        + System.getProperty("line.separator");
                editId.setText("");
                editName.setText("");
            }
            textView.setText(studentsList);
        } else {
            textView.setText("No Match Found");
            editId.setText("");
            editName.setText("");
        }
    }

}
