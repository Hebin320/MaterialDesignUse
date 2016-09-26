package com.hebin.mduse.listener;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hebin.mduse.R;

import java.util.List;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

public class AdListener implements AutoScrollViewPager.OnPageChangeListener {

    private ImageView[] imageViews;

    public AdListener(ImageView[] imageViews){
        this.imageViews = imageViews;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < imageViews.length; i++) {
            if (i == position){
                imageViews[i].setImageResource(R.drawable.color_circle);
            }else {
                imageViews[i].setImageResource(R.drawable.gray_circle);
            }
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    public static  ImageView[]  setImageView(Context c , LinearLayout llTopPoint , List list){
        final ImageView[] imageViews = new ImageView[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ImageView img = new ImageView(c);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(20, 20);
            layoutParams.setMargins(5,0,5,0);
            img.setLayoutParams(layoutParams);
            imageViews[i] = img;
            if (i == 0) {
                imageViews[i].setBackgroundResource(R.drawable.color_circle);
            } else {
                imageViews[i].setBackgroundResource(R.drawable.gray_circle);
            }

            llTopPoint.addView(imageViews[i]);
        }
        return imageViews;
    }
}
