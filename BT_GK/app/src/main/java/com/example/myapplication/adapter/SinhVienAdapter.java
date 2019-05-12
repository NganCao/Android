package com.example.myapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
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
