package com.example.dhani.kholas.page;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.dhani.kholas.R;
import com.example.dhani.kholas.adapter.ViewPagerAdapter;
import com.example.dhani.kholas.base.ObjectBox;
import com.example.dhani.kholas.model.CurrentImage;

public class SlidingActivity extends AppCompatActivity{

    ViewPager viewPager;
    int posisi;
    CurrentImage currentImage;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_sliding);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(posisi);


    }

    //
    public Integer getPosisi(){
        posisi = this.getIntent().getIntExtra("posisi",0);
        return posisi;
    }
}
