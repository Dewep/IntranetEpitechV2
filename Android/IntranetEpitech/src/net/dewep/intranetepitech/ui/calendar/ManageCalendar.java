package net.dewep.intranetepitech.ui.calendar;

import java.util.Calendar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import net.dewep.intranetepitech.api.model.EventModel;

public class ManageCalendar extends GenericCalendar {
    private LayoutInflater mInflater = null;
    private View mView = null;

    public ManageCalendar(LayoutInflater inflater, LinearLayout rootView) {
        mInflater = inflater;
        mView = rootView;
    }

    public View inflateView(int identifier, ViewGroup parent) {
        return mInflater.inflate(identifier, parent, false);
    }

    public IGenericCalendar getInstance(int index, EventModel event) {
        return new YearCalendar(mInflater, (LinearLayout) mView, index, event);
    }

    public Integer getIdentifier(EventModel event) {
        return event.getCalendarStart().get(Calendar.YEAR);
    }

    public void onRefreshFiltering() {
        if (mIsDisplay) {
            mView.setVisibility(View.VISIBLE);
        } else {
            mView.setVisibility(View.GONE);
        }
    }
}
