package com.phongson.activity;

import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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
    Button btnXoa, btnSua , btnThoat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binh_luan);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        ID_TinTuc = TinActivity.tinTuc.getIdTin();

        addConTrols();
        getDataBinhLuan();
        addEvents();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
                    edtBinhLuan.setText("");
                }else {
                    Toast.makeText(BinhLuanActivity.this, "Ban Chua Dang Nhap", Toast.LENGTH_SHORT).show();
                }

            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                if(AccessToken.getCurrentAccessToken()!=null && listBinhLuan.get(position).getIdUser().equals(Main2Activity.ID_USER))
                {
                final Dialog dialog = new Dialog(BinhLuanActivity.this);
                dialog.setContentView(R.layout.dialog_binh_luan);
                btnXoa = dialog.findViewById(R.id.btnXoa);
                btnSua = dialog.findViewById(R.id.btnSua);
                btnThoat = dialog.findViewById(R.id.btnThoat);

                btnXoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MainActivity.mDatabase.child("BinhLuan").child(ID_TinTuc).child(listBinhLuan.get(position).getIdBinhLuan()).removeValue();
                        listBinhLuan.remove(position);
                       hienthi();
                        dialog.cancel();
                    }
                });
                btnThoat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                btnSua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(BinhLuanActivity.this , CapNhatBinhLuanActivity.class);
                        intent.putExtra("BinhLuan",listBinhLuan.get(position));
                        startActivity(intent);
                    }
                });
                dialog.show();
                }
                return false;
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
                hienthi();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                BinhLuan binhLuan = dataSnapshot.getValue(BinhLuan.class);
                if (tim(binhLuan.getIdBinhLuan())!=-1)
                {
                    int vitri = tim(binhLuan.getIdBinhLuan());
                    listBinhLuan.set(vitri,binhLuan);
                }
                hienthi();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                BinhLuan binhLuan = dataSnapshot.getValue(BinhLuan.class);
                if (tim(binhLuan.getIdBinhLuan())!=-1)
                {
                    int vitri = tim(binhLuan.getIdBinhLuan());
                    listBinhLuan.remove(vitri);
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


    private void addConTrols() {
        listBinhLuan = new ArrayList<>();
        listView = findViewById(R.id.listview);
        btnBinhLuan = findViewById(R.id.btnBinhLuan);
        edtBinhLuan = findViewById(R.id.edtBinhLuan);
    }
    private  int tim(String id)
    {
        for (int i=0;i<listBinhLuan.size();i++)
        {
            if (id.equals(listBinhLuan.get(i).getIdBinhLuan()))
                return i;
        }
        return  -1;
    }
}
