package com.rok.demoproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.rok.demoproject.Fragment.AppliancesFragment;
import com.rok.demoproject.Fragment.HistoryFragment;
import com.rok.demoproject.Fragment.HomeFragment;
import com.rok.demoproject.Fragment.ProfileFragment;

public class HomeActivity extends AppCompatActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener{

    BottomNavigationView homeBottomNavigation;
    boolean doubleTap;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        preferences = PreferenceManager.getDefaultSharedPreferences(HomeActivity.this);
        editor = preferences.edit();

        homeBottomNavigation = findViewById(R.id.homeBottomNavigation);
        homeBottomNavigation.setOnNavigationItemSelectedListener(this);
        homeBottomNavigation.setSelectedItemId(R.id.navHome);

        boolean isFirstTime = preferences.getBoolean("isFirstTime",true);
        if (isFirstTime)
        {
            welcome();
        }

    }

    private void welcome() {

        AlertDialog.Builder ad = new AlertDialog.Builder(HomeActivity.this);
        ad.setTitle("Blood Link");
        ad.setMessage("Welcome to HomeCare App");
        ad.setPositiveButton("Thank You", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).show().create();
        editor.putBoolean("isFirstTime",false).commit();
    }

    @Override
    public void onBackPressed() {
        if (doubleTap)
        {
            finishAffinity();
        }
        else
        {
            Toast.makeText(this, "Double tap to exit", Toast.LENGTH_SHORT).show();
            doubleTap = true;
            Handler h = new Handler();
            h.postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleTap = false;
                }
            },2000);
        }
    }

    HomeFragment homeFragment = new HomeFragment();
    HistoryFragment historyFragment = new HistoryFragment();
    AppliancesFragment appliancesFragment = new AppliancesFragment();
    ProfileFragment profileFragment = new ProfileFragment();
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        if (menuItem.getItemId() == R.id.navHome)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.homeFrameLayout,homeFragment).commit();
        }
        else if (menuItem.getItemId() == R.id.navAppliances)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.homeFrameLayout,appliancesFragment).commit();
        }
        else if (menuItem.getItemId() == R.id.navHistory)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.homeFrameLayout,historyFragment).commit();
        }
        else if (menuItem.getItemId() == R.id.navProfile)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.homeFrameLayout,profileFragment).commit();
        }

        return true;
    }
}