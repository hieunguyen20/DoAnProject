package com.example.myapplication.View.DangNhap_DangKy.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import com.example.myapplication.Model.ObjectClass.NguoiDung;
import com.example.myapplication.Presenter.DangKy.PresenterLogicDangKy;
import com.example.myapplication.R;
import com.example.myapplication.View.DangNhap_DangKy.ViewDangKy;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FragmentDangKy extends Fragment implements ViewDangKy, View.OnClickListener, View.OnFocusChangeListener {

    PresenterLogicDangKy presenterLogicDangKy;
    Button btnDangKy;
    EditText edHoTen, edMatKhau, edNhapLaiMatKhau, edDiaChiEmail;
    SwitchCompat sEmailDocQuyen;
    TextInputLayout input_edHoTen, input_edMatKhau, input_edNhapLaimatKhau, input_edDiaChiEmail;
    Boolean kiemtrathongtin = false;

    Matcher matcher;
    String MATCHER_PATERN = "((?=.*\\d)(?=.*[A-Z])(?=.*[a-z]).{6,20})";
    Pattern pattern;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dang_ky, container, false);

        btnDangKy = view.findViewById(R.id.btnDangKy);
        edHoTen = view.findViewById(R.id.edHoTenDK);
        edMatKhau = view.findViewById(R.id.edMatKhauDK);
        edNhapLaiMatKhau = view.findViewById(R.id.edNhapLaiMatKhauDK);
        edDiaChiEmail = view.findViewById(R.id.edDiaChiEmailDK);
        sEmailDocQuyen = view.findViewById(R.id.sEmailDocQuyen);
        input_edHoTen = view.findViewById(R.id.input_edHoTenDK);
        input_edMatKhau = view.findViewById(R.id.input_edMatKhauDK);
        input_edNhapLaimatKhau = view.findViewById(R.id.input_edNhapLaiMatKhauDK);
        input_edDiaChiEmail = view.findViewById(R.id.input_edDiaChiEmailDK);

        presenterLogicDangKy = new PresenterLogicDangKy(this);

        btnDangKy.setOnClickListener(this);
        edHoTen.setOnFocusChangeListener(this);
        edMatKhau.setOnFocusChangeListener(this);
        edNhapLaiMatKhau.setOnFocusChangeListener(this);
        edDiaChiEmail.setOnFocusChangeListener(this);

        return view;
    }

    @Override
    public void DangKyThanhCong() {
        Toast.makeText(getActivity(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void DangKyThatBai() {
        Toast.makeText(getActivity(), "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnDangKy:
                btnDangKy();
                break;
        }
    }

    String emaildocquyen = "";

    private void btnDangKy() {
        String hoTen = edHoTen.getText().toString();
        String email = edDiaChiEmail.getText().toString();
        String matkhau = edMatKhau.getText().toString();
        String nhaplaimatkhau = edNhapLaiMatKhau.getText().toString();

        sEmailDocQuyen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                emaildocquyen = b + "";
            }
        });

        if (kiemtrathongtin) {

            NguoiDung nguoiDung = new NguoiDung();
            nguoiDung.setTenNguoiDung(hoTen);
            nguoiDung.setTenDangNhap(email);
            nguoiDung.setMatKhau(matkhau);
            nguoiDung.setEmailDocQuyen(emaildocquyen);
            nguoiDung.setMaLoaiNguoiDung(2);

            presenterLogicDangKy.ThucHienDangKy(nguoiDung);
            Log.d("check_đangky", "đang ky thanh cong");

            edHoTen.setText("");
            edDiaChiEmail.setText("");
            edMatKhau.setText("");
            edNhapLaiMatKhau.setText("");
        }


    }

    @Override
    public void onFocusChange(View view, boolean b) {
        int id = view.getId();
        switch (id) {
            case R.id.edHoTenDK:
                if (!b) {
                    String chuoi = ((EditText) view).getText().toString();
                    if (chuoi.trim().equals("") || chuoi.equals(null)) {
                        input_edHoTen.setErrorEnabled(true);
                        input_edHoTen.setError("Bạn chưa nhập mục này");
                        kiemtrathongtin = false;
                    } else {
                        input_edHoTen.setErrorEnabled(false);
                        input_edHoTen.setError("");
                        kiemtrathongtin = true;
                    }
                }
                break;
            case R.id.edDiaChiEmailDK:
                if (!b) {
                    String chuoi = ((EditText) view).getText().toString();
                    if (chuoi.trim().equals("") || chuoi.equals(null)) {
                        input_edDiaChiEmail.setErrorEnabled(true);
                        input_edDiaChiEmail.setError("Bạn chưa nhập mục này");
                        kiemtrathongtin = false;
                    } else {
                        Boolean kiemtra = Patterns.EMAIL_ADDRESS.matcher(chuoi).matches();
                        if (!kiemtra) {
                            input_edDiaChiEmail.setErrorEnabled(true);
                            input_edDiaChiEmail.setError("Đây không phải địa chỉ email");
                            kiemtrathongtin = false;
                        } else {
                            input_edDiaChiEmail.setErrorEnabled(false);
                            input_edDiaChiEmail.setError("");
                            kiemtrathongtin = true;
                        }

                    }
                }
                break;
            case R.id.edMatKhauDK:
                pattern = Pattern.compile(MATCHER_PATERN);

                if (!b) {
                    String chuoi = ((EditText) view).getText().toString();
                    matcher = pattern.matcher(chuoi);
                    if (chuoi.trim().equals("") || chuoi.equals(null)) {
                        input_edMatKhau.setErrorEnabled(true);
                        input_edMatKhau.setError("Bạn chưa nhập mục này");
                        kiemtrathongtin = false;
                    } else {
                        if (!matcher.matches()) {
                            input_edMatKhau.setErrorEnabled(true);
                            input_edMatKhau.setError("Mật khẩu phải trên 6 ký tự và một chữ hoa");
                            kiemtrathongtin = false;
                        } else {
                            input_edMatKhau.setErrorEnabled(false);
                            input_edMatKhau.setError("");
                            kiemtrathongtin = true;
                        }
                    }
                }
                break;
            case R.id.edNhapLaiMatKhauDK:

                if (!b) {
                    String chuoi = ((EditText) view).getText().toString();
                    String makhau = edMatKhau.getText().toString();
                    if (!chuoi.equals(makhau)) {
                        input_edNhapLaimatKhau.setErrorEnabled(true);
                        input_edNhapLaimatKhau.setError("Mật khẩu không trùng khớp");
                        kiemtrathongtin = false;
                    } else {
                        input_edNhapLaimatKhau.setErrorEnabled(false);
                        input_edNhapLaimatKhau.setError("");
                        kiemtrathongtin = true;
                    }
                }

                break;
        }

    }
}
