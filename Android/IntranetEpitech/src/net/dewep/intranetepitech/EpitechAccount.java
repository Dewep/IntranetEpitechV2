/*
 * Copyright (C) 2014 Maigret Aurelien / Colin Julien
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.dewep.intranetepitech;

import fr.qinder.pref.Preferences;

/**
 * TODO: Comments this class
 * 
 * @author Maigret Aurelien
 * @author Colin Julien
 */
public final class EpitechAccount {

    private static final String NAMESPACE_ACCOUNT = "Account";

    /**
     * Constructor, not called, because this is an Utility Class.
     */
    private EpitechAccount() {
    }

    public static void set(String key, String value) {
        Preferences.set(NAMESPACE_ACCOUNT, key, value);
    }

    public static String get(String key, String defaultValue) {
        return Preferences.get(NAMESPACE_ACCOUNT, key, defaultValue);
    }

    public static void set(String key, int value) {
        Preferences.set(NAMESPACE_ACCOUNT, key, value);
    }

    public static int get(String key, int default_value) {
        return Preferences.get(NAMESPACE_ACCOUNT, key, default_value);
    }

    public static void setLogin(String value) {
        set("login", value);
    }

    public static String getLogin(String defaultValue) {
        return get("login", defaultValue);
    }

    public static String getLogin() {
        return getLogin("");
    }

    public static void setPassword(String value) {
        set("password", value);
    }

    public static String getPassword(String defaultValue) {
        return get("password", defaultValue);
    }

    public static String getPassword() {
        return getPassword("");
    }

    public static void setTitle(String value) {
        set("title", value);
    }

    public static String getTitle(String defaultValue) {
        return get("title", defaultValue);
    }

    public static String getTitle() {
        return getTitle("");
    }

    public static void setLastname(String value) {
        set("lastname", value);
    }

    public static String getLastname(String defaultValue) {
        return get("lastname", defaultValue);
    }

    public static String getLastname() {
        return getLastname("");
    }

    public static void setFirstname(String value) {
        set("firstname", value);
    }

    public static String getFirstname(String defaultValue) {
        return get("firstname", defaultValue);
    }

    public static String getFirstname() {
        return getFirstname("");
    }

    public static void setLocation(String value) {
        set("location", value);
    }

    public static String getLocation(String defaultValue) {
        return get("location", defaultValue);
    }

    public static String getLocation() {
        return getLocation("");
    }

    public static void setPromo(int value) {
        set("promo", value);
    }

    public static int getPromo(int defaultValue) {
        return get("promo", defaultValue);
    }

    public static int getPromo() {
        return getPromo(0);
    }

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
