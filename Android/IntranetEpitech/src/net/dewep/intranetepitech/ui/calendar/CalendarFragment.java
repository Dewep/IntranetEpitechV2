/*
 * Copyright (C) 2014 Maigret Aurelien / Colin Julien
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.dewep.intranetepitech.ui.calendar;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import net.dewep.intranetepitech.R;
import net.dewep.intranetepitech.api.model.EventModel;
import net.dewep.intranetepitech.api.request.CalendarAPI;
import net.dewep.intranetepitech.ui.UiFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * TODO: Comments this class
 * 
 * @author Maigret Aurelien
 * @author Colin Julien
 */
public class CalendarFragment extends UiFragment {
    private Calendar mDateStart;
    private Calendar mDateEnd;
    private ManageCalendar mCalendar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.ui_calendar_fragment, container, false);
        mDateStart = GregorianCalendar.getInstance();
        mDateStart.add(Calendar.DAY_OF_YEAR, -7);
        mDateEnd = GregorianCalendar.getInstance();
        mDateEnd.add(Calendar.DAY_OF_YEAR, 30);
        mCalendar = new ManageCalendar(inflater, (LinearLayout) rootView.findViewById(R.id.ui_calendar_container));
        Log.d("toto", "wait");
        new CalendarAPI(mDateStart, mDateEnd) {
            @Override
            public void onSuccess() {
                Log.d("toto", "success");
                List<EventModel> events = getRegisteredEvents();
                for (int i = 0; i < events.size(); i++) {
                    EventModel e = events.get(i);
                    mCalendar.addEvent(e);
                }
                mCalendar.refreshFiltering();
            }

            @Override
            public void onError() {
                Log.d("toto", response.getData());
            }
        };
        return rootView;
    }

    @Override
    protected int getIdTitle() {
        return R.string.navigation_calendar;
    }

}
