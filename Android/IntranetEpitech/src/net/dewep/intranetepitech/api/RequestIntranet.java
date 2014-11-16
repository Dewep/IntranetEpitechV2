package net.dewep.intranetepitech.api;

import org.json.JSONObject;

import fr.qinder.api.APIRequest;
import fr.qinder.tools.JSON;

public abstract class RequestIntranet extends APIRequest {
	private Boolean with_json = true;
	private JSONObject json = null;

	public RequestIntranet(String _url, Boolean _with_json) {
		super(Configurations.getHost() + _url);
		with_json = _with_json;
	}

	public RequestIntranet(String _url) {
		super(Configurations.getFullHost() + _url);
	}

	public JSONObject getJSON() {
		return json;
	}

	public void preExecute() {
		super.preExecute();
		if (with_json)
			this.addGet("format", "json");
	}

	public void postExecute() {
		super.preExecute();
		if (response.data != null && response.data.startsWith("//") && response.data.indexOf('\n') != -1)
			response.data = response.data.substring(response.data.indexOf('\n') + 1);
		if (with_json)
			json = JSON.getObject(response.data);
	}
}
