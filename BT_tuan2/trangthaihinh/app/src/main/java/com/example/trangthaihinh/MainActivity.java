package com.example.trangthaihinh;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    CheckBox cb1,cb2,cb3,cb4;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cb1 = (CheckBox) findViewById(R.id.cbkhicau);
        cb2 = (CheckBox) findViewById(R.id.cbmay);
        cb3 = (CheckBox) findViewById(R.id.cbmua);
        cb4 = (CheckBox) findViewById(R.id.cbo);
        iv = (ImageView) findViewById(R.id.imagev);

//
//        if(checked1){
//            iv.setImageResource(R.drawable.h1);
//        }
//          if (checked2){
//            iv.setImageResource(R.drawable.h2);
//        }
//          if (checked3){
//            iv.setImageResource(R.drawable.h3);
//        }
//          if (checked4){
//            iv.setImageResource(R.drawable.h4);
//        }
//          if (checked1 && checked2){
//            iv.setImageResource(R.drawable.h12);
//        }
//          if (checked1 && checked3){
//            iv.setImageResource(R.drawable.h13);
//        }
//          if (checked1 && checked4){
//            iv.setImageResource(R.drawable.h14);
//        }
//          if (checked2 && checked3){
//            iv.setImageResource(R.drawable.h23);
//        }
//          if (checked2 && checked4){
//            iv.setImageResource(R.drawable.h24);
//        }
//          if (checked3 && checked4){
//            iv.setImageResource(R.drawable.h34);
//        }
//          if (checked2 && checked1 && checked3){
//            iv.setImageResource(R.drawable.h123);
//        }
//          if (checked2 && checked1 && checked4){
//            iv.setImageResource(R.drawable.h124);
//        }
//          if (checked3 && checked1 && checked4){
//            iv.setImageResource(R.drawable.h134);
//        }
//          if (checked2  && checked3 && checked4){
//            iv.setImageResource(R.drawable.h234);
//        }
//          if (checked2 && checked4 && checked1 && checked3){
//            iv.setImageResource(R.drawable.h5);
//        }
//        else{
//            iv.setImageResource(R.drawable.h0);
//        }

    }

    public void onCheckboxClicked(View view) {


        boolean checked1 = cb1.isChecked();
        boolean checked2 = cb2.isChecked();
        boolean checked3 = cb3.isChecked();
        boolean checked4 = cb4.isChecked();

        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();
//
//        // Check which checkbox was clicked
//        switch(view.getId()) {
//            case R.id.cbkhicau:
//                if (checked){
//                    this.iv.setImageResource(R.drawable.h234);
//                }
//            else
//                    this.iv.setImageResource(R.drawable.h0);
//                break;
//            case R.id.cbmay:
//                if (checked){
//                    this.iv.setImageResource(R.drawable.h134);
//                }
//            else
//                    this.iv.setImageResource(R.drawable.h0);
//                break;
//            case R.id.cbmua:
//                if (checked){
//                    this.iv.setImageResource(R.drawable.h124);
//                }
//                else
//                    this.iv.setImageResource(R.drawable.h0);
//                break;
//            case R.id.cbo:
//                if (checked){
//                    this.iv.setImageResource(R.drawable.h123);
//                }
//                else
//                    this.iv.setImageResource(R.drawable.h0);
//                break;
//            case R.id.cbo + R.id.cbmay:
//                if (checked){
//                    this.iv.setImageResource(R.drawable.h123);
//                }
//                else
//                    this.iv.setImageResource(R.drawable.h0);
//                break;
//
//        }

//        if(checked1){
//            this.iv.setImageResource(R.drawable.h1);
//        }
//          else if (checked2){
//            this.iv.setImageResource(R.drawable.h2);
//        }
//          else if (checked3){
//            this.iv.setImageResource(R.drawable.h3);
//        }
//          else if (checked4){
//            this.iv.setImageResource(R.drawable.h4);
//        }
//          else if (checked1 && checked2){
//            this.iv.setImageResource(R.drawable.h12);
//        }
//          else if (checked1 && checked3){
//            this.iv.setImageResource(R.drawable.h13);
//        }
//          else if (checked1 && checked4){
//            this.iv.setImageResource(R.drawable.h14);
//        }
//          else if (checked2 && checked3){
//            this.iv.setImageResource(R.drawable.h23);
//        }
//          else if (checked2 && checked4){
//            this.iv.setImageResource(R.drawable.h24);
//        }
//          else if (checked3 && checked4){
//            this.iv.setImageResource(R.drawable.h34);
//        }
//          else if (checked2 && checked1 && checked3){
//            this.iv.setImageResource(R.drawable.h123);
//        }
//          else if (checked2 && checked1 && checked4){
//            this.iv.setImageResource(R.drawable.h124);
//        }
//          else if (checked3 && checked1 && checked4){
//            this.iv.setImageResource(R.drawable.h134);
//        }
//          else if (checked2  && checked3 && checked4){
//            this.iv.setImageResource(R.drawable.h234);
//        }
//          else if (checked2 && checked4 && checked1 && checked3){
//            this.iv.setImageResource(R.drawable.h5);
//        }
//        else{
//            iv.setImageResource(R.drawable.h0);
//        }

        if(checked1){
            if (checked2){
                if (checked3){
                    if (checked4){
                        this.iv.setImageResource(R.drawable.h5);
                    }else{
                        this.iv.setImageResource(R.drawable.h4);
                    }
                }else{
                    if (checked4){
                        this.iv.setImageResource(R.drawable.h3);
                    }else{
                        this.iv.setImageResource(R.drawable.h34);
                    }
                }
            }else{
                if (checked3){
                    if (checked4){
                        this.iv.setImageResource(R.drawable.h2);
                    }else{
                        this.iv.setImageResource(R.drawable.h24);
                    }
                }else {
                    if (checked4){
                        this.iv.setImageResource(R.drawable.h23);
                    }else{
                        this.iv.setImageResource(R.drawable.h234);
                    }
                }
            }
        }else{
            if(checked2){
                if (checked3){
                    if (checked4){
                        this.iv.setImageResource(R.drawable.h1);
                    }else{
                        this.iv.setImageResource(R.drawable.h14);
                    }
                }else{
                    if (checked4){
                        this.iv.setImageResource(R.drawable.h13);
                    }else{
                        this.iv.setImageResource(R.drawable.h134);
                    }
                }
            }else{
                if (checked3){
                    if (checked4){
                        this.iv.setImageResource(R.drawable.h12);
                    }else{
                        this.iv.setImageResource(R.drawable.h124);
                    }
                }else{
                    if (checked4){
                        this.iv.setImageResource(R.drawable.h123);
                    }else{
                        this.iv.setImageResource(R.drawable.h0);
                    }
                }
            }
        }

//        if (checked2){
//            if (checked3){
//
//            }
//        }

    }

}
