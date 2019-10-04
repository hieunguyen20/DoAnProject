package com.example.myapplication.Presenter.TimKiem;

import com.example.myapplication.Model.ObjectClass.SanPham;
import com.example.myapplication.Model.TimKiem.ModelTimKiem;
import com.example.myapplication.View.TimKiem.ViewTimKiem;

import java.util.List;

public class PresenterLogicTimKiem implements IPresenterTinKiem{
   ViewTimKiem viewTimKiem;
   ModelTimKiem modelTimKiem;

    public PresenterLogicTimKiem(ViewTimKiem viewTimKiem){
        this.viewTimKiem = viewTimKiem;
        modelTimKiem = new ModelTimKiem();
    }

    @Override
    public void TimKiemSanPhamTheoTenSP(String tensp, int limit) {
        List<SanPham> sanPhamList = modelTimKiem.TimKiemSanPhamTheoTen(tensp,"DANHSACHSANPHAM","TimKiemSanPhamTheoTenSP",limit);

        if (sanPhamList.size() >0){
            viewTimKiem.TimKiemThanhCong(sanPhamList);
        }else{
            viewTimKiem.TimKiemThatBai();
        }
    }
}
