package com.phongson.model;

import java.util.Date;

public class DocGanDay extends TinTuc{
    public DocGanDay() {
    }

    public DocGanDay(String idTin, String linkTinTuc, String linkHinhAnh, String tieuDe, Date ngayDang) {
        super(idTin, linkTinTuc, linkHinhAnh, tieuDe, ngayDang);
    }

    public DocGanDay(String idTin, String linkTinTuc, String linkHinhAnh, String tieuDe, String idLink, Date ngayDang) {
        super(idTin, linkTinTuc, linkHinhAnh, tieuDe, idLink, ngayDang);

    }
}
