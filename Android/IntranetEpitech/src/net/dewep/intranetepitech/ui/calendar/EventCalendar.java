package net.dewep.intranetepitech.ui.calendar;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import fr.qinder.tools.ConvertDate;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import net.dewep.intranetepitech.R;
import net.dewep.intranetepitech.api.model.EventModel;

public class EventCalendar implements IFilterCalendar {
    private View mView = null;
    private Boolean mIsDisplay = true;
    private EventModel mEvent = null;

    public EventCalendar(LayoutInflater inflater, LinearLayout parent, EventModel event) {
        mEvent = event;
        mView = inflater.inflate(R.layout.ui_calendar_event, parent, false);
        SimpleDateFormat formater = null;
        if (mEvent.getCalendarStart().get(Calendar.DAY_OF_MONTH) == mEvent.getCalendarEnd().get(Calendar.DAY_OF_MONTH)) {
            formater = ConvertDate.formatDefault("k:mm");
        } else  {
            formater = ConvertDate.formatDefault("d MMM k:mm");
        }

        LinearLayout layout = (LinearLayout) mView.findViewById(R.id.ui_calendar_event_layout);
        if (mEvent.getGeneralTypeCode().equals("class")) {
            layout.setBackgroundResource(R.color.appoint_class);
        } else if (mEvent.getGeneralTypeCode().equals("tp")) {
            layout.setBackgroundResource(R.color.appoint_tp);
        } else if (mEvent.getGeneralTypeCode().equals("exam")) {
            layout.setBackgroundResource(R.color.appoint_exam);
        } else if (mEvent.isGeneralRdv() || mEvent.getGeneralTypeCode().equals("rdv")) {
            layout.setBackgroundResource(R.color.appoint_rdv);
        } else {
            layout.setBackgroundResource(R.color.appoint_other);
        }
        ((TextView) mView.findViewById(R.id.ui_calendar_event_title)).setText(mEvent.getTitle());
        if (mEvent.getGeneralCodeModule().length() == 0) {
            ((TextView) mView.findViewById(R.id.ui_calendar_event_module)).setVisibility(View.GONE);
        } else {
            ((TextView) mView.findViewById(R.id.ui_calendar_event_module)).setText(mEvent.getGeneralCodeModule());
        }
        ((TextView) mView.findViewById(R.id.ui_calendar_event_hour)).setText(formater.format(event.getCalendarStart().getTime()) + " - " + formater.format(event.getCalendarEnd().getTime()));
        ((TextView) mView.findViewById(R.id.ui_calendar_event_location)).setText(event.getRoom());

        parent.addView(mView);
    }

    public void refreshFiltering() {
        mIsDisplay = false;
        if (mEvent.isRegistered()) {
            mIsDisplay = true;
        }
        Log.i("Event refreshFiltering", mEvent.getTitle() + " : " + String.valueOf(mIsDisplay));
        if (mIsDisplay) {
            mView.setVisibility(View.VISIBLE);
        } else {
            mView.setVisibility(View.GONE);
        }
    }

    public boolean isDisplay() {
        return true;
    }
}
