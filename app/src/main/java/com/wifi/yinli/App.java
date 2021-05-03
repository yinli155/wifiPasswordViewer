package com.wifi.yinli;

import android.app.Application;

public class App extends Application {

	private static App sApp;

	@Override
	public void onCreate() {
		super.onCreate();
		sApp = this;
		CrashHandler.init(this);
	}

	public static App getApp() {
		return sApp;
	}

}
