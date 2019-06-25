package com.example.sorpluserend.HomePage.MVP;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabItem;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.sorpluserend.HomePage.MVP.Cart.CartFragment;
import com.example.sorpluserend.HomePage.MVP.Enquiry.EnquiryFragment;
import com.example.sorpluserend.HomePage.MVP.Market.MarketFragment;
import com.example.sorpluserend.HomePage.MVP.Product.ProductFragment;
import com.example.sorpluserend.LogIn.MVP.LogInActivity;
import com.example.sorpluserend.R;
import com.example.sorpluserend.Utilities.MyApplication;
import com.example.sorpluserend.Utilities.SharedPref;

import timber.log.Timber;

public class HomeActivity extends AppCompatActivity implements HomeContract.view
{
    HomeContract.presenter presenter;
    DrawerLayout dl;
    ActionBarDrawerToggle abdt;
    SharedPref sharedPref;

    final Fragment fragment1 = new ProductFragment();
    final Fragment fragment2 = new MarketFragment();
    final Fragment fragment3 = new EnquiryFragment();
    final Fragment fragment4 = new CartFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragment1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        sharedPref=new SharedPref(this);
        presenter=new HomePresenter(this);

        Log.i("MY FCM",MyApplication.getFcm());

        dl=findViewById(R.id.drawer_layout);
        abdt=new ActionBarDrawerToggle(this,dl,R.string.Open,R.string.Close);
        abdt.setDrawerIndicatorEnabled(true);
        dl.addDrawerListener(abdt);
        abdt.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navview=findViewById(R.id.nav_view);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fm.beginTransaction().add(R.id.main_container,fragment4,"4").hide(fragment4).commit();
        fm.beginTransaction().add(R.id.main_container, fragment3, "3").hide(fragment3).commit();
        fm.beginTransaction().add(R.id.main_container, fragment2, "2").hide(fragment2).commit();
        fm.beginTransaction().add(R.id.main_container,fragment1, "1").commit();

        navview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {
                int id=menuItem.getItemId();
                if(id==R.id.navigation_logout)
                {
                    finish();
                    sharedPref.setMobile("");
                    sharedPref.setAccessToken("");
                    startActivity(new Intent(HomeActivity.this, LogInActivity.class));
                }
                else if(id==R.id.navigation_profile)
                {
                    Toast.makeText(HomeActivity.this, "Mobile is "+sharedPref.getMobile(), Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }

    BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_products:
                    fm.beginTransaction().hide(active).show(fragment1).commit();
                    active = fragment1;
                    return true;

                case R.id.navigation_market:
                    fm.beginTransaction().hide(active).show(fragment2).commit();
                    active = fragment2;
                    return true;

                case R.id.navigation_enquiry:
                    fm.beginTransaction().hide(active).show(fragment3).commit();
                    active = fragment3;
                    return true;

                case R.id.navigation_cart:
                    fm.beginTransaction().hide(active).show(fragment4).commit();
                    active=fragment4;
                    return true;
            }
            return false;
        }
    };

    public boolean onOptionsItemSelected(MenuItem item)
    {
        return abdt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }
}
