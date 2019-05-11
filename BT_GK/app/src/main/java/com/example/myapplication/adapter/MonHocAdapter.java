package com.example.myapplication.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.myapplication.model.MonHoc;

import java.util.ArrayList;
import java.util.List;

public class MonHocAdapter extends ArrayAdapter<MonHoc> {

    Context context;
    int layoutResourceId;
    ArrayList<MonHoc> data;

    public MonHocAdapter(Context context, int resource, List<MonHoc> objects, Context context1, int layoutResourceId, ArrayList<MonHoc> data) {
        super(context, resource, objects);
        this.context = context1;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }

    private static class MonHocViewHolder{
        TextView txt_maMH, txt_tenMH, txt_hocKyMH;
    }
}
