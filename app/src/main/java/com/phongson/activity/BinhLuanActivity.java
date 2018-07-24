package com.phongson.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.phongson.R;
import com.phongson.adapter.BinhLuanAdapter;
import com.phongson.model.BinhLuan;

import java.util.ArrayList;
import java.util.Date;

public class BinhLuanActivity extends AppCompatActivity {
    String ID_TinTuc;
    BinhLuanAdapter adapter;
    ListView listView;
    ArrayList<BinhLuan> listBinhLuan;

    // binh luan

    EditText edtBinhLuan;
    ImageButton btnBinhLuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binh_luan);
        Intent intent = getIntent();
        ID_TinTuc = intent.getStringExtra("ID_TinTuc");

        addConTrols();
        getDataBinhLuan();
        addEvents();
    }

    private void addEvents() {
        btnBinhLuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtBinhLuan.getText().toString().equals("")||edtBinhLuan.getText().toString().length()<4) {
                    Toast.makeText(BinhLuanActivity.this, "Binh Luan Nhieu Hon 3 ky tu", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (AccessToken.getCurrentAccessToken()!=null)
                {
                    // Date date = new Date();
                    String id = MainActivity.mDatabase.push().getKey();
                    BinhLuan binhLuan = new BinhLuan(id,ID_TinTuc,edtBinhLuan.getText().toString(),Main2Activity.ID_USER,null);
                    MainActivity.mDatabase.child("BinhLuan").child(ID_TinTuc).child(id).setValue(binhLuan);
                    hienthi();
                }else {
                    Toast.makeText(BinhLuanActivity.this, "Ban Chua Dang Nhap", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    void hienthi()
    {
        adapter = new BinhLuanAdapter(BinhLuanActivity.this,R.layout.item_binhluan,listBinhLuan);
        listView.setAdapter(adapter);
    }
    private void getDataBinhLuan() {
        MainActivity.mDatabase.child("BinhLuan").child(ID_TinTuc).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                BinhLuan binhLuan = dataSnapshot.getValue(BinhLuan.class);
                listBinhLuan.add(binhLuan);
                adapter = new BinhLuanAdapter(BinhLuanActivity.this,R.layout.item_binhluan,listBinhLuan);
                listView.setAdapter(adapter);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void addConTrols() {
        listBinhLuan = new ArrayList<>();
        listView = findViewById(R.id.listview);
        btnBinhLuan = findViewById(R.id.btnBinhLuan);
        edtBinhLuan = findViewById(R.id.edtBinhLuan);
    }
}
