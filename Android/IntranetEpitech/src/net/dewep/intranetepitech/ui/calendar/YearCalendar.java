package net.dewep.intranetepitech.ui.calendar;

import java.util.Calendar;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import net.dewep.intranetepitech.R;
import net.dewep.intranetepitech.api.model.EventModel;

public class YearCalendar extends GenericCalendar {
    private LayoutInflater mInflater = null;
    private View mView = null;

    public YearCalendar(LayoutInflater inflater, LinearLayout parent, int index, EventModel event) {
        mInflater = inflater;
        mView = mInflater.inflate(R.layout.ui_calendar_year, parent, false);
        parent.addView(mView, index);
    }

    public IGenericCalendar getInstance(int index, EventModel event) {
        return new MonthsCalendar(mInflater, (LinearLayout) mView.findViewById(R.id.ui_calendar_year_container), index, event);
    }

    public Integer getIdentifier(EventModel event) {
        return event.getCalendarStart().get(Calendar.MONTH);
    }

    public void onRefreshFiltering() {
        if (mIsDisplay) {
            mView.setVisibility(View.VISIBLE);
        } else {
            mView.setVisibility(View.GONE);
        }
    }
}
