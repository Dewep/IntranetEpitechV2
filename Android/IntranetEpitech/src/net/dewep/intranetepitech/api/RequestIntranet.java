package net.dewep.intranetepitech.api;

import fr.qinder.api.APIRequest;
import fr.qinder.api.APIResponse;

public abstract class RequestIntranet extends APIRequest {
	private Boolean with_json = true;

	public RequestIntranet(String _url, Boolean _with_json) {
		super(Configurations.getHost() + _url);
		with_json = _with_json;
	}

	public RequestIntranet(String _url) {
		super(Configurations.getFullHost() + _url);
	}

	public void preExecute(APIResponse response) {
		super.preExecute(response);
		if (with_json)
			this.addGet("format", "json");
	}

	public void postExecute(APIResponse response) {
		super.preExecute(response);
		if (response.data != null && response.data.startsWith("//") && response.data.indexOf('\n') != -1)
		{
			response.data = response.data.substring(response.data.indexOf('\n') + 1);
		}
	}
}
