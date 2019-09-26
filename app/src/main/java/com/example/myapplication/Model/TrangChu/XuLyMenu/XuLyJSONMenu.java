package com.example.myapplication.Model.TrangChu.XuLyMenu;

import android.util.Log;

import com.example.myapplication.ConnectInternet.DownloadJSON;
import com.example.myapplication.Model.ObjectClass.LoaiSanPham;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class XuLyJSONMenu {

    public List<LoaiSanPham> ParserJSONMenu(String dulieujson) {
        List<LoaiSanPham> loaiSanPhamList = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(dulieujson);
            JSONArray loaisanpham = jsonObject.getJSONArray("LOAISANPHAM");
            int count = loaisanpham.length();
            for (int i = 0; i <count; i++){
                JSONObject value = loaisanpham.getJSONObject(i);

                LoaiSanPham dataloaiSanPham = new LoaiSanPham();
                dataloaiSanPham.setMALOAISP(Integer.parseInt(value.getString("MALOAISP")));
                dataloaiSanPham.setMALOAICHA(Integer.parseInt(value.getString("MALOAI_CHA")));
                dataloaiSanPham.setTENLOAISP((value.getString("TENLOAISP")));

                loaiSanPhamList.add(dataloaiSanPham);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return loaiSanPhamList;
    }

    public List<LoaiSanPham> LayLoaiSanPhamTheoMaLoai(int maloaisp){
        List<LoaiSanPham> loaiSanPhamList = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan ="http://192.168.43.254/webservice/loaisanpham.php";
//        String duongdan ="http://192.168.43.17/webservice/loaisanpham.php";
        HashMap<String, String> hsMaLoaiCha = new HashMap<>();
        hsMaLoaiCha.put("maloaicha", String.valueOf(maloaisp));
        attrs.add(hsMaLoaiCha);
        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        // End phương thức post
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();
            XuLyJSONMenu xuLyJSONMenu = new XuLyJSONMenu();
            loaiSanPhamList = xuLyJSONMenu.ParserJSONMenu(dataJSON);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return loaiSanPhamList;
    }
}
