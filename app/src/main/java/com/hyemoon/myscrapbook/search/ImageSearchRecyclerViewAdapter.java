package com.hyemoon.myscrapbook.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hyemoon.myscrapbook.R;
import com.hyemoon.myscrapbook.search.model.Image;

import java.util.List;

/**
 * Created by MOON on 8/27/17
 */

public class ImageSearchRecyclerViewAdapter extends RecyclerView.Adapter<ImageSearchRecyclerViewAdapter.ViewHolder> {

	private Context context;
	private List<Image> imageList;
	private ItemClickListener itemClickListener;

	public ImageSearchRecyclerViewAdapter(Context context, List<Image> images) {
		this.context = context;
		this.imageList = images;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View view = inflater.inflate(R.layout.item_image_list, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		if (position < imageList.size()) {
			Glide.with(context)
				.load(imageList.get(position).getUrl())
				.placeholder(R.drawable.img_loading)
				.into(holder.imageView);
		}

		holder.checkbox.setChecked(imageList.get(position).isSelected());
	}

	@Override
	public int getItemCount() {
		if(imageList == null) {
			return 0;
		}

		return imageList.size();
	}

	public Image getItem(int position) {
		if(imageList == null) {
			return null;
		}

		return imageList.get(position);
	}

	public void replaceData(List<Image> images) {
		imageList = images;
		notifyDataSetChanged();
	}

	public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
		public ImageView imageView;
		public CheckBox checkbox;

		public ViewHolder(View itemView) {
			super(itemView);
			imageView = (ImageView) itemView.findViewById(R.id.item_image);
			checkbox = (CheckBox) itemView.findViewById(R.id.item_checkbox);
			itemView.setOnClickListener(this);
		}

		@Override
		public void onClick(View view) {
			if(itemClickListener != null) {
				itemClickListener.onItemClick(view, getAdapterPosition());
			}
		}
	}

	public void setClickListener(ItemClickListener itemClickListener) {
		this.itemClickListener = itemClickListener;
	}

	public interface ItemClickListener {
		void onItemClick(View view, int position);
	}
}
