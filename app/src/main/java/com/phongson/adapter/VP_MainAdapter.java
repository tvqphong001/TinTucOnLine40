package com.phongson.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.phongson.activity.Main2Activity;
import com.phongson.fragment.fm_CongNghe;
import com.phongson.fragment.fm_GiaiTri;
import com.phongson.fragment.fm_GiaoDuc;
import com.phongson.fragment.fm_SucKhoe;
import com.phongson.fragment.fm_TheThao;
import com.phongson.fragment.fm_ThoiSu;
import com.phongson.fragment.fm_TrangChu;
import com.phongson.fragment.fm_Video;

import java.util.ArrayList;
import java.util.Arrays;


public class VP_MainAdapter extends FragmentPagerAdapter {
    private String fragments[] = {"Trang Chu", "Dien Dan", "Tin Hot"};
    String p1 = "Trang Chủ";
    String p2 = "Thời Sự";
    String p3 = "Giải Trí";
    String p4 = "Giáo Dục";
    String p5 = "Thể Thao";
    String p6 = "Sức Khỏe";
    String p7 = "Công Nghệ";
    String p8 = "Video";
    private ArrayList<String> tab = new ArrayList<String>(Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8));
    public VP_MainAdapter(FragmentManager supportFragmentManager, Context applicationContext) {
        super(supportFragmentManager);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position)
        {
            case 0:
                    return new fm_TrangChu();
            case 1:
                return khoitaoFragment(position);

            case 2:
                return khoitaoFragment(position);

            case 3:
                return khoitaoFragment(position);

            case 4:
                return khoitaoFragment(position);

            case 5:
                return khoitaoFragment(position);

            case 6:
                return khoitaoFragment(position);

            case 7:
                return khoitaoFragment(position);


            default:
                return null;

        }
    }

    private Fragment khoitaoFragment(int position)
    {
        if ("Thời Sự".equals(Main2Activity.listChuyenMuc.get(position).getTenChuyenMuc()))
            return new fm_ThoiSu();
        if ("Giải Trí".equals(Main2Activity.listChuyenMuc.get(position).getTenChuyenMuc()))
            return new fm_GiaiTri();
        if ("Giáo Dục".equals(Main2Activity.listChuyenMuc.get(position).getTenChuyenMuc()))
            return new fm_GiaoDuc();
        if ("Sức Khỏe".equals(Main2Activity.listChuyenMuc.get(position).getTenChuyenMuc()))
            return new fm_SucKhoe();
        if ("Công Nghệ".equals(Main2Activity.listChuyenMuc.get(position).getTenChuyenMuc()))
            return new fm_CongNghe();
        if ("Video".equals(Main2Activity.listChuyenMuc.get(position).getTenChuyenMuc()))
            return new fm_Video();
        if ("Thể Thao".equals(Main2Activity.listChuyenMuc.get(position).getTenChuyenMuc()))
            return new fm_TheThao();
        return null;
    }
    @Override
    public int getCount() {
        return Main2Activity.listChuyenMuc.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return Main2Activity.listChuyenMuc.get(position).getTenChuyenMuc() ;
    }
}
