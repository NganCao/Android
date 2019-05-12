package com.example.myapplication.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.myapplication.R;

import com.example.myapplication.model.Diem;


import java.util.ArrayList;

public class DiemAdapter extends ArrayAdapter<Diem> {
    Activity context;
    int layoutId;
    ArrayList<Diem> data;

    public DiemAdapter (Activity context, int layoutId, ArrayList<Diem> data){
        super(context, layoutId, data);
        this.context = context;
        this.layoutId = layoutId;
        this.data = data;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        Holder holder = null;
        if(row == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutId, parent,false);

            holder = new Holder();
            holder.txtMamh = (TextView) row.findViewById(R.id.txtMamh);
            holder.txtMasv = (TextView) row.findViewById(R.id.txtMasv);
            holder.txtDiem = (TextView) row.findViewById(R.id.txtDiem);

            row.setTag(holder);
        }
        else {
            holder = (Holder) row.getTag();
        }

        Diem diem = data.get(position);
        holder.txtMamh.setText("Mã MH: "+ diem.getMamh());
        holder.txtMasv.setText("Mã SV: " + diem.getMasv());
        holder.txtDiem.setText("Điểm: " + diem.getDiem());
        return row;

//        Holder holder = null;
//        convertView = context.getLayoutInflater().inflate(layoutId, null);
//
//        holder = new Holder();
//            holder.txtMamh = (TextView) convertView.findViewById(R.id.txtMamh);
//            holder.txtMasv = (TextView) convertView.findViewById(R.id.txtMasv);
//            holder.txtDiem = (TextView) convertView.findViewById(R.id.txtDiem);
//
//        Diem diem = data.get(position);
//        holder.txtMamh.setText("Mã MH: ");
//        holder.txtMasv.setText("Mã SV: " + diem.getSinhVien().getMa());
//        holder.txtDiem.setText("Điểm: " + diem.getDiem());
//
//        return convertView;
    }

    static class Holder{
        TextView txtMasv, txtMamh, txtDiem;
    }

}
