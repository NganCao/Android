package com.example.bt2_qlnv.activity;

import java.util.ArrayList;

import com.example.bt2_qlnv.R;
import com.example.bt2_qlnv.model.ChucVu;
import com.example.bt2_qlnv.model.NhanVien;
import com.example.bt2_qlnv.model.PhongBan;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.app.Activity;
import android.content.Intent;
/**
 * màn hình sẽ hiển thị danh sách nhân viên vào 2 ListView khác nhau
 * ListView 1 dùng Radio để chọn trưởng phòng
 * ListView 2 dùng Checkbox để chọn phó phòng
 * @author drthanh
 *
 */
public class thietlap extends Activity {

    ListView lvtruongphong,lvphophong;
    ArrayList<NhanVien>arrNvForTP=new ArrayList<NhanVien>();
    ArrayAdapter<NhanVien> adapterForTP;
    ArrayList<NhanVien>arrNvForPP=new ArrayList<NhanVien>();
    ArrayAdapter<NhanVien> adapterForPP;
    ImageButton btnApply;
    int lastChecked=-1;
    PhongBan pb=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thietlap);
        getFormWidgets();
    }
    /**
     * hàm lấy control theo id
     * xử lý sự kiện checked (chọn chức vụ cho nhân viên)
     * Mọi thứ là hướng đối tượng nên nó tự tham chiếu
     */
    public void getFormWidgets()
    {
        lvtruongphong=(ListView) findViewById(R.id.lvtruongphong);
        lvtruongphong.setTextFilterEnabled(true);
        lvtruongphong.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lvtruongphong.setOnItemClickListener(new OnItemClickListener() {
            boolean somethingChecked = false;
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                arrNvForTP.get(arg2).setChucvu(ChucVu.TruongPhong);
                if(somethingChecked){
                    CheckedTextView cv = (CheckedTextView) arg1;
                    cv.setChecked(false);

                }
                CheckedTextView cv = (CheckedTextView) arg1;
                if(!cv.isChecked())
                {
                    cv.setChecked(true);
                    arrNvForTP.get(arg2).setChucvu(ChucVu.TruongPhong);
                }
                else
                {
                    arrNvForTP.get(arg2).setChucvu(ChucVu.NhanVien);
                }
                lastChecked = arg2;
                somethingChecked=true;
            }

        });
        lvphophong=(ListView) findViewById(R.id.lvphophong);
        lvphophong.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                CheckedTextView cv = (CheckedTextView) arg1;
                if(!cv.isChecked())
                {cv.setChecked(true);
                    arrNvForPP.get(arg2).setChucvu(ChucVu.PhoPhong);
                }
                else
                {cv.setChecked(false);
                    arrNvForPP.get(arg2).setChucvu(ChucVu.NhanVien);
                }
            }

        });

        adapterForTP=new ArrayAdapter<NhanVien>(this,
                android.R.layout.simple_list_item_single_choice,
                arrNvForTP);
        adapterForPP=new ArrayAdapter<NhanVien>(this,
                android.R.layout.simple_list_item_multiple_choice,
                arrNvForPP);
        lvtruongphong.setAdapter(adapterForTP);
        lvphophong.setAdapter(adapterForPP);
        //Lấy được phòng ban gửi qua từ MainActivity
        Intent i= getIntent();
        Bundle bundle= i.getBundleExtra("DATA");
        pb= (PhongBan) bundle.getSerializable("PHONGBAN");
        addNvToListTP(pb);
        addNvToListPP(pb);
        adapterForTP.notifyDataSetChanged();
        adapterForPP.notifyDataSetChanged();

        btnApply=(ImageButton) findViewById(R.id.imgapply);
        btnApply.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                doApply();

            }
        });
    }
    /**
     * gửi thông tin lại Mainactivity sau khi thiết lập
     */
    public void doApply()
    {
        Intent i=getIntent();
        Bundle bundle=new Bundle();
        bundle.putSerializable("PHONGBAN", pb);
        i.putExtra("DATA", bundle);
        setResult(MainActivity.TP_PP_PASS, i);
        finish();
    }
    /**
     * duyệt toàn bộ nhân viên vào danh sách ứng viên Trưởng phòng
     * @param pb
     */
    public void addNvToListTP(PhongBan pb)
    {
        arrNvForTP.clear();
        for(NhanVien nv:pb.getListNhanVien())
        {
            arrNvForTP.add(nv);
        }
    }
    /**
     * duyệt toàn bộ nhân viên vào danh sách ứng viên phó phòng
     * @param pb
     */
    public void addNvToListPP(PhongBan pb)
    {
        arrNvForPP.clear();
        for(NhanVien nv:pb.getListNhanVien())
        {
            arrNvForPP.add(nv);
        }
    }
}
