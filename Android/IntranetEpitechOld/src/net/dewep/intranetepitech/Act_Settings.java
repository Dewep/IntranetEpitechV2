package net.dewep.intranetepitech;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Act_Settings extends MyActivity implements OnClickListener, OnCheckedChangeListener {
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (android.os.Build.VERSION.SDK_INT >= 11)
			getActionBar().setDisplayHomeAsUpEnabled(true);

		Bundle b = getIntent().getExtras();
		int autolog = (b != null) ? b.getInt("autolog", 0) : 0;

		setContentView(R.layout.act_settings);
		SharedPreferences pref = getSharedPreferences("user", MODE_PRIVATE);

		if (autolog == 1 && !pref.getString("login", "").equals(""))
			this.login();
		else if (android.os.Build.VERSION.SDK_INT >= 11)
			getActionBar().setDisplayHomeAsUpEnabled(true);

		EditText login = (EditText) findViewById(R.id.login);
		login.setText(pref.getString("login", ""));
		EditText password = (EditText) findViewById(R.id.password);
		password.setText(pref.getString("password", ""));
		EditText logas = (EditText) findViewById(R.id.logas);
		TextView t2 = (TextView) findViewById(R.id.logas_title);
		if (pref.getBoolean("canLogas", false))
		{
			logas.setText(pref.getString("logas", ""));
			logas.setVisibility(View.VISIBLE);
			t2.setVisibility(View.VISIBLE);
		}
		else
		{
			logas.setVisibility(View.GONE);
			t2.setVisibility(View.GONE);
		}
		TextView info = (TextView) findViewById(R.id.settings_info);
		info.setText("");
		CheckBox notif_messages = (CheckBox) findViewById(R.id.notif_messages);
		notif_messages.setChecked(pref.getBoolean("notif_messages", true));
		notif_messages.setOnCheckedChangeListener(this);
		CheckBox notif_activities = (CheckBox) findViewById(R.id.notif_activities);
		notif_activities.setChecked(pref.getBoolean("notif_activities", true));
		notif_activities.setOnCheckedChangeListener(this);
		Button butt = (Button) findViewById(R.id.save_settings);
		butt.setOnClickListener(this);
		Button butt2 = (Button) findViewById(R.id.go_netsoul);
		butt2.setOnClickListener(this);
		TextView t1 = (TextView) findViewById(R.id.dewep);
		t1.setOnClickListener(this);
	}

	protected void login()
	{
		RecupDonneesNet mnm = new RecupDonneesNet(this, true);
		MyRequest req = new MyRequest();
		req.url = "";
		req.type = Global.T_LOGIN;
		mnm.execute(req);
	}

	protected void OnRestart()
	{
		Global.login(this);
	}

	protected void OnResume()
	{
		Global.login(this);
	}

	@SuppressLint("DefaultLocale")
	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.dewep)
		{
			Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.dewep.net"));
			startActivity(intent);
		}
		else if (v.getId() == R.id.go_netsoul)
		{
			Intent intent = new Intent(this, Act_Netsoul.class);
			startActivity(intent);
		}
		else if (v.getId() == R.id.save_settings)
		{
			TextView info = (TextView) findViewById(R.id.settings_info);
			EditText login = (EditText) findViewById(R.id.login);
			EditText password = (EditText) findViewById(R.id.password);
			EditText logas = (EditText) findViewById(R.id.logas);
			String s_login = login.getText().toString().trim().toLowerCase();
			String s_logas = logas.getText().toString().trim().toLowerCase();
			if (s_login.length() < 1)
			{
				info.setText(getResources().getString(R.string.login_trop_court));
			}
			/*else if (password.getText().length() < 1)
			{
				info.setText(getResources().getString(R.string.mdp_trop_court));
			}*/
			else
			{
				SharedPreferences pref = getSharedPreferences("user", MODE_PRIVATE);
				SharedPreferences.Editor editor = pref.edit();
				editor.putString("login", s_login);
				editor.putString("password", password.getText().toString());
				if (pref.getBoolean("canLogas", false))
					editor.putString("logas", s_logas);
				editor.commit();
				info.setText(getResources().getString(R.string.identifiants_save));
				Stock.getInstance().httpclient = null;
				this.login();
				/*Intent parentActivityIntent = new Intent(this, Act_Main.class);
				startActivity(parentActivityIntent);*/
				Toast.makeText(this, "Paramètres sauvegardés", Toast.LENGTH_SHORT).show();
			}
		}
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		SharedPreferences pref = getSharedPreferences("user", MODE_PRIVATE);
		SharedPreferences.Editor editor = pref.edit();
		Log.d("ID button", String.valueOf(buttonView.getId()));
		Log.d("Value button", String.valueOf(isChecked));
		if (buttonView.getId() == R.id.notif_messages)
			editor.putBoolean("notif_messages", isChecked);
		if (buttonView.getId() == R.id.notif_activities)
			editor.putBoolean("notif_activities", isChecked);
		editor.commit();
		Toast.makeText(this, "Paramètres sauvegardés", Toast.LENGTH_SHORT).show();
	}

	public static Boolean isNotifMessages(Context context)
	{
		SharedPreferences pref = context.getSharedPreferences("user", Context.MODE_PRIVATE);
		return pref.getBoolean("notif_messages", true);
	}

	public static Boolean isNotifActivites(Context context)
	{
		SharedPreferences pref = context.getSharedPreferences("user", Context.MODE_PRIVATE);
		return pref.getBoolean("notif_activities", true);
	}

	public static String getLogin(Context context)
	{
		SharedPreferences pref = context.getSharedPreferences("user", Context.MODE_PRIVATE);
		return pref.getString("login", "");
	}

	public static String getPassword(Context context)
	{
		SharedPreferences pref = context.getSharedPreferences("user", Context.MODE_PRIVATE);
		return pref.getString("password", "");
	}

	public static String getLogas(Context context)
	{
		if (!Act_Settings.canLogas(context))
			return "";
		SharedPreferences pref = context.getSharedPreferences("user", Context.MODE_PRIVATE);
		String logas = pref.getString("logas", "");
		if (!logas.equals(""))
			logas = "/" + logas + "-autolog-42";
		return logas;
	}

	public static Boolean canLogas(Context context)
	{
		SharedPreferences pref = context.getSharedPreferences("user", Context.MODE_PRIVATE);
		return pref.getBoolean("canLogas", false);
	}

	public static String getLoginActu(Context context)
	{
		SharedPreferences pref = context.getSharedPreferences("user", Context.MODE_PRIVATE);
		if (!Act_Settings.canLogas(context))
			return pref.getString("login", "");
		String login = pref.getString("logas", "");
		if (login.equals(""))
			login = pref.getString("login", "");
		//Log.i("LOGGGGGGGGGGGGINNNN", login);
		return login;
	}

	public static void setCanLogas(Context context, boolean value)
	{
		SharedPreferences pref = context.getSharedPreferences("user", MODE_PRIVATE);
		SharedPreferences.Editor editor = pref.edit();
		editor.putBoolean("canLogas", value);
		editor.commit();
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
		case R.id.menu_home:
			Intent parentActivityIntent = new Intent(this, Act_Main.class);
			parentActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(parentActivityIntent);
			return true;
		case R.id.menu_netsoul:
			Intent parentActivityIntent2 = new Intent(this, Act_Netsoul.class);
			parentActivityIntent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(parentActivityIntent2);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (android.os.Build.VERSION.SDK_INT >= 11)
			getMenuInflater().inflate(R.menu.netsoul, menu);
		else
			getMenuInflater().inflate(R.menu.home_netsoul, menu);
		return true;
	}
}
