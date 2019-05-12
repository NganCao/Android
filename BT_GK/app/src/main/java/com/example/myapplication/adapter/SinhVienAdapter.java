package com.example.myapplication.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.model.SinhVien;

import java.util.ArrayList;

public class SinhVienAdapter extends ArrayAdapter<SinhVien> {
    Context context;
    int layoutId;
    ArrayList<SinhVien> data = null;

    public SinhVienAdapter(Context context, int layoutId, ArrayList<SinhVien> data){
        super(context, layoutId, data);
        this.context = context;
        this.layoutId = layoutId;
        this.data = data;
    }
    static class SVHolder {
        ImageView imageView;
        TextView txtMasv, txtMalop, txtTen;
    }

    
}
