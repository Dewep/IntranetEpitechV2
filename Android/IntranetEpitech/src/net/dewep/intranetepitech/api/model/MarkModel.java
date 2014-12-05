package net.dewep.intranetepitech.api.model;

import org.json.JSONObject;

import fr.qinder.tools.JSON;

public class MarkModel {
    private JSONObject mObject;

    public MarkModel(JSONObject object) {
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

    public String getTitleModule() {
        return JSON.parse(mObject, "titlemodule", "");
    }

    public String getCodeActivity() {
        return JSON.parse(mObject, "codeacti", "");
    }

    public String getTitle() {
        return JSON.parse(mObject, "title", "");
    }

    public String getDate() {
        return JSON.parse(mObject, "date", "");
    }

    public String getCorrecteur() {
        return JSON.parse(mObject, "correcteur", "");
    }

    public String getGrade() {
        return JSON.parse(mObject, "grade", "");
    }

    public int getNote() {
        return JSON.parse(mObject, "final_note", 0);
    }

    public String getComment() {
        return JSON.parse(mObject, "comment", "");
    }
}
