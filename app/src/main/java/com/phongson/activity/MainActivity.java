package com.phongson.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.phongson.R;
import com.phongson.adapter.TinDaLuuAdapter;
import com.phongson.model.ChuyenMuc;
import com.phongson.model.TinDaLuu;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<ChuyenMuc> listChuyenMuc;
    public static DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    public static ArrayList<TinDaLuu> listTinDaLuu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getDataChuyenMuc();
        getDataTinDaLuu();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this,Main2Activity.class));
            }
        },5000);


    }

    private void getDataTinDaLuu() {
        listTinDaLuu = new ArrayList<>();
        MainActivity.mDatabase.child("TinDaLuu").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                TinDaLuu tinDaLuu = dataSnapshot.getValue(TinDaLuu.class);
                listTinDaLuu.add(tinDaLuu);

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
