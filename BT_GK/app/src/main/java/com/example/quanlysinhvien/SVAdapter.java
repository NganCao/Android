package com.example.quanlysinhvien;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SVAdapter {
    Context context;
    int layoutResourceId;
    ArrayList<SV> data = null;

    public SVAdapter(Context context, int layoutResourceId, ArrayList<SV> data) {
//        super(context, layoutResourceId, data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View row = convertView;
        SVHolder holder = null;
        if (row != null){
            holder = (SVHolder) row.getTag();
        }
        else
        {
            holder = new SVHolder();
            LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
            row = layoutInflater.inflate(R.layout.item_for_sv, parent, false);

            holder.icon = (ImageView) row.findViewById(R.id.icon);
            holder.malop = (TextView) row.findViewById(R.id.maLop);
            holder.masv = (TextView) row.findViewById(R.id.maSV);
            holder.tensv = (TextView) row.findViewById(R.id.tenSV);
            row.setTag(holder);
        }
        SV sv = data.get(position);

        holder.icon.setImageResource(R.drawable.boy);
        holder.malop.setText("Mã lớp: " + sv.getMaLop());
        holder.masv.setText("Mã SV: " + sv.getMaSV());
        holder.masv.setText("Tên SV: " + sv.getHoSV() + sv.getTenSV());


        return row;
    }

    static class SVHolder{
        ImageView icon;
        TextView malop, masv, tensv;
    }
}
