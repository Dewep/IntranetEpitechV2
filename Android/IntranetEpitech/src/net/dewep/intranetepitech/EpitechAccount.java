package net.dewep.intranetepitech;

import fr.qinder.pref.Preferences;

public class EpitechAccount {
	public static void set(String key, String value) { Preferences.set("Account", key, value); }
	public static String get(String key, String default_value) { return Preferences.get("Account", key, default_value); }
	public static void set(String key, int value) { Preferences.set("Account", key, value); }
	public static int get(String key, int default_value) { return Preferences.get("Account", key, default_value); }

	public static void setLogin(String value) { set("login", value); }
	public static String getLogin(String default_value) { return get("login", default_value); }
	public static String getLogin() { return getLogin(""); }

	public static void setPassword(String value) { set("password", value); }
	public static String getPassword(String default_value) { return get("password", default_value); }
	public static String getPassword() { return getPassword(""); }

	public static void setTitle(String value) { set("title", value); }
	public static String getTitle(String default_value) { return get("title", default_value); }
	public static String getTitle() { return getTitle(""); }

	public static void setLastname(String value) { set("lastname", value); }
	public static String getLastname(String default_value) { return get("lastname", default_value); }
	public static String getLastname() { return getLastname(""); }

	public static void setFirstname(String value) { set("firstname", value); }
	public static String getFirstname(String default_value) { return get("firstname", default_value); }
	public static String getFirstname() { return getFirstname(""); }

	public static void setLocation(String value) { set("location", value); }
	public static String getLocation(String default_value) { return get("location", default_value); }
	public static String getLocation() { return getLocation(""); }

	public static void setPromo(int value) { set("promo", value); }
	public static int getPromo(int default_value) { return get("promo", default_value); }
	public static int getPromo() { return getPromo(0); }

	public static void logout() {
		setLogin(null);
		setPassword(null);
		setTitle(null);
		setLastname(null);
		setFirstname(null);
		setLocation(null);
		setPromo(0);
	}
}
