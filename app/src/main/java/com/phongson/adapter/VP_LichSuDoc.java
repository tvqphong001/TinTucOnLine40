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
import com.phongson.fragment.his_fm_DocGanDay;
import com.phongson.fragment.his_fm_TinDaLuu;

import java.util.ArrayList;
import java.util.Arrays;


public class VP_LichSuDoc extends FragmentPagerAdapter {
    private String fragments[] = {"Đọc Gần Đây", "Tin Đã Lưu"};
    public VP_LichSuDoc(FragmentManager supportFragmentManager, Context applicationContext) {
        super(supportFragmentManager);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position)
        {
            case 0:
                return new his_fm_DocGanDay();
            case 1:
                return new his_fm_TinDaLuu();


            default:
                return null;

        }
    }
    @Override
    public int getCount() {
        return fragments.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragments[position];
    }
}
