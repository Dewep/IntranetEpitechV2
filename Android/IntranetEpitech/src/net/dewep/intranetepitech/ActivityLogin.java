package net.dewep.intranetepitech;

import net.dewep.intranetepitech.api.Configurations;
import net.dewep.intranetepitech.api.Intranet;
import net.dewep.intranetepitech.api.RequestIntranet;
import fr.qinder.api.APIResponse;
import fr.qinder.pref.Preferences;
import fr.qinder.tools.JSON;
import fr.qinder.Q;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ActivityLogin extends Activity {

	//ImageView login_logo;
	LinearLayout login_bloc_connect;
	LinearLayout login_bloc_settings;
	//ProgressBar login_loader;
	//TextView login_status;
	EditText login_login;
	EditText login_password;
	TextView login_error;
	Button login_connect;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_login);
		Q.init(this);

		//login_logo = (ImageView) this.findViewById(R.id.login_logo);
		login_bloc_connect = (LinearLayout) this.findViewById(R.id.login_bloc_connect);
		login_bloc_settings = (LinearLayout) this.findViewById(R.id.login_bloc_settings);
		//login_loader = (ProgressBar) this.findViewById(R.id.login_loader);
		//login_status = (TextView) this.findViewById(R.id.login_status);
		login_login = (EditText) this.findViewById(R.id.login_login);
		login_password = (EditText) this.findViewById(R.id.login_password);
		login_error = (TextView) this.findViewById(R.id.login_error);
		login_connect = (Button) this.findViewById(R.id.login_connect);

		login_connect.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Preferences.set("Account", "login", login_login.getText().toString());
				Preferences.set("Account", "password", login_password.getText().toString());
				checkFormatAccount(true);
			}
		});
		checkFormatAccount(false);
	}

	protected void checkFormatAccount(Boolean with_error) {
		String login = Preferences.get("Account", "login", null);
		String password = Preferences.get("Account", "password", null);
		if (login == null || login.length() < 2)
			blocSettings(with_error ? "Merci de renseigner le login." : "");
		else if (password == null || password.length() < 2)
			blocSettings(with_error ? "Merci de renseigner le mot de passe." : "");
		else
			blocConnect();
	}

	protected void blocConnect() {
		login_bloc_settings.setVisibility(View.GONE);
		login_bloc_connect.setVisibility(View.VISIBLE);
		Intranet.request(new RequestIntranet(Configurations.getPathLogin()) {
			@Override
			public void preExecute(APIResponse response) {
				super.preExecute(response);
				addPost("login", Preferences.get("Account", "login", ""));
				addPost("password", Preferences.get("Account", "password", ""));
			}
			@Override
			public void onResult(APIResponse response) {
				if (response.code == 403)
					blocSettings(JSON.parse(response.data, "message", "Mauvais identifiants."));
				else if (response.code == 200)
					blocSettings("Connexion réussie!");
				else
					blocSettings("Impossible d'accéder à l'Intranet.");
				Log.d("Result", response.data);
			}
		}).execute();
	}

	protected void blocSettings(String error) {
		login_bloc_connect.setVisibility(View.GONE);
		login_bloc_settings.setVisibility(View.VISIBLE);
		login_error.setText(error);
		login_login.setText(Preferences.get("Account", "login", ""));
		login_password.setText("");
	}

}
