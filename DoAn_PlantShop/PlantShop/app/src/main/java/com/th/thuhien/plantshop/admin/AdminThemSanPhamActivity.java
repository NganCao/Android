package com.th.thuhien.plantshop.admin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.th.thuhien.plantshop.R;
import com.th.thuhien.plantshop.adapter.MenuAdapter;
import com.th.thuhien.plantshop.model.Menu;
import com.th.thuhien.plantshop.ultil.MenuService;
import com.th.thuhien.plantshop.ultil.SanPhamService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdminThemSanPhamActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_CODE = 100;
    private Toolbar toolbarAdminThemSp;
    private EditText edt_ThemTenSp, edt_ThemGiaSp, edt_ThemThongTinSp;
    private Button btn_ThemSpSave;
    private Spinner spinnerAdminThemSpMenu;
    private ImageView img_hinhMinhHoaThemSp;

    private ProgressDialog dialog;

    StorageReference storageReference;

    ArrayList<Menu> listMenu;
    MenuAdapter menuAdapter;

    String mamenu = "";
    String txt_url = "";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_CODE && data.getData() != null){
            //dialog.setTitle("Loading...");
            //dialog.show();
            dialog = ProgressDialog.show(AdminThemSanPhamActivity.this, "",
                    "Loading. Please wait...", true);
            UploadTask uploadTask = storageReference.putFile(data.getData());

            Task<Uri> task = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()){
                        Toast.makeText(AdminThemSanPhamActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                    return storageReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()){
                        String url = task.getResult().toString().substring(0, task.getResult().toString().indexOf("&token"));
                        //String url = task.getResult().toString();
                        Log.d("DIRECTLINK", url);
                        txt_url = url;
                        Picasso.with(AdminThemSanPhamActivity.this).load(url).into(img_hinhMinhHoaThemSp);
                        //Toast.makeText(getApplicationContext(), "Complete", Toast.LENGTH_LONG).show();
                        //dialog.dismiss();
                        dialog.dismiss();
                    }
                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_them_san_pham);

        //storageReference = FirebaseStorage.getInstance().getReference("image_upload_"+ );

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String currentDateandTime = sdf.format(new Date());

        storageReference = FirebaseStorage.getInstance().getReference("product_" + currentDateandTime);


        AnhXa();
        ActionBar();
        setImage();
        setDataSpinnerMenu();
        setEventButtonSave();
    }

    private void setImage() {
        img_hinhMinhHoaThemSp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Selected picture"), PICK_IMAGE_CODE);
            }
        });
    }

    private void setDataSpinnerMenu() {
        AsynAdminMenu asynAdminMenu = new AsynAdminMenu();
        asynAdminMenu.execute();
    }

    private void setEventSpinnerMenu() {
        spinnerAdminThemSpMenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mamenu = String.valueOf(listMenu.get(position).getMaMenu());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setEventButtonSave() {
        final String url_HinhSp = "https://4.bp.blogspot.com/-EfuHbKEuRF0/XNPqKPPT8bI/AAAAAAAAGUs/2EaZQ1UIYiMGOA3ma_tDrZcBabk5voiegCLcBGAs/s1600/product.png"; // vì chưa làm được nên để mặc định
        btn_ThemSpSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setEventSpinnerMenu();
                if (edt_ThemTenSp.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Tên không được rỗng", Toast.LENGTH_LONG).show();
                    return;
                }
                if (edt_ThemGiaSp.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Giá không được rỗng", Toast.LENGTH_LONG).show();
                    return;
                }
                String tensp = edt_ThemTenSp.getText().toString();
                String giasp = edt_ThemGiaSp.getText().toString();
                String thongtinsp = edt_ThemThongTinSp.getText().toString();

                if (txt_url.equals("")){
                    txt_url = url_HinhSp;
                }
                AsynAdminThemSp asynAdminThemSp = new AsynAdminThemSp();
                asynAdminThemSp.execute(tensp, url_HinhSp, thongtinsp, giasp, mamenu);
            }
        });


    }

    private void ActionBar() {
        setSupportActionBar(toolbarAdminThemSp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarAdminThemSp.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void AnhXa() {
        toolbarAdminThemSp = (Toolbar) findViewById(R.id.toolbarAdminThemSanPham);
        img_hinhMinhHoaThemSp = (ImageView) findViewById(R.id.imageThemHinhSp);
        edt_ThemTenSp = (EditText) findViewById(R.id.edittextThemTenSp);
        edt_ThemGiaSp = (EditText) findViewById(R.id.edittextThemGiaSp);
        edt_ThemThongTinSp = (EditText) findViewById(R.id.edittextThemThongTinSp);
        spinnerAdminThemSpMenu = (Spinner) findViewById(R.id.spinnerThemSpMenu);
        btn_ThemSpSave = (Button) findViewById(R.id.buttonThemSpSave);

        listMenu = new ArrayList<>();
        menuAdapter = new MenuAdapter(listMenu, getApplicationContext());
        spinnerAdminThemSpMenu.setAdapter(menuAdapter);
    }

    private class AsynAdminMenu extends AsyncTask<Void, Void, List<Menu>>{

        @Override
        protected List<Menu> doInBackground(Void... voids) {
            MenuService menuService = new MenuService();
            return menuService.getListMenu();
        }

        @Override
        protected void onPostExecute(List<Menu> menus) {
            super.onPostExecute(menus);
            for (int i = 0; i < menus.size(); i++){
                listMenu.add(menus.get(i));
            }
            menuAdapter.notifyDataSetChanged();
        }
    }

    private class AsynAdminThemSp extends AsyncTask<String, Void, Boolean>{

        @Override
        protected Boolean doInBackground(String... strings) {
            SanPhamService sanPhamService = new SanPhamService();
            return sanPhamService.insetSanPham(strings[0], strings[1], strings[2], strings[3], strings[4]);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean == true){
                Toast.makeText(getApplicationContext(), "Thêm sản phẩm THÀNH CÔNG", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(AdminThemSanPhamActivity.this, AdminSanPhamActivity.class);
                startActivity(intent);
            }else {
                Toast.makeText(getApplicationContext(), "Thêm sản phẩm THẤT BẠI", Toast.LENGTH_LONG).show();

            }
        }
    }
}
