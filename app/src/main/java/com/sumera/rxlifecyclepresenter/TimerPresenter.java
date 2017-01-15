package com.sumera.rxlifecyclepresenter;


import android.util.Log;

import com.sumera.rxlifecyclepresenter.base.BaseLifecyclePresenter;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class TimerPresenter extends BaseLifecyclePresenter<TimerView> {

	@Override
	public void attachView(TimerView view) {
		super.attachView(view);
		startTimer();
	}

	private void startTimer() {
		Observable.interval(1, TimeUnit.SECONDS)
				  .subscribeOn(Schedulers.newThread())
				  .observeOn(AndroidSchedulers.mainThread())
				  .compose(this.<Long>bindToLifecycle())
				  .subscribe(timerSubscriber);
	}

	private Subscriber<Long> timerSubscriber = new Subscriber<Long>() {
		@Override
		public void onNext(Long timerValue) {
			if (isViewAttached()) {
				getView().updateTimerValue(timerValue.intValue());
			}
			Log.d("SUMERA", "ON NEXT " + timerValue);
		}

		@Override
		public void onCompleted() {
			Log.d("SUMERA", "COMPLETE");
		}

		@Override
		public void onError(Throwable e) {
			Log.e("SUMERA", e.toString());
		}
	};

}
