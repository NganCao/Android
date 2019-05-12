package com.th.thuhien.plantshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.th.thuhien.plantshop.R;
import com.th.thuhien.plantshop.model.DonDatHang;
import com.th.thuhien.plantshop.model.Menu;

import java.util.ArrayList;

public class DonDatHangAdapter extends BaseAdapter {
    ArrayList<DonDatHang> donDatHangArrayList;
    Context context;

    public DonDatHangAdapter(ArrayList<DonDatHang> donDatHangArrayList, Context context) {
        this.donDatHangArrayList = donDatHangArrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return donDatHangArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return donDatHangArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class DDHViewHolder{
        TextView txt_MaDDH, txt_TenKH, txt_SdtKH, txt_EmailKH, txt_DiaChiKH;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DDHViewHolder viewHolder = null;
        if (convertView == null){
            viewHolder = new DDHViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_dondathang_listview,null);
            viewHolder.txt_MaDDH = convertView.findViewById(R.id.textviewMaDDH);
            viewHolder.txt_TenKH = convertView.findViewById(R.id.textviewTenKH);
            viewHolder.txt_SdtKH = convertView.findViewById(R.id.textviewSdtKH);
            viewHolder.txt_EmailKH = convertView.findViewById(R.id.textviewEmailKH);
            viewHolder.txt_DiaChiKH = convertView.findViewById(R.id.textviewDiachiKH);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (DDHViewHolder) convertView.getTag();
        }
        DonDatHang donDatHang = (DonDatHang) getItem(position);
        viewHolder.txt_MaDDH.setText(String.valueOf(donDatHang.getMaDDH()));
        viewHolder.txt_TenKH.setText(donDatHang.getTenKH());
        viewHolder.txt_SdtKH.setText(donDatHang.getSdtKH());
        viewHolder.txt_EmailKH.setText(donDatHang.getEmailKH());
        viewHolder.txt_DiaChiKH.setText(donDatHang.getDiachiKH());
        return convertView;
    }
}
