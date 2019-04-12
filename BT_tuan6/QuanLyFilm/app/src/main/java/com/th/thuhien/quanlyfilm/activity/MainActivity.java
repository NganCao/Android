package com.th.thuhien.quanlyfilm.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.th.thuhien.quanlyfilm.model.Film;
import com.th.thuhien.quanlyfilm.adapter.FilmAdapter;
import com.th.thuhien.quanlyfilm.R;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    LinearLayout linearLayout;
    TextView txtTieuDe;
    ListView lv_film;
    TextView txtTotal;
    ArrayList<Film> data = new ArrayList<>();;
    FilmAdapter adapter;
    Film selectContact = null;
    int indexSelected = -1;

    String nhan = "";

    private static final int RESULT_EDIT = 0x9345;
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

    }

    private void setEvent() {
        lv_film.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                indexSelected = position;
                Film film = data.get(indexSelected);

                String textRating = String.valueOf(film.getRating());
                //Toast.makeText(MainActivity.this, indexSelected, Toast.LENGTH_LONG).show();
                String object = film.getImg() + "-" + film.getTenTA()
                        + "-" + film.getTenTV() + "-" + textRating;
                Intent intent = new Intent(MainActivity.this, EditFilmActivity.class);
                intent.putExtra("object", object);
                startActivityForResult(intent, REQUEST_CODE_EDIT);


                //finish();
                //nhận giá trị khi edit
//                Intent intent1 = getIntent();
//                 nhan = intent1.getStringExtra("trave");
////                Toast.makeText(MainActivity.this, data, Toast.LENGTH_LONG).show();
//                //String[] temp = nhan.split("-");
//
//                data.get(index).setTenTA(nhan);
                //adapter.clear();;
                //adapter.notifyDataSetChanged();
//                data.get(index).setTenTV(temp[2]);
//                data.get(index).setRating(Integer.parseInt(temp[3]));
//                adapter.notifyDataSetChanged();
//                lv_film.invalidateViews();
                //String wordClicked = ((TextView) view).getText().toString();

                /////  add following line
//                adapter.notifyDatasetChanged();
            }
        });

        lv_film.setOnItemLongClickListener(new ItemLongClickRemove());

        txtTotal.setText("Total: " + data.size());


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
                    data.remove(position);
                    adapter.notifyDataSetChanged();
                    txtTotal.setText("Total: " + data.size());
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



//    private void updateDanhBa(String nhan){
//
//        String[] temp = nhan.split("-");
//        //Toast.makeText(MainActivity.this, temp[0] + "-" + temp[1], Toast.LENGTH_LONG).show();
//        data.get(indexSelected).setImg(data.get(indexSelected).getImg());
//        data.get(indexSelected).setTenTA(temp[1]);
//        data.get(indexSelected).setTenTV(temp[2]);
//        data.get(indexSelected).setRating(Integer.parseInt(temp[3]));
//        adapter.notifyDataSetChanged();
//    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == REQUEST_CODE_EDIT){
            if (resultCode == RESULT_OK){
                nhan = intent.getStringExtra("stringTrave");
                //String ratingNhan = intent.getStringExtra("ratingEdit");
                //updateDanhBa(nhan);
                String[] temp = nhan.split("-");
                float ratingnhan = Float.parseFloat(temp[2]);
                //Toast.makeText(MainActivity.this, (int) ratingnhan + 1, Toast.LENGTH_LONG).show();
                //data.get(indexSelected).setImg(data.get(indexSelected).getImg());
                data.get(indexSelected).setTenTA(temp[0]);
                data.get(indexSelected).setTenTV(temp[1]);
                data.get(indexSelected).setRating((int) (ratingnhan+0));
                adapter.notifyDataSetChanged();
            }
            if (requestCode == RESULT_CANCELED){

            }
        }
    }

    private void AnhXa() {

        linearLayout = (LinearLayout) findViewById(R.id.layout);
        txtTieuDe = (TextView) findViewById(R.id.textviewTieuDe);
        lv_film = (ListView) findViewById(R.id.listviewFilm);
        txtTotal = (TextView) findViewById(R.id.textviewTotal);

        // set độ mờ của background của cả màn hình
        linearLayout.getBackground().setAlpha(70);

        // set độ mờ của background của tiêu đề
        txtTieuDe.getBackground().setAlpha(90);


        data = KhoiTao();
        adapter = new FilmAdapter(this, R.layout.listview_film_row, data);
        lv_film.setAdapter(adapter);
    }

    private ArrayList<Film> KhoiTao(){
        ArrayList<Film> list = new ArrayList<>();

        Film film1 = new Film("film_supergirl", "Supergirl", "Nữ siêu nhân", 3);
        Film film2 = new Film("bird_box", "Bird box", "Lồng chim", 2);
        Film film3 = new Film("peppermint", "Peppermint", "Thiên thần công lý", 1);
        Film film4 = new Film("searching", "Searching", "Truy tìm tung tích ảo", 4);
        Film film5 = new Film("the_expanse", "The expanse", "Cuộc mở rộng", 5);

        list.add(film1);
        list.add(film2);
        list.add(film3);
        list.add(film4);
        list.add(film5);

        return list;
    }
}
