package com.hyemoon.myscrapbook.scrapbook;

import com.hyemoon.myscrapbook.BasePresenter;
import com.hyemoon.myscrapbook.BaseView;
import com.hyemoon.myscrapbook.search.model.Image;

import java.util.List;

/**
 * Created by MOON on 8/26/17
 */

public interface ScrapbookContract {

	interface View extends BaseView<Presenter> {

		void showScrappedImages();
	}

	interface Presenter extends BasePresenter {

		List<Image> getScrappedImages();
	}
}
