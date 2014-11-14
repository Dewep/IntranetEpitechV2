package net.dewep.intranetepitech;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import fr.qinder.api.APIGetter;
import fr.qinder.api.APIListener;
import fr.qinder.api.APIRequest;
import fr.qinder.api.APIResponse;

public class ActivityMain extends Activity implements APIListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_main);

		APIGetter getter = new APIGetter(this, true);
		APIRequest request = new APIRequest("GetHome", Configurations.getUrlDashboard());
		getter.execute(request);
	}

	@Override
	public void onResult(APIResponse response) {
		Log.d("result", response.data);
	}

}
