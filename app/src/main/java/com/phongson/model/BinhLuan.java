package com.phongson.model;

public class BinhLuan {
    private String idBinhLuan , idTin , NoiDung , NgayDang ;

    public String getIdBinhLuan() {
        return idBinhLuan;
    }

    public void setIdBinhLuan(String idBinhLuan) {
        this.idBinhLuan = idBinhLuan;
    }

    public String getIdTin() {
        return idTin;
    }

    public void setIdTin(String idTin) {
        this.idTin = idTin;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String noiDung) {
        NoiDung = noiDung;
    }

    public String getNgayDang() {
        return NgayDang;
    }

    public void setNgayDang(String ngayDang) {
        NgayDang = ngayDang;
    }

    public BinhLuan() {
    }

    public BinhLuan(String idBinhLuan, String idTin, String noiDung, String ngayDang) {

        this.idBinhLuan = idBinhLuan;
        this.idTin = idTin;
        NoiDung = noiDung;
        NgayDang = ngayDang;
    }
}
