package com.example.myapplication.Presenter.ChiTietSanPham;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.Model.ChiTietSanPham.ModelChiTietSanPham;
import com.example.myapplication.Model.ObjectClass.SanPham;
import com.example.myapplication.View.ChiTietSanPham.ViewChiTietSanPham;

public class PresenterLogicChiTietSanPham implements  IPresenterChiTietSanPham {
    ViewChiTietSanPham viewChiTietSanPham;
    ModelChiTietSanPham modelChiTietSanPham;

    public PresenterLogicChiTietSanPham(ViewChiTietSanPham viewChiTietSanPham){
        this.viewChiTietSanPham = viewChiTietSanPham;
        modelChiTietSanPham = new ModelChiTietSanPham();
    }

    @Override
    public void LayChiTietSanPham(int masp) {
        SanPham sanPham = modelChiTietSanPham.LayChiTietSanPham("LaySanPhamVsChitietTheoMaSP","CHITIETSANPHAM",masp);
        Log.d("MASP-Presenter", masp+"");
        if (sanPham.getMASP() > 0){
            String[] linkhinhanh = sanPham.getANHNHO().split(",");
            viewChiTietSanPham.HienSliderSanPham(linkhinhanh);
        }
    }
}
