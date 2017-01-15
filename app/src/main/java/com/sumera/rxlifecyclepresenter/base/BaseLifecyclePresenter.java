package com.sumera.rxlifecyclepresenter.base;


import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;

import com.sumera.rxlifecyclepresenter.rxlifecycle.PresenterEvent;
import com.sumera.rxlifecyclepresenter.rxlifecycle.RxLifecyclePresenter;
import com.trello.rxlifecycle.LifecycleProvider;
import com.trello.rxlifecycle.LifecycleTransformer;
import com.trello.rxlifecycle.RxLifecycle;

import rx.Observable;
import rx.subjects.BehaviorSubject;

public class BaseLifecyclePresenter<V extends MvpView> extends BasePresenter<V> implements LifecycleProvider<Integer> {

	private final BehaviorSubject<Integer> lifecycleSubject = BehaviorSubject.create();

	@Override
	@NonNull
	public final Observable<Integer> lifecycle() {
		return lifecycleSubject.asObservable();
	}

	@Override
	@NonNull
	public final <T> LifecycleTransformer<T> bindUntilEvent(@NonNull Integer event) {
		return RxLifecycle.bindUntilEvent(lifecycleSubject, event);
	}

	@Override
	@NonNull
	public final <T> LifecycleTransformer<T> bindToLifecycle() {
		return RxLifecyclePresenter.bindPresenter(lifecycleSubject);
	}

	@Override
	@CallSuper
	public void attachView(V view) {
		super.attachView(view);
		lifecycleSubject.onNext(PresenterEvent.ATTACH);
	}

	@Override
	@CallSuper
	public void detachView() {
		super.detachView();
		lifecycleSubject.onNext(PresenterEvent.DETACH);
	}

}
