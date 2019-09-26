package com.example.myapplication.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.myapplication.View.TrangChu.Fragment.FragmentChuongTrinhKhuyenMai;
import com.example.myapplication.View.TrangChu.Fragment.FragmentDienTu;
import com.example.myapplication.View.TrangChu.Fragment.FragmentLamDep;
import com.example.myapplication.View.TrangChu.Fragment.FragmentMeVaBe;
import com.example.myapplication.View.TrangChu.Fragment.FragmentNhaCuaVaDoiSong;
import com.example.myapplication.View.TrangChu.Fragment.FragmentNoiBat;
import com.example.myapplication.View.TrangChu.Fragment.FragmentTheThaoVaDuLich;
import com.example.myapplication.View.TrangChu.Fragment.FragmentThuongHieu;
import com.example.myapplication.View.TrangChu.Fragment.FragmentThoiTrang;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    List<Fragment> listFragment = new ArrayList<Fragment>();
    List<String> titleFragment = new ArrayList<String>();

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
        listFragment.add(new FragmentNoiBat());
        listFragment.add(new FragmentChuongTrinhKhuyenMai());
        listFragment.add(new FragmentDienTu());
        listFragment.add(new FragmentNhaCuaVaDoiSong());
        listFragment.add(new FragmentMeVaBe());
        listFragment.add(new FragmentLamDep());
        listFragment.add(new FragmentThoiTrang());
        listFragment.add(new FragmentTheThaoVaDuLich());
        listFragment.add(new FragmentThuongHieu());

        titleFragment.add("Nổi bật");
        titleFragment.add("Chương trình khuyến mãi");
        titleFragment.add("Điện tử");
        titleFragment.add("Nhà cửa & đời sống");
        titleFragment.add("Mẹ và bé");
        titleFragment.add("Làm đẹp");
        titleFragment.add("Thời trang");
        titleFragment.add("Thể thao và du lịch");
        titleFragment.add("Thương hiệu");
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return listFragment.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titleFragment.get(position);
    }
}
