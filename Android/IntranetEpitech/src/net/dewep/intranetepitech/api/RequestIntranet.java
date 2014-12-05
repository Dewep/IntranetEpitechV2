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

import org.apache.http.HttpStatus;
import org.json.JSONObject;

import fr.qinder.api.APIRequest;
import fr.qinder.tools.JSON;

/**
 * TODO: Comments this class
 * 
 * @author Maigret Aurelien
 * @author Colin Julien
 */
public abstract class RequestIntranet extends APIRequest {

    private Boolean mWithJson = true;
    private JSONObject mJson = null;

    public RequestIntranet(String url, Boolean withJson) {
        super(Configurations.getHost() + url);
        this.mWithJson = withJson;
    }

    public RequestIntranet(String url) {
        super(Configurations.getFullHost() + url);
    }

    public JSONObject getJSON() {
        return mJson;
    }

    public void preExecute() {
        super.preExecute();
        if (mWithJson) {
            this.addGet("format", "json");
        }
    }

    public void postExecute() {
        super.preExecute();
        if (response.getData() != null && response.getData().startsWith("//") && response.getData().indexOf('\n') != -1) {
            response.setData(response.getData().substring(response.getData().indexOf('\n') + 1));
        }
        if (mWithJson) {
            mJson = JSON.getObject(response.getData());
        }
    }

    @Override
    public void onResult() {
        if (response.getCode() == HttpStatus.SC_OK) {
            onPreSuccess();
            onSuccess();
        } else {
            onError();
        }
    }

    public void onPreSuccess() {
    }

    public abstract void onSuccess();

    public abstract void onError();

}
