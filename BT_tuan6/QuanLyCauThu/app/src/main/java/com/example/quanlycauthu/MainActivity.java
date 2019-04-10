package com.example.quanlycauthu;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    ArrayList<CauThu> data = new ArrayList<>();
    private Context context;
    EditText name, team, range;
    ImageView icon;
    int index = -1;
    int n, RESULT_EDIT =123;
    String[] nameCT = {"Gerard Piqué","Jaime Seoane","Oriol Busquets","Lionel Messi","Marcel Ovieira","Pele","Diego Maradona","Johan Cruft","Franz Beckenbauer","Michel Platini"};
    String[] teamCT ={"Barcelona","Real Madrid","Barcelona","Barcelona","Real Madrid","Santos","Argentinos Juniors","FC Barcelona","FC Bayern München","Juventus"};
    String[] rangeCT ={"13","12","13","13","14","15","17","16","13","18","11"};
    int[] imageCT = {R.drawable.gerardpique,R.drawable.jaimeseoane,R.drawable.oriolbusquets,R.drawable.messi,R.drawable.marcelovieira,R.drawable.a1,R.drawable.b1,R.drawable.c1,R.drawable.d1,R.drawable.a2};
    CauThuAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        n = nameCT.length;

        setControl();
        setEvent();
    }

    private void setControl(){
        listView = (ListView) findViewById(R.id.listView);

    }

    private void setEvent(){
        KhoiTao();

        CauThuAdapter adapter = new CauThuAdapter(this, R.layout.item_row, data);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                view.setSelected(true);
                Toast.makeText(context,"Bạn đã chọn "+data.get(position).getName().toString(),Toast.LENGTH_LONG).show();
                index=position;
                //encode image to base64 string
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imageCT[position]);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] imageBytes = baos.toByteArray();
                String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);

                Intent intent = new Intent(MainActivity.this, chitiet_cauthu.class);
                Bundle bundle = new Bundle();
                bundle.putString("imageString", imageString);
                bundle.putString("name", nameCT[position]);
                bundle.putString("team", teamCT[position]);
                bundle.putString("range", rangeCT[position]);
                intent.putExtras(bundle);
                startActivityForResult(intent, RESULT_EDIT);
                finish();
            }
        });
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent i) {
//        super.onActivityResult(requestCode, resultCode, i);
//
//        resultCode = Integer.parseInt(i.getExtras().getString("key"));
//
//        if (requestCode == RESULT_EDIT ){
//            if (resultCode == 100){
//                String h = i.getExtras().getString("hangi");
//                String r = i.getExtras().getString("namei");
//
//                data.get(index).setHang(h);
//                data.get(index).setTeam(r);
//
//                Toast.makeText(this, "Thành Công!", Toast.LENGTH_SHORT).show();
//                adapter.notifyDataSetChanged();
//            }
//        }
//
//    }


//    @Override
//    protected void onResume() {
//        super.onResume();
//        reLoad();
//    }
//
//    private void reLoad(){
//        Intent i = getIntent();
//        String h = i.getExtras().getString("h");
//        String t = i.getExtras().getString("t");
//
//                data.get(index).setHang(h);
//                data.get(index).setTeam(t);
//
//                Toast.makeText(this, "Thành Công!", Toast.LENGTH_SHORT).show();
//                adapter.notifyDataSetChanged();
//    }

    void KhoiTao(){
        for(int i = 0; i<n;i++){
            data.add(new CauThu(imageCT[i], nameCT[i] ,teamCT[i],rangeCT[i]));
        }
    }
}
