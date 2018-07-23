package com.phongson.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.phongson.R;
import com.phongson.model.ChuyenMuc;
import com.phongson.model.LinkTinTuc;
import com.phongson.model.NguonTinTuc;
import com.phongson.model.TinDaLuu;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<ChuyenMuc> listChuyenMuc;
    public static DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    public static ArrayList<NguonTinTuc> listNgonTinTuc;
    public static ArrayList<LinkTinTuc> listLinkTinTuc;
    public static int UngDungDangHoatDong = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getDataChuyenMuc();
        getdataNguonTinTuc();
        getdataLinkTinTuc();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this,Main2Activity.class));
            }
        },5000);


    }

    private void getdataLinkTinTuc() {
        listLinkTinTuc = new ArrayList<>();
        mDatabase.child("LinkTinTuc").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                LinkTinTuc linkTinTuc = dataSnapshot.getValue(LinkTinTuc.class);
                listLinkTinTuc.add(linkTinTuc);
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

    private void getdataNguonTinTuc() {
        listNgonTinTuc = new ArrayList<>();
        mDatabase.child("NguonTinTuc").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                NguonTinTuc nguonTinTuc = dataSnapshot.getValue(NguonTinTuc.class);
                listNgonTinTuc.add(nguonTinTuc);
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

    private void getDataChuyenMuc() {
        listChuyenMuc = new ArrayList<>();
        mDatabase.child("ChuyenMuc").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                ChuyenMuc chuyenMuc = dataSnapshot.getValue(ChuyenMuc.class);
                listChuyenMuc.add(chuyenMuc);

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
}
