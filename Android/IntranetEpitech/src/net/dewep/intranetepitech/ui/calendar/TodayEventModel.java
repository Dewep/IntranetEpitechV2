package net.dewep.intranetepitech.ui.calendar;

import java.util.GregorianCalendar;

import net.dewep.intranetepitech.api.model.EventModel;

public class TodayEventModel extends EventModel {

    public TodayEventModel() {
        super(null);
        mCalendarStart = GregorianCalendar.getInstance();
        mCalendarEnd = mCalendarStart;
    }

}
