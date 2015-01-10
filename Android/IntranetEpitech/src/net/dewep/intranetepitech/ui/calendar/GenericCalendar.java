package net.dewep.intranetepitech.ui.calendar;

import java.util.Map;
import java.util.TreeMap;

import net.dewep.intranetepitech.api.model.EventModel;

public abstract class GenericCalendar implements IGenericCalendar, IFilterCalendar {
    protected Map<Integer, IGenericCalendar> mContainer = new TreeMap<Integer, IGenericCalendar>();
    protected Boolean mIsDisplay = true;
    protected int mIdentifier = 0;

    public abstract IGenericCalendar getInstance(int index, EventModel event);

    public abstract Integer getIdentifier(EventModel event);

    public abstract void onRefreshFiltering();

    public void setIdentifier(int identifier) {
        mIdentifier = identifier;
    }

    public int getIdentifier() {
        return mIdentifier;
    }

    public void addEvent(EventModel event) {
        int identifier = getIdentifier(event);

        if (!mContainer.containsKey(identifier)) {
            int index = 0;
            for (Map.Entry<Integer, IGenericCalendar> entry : mContainer.entrySet()) {
                if (getIdentifier(event) > ((IGenericCalendar) entry.getValue()).getIdentifier()) {
                    index++;
                }
            }
            IGenericCalendar instance = getInstance(index, event);
            mContainer.put(identifier, instance);
            if (instance instanceof IGenericCalendar) {
                ((IGenericCalendar) instance).setIdentifier(identifier);
            }
        }

        if (mContainer.get(identifier) instanceof IGenericCalendar) {
            ((IGenericCalendar) mContainer.get(identifier)).addEvent(event);
        }
    }

    public void refreshFiltering() {
        mIsDisplay = false;
        for (Map.Entry<Integer, IGenericCalendar> entry : mContainer.entrySet()) {
            if (entry.getValue() instanceof IFilterCalendar) {
                IFilterCalendar filter = ((IFilterCalendar) entry.getValue());
                filter.refreshFiltering();
                if (filter.isDisplay()) {
                    mIsDisplay = true;
                }
            }
        }
        onRefreshFiltering();
    }

    public boolean isDisplay() {
        return mIsDisplay;
    }
}
