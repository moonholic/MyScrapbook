package com.hyemoon.myscrapbook.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.hyemoon.myscrapbook.R;
import com.hyemoon.myscrapbook.search.scrapbookTab.ScrapbookFragment;
import com.hyemoon.myscrapbook.search.scrapbookTab.ScrapbookPresenter;
import com.hyemoon.myscrapbook.search.searchTab.ImageSearchFragment;
import com.hyemoon.myscrapbook.search.searchTab.ImageSearchPresenter;

/**
 * Created by MOON on 8/26/17
 *
 *  - MVP 패턴 적용 : 일반적으로 Tab은 한 Activity 안에서 Fragment 두개로 구현하지만,
 *    확장성 및 가독성을 위해서 패키지를 search와 scrapbook로 나눔
 */

public class MyScrapbookActivity extends AppCompatActivity implements View.OnClickListener {

	private final static int FRAGMENT_IMAGE_SEARCH = 1;
	private final static int FRAGMENT_MY_SCRAPBOOK = 2;

	private Button bt_tab1;
	private Button bt_tab2;

	private ImageSearchFragment imageSearchFragment;
	private ImageSearchPresenter imageSearchPresenter;

	private ScrapbookFragment scrapbookFragment;
	private ScrapbookPresenter scrapbookPresenter;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_image_search);

		bt_tab1 = (Button)findViewById(R.id.bt_tab1);
		bt_tab2 = (Button)findViewById(R.id.bt_tab2);

		bt_tab1.setOnClickListener(this);
		bt_tab2.setOnClickListener(this);

		imageSearchFragment = new ImageSearchFragment();
		imageSearchPresenter = new ImageSearchPresenter(imageSearchFragment);

		scrapbookFragment = new ScrapbookFragment();
		scrapbookPresenter = new ScrapbookPresenter(scrapbookFragment);

		// first page
		showFragment(FRAGMENT_IMAGE_SEARCH);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()){
			case R.id.bt_tab1 :
				showFragment(FRAGMENT_IMAGE_SEARCH);
				break;

			case R.id.bt_tab2 :
				showFragment(FRAGMENT_MY_SCRAPBOOK);
				break;
		}
	}

	private void showFragment(int frag_num) {
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		switch (frag_num) {
			case FRAGMENT_IMAGE_SEARCH:
				transaction.replace(R.id.fragment_container, imageSearchFragment);
				transaction.commit();
				break;

			case FRAGMENT_MY_SCRAPBOOK:
				transaction.replace(R.id.fragment_container, scrapbookFragment);
				transaction.commit();
				break;
		}
	}
}
