package net.dewep.intranetepitech.api.request;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import net.dewep.intranetepitech.api.Configurations;
import net.dewep.intranetepitech.api.IntranetAPI;
import net.dewep.intranetepitech.api.RequestIntranet;
import net.dewep.intranetepitech.api.model.MarkModel;
import net.dewep.intranetepitech.api.model.ModuleModel;

public abstract class MarkAPI extends RequestIntranet {
    private List<MarkModel> mMarks = new ArrayList<MarkModel>();
    private List<ModuleModel> mModules = new ArrayList<ModuleModel>();

    public MarkAPI(String login) {
        super(Configurations.getPathMark(login));
        IntranetAPI.request("MarkAPI", this).execute();
    }

    public List<MarkModel> getMarks() {
        return mMarks;
    }

    public List<ModuleModel> getModules() {
        return mModules;
    }

    @Override
    public void onPreSuccess() {
        JSONObject json = getJSON();
        if (json != null) {
            try {
                JSONArray marks = json.getJSONArray("notes");
                for (int index = 0; marks != null && index < marks.length(); index++) {
                    mMarks.add(0, new MarkModel(marks.getJSONObject(index)));
                }
            } catch (JSONException e) {
                mMarks.clear();
            }
            try {
                JSONArray modules = json.getJSONArray("modules");
                for (int index = 0; modules != null && index < modules.length(); index++) {
                    mModules.add(new ModuleModel(modules.getJSONObject(index)));
                }
            } catch (JSONException e) {
                mModules.clear();
            }
        }
    }

}
