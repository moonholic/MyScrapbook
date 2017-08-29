package com.hyemoon.myscrapbook.search.model;

import android.support.annotation.NonNull;

/**
 * Created by MOON on 8/26/17.
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
