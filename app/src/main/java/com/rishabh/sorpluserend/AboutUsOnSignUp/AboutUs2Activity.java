package com.rishabh.sorpluserend.AboutUsOnSignUp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.rishabh.sorpluserend.AboutUs.MainSliderAdapter;
import com.rishabh.sorpluserend.AboutUs.PicassoImageLoadingService;
import com.rishabh.sorpluserend.LogIn.MVP.LogInActivity;
import com.rishabh.sorpluserend.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import ss.com.bannerslider.Slider;

public class AboutUs2Activity extends AppCompatActivity
{
    @BindView(R.id.banner_slider1)
    Slider banner_slider;
    @BindView(R.id.proceed_button)
    Button proceed;
    private static final int REQUEST_SIGNUP = 0;
    com.rishabh.sorpluserend.AboutUs.MainSliderAdapter MainSliderAdapter = new MainSliderAdapter();
    com.rishabh.sorpluserend.AboutUs.PicassoImageLoadingService PicassoImageLoadingService =new PicassoImageLoadingService(AboutUs2Activity.this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus2);
        ButterKnife.bind(this);

        Slider.init(PicassoImageLoadingService);
        banner_slider.setAdapter(MainSliderAdapter);

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LogInActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }
}
