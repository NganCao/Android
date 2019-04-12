package com.example.myapplication;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Base64;
import android.view.ContextMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

    ListView listView;
    ArrayList<CauThu> list = new ArrayList<>();
    Context context;
    int index = -1;
    int n, RESULT_EDIT =123;
    String[] nameCT = {"Gerard Piqué","Jaime Seoane","Oriol Busquets","Lionel Messi","Marcel Ovieira","Pele","Diego Maradona","Johan Cruft","Franz Beckenbauer","Michel Platini"};
    String[] teamCT ={"Barcelona","Real Madrid","Barcelona","Barcelona","Real Madrid","Santos","Argentinos Juniors","FC Barcelona","FC Bayern München","Juventus"};
    String[] rangeCT ={"13","12","13","13","14","15","17","16","13","18","11"};
    int[] imageCT = {R.drawable.gerardpique,R.drawable.jaimeseoane,R.drawable.oriolbusquets,R.drawable.messi,R.drawable.marcelovieira,R.drawable.a1,R.drawable.b1,R.drawable.c1,R.drawable.d1,R.drawable.a2};
    CauThuAdapter adapter;
    String nhan = "";

    public static final int REQUEST_CODE_EDIT=113;
    public static final int RESULT_CODE_SAVE1=115;
    public static final int RESULT_CODE_SAVE2=116;
//    Button btnInputData;
//    ArrayList<Integer>arrData=new ArrayList<Integer>();
//    ArrayAdapter<Integer> adapter=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        n = nameCT.length;

        setControl();
        setEvent();


    }

    private void setEvent() {
        KhoiTao();
        CauThuAdapter adapter = new CauThuAdapter(this, R.layout.item_row, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                index = position;
                CauThu cauThu = list.get(index);

                Toast.makeText(MainActivity.this, "Your choise is " + list.get(index).getName(), Toast.LENGTH_LONG).show();
                //encode image to base64 string
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imageCT[position]);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] imageBytes = baos.toByteArray();
                String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);

                String object = imageString + "-" + cauThu.getName()
                        + "-" + cauThu.getTeam() + "-" + cauThu.getHang();
                Intent intent = new Intent(MainActivity.this, ActivityInput.class);
                intent.putExtra("object", object);
                startActivityForResult(intent, REQUEST_CODE_EDIT);
            }
        });

        listView.setOnItemLongClickListener(new ItemLongClickRemove());
    }


    private class ItemLongClickRemove implements AdapterView.OnItemLongClickListener {

        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
            AlertDialog.Builder aBuilder = new AlertDialog.Builder(MainActivity.this);
            aBuilder.setTitle("Delete Player!");
            aBuilder.setMessage("Are you sure to delete this player ?");
            aBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    list.remove(position);
                    adapter.notifyDataSetChanged();
                }
            });
            aBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // không làm gì
                }
            });
            aBuilder.show();
            return true;
        }
    }

    /**
     * Xử lý kết quả trả về ở đây
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == REQUEST_CODE_EDIT){
            if (resultCode == RESULT_OK){
                nhan = intent.getStringExtra("stringTrave");
                String[] temp = nhan.split("-");
                Toast.makeText(MainActivity.this, "Update success!", Toast.LENGTH_LONG).show();
                list.get(index).setTeam(temp[0]);
                list.get(index).setHang(temp[1]);
                adapter.notifyDataSetChanged();
            }
            if (requestCode == RESULT_CANCELED){

            }
        }
    }

    private void setControl(){
//        btnInputData	=	(Button) findViewById(R.id.btnopenactivity);
        listView = (ListView) findViewById(R.id.listView);
    }

//    private void setEvent(){
////        btnInputData.setOnClickL
//        //đoạn code dưới này họcistener(new OnClickListener() {
//        ////
//        ////            @Override
//        ////            public void onClick(View v) {
//        ////                //Mở Activity với REQUEST_CODE_INPUT
//        ////                Intent	intent	=	new 	Intent(getApplicationContext(),ActivityInput.class);
//        ////                //gọi startActivityForResult
//        ////                startActivityForResult(intent, REQUEST_CODE_INPUT);
//        ////            }
//        ////        }); nhiều rồi, ko nói lại
//
//        KhoiTao();
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(context, "Bạn đã chọn " + list.get(position).getName(), Toast.LENGTH_SHORT).show();
//                index=position;
//                //encode image to base64 string
//                ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imageCT[position]);
//                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//                byte[] imageBytes = baos.toByteArray();
//                String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);
//
//                //Mở Activity với REQUEST_CODE_INPUT
//                Intent	intent	=	new 	Intent(getApplicationContext(),ActivityInput.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("imageString", imageString);
//                bundle.putString("name", nameCT[position]);
//                bundle.putString("team", teamCT[position]);
//                bundle.putString("range", rangeCT[position]);
//                intent.putExtras(bundle);
//                //gọi startActivityForResult
//                startActivityForResult(intent, REQUEST_CODE_EDIT);
//            }
//        });
//
////        listView=(ListView) findViewById(R.id.listView);
////        adapter=new ArrayAdapter<Integer>
////                (this,
////                        android.R.layout.simple_list_item_1,
////                        arrData);
////        listView.setAdapter(adapter);
//
//        CauThuAdapter adapter = new CauThuAdapter(this, R.layout.item_row, list);
//        listView.setAdapter(adapter);
//
//    }



    void KhoiTao(){
        for(int i = 0; i<n;i++){
            list.add(new CauThu(imageCT[i], nameCT[i] ,teamCT[i],rangeCT[i]));
        }
    }

}