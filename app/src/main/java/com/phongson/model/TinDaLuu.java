package com.phongson.model;

import java.util.Date;

public class TinDaLuu extends TinTuc {
    public TinDaLuu(String idTin, String linkTinTuc, String linkHinhAnh, String tieuDe, String luotBinhLuan, String luotXem, String idLink, Date ngayDang) {
        super(idTin, linkTinTuc, linkHinhAnh, tieuDe, luotBinhLuan, luotXem, idLink, ngayDang);
    }

    public TinDaLuu() {
    }

    public TinDaLuu(String idTin, String linkTinTuc, String linkHinhAnh, String tieuDe, String luotBinhLuan, String luotXem, Date ngayDang) {
        super(idTin, linkTinTuc, linkHinhAnh, tieuDe, luotBinhLuan, luotXem, ngayDang);
    }
}
