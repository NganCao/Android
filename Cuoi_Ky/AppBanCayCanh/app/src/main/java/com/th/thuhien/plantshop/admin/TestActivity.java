package com.th.thuhien.plantshop.admin;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.th.thuhien.plantshop.R;
import com.th.thuhien.plantshop.ultil.MenuService;

public class TestActivity extends AppCompatActivity {

    String user = "meomeo";
    String pass = "2345";
    String tenmenu = "test1001";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        AsynTest asynTest = new AsynTest();
        asynTest.execute(tenmenu);
    }

    public class AsynTest extends AsyncTask<String, Void, Integer>{

        @Override
        protected Integer doInBackground(String... strings) {
            MenuService menuService = new MenuService();
            return menuService.insetMenu(strings[0]);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);

                Log.d("trave: ", String.valueOf(integer));

        }
    }
}
