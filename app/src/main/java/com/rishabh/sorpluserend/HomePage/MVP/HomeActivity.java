package com.rishabh.sorpluserend.HomePage.MVP;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rishabh.sorpluserend.AboutUs.AboutUsActivity;
import com.rishabh.sorpluserend.Affiliation.MVP.AffiliationActivity;
import com.rishabh.sorpluserend.Cart.CartActivity;
import com.rishabh.sorpluserend.CompanyData.MVP.CompanyActivity;
import com.rishabh.sorpluserend.ContactUs.MVP.ContactActivity;
import com.rishabh.sorpluserend.FAQ.MVP.FAQActivity;
import com.rishabh.sorpluserend.Help.MVP.HelpActivity;
import com.rishabh.sorpluserend.History.MVP.HistoryActivity;
import com.rishabh.sorpluserend.HomePage.MVP.Cart.CartFragment;
import com.rishabh.sorpluserend.HomePage.MVP.Enquiry.EnquiryFragment;
import com.rishabh.sorpluserend.HomePage.MVP.Feed.FeedFragment;
import com.rishabh.sorpluserend.HomePage.MVP.Market.MarketFragment;
import com.rishabh.sorpluserend.HomePage.MVP.Product.ProductFragment;
import com.rishabh.sorpluserend.LogIn.MVP.LogInActivity;
import com.rishabh.sorpluserend.MyDetails.MVP.DetailActivity;
import com.rishabh.sorpluserend.R;
import com.rishabh.sorpluserend.Utilities.MyApplication;
import com.rishabh.sorpluserend.Utilities.SharedPref;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

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
    @BindView(R.id.toolbar_help)
    ImageView help;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    final Fragment fragment1 = new ProductFragment();
    final Fragment fragment2 = new MarketFragment();
    final Fragment fragment3 = new EnquiryFragment();
    final Fragment fragment4 = new CartFragment();
    final Fragment fragment5 = new FeedFragment();
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

        fm.beginTransaction().add(R.id.main_container, fragment5, "5").hide(fragment5).commit();
        fm.beginTransaction().add(R.id.main_container, fragment4, "4").hide(fragment4).commit();
        fm.beginTransaction().add(R.id.main_container, fragment3, "3").hide(fragment3).commit();
        fm.beginTransaction().add(R.id.main_container, fragment2, "2").hide(fragment2).commit();
        fm.beginTransaction().add(R.id.main_container, fragment1, "1").commit();

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
                    startActivity(new Intent(HomeActivity.this, DetailActivity.class));
                else if(id==R.id.navigation_contactus)
                    startActivity(new Intent(HomeActivity.this, ContactActivity.class));
                else if(id==R.id.navigation_faq)
                    startActivity(new Intent(HomeActivity.this, FAQActivity.class));
                else if(id==R.id.navigation_about)
                    startActivity(new Intent(HomeActivity.this, AboutUsActivity.class));
                else if(id==R.id.navigation_history)
                    startActivity(new Intent(HomeActivity.this, HistoryActivity.class));
                else if (id==R.id.navigation_data)
                    startActivity(new Intent(HomeActivity.this, CompanyActivity.class));
                else if(id==R.id.navigation_policy)
                    showPolicy();
                else if(id==R.id.navigation_affiliation)
                    startActivity(new Intent(HomeActivity.this, AffiliationActivity.class));
                return true;
            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCart();
            }
        });
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoHelp();
            }
        });
    }

    private void showPolicy()
    {
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://sites.google.com/view/sorpl-privacypolicy/home"));
        startActivity(intent);
    }

    private void gotoHelp() {
            startActivity(new Intent(this, HelpActivity.class));
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
                    heading.setText("Home");
                    fm.beginTransaction().hide(active).show(fragment1).commit();
                    active = fragment1;
                    return true;

                case R.id.navigation_market:
                    heading.setText("Market");
                    fm.beginTransaction().hide(active).show(fragment2).commit();
                    active = fragment2;
                    return true;

                case R.id.navigation_enquiry:
                    heading.setText("Enquiry");
                    fm.beginTransaction().hide(active).show(fragment3).commit();
                    active = fragment3;
                    return true;

                case R.id.navigation_cart:
                    fm.beginTransaction().hide(active).show(fragment4).commit();
                    active=fragment4;
                    return true;
                case R.id.navigation_feed:
                    heading.setText("News Feed");
                    fm.beginTransaction().hide(active).show(fragment5).commit();
                    active=fragment5;
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
                finish();
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
