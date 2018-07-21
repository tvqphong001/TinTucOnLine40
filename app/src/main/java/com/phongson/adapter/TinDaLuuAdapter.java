package com.phongson.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.phongson.R;
import com.phongson.model.TinDaLuu;

import java.util.List;

public class TinDaLuuAdapter extends ArrayAdapter<TinDaLuu> {
    Activity context;
    int resource;
    List<TinDaLuu> objects;
    public TinDaLuuAdapter(@NonNull Activity context, int resource, @NonNull List<TinDaLuu> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(getContext());
        view = inflater.inflate(R.layout.item_lichsudoc,null);
        TextView textView = view.findViewById(R.id.txtTieuDe);


        TinDaLuu tinDaLuu = objects.get(position);
        textView.setText(tinDaLuu.getTieuDe());

        return view;


    }
}
