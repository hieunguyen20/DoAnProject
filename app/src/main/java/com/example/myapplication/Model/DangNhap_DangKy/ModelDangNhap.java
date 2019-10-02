package com.example.myapplication.Model.DangNhap_DangKy;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.myapplication.ConnectInternet.DownloadJSON;
import com.example.myapplication.View.TrangChu.TrangChuActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelDangNhap {

    public String LayCachedDangNhap(Context context){

        SharedPreferences cachedDangNhap = context.getSharedPreferences("dangnhap", Context.MODE_PRIVATE);
        String tennguoidung = cachedDangNhap.getString("tennguoidung","");

        return tennguoidung;

    }

    public boolean KiemTraDangNhap(Context context, String tendangnhap, String matkhau){
        boolean kiemtra = false;
        String duongdan = TrangChuActivity.SERVER_NAME;
        List<HashMap<String, String>> attrs = new ArrayList<>();

        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham", "KiemTraDangNhap");

        HashMap<String, String> hsTenDangNhap = new HashMap<>();
        hsTenDangNhap.put("tendangnhap", tendangnhap);

        HashMap<String, String> hsMatKhau = new HashMap<>();
        hsMatKhau.put("matkhau", matkhau);

        attrs.add(hsHam);
        attrs.add(hsTenDangNhap);
        attrs.add(hsMatKhau);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan, attrs);
        downloadJSON.execute();

        try {
            String dulieu = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dulieu);
            String jsonKetQua = jsonObject.getString("ketqua");
            if (jsonKetQua.equals("true")){
                kiemtra = true;
                String tennguoidung = jsonObject.getString("tennguoidung");
                SharedPreferences cachedDangNhap = context.getSharedPreferences("dangnhap", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = cachedDangNhap.edit();
                editor.putString("tennguoidung", tennguoidung);

                editor.commit();
            } else {
                kiemtra = false;
            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return kiemtra;

    }
}
