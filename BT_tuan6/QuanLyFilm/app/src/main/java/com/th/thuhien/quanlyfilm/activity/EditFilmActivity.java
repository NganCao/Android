package com.th.thuhien.quanlyfilm.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.th.thuhien.quanlyfilm.R;

public class EditFilmActivity extends AppCompatActivity {

    LinearLayout layoutEdit;
    ImageView imgEdit;
    EditText edtTenTA, edtTenTV;
    RatingBar ratingBarEdit;
    Button btnOk, btnCancel;
    LinearLayout backgroundEditButton;

    float ratingReturn = (float) 1.0;
    //public static final String EXTRA_DATA = "EXTRA_DATA";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_film);

        AnhXa();
        setEvent();
        setEventButtonOk();
    }

    private void setEventButtonOk() {

    }

    private void setEvent() {

        // nhận dữ liệu gửi về
        String[] temp;
        Intent intent = getIntent();
        String data = intent.getStringExtra("object");
        temp = data.split("-");
        // set giá trị mặc định khi nhận về
        //int img = Integer.parseInt(temp[0]);
        //imgEdit.setImageResource(img);
        //imgEdit.setImageResource(Integer.parseInt(temp[0]));
        imgEdit.setImageResource(getIdByNameImage(temp[0]));
        edtTenTA.setText(temp[1], TextView.BufferType.EDITABLE);
        edtTenTV.setText(temp[2], TextView.BufferType.EDITABLE);
        ratingBarEdit.setRating(Integer.parseInt(temp[3]));

        // set con trỏ chuột nằm cuối edittext
        edtTenTA.setSelection(edtTenTA.getText().length());

        ratingBarEdit.setOnRatingBarChangeListener(new RatingBarChange());
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                traVeItem();
            }
        });
        btnCancel.setOnClickListener(new CancelClickListener());

    }

    class RatingBarChange implements RatingBar.OnRatingBarChangeListener {

        @Override
        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
            if (rating > 1.0){
                //ratingReturn = rating;
                //edtTenTA.setText((int) ratingBarEdit.getRating(), TextView.BufferType.EDITABLE);
                //Toast.makeText(EditFilmActivity.this, (int) rating, Toast.LENGTH_LONG).show();
            }else {
                ratingReturn = rating;
            }
        }
    }

    class CancelClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent returnIntent = new Intent();
            setResult(Activity.RESULT_CANCELED, returnIntent);
            finish();
        }
    }

    private void traVeItem(){

        String ratingGui = String.valueOf(ratingBarEdit.getRating());
        String data = edtTenTA.getText() + "-" + edtTenTV.getText() + "-" + ratingGui;
        Intent returnIntent = new Intent();
        returnIntent.putExtra("stringTrave", data);
        //returnIntent.putExtra("ratingEdit", ratingGui);

        setResult(RESULT_OK, returnIntent);
        finish();
//        Intent intent = new Intent(EditFilmActivity.this, MainActivity.class);
//        intent.putExtra("trave", data);
//        startActivity(intent);
//        finish();
    }
    private void AnhXa() {
        layoutEdit = (LinearLayout) findViewById(R.id.layoutEdit);
        imgEdit = (ImageView) findViewById(R.id.imageviewEdit);
        edtTenTA = (EditText) findViewById(R.id.edittextEditTenTA);
        edtTenTV = (EditText) findViewById(R.id.edittextEditTenTV);
        ratingBarEdit = (RatingBar) findViewById(R.id.ratingbar_danhgiaUpdate);
        btnOk = (Button) findViewById(R.id.buttonEditOk);
        btnCancel = (Button) findViewById(R.id.buttonEditCancel);
        backgroundEditButton = (LinearLayout) findViewById(R.id.backgroundEditButton);

        // set độ mờ
        layoutEdit.getBackground().setAlpha(70);
        backgroundEditButton.getBackground().setAlpha(90);


    }
    private int getIdByNameImage(String name){
        String pkgName = this.getPackageName();
        int resId = this.getResources().getIdentifier(name, "mipmap", pkgName);
        return resId;
    }
}
