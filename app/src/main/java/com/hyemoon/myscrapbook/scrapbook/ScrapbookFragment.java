package com.hyemoon.myscrapbook.scrapbook;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hyemoon.myscrapbook.R;
import com.hyemoon.myscrapbook.databinding.ContentScrapbookBinding;
import com.hyemoon.myscrapbook.search.model.Image;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by MOON on 8/26/17.
 *
 *  - MVP 패턴 적용 : 일반적으로 Tab은 한 Activity 안에서 Fragment 두개로 구현하지만,
 *    확장성 및 가독성을 위해서 패키지를 search와 scrapbook로 나눔
 *
 *  - DataBinding 기술을 이용해 ButterKnife 보다 훨씬 간결한 뷰 바인딩 구현
 *
 *  - GridView & ViewHolder 활용하여 내 보관함 구현
 *
 *  - Adapter는 Presenter 역할도 하지만 View 역할도 하므로 이 프로젝트에서는 Fragment에 포함시킴
 */

public class ScrapbookFragment extends Fragment implements ScrapbookContract.View {

	private ScrapbookContract.Presenter presenter;

	ContentScrapbookBinding binding;

	private ScrapbookAdapter gridViewAdapter;

	public ScrapbookFragment() {
		// Requires empty public constructor
	}

	public static ScrapbookFragment newInstance() {
		return new ScrapbookFragment();
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onResume() {
		super.onResume();
		presenter.start();
	}

	public void setPresenter(ScrapbookContract.Presenter presenter) {
		this.presenter = checkNotNull(presenter);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View root = inflater.inflate(R.layout.fragment_scrapbook, null);
		return root;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		binding = ContentScrapbookBinding.bind(getView());
		initView();
	}

	private void initView() {
		binding.tabSearch.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				getActivity().finish();
			}
		});
	}

	@Override
	public void showScrappedImages() {
		List<Image> scrappedImages = presenter.getScrappedImages();

		if(scrappedImages != null && scrappedImages.size() > 0) {
			gridViewAdapter = new ScrapbookAdapter(getContext(), presenter.getScrappedImages());
			binding.gridviewScrapbook.setAdapter(gridViewAdapter);

			StringBuilder builder = new StringBuilder();
			builder.append("내 보관함 (").append(presenter.getScrappedImages().size()).append(")");
			binding.scrapbookTitle.setText(builder.toString());

			binding.noResultLayout.setVisibility(View.GONE);
		} else {
			binding.noResultLayout.setVisibility(View.VISIBLE);
		}
	}
}
