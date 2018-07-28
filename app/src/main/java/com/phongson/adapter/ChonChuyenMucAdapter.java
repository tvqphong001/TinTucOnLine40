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
        if (chuyenMuc.getTenChuyenMuc().equals("Thời Sự"))
        {
            imageView.setImageResource(R.drawable.tintuc);
        }
        if (chuyenMuc.getTenChuyenMuc().equals("Giải Trí"))
        {
            imageView.setImageResource(R.drawable.giaitri);
        }
        if (chuyenMuc.getTenChuyenMuc().equals("Giáo Dục"))
        {
            imageView.setImageResource(R.drawable.tinhduc);
        }
        if (chuyenMuc.getTenChuyenMuc().equals("Thể Thao"))
        {
            imageView.setImageResource(R.drawable.thethao);
        }
        if (chuyenMuc.getTenChuyenMuc().equals("Công Nghệ"))
        {
            imageView.setImageResource(R.drawable.congnghe);
        }
        if (chuyenMuc.getTenChuyenMuc().equals("Video"))
        {
            imageView.setImageResource(R.drawable.videocm);
        }
        if (chuyenMuc.getTenChuyenMuc().equals("Sức Khỏe"))
        {
            imageView.setImageResource(R.drawable.suckhoe);
        }



        return view;

    }
}
