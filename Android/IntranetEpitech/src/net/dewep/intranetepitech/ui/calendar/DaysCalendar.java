package net.dewep.intranetepitech.ui.calendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import fr.qinder.Q;
import fr.qinder.tools.ConvertDate;

import android.util.Log;
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
    private List<EventCalendar> mEvents = new ArrayList<EventCalendar>();
    private int mIdentifier = 0;

    public DaysCalendar(LayoutInflater inflater, LinearLayout parent, int index, EventModel event) {
        mInflater = inflater;
        mView = mInflater.inflate(R.layout.ui_calendar_day, parent, false);

        Calendar todayDate = GregorianCalendar.getInstance();
        Calendar eventDate = event.getCalendarStart();
        todayDate.set(Calendar.HOUR_OF_DAY, 0);
        todayDate.set(Calendar.MINUTE, 0);
        todayDate.set(Calendar.SECOND, 0);
        todayDate.set(Calendar.MILLISECOND, 0);
        eventDate.set(Calendar.HOUR_OF_DAY, 0);
        eventDate.set(Calendar.MINUTE, 0);
        eventDate.set(Calendar.SECOND, 0);
        eventDate.set(Calendar.MILLISECOND, 0);
        int color = R.color.black;
        if (todayDate.compareTo(eventDate) > 0) { // Ancien
            color = R.color.grey_dark;
        } else if (todayDate.compareTo(eventDate) == 0) { // Aujourd'hui
            color = R.color.blue;
        } else if (todayDate.compareTo(eventDate) < 0) { // Futur
            color = R.color.black;
        }

        TextView title = (TextView) mView.findViewById(R.id.ui_calendar_day_title);
        title.setText(String.valueOf(event.getCalendarStart().get(Calendar.DAY_OF_MONTH)));
        TextView subtitle = (TextView) mView.findViewById(R.id.ui_calendar_day_subtitle);
        subtitle.setText(ConvertDate.formatDefault("EE").format(event.getCalendarStart().getTime()));
        title.setTextColor(Q.get().getResources().getColor(color));
        subtitle.setTextColor(Q.get().getResources().getColor(color));

        parent.addView(mView, index);
    }

    public void addEvent(EventModel event) {
        int index = 0;
        for (EventCalendar e : mEvents) {
            if (event.getCalendarStart().compareTo(e.getEvent().getCalendarStart()) > 0) {
                index++;
            }
        }
        mEvents.add(new EventCalendar(mInflater, (LinearLayout) mView.findViewById(R.id.ui_calendar_day_container), index, event));
    }

    public void refreshFiltering() {
        mIsDisplay = false;
        for (EventCalendar event : mEvents) {
            event.refreshFiltering();
            if (event.isDisplay()) {
                mIsDisplay = true;
            }
        }
        Log.d("EVENT " + ((TextView) mView.findViewById(R.id.ui_calendar_day_title)).getText(), String.valueOf(mIsDisplay));
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
