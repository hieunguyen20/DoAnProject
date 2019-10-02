package com.example.myapplication.Model.ObjectClass;

import android.widget.ImageView;

import java.util.List;

public class DienTu {
    List<ThuongHieu> thuongHieus;
    List<SanPham> sanPhams;
    String tennoibat, tentopnoibat;
    Boolean thuonghieu;

    public Boolean getThuonghieu() {
        return thuonghieu;
    }

    public void setThuonghieu(Boolean thuonghieu) {
        this.thuonghieu = thuonghieu;
    }

    public String getTennoibat() {
        return tennoibat;
    }

    public void setTennoibat(String tennoibat) {
        this.tennoibat = tennoibat;
    }

    public String getTentopnoibat() {
        return tentopnoibat;
    }

    public void setTentopnoibat(String tentopnoibat) {
        this.tentopnoibat = tentopnoibat;
    }


    public List<ThuongHieu> getThuongHieus() {
        return thuongHieus;
    }

    public void setThuongHieus(List<ThuongHieu> thuongHieus) {
        this.thuongHieus = thuongHieus;
    }

    public List<SanPham> getSanPhams() {
        return sanPhams;
    }

    public void setSanPhams(List<SanPham> sanPhams) {
        this.sanPhams = sanPhams;
    }
}
