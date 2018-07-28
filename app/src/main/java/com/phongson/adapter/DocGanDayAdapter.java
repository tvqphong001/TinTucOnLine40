package com.phongson.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.phongson.R;
import com.phongson.activity.LichSuDocActivity;
import com.phongson.activity.MainActivity;
import com.phongson.model.DocGanDay;
import com.phongson.model.TinDaLuu;

import java.util.List;

public class DocGanDayAdapter extends ArrayAdapter {
    Activity context;
    int resource;
    List<DocGanDay> objects;
    public DocGanDayAdapter(@NonNull Activity context, int resource, @NonNull List<DocGanDay> objects) {
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
        DocGanDay docGanDay = objects.get(position);

//        Button btnXoa = view.findViewById(R.id.btnXoa);
//        if (LichSuDocActivity.trangthaichon==0)
//        {
//            btnXoa.setVisibility(View.INVISIBLE);
//        }
//        else btnXoa.setVisibility(View.VISIBLE);
//        btnXoa.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MainActivity.mDatabase.child("DocGanDay").child(objects.get(position).getIdTin()).removeValue();
//                objects.remove(position);
//            }
//        });


        textView.setText(docGanDay.getTieuDe());

        return view;


    }

}
