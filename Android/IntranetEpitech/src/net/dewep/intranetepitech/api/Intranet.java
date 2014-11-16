package net.dewep.intranetepitech.api;

import fr.qinder.api.APICookie;
import fr.qinder.api.APIRequest;
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

	private APIRequestsListener createListener() {
		return new APIRequestsListener(null);
	}

	public static APIRequestsListener request(APIRequest request) {
		return Intranet.getInstance().createListener().request(request);
	}
}
