package net.dewep.intranetepitech.api;

import android.app.Activity;
import fr.qinder.api.APICookie;
import fr.qinder.api.APIRequestsListener;

public class Intranet {

	private static volatile Intranet instance = null;

	private Intranet() {
		APICookie.add("https://intra.epitech.eu", "language", "fr");
	}

	public final static Intranet getInstance() {
		if (Intranet.instance == null) {
			synchronized (Intranet.class) {
				if (Intranet.instance == null) {
					Intranet.instance = new Intranet();
				}
			}
		}
		return Intranet.instance;
	}

	private APIRequestsListener createListener(Activity activity) {
		return new APIRequestsListener(activity);
	}

	public static APIRequestsListener prepare(Activity activity) {
		return Intranet.getInstance().createListener(activity);
	}

	public static APIRequestsListener prepare() {
		return Intranet.prepare(null);
	}
}
