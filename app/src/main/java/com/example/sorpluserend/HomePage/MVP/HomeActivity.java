package com.example.sorpluserend.HomePage.MVP;

import android.content.DialogInterface;
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
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sorpluserend.AboutUs.AboutUsActivity;
import com.example.sorpluserend.Cart.CartActivity;
import com.example.sorpluserend.ContactUs.MVP.ContactActivity;
import com.example.sorpluserend.FAQ.MVP.FAQActivity;
import com.example.sorpluserend.History.MVP.HistoryActivity;
import com.example.sorpluserend.HomePage.MVP.Cart.CartFragment;
import com.example.sorpluserend.HomePage.MVP.Enquiry.EnquiryFragment;
import com.example.sorpluserend.HomePage.MVP.Market.MarketFragment;
import com.example.sorpluserend.HomePage.MVP.Product.ProductFragment;
import com.example.sorpluserend.LogIn.MVP.LogInActivity;
import com.example.sorpluserend.R;
import com.example.sorpluserend.Utilities.MyApplication;
import com.example.sorpluserend.Utilities.SharedPref;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

import static com.example.sorpluserend.Utilities.MyApplication.getContext;

public class HomeActivity extends AppCompatActivity implements HomeContract.view, CartFragment.CartFragmentListener, ProductFragment.ProductFragmentListener
{
    HomeContract.presenter presenter;
    DrawerLayout dl;
    ActionBarDrawerToggle abdt;
    SharedPref sharedPref;

    @BindView(R.id.toolbar_text)
    TextView heading;
    @BindView(R.id.toolbar_cart)
    ImageView cart;
    @BindView(R.id.toolbar)
    Toolbar toolbar;


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
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        heading.setText("Home");

        sharedPref=new SharedPref(this);
        ButterKnife.bind(this);
        presenter=new HomePresenter(this);

        Timber.i(MyApplication.getFcm());

        dl=findViewById(R.id.drawer_layout);
        abdt=new ActionBarDrawerToggle(this,dl,toolbar,R.string.Open,R.string.Close);
        abdt.setDrawerIndicatorEnabled(true);
        dl.addDrawerListener(abdt);
        abdt.syncState();
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
                    logout();
                }
                else if(id==R.id.navigation_profile)
                    Toast.makeText(HomeActivity.this, "Mobile is "+sharedPref.getMobile(), Toast.LENGTH_LONG).show();
                else if(id==R.id.navigation_contactus)
                    startActivity(new Intent(HomeActivity.this, ContactActivity.class));
                else if(id==R.id.navigation_faq)
                    startActivity(new Intent(HomeActivity.this, FAQActivity.class));
                else if(id==R.id.navigation_about)
                    startActivity(new Intent(HomeActivity.this, AboutUsActivity.class));
                else if(id==R.id.navigation_history)
                    startActivity(new Intent(HomeActivity.this, HistoryActivity.class));
                return true;
            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCart();
            }
        });
    }

    private void goToCart()
    {
        startActivity(new Intent(this, CartActivity.class));
    }

    private void logout()
    {
        final AlertDialog alertDialog=new AlertDialog.Builder(this).create();
        alertDialog.setTitle("LOG OUT");
        alertDialog.setMessage("Are You Sure you want to logout ?");
        alertDialog.setCancelable(false);

        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
                finish();
                sharedPref.setMobile("");
                sharedPref.setAccessToken("");
                startActivity(new Intent(HomeActivity.this, LogInActivity.class));
            }
        });
        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
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

    @Override
    public void UPdateCart() {
        ((CartFragment)fragment4).RefreshCart();
    }


    @Override
    public void onBackPressed() {
        final AlertDialog alertDialog=new AlertDialog.Builder(this).create();
        alertDialog.setTitle("EXIT");
        alertDialog.setMessage("Are You Sure you want to exit ?");
        alertDialog.setCancelable(false);

        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
                HomeActivity.super.onBackPressed();
            }
        });
        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }
}
