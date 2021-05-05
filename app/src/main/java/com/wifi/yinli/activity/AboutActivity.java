package com.wifi.yinli.activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.wifi.yinli.fragment.AboutFragment;
import com.wifi.yinli.R;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceScreen;


public class AboutActivity extends AppCompatActivity implements PreferenceFragmentCompat.OnPreferenceStartScreenCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_settings_container, new AboutFragment())
                .commit();
        }
    }
    
    @Override
    public boolean onPreferenceStartScreen(PreferenceFragmentCompat p1, PreferenceScreen p2) {
        getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.activity_settings_container, new AboutFragment())
            .addToBackStack(null)
            .commit();
        return true;
    }
    
}

