package com.phongson.model;

import java.util.Date;

public class DocGanDay extends TinTuc{

    public DocGanDay(String idTin, String linkTinTuc, String linkHinhAnh, String tieuDe, String luotBinhLuan, String luotXem, String idLink, Date ngayDang) {
        super(idTin, linkTinTuc, linkHinhAnh, tieuDe, luotBinhLuan, luotXem, idLink, ngayDang);
    }

    public DocGanDay() {
    }

    public DocGanDay(String idTin, String linkTinTuc, String linkHinhAnh, String tieuDe, String luotBinhLuan, String luotXem, Date ngayDang) {
        super(idTin, linkTinTuc, linkHinhAnh, tieuDe, luotBinhLuan, luotXem, ngayDang);
    }
}
