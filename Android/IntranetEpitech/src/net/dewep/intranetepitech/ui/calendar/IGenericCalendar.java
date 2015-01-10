package net.dewep.intranetepitech.ui.calendar;

import net.dewep.intranetepitech.api.model.EventModel;

public interface IGenericCalendar {
    public void setIdentifier(int identifier);
    public int getIdentifier();
    public void addEvent(EventModel event);
}
