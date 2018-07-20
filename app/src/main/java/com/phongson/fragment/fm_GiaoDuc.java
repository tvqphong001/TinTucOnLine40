package com.phongson.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.phongson.R;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class fm_GiaoDuc extends Fragment {
//    ArrayList<TinTuc> list;
//    ListView listView;
//    TinTucAdapter adapter;
//    ArrayList<TheLoai> listTheLoai;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fm_giaoduc, container, false);
        return myView;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        list = new ArrayList<>();
//        listTheLoai = new ArrayList<>();
//        listView = view.findViewById(R.id.listview);
//        // lay du lieu tu firebase ve
//        getdataTheloai();
//
//        // chờ fire 3000ms= 3s để listTheLoai lay về nếu không sẽ null
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                // gang dữ liệu cho list view và setAdapter
//                setdata();
//            }
//        },1000);
//
//        // Chon moi item trong list view sẽ lấy đường link tương ứng
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(getActivity(), TinActivity.class);
//                // lấy đối tượng TinTuc trong list ngay position(Vị Trí) mà mình nhấn vào
//                intent.putExtra("link",list.get(position).getLink());
//                intent.putExtra("ID_USER",MainActivity.ID_USER);
//                startActivity(intent);
//            }
//        });
//    }
//
//    private void getdataTheloai() {
//        MainActivity.mDatabase.child("TheLoai").addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                TheLoai theLoai = dataSnapshot.getValue(TheLoai.class);
//                // so sánh Ten the loại có giống như fragment đang mở hay không
//                if ("Giáo Dục".equals(theLoai.getTen()))
//                {
//                    listTheLoai.add(theLoai);
//                }
//
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }
//
//
//    private void setdata() {
//        getActivity().runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0;i<listTheLoai.size();i++)
//                {
//                    if (listTheLoai.get(i).getNguonbao().equals("VnExpress"))
//                    {
//                        new ReadDataVNEpress().execute(listTheLoai.get(i).getLinkTheloai());
//                    }
//                    if (listTheLoai.get(i).getNguonbao().equals("Thanh Niên"))
//                    {
//                        new ReadDataThanhNien().execute(listTheLoai.get(i).getLinkTheloai());
//                    }
//                    if (listTheLoai.get(i).getNguonbao().equals("VietNamNet"))
//                    {
//                        new ReadDataVietNamNet().execute(listTheLoai.get(i).getLinkTheloai());
//                    }
//                }
//            }
//        });
////        new Handler().postDelayed(new Runnable() {
////            @Override
////            public void run() {
////
////            }
////        },3000);
//    }
//
//    class ReadDataThanhNien extends AsyncTask<String, Integer, String> {
//
//        protected String doInBackground(String... strings) {
//            return docNoiDung_Tu_URL(strings[0]);
//        }
//
//        // vn express
//        protected void onPostExecute(String s) {
//            XMLDOMParser parser = new XMLDOMParser();
//            Document document = parser.getDocument(s);
//            NodeList nodeList = document.getElementsByTagName("item");
//            NodeList nodeList1Desciption = document.getElementsByTagName("description");
//            NodeList nodeListTitle = document.getElementsByTagName("title");
//            // NodeList nodeListLink = document.getElementsByTagName("link");
//            String title = "";
//            String link = "";
//            String hinhanh = "";
//            String NguonBao = nodeListTitle.item(1).getTextContent();
//            String Ngay = "";
//            for (int i = 0; i < nodeList.getLength(); i++) {
//                String cdata = nodeList1Desciption.item(i + 1).getTextContent();
//                int flag = 0;
////                Element line = (Element) nodeList1Desciption.item(i+1);
////                String cdata = getCharacterDataFromElement(line);
//                Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
//                Pattern p1 = Pattern.compile("<img[^>]+data-original\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
//                Matcher matcher = p.matcher(cdata);
//                Matcher matcher1 = p1.matcher(cdata);
//                //Matcher matcher = Pattern.compile("<img src=\"([^\"]+)").matcher(getCharacterDataFromElement(line));
//
//                if (matcher1.find()) {
//                    hinhanh = matcher1.group(1);
//                    flag = 1;
//
//                }
//                if (matcher.find() && flag == 0) {
//
//                    hinhanh = matcher.group(1);
//                    //Log.d("hinhanh",hinhanh + ".........." +i);
//                }
//
//                Element element = (Element) nodeList.item(i);
//                title = nodeListTitle.item(i + 2).getTextContent();
//                link = parser.getValue(element, "link");
//                Ngay = parser.getValue(element, "pubDate");
//                TinTuc tinTuc = new TinTuc(title,link,hinhanh,"",hinhanh,NguonBao,"","");
//                list.add(tinTuc);
//
//            }
//            listView.setAdapter(adapter);
//            adapter = new TinTucAdapter(getActivity(),R.layout.item_list_tintucc,list);
//            super.onPostExecute(s);
//
//        }
//    }
//
//    private String docNoiDung_Tu_URL(String theUrl) {
//        StringBuilder content = new StringBuilder();
//        try {
//            // create a url object
//            URL url = new URL(theUrl);
//
//            // create a urlconnection object
//            URLConnection urlConnection = url.openConnection();
//
//            // wrap the urlconnection in a bufferedreader
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
//
//            String line;
//
//            // read from the urlconnection via the bufferedreader
//            while ((line = bufferedReader.readLine()) != null) {
//                content.append(line + "\n");
//            }
//            bufferedReader.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return content.toString();
//    }
//
//    class ReadDataVNEpress extends AsyncTask<String, Integer, String> {
//
//        protected String doInBackground(String... strings) {
//            return docNoiDung_Tu_URL(strings[0]);
//        }
//
//        // vn express
//        protected void onPostExecute(String s) {
//            XMLDOMParser parser = new XMLDOMParser();
//            Document document = parser.getDocument(s);
//            NodeList nodeList = document.getElementsByTagName("item");
//            NodeList nodeList1Desciption = document.getElementsByTagName("description");
//            String title = "";
//            String link = "";
//            String hinhanh = "";
//            String nguonBao = "VnExpress";
//            for (int i = 0; i < nodeList.getLength(); i++) {
//                String cdata = nodeList1Desciption.item(i + 1).getTextContent();
//                int flag = 0;
//                Element line = (Element) nodeList1Desciption.item(i + 1);
//                //String cdata = getCharacterDataFromElement(line);
//                Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
//                Pattern p1 = Pattern.compile("<img[^>]+data-original\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
//                Matcher matcher = p.matcher(cdata);
//                Matcher matcher1 = p1.matcher(cdata);
//                //Matcher matcher = Pattern.compile("<img src=\"([^\"]+)").matcher(getCharacterDataFromElement(line));
//
//                if (matcher1.find()) {
//                    hinhanh = matcher1.group(1);
//                    flag = 1;
//
//                }
//                if (matcher.find() && flag == 0) {
//
//                    hinhanh = matcher.group(1);
//                    //Log.d("hinhanh",hinhanh + ".........." +i);
//                }
//
//                Element element = (Element) nodeList.item(i);
//                title = parser.getValue(element, "title");
//                link = parser.getValue(element, "link");
//                TinTuc tinTuc = new TinTuc(title,link,hinhanh,"",hinhanh,nguonBao,"","");
//                list.add(tinTuc);
//                listView.setAdapter(adapter);
//                adapter = new TinTucAdapter(getActivity(),R.layout.item_list_tintucc,list);
////                listtin.add(docBao);
//
//            }
////            listView.setAdapter(adapter);
////            adapter = new TinTucAdapter(getActivity(),R.layout.item_list_tintucc,list);
//            super.onPostExecute(s);
//
//        }
//    }
//
//    class ReadDataVietNamNet extends AsyncTask<String, Integer, String> {
//
//        protected String doInBackground(String... strings) {
//            return docNoiDung_Tu_URL(strings[0]);
//        }
//
//        // vn express
//        protected void onPostExecute(String s) {
//            XMLDOMParser parser = new XMLDOMParser();
//            Document document = parser.getDocument(s);
//            NodeList nodeList = document.getElementsByTagName("item");
//            NodeList nodeList1Desciption = document.getElementsByTagName("description");
//            String title = "";
//            String link = "";
//            String hinhanh = "";
//            String nguonbao = "Vietnamnet";
//            for (int i = 0; i < nodeList.getLength(); i++) {
//                String cdata = nodeList1Desciption.item(i + 1).getTextContent();
//                int flag = 0;
//                Element line = (Element) nodeList1Desciption.item(i + 1);
//                Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
//                Pattern p1 = Pattern.compile("<img[^>]+data-original\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
//                Matcher matcher = p.matcher(cdata);
//                Matcher matcher1 = p1.matcher(cdata);
//                //Matcher matcher = Pattern.compile("<img src=\"([^\"]+)").matcher(getCharacterDataFromElement(line));
//
//                if (matcher1.find()) {
//                    hinhanh = matcher1.group(1);
//                    flag = 1;
//
//                }
//                if (matcher.find() && flag == 0) {
//
//                    hinhanh = matcher.group(1);
//                    //Log.d("hinhanh",hinhanh + ".........." +i);
//                }
//
//                Element element = (Element) nodeList.item(i);
//                title = parser.getValue(element, "title");
//                link = parser.getValue(element, "link");
//                TinTuc tinTuc = new TinTuc(title,link,hinhanh,"",hinhanh,nguonbao,"","");
//                list.add(tinTuc);
//
//            }
//            listView.setAdapter(adapter);
//            adapter = new TinTucAdapter(getActivity(),R.layout.item_list_tintucc,list);
//            super.onPostExecute(s);
//
//        }
    }
}
