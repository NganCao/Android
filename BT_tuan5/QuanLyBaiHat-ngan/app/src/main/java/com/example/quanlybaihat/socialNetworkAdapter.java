package com.example.quanlybaihat;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class socialNetworkAdapter extends ArrayAdapter<socialNetwork> {

    Context context;
    int layoutResourceId;
    ArrayList<socialNetwork> data = null;

    public socialNetworkAdapter(Context context, int layoutResourceId,ArrayList<socialNetwork> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        View row = convertView;
        socialNetworkHolder holder = null;

        if(row == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new socialNetworkHolder();
            holder.icon = (ImageView)row.findViewById(R.id.icon);
            holder.tittle = (TextView)row.findViewById(R.id.tittle);
            holder.singer = (TextView)row.findViewById(R.id.singer);
            holder.timeplay = (TextView)row.findViewById(R.id.timeplay);

            row.setTag(holder);
        }else{
            holder = (socialNetworkHolder)row.getTag();
        }

        socialNetwork item = data.get(position);
        holder.tittle.setText(item.tittle);
        holder.singer.setText(item.singer);
        holder.icon.setImageResource(item.icon);
        holder.timeplay.setText(item.timeplay);

        return row;
    }

    static class socialNetworkHolder{
        ImageView icon;
        TextView tittle;
        TextView singer;
        TextView timeplay;
    }
}

