package com.hyemoon.myscrapbook.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

/**
 * Created by MOON on 8/26/17.
 */

public class LogUtility {


	private static boolean debuggable = false;

	public static void setupLogUtility(Context context){
		debuggable = isDebuggable(context);
	}

	public static boolean isDebuggable(Context context) {
		PackageManager manager = context.getPackageManager();
		ApplicationInfo appInfo = null;
		try {
			appInfo = manager.getApplicationInfo(context.getPackageName(), 0);
		} catch (PackageManager.NameNotFoundException e) {
			return false;
		}

		if ((appInfo.flags & ApplicationInfo.FLAG_DEBUGGABLE) == ApplicationInfo.FLAG_DEBUGGABLE) {
			return true;
		}
		return false;
	}

	public static final void v(String tag, String msg) {
		if (debuggable) {
			Log.v(tag, msg);
		}
	}

	public static final void d(String tag, String msg) {

		if (debuggable) {
			Log.d(tag, msg);
		}
	}

	public static final void i(String tag, String msg) {
		if (debuggable) {
			Log.i(tag, msg);
		}
	}

	public static final void w(String tag, String msg) {
		if (debuggable) {
			Log.w(tag, msg);
		}
	}

	public static final void e(String tag, String msg) {
		if (debuggable) {
			Log.e(tag, msg);
		}
	}

	public static final void e(String tag, String msg, Exception e) {
		if (debuggable) {
			Log.e(tag, msg, e);
		}
	}

	public static final void w(String tag, String msg, Exception e) {
		if (debuggable) {
			Log.w(tag, msg, e);
		}
	}
}
