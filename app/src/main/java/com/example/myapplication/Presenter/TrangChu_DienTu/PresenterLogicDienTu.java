package com.example.myapplication.Presenter.TrangChu_DienTu;

import android.view.View;

import com.example.myapplication.Model.ObjectClass.DienTu;
import com.example.myapplication.Model.ObjectClass.SanPham;
import com.example.myapplication.Model.ObjectClass.ThuongHieu;
import com.example.myapplication.Model.Trangchu_DienTu.ModelDienTu;
import com.example.myapplication.View.TrangChu.ViewDienTu;

import java.util.ArrayList;
import java.util.List;

public class PresenterLogicDienTu implements IPresenterDienTu {

    ViewDienTu viewDienTu;
    ModelDienTu modelDienTu;

    public PresenterLogicDienTu(ViewDienTu viewDienTu){
        this.viewDienTu = viewDienTu;
        modelDienTu = new ModelDienTu();
    }

    @Override
    public void LayDanhSachDienTu() {
        List<DienTu> dienTuList = new ArrayList<>();

        List<ThuongHieu> thuongHieuList =  modelDienTu.LayDanhSachThuongHieuLon("LayDanhSachCacThuongHieuLon","DANHSACHTHUONGHIEU");
        List<SanPham> sanPhamList = modelDienTu.LayDanhSachSanPhamTOP("LayDanhSachTopDienThoaiMayTinhBang","TOPDIENTHOAIVAMAYTINHBANG");

        DienTu dienTu1 = new DienTu();
        dienTu1.setThuongHieus(thuongHieuList);
        dienTu1.setSanPhams(sanPhamList);
        dienTu1.setTennoibat("Thương hiệu lớn");
        dienTu1.setTentopnoibat("Top điện thoại & máy tính bảng");
        dienTu1.setThuonghieu(true);
        dienTuList.add(dienTu1);


        List<ThuongHieu> phukienList =  modelDienTu.LayDanhSachThuongHieuLon("LayDanhSachPhuKien","DANHSACHPHUKIEN");
        List<SanPham> topphukienList = modelDienTu.LayDanhSachSanPhamTOP("LayDanhSachTopPhuKien","TOPPHUKIEN");

        DienTu dienTu2 = new DienTu();
        dienTu2.setThuongHieus(phukienList);
        dienTu2.setSanPhams(topphukienList);
        dienTu2.setTennoibat("Phụ kiện");
        dienTu2.setTentopnoibat("Top phụ kiện");
        dienTu2.setThuonghieu(false);
        dienTuList.add(dienTu2);

        List<ThuongHieu> tienichList =  modelDienTu.LayDanhSachThuongHieuLon("LayDanhSachTienIch","DANHSACHTIENICH");
        List<SanPham> toptienichList = modelDienTu.LayDanhSachSanPhamTOP("LayTopTienIch","TOPTIENICH");

        DienTu dienTu3 = new DienTu();
        dienTu3.setThuongHieus(tienichList);
        dienTu3.setSanPhams(toptienichList);
        dienTu3.setTennoibat("Tiện ích");
        dienTu3.setTentopnoibat("Top video & tivi");
        dienTu3.setThuonghieu(false);
        dienTuList.add(dienTu3);

        if (thuongHieuList.size() > 0 ){
            viewDienTu.HienThiDanhSach(dienTuList);
        }
    }
}
