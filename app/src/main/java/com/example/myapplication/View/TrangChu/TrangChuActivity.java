package com.example.myapplication.View.TrangChu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import com.example.myapplication.Adapter.ExpandAdapter;
import com.example.myapplication.Adapter.ViewPagerAdapter;
import com.example.myapplication.Model.DangNhap_DangKy.ModelDangNhap;
import com.example.myapplication.Model.ObjectClass.LoaiSanPham;
import com.example.myapplication.Presenter.TrangChu.XuLyMenu.PresenterLogicXuLyMenu;
import com.example.myapplication.R;
import com.example.myapplication.View.DangNhap_DangKy.DangNhapActivity;
import com.example.myapplication.View.TimKiem.TimKiemActivity;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class TrangChuActivity extends AppCompatActivity implements ViewXuLyMenu, AppBarLayout.OnOffsetChangedListener, View.OnClickListener {

    public static final String SERVER_NAME = "http://192.168.43.254/webservice/apiserver.php";
    public static final String SERVER = "http://192.168.43.254/webservice";

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    ExpandableListView expandableListView;
    AppBarLayout appBarLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Menu menu;
    ModelDangNhap modelDangNhap;
    LinearLayout linearLayoutSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);

        toolbar = findViewById(R.id.toolbar);
        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.viewpager);
        drawerLayout = findViewById(R.id.drawerLayout);
        appBarLayout = findViewById(R.id.appbar);
        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        expandableListView = findViewById(R.id.epMenu);
        linearLayoutSearch = findViewById(R.id.lnSearch);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerToggle.syncState();

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        PresenterLogicXuLyMenu logicXuLyMenu = new PresenterLogicXuLyMenu(this);
        logicXuLyMenu.LayDanhSachMenu();

        linearLayoutSearch.setOnClickListener(this);

        appBarLayout.addOnOffsetChangedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menutrangchu, menu);
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        int id = item.getItemId();
        switch (id) {
            case R.id.itDangNhap:
                Intent iDangNhap = new Intent(this, DangNhapActivity.class);
                startActivity(iDangNhap);

            case R.id.itSearch:
                Intent iTimkiem = new Intent(this, TimKiemActivity.class);
                startActivity(iTimkiem);
                break;
        }
        return true;
    }

    @Override
    public void HienThiDanhSachMenu(List<LoaiSanPham> loaiSanPhamList) {
//        Log.d("kiem tra drawerMenu", loaiSanPhamList.get(0).getTENLOAISP());
        ExpandAdapter expandAdapter = new ExpandAdapter(this, loaiSanPhamList);
        expandableListView.setAdapter(expandAdapter);
        expandAdapter.notifyDataSetChanged();
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        // Kiểm tra chiều cao của AppBarLayout
//        Log.d("Kiem tra",collapsingToolbarLayout.getHeight() +" - " + i + " - " +  ViewCompat.getMinimumHeight(collapsingToolbarLayout));
        if (collapsingToolbarLayout.getHeight() + i <= 1.5 * ViewCompat.getMinimumHeight(collapsingToolbarLayout)) {
            LinearLayout linearLayout = findViewById(R.id.lnSearch);
            linearLayout.animate().alpha(0).setDuration(200);

                MenuItem itSearch = menu.findItem(R.id.itSearch);
                itSearch.setVisible(true);

        } else {
            LinearLayout linearLayout = findViewById(R.id.lnSearch);
            linearLayout.animate().alpha(1).setDuration(200);
            try {
                MenuItem itSearch = menu.findItem(R.id.itSearch);
                itSearch.setVisible(false);
            } catch (Exception e) {

            }

        }

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.lnSearch:
                Intent iTimkiem = new Intent(this, TimKiemActivity.class);
                startActivity(iTimkiem);
                break;
        }
    }
}
