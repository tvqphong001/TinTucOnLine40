package com.phongson.model;

public class NguonTinTuc {
    private String idNguon , tenNguon , idChuyenMuc;

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

    public String getIdChuyenMuc() {
        return idChuyenMuc;
    }

    public void setIdChuyenMuc(String idChuyenMuc) {
        this.idChuyenMuc = idChuyenMuc;
    }

    public NguonTinTuc() {
    }

    public NguonTinTuc(String idNguon, String tenNguon, String idChuyenMuc) {
        this.idNguon = idNguon;
        this.tenNguon = tenNguon;
        this.idChuyenMuc = idChuyenMuc;
    }
}
