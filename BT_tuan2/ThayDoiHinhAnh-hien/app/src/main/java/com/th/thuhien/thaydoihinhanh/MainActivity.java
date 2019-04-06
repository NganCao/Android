package com.th.thuhien.thaydoihinhanh;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    RadioGroup rdGroup;
    RadioButton rbBird, rbCat, rbDog, rbRabbit, rbPig;
    ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // anh xa
        rdGroup = (RadioGroup) findViewById(R.id.radioGroup);
        rbBird = (RadioButton) findViewById(R.id.rbtBird);
        rbCat = (RadioButton) findViewById(R.id.rbtCat);
        rbDog = (RadioButton) findViewById(R.id.rbtDog);
        rbRabbit = (RadioButton) findViewById(R.id.rbtRabbit);
        rbPig = (RadioButton) findViewById(R.id.rbtPig);
        imgView = (ImageView) findViewById(R.id.imageView);

        rdGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                doOnDifficultyLevelChanged(radioGroup,i);
            }
        });

    }
    private void doOnDifficultyLevelChanged(RadioGroup group, int checkedId) {
        int checkedRadioId = group.getCheckedRadioButtonId();
        if(checkedRadioId== R.id.rbtBird) {
            imgView.setImageResource(R.drawable.img_bird);
        } else if(checkedRadioId== R.id.rbtCat ) {
            imgView.setImageResource(R.drawable.img_cat);
        } else if(checkedRadioId== R.id.rbtDog) {
            imgView.setImageResource(R.drawable.img_dog);
        } else if(checkedRadioId== R.id.rbtRabbit) {
            imgView.setImageResource(R.drawable.img_rabbit);
        } else {
            imgView.setImageResource(R.drawable.img_pig);
        }
    }

}