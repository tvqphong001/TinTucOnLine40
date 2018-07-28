package com.phongson.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.phongson.model.TinDaLuu;

import java.util.List;

public class DocGanDayAdapter extends LichSuDocAdapter {
    public DocGanDayAdapter(@NonNull Activity context, int resource, @NonNull List<TinDaLuu> objects) {
        super(context, resource, objects);
    }
}
