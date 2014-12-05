package net.dewep.intranetepitech.api.model;

import org.json.JSONObject;

import fr.qinder.tools.JSON;

public class ModuleModel {
    private JSONObject mObject;

    public ModuleModel(JSONObject object) {
        mObject = object;
    }

    public int getScolarYear() {
        return JSON.parse(mObject, "scolaryear", 0);
    }

    public String getCodeModule() {
        return JSON.parse(mObject, "codemodule", "");
    }

    public String getCodeInstance() {
        return JSON.parse(mObject, "codeinstance", "");
    }

    public String getTitle() {
        return JSON.parse(mObject, "title", "");
    }

    public String getDateInscription() {
        return JSON.parse(mObject, "date_ins", "");
    }

    public String getGrade() {
        return JSON.parse(mObject, "grade", "");
    }

    public int getCredits() {
        return JSON.parse(mObject, "credits", 0);
    }

    public boolean isBarrage() {
        return JSON.parse(mObject, "barrage", 0) == 1;
    }
}
