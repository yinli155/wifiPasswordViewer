package com.wifi.yinli.activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;

public class AboutActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PreferenceScreen preferenceScreen = this.getPreferenceManager().createPreferenceScreen(this);//先构建PreferenceScreen对象得到一个布局容preferenceScreen
        this.setPreferenceScreen(preferenceScreen);//设置容器
        PreferenceCategory preferenceCategory=new PreferenceCategory(this);//构建一个子Preference，待添加到容器中
        preferenceCategory.setTitle("关于");//设置title
        Preference preference=new Preference(this);//构建一个子Preference，待添加到容器中
        preference.setTitle("当前版本");//设置title
        preference.setSummary("v"+getVersionName());
        Preference preference2=new Preference(this);//构建一个子Preference，待添加到容器中
        preference2.setTitle("开发者");//设置title
        preference2.setSummary("by音离");
        preferenceScreen.addPreference(preferenceCategory);//添加到容器中
        preferenceScreen.addPreference(preference);//添加到容器中
        preferenceScreen.addPreference(preference2);//添加到容器中

    }
    private synchronized String getVersionName() {
        try {
            PackageManager packageManager = getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                getPackageName(), 0);
            return packageInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

