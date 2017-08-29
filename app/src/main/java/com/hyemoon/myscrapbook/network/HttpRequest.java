package com.hyemoon.myscrapbook.network;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.JsonObjectRequest;
import com.hyemoon.myscrapbook.Constants;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by MOON on 8/27/17
 */

public class HttpRequest extends JsonObjectRequest {

	public HttpRequest(int method, String url, JSONObject jsonRequest,
		Response.Listener<JSONObject> listener,
		Response.ErrorListener errorListener) {
		super(method, url, jsonRequest, listener, errorListener);
	}

	@Override
	public Map<String, String> getHeaders() throws AuthFailureError {
		HashMap<String, String> headers = new HashMap<>();
		headers.put("Authorization", "KakaoAK " + Constants.KAKAO_OPEN_API_KEY);
		headers.put("Content-Type", "application/json; charset=utf-8");
		return headers;
	}

	@Override
	public RetryPolicy getRetryPolicy() {
		// here you can write a custom retry policy
		return super.getRetryPolicy();
	}
}