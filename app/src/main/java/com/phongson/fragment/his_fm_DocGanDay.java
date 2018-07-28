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
import com.phongson.adapter.DocGanDayAdapter;
import com.phongson.adapter.LichSuDocAdapter;
import com.phongson.model.DocGanDay;
import com.phongson.model.TinDaLuu;

import java.util.ArrayList;

public class his_fm_DocGanDay extends Fragment {
    String ID_USER;
    ArrayList<DocGanDay> listDocGanDay;
    ListView lvDocGanDay;
    DocGanDayAdapter adapter;
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
        fm_view = view;
        Intent intent1 = getActivity().getIntent();
        ID_USER = intent1.getStringExtra("ID_USER");
        addControls();
        if (AccessToken.getCurrentAccessToken() != null) {
            getDada();
        }
        addEvents();
    }

    private void addEvents() {
        lvDocGanDay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), TinActivity.class);
                intent.putExtra("link", listDocGanDay.get(position).getLinkTinTuc());
                intent.putExtra("ID_USER", Main2Activity.ID_USER);
                intent.putExtra("TinTuc", listDocGanDay.get(position));
                intent.putExtra("LichSuDoc", 1);
                startActivity(intent);
            }
        });
        lvDocGanDay.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                MainActivity.mDatabase.child("DocGanDay").child(ID_USER).child(listDocGanDay.get(position).getIdTin()).removeValue();
                Main2Activity.listDocGanDay.remove(position);
                hienthi();
                return false;
            }
        });
    }

    private void addControls() {
        listDocGanDay = new ArrayList<>();
        lvDocGanDay = fm_view.findViewById(R.id.listview);
    }

    private int tim(String id) {
        for (int i = 0; i < listDocGanDay.size(); i++) {
            if (id.equals(listDocGanDay.get(i).getIdTin()))
                return i;
        }
        return -1;
    }

    private void hienthi() {
        adapter = new DocGanDayAdapter(getActivity(), R.layout.item_lichsudoc, listDocGanDay);
        lvDocGanDay.setAdapter(adapter);
    }

    private void getDada() {
        MainActivity.mDatabase.child("DocGanDay").child(ID_USER).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                DocGanDay docGanDay = dataSnapshot.getValue(DocGanDay.class);
                listDocGanDay.add(docGanDay);
                hienthi();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                DocGanDay docGanDay = dataSnapshot.getValue(DocGanDay.class);
                if (tim(docGanDay.getIdTin()) != -1) {
                    listDocGanDay.set(tim(docGanDay.getIdTin()), docGanDay);
                }
                hienthi();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                DocGanDay docGanDay = dataSnapshot.getValue(DocGanDay.class);
                if (tim(docGanDay.getIdTin()) != -1) {
                    listDocGanDay.remove(tim(docGanDay.getIdTin()));
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
}
