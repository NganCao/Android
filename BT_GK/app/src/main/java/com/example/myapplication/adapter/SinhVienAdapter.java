package com.example.myapplication.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.myapplication.R;
import com.example.myapplication.model.SinhVien;

import java.util.ArrayList;

public class SinhVienAdapter extends ArrayAdapter<SinhVien> {
    Activity context;
    int layoutId;
    ArrayList<SinhVien> data;

    public SinhVienAdapter(Activity context, int layoutId, ArrayList<SinhVien> data){
        super(context, layoutId, data);
        this.context = context;
        this.layoutId = layoutId;
        this.data = data;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        convertView = context.getLayoutInflater().inflate(layoutId, null);

        Holder holder = new Holder();
        holder.txtMasv = (TextView) convertView.findViewById(R.id.txtMasv);
        holder.txtMalop = (TextView) convertView.findViewById(R.id.txtMalop);
        holder.txtTen = (TextView) convertView.findViewById(R.id.txtTen);
        holder.icon = (ImageView) convertView.findViewById(R.id.icon);

        SinhVien sv = data.get(position);
        holder.txtMalop.setText("Mã lớp: "+ sv.getLop().getMaLop());
        holder.txtMasv.setText("Mã SV: "+ sv.getMa());
        holder.txtTen.setText("Họ tên: "+ sv.getHo()+" "+ sv.getTen());
        holder.icon.setImageResource(R.drawable.boy);
        if (!sv.isPhai())
            holder.icon.setImageResource(R.drawable.girl);

        return convertView;
    }

    static class Holder{
        TextView txtMalop, txtMasv, txtTen;
        ImageView icon;
    }
}
