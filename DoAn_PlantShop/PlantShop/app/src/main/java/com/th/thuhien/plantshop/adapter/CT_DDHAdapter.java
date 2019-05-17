package com.th.thuhien.plantshop.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.th.thuhien.plantshop.R;
import com.th.thuhien.plantshop.model.CT_DDH;
import com.th.thuhien.plantshop.model.DonDatHang;
import com.th.thuhien.plantshop.model.SanPham;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CT_DDHAdapter extends BaseAdapter {
    ArrayList<CT_DDH> ct_ddhAdapterArrayList;
    //ArrayList<String> sanPhamArrayList;
    Context context;

    public CT_DDHAdapter(ArrayList<CT_DDH> ct_ddhAdapterArrayList, Context context) {
        this.ct_ddhAdapterArrayList = ct_ddhAdapterArrayList;
        //this.sanPhamArrayList = sanPhams;
        this.context = context;
    }

//    public CT_DDHAdapter(ArrayList<CT_DDH> ct_ddhAdapterArrayList, ArrayList<String> sanPhams, Context context) {
//        this.ct_ddhAdapterArrayList = ct_ddhAdapterArrayList;
//        this.sanPhamArrayList = sanPhams;
//        this.context = context;
//    }

    @Override
    public int getCount() {
        return ct_ddhAdapterArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return ct_ddhAdapterArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class CT_DDHViewHolder{
        TextView txt_TenSP, txt_Gia, txt_SoLuong;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Log.d("sizelistsp: ", String.valueOf(sanPhamArrayList.size()));
        CT_DDHViewHolder viewHolder = null;
        if (convertView == null){
            viewHolder = new CT_DDHViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_ctddh_listview,null);
            viewHolder.txt_TenSP = convertView.findViewById(R.id.textviewCT_Ten);
            viewHolder.txt_Gia = convertView.findViewById(R.id.textviewCT_Gia);
            viewHolder.txt_SoLuong = convertView.findViewById(R.id.textviewCT_soluong);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (CT_DDHViewHolder) convertView.getTag();
        }
        CT_DDH ct_ddh = (CT_DDH) getItem(position);
        Log.d("position: ", String.valueOf(position));
        //SanPham sanPham = sanPhamArrayList.get(position);
        //String ten = sanPhamArrayList.get(position);
//        Log.d("tensp: ", ten);
//        if (ten != null){
//            viewHolder.txt_TenSP.setText(ten);
//        }else {
//            viewHolder.txt_TenSP.setText(String.valueOf(ct_ddh.getMaSP()));
//        }
        viewHolder.txt_TenSP.setText(String.valueOf(ct_ddh.getMaSP()));
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txt_Gia.setText(String.valueOf(decimalFormat.format(ct_ddh.getDongia())));
        viewHolder.txt_SoLuong.setText(String.valueOf(ct_ddh.getSoluong()));

        return convertView;
    }
}
