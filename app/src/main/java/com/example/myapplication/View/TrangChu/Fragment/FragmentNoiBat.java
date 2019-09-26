package com.example.myapplication.View.TrangChu.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.AdapterNoiBat;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentNoiBat extends Fragment {
    RecyclerView recyclerView;
    AdapterNoiBat adapterNoiBat;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_noibat, container, false);

        recyclerView = view.findViewById(R.id.recylerNoiBat);
        List<String> dulieu = new ArrayList<>();
        for(int i = 0; i<50; i++){
            String ten = " Noi bat " + i;
            dulieu.add(ten);
        }

//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        adapterNoiBat = new AdapterNoiBat(getActivity(),dulieu);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterNoiBat);
        adapterNoiBat.notifyDataSetChanged();

        return view;
    }
}
