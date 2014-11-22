package net.dewep.intranetepitech;

import net.dewep.intranetepitech.landing.LandingActivity;
import fr.qinder.Q;
import fr.qinder.pref.Preferences;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class StartActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Q.init(this);
		Preferences.set("Account", "login", null);
		Preferences.set("Account", "password", null);
		Intent intent = new Intent(StartActivity.this, LandingActivity.class);
		startActivity(intent);
		finish();
	}
}
