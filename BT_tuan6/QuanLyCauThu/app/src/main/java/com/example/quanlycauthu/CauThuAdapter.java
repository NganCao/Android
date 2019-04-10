package com.example.quanlycauthu;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CauThuAdapter extends ArrayAdapter<CauThu> {
    Context context;
    int layoutResourceId;
    ArrayList<CauThu> data = null;

    public CauThuAdapter(Context context, int layoutResourceId, ArrayList<CauThu> data){
        super(context,layoutResourceId,data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }

    public View getView (int position, View convertView, ViewGroup parent){
        View row = convertView;
        CauThuHolder holder = null;
        if(row == null)
        {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new CauThuHolder();
            holder.icon = (ImageView)row.findViewById(R.id.icon);
            holder.txt1 = (TextView) row.findViewById(R.id.name);
            holder.txt3 = (TextView)row.findViewById(R.id.team);
            holder.txt2 = (TextView)row.findViewById(R.id.hang);

            row.setTag(holder);
        }
        else {
            holder = (CauThuHolder) row.getTag();
        }

        CauThu item = data.get(position);
        holder.icon.setImageResource(item.icon);
        holder.txt1.setText(item.name);
        holder.txt2.setText(item.hang);
        holder.txt3.setText(item.team);

        return row;
    }

    static class CauThuHolder{
        ImageView icon;
        TextView txt1, txt2, txt3;
    }
}
