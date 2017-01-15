package com.sumera.rxlifecyclepresenter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements TimerView {

	@BindView(R.id.timer_value) TextView timerValueTextView;

	private TimerPresenter presenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);

		presenter = new TimerPresenter();
		presenter.attachView(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		presenter.detachView();
	}

	@Override
	public void updateTimerValue(int value) {
		timerValueTextView.setText("" + value);
	}

}
