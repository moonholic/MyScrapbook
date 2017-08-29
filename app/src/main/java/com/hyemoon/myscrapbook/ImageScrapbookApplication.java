package com.hyemoon.myscrapbook;

import android.support.multidex.MultiDexApplication;

import com.hyemoon.myscrapbook.network.VolleyRequestQueue;
import com.hyemoon.myscrapbook.util.LogUtility;

/**
 * Created by MOON on 8/27/17.
 */

public class ImageScrapbookApplication extends MultiDexApplication {

	@Override
	public void onCreate() {
		super.onCreate();

		LogUtility.setupLogUtility(this);
		initNetwork();
	}

	private void initNetwork() {
		VolleyRequestQueue.initCacheDir(getCacheDir());
		VolleyRequestQueue.handleSSLHandshake();
	}
}
