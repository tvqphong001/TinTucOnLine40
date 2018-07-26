package com.phongson.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.phongson.R;

public class CapNhatBinhLuanActivity extends AppCompatActivity {

    EditText edtCapNhatBinhLuan;
    Button btnCapNhat , btnHuyCapNhat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cap_nhat_binh_luan);

        addControl();
        addEvents();

    }

    private void addEvents() {
        
    }

    private void addControl() {

    }
}
