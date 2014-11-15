package net.dewep.intranetepitech;

import net.dewep.intranetepitech.api.Configurations;
import net.dewep.intranetepitech.api.Intranet;
import net.dewep.intranetepitech.api.RequestIntranet;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import fr.qinder.api.APIResponse;

public class ActivityMain extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_main);

		Intranet.prepare(this).request(new RequestIntranet(Configurations.getPathDashboard()) {
			@Override
			public void onResult(APIResponse response) {
				Log.d("URL", this.url);
				Log.d("Result", response.data);
			}
		}).execute();
	}

}
