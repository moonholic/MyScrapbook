package com.hyemoon.myscrapbook.search.model;

import android.support.annotation.NonNull;

/**
 * Created by MOON on 8/26/17
 *
 * 현재 앱에서 모든 항목이 사용되지는 않지만,
 * 추후 확장성을 위해서 API Response의 정보를 모두 추가함
 */

public class Image {

	private String collection;
	private String thumbnail_url;
	@NonNull private String image_url;
	private String width;
	private String height;
	private String display_sitename;
	private String doc_url;
	private String datetime;

	private boolean selected = false;	// default

	@NonNull
	public String getUrl() {
		return image_url;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean value) {
		this.selected = value;
	}
}
