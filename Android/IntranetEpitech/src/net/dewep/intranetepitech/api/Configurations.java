package net.dewep.intranetepitech.api;

public class Configurations {

	public static String getFullHost() {
		return "https://" + getHost();
	}

	public static String getHost() {
		return "intra.epitech.eu";
	}

	public static String getPathDashboard() {
		return "/";
	}

}
