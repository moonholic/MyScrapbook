package com.hyemoon.myscrapbook.scrapbook.searchTab;

import com.hyemoon.myscrapbook.BasePresenter;
import com.hyemoon.myscrapbook.BaseView;
import com.hyemoon.myscrapbook.scrapbook.model.Image;

import java.util.List;

/**
 * Created by MOON on 8/26/17
 */

public interface ImageSearchContract {

	interface View extends BaseView<Presenter> {

		void showImageList(List<Image> images);

		void showLoadingIndicator();

		void hideLoadingIndicator();
	}

	interface Presenter extends BasePresenter {

		void searchImageByKeyword(String keyword, int page);

		boolean scrapImage(int position);
	}
}
