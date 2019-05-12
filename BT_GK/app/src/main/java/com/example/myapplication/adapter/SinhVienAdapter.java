package com.example.myapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
<<<<<<< HEAD
import com.example.myapplication.model.MonHoc;
=======
>>>>>>> 962d71db93539e2687bcd240000ffdd441025e57
import com.example.myapplication.model.SinhVien;

import java.util.ArrayList;

public class SinhVienAdapter extends BaseAdapter {
    Context context;
    LayoutInflater layoutResourceId;
    ArrayList<SinhVien> data = null;

    public SinhVienAdapter(Context context, ArrayList<SinhVien> data) {
        this.context = context;
        this.data = data;
        layoutResourceId = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SVHolder holder;

        if (convertView == null){
            convertView = layoutResourceId.inflate(R.layout.item_row_sv, null);
            holder = new SVHolder();

            holder.txtMalop = (TextView) convertView.findViewById(R.id.rowMalop);
            holder.txtMasv = (TextView) convertView.findViewById(R.id.rowMasv);
            holder.txtTen = (TextView) convertView.findViewById(R.id.rowTen);
            holder.imageView = (ImageView) convertView.findViewById(R.id.rowicon);

            convertView.setTag(holder);
        }else {
            holder = (SVHolder) convertView.getTag();
        }

        SinhVien sinhVien = data.get(position);

        if (sinhVien.getPhai().equals("nu")){
            holder.imageView.setImageResource(R.drawable.girl);
        }else {
            holder.imageView.setImageResource(R.drawable.boy);
        }

        holder.txtMalop.setText(sinhVien.getMalop());
        holder.txtMasv.setText(sinhVien.getMasv());
        holder.txtTen.setText(sinhVien.getTen());

        return convertView;
    }

    static class SVHolder {
        ImageView imageView;
        TextView txtMasv, txtMalop, txtTen;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View row = convertView;
        SVHolder holder = new SVHolder();
        if (row != null){
            holder = (SVHolder) row.getTag();
        }
        else {
            holder = new SVHolder();
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(R.layout.item_row_sv, parent, false);

            holder.imageView = (ImageView) row.findViewById(R.id.rowicon);
            holder.txtMasv = (TextView) row.findViewById(R.id.rowMasv);
            holder.txtMalop = (TextView) row.findViewById(R.id.rowMalop);
            holder.txtTen = (TextView) row.findViewById(R.id.rowTen);

            row.setTag(holder);
        }

        SinhVien sv = data.get(position);

        holder.imageView.setImageResource(R.drawable.boy);
        holder.txtMalop.setText("Mã lớp: " + sv.getMalop());
        holder.txtMasv.setText("Mã sv: " + sv.getMasv());
        holder.txtTen.setText("Họ tên: " + sv.getHo() + " " + sv.getTen());


        return row;
    }
}
