package com.phongson.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.phongson.R;
import com.phongson.activity.LichSuDocActivity;
import com.phongson.activity.Main2Activity;
import com.phongson.activity.MainActivity;
import com.phongson.activity.SearchActivity;
import com.phongson.model.TinDaLuu;

import java.util.List;

public class LichSuDocAdapter extends ArrayAdapter<TinDaLuu> {
    Activity context;
    int resource;
    List<TinDaLuu> objects;
    public LichSuDocAdapter(@NonNull Activity context, int resource, @NonNull List<TinDaLuu> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(getContext());
        view = inflater.inflate(R.layout.item_lichsudoc,null);
        TextView textView = view.findViewById(R.id.txtTieuDe);

        final TinDaLuu tinDaLuu = objects.get(position);

//        {
//            btnXoa.setVisibility(View.INVISIBLE);
//        }
//        else btnXoa.setVisibility(View.VISIBLE);
//        btnXoa.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MainActivity.mDatabase.child("TinDaLuu").child(tinDaLuu.getIdTin()).removeValue();
//                objects.remove(position);
//            }
//        });
        textView.setText(tinDaLuu.getTieuDe());

        return view;


    }

}
