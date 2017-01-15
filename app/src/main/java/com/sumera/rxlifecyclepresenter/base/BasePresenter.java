package com.sumera.rxlifecyclepresenter.base;


import android.support.annotation.CallSuper;

public class BasePresenter<T extends MvpView> {

	private T mvpView;

	@CallSuper
	public void attachView(T mvpView) {
		this.mvpView = mvpView;
	}

	@CallSuper
	public void detachView() {
		mvpView = null;
	}

	public final boolean isViewAttached() {
		return mvpView != null;
	}

	public final T getView() {
		return mvpView;
	}
}
