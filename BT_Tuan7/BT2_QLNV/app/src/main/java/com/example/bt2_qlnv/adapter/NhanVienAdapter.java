package com.example.bt2_qlnv.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bt2_qlnv.R;
import com.example.bt2_qlnv.model.NhanVien;

import java.util.ArrayList;

public class NhanVienAdapter extends ArrayAdapter<NhanVien> {
    Activity context;
    int resourceLayoutId;
    ArrayList<NhanVien> data;
    public NhanVienAdapter(Activity context, int resourceLayoutId, ArrayList<NhanVien> data){
        super(context, resourceLayoutId, data);
        this.context = context;
        this.resourceLayoutId = resourceLayoutId;
        this.data = data;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        convertView=context.getLayoutInflater().inflate(resourceLayoutId, null);
        //lấy các control ra
        TextView txtnv= (TextView) convertView.findViewById(R.id.txtShortInfor);
        TextView txtmotanv= (TextView) convertView.findViewById(R.id.txtDetailInfor);
        ImageView img=(ImageView) convertView.findViewById(R.id.imgview);
        //lấy nhân viên thứ position
        NhanVien nv=data.get(position);
        txtnv.setText(nv.toString());
        String strMota="";
        String cv="Chức vụ: "+nv.getChucvu().getChucVu();
        String gt="Giới tính: "+(nv.isGioitinh()?"Nữ":"Nam");
        //Kiểm tra giới tính để gán cho đúng hình đại diện
        img.setImageResource(R.drawable.girl);
        if(!nv.isGioitinh())
            img.setImageResource(R.drawable.boy);
        strMota=cv+"\n"+gt;
        txtmotanv.setText(strMota);
        return convertView;
    }

}
