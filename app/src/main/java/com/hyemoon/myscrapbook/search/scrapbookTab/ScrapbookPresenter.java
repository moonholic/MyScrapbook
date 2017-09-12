package com.hyemoon.myscrapbook.search.scrapbookTab;

import com.hyemoon.myscrapbook.data.ImageRepository;
import com.hyemoon.myscrapbook.search.model.Image;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by MOON on 8/26/17
 */

public class ScrapbookPresenter implements ScrapbookContract.Presenter {

	private final ScrapbookContract.View view;
	private List<Image> scrappedImages;

	public ScrapbookPresenter(ScrapbookContract.View view) {
		this.view = checkNotNull(view, "tasksView cannot be null!");
		this.view.setPresenter(this);
	}
	@Override
	public void start() {
		view.showScrappedImages();
	}

	@Override
	public List<Image> getScrappedImages() {
		scrappedImages = ImageRepository.getInstance().getSelectedImageList();
		return scrappedImages;
	}
}
