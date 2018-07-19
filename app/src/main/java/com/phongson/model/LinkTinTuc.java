package com.phongson.model;

public class LinkTinTuc {
    private String idLink , duongLink , idNguon, idChuyenMuc ;

    public String getIdChuyenMuc() {
        return idChuyenMuc;
    }

    public void setIdChuyenMuc(String idChuyenMuc) {
        this.idChuyenMuc = idChuyenMuc;
    }

    public LinkTinTuc(String idLink, String duongLink, String idNguon, String idChuyenMuc) {
        this.idLink = idLink;
        this.duongLink = duongLink;
        this.idNguon = idNguon;
        this.idChuyenMuc = idChuyenMuc;
    }

    public String getIdLink() {
        return idLink;
    }

    public void setIdLink(String idLink) {
        this.idLink = idLink;
    }

    public String getDuongLink() {
        return duongLink;
    }

    public void setDuongLink(String duongLink) {
        this.duongLink = duongLink;
    }

    public String getIdNguon() {
        return idNguon;
    }

    public void setIdNguon(String idNguon) {
        this.idNguon = idNguon;
    }

    public LinkTinTuc() {
    }

    public LinkTinTuc(String idLink, String duongLink, String idNguon) {
        this.idLink = idLink;
        this.duongLink = duongLink;
        this.idNguon = idNguon;
    }
}
