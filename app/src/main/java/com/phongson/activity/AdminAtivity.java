package com.phongson.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.phongson.R;
import com.phongson.model.ChuyenMuc;
import com.phongson.model.LinkTinTuc;
import com.phongson.model.NguonTinTuc;

public class AdminAtivity extends AppCompatActivity {
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    EditText idChuyenmuc,TenChuyenmuc,idNguon,TenNguon,idLink,tenLink;
    Button btnThemChuyenMuc,btnNguon,btnLink, btnCapNhat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        addControls();
        addEvent();

    }

    private void addEvent() {
        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnThemChuyenMuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChuyenMuc chuyenMuc = new ChuyenMuc();
                String id = mDatabase.push().getKey();
                chuyenMuc.setIdChuyenMuc(id);
                chuyenMuc.setTenChuyenMuc(TenChuyenmuc.getText().toString());
                mDatabase.child("ChuyenMuc").child(id).setValue(chuyenMuc);

            }
        });
        btnNguon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NguonTinTuc nguonTinTuc = new NguonTinTuc();
                String id = mDatabase.push().getKey();
                nguonTinTuc.setIdNguon(id);
                nguonTinTuc.setTenNguon(TenNguon.getText().toString());
                mDatabase.child("NguonTinTuc").child(id).setValue(nguonTinTuc);
            }
        });
        btnLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinkTinTuc linkTinTuc = new LinkTinTuc();
                String id = mDatabase.push().getKey();
                linkTinTuc.setIdLink(id);
                linkTinTuc.setDuongLink("");
                linkTinTuc.setIdNguon("");
                linkTinTuc.setIdChuyenMuc("");
                mDatabase.child("LinkTinTuc").child(id).setValue(linkTinTuc);
            }
        });
    }

    private void addControls() {
        idChuyenmuc = findViewById(R.id.idChuyenmuc);
        TenChuyenmuc = findViewById(R.id.TenChuyenmuc);
        idNguon = findViewById(R.id.TenNguon);
        TenNguon = findViewById(R.id.TenNguon);
        idLink = findViewById(R.id.idLink);
        tenLink = findViewById(R.id.TenLink);
        btnThemChuyenMuc = findViewById(R.id.btnThemChuyenMuc);
        btnNguon= findViewById(R.id.btnNguon);
        btnLink = findViewById(R.id.btnLink);
        btnCapNhat = findViewById(R.id.btnCapNhatTinTuc);
    }
}
