package com.th.thuhien.quanlynhanvien;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NhanVienAdapter extends ArrayAdapter<NhanVien> {
    Context context;
    int layoutResourceId;
    ArrayList<NhanVien> data = null;

    public NhanVienAdapter(Context context, int layoutResourceId, ArrayList<NhanVien> data) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NhanVienViewHolder holder = null;
        if (convertView == null){
            holder = new NhanVienViewHolder();
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.listview_nhanvien_row, null);

            holder.imgNV = convertView.findViewById(R.id.imageviewNV);
            holder.maVaTenNV = convertView.findViewById(R.id.textviewMaTenNV);
            holder.isCheckbox = convertView.findViewById(R.id.checkboxXoaNV);

            holder.isCheckbox = convertView.findViewById(R.id.checkboxXoaNV);

            convertView.setTag(holder);
        }else {
            holder = (NhanVienViewHolder) convertView.getTag();
        }

        NhanVien nhanVien = data.get(position);
        if (nhanVien.getGioiTinhNV().equals("nu")){
            holder.imgNV.setImageResource(R.drawable.avatar_female);
        }else {
            holder.imgNV.setImageResource(R.drawable.avatar_male);
        }

        holder.maVaTenNV.setText(nhanVien.getMaNV() + "-" + nhanVien.getTenNV());
        holder.isCheckbox.setChecked(nhanVien.isSelected());

        return convertView;
    }

    static class NhanVienViewHolder{
        ImageView imgNV;
        TextView maVaTenNV;
        CheckBox isCheckbox;
    }
}
