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

package net.dewep.intranetepitech.api;

/**
 * TODO: Comments this class
 * 
 * @author Maigret Aurelien
 * @author Colin Julien
 */
public final class Configurations {

    /**
     * Constructor, not called, because this is an Utility Class.
     */
    private Configurations() {
    }

    public static String getFullHost() {
        return "https://" + getHost();
    }

    public static String getHost() {
        return "intra.epitech.eu";
    }

    public static String getPathLogin() {
        return "/";
    }

    public static String getPathDashboard() {
        return "/";
    }

}
