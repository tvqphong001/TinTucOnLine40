package com.phongson.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.facebook.AccessToken;
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
import com.phongson.model.User;
import com.phongson.util.NetworkStateChangeReceiver;

import java.util.ArrayList;

import static com.phongson.util.NetworkStateChangeReceiver.IS_NETWORK_AVAILABLE;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<ChuyenMuc> listChuyenMuc;
    public static DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    public static ArrayList<NguonTinTuc> listNgonTinTuc;
    public static ArrayList<LinkTinTuc> listLinkTinTuc;
    public static ArrayList<User> listUser;
    public static AccessToken accessToken = AccessToken.getCurrentAccessToken();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (checkNetwork()) {
            getDataChuyenMuc();
            getdataNguonTinTuc();
            getdataLinkTinTuc();
            getDataUser();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(MainActivity.this, Main2Activity.class));
                }
            }, 5000);

        }
        else Toast.makeText(this, "Vui Long Kiem Tra Internet!", Toast.LENGTH_SHORT).show();

    }

    private void getDataUser() {
        listUser = new ArrayList<>();
        mDatabase.child("User").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                User user= dataSnapshot.getValue(User.class);
                listUser.add(user);
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

    private boolean checkNetwork() {
        NetworkStateChangeReceiver networkStateChangeReceiver = new NetworkStateChangeReceiver();
        boolean internet = networkStateChangeReceiver.isConnectedToInternet(MainActivity.this);
        return internet;
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
