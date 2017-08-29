package com.hyemoon.myscrapbook.scrapbook;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hyemoon.myscrapbook.R;
import com.hyemoon.myscrapbook.databinding.ActivityScrapbookBinding;
import com.hyemoon.myscrapbook.util.ActivityUtility;

/**
 * Created by MOON on 8/26/17.
 *
 *  - MVP 패턴 적용 : 일반적으로 Tab은 한 Activity 안에서 Fragment 두개로 구현하지만,
 *    확장성 및 가독성을 위해서 패키지를 search 와 scrapbook 로 나눔
 *
 *  - DataBinding 기술을 이용해 ButterKnife 보다 훨씬 간결한 뷰 바인딩 구현
 */

public class ScrapbookActivity extends AppCompatActivity {

	ActivityScrapbookBinding binding;

	private ScrapbookPresenter scrapbookPresenter;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		binding = DataBindingUtil.setContentView(this, R.layout.activity_scrapbook);

		ScrapbookFragment scrapbookFragment =
			(ScrapbookFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

		if (scrapbookFragment == null) {
			scrapbookFragment = ScrapbookFragment.newInstance();
			ActivityUtility.addFragmentToActivity(
				getSupportFragmentManager(), scrapbookFragment, R.id.contentFrame);
		}

		/**
		 *  현재 Activity에서 Presenter를 직접 사용하지는 않지만,
		 *  Activity 에서 Presenter 와 View(Fragment)를 연결시키는 역할을 함
		 */
		scrapbookPresenter = new ScrapbookPresenter(scrapbookFragment);
	}
}
