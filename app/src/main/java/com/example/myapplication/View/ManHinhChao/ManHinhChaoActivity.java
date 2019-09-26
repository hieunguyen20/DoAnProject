package com.example.myapplication.View.ManHinhChao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.View.TrangChu.TrangChuActivity;

public class ManHinhChaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chao);

        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                }catch (Exception e){

                } finally {
                    Intent intentTrangChu = new Intent(ManHinhChaoActivity.this, TrangChuActivity.class);
                    startActivity(intentTrangChu);
                }
            }
        });

        thread.start();
    }
}
