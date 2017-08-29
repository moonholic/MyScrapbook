package com.hyemoon.myscrapbook.data;

import com.hyemoon.myscrapbook.search.model.Image;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by MOON on 8/29/17
 */
public class ImageRepositoryTest {
	@Before
	public void setUp() throws Exception {
		ImageRepository.getInstance().addImage(new Image());
	}

	@Test
	public void addImageTest() throws Exception {
		ImageRepository.getInstance().addImage(new Image());
		assertThat(ImageRepository.getInstance().getImageCount(), is(2));
	}

	@Test
	public void addImageListTest() throws Exception {
		List<Image> images = new ArrayList<>();
		images.add(new Image());
		images.add(new Image());
		images.add(new Image());

		ImageRepository.getInstance().addImageList(images);

		assertThat(ImageRepository.getInstance().getImageCount(), is(4));
	}

	@Test
	public void removeAll() throws Exception {
		ImageRepository.getInstance().removeAll();
		assertThat(ImageRepository.getInstance().getImageCount(), is(0));
	}

}