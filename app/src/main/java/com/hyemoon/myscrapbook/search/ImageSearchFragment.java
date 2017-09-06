package com.hyemoon.myscrapbook.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.hyemoon.myscrapbook.R;
import com.hyemoon.myscrapbook.scrapbook.ScrapbookActivity;
import com.hyemoon.myscrapbook.search.adapter.ImageSearchRecyclerViewAdapter;
import com.hyemoon.myscrapbook.search.model.Image;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by MOON on 8/26/17.
 *
 *  - MVP 패턴 적용 : 일반적으로 Tab은 한 Activity 안에서 Fragment 두개로 구현하지만,
 *    확장성 및 가독성을 위해서 패키지를 search 와 scrapbook 로 나눔
 *
 *  - ButterKnife를 활용하여 annotation 방식으로 뷰 바인딩 구현
 *    (ScrapbookFragment는 DataBinding 이용)
 *
 *  - RecyclerView를 활용하여 GridView UI 구현
 *
 *  - Adapter는 Presenter 역할도 하지만 View 역할도 하므로 이 프로젝트에서는 Fragment에 포함시킴
 */

public class ImageSearchFragment extends Fragment implements ImageSearchContract.View {

	private ImageSearchContract.Presenter presenter;

	private int page = 1;

	@BindView(R.id.tab_search)
	Button searchTab;

	@BindView(R.id.tab_scrapbook)
	Button scrapbookTab;

	@BindView(R.id.btn_search)
	Button searchBtn;

	@BindView(R.id.edittext_keyword)
	EditText editText;

	@BindView(R.id.recyclerview_images)
	RecyclerView recyclerView;

	private ImageSearchRecyclerViewAdapter recyclerViewAdapter;

	@BindView(R.id.loading_indicator)
	ProgressBar progressBar;

	public ImageSearchFragment() {
		// Requires empty public constructor
	}

	public static ImageSearchFragment newInstance() {
		return new ImageSearchFragment();
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

	public void setPresenter(ImageSearchContract.Presenter scrapbookPresenter) {
		presenter = checkNotNull(scrapbookPresenter);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.fragment_image_search, null);
		ButterKnife.bind(this, root);
		initView();
		return root;
	}

	private void initView() {
		recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

		searchBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				search();
			}
		});

		scrapbookTab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				openScrapbook();
			}
		});
	}

	private void search() {
		String keyword = editText.getText().toString();
		if(TextUtils.isEmpty(keyword)) {
			Toast.makeText(getActivity(), "키워드를 입력하세요 ^^", Toast.LENGTH_SHORT).show();
		} else {
			presenter.searchImageByKeyword(editText.getText().toString(), page);
			hideSoftKeyboard();
		}
	}

	private void hideSoftKeyboard() {
		View view = getActivity().getCurrentFocus();
		if (view != null) {
			InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
		}
	}

	@Override
	public void showImageList(List<Image> images) {
		hideLoadingIndicator();
		recyclerView.scrollToPosition(0);

		if(recyclerViewAdapter == null) {
			recyclerViewAdapter = new ImageSearchRecyclerViewAdapter(getContext(), images);
			recyclerViewAdapter.setClickListener(new ImageSearchRecyclerViewAdapter.ItemClickListener() {
				@Override
				public void onItemClick(View view, int position) {
					boolean newValue = presenter.scrapImage(position);
					recyclerViewAdapter.getItem(position).setSelected(newValue);

					CheckBox checkBox = (CheckBox) view.findViewById(R.id.item_checkbox);
					checkBox.setChecked(!checkBox.isChecked());
				}
			});
			recyclerView.setAdapter(recyclerViewAdapter);
		} else {
			recyclerViewAdapter.replaceData(images);
		}
	}

	@Override
	public void openScrapbook() {
		Intent intent = new Intent(getContext(), ScrapbookActivity.class);
		startActivity(intent);
	}

	@Override
	public void showLoadingIndicator() {
		progressBar.setVisibility(View.VISIBLE);
	}

	@Override
	public void hideLoadingIndicator() {
		progressBar.setVisibility(View.GONE);
	}
}
