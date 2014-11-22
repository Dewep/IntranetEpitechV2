package net.dewep.intranetepitech.api.request;

import net.dewep.intranetepitech.api.Configurations;
import net.dewep.intranetepitech.api.IntranetAPI;
import net.dewep.intranetepitech.api.RequestIntranet;

public abstract class LoginAPI extends RequestIntranet {
	public LoginAPI(String login, String password) {
		super(Configurations.getPathLogin());
		addPost("login", login);
		addPost("password", password);
		IntranetAPI.request("LoginAPI", this).execute();
	}
}
