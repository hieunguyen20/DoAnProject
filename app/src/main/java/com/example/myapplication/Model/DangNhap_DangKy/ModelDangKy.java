package com.example.myapplication.Model.DangNhap_DangKy;

import android.util.Log;

import com.example.myapplication.ConnectInternet.DownloadJSON;
import com.example.myapplication.Model.ObjectClass.NguoiDung;
import com.example.myapplication.View.TrangChu.TrangChuActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelDangKy {

    public Boolean DangKyThanhVien(NguoiDung nguoiDung){
        String duongdan = TrangChuActivity.SERVER_NAME;
        boolean kiemtra = false;

        List<HashMap<String, String>> attrs = new ArrayList<>();

        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham", "DangKyThanhVien");

        HashMap<String, String> hsTenNguoiDung = new HashMap<>();
        hsTenNguoiDung.put("tennguoidung", nguoiDung.getTenNguoiDung());

        HashMap<String, String> hsTenDangNhap = new HashMap<>();
        hsTenDangNhap.put("tendangnhap", nguoiDung.getTenDangNhap());

        HashMap<String, String> hsMatKhau = new HashMap<>();
        hsMatKhau.put("matkhau", nguoiDung.getMatKhau());

        HashMap<String, String> hsMaLoaiNguoiDung = new HashMap<>();
        hsMaLoaiNguoiDung.put("maloainguoidung", String.valueOf(nguoiDung.getMaLoaiNguoiDung()));

        HashMap<String, String> hsEmailDocQuyen = new HashMap<>();
        hsEmailDocQuyen.put("emaildocquyen", nguoiDung.getEmailDocQuyen());

        attrs.add(hsTenNguoiDung);
        attrs.add(hsTenDangNhap);
        attrs.add(hsMatKhau);
        attrs.add(hsMaLoaiNguoiDung);
        attrs.add(hsEmailDocQuyen);
        attrs.add(hsHam);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan, attrs);
        downloadJSON.execute();

        try {
            String dulieuJSON = downloadJSON.get();
            Log.d("kiemtra đăng ký", dulieuJSON);
            JSONObject jsonObject = new JSONObject(dulieuJSON);
            String ketqua = jsonObject.getString("ketqua");

            if (ketqua.equals("true")){
                kiemtra = true;
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
