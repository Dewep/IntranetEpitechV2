package net.dewep.intranetepitech.ui.calendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import fr.qinder.tools.ConvertDate;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.dewep.intranetepitech.R;
import net.dewep.intranetepitech.api.model.EventModel;

public class DaysCalendar implements IGenericCalendar, IFilterCalendar {
    private LayoutInflater mInflater = null;
    private View mView = null;
    private Boolean mIsDisplay = true;
    private List<EventCalendar> mDays = new ArrayList<EventCalendar>();
    private int mIdentifier = 0;

    public DaysCalendar(LayoutInflater inflater, LinearLayout parent, int index, EventModel event) {
        mInflater = inflater;
        mView = mInflater.inflate(R.layout.ui_calendar_day, parent, false);
        ((TextView) mView.findViewById(R.id.ui_calendar_day_title)).setText(String.valueOf(event.getCalendarStart().get(Calendar.DAY_OF_MONTH)));
        ((TextView) mView.findViewById(R.id.ui_calendar_day_subtitle)).setText(ConvertDate.formatDefault("EE").format(event.getCalendarStart().getTime()));
        parent.addView(mView, index);
    }

    public void addEvent(EventModel event) {
        mDays.add(new EventCalendar(mInflater, (LinearLayout) mView.findViewById(R.id.ui_calendar_day_container), event));
    }

    public void refreshFiltering() {
        mIsDisplay = false;
        for (EventCalendar event : mDays) {
            event.refreshFiltering();
            if (event.isDisplay()) {
                mIsDisplay = true;
            }
        }
        if (mIsDisplay) {
            mView.setVisibility(View.VISIBLE);
        } else {
            mView.setVisibility(View.GONE);
        }
    }

    public boolean isDisplay() {
        return mIsDisplay;
    }

    public void setIdentifier(int identifier) {
        mIdentifier = identifier;
    }

    public int getIdentifier() {
        return mIdentifier;
    }
}
