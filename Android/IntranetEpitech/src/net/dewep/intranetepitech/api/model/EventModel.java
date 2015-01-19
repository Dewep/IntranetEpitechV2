package net.dewep.intranetepitech.api.model;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fr.qinder.tools.ConvertDate;
import fr.qinder.tools.JSON;

public class EventModel {
    protected JSONObject mObject;

    // All
    protected String mType = "";
    protected String mLocation = "";
    protected String mLocationType = "";
    protected String mRoom = "";
    protected String mTitle = "";
    protected Boolean mIsRegistered = false;
    protected int mNumberRegistered = 0;
    protected int mNumberPlace = 0;
    protected String mDateStart = "";
    protected String mDateEnd = "";
    protected String mDuration = "";
    protected List<String> mRights = new ArrayList<String>();

    // General
    protected int mGeneralScolarYear = 0;
    protected String mGeneralCodeModule = "";
    protected String mGeneralCodeInstance = "";
    protected String mGeneralCodeActivity = "";
    protected String mGeneralCodeEvent = "";
    protected int mGeneralSemester = 0;
    protected String mGeneralTitleModule = "";
    protected String mGeneralTypeTitle = "";
    protected String mGeneralTypeCode = "";
    protected Boolean mGeneralIsRdv = false;
    protected Boolean mGeneralIsModuleAvailable = false;
    protected Boolean mGeneralIsModuleRegistered = false;
    protected Boolean mGeneralIsAllowRegistered = false;
    protected Boolean mGeneralIsProject = false;
    protected Boolean mGeneralIsAllowToken = false;
    protected Boolean mGeneralIsRegisterStudent = false;
    protected String mGeneralEventRegistered = "";
    protected String mGeneralRdvGroupRegistered = "";
    protected String mGeneralRdvIndivRegistered = "";

    // Perso calendar
    protected int mPersoId = 0;
    protected int mPersoIdCalendar = 0;
    protected String mPersoType = "";
    protected String mPersoDescription = "";
    protected String mPersoColor = "";
    protected String mPersoMakerLogin = "";
    protected String mPersoMakerTitle = "";

    // Calendar
    protected Calendar mCalendarStart = null;
    protected Calendar mCalendarEnd = null;

    public EventModel(JSONObject object) {
        mObject = object;

        mType = JSON.parse(mObject, "calendar_type", "general");
        mDateStart = JSON.parse(mObject, "start", "");
        mDateEnd = JSON.parse(mObject, "end", "");
        mCalendarStart = Calendar.getInstance();
        mCalendarEnd = Calendar.getInstance();
        try {
            mCalendarStart.setTime(ConvertDate.formatUS("yyyy-MM-dd HH:mm:ss").parse(mDateStart));
            mCalendarEnd.setTime(ConvertDate.formatUS("yyyy-MM-dd HH:mm:ss").parse(mDateEnd));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            JSONArray rights = JSON.parse(mObject, "rights", new JSONArray("[]"));
            for (int i = 0; rights != null && i < rights.length(); i++) {
                mRights.add(rights.getString(i));
            }
        } catch (JSONException e) {
            mRights.clear();
        }

        if (mType.equals("general")) {
            mLocation = JSON.parse(mObject, "room>code", "");
            mLocationType = JSON.parse(mObject, "room>type", "");
            mTitle = JSON.parse(mObject, "acti_title", "");
            mIsRegistered = !JSON.parse(mObject, "event_registered", "null").equals("null");
            mNumberRegistered = JSON.parse(mObject, "total_students_registered", 0);
            mNumberPlace = JSON.parse(mObject, "room>seats", 0);
            mDuration = JSON.parse(mObject, "nb_hours", "");
        } else {
            mLocation = JSON.parse(mObject, "location", "");
            mLocationType = JSON.parse(mObject, "type_room", "");
            mTitle = JSON.parse(mObject, "title", "");
            mIsRegistered = JSON.parse(mObject, "event_registered", false);
            mNumberRegistered = JSON.parse(mObject, "registered", 0);
            mNumberPlace = JSON.parse(mObject, "nb_place", 0);
            mDuration = JSON.parse(mObject, "duration", "");
        }

        int index = mLocation.lastIndexOf('/');
        if (index > 0 && index < mLocation.length() - 1) {
            mRoom = mLocation.substring(mLocation.lastIndexOf('/') + 1);
        }

        if (mType.equals("general")) {
            mGeneralScolarYear = JSON.parse(mObject, "scolaryear", 0);
            mGeneralCodeModule = JSON.parse(mObject, "codemodule", "");
            mGeneralCodeInstance = JSON.parse(mObject, "codeinstance", "");
            mGeneralCodeActivity = JSON.parse(mObject, "codeacti", "");
            mGeneralCodeEvent = JSON.parse(mObject, "codeevent", "");
            mGeneralSemester = JSON.parse(mObject, "semester", 0);
            mGeneralTitleModule = JSON.parse(mObject, "titlemodule", "");
            mGeneralTypeTitle = JSON.parse(mObject, "type_title", "");
            mGeneralTypeCode = JSON.parse(mObject, "type_code", "");
            mGeneralIsRdv = JSON.parse(mObject, "is_rdv", false);
            mGeneralIsModuleAvailable = JSON.parse(mObject, "module_available", false);
            mGeneralIsModuleRegistered = JSON.parse(mObject, "module_registered", false);
            mGeneralIsAllowRegistered = JSON.parse(mObject, "allow_register", false);
            mGeneralIsProject = JSON.parse(mObject, "project", false);
            mGeneralIsAllowToken = JSON.parse(mObject, "allow_token", false);
            mGeneralIsRegisterStudent = JSON.parse(mObject, "register_student", false);
            mGeneralEventRegistered = JSON.parse(mObject, "event_registered", "");
            mGeneralRdvGroupRegistered = JSON.parse(mObject, "rdv_group_registered", "");
            mGeneralRdvIndivRegistered = JSON.parse(mObject, "rdv_indiv_registered", "");
        } else {
            mPersoId = JSON.parse(mObject, "id", 0);
            mPersoIdCalendar = JSON.parse(mObject, "id_calendar", 0);
            mPersoType = JSON.parse(mObject, "type", "");
            mPersoDescription = JSON.parse(mObject, "description", "");
            mPersoColor = JSON.parse(mObject, "color", "");
            mPersoMakerLogin = JSON.parse(mObject, "maker>login", "");
            mPersoMakerTitle = JSON.parse(mObject, "maker>title", "");
        }

        mPersoId = JSON.parse(mObject, "id", 0);
        mPersoIdCalendar = JSON.parse(mObject, "id_calendar", 0);
    }

    public Boolean isAssitant() {
        for (int i = 0; mRights != null && i < mRights.size(); i++) {
            if (mRights.get(i).equals("assistant")) {
                return true;
            }
        }
        return false;
    }

    public Calendar getCalendarStart() {
        return mCalendarStart;
    }

    public Calendar getCalendarEnd() {
        return mCalendarEnd;
    }

    public String getType() {
        return mType;
    }

    public String getLocation() {
        return mLocation;
    }

    public String getRoomType() {
        return mLocationType;
    }

    public String getRoom() {
        return mRoom;
    }

    public String getTitle() {
        return mTitle;
    }

    public Boolean isRegistered() {
        return mIsRegistered;
    }

    public Integer getNumberRegistered() {
        return mNumberRegistered;
    }

    public Integer getNumberPlace() {
        return mNumberPlace;
    }

    public String getDateStart() {
        return mDateStart;
    }

    public String getDateEnd() {
        return mDateEnd;
    }

    public String getDuration() {
        return mDuration;
    }

    public List<String> getRights() {
        return mRights;
    }

    public int getGeneralScolarYear() {
        return mGeneralScolarYear;
    }

    public String getGeneralCodeModule() {
        return mGeneralCodeModule;
    }

    public String getGeneralCodeInstance() {
        return mGeneralCodeInstance;
    }

    public String getGeneralCodeActivity() {
        return mGeneralCodeActivity;
    }

    public String getGeneralCodeEvent() {
        return mGeneralCodeEvent;
    }

    public int getGeneralSemester() {
        return mGeneralSemester;
    }

    public String getGeneralTitleModule() {
        return mGeneralTitleModule;
    }

    public String getGeneralTypeTitle() {
        return mGeneralTypeTitle;
    }

    public String getGeneralTypeCode() {
        return mGeneralTypeCode;
    }

    public Boolean isGeneralRdv() {
        return mGeneralIsRdv;
    }

    public Boolean isGeneralModuleAvailable() {
        return mGeneralIsModuleAvailable;
    }

    public Boolean isGeneralModuleRegistered() {
        return mGeneralIsModuleRegistered;
    }

    public Boolean isGeneralAllowRegistered() {
        return mGeneralIsAllowRegistered;
    }

    public Boolean isGeneralProject() {
        return mGeneralIsProject;
    }

    public Boolean isGeneralAllowToken() {
        return mGeneralIsAllowToken;
    }

    public Boolean isGeneralRegisterStudent() {
        return mGeneralIsRegisterStudent;
    }

    public String getGeneralEventRegistered() {
        return mGeneralEventRegistered;
    }

    public String getGeneralRdvGroupRegistered() {
        return mGeneralRdvGroupRegistered;
    }

    public String getGeneralRdvIndivRegistered() {
        return mGeneralRdvIndivRegistered;
    }

    public int getPersoId() {
        return mPersoId;
    }

    public int getPersoIdCalendar() {
        return mPersoIdCalendar;
    }

    public String getPersoType() {
        return mPersoType;
    }

    public String getPersoDescription() {
        return mPersoDescription;
    }

    public String getPersoColor() {
        return mPersoColor;
    }

    public String getPersoMakerLogin() {
        return mPersoMakerLogin;
    }

    public String getPersoMakerTitle() {
        return mPersoMakerTitle;
    }

}
