package com.th.thuhien.quanlymonhocsqlite;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MonHocAdapter extends ArrayAdapter<MonHoc> {
    Context context;
    int layoutResourceId;
    ArrayList<MonHoc> data= null;

    public MonHocAdapter(Context context, int layoutResourceId, ArrayList<MonHoc> data) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }

    static class MonHocHolder{
        ImageView icon;
        TextView txt1,txt2,txt3;
    }

    public View getView (int position, View convertView, ViewGroup parent){
        View row = convertView;
        MonHocHolder holder = null;
        if (row != null) {
            holder = (MonHocHolder) row.getTag();
        }
        else {
            holder = new MonHocHolder();
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(R.layout.item_monhoc_listview,parent,false);

            holder.icon = (ImageView) row.findViewById(R.id.icon);
            holder.txt1 = (TextView) row.findViewById(R.id.maMon);
            holder.txt2 = (TextView) row.findViewById(R.id.tenMon);
            holder.txt3 = (TextView) row.findViewById(R.id.soTiet);

            row.setTag(holder);
        }
        MonHoc mh = data.get(position);

        holder.txt1.setText("Mã MH: " + mh.getMa());
        holder.txt2.setText("Tên MH: " + mh.getTen());
        holder.txt3.setText("Số tiết: " + mh.getSotiet());

        return row;
    }
}