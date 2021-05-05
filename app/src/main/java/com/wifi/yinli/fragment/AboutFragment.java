package com.wifi.yinli.fragment;

import android.os.Bundle;
import androidx.preference.PreferenceFragmentCompat;
import com.wifi.yinli.R;
import android.content.SharedPreferences;
import androidx.preference.PreferenceManager;
import android.widget.Toast;
import androidx.preference.Preference;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.Intent;
import android.net.Uri;

public class AboutFragment extends PreferenceFragmentCompat {

    private Preference versionPreference,githubPreference;
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
        versionPreference = findPreference("version");
        versionPreference.setSummary("v "+getVersionName());
        githubPreference = findPreference("github");
        githubPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener(){

                @Override
                public boolean onPreferenceClick(Preference p1) {
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");//打开手机自带浏览器
                    intent.setData(Uri.parse("https://github.com/yinli155/wifiPasswordViewer"));//需要打开的网址
                    startActivity(intent);
                    return false;
                }
            });
        
    }
    private synchronized String getVersionName() {
        try {
            PackageManager packageManager =getContext().getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
               getContext(). getPackageName(), 0);
            return packageInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
  }
    
    
    

