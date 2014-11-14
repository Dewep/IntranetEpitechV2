package net.dewep.intranetepitech;

import com.google.android.gms.analytics.GoogleAnalytics;

import android.app.Activity;
import android.os.Bundle;

public class MyActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Get a Tracker (should auto-report)
		((MyApplication) this.getApplication()).getTracker(MyApplication.TrackerName.APP_TRACKER);
	}

	@Override
	protected void onStart() {
		super.onStart();
		//Get an Analytics tracker to report app starts & uncaught exceptions etc.
		GoogleAnalytics.getInstance(this).reportActivityStart(this);
	}

	@Override
	protected void onStop() {
		super.onStop();
		//Stop the analytics tracking
		GoogleAnalytics.getInstance(this).reportActivityStop(this);
	}

}
