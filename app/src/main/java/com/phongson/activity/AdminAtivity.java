package com.phongson.activity;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.phongson.R;
import com.phongson.model.ChuyenMuc;
import com.phongson.model.LinkTinTuc;
import com.phongson.model.NguonTinTuc;
import com.phongson.model.TinTuc;
import com.phongson.util.XMLDOMParser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.TypeInfo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminAtivity extends AppCompatActivity {
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    EditText idChuyenmuc,TenChuyenmuc,idNguon,TenNguon,idLink,tenLink;
    Button btnThemChuyenMuc,btnNguon,btnLink, btnCapNhat;
    ArrayList<TinTuc> listTinTuc;
    ArrayList<LinkTinTuc> listLinkTinTuc;
    ArrayList<NguonTinTuc> listNguonTinTuc;
    String duongLink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        addControls();
        getLinkTinTuc();
        getNguonTinTuc();
        addEvent();
    }

    private void getNguonTinTuc() {
        mDatabase.child("NguonTinTuc").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                NguonTinTuc nguonTinTuc = dataSnapshot.getValue(NguonTinTuc.class);
                listNguonTinTuc.add(nguonTinTuc);
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

    private void getLinkTinTuc() {
        mDatabase.child("LinkTinTuc").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                LinkTinTuc linkTinTuc = dataSnapshot.getValue(LinkTinTuc.class);
                listLinkTinTuc.add(linkTinTuc);

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

    private void addEvent() {
        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                capnhatTinTuc();
            }
        });
        btnThemChuyenMuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChuyenMuc chuyenMuc = new ChuyenMuc();
                String id = mDatabase.push().getKey();
                chuyenMuc.setIdChuyenMuc(id);
                chuyenMuc.setTenChuyenMuc(TenChuyenmuc.getText().toString());
                mDatabase.child("ChuyenMuc").child(id).setValue(chuyenMuc);

            }
        });
        btnNguon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NguonTinTuc nguonTinTuc = new NguonTinTuc();
                String id = mDatabase.push().getKey();
                nguonTinTuc.setIdNguon(id);
                nguonTinTuc.setTenNguon(TenNguon.getText().toString());
                mDatabase.child("NguonTinTuc").child(id).setValue(nguonTinTuc);
            }
        });
        btnLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinkTinTuc linkTinTuc = new LinkTinTuc();
                String id = mDatabase.push().getKey();
                linkTinTuc.setIdLink(id);
                linkTinTuc.setDuongLink("");
                linkTinTuc.setIdNguon("");
                linkTinTuc.setIdChuyenMuc("");
                mDatabase.child("LinkTinTuc").child(id).setValue(linkTinTuc);
            }
        });
    }

    private void capnhatTinTuc() {

        mDatabase.child("TinTuc").removeValue();
        setdata();
    }

    private void setdata() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i<listLinkTinTuc.size();i++)
                {
                    for (int j = 0; j<listNguonTinTuc.size();j++)
                    {
                        if (listNguonTinTuc.get(j).getIdNguon().equals(listLinkTinTuc.get(i).getIdNguon())) {
                            if (listNguonTinTuc.get(j).getTenNguon().equals("VnExpress")) {
                                duongLink = new String();
                                duongLink = listLinkTinTuc.get(i).getDuongLink();
                                new ReadDataVNEpress().execute(listLinkTinTuc.get(i).getDuongLink());
//                                ReadDataVNEpress readDataVNEpress = new ReadDataVNEpress();
//                                readDataVNEpress.onPostExecute(readDataVNEpress.doInBackground(listLinkTinTuc.get(i).getDuongLink()),listLinkTinTuc.get(i).getDuongLink());
                            }
                            if (listNguonTinTuc.get(j).getTenNguon().equals("Thanh Niên")) {
                                duongLink = new String();
                                duongLink = listLinkTinTuc.get(i).getDuongLink();
                                new ReadDataThanhNien().execute(listLinkTinTuc.get(i).getDuongLink());

                            }
                            if (listNguonTinTuc.get(j).getTenNguon().equals("VietNamNet")) {
                                duongLink = new String();
                                duongLink = listLinkTinTuc.get(i).getDuongLink();
                                new ReadDataVietNamNet().execute(listLinkTinTuc.get(i).getDuongLink());

                            }
                        }

                    }
                }
            }
        });
    }
    private void addControls() {
        idChuyenmuc = findViewById(R.id.idChuyenmuc);
        TenChuyenmuc = findViewById(R.id.TenChuyenmuc);
        idNguon = findViewById(R.id.TenNguon);
        TenNguon = findViewById(R.id.TenNguon);
        idLink = findViewById(R.id.idLink);
        tenLink = findViewById(R.id.TenLink);
        btnThemChuyenMuc = findViewById(R.id.btnThemChuyenMuc);
        btnNguon= findViewById(R.id.btnNguon);
        btnLink = findViewById(R.id.btnLink);
        btnCapNhat = findViewById(R.id.btnCapNhatTinTuc);

        // new list
        listTinTuc = new ArrayList<>();
        listLinkTinTuc = new ArrayList<>();
        listNguonTinTuc = new ArrayList<>();
    }
    private String docNoiDung_Tu_URL(String theUrl) {
        StringBuilder content = new StringBuilder();
        try {
            // create a url object
            URL url = new URL(theUrl);

            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();

            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    void readDataVNEpress(String idLinkTinTuc,String linkTinTuc)
    {

    }
    private String  TimIDNguon(String tenNguon)
    {
        int i = 0;
        while (i<listNguonTinTuc.size())
        {
            if (listNguonTinTuc.get(i).getTenNguon().equals(tenNguon))
                return listNguonTinTuc.get(i).getIdNguon();
            i++;
        }
        return "";
    }
    private String TimIDLink(String URL)
    {
        int i = 0;
        while (i<listLinkTinTuc.size())
        {
            if (listLinkTinTuc.get(i).getDuongLink().equals(URL))
                return listLinkTinTuc.get(i).getIdLink();
            i++;
        }
        return "";
    }
    // vn express
    class ReadDataVNEpress extends AsyncTask<String, Integer, String> {

        private String duonglink = "";
        protected String doInBackground(String... strings) {
            duonglink = strings[0];
            return docNoiDung_Tu_URL(strings[0]);
        }


        protected void onPostExecute(String s) {
            XMLDOMParser parser = new XMLDOMParser();
            Document document = parser.getDocument(s);
            NodeList nodeList = document.getElementsByTagName("item");
            NodeList nodeList1Desciption = document.getElementsByTagName("description");
            String title = "";
            String link = "";
            String hinhanh = "";
            String idLink = TimIDLink(duonglink);
            for (int i = 0; i < nodeList.getLength(); i++) {
                String cdata = nodeList1Desciption.item(i + 1).getTextContent();
                int flag = 0;
                Element line = (Element) nodeList1Desciption.item(i + 1);
                Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
                Pattern p1 = Pattern.compile("<img[^>]+data-original\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
                Matcher matcher = p.matcher(cdata);
                Matcher matcher1 = p1.matcher(cdata);
                if (matcher1.find()) {
                    hinhanh = matcher1.group(1);
                    flag = 1;

                }
                if (matcher.find() && flag == 0) {

                    hinhanh = matcher.group(1);
                }


                Element element = (Element) nodeList.item(i);
                title = parser.getValue(element, "title");
                link = parser.getValue(element, "link");
                String id = mDatabase.push().getKey();
                TinTuc tinTuc = new TinTuc(id,link,hinhanh,title,"0","0",idLink,null);
                mDatabase.child("TinTuc").child(id).setValue(tinTuc);

            }
            super.onPostExecute(s);

        }
    }

    class ReadDataVietNamNet extends AsyncTask<String, Integer, String> {
        private String duonglink = "";
        protected String doInBackground(String... strings) {
            duonglink =strings[0];
            return docNoiDung_Tu_URL(strings[0]);
        }

        // vn express
        protected void onPostExecute(String s) {
            XMLDOMParser parser = new XMLDOMParser();
            Document document = parser.getDocument(s);
            NodeList nodeList = document.getElementsByTagName("item");
            NodeList nodeList1Desciption = document.getElementsByTagName("description");
            String title = "";
            String link = "";
            String hinhanh = "";
            String idLink = TimIDLink(duonglink);
            for (int i = 0; i < nodeList.getLength(); i++) {
                String cdata = nodeList1Desciption.item(i + 1).getTextContent();
                int flag = 0;
                Element line = (Element) nodeList1Desciption.item(i + 1);
                Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
                Pattern p1 = Pattern.compile("<img[^>]+data-original\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
                Matcher matcher = p.matcher(cdata);
                Matcher matcher1 = p1.matcher(cdata);
                if (matcher1.find()) {
                    hinhanh = matcher1.group(1);
                    flag = 1;

                }
                if (matcher.find() && flag == 0) {

                    hinhanh = matcher.group(1);
                }

                Element element = (Element) nodeList.item(i);
                title = parser.getValue(element, "title");
                link = parser.getValue(element, "link");
                String id = mDatabase.push().getKey();
                TinTuc tinTuc = new TinTuc(id,link,hinhanh,title,"0","0",idLink,null);
                mDatabase.child("TinTuc").child(id).setValue(tinTuc);
            }
            super.onPostExecute(s);

        }
    }
    class ReadDataTuoitre extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... strings) {
            return docNoiDung_Tu_URL(strings[0]);
        }

        // vn express
        @Override
        protected void onPostExecute(String s) {
            XMLDOMParser parser = new XMLDOMParser();
            Document document = parser.getDocument(s);
            NodeList nodeList = document.getElementsByTagName("item");
            NodeList nodeList1Desciption = document.getElementsByTagName("description");
            NodeList nodeListTitle = document.getElementsByTagName("title");
            NodeList nodeListLink = document.getElementsByTagName("link");
            String title = "";
            String link = "";
            String hinhanh = "";
            String nguonbao ="Tuổi Trẻ";
            for (int i = 0; i < nodeList.getLength(); i++) {
                String cdata = nodeList1Desciption.item(i).getTextContent();
                int flag =0;
//                Element line = (Element) nodeList1Desciption.item(i+1);
//                String cdata = getCharacterDataFromElement(line);
                Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
                Pattern p1 = Pattern.compile("<img[^>]+data-original\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
                Matcher matcher = p.matcher(cdata);
                Matcher matcher1 = p1.matcher(cdata);

                //Matcher matcher = Pattern.compile("<img src=\"([^\"]+)").matcher(getCharacterDataFromElement(line));

                if (matcher1.find())
                {
                    hinhanh = matcher1.group(1);
                    flag=1;

                }
                if  (matcher.find()&& flag==0)
                {

                    hinhanh = matcher.group(1);
                }

                Element element = (Element) nodeList.item(i);
                title = nodeListTitle.item(i+1).getTextContent();
                link = nodeListLink.item(i+1).getTextContent();
            }
            super.onPostExecute(s);

        }
    }
    class ReadDataThanhNien extends AsyncTask<String, Integer, String> {

        private String duonglink ="";
        protected String doInBackground(String... strings) {
            duonglink =strings[0];
            return docNoiDung_Tu_URL(strings[0]);
        }

        // vn express
        protected void onPostExecute(String s) {
            XMLDOMParser parser = new XMLDOMParser();
            Document document = parser.getDocument(s);
            NodeList nodeList = document.getElementsByTagName("item");
            NodeList nodeList1Desciption = document.getElementsByTagName("description");
            //NodeList nodeListTitle = document.getElementsByTagName("title");
            // NodeList nodeListLink = document.getElementsByTagName("link");
            String title = "";
            String link = "";
            String hinhanh = "";
            String nguonbao = "Thanh Niên";
            String idNguonTinTuc = TimIDNguon(nguonbao);
            String idLink = TimIDLink(duonglink);
            for (int i = 0; i < nodeList.getLength(); i++) {
                String cdata = nodeList1Desciption.item(i + 1).getTextContent();
                int flag = 0;
//                Element line = (Element) nodeList1Desciption.item(i+1);
//                String cdata = getCharacterDataFromElement(line);
                Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
                Pattern p1 = Pattern.compile("<img[^>]+data-original\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
                Matcher matcher = p.matcher(cdata);
                Matcher matcher1 = p1.matcher(cdata);

                if (matcher1.find()) {
                    hinhanh = matcher1.group(1);
                    flag = 1;

                }
                if (matcher.find() && flag == 0) {

                    hinhanh = matcher.group(1);
                }

                Element element = (Element) nodeList.item(i);
//                title = nodeListTitle.item(i + 2).getTextContent();
                title = parser.getValue(element,"title");
                link = parser.getValue(element, "link");
                String id = mDatabase.push().getKey();
                TinTuc tinTuc = new TinTuc(id,link,hinhanh,title,"0","0",idLink,null);
                mDatabase.child("TinTuc").child(id).setValue(tinTuc);
            }
            super.onPostExecute(s);

        }
    }
}
