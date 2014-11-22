package net.dewep.intranetepitech;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class ActivityMain extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_main);

		findViewById(R.id.main_logout).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				EpitechAccount.logout();
				Intent intent = new Intent(ActivityMain.this, StartActivity.class);
				startActivity(intent);
				finish();
			}
		});

		Toast.makeText(this, "Title: " + EpitechAccount.getTitle(), Toast.LENGTH_SHORT).show();
	}

}
