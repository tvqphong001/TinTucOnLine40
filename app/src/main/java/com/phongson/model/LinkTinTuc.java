package com.phongson.model;

public class LinkTinTuc {
    private String idLink , duongLink , idNguon ;

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
