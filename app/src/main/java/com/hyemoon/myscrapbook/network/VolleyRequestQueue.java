package com.hyemoon.myscrapbook.network;

import android.annotation.SuppressLint;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;

import java.io.File;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Created by MOON on 8/27/17
 */

public class VolleyRequestQueue {

	private static VolleyRequestQueue INSTANCE;
	private static File cacheDir;
	private RequestQueue requestQueue;

	private VolleyRequestQueue() {
		requestQueue = getRequestQueue();
	}

	public static void initCacheDir(File cacheDir) {
		VolleyRequestQueue.cacheDir = cacheDir;
	}

	public static VolleyRequestQueue getInstance() {
		if (INSTANCE == null) {
			synchronized (VolleyRequestQueue.class) {
				if (INSTANCE == null) {
					INSTANCE = new VolleyRequestQueue();
				}
			}
		}

		return INSTANCE;
	}

	public RequestQueue getRequestQueue() {
		if (requestQueue == null) {
			Cache cache = new DiskBasedCache(cacheDir, 10 * 1024 * 1024);
			Network network = new BasicNetwork(new HurlStack());
			requestQueue = new RequestQueue(cache, network);
			// Don't forget to start the volley request queue
			requestQueue.start();
		}
		return requestQueue;
	}

	/**
	 * Enables https connections
	 */
	@SuppressLint("TrulyRandom")
	public static void handleSSLHandshake() {
		try {
			TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
				public X509Certificate[] getAcceptedIssuers() {
					return new X509Certificate[0];
				}

				@Override
				public void checkClientTrusted(X509Certificate[] certs, String authType) {
				}

				@Override
				public void checkServerTrusted(X509Certificate[] certs, String authType) {
				}
			}};

			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
				@Override
				public boolean verify(String arg0, SSLSession arg1) {
					return true;
				}
			});
		} catch (Exception ignored) {
		}
	}
}
