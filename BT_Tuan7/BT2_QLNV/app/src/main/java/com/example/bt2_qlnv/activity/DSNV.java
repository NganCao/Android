package com.example.bt2_qlnv.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.bt2_qlnv.R;
import com.example.bt2_qlnv.adapter.NhanVienAdapter;
import com.example.bt2_qlnv.model.NhanVien;
import com.example.bt2_qlnv.model.PhongBan;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import java.util.ArrayList;

public class DSNV extends AppCompatActivity {

    TextView txtmsg;
    ImageButton btnback;
    ListView lvNhanvien;
    ArrayList<NhanVien> arrNhanvien=null;
    //Nhân viên Adapter để hiển thị thông tin
    //và chi tiết : chức vụ, giới tính
    NhanVienAdapter adapter=null;
    PhongBan pb=null;
    private NhanVien nvSelected=null;
    private int position=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dsnv);

        setControl();
        getDataFromMain();
        setEvents();
        registerForContextMenu(lvNhanvien);
    }

    public void setControl(){
        txtmsg=(TextView) findViewById(R.id.txtmsg);
        btnback=(ImageButton) findViewById(R.id.btnback);
        lvNhanvien=(ListView) findViewById(R.id.lvnhanvien);
    }
    /**
     * hàm lấy giá trị từ MainActivity
     * sẽ truyền phòng ban qua
     * bên này lấy phòng ban và dựa vào phòng ban này
     * lấy ra danh sách nhân viên
     */
    public void getDataFromMain()
    {
        Intent i=getIntent();
        Bundle b=i.getBundleExtra("DATA");
        pb= (PhongBan) b.getSerializable("PHONGBAN");
        arrNhanvien=pb.getListNhanVien();
        adapter=new NhanVienAdapter(this,
                R.layout.layout_item_custom,
                arrNhanvien);
        lvNhanvien.setAdapter(adapter);
        txtmsg.setText("DS nhân viên ["+pb.getTen()+"]");
    }
    /**
     * hàm gán sự kiện (đã quá quen thuộc rồi)
     * có lưu vết lại nhân viên vừa chọn để xử lý
     * cho contextmenu
     */
    public void setEvents()
    {
        btnback.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                doUpdateToMain();
            }
        });
        lvNhanvien.setOnItemLongClickListener(new OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int arg2, long arg3) {
                nvSelected=arrNhanvien.get(arg2);
                position=arg2;
                return false;
            }

        });
    }
    /**
     * hàm gán context menu(đã quá quen thuộc)
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenuInfo menuInfo) {
        // TODO Auto-generated method stub
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.contextmenu_nhanvien, menu);
    }
    /**
     * hàm xử lý sự kiện chọn menuitem (đã quen thuộc quá)
     */
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch(item.getItemId())
        {
            case R.id.mnusuanv:
                doSuaNhanVien();
                break;
            case R.id.mnuchuyenpb:
                doChuyenPhongBan();
                break;
            case R.id.mnuxoanv:
                doXoaNhanVien();
                break;
        }
        return super.onContextItemSelected(item);
    }
    /**
     * hàm onActivityResult xử lý kết quả trả về
     * cho trường hợp xử dụng COntext Menu để mở các
     * Activity khác
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        //lấy kết quả sửa nhân viên thành công
        if(resultCode==MainActivity.SUA_PASS)
        {
            Bundle b=data.getBundleExtra("DATA");
            NhanVien nv= (NhanVien) b.getSerializable("NHANVIEN");
            arrNhanvien.set(position, nv);
            adapter.notifyDataSetChanged();
        }
        //lấy kết quả chuyển phòng ban thành công
        else if(resultCode==MainActivity.CHUYENPB_PASS)
        {
            arrNhanvien.remove(nvSelected);
            adapter.notifyDataSetChanged();
        }
    }
    /**
     * hàm sửa nhân viên
     * đơn giản là mở Activity sửa nhân viên lên
     * rồi truyền nhân viên đang chọn qua Activity đó
     *
     */
    public void doSuaNhanVien()
    {
        Intent i=new Intent(this, suaNV.class);
        Bundle b=new Bundle();
        b.putSerializable("NHANVIEN", nvSelected);
        i.putExtra("DATA", b);
        startActivityForResult(i, MainActivity.MO_SUA_NV);
    }
    /**
     * hàm chuyển phòng ban cho nhân viên đang chọn
     * đơn thuần là mở Activity chuyển phòng ban
     * Activity này có nhiệm vụ hiển thị toàn bộ phòng ban
     * rồi cho phép người sử dụng chọn phòng ban để chuyển
     */
    public void doChuyenPhongBan()
    {
        Intent i=new Intent(this, ChuyenPB.class);
        Bundle b=new Bundle();
        b.putSerializable("NHANVIEN", nvSelected);
        i.putExtra("DATA", b);
        startActivityForResult(i, MainActivity.MO_CHUYENPB);
    }
    /**
     * hàm cho phép xóa nhân viên hiện tại
     * (đã quen thuộc quá)
     */
    public void doXoaNhanVien()
    {
        AlertDialog.Builder builder=new AlertDialog.Builder (this);
        builder.setTitle("Hỏi xóa");
        builder.setMessage("Bạn có chắc chắn muốn xóa ["+nvSelected.getTen()+"]");
        builder.setIcon(android.R.drawable.ic_input_delete);
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                // TODO Auto-generated method stub
                arg0.cancel();
            }
        });
        builder.setPositiveButton("Ừ", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                arrNhanvien.remove(nvSelected);
                adapter.notifyDataSetChanged();
            }
        });
        builder.show();
    }
    /**
     * khi nhấn nút Back (hình back)
     * thì truyền thông số về MainActivity để cập nhật
     * phòng ban
     */
    public void doUpdateToMain()
    {
        Intent i=getIntent();
        Bundle b=new Bundle();
        b.putSerializable("PHONGBAN", pb);
        i.putExtra("DATA", b);
        setResult(MainActivity.UPDATE_PASS, i);
        finish();
    }
}
