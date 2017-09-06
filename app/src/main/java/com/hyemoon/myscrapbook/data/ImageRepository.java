package com.hyemoon.myscrapbook.data;

import com.hyemoon.myscrapbook.search.model.Image;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MOON on 8/26/17
 */

public class ImageRepository {

	private static ImageRepository INSTANCE = null;

	private static List<Image> images;

	// Prevent direct instantiation.
	private ImageRepository() {
		images = new ArrayList<>();
	}

	/**
	 * Returns the single instance of this class, creating it if necessary.
	 *
	 * @return the {@link ImageRepository} instance
	 */
	public static ImageRepository getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ImageRepository();
		}
		return INSTANCE;
	}

	public void addImage(Image image) {
		if (images == null) {
			images = new ArrayList<>();
		}
		images.add(image);
	}

	public void addImageList(List<Image> imageList) {
		if (images == null) {
			images = new ArrayList<>();
		}

		images.addAll(imageList);
	}

	public int getImageCount() {
		return images.size();
	}

	public List<Image> getImageList() {
		return images;
	}

	public Image getImage(int position) {
		return images.get(position);
	}

	public List<Image> getSelectedImageList() {
		List<Image> selectedImages = new ArrayList<>();

		if(images != null && images.size() > 0) {
			for(Image image : images) {
				if(image.isSelected()) {
					selectedImages.add(image);
				}
			}
		}

		return selectedImages;
	}

	public void removeAll() {
		if(images != null) {
			images.clear();
		}
	}
}
