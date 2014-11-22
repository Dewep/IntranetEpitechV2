package net.dewep.intranetepitech.api;

import java.util.HashMap;

import fr.qinder.api.APICookie;
import fr.qinder.api.APIRequest;
import fr.qinder.api.APIRequestsListener;

public class IntranetAPI {

	private static volatile IntranetAPI instance = null;

	private HashMap<String, APIRequestsListener> api;

	private IntranetAPI() {
		api = new HashMap<String, APIRequestsListener>();
		APICookie.add(Configurations.getFullHost(), "language", "fr");
	}

	public final static IntranetAPI getInstance() {
		if (IntranetAPI.instance == null) {
			synchronized (IntranetAPI.class) {
				if (IntranetAPI.instance == null) {
					IntranetAPI.instance = new IntranetAPI();
				}
			}
		}
		return IntranetAPI.instance;
	}

	private APIRequestsListener createListener() {
		return new APIRequestsListener(null);
	}

	public static APIRequestsListener request(APIRequest request) {
		return IntranetAPI.getInstance().createListener().request(request);
	}

	private APIRequestsListener createListener(String id) {
		if (api.get(id) != null) {
			api.get(id).cancelTask();
			api.remove(id);
		}
		api.put(id, new APIRequestsListener(null));
		return api.get(id);
	}

	public static APIRequestsListener request(String id, APIRequest request) {
		return IntranetAPI.getInstance().createListener(id).request(request);
	}
}
