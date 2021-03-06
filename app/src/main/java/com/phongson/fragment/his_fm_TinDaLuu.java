package com.phongson.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.facebook.AccessToken;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.phongson.R;
import com.phongson.activity.Main2Activity;
import com.phongson.activity.MainActivity;
import com.phongson.activity.TinActivity;
import com.phongson.adapter.LichSuDocAdapter;
import com.phongson.model.TinDaLuu;

import java.util.ArrayList;

public class his_fm_TinDaLuu extends Fragment {
    ArrayList<TinDaLuu> listTinDaLuu;
    ListView lvTinDaLuu;
    LichSuDocAdapter adapter;
    String ID_USER;
    View fm_view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.his_fm_tindaluu, container, false);
        return myView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fm_view =view;
        Intent intent1 = getActivity().getIntent();
        ID_USER=intent1.getStringExtra("ID_USER");
        addControls();
        if (AccessToken.getCurrentAccessToken()!=null) {
            getDada();
        }
        addEvents();

    }

    private void addEvents() {
        lvTinDaLuu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), TinActivity.class);
                intent.putExtra("link",listTinDaLuu.get(position).getLinkTinTuc());
                intent.putExtra("ID_USER", Main2Activity.ID_USER);
                intent.putExtra("TinTuc",listTinDaLuu.get(position));
                intent.putExtra("LichSuDoc", 1);
                intent.putExtra("ID_TinDaLuu",listTinDaLuu.get(position).getIdTin());
                startActivity(intent);
            }
        });
        lvTinDaLuu.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                MainActivity.mDatabase.child("TinDaLuu").child(ID_USER).child(listTinDaLuu.get(position).getIdTin()).removeValue();
                Main2Activity.listTinDaLuu.remove(position);
                return false;
            }
        });
    }

    private void getDada() {
        MainActivity.mDatabase.child("TinDaLuu").child(ID_USER).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                TinDaLuu tinDaLuu = dataSnapshot.getValue(TinDaLuu.class);
                listTinDaLuu.add(tinDaLuu);
                hienthi();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                TinDaLuu tinDaLuu = dataSnapshot.getValue(TinDaLuu.class);
                if (tim(tinDaLuu.getIdTin())!=-1)
                {
                    listTinDaLuu.set(tim(tinDaLuu.getIdTin()),tinDaLuu);
                }
                hienthi();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                TinDaLuu tinDaLuu = dataSnapshot.getValue(TinDaLuu.class);
                if (tim(tinDaLuu.getIdTin())!=-1)
                {
                    listTinDaLuu.remove(tim(tinDaLuu.getIdTin()));
                }
                hienthi();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void hienthi() {
        adapter = new LichSuDocAdapter(getActivity(),R.layout.item_lichsudoc,listTinDaLuu);
        lvTinDaLuu.setAdapter(adapter);
    }

    private int tim(String id){
        for (int i=0;i<listTinDaLuu.size();i++)
        {
            if (id.equals(listTinDaLuu.get(i).getIdTin()))
                return i;
        }
        return -1;
    }
    private void addControls() {
        listTinDaLuu = new ArrayList<>();
        lvTinDaLuu = fm_view.findViewById(R.id.listview);
    }
}
