package com.th.thuhien.plantshop.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.th.thuhien.plantshop.R;
import com.th.thuhien.plantshop.activity.ChiTietSanPhamActivity;
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
        final HinhSanPham hinh = hinhSanPhams.get(i);
        Picasso.with(context).load(hinh.getUrlHinh())
                .error(R.drawable.error)
                .placeholder(R.drawable.product)
                .into(hinhSpViewHolder.img);

//        hinhSpViewHolder.layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, ChiTietSanPhamActivity.class);
//                intent.putExtra("url_hinh", hinh.getUrlHinh());
//                context.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return hinhSanPhams.size();
    }

    public class HinhSpViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        LinearLayout layout;

        public HinhSpViewHolder(@NonNull final View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.imageviewShowHinhSp);
            layout = (LinearLayout) itemView.findViewById(R.id.layoutparent);

            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent intent = new Intent(context, ChiTietSanPhamActivity.class);
//                    intent.putExtra("url_hinh", hinhSanPhams.get(getAdapterPosition()).getUrlHinh());
//                    context.startActivity(intent);

                    Intent intent = new Intent("custom-message");
                    //            intent.putExtra("quantity",Integer.parseInt(quantity.getText().toString()));
                    intent.putExtra("hinhsp",hinhSanPhams.get(getAdapterPosition()).getUrlHinh());
                    LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                }
            });
        }
    }
}
