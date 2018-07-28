package com.phongson.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.phongson.R;
import com.phongson.adapter.ChonChuyenMucAdapter;
import com.phongson.model.ChuyenMuc;

import java.util.ArrayList;


public class ChonChuyenMucActivity extends AppCompatActivity {

    ArrayList<ChuyenMuc> listChuyenMuc;
    public static ArrayList<ChuyenMuc> listChonChuyenMuc;
    Button btnThayDoi;
    public static ArrayList<Integer> ViTri = new ArrayList<>();
    ListView listView;
    ChonChuyenMucAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_chuyen_muc);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        addControl();
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
    private ChuyenMuc getTrangChu() {
        for (int i= 0;i<MainActivity.listChuyenMuc.size();i++)
        {
            if (MainActivity.listChuyenMuc.get(i).getTenChuyenMuc().equals("Trang Chủ"))
                return MainActivity.listChuyenMuc.get(i);
        }
        return null;
    }

    private int getViTriTrangChu() {
        for (int i= 0;i<MainActivity.listChuyenMuc.size();i++)
        {
            if (MainActivity.listChuyenMuc.get(i).getTenChuyenMuc().equals("Trang Chủ"))
                return i;
        }
        return -1;
    }

    private void addEvents() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(kiemtrachon(position)==1)
                {
                    view.setBackgroundColor(Color.WHITE);
                    xoaChiTiet(listChuyenMuc.get(position));
                }
                else {
                    view.setBackgroundColor(Color.GRAY);
                    themVT(position);
                    themCTMH(listChuyenMuc.get(position));
                }
            }
        });
        btnThayDoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChonChuyenMucActivity.this,Main2Activity.class);
                intent.putExtra("ThayDoi",1);
                startActivity(intent);
            }
        });
    }
    private void themCTMH(ChuyenMuc chuyenMuc) {
        listChonChuyenMuc.add(chuyenMuc);
    }
    private void themVT(int position) {
        ViTri.add(position);
    }
    private void addControl() {
        ViTri.clear();
        listChuyenMuc = new ArrayList<>();
        listChonChuyenMuc = new ArrayList<>();
        listChonChuyenMuc.add(getTrangChu());
        setListChuyenMuc();
        listChuyenMuc.remove(getViTriTrangChu());
        listView = findViewById(R.id.listview);
        btnThayDoi = findViewById(R.id.btnThayDoi);
        adapter = new ChonChuyenMucAdapter(this,R.layout.item_chuyenmuc,listChuyenMuc);
        listView.setAdapter(adapter);
    }

    private void setListChuyenMuc() {
        for (int i = 0;i<MainActivity.listChuyenMuc.size();i++)
        {
            ChuyenMuc chuyenMuc;
            chuyenMuc = MainActivity.listChuyenMuc.get(i);
            listChuyenMuc.add(chuyenMuc);
        }
    }

    private void xoaChiTiet(ChuyenMuc chuyenMuc) {
        String machuyenmuc = chuyenMuc.getIdChuyenMuc();
        for (int i = listChonChuyenMuc.size()-1;i>=0;i--)
        {
            if (machuyenmuc.equals(listChonChuyenMuc.get(i).getIdChuyenMuc()))
            {
                listChonChuyenMuc.remove(i);
                break;
            }
        }
    }
    private int kiemtrachon(int position) {
        for (int i = ViTri.size()-1;i>=0;i--)
        {
            if (ViTri.get(i)==position) {
                ViTri.remove(i);
                return 1;
            }
        }
        return 0;
    }
}
