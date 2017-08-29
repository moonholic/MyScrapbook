package com.hyemoon.myscrapbook.scrapbook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hyemoon.myscrapbook.R;
import com.hyemoon.myscrapbook.search.model.Image;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MOON on 8/27/17
 */

public class ScrapbookAdapter extends BaseAdapter {

	private Context context;
	private List<Image> imageList;

	public ScrapbookAdapter(Context context, List<Image> imageList) {
		this.context = context;
		if (imageList == null) {
			imageList = new ArrayList<>();
		}

		setImageList(imageList);
	}

	private void setImageList(List<Image> images) {
		this.imageList = images;
	}

	@Override
	public int getCount() {
		return imageList.size();
	}

	@Override
	public Image getItem(int i) {
		return imageList.get(i);
	}

	@Override
	public long getItemId(int i) {
		return i;
	}

	public class ViewHolder {
		public ImageView imageView;
		public CheckBox checkbox;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View rootView = convertView;

		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(parent.getContext());
			rootView = inflater.inflate(R.layout.item_image_list, parent, false);
			ViewHolder viewHolder = new ViewHolder();
			viewHolder.imageView = (ImageView) rootView.findViewById(R.id.item_image);
			viewHolder.checkbox = (CheckBox) rootView.findViewById(R.id.item_checkbox);
			rootView.setTag(viewHolder);
		}

		ViewHolder holder = (ViewHolder) rootView.getTag();

		if (position < imageList.size()) {
			Glide.with(context)
				.load(imageList.get(position).getUrl())
				.placeholder(R.drawable.img_loading)
				.into(holder.imageView);
		}

		holder.checkbox.setChecked(imageList.get(position).isSelected());

		return rootView;
	}
}