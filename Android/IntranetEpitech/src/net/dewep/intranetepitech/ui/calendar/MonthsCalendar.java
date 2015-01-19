package net.dewep.intranetepitech.ui.calendar;

import java.util.Calendar;

import fr.qinder.tools.ConvertDate;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.dewep.intranetepitech.R;
import net.dewep.intranetepitech.api.model.EventModel;

public class MonthsCalendar extends GenericCalendar {
    private LayoutInflater mInflater = null;
    private View mView = null;

    public MonthsCalendar(LayoutInflater inflater, LinearLayout parent, int index, EventModel event) {
        mInflater = inflater;
        mView = mInflater.inflate(R.layout.ui_calendar_month, parent, false);
        ((TextView) mView.findViewById(R.id.ui_calendar_month_title)).setText(ConvertDate.formatDefault("MMMM yyyy").format(event.getCalendarStart().getTime()));
        parent.addView(mView, index);
    }

    public IGenericCalendar getInstance(int index, EventModel event) {
        return new WeeksCalendar(mInflater, (LinearLayout) mView.findViewById(R.id.ui_calendar_month_container), index, event);
    }

    public Integer getIdentifier(EventModel event) {
        return event.getCalendarStart().get(Calendar.WEEK_OF_MONTH);
    }

    public void onRefreshFiltering() {
        if (mIsDisplay) {
            mView.setVisibility(View.VISIBLE);
        } else {
            mView.setVisibility(View.GONE);
        }
    }
}
