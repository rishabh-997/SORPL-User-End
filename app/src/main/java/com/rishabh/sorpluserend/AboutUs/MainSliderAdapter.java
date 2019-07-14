package com.rishabh.sorpluserend.AboutUs;

import com.rishabh.sorpluserend.R;

import ss.com.bannerslider.adapters.SliderAdapter;
import ss.com.bannerslider.viewholder.ImageSlideViewHolder;

public class MainSliderAdapter extends SliderAdapter {

    @Override
    public int getItemCount() {
        return 4;
    }

    @Override
    public void onBindImageSlide(int position, ImageSlideViewHolder viewHolder) {
        switch (position) {
            case 0:
                viewHolder.bindImageSlide(R.mipmap.app1);
                break;
            case 1:
                viewHolder.bindImageSlide(R.mipmap.app2);
                break;
            case 2:
                viewHolder.bindImageSlide(R.mipmap.app3);
                break;
            case 3:
                viewHolder.bindImageSlide(R.mipmap.app4);
        }
    }
}