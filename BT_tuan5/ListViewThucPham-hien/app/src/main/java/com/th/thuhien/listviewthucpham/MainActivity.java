package com.th.thuhien.listviewthucpham;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.th.thuhien.listviewthucpham.DrinkActivity;
import com.th.thuhien.listviewthucpham.FoodActivity;
import com.th.thuhien.listviewthucpham.R;

public class MainActivity extends AppCompatActivity {

    Button btnThucAn, btnDoUong, btnThoat;
    TextView tvKetQua;

    String resultFood, resultDrink;
    String doan1, douong1;

    /* REQUEST_CODE là một giá trị int dùng để định danh mỗi request. Khi nhận được kết quả, hàm callback sẽ trả về cùng REQUEST_CODE này để ta có thể xác định và xử lý kết quả. */
    private static final int REQUEST_CODE_EXAMPLE = 0x9345;
    private static final int REQUEST_CODE_EXAMPLE_DRINK = 0x9345;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();

        btnThucAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo một Intent để start DetailActivity
                Intent intent = new Intent(MainActivity.this, FoodActivity.class);
                // Start DetailActivity với request code vừa được khai báo trước đó
                startActivityForResult(intent, REQUEST_CODE_EXAMPLE);

            }
        });




        btnDoUong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo một Intent để start DetailActivity
                Intent intent1 = new Intent(MainActivity.this, DrinkActivity.class);
                // Start DetailActivity với request code vừa được khai báo trước đó
                startActivityForResult(intent1, REQUEST_CODE_EXAMPLE_DRINK);
            }
        });

        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //Intent intent = getIntent();





    }

    private void AnhXa() {
        btnThucAn = (Button) findViewById(R.id.buttonThucAn);
        btnDoUong = (Button) findViewById(R.id.buttonDoUong);
        btnThoat = (Button) findViewById(R.id.buttonThoat);
        tvKetQua = (TextView) findViewById(R.id.textviewKetQua);
    }

    // Khi kết quả được trả về từ Activity khác, hàm onActivityResult sẽ được gọi.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Kiểm tra requestCode có trùng với REQUEST_CODE vừa dùng
        if (requestCode == REQUEST_CODE_EXAMPLE){

            // resultCode được set bởi DetailActivity
            // RESULT_OK chỉ ra rằng kết quả này đã thành công
            if (resultCode == Activity.RESULT_OK){

                // Nhận dữ liệu từ Intent trả về
                resultFood = data.getStringExtra(FoodActivity.EXTRA_DATA);
                //Toast.makeText(this, "Result: " + resultFood, Toast.LENGTH_LONG).show();
                if (resultFood != null){
                    doan1 = resultFood;
                }

            }else {
                // FoodActivity không thành công, không có data trả về.
            }
        }
        if (requestCode == REQUEST_CODE_EXAMPLE_DRINK){
            // resultCode được set bởi DetailActivity
            // RESULT_OK chỉ ra rằng kết quả này đã thành công
            if (resultCode == Activity.RESULT_OK){

                // Nhận dữ liệu từ Intent trả về
                resultDrink = data.getStringExtra(DrinkActivity.EXTRA_DATA_2);
                if (resultDrink != null){
                    douong1 = resultDrink;
                }

            }else {
                // FoodActivity không thành công, không có data trả về.
            }
        }

        tvKetQua.setText(doan1 + " - " + douong1);
    }
}
