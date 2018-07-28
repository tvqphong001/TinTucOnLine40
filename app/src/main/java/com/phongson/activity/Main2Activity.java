package com.phongson.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.phongson.R;
import com.phongson.adapter.VP_MainAdapter;
import com.phongson.model.ChuyenMuc;
import com.phongson.model.DocGanDay;
import com.phongson.model.TinDaLuu;
import com.phongson.model.User;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TabLayout tabLayout;
    ViewPager viewPager;
    LoginButton btnLoginButton;
    ImageView imvUser;
    TextView txtUser;
    public static String ID_USER = "";
    CallbackManager callbackManager = CallbackManager.Factory.create();
    public static ArrayList<ChuyenMuc> listChuyenMuc;
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

    //Lich Su doc
    public static ArrayList<TinDaLuu> listTinDaLuu;
    public static ArrayList<DocGanDay> listDocGanDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        addConTrol();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        setDataUser(navigationView.getHeaderView(0));

        listChuyenMuc = MainActivity.listChuyenMuc;

        Intent intent = getIntent();
        int Thaydoi = intent.getIntExtra("ThayDoi", -1);
        if (Thaydoi == 1) {
            listChuyenMuc = ChonChuyenMucActivity.listChonChuyenMuc;
        } else {
            getDataChuyenMuc();
        }
        setViewPager();

    }

    private void addConTrol() {
        listDocGanDay = new ArrayList<>();
        listTinDaLuu = new ArrayList<>();
    }

    private void setDataUser(View hview) {
        btnLoginButton = hview.findViewById(R.id.btnLoginFb);
        imvUser = hview.findViewById(R.id.imvUser);
        txtUser = hview.findViewById(R.id.txtUser);
        if (AccessToken.getCurrentAccessToken() != null) {
            GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                @Override
                public void onCompleted(JSONObject object, GraphResponse response) {
                    //Log.d("Json", response.getJSONObject().toString());
                    User user = null;
                    try {
                        String name = object.getString("name");
                        String id = object.getString("id");
                        String profilePicUrl = "https://graph.facebook.com/" + id + "/picture?type=large";
                        Picasso.get().load(profilePicUrl).into(imvUser);
                        txtUser.setText(name);
                        ID_USER = id;
                        getLichSuDoc(ID_USER);
                        //ID_USER = Integer.parseInt(id);
                        //user = new User(id,name,email);
                        //mDatabase.child("User").push().setValue(user);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "name,id,email");
            request.setParameters(parameters);
            request.executeAsync();
            btnLoginButton.setVisibility(View.INVISIBLE);

        }
        btnLoginButton.setReadPermissions(Arrays.asList("public_profile", "email"));
        btnLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        //Log.d("Json", response.getJSONObject().toString());
                        //User user = null;
                        try {
                            String name = object.getString("name");
                            String id = object.getString("id");
                            //String email = object.getString("email");
                            String profilePicUrl = "https://graph.facebook.com/" + id + "/picture?type=large";
                            Picasso.get().load(profilePicUrl).into(imvUser);
                            txtUser.setText(name);
                            ID_USER = id;
                            getLichSuDoc(ID_USER);
                            //ID_USER = Integer.parseInt(id);
                            //user = new User(id,name,email);
                            //mDatabase.child("User").push().setValue(user);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "name,id,email");
                request.setParameters(parameters);
                request.executeAsync();
                btnLoginButton.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });


    }

    private void getLichSuDoc(String ID_USER) {

        mDatabase.child("TinDaLuu").child(ID_USER).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                TinDaLuu tinDaLuu = dataSnapshot.getValue(TinDaLuu.class);
                listTinDaLuu.add(tinDaLuu);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mDatabase.child("DocGanDay").child(ID_USER).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                DocGanDay docGanDay = dataSnapshot.getValue(DocGanDay.class);
                listDocGanDay.add(docGanDay);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
    private void getDataChuyenMuc() {
        listChuyenMuc = MainActivity.listChuyenMuc;
    }

    private void setViewPager() {
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

//        viewPager.setAdapter(new VP_MainAdapter(getSupportFragmentManager(),getApplicationContext()) );

        viewPager.setAdapter(new VP_MainAdapter(getSupportFragmentManager(), getApplicationContext()));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
        });
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_Home) {
            setViewPager();
        }
        if (id == R.id.nav_thongtincanhan) {
//            fragmentManager.beginTransaction()
//                    .replace(R.id.content_frame
//                            , new fm_ChiTietThongTin())
//                    .commit();

            //startActivity(new Intent(MainActivity2.this, ThongTinCaNhanActivity.class));
        }
        if (id == R.id.nav_chonchuyenmuc) {
            startActivity(new Intent(Main2Activity.this, ChonChuyenMucActivity.class));
        }
        if (id == R.id.nav_setting) {
            // startActivity(new Intent(MainActivity2.this, SettingsActivity2.class));
        }
        if (id == R.id.nav_history_new_save) {
            Intent intent = new Intent(Main2Activity.this, LichSuDocActivity.class);
            intent.putExtra("ID_USER",ID_USER);
            startActivity(intent);
        }
        if (id == R.id.nav_logout) {
            LoginManager.getInstance().logOut();
            btnLoginButton.setVisibility(View.VISIBLE);
            imvUser.setImageResource(R.drawable.guest);
            txtUser.setText("Khách");
            // đăng xuất fb
        }
        if (id==R.id.nav_admin)
        {
            startActivity(new Intent(Main2Activity.this, AdminAtivity.class));
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
