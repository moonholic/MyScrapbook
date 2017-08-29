package com.hyemoon.myscrapbook.search;

import com.hyemoon.myscrapbook.data.ImageRepository;
import com.hyemoon.myscrapbook.search.model.Image;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by MOON on 8/29/17
 */
public class ImageSearchPresenterTest {

	ImageSearchPresenter presenter;

	@Before
	public void setUp() throws Exception {
		presenter = new ImageSearchPresenter(ImageSearchFragment.newInstance());
		ImageRepository.getInstance().addImage(new Image());
	}

	@Test
	public void scrapImageTest() throws Exception {
		assertThat(presenter.scrapImage(1), is(true));
	}
}