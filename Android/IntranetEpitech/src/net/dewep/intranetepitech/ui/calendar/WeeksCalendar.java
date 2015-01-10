package net.dewep.intranetepitech.ui.calendar;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import fr.qinder.Q;
import fr.qinder.tools.ConvertDate;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.dewep.intranetepitech.R;
import net.dewep.intranetepitech.api.model.EventModel;

public class WeeksCalendar extends GenericCalendar {
    private LayoutInflater mInflater = null;
    private View mView = null;

    public WeeksCalendar(LayoutInflater inflater, LinearLayout parent, int index, EventModel event) {
        mInflater = inflater;
        mView = mInflater.inflate(R.layout.ui_calendar_week, parent, false);
        ((TextView) mView.findViewById(R.id.ui_calendar_week_title)).setText("Semaine du : " + String.valueOf(event.getCalendarStart().get(Calendar.WEEK_OF_MONTH)));

        SimpleDateFormat formater = ConvertDate.formatDefault("d MMM");
        Calendar week = Calendar.getInstance();
        week.setTime(event.getCalendarStart().getTime());
        week.set(Calendar.DAY_OF_WEEK, week.getFirstDayOfWeek());
        String weekStart = formater.format(week.getTime());
        week.add(Calendar.WEEK_OF_YEAR, 1);
        week.add(Calendar.DAY_OF_MONTH, -1);
        String weekEnd = formater.format(week.getTime());
        ((TextView) mView.findViewById(R.id.ui_calendar_week_title)).setText(Q.getString(R.string.calendar_week_title, weekStart, weekEnd));
        parent.addView(mView, index);
    }

    public IGenericCalendar getInstance(int index, EventModel event) {
        return new DaysCalendar(mInflater, (LinearLayout) mView.findViewById(R.id.ui_calendar_week_container), index, event);
    }

    public Integer getIdentifier(EventModel event) {
        return event.getCalendarStart().get(Calendar.DAY_OF_MONTH);
    }

    public void onRefreshFiltering() {
        if (mIsDisplay) {
            mView.setVisibility(View.VISIBLE);
        } else {
            mView.setVisibility(View.GONE);
        }
    }
}
