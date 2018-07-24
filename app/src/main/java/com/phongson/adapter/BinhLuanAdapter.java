package com.phongson.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.phongson.R;
import com.phongson.model.BinhLuan;
import com.squareup.picasso.Picasso;

import java.util.List;


public class BinhLuanAdapter extends ArrayAdapter<BinhLuan> {
    Activity context;
    int resource;
    List<BinhLuan> objects;
    public BinhLuanAdapter(@NonNull Activity context, int resource, @NonNull List<BinhLuan> objects) {
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
        view = inflater.inflate(R.layout.item_binhluan,null);

        BinhLuan binhLuan = objects.get(position);

        ImageView imvNguoiBinhLuan = view.findViewById(R.id.imvNguoiBinhLuan);
        TextView txtNguoiBinhLuan = view.findViewById(R.id.txtTenNguoiBinhLuan);
        TextView txtNoiDungBinhLuan = view.findViewById(R.id.txtNoiDungBinhLuan);

        if(binhLuan.getIdUser()!="") {
            String profilePicUrl = "https://graph.facebook.com/" + binhLuan.getIdUser() + "/picture?type=large";
            Picasso.get().load(profilePicUrl).into(imvNguoiBinhLuan);
        }
        txtNguoiBinhLuan.setText("Nguoi Binh Luan");
        txtNoiDungBinhLuan.setText(binhLuan.getNoiDung());
        return view;
    }
}
