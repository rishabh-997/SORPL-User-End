package com.rishabh.sorpluserend.Splash.MVP;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.rishabh.sorpluserend.HomePage.MVP.HomeActivity;
import com.rishabh.sorpluserend.LogIn.MVP.LogInActivity;
import com.rishabh.sorpluserend.R;
import com.rishabh.sorpluserend.Splash.Model.VersionResponse;
import com.rishabh.sorpluserend.Utilities.SharedPref;

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
                public void onClick(DialogInterface dialog, int which)
                {
                    Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.rishabh.sorpluserend"));
                    startActivity(intent);
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