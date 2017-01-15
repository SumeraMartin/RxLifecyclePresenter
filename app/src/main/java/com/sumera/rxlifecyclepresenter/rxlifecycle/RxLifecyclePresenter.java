package com.sumera.rxlifecyclepresenter.rxlifecycle;


import android.support.annotation.NonNull;

import com.trello.rxlifecycle.LifecycleTransformer;
import com.trello.rxlifecycle.OutsideLifecycleException;
import com.trello.rxlifecycle.RxLifecycle;

import rx.Observable;
import rx.functions.Func1;

public final class RxLifecyclePresenter {

	private RxLifecyclePresenter() {
		throw new AssertionError("No instances");
	}

	@NonNull
	public static <T> LifecycleTransformer<T> bindPresenter(@NonNull final Observable<Integer> lifecycle) {
		return RxLifecycle.bind(lifecycle, PRESENTER_LIFECYCLE);
	}

	private static final Func1<Integer, Integer> PRESENTER_LIFECYCLE = new Func1<Integer, Integer>() {
		@Override public Integer call(Integer lastEvent) {
			switch (lastEvent) {
				case PresenterEvent.ATTACH:
					return PresenterEvent.DETACH;
				case PresenterEvent.DETACH:
					throw new OutsideLifecycleException("Cannot bind to Presenter lifecycle when outside of it.");
				default:
					throw new UnsupportedOperationException("Binding to " + lastEvent + " not yet implemented");
			}
		}
	};

}

