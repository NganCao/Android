package com.th.thuhien.listviewcountry;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CountryAdapter extends BaseAdapter {
    private List<Country> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public CountryAdapter(Context aContext, List<Country> listData){
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
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
        ViewHolder holder;

        if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.listview_country_row, null);
            holder = new ViewHolder();

            holder.imgFlag = (ImageView) convertView.findViewById(R.id.imgflag);
            holder.tenQG = (TextView) convertView.findViewById(R.id.textviewTenQuocGia);
            holder.danSo = (TextView) convertView.findViewById(R.id.textviewDanSo);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        Country country = listData.get(position);

        holder.tenQG.setText(country.getTen());
        holder.danSo.setText("Population: " + country.getDanSo());

        int imageId = getMipmapResIdByName(country.getHinh());
        holder.imgFlag.setImageResource(imageId);

        return convertView;
    }
    public int getMipmapResIdByName(String resName){
        String pkgName = context.getPackageName();

        //tra ve 0 -> khong tim thay
        int resId = context.getResources().getIdentifier(resName, "mipmap", pkgName);
        return resId;
    }
    static class ViewHolder{
        ImageView imgFlag;
        TextView tenQG;
        TextView danSo;
    }
}
