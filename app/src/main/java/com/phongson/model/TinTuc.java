package com.phongson.model;

import java.io.Serializable;
import java.util.Date;

public class TinTuc implements Serializable {
    public TinTuc(String idTin, String linkTinTuc, String linkHinhAnh, String tieuDe, Date ngayDang) {
        this.idTin = idTin;
        this.linkTinTuc = linkTinTuc;
        this.linkHinhAnh = linkHinhAnh;
        TieuDe = tieuDe;
        this.ngayDang = ngayDang;
    }

    public TinTuc(String idTin, String linkTinTuc, String linkHinhAnh, String tieuDe, String idLink, Date ngayDang) {
        this.idTin = idTin;
        this.linkTinTuc = linkTinTuc;
        this.linkHinhAnh = linkHinhAnh;
        TieuDe = tieuDe;
        this.idLink = idLink;
        this.ngayDang = ngayDang;
    }

    private String idTin , linkTinTuc , linkHinhAnh , TieuDe , luotBinhLuan , luotXem ,idLink ;
    private Date ngayDang ;

    public String getIdLink() {
        return idLink;
    }

    public void setIdLink(String idLink) {
        this.idLink = idLink;
    }

    public TinTuc(String idTin, String linkTinTuc, String linkHinhAnh, String tieuDe, String luotBinhLuan, String luotXem, String idLink, Date ngayDang) {
        this.idTin = idTin;
        this.linkTinTuc = linkTinTuc;
        this.linkHinhAnh = linkHinhAnh;
        TieuDe = tieuDe;
        this.luotBinhLuan = luotBinhLuan;
        this.luotXem = luotXem;
        this.idLink = idLink;
        this.ngayDang = ngayDang;
    }

    public String getIdTin() {
        return idTin;
    }

    public void setIdTin(String idTin) {
        this.idTin = idTin;
    }

    public String getLinkTinTuc() {
        return linkTinTuc;
    }

    public void setLinkTinTuc(String linkTinTuc) {
        this.linkTinTuc = linkTinTuc;
    }

    public String getLinkHinhAnh() {
        return linkHinhAnh;
    }

    public void setLinkHinhAnh(String linkHinhAnh) {
        this.linkHinhAnh = linkHinhAnh;
    }

    public String getTieuDe() {
        return TieuDe;
    }

    public void setTieuDe(String tieuDe) {
        TieuDe = tieuDe;
    }

    public String getLuotBinhLuan() {
        return luotBinhLuan;
    }

    public void setLuotBinhLuan(String luotBinhLuan) {
        this.luotBinhLuan = luotBinhLuan;
    }

    public String getLuotXem() {
        return luotXem;
    }

    public void setLuotXem(String luotXem) {
        this.luotXem = luotXem;
    }

    public Date getNgayDang() {
        return ngayDang;
    }

    public void setNgayDang(Date ngayDang) {
        this.ngayDang = ngayDang;
    }

    public TinTuc() {
    }

    public TinTuc(String idTin, String linkTinTuc, String linkHinhAnh, String tieuDe, String luotBinhLuan, String luotXem, Date ngayDang) {
        this.idTin = idTin;
        this.linkTinTuc = linkTinTuc;
        this.linkHinhAnh = linkHinhAnh;
        TieuDe = tieuDe;
        this.luotBinhLuan = luotBinhLuan;
        this.luotXem = luotXem;
        this.ngayDang = ngayDang;
    }
}
