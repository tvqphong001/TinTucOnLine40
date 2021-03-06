package com.phongson.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.phongson.R;
import com.phongson.model.DocGanDay;
import com.phongson.model.TinDaLuu;
import com.phongson.model.TinTuc;

import org.w3c.dom.NodeList;


public class TinActivity extends AppCompatActivity {
    WebView webView;
    String ID_USER;
    CallbackManager callbackManager;
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    ShareDialog shareDialog;
    public static TinTuc tinTuc;
    int TrangThaiLuu = 0;
    String ID_TinVuaLuu ="abc";
    String ID_LichSuDoc = "";
    BottomNavigationView navigation;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_Save:
                    if (AccessToken.getCurrentAccessToken() != null) {

                            if (kiemTraTrung(webView.getUrl()) == -1) {
                                String id = mDatabase.push().getKey();
                                TinDaLuu tinDaLuu = new TinDaLuu(id, webView.getUrl(), "", webView.getTitle(), "", "", null);
                                mDatabase.child("TinDaLuu").child(ID_USER).child(id).setValue(tinDaLuu);
                                ID_TinVuaLuu = id;
                                Toast.makeText(TinActivity.this, "Luu Thanh Cong", Toast.LENGTH_SHORT).show();
                                navigation.getMenu().getItem(0).setIcon(R.drawable.ic_archive_wht_24dp);
                            } else {
                                int vitri = kiemTraTrung(webView.getUrl());
                                mDatabase.child("TinDaLuu").child(ID_USER).child(Main2Activity.listTinDaLuu.get(vitri).getIdTin()).removeValue();
                                Toast.makeText(TinActivity.this, "Đã Xóa Tin", Toast.LENGTH_SHORT).show();
                                navigation.getMenu().getItem(0).setIcon(R.drawable.ic_archive_black_24dp);
                            }

                    } else {
                        Toast.makeText(TinActivity.this, "No Login!", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                case R.id.navigation_Comments:
                    Intent intent = new Intent(TinActivity.this, BinhLuanActivity.class);
                    intent.putExtra("ID_TinTuc", tinTuc.getIdTin());
                    startActivity(intent);
                    return true;
                case R.id.navigation_Share:
                    shareFaceBook();
                    return true;
            }
            return false;
        }
    };

    //    private void XoaTin(String url)
//    {
//        ArrayList<TinDaLuu> listTinDaLuu =MainActivity.listTinDaLuu;
//        for (int i =0;i<listTinDaLuu.size();i++)
//        {
//            if (url.equals(listTinDaLuu.get(i).getLinkTinTuc()))
//            {
//               listTinDaLuu.remove(i);
//               mDatabase.child("TinDaLuu").child(MainActivity.listTinDaLuu.remove(i).getIdTin()).removeValue();
//               return;
//            }
//        }
//    }
    private int kiemTraTrung(String url) {
        for (int i = 0; i < Main2Activity.listTinDaLuu.size(); i++) {
            if (url.equals(Main2Activity.listTinDaLuu.get(i).getLinkTinTuc()))
                return i;
        }
        return -1;
    }

    private int timTin(String id)
    {
        for (int i = 0; i < Main2Activity.listTinDaLuu.size(); i++) {
            if (id.equals(Main2Activity.listTinDaLuu.get(i).getIdTin()))
                return i;
        }
        return -1;
    }
    private boolean kiemTraTrungDocGanDay(String url) {
        for (int i = 0; i < Main2Activity.listDocGanDay.size(); i++) {
            if (url.equals(Main2Activity.listDocGanDay.get(i).getLinkTinTuc()))
                return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tin);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getResources().getString(R.string.app_name));
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        Intent intent = getIntent();
        String link = intent.getStringExtra("link");
        ID_USER = intent.getStringExtra("ID_USER");
        tinTuc = (TinTuc) intent.getSerializableExtra("TinTuc");
        TrangThaiLuu = intent.getIntExtra("LichSuDoc", 0);
        ID_LichSuDoc = intent.getStringExtra("ID_TinDaLuu");


        // webView
        webView = findViewById(R.id.webview_tintuc);
        settingWebView(true);
        webView.loadUrl(link);

        if (kiemTraTrung(webView.getUrl())!=-1)
        {
            navigation.getMenu().getItem(0).setIcon(R.drawable.ic_archive_wht_24dp);
        }

        // Luu Tin doc gan day
        if (AccessToken.getCurrentAccessToken() != null) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (kiemTraTrungDocGanDay(webView.getUrl()) == false) {
                        if(Main2Activity.listDocGanDay.size()>20)
                        {
                            Main2Activity.listDocGanDay.remove(0);
                        }
                        String id = mDatabase.push().getKey();
                        DocGanDay docGanDay = new DocGanDay(id, webView.getUrl(), "", webView.getTitle(), null);
                        Main2Activity.listDocGanDay.add(docGanDay);
                        mDatabase.child("DocGanDay").child(ID_USER).child(id).setValue(docGanDay);
                    }
                }
            }, 4000);
        }
        //new Web VewClient
        webView.setWebViewClient(new WebViewClient());
    }

    private void shareFaceBook() {
        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse(webView.getUrl()))
                .build();

        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);
        ShareDialog.show(this, content);

//        if (ShareDialog.canShow(ShareLinkContent.class)) {
//            ShareLinkContent linkContent = new ShareLinkContent.Builder()
//                    .setContentUrl(Uri.parse(webView.getUrl()))
//                    .build();
//            shareDialog.show(linkContent);
//        }
        shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
            @Override
            public void onSuccess(Sharer.Result result) {
                Toast.makeText(TinActivity.this, "Chia Sẽ Thành Công", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                Toast.makeText(TinActivity.this, "Chia Sẽ Đã Thoát", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(TinActivity.this, "Lỗi Chia Sẽ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void settingWebView(boolean trangthai) {
        webView.getSettings().setJavaScriptEnabled(trangthai);
        // webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(trangthai);
        webView.getSettings().setSupportMultipleWindows(trangthai);
        webView.getSettings().setSupportZoom(trangthai);
        webView.getSettings().setBuiltInZoomControls(trangthai);
        webView.getSettings().setAllowFileAccess(trangthai);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //settingWebView(false);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
