package com.phongson.model;

public class User {
    private String idUser , idFacebook , TenNguoiDung ;

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdFacebook() {
        return idFacebook;
    }

    public void setIdFacebook(String idFacebook) {
        this.idFacebook = idFacebook;
    }

    public String getTenNguoiDung() {
        return TenNguoiDung;
    }

    public void setTenNguoiDung(String tenNguoiDung) {
        TenNguoiDung = tenNguoiDung;
    }

    public User() {
    }

    public User(String idUser, String idFacebook, String tenNguoiDung) {
        this.idUser = idUser;
        this.idFacebook = idFacebook;
        TenNguoiDung = tenNguoiDung;
    }
}
