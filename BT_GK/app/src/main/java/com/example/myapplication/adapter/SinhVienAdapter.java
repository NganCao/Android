package com.example.myapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.myapplication.R;
import com.example.myapplication.model.Lop;
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
            holder.icon = (ImageView) convertView.findViewById(R.id.rowicon);

            convertView.setTag(holder);
        }else {
            holder = (SVHolder) convertView.getTag();
        }

        SinhVien sv = data.get(position);

        holder.txtMalop.setText(sv.getMalop());
        holder.txtMasv.setText(sv.getMasv());
        holder.txtTen.setText(sv.getHo() + " " + sv.getTen());
        holder.icon.setImageResource(R.drawable.boy);

        return convertView;
    }

    static class SVHolder{
        TextView txtMalop, txtMasv, txtTen;
        ImageView icon;
    }
}
