package com.th.thuhien.formatchu;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    CheckBox cb_Background, cb_TextColor, cb_Center;
    RadioButton rb_odd, rb_even, rb_both;
    Button btnResult;
    TextView txtKetQua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();

        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_Background.isChecked()){
                    txtKetQua.setBackgroundResource(R.color.colorBackground);
                }else {
                    txtKetQua.setBackgroundResource(0);
                }

                if (cb_TextColor.isChecked()){
                    txtKetQua.setTextColor(Color.RED);
                }else {
                    txtKetQua.setTextColor(Color.BLACK);
                }

                if (cb_Center.isChecked()){
                    txtKetQua.setGravity(Gravity.CENTER);
                }else {
                    txtKetQua.setGravity(Gravity.NO_GRAVITY);
                }

                if (rb_odd.isChecked()){
                    txtKetQua.setText("43");
                }else if (rb_even.isChecked()){
                    txtKetQua.setText("48");
                }else {
                    txtKetQua.setText("43,48");
                }
            }
        });
    }

    private void AnhXa() {
        cb_Background = (CheckBox) findViewById(R.id.checkboxBackground);
        cb_TextColor = (CheckBox) findViewById(R.id.checkboxTextColor);
        cb_Center = (CheckBox) findViewById(R.id.checkboxCenter);

        rb_odd = (RadioButton) findViewById(R.id.radiobuttonOdd);
        rb_even = (RadioButton) findViewById(R.id.radiobuttonEven);
        rb_both = (RadioButton) findViewById(R.id.radiobuttonBoth);

        btnResult = (Button) findViewById(R.id.buttonResult);
        txtKetQua = (TextView) findViewById(R.id.textviewKetQua);
    }
}
