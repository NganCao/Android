package com.th.thuhien.plantshop.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.th.thuhien.plantshop.R;
import com.th.thuhien.plantshop.model.HinhSanPham;

import java.util.ArrayList;

public class RecyclerViewCTHinhSpAdapter extends RecyclerView.Adapter<RecyclerViewCTHinhSpAdapter.HinhSpViewHolder>{

    Context context;
    int layoutResourceId;
    ArrayList<HinhSanPham> hinhSanPhams;

    private String url_hinh = "";

    public RecyclerViewCTHinhSpAdapter(Context context, int layoutResourceId, ArrayList<HinhSanPham> hinhSanPhams) {
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.hinhSanPhams = hinhSanPhams;
    }

    @NonNull
    @Override
    public HinhSpViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(layoutResourceId, null);
        HinhSpViewHolder viewHolder = new HinhSpViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HinhSpViewHolder hinhSpViewHolder, int i) {
        HinhSanPham hinh = hinhSanPhams.get(i);
        Picasso.with(context).load(hinh.getUrlHinh())
                .error(R.drawable.error)
                .placeholder(R.drawable.product)
                .into(hinhSpViewHolder.img);
    }

    @Override
    public int getItemCount() {
        return hinhSanPhams.size();
    }

    public class HinhSpViewHolder extends RecyclerView.ViewHolder{
        ImageView img;

        public HinhSpViewHolder(@NonNull final View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.imageviewShowHinhSp);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "vua chon: " + hinhSanPhams.get(getAdapterPosition()).getUrlHinh(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
