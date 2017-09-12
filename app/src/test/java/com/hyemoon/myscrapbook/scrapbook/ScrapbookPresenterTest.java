package com.hyemoon.myscrapbook.scrapbook;

import com.hyemoon.myscrapbook.data.ImageRepository;
import com.hyemoon.myscrapbook.scrapbook.model.Image;
import com.hyemoon.myscrapbook.scrapbook.scrapbookTab.ScrapbookFragment;
import com.hyemoon.myscrapbook.scrapbook.scrapbookTab.ScrapbookPresenter;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by MOON on 8/29/17
 */
public class ScrapbookPresenterTest {

	ScrapbookPresenter presenter;

	@Before
	public void setUp() throws Exception {
		presenter = new ScrapbookPresenter(ScrapbookFragment.newInstance());

		Image i1 = new Image();
		i1.setSelected(true);

		Image i2 = new Image();
		i2.setSelected(false);

		Image i3 = new Image();
		i3.setSelected(true);

		Image i4 = new Image();
		i4.setSelected(false);

		List<Image> list = new ArrayList<>();
		list.add(i1);
		list.add(i2);
		list.add(i3);
		list.add(i4);

		ImageRepository.getInstance().addImageList(list);
	}

	@Test
	public void getScrappedImagesTest() throws Exception {
		presenter.start();
		assertThat(presenter.getScrappedImages().size(), is(2));
	}
}