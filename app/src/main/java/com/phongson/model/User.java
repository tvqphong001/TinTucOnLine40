package com.phongson.model;

public class User {
    private String idUser,TenNguoiDung,email;

    public User(String idUser, String tenNguoiDung, String email) {
        this.idUser = idUser;
        this.TenNguoiDung = tenNguoiDung;
        this.email = email;
    }

    public User() {
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getTenNguoiDung() {
        return TenNguoiDung;
    }

    public void setTenNguoiDung(String tenNguoiDung) {
        TenNguoiDung = tenNguoiDung;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
