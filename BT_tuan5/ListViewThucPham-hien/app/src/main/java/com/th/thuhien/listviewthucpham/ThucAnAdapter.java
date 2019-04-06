package com.th.thuhien.listviewthucpham;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ThucAnAdapter extends BaseAdapter{

    private Context context;
    private LayoutInflater inflater;
    private List<ThucAn> listData;

    public ThucAnAdapter(Context acontext, List<ThucAn> listData){
        this.context = acontext;
        this.inflater = LayoutInflater.from(acontext);
        this.listData = listData;
    }
    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null){
            convertView = inflater.inflate(R.layout.listview_thucan_row, null);
            viewHolder = new ViewHolder();

            viewHolder.hinh = (ImageView) convertView.findViewById(R.id.imageView);
            viewHolder.ten = (TextView) convertView.findViewById(R.id.textView);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ThucAn thucAn = listData.get(position);

        int imgId = getIdByNameImage(thucAn.getHinh());
        viewHolder.hinh.setImageResource(imgId);
        viewHolder.ten.setText(thucAn.getTen());
        return convertView;
    }

    public int getIdByNameImage(String nameImage){
        String pkg = context.getPackageName();

        int resId = context.getResources().getIdentifier(nameImage, "mipmap", pkg);
        return resId;
    }
    static class ViewHolder{
        ImageView hinh;
        TextView ten;
    }
}