package com.phongson.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.phongson.R;
import com.phongson.activity.ChonChuyenMucActivity;
import com.phongson.model.ChuyenMuc;

import java.util.List;

public class ChonChuyenMucAdapter extends ArrayAdapter {
    Activity context;
    int resource;
    List<ChuyenMuc> objects;
    public ChonChuyenMucAdapter(@NonNull Activity context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.context =context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(getContext());
        view = inflater.inflate(R.layout.item_chuyenmuc,null);
        ChuyenMuc chuyenMuc = objects.get(position);

        TextView tenchuyemuc = view.findViewById(R.id.tenchuyenmuc);
        ImageView imageView = view.findViewById(R.id.chon);

        tenchuyemuc.setText(chuyenMuc.getTenChuyenMuc());
        for (int i = 0; i< ChonChuyenMucActivity.ViTri.size(); i++)
        {
            if (position==ChonChuyenMucActivity.ViTri.get(i))
            {
                view.setBackgroundColor(Color.GRAY);
            }
        }
        return view;
    }
}
