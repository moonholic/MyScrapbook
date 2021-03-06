package com.hyemoon.myscrapbook.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hyemoon.myscrapbook.R;
import com.hyemoon.myscrapbook.util.ActivityUtility;

/**
 * Created by MOON on 8/26/17
 *
 *  - MVP 패턴 적용 : 일반적으로 Tab은 한 Activity 안에서 Fragment 두개로 구현하지만,
 *    확장성 및 가독성을 위해서 패키지를 search와 scrapbook로 나눔
 */

public class ImageSearchActivity extends AppCompatActivity {

	private ImageSearchPresenter imageSearchPresenter;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_image_search);

		ImageSearchFragment imageSearchFragment =
			(ImageSearchFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

		if (imageSearchFragment == null) {
			imageSearchFragment = ImageSearchFragment.newInstance();
			ActivityUtility.addFragmentToActivity(
				getSupportFragmentManager(), imageSearchFragment, R.id.contentFrame);
		}

		/**
		 *  현재 Activity에서 Presenter를 직접 사용하지는 않지만,
		 *  Activity 에서 Presenter 와 View(Fragment)를 연결시키는 역할을 함
		 */
		imageSearchPresenter = new ImageSearchPresenter(imageSearchFragment);
	}
}
