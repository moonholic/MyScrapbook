package com.hyemoon.myscrapbook;

/**
 * Created by MOON on 8/26/17
 */

public interface BaseView<T extends BasePresenter> {
	void setPresenter(T presenter);
}
