package com.phongson.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.phongson.R;
import com.phongson.activity.Main2Activity;
import com.phongson.activity.MainActivity;
import com.phongson.activity.TinActivity;
import com.phongson.adapter.TinTucAdapter;
import com.phongson.model.TinTuc;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class fm_TheThao extends Fragment {
    ArrayList<TinTuc> list;
    ListView listView;
    TinTucAdapter adapter;
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fm_thethao, container, false);
        return myView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list = new ArrayList<>();
        listView = view.findViewById(R.id.listview);
//        // lay du lieu tu firebase ve
        getdataTinTuc();
//
//        // Chon moi item trong list view sẽ lấy đường link tương ứng
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), TinActivity.class);
                intent.putExtra("link",list.get(position).getLinkTinTuc());
                intent.putExtra("ID_USER", Main2Activity.ID_USER);
                startActivity(intent);
            }
        });
    }

    private void getdataTinTuc() {
        MainActivity.mDatabase.child("TinTuc").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                TinTuc tinTuc = dataSnapshot.getValue(TinTuc.class);
                if(timChuyenMuc(tinTuc.getIdLink()).equals("Thể Thao")) {
                    list.add(tinTuc);
                }
                adapter = new TinTucAdapter(getActivity(),R.layout.item_list_tintucc,list);
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
    private String timChuyenMuc(String idLink) {
        for(int i =0; i<MainActivity.listLinkTinTuc.size();i++)
        {
            if (idLink.equals(MainActivity.listLinkTinTuc.get(i).getIdLink()))
            {
                if (timTenChuyenMuc(MainActivity.listLinkTinTuc.get(i).getIdChuyenMuc())!="")
                    return timTenChuyenMuc(MainActivity.listLinkTinTuc.get(i).getIdChuyenMuc());
            }
        }
        return "";
    }

    private String timTenChuyenMuc(String idChuyenMuc) {
        for (int i = 0;i< MainActivity.listChuyenMuc.size();i++)
        {
            if (idChuyenMuc.equals(MainActivity.listChuyenMuc.get(i).getIdChuyenMuc()))
            {
                return MainActivity.listChuyenMuc.get(i).getTenChuyenMuc();
            }
        }
        return "";
    }
}
