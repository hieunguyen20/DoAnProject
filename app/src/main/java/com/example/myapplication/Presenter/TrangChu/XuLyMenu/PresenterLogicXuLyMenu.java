package com.example.myapplication.Presenter.TrangChu.XuLyMenu;

import android.util.Half;
import android.util.Log;

import com.example.myapplication.ConnectInternet.DownloadJSON;
import com.example.myapplication.Model.ObjectClass.LoaiSanPham;
import com.example.myapplication.Model.TrangChu.XuLyMenu.XuLyJSONMenu;
import com.example.myapplication.View.TrangChu.TrangChuActivity;
import com.example.myapplication.View.TrangChu.ViewXuLyMenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class PresenterLogicXuLyMenu implements IPresenterXuLyMenu {
    ViewXuLyMenu viewXuLyMenu;

    public PresenterLogicXuLyMenu(ViewXuLyMenu viewXuLyMenu){
        this.viewXuLyMenu = viewXuLyMenu;
    }

    @Override
    public void LayDanhSachMenu() {

        List<LoaiSanPham> loaiSanPhamList;
        String dataJSON ="";
        List<HashMap<String, String>> attrs = new ArrayList<>();

//        //Lấy bằng GET
//
//        String duongdan ="http://192.168.43.254/webservice/loaisanpham.php?maloaicha=0";
//
//        DownloadJSON downloadJSON = new DownloadJSON(duongdan);
//
//        // End methodGET

        //Lấy bằng phương thức POST

        String duongdan = TrangChuActivity.SERVER_NAME;

        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham","LayDanhSachMenu");

        HashMap<String, String> hsMaLoaiCha = new HashMap<>();
        hsMaLoaiCha.put("maloaicha","0");

        attrs.add(hsMaLoaiCha);
        attrs.add(hsHam);
        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);

        // End phương thức post
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();
            XuLyJSONMenu xuLyJSONMenu = new XuLyJSONMenu();
            loaiSanPhamList = xuLyJSONMenu.ParserJSONMenu(dataJSON);
            viewXuLyMenu.HienThiDanhSachMenu(loaiSanPhamList);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
