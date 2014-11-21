package net.dewep.intranetepitech;

import fr.qinder.Q;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class StartActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Q.init(this);
		Intent intent = new Intent(StartActivity.this, LandingActivity.class);
		startActivity(intent);
		finish();
	}
}
