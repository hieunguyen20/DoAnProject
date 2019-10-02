package com.example.myapplication.View.TrangChu.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.AdapterDienTu;
import com.example.myapplication.Model.ObjectClass.DienTu;
import com.example.myapplication.Model.ObjectClass.SanPham;
import com.example.myapplication.Model.ObjectClass.ThuongHieu;
import com.example.myapplication.Presenter.TrangChu_DienTu.PresenterLogicDienTu;
import com.example.myapplication.R;
import com.example.myapplication.View.TrangChu.ViewDienTu;

import java.util.ArrayList;
import java.util.List;

public class FragmentDienTu extends Fragment implements ViewDienTu {

    RecyclerView recyclerView;
    List<DienTu> dienTuList;
    PresenterLogicDienTu presenterLogicDienTu;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dientu, container, false);

        recyclerView = view.findViewById(R.id.recyclerDienTu);

        presenterLogicDienTu = new PresenterLogicDienTu(this);

        dienTuList = new ArrayList<>();

        presenterLogicDienTu.LayDanhSachDienTu();
        return view;
    }

    @Override
    public void HienThiDanhSach(List<DienTu> dienTus) {

        dienTuList = dienTus;

        AdapterDienTu adapterDienTu = new AdapterDienTu(getContext(), dienTuList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterDienTu);

        adapterDienTu.notifyDataSetChanged();
    }
}
