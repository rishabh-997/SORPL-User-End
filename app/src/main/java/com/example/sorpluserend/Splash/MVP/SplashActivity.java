package com.example.sorpluserend.Splash.MVP;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.sorpluserend.HomePage.MVP.HomeActivity;
import com.example.sorpluserend.LogIn.MVP.LogInActivity;
import com.example.sorpluserend.R;
import com.example.sorpluserend.Splash.Model.VersionResponse;
import com.example.sorpluserend.Utilities.SharedPref;
import com.example.sorpluserend.Utilities.Utils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SplashActivity extends AppCompatActivity implements SplashContract.view
{
    String versionName;
    SharedPref sharedPref;

    SplashContract.presenter presenter;
    android.os.Handler Handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        sharedPref=new SharedPref(this);
        presenter=new SplashPresenter(this);

        presenter.getVersion();
    }

    @Override
    public void showToast(String message)
    {
        Toast.makeText(this, "Internet Not Connected ! Restart the App", Toast.LENGTH_LONG).show();
    }

    @Override
    public void checkVersion(VersionResponse body)
    {
        try
        {
            versionName = this.getPackageManager().getPackageInfo(this.getPackageName(), 0).versionName;
        }
        catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
        }

        if(!body.getVersion().equals(versionName) || body.getCompulsary_update())
        {
            Toast.makeText(this, "Please Update the App from PlayStore !", Toast.LENGTH_SHORT).show();

            final AlertDialog alertDialog=new AlertDialog.Builder(this).create();
            alertDialog.setTitle("UPDATE");
            alertDialog.setMessage("Please update the app first !");
            alertDialog.setCancelable(false);

            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Update", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    alertDialog.dismiss();
                    finish();
                }
            });
            alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Exit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    alertDialog.dismiss();
                    finish();
                }
            });
            alertDialog.show();

        }
        else
        {
            if(sharedPref.getAccessToken().equals(""))
            {
                Handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                        Intent intent=new Intent(SplashActivity.this, LogInActivity.class);
                        startActivity(intent);
                    }
                },1000);
            }
            else
            {
                Handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                        Intent intent=new Intent(SplashActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }
                },1000);
            }
        }
    }
}