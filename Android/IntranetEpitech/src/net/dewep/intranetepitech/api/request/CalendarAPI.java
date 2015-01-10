package net.dewep.intranetepitech.api.request;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import fr.qinder.tools.ConvertDate;

import net.dewep.intranetepitech.api.Configurations;
import net.dewep.intranetepitech.api.IntranetAPI;
import net.dewep.intranetepitech.api.RequestIntranet;
import net.dewep.intranetepitech.api.model.EventModel;

public abstract class CalendarAPI extends RequestIntranet {
    private List<EventModel> mEvents = new ArrayList<EventModel>();

    public CalendarAPI(Calendar date_start, Calendar date_end) {
        super(Configurations.getPathCalendar());
        addGet("start", ConvertDate.getFormatUS("yyyy-MM-dd", date_start));
        addGet("end", ConvertDate.getFormatUS("yyyy-MM-dd", date_end));
        IntranetAPI.request("CalendarAPI", this).execute();
    }

    public List<EventModel> getEvents() {
        return mEvents;
    }

    public List<EventModel> getRegisteredEvents() {
        List<EventModel> list = new ArrayList<EventModel>();
        for (EventModel e : mEvents) {
            if (e.isRegistered()) {
                list.add(e);
            }
        }
        return list;
    }

    @Override
    public void onPreSuccess() {
        JSONArray json = getJSONArray();
        if (json != null) {
            try {
                for (int index = 0; index < json.length(); index++) {
                    mEvents.add(0, new EventModel(json.getJSONObject(index)));
                }
            } catch (JSONException e) {
                mEvents.clear();
            }
        }
    }

}
