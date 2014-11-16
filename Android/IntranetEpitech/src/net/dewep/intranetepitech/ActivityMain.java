package net.dewep.intranetepitech;

import fr.qinder.pref.Preferences;
import net.dewep.intranetepitech.api.Configurations;
import net.dewep.intranetepitech.api.Intranet;
import net.dewep.intranetepitech.api.RequestIntranet;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class ActivityMain extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_main);

		Intranet.request(new RequestIntranet(Configurations.getPathDashboard()) {
			@Override
			public void onResult() {
				Log.d("URL", this.url);
				Log.d("Cache", String.valueOf(this.response.isCache));
				Log.d("Result3", String.valueOf(response.code));
			}
		}).request(new RequestIntranet(Configurations.getPathDashboard()) {
			@Override
			public void onResult() {
				Log.d("URL2", this.url);
				Log.d("Cache", String.valueOf(this.response.isCache));
				Log.d("Result3", String.valueOf(response.code));
			}
		}).request(new RequestIntranet(Configurations.getPathDashboard()) {
			@Override
			public void onResult() {
				Log.d("URL3", this.url);
				Log.d("Cache", String.valueOf(this.response.isCache));
				Log.d("Result3", String.valueOf(response.code));
			}
		}).execute();

		Log.d("AccountTitle", Preferences.get("Account", "title", ""));
	}

}
