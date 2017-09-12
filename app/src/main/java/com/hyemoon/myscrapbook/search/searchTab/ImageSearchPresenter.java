package com.hyemoon.myscrapbook.search.searchTab;

import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.hyemoon.myscrapbook.Constants;
import com.hyemoon.myscrapbook.data.ImageRepository;
import com.hyemoon.myscrapbook.network.HttpRequest;
import com.hyemoon.myscrapbook.network.VolleyRequestQueue;
import com.hyemoon.myscrapbook.search.dto.ImageSearchVO;
import com.hyemoon.myscrapbook.search.model.Image;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by MOON on 8/26/17
 */

public class ImageSearchPresenter implements ImageSearchContract.Presenter, Response.Listener<JSONObject>,
	Response.ErrorListener {

	private final ImageSearchContract.View view;

	public static final String TAG = ImageSearchPresenter.class.getSimpleName();
	private RequestQueue requestQueue;

	public ImageSearchPresenter(ImageSearchContract.View view) {
		this.view = checkNotNull(view, "tasksView cannot be null!");
		this.view.setPresenter(this);
	}

	@Override
	public void start() {
		requestQueue = VolleyRequestQueue
			.getInstance()
			.getRequestQueue();

		List<Image> imageList = ImageRepository.getInstance().getImageList();
		if(imageList != null && !imageList.isEmpty()) {
			view.showImageList(imageList);
		}
	}

	@Override
	public void searchImageByKeyword(String keyword, int page) {
		view.showLoadingIndicator();

		String requestUrl = makeRequestUrl(keyword, page);
		if(!TextUtils.isEmpty(requestUrl)) {
			HttpRequest httpRequest = new HttpRequest(Request.Method.GET, makeRequestUrl(keyword, page), new JSONObject(), this, this);
			httpRequest.setTag(TAG);
			requestQueue.add(httpRequest);
		}
	}

	private String makeRequestUrl(String keyword, int page) {
		String result;
		try {
			result = Constants.IMAGE_SEARCH_URL + "?query=" + URLEncoder.encode(keyword, "UTF-8") + "&page=" + page;
			return result;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void onErrorResponse(VolleyError error) {
		Log.d(TAG, "Volley Error ::: " + error.getMessage());
	}

	@Override
	public void onResponse(JSONObject response) {
		Log.d(TAG, "Volley Success!! response ::: " + response.toString());

		saveImageList(response);

		view.showImageList(ImageRepository.getInstance().getImageList());
		view.hideLoadingIndicator();
	}

	private void saveImageList(JSONObject response) {
		Gson gson = new Gson();
		ImageSearchVO vo = gson.fromJson(response.toString(), ImageSearchVO.class);

		// ask Model to clean repo
		ImageRepository.getInstance().removeAll();

		// ask Model to save data to repo
		ImageRepository.getInstance().addImageList(vo.getDocuments());
	}

	@Override
	public boolean scrapImage(int position) {
		return !ImageRepository.getInstance().getImage(position).isSelected();
	}
}
