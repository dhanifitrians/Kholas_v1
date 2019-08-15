package com.example.dhani.kholas.adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dhani.kholas.R;
import com.example.dhani.kholas.model.CurrentImage;
import com.example.dhani.kholas.page.SlidingActivity;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ViewPagerAdapter extends PagerAdapter implements CurrentImage {
    private Context context;
    private LayoutInflater layoutInflater;
    private Integer [] images = {R.drawable.page001,R.drawable.page002,R.drawable.page003,R.drawable.page004,R.drawable.page005,R.drawable.page006};
    private ArrayList<Bitmap> listImage = new ArrayList<Bitmap>();
    private int posisiImage;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public ViewPagerAdapter(Context context) {
        this.context = context;
        AssetManager assetManager = context.getAssets();
        for (int i=0;i<6;i++){
            try (
                    //declaration of inputStream in try-with-resources statement will automatically close inputStream
                    // ==> no explicit inputStream.close() in additional block finally {...} necessary
                    InputStream inputStream = assetManager.open("image/product00"+i+1+".jpg")) {
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                listImage.set(i,bitmap);

            } catch (IOException ex) {
                //ignored
            }
        }
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout, null);
        ImageView imageView = view.findViewById(R.id.imageView);
        imageView.setRotationY(180);
        imageView.setImageResource(images[position]);


        if(listImage.size() > 0)
        {
            int index = listImage.size() -1;
            Bitmap lastbitmap = listImage.get(index);
            imageView.setImageBitmap(lastbitmap);
        }



        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        if (((SlidingActivity)context).getPosisi() == 0){
            vp.setCurrentItem(0);
        }else if (((SlidingActivity)context).getPosisi() == 1){
            vp.setCurrentItem(1);
        }else {
            imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.blank_page));
        }

        vp.setRotationY(180);
        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);

    }

    @Override
    public void getImagePosisi(int posisi) {
        posisiImage = posisi;
    }
}
