package com.example.myapplication.Presenter.ChiTietSanPham;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

public class FragmentSliderChiTietSanPham extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_slider_chitietsanpham, container, false);
        Bundle bundle = getArguments();
        String linkhinh = bundle.getString("linkhinh");
        ImageView imageView = view.findViewById(R.id.imHinhSlider);

        Picasso.with(getContext()).load(linkhinh).into(imageView);

        return view;
    }
}
