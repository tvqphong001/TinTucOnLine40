package com.phongson.activity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import com.facebook.AccessToken;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.phongson.R;
import com.phongson.adapter.TinDaLuuAdapter;
import com.phongson.model.TinDaLuu;

import java.util.ArrayList;

public class LichSuDocActivity extends AppCompatActivity {

    ArrayList<TinDaLuu> ListTinDaLuu;
    ListView lvLichSuDoc;
    TinDaLuuAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_doc);
        addControls();
        addEvents();
        getDaTaLichSu();



    }

    private void addEvents() {
        lvLichSuDoc.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                ListTinDaLuu.remove(position);
                MainActivity.mDatabase.child("TinDaLuu").child(ListTinDaLuu.get(position).getIdTin()).removeValue();
                adapter = new TinDaLuuAdapter(LichSuDocActivity.this,R.layout.item_lichsudoc,ListTinDaLuu);
                lvLichSuDoc.setAdapter(adapter);
                return false;
            }
        });
    }

    private void getDaTaLichSu() {

        if (AccessToken.getCurrentAccessToken() != null) {
            MainActivity.mDatabase.child("TinDaLuu").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    TinDaLuu tinDaLuu = dataSnapshot.getValue(TinDaLuu.class);
                    ListTinDaLuu.add(tinDaLuu);
                    adapter = new TinDaLuuAdapter(LichSuDocActivity.this,R.layout.item_lichsudoc,ListTinDaLuu);
                    lvLichSuDoc.setAdapter(adapter);

                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                    TinDaLuu tinDaLuu = dataSnapshot.getValue(TinDaLuu.class);
                    if (tim(tinDaLuu.getIdTin())!=-1)
                    {
                        int vitri = tim(tinDaLuu.getIdTin());
                        ListTinDaLuu.remove(vitri);
                    }
                    adapter = new TinDaLuuAdapter(LichSuDocActivity.this,R.layout.item_lichsudoc,ListTinDaLuu);
                    lvLichSuDoc.setAdapter(adapter);
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

    private void addControls() {
        ListTinDaLuu = new ArrayList<>();
        lvLichSuDoc = findViewById(R.id.lvLichSuDoc);
    }
    private int tim(String id){
        for (int i=0;i<ListTinDaLuu.size();i++)
        {
            if (id.equals(ListTinDaLuu.get(i).getIdTin()))
                return i;
        }
        return -1;
    }
}
