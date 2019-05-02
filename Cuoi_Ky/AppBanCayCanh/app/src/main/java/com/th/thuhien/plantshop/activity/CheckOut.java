package com.th.thuhien.plantshop.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.th.thuhien.plantshop.R;
import com.th.thuhien.plantshop.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CheckOut extends AppCompatActivity {

    EditText editName, editEmail, editPhone, editDiachi;
    Button btnBack, btnDathang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        setControl();
        setEvent();
    }

    private void setEvent() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        if (CheckConnection.haveNetworkConnection(getApplicationContext())){
//            EventButton();
//        }
//        else {
//            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối Internet");
//        }

        EventButton();
    }

    private void EventButton() {
        btnDathang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String ten = editName.getText().toString().trim();
                final String email = editEmail.getText().toString().trim();
                final String sdt = editPhone.getText().toString().trim();
                final String diachi = editDiachi.getText().toString().trim();
                if (ten.length() > 0 && sdt.length() >0 && email.length() >0 && diachi.length() >0){
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.Duongdandonhang, new Response.Listener<String>() {
                        @Override
                        public void onResponse(final String maddh) {
                            Log.d("maddh",maddh);
                            if (Integer.parseInt(maddh) > 0){
                                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                                StringRequest request = new StringRequest(Request.Method.POST, "", new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {

                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                }){
                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        JSONArray jsonArray = new JSONArray();
                                        for (int i = 0; i<MainActivity.arrayGioHang.size(); i++){
                                            JSONObject jsonObject = new JSONObject();
                                            try {
                                                jsonObject.put("maddh",maddh);
                                                jsonObject.put("masp",MainActivity.arrayGioHang.get(i).getIdSP());
                                                jsonObject.put("soluong",MainActivity.arrayGioHang.get(i).getSoluongSP());
                                                jsonObject.put("dongia",MainActivity.arrayGioHang.get(i).getGiaSP());
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            jsonArray.put(jsonObject);
                                        }
                                        HashMap<String, String> hashMap = new HashMap<>();
                                        return super.getParams();
                                    }
                                };
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String, String> hashMap = new HashMap<String, String>();
                            hashMap.put("tenkhachhang",ten);
                            hashMap.put("email",email);
                            hashMap.put("sdt",sdt);
                            hashMap.put("diachi",diachi);
                            return hashMap;
                        }
                    };
                    requestQueue.add(stringRequest);
                }else {
                    Toast.makeText(getApplicationContext(), "Hãy kiểm tra lại kết nối của bạn", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setControl() {
        editName = (EditText) findViewById(R.id.editName);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editPhone = (EditText) findViewById(R.id.editPhone);
        editDiachi = (EditText) findViewById(R.id.editDiachi);
        btnBack = (Button) findViewById(R.id.btnBack);
        btnDathang = (Button) findViewById(R.id.btnDathang);
    }
}
