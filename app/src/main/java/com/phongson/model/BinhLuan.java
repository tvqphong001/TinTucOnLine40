package com.phongson.model;

import java.util.Date;

public class BinhLuan {
    private String idBinhLuan , idTin , NoiDung, idUser;
    Date NgayDang;

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public BinhLuan(String idBinhLuan, String idTin, String noiDung, String idUser, Date ngayDang) {
        this.idBinhLuan = idBinhLuan;
        this.idTin = idTin;
        NoiDung = noiDung;
        this.idUser = idUser;
        NgayDang = ngayDang;
    }

    public Date getNgayDang() {
        return NgayDang;
    }

    public void setNgayDang(Date ngayDang) {
        NgayDang = ngayDang;
    }

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



    public BinhLuan() {
    }


}
