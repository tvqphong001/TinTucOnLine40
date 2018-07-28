package com.phongson.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.phongson.R;
import com.phongson.model.BinhLuan;

public class CapNhatBinhLuanActivity extends AppCompatActivity {

    EditText edtCapNhatBinhLuan;
    Button btnCapNhat , btnHuyCapNhat;
    BinhLuan binhLuan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cap_nhat_binh_luan);
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
    private void addEvents() {
        btnHuyCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CapNhatBinhLuanActivity.this , BinhLuanActivity.class);
                startActivity(intent);
            }
        });
        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CapNhatBinhLuanActivity.this , BinhLuanActivity.class);
                binhLuan.setNoiDung(edtCapNhatBinhLuan.getText().toString());
                MainActivity.mDatabase.child("BinhLuan").child(binhLuan.getIdTin()).child(binhLuan.getIdBinhLuan()).setValue(binhLuan);
                startActivity(intent);
            }
        });
    }

    private void addControl() {
        edtCapNhatBinhLuan = findViewById(R.id.edtCapNhatBinhLuan);
        btnCapNhat = findViewById(R.id.btnCapNhat);
        btnHuyCapNhat = findViewById(R.id.btnHuyCapNhat);

        Intent intent = getIntent();
        binhLuan = (BinhLuan) intent.getSerializableExtra("BinhLuan");

        edtCapNhatBinhLuan.setText(binhLuan.getNoiDung());
    }
}
