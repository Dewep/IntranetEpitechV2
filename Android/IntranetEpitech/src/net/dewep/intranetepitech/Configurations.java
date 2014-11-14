package net.dewep.intranetepitech;

import android.content.Context;

public class Configurations {

	private static Configurations Singleton = null;
	Context _context = null;

	public Configurations(Context context) {
		_context = context;
	}

	public static Configurations getInstance(Context context) {
		if (Singleton == null)
			Singleton = new Configurations(context);
		return Singleton;
	}

	public static Configurations getInstance() {
		if (Singleton == null)
			Singleton = new Configurations(null);
		return Singleton;
	}

	public static String getSchema() {
		return "https";
	}

	public static int getPort() {
		return 443;
	}

	private String getHost() {
		return "intra.epitech.eu";
	}

	public static String getBaseURL() {
		return getSchema() + "://" + getInstance().getHost() + ":" + String.valueOf(getPort());
	}

	public static String getUrlDashboard() {
		return getBaseURL() + "/?format=json";
	}

}
