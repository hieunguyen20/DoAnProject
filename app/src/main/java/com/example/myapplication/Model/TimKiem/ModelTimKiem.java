package com.example.myapplication.Model.TimKiem;

import com.example.myapplication.ConnectInternet.DownloadJSON;
import com.example.myapplication.Model.ObjectClass.SanPham;
import com.example.myapplication.View.TrangChu.TrangChuActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelTimKiem {

    public List<SanPham> TimKiemSanPhamTheoTen(String tensp, String tenmang, String tenham, int limit) {

        List<SanPham> sanPhams = new ArrayList<>();

        List<HashMap<String, String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan = TrangChuActivity.SERVER_NAME;

        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham", tenham);

        HashMap<String, String> hsTenSP = new HashMap<>();
        hsTenSP.put("tensp", tensp);

        HashMap<String, String> hsLimit = new HashMap<>();
        hsLimit.put("limit", String.valueOf(limit));

        attrs.add(hsHam);
        attrs.add(hsTenSP);
        attrs.add(hsLimit);
        DownloadJSON downloadJSON = new DownloadJSON(duongdan, attrs);
        // End phương thức post
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();

            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayDanhSachSanPham = jsonObject.getJSONArray(tenmang);

            int dem = jsonArrayDanhSachSanPham.length();
            for (int i = 0; i < dem; i++) {
                SanPham sanPham = new SanPham();
                JSONObject object = jsonArrayDanhSachSanPham.getJSONObject(i);

                sanPham.setMASP(object.getInt("MASP"));
                sanPham.setTENSP(object.getString("TENSP"));
                sanPham.setGIA(object.getInt("GIATIEN"));
                sanPham.setANHLON(TrangChuActivity.SERVER + object.getString("HINHSANPHAM"));
                sanPham.setANHNHO(object.getString("HINHSANPHAMNHO"));

                sanPhams.add(sanPham);
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sanPhams;
    }
}
