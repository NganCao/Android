package com.th.thuhien.plantshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.th.thuhien.plantshop.R;
import com.th.thuhien.plantshop.model.GioHang;
import com.th.thuhien.plantshop.model.SanPham;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GiohangAdapter extends BaseAdapter {

    Context context;
    ArrayList<GioHang> dataGH;

    public GiohangAdapter(Context context, ArrayList<GioHang> dataGH) {
        this.context = context;
        this.dataGH = dataGH;
    }

    @Override
    public int getCount() {
        return dataGH.size();
    }

    @Override
    public Object getItem(int position) {
        return dataGH.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class GHHolder {
        public TextView txtName, txtGia;
        public ImageView icon;
        public Button add, del,quantity;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GHHolder holder = null;
        if (holder == null){
            holder = new GHHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = (View) inflater.inflate(R.layout.item_row_giohang, null);
            holder.txtName = (TextView) convertView.findViewById(R.id.txtName);
            holder.txtGia = (TextView) convertView.findViewById(R.id.txtGia);
            holder.icon = (ImageView) convertView.findViewById(R.id.icon);
            holder.quantity = (Button) convertView.findViewById(R.id.quantity);
            holder.add = (Button) convertView.findViewById(R.id.btncong);
            holder.del = (Button) convertView.findViewById(R.id.btntru);
            convertView.setTag(holder);
        }
        else {
            holder = (GHHolder) convertView.getTag();
        }
        GioHang gioHang = (GioHang) getItem(position);
        holder.txtName.setText(gioHang.getTenSP());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtGia.setText(decimalFormat.format(gioHang.getGiaSP())+" vnd");
        Picasso.with(context).load(gioHang.getHinhSP())
                .placeholder(R.drawable.product)
                .error(R.drawable.error)
                .into(holder.icon);
        holder.quantity.setText(gioHang.soluongSP);
        return null;
    }
}
