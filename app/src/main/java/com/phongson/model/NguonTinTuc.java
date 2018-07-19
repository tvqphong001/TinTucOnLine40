package com.phongson.model;

public class NguonTinTuc {
    private String idNguon , tenNguon;

    public String getIdNguon() {
        return idNguon;
    }

    public void setIdNguon(String idNguon) {
        this.idNguon = idNguon;
    }

    public String getTenNguon() {
        return tenNguon;
    }

    public void setTenNguon(String tenNguon) {
        this.tenNguon = tenNguon;
    }



    public NguonTinTuc() {
    }

    public NguonTinTuc(String idNguon, String tenNguon) {
        this.idNguon = idNguon;
        this.tenNguon = tenNguon;
    }
}
