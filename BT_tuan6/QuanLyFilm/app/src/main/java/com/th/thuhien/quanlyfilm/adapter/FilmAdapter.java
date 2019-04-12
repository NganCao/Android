package com.th.thuhien.quanlyfilm.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.th.thuhien.quanlyfilm.R;
import com.th.thuhien.quanlyfilm.model.Film;

import java.util.ArrayList;

public class FilmAdapter extends ArrayAdapter<Film> {

    Context context;
    int layoutResourceId;
    ArrayList<Film> data;

    public FilmAdapter(@NonNull Context context, int resource, ArrayList<Film> data) {
        super(context, resource, data);
        this.context = context;
        this.layoutResourceId = resource;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        ViewHolder holder = null;
        if (convertView == null){
            holder = new ViewHolder();

            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.listview_film_row, parent, false);

            holder.imgFilm = (ImageView) convertView.findViewById(R.id.imageviewFilm);
            holder.txtTenTA = (TextView) convertView.findViewById(R.id.textviewTenTA);
            holder.txtTenTV = (TextView) convertView.findViewById(R.id.textviewTenTV);
            holder.ratingBar = (RatingBar) convertView.findViewById(R.id.ratingbar_danhgia);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        Film film = data.get(position);
        holder.txtTenTA.setText(film.getTenTA());
        holder.txtTenTV.setText("(" + film.getTenTV() + ")");
        holder.ratingBar.setRating(film.getRating());

        int imgId = getIdByNameImage(film.getImg());
        holder.imgFilm.setImageResource(imgId);

        return convertView;
    }

    private int getIdByNameImage(String name){
        String pkgName = context.getPackageName();
        int resId = context.getResources().getIdentifier(name, "mipmap", pkgName);
        return resId;
    }

    static class ViewHolder{
        ImageView imgFilm;
        TextView txtTenTA, txtTenTV;
        RatingBar ratingBar;
    }
}
