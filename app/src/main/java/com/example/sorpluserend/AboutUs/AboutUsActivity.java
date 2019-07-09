package com.example.sorpluserend.AboutUs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sorpluserend.R;
import com.example.sorpluserend.Utilities.MyApplication;

import butterknife.BindView;
import butterknife.ButterKnife;
import ss.com.bannerslider.Slider;

public class AboutUsActivity  extends AppCompatActivity
{
    @BindView(R.id.toolbar_back)
    ImageView imageView;
    @BindView(R.id.toolbar_text)
    TextView heading;
    @BindView(R.id.banner_slider1)
    Slider banner_slider;

    MainSliderAdapter MainSliderAdapter = new MainSliderAdapter();
    PicassoImageLoadingService PicassoImageLoadingService =new PicassoImageLoadingService(AboutUsActivity.this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);
        ButterKnife.bind(this);
        getSupportActionBar().hide();

        Slider.init(PicassoImageLoadingService);
        banner_slider.setAdapter(MainSliderAdapter);

        heading.setText("About Us");
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                onBackPressed();
            }
        });
        Log.i("fcm", MyApplication.getFcm());
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}


/*
Girdhari Chemicals and Resins Pvt.Ltd is our mother concerned company which has been catering the protective coating industry and printing ink industry now for more than 2 decades.

Subham Oils and Resins Pvt. Ltd is a name of trust, expertise and quality in the field of manufacturing and exporting of Alkyd and Synthetic Resin, both in India and Overseas.

With an ISO Certification of ISO 9001:2015 and the vision of moulding the concept of Exponential growth around Quality products and customer satisfaction the company has always focused on catering to the needs of our customer and create a hassle free and smooth environment to work in.

With a strong team of experts the company has been acing this field and is growing with multiple folds with every passing year.

The important activities of R and D are in the areas of development of new products and applications, development of formulations using alternative raw materials and improvement in its existing products to suit customer requirements.

We focus not only on the traditional products like 'Alkyds' but invest considerable financial resources and time on developing resins that are eco-friendly and move along with the world trends.

We are committed to deliver customers consistent quality products and an assurance of the quality for the entire range of the products.

This assurance begins from the purchase of the raw materials, its analytical testing and approval before consumption, stringent control of processing parameters, intermediate quality monitoring and approval of the finished products before dispatch.

With our main setup in Kolkata we have a head office located in the main city and two manufacturing plants in the industrial belt of the city with a production capacity of almost 50 Metric Tons per day.

Being in this Industry for the last 2 decades has helped us to use our experience and blend it with the modern amenities, equipments and technology to not only make our product the best in the market but at the same time work on it to make a variety of different products and expertise in that.In the coming years, we are also focusing on expanding our wings further across Asia, Africa, and Middle East and South East Asian countries

 */