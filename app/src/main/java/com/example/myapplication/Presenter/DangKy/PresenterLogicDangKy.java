package com.example.myapplication.Presenter.DangKy;

import com.example.myapplication.Model.DangNhap_DangKy.ModelDangKy;
import com.example.myapplication.Model.ObjectClass.NguoiDung;
import com.example.myapplication.View.DangNhap_DangKy.Fragment.FragmentDangKy;
import com.example.myapplication.View.DangNhap_DangKy.ViewDangKy;

public class PresenterLogicDangKy implements  IPresenterDangKy {
    ViewDangKy viewDangKy;
    ModelDangKy modelDangKy;

    public PresenterLogicDangKy(ViewDangKy viewDangKy){
        this.viewDangKy = viewDangKy;
        modelDangKy = new ModelDangKy();
    }

    @Override
    public void ThucHienDangKy(NguoiDung nguoiDung) {
        Boolean kiemtra = modelDangKy.DangKyThanhVien(nguoiDung);
        if (kiemtra){
            this.viewDangKy.DangKyThanhCong();
        } else {
            viewDangKy.DangKyThatBai();
        }
    }
}
