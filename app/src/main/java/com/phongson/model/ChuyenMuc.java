package com.phongson.model;

public class ChuyenMuc {
    private String idChuyenMuc , tenChuyenMuc;

    public String getIdChuyenMuc() {
        return idChuyenMuc;
    }

    public void setIdChuyenMuc(String idChuyenMuc) {
        this.idChuyenMuc = idChuyenMuc;
    }

    public String getTenChuyenMuc() {
        return tenChuyenMuc;
    }

    public void setTenChuyenMuc(String tenChuyenMuc) {
        this.tenChuyenMuc = tenChuyenMuc;
    }

    public ChuyenMuc() {
    }

    public ChuyenMuc(String idChuyenMuc, String tenChuyenMuc) {
        this.idChuyenMuc = idChuyenMuc;
        this.tenChuyenMuc = tenChuyenMuc;
    }
}
