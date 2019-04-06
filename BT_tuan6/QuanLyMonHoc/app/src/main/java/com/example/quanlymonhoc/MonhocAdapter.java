package com.example.quanlymonhoc;

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

public class MonhocAdapter extends ArrayAdapter<Monhoc> {
    Context context;
    int layoutResourceId;
    ArrayList<Monhoc> data= null;

    public MonhocAdapter(Context context, int layoutResourceId, ArrayList<Monhoc> data) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }

    static class MonhocHolder{
        ImageView icon;
        TextView txt1,txt2,txt3;
    }

    public View getView (int position, View convertView, ViewGroup parent){
        View row = convertView;
        MonhocHolder holder = null;
        if (row != null) {
            holder = (MonhocHolder) row.getTag();
        }
        else {
            holder = new MonhocHolder();
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(R.layout.item_row,parent,false);

            holder.icon = (ImageView) row.findViewById(R.id.icon);
            holder.txt1 = (TextView) row.findViewById(R.id.maMon);
            holder.txt2 = (TextView) row.findViewById(R.id.tenMH);
            holder.txt3 = (TextView) row.findViewById(R.id.soTiet);

            row.setTag(holder);
        }
        Monhoc mh = data.get(position);

        holder.icon.setImageResource(R.drawable.cntt);
        holder.txt1.setText("Mã MH: " + mh.getMa());
        holder.txt2.setText("Tên MH: " + mh.getTen());
        holder.txt3.setText("Số tiết: " + mh.getSotiet());

        return row;
    }
}
