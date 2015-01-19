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

import fr.qinder.layout.ScrollViewEvent;
import fr.qinder.layout.ScrollViewEventListener;

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
    private View mSliderTop;
    private View mSliderBot;
    private Boolean isLoad = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.ui_calendar_fragment, container, false);
        mSliderTop = rootView.findViewById(R.id.ui_calendar_slider_top);
        mSliderBot = rootView.findViewById(R.id.ui_calendar_slider_bot);
        setSliderTop(false);
        setSliderBot(false);
        mDateStart = GregorianCalendar.getInstance();
        mDateStart.add(Calendar.DAY_OF_YEAR, -4);
        mDateEnd = GregorianCalendar.getInstance();
        mDateEnd.add(Calendar.DAY_OF_YEAR, 30);
        mCalendar = new ManageCalendar(inflater, (LinearLayout) rootView.findViewById(R.id.ui_calendar_container));
        loadEvents(mDateStart, mDateEnd, true, true);
        ((ScrollViewEvent) rootView.findViewById(R.id.ui_calendar_scrollviewevent)).setScrollViewListener(new ScrollViewEventListener() {
            @Override
            public void onTop(ScrollViewEvent scrollView) {
                if (!isLoad) {
                    Calendar end = (Calendar) mDateStart.clone();
                    end.add(Calendar.DAY_OF_YEAR, -1);
                    mDateStart.add(Calendar.DAY_OF_YEAR, -7);
                    loadEvents(mDateStart, end, true, false);
                }
            }

            @Override
            public void onScrollChanged(ScrollViewEvent scrollView, int x, int y, int oldx, int oldy) {
            }

            @Override
            public void onBot(ScrollViewEvent scrollView) {
                if (!isLoad) {
                    Calendar start = (Calendar) mDateEnd.clone();
                    start.add(Calendar.DAY_OF_YEAR, +1);
                    mDateEnd.add(Calendar.DAY_OF_YEAR, +7);
                    loadEvents(start, mDateEnd, false, true);
                }
            }
        });
        return rootView;
    }

    private void setSliderTop(Boolean state) {
        mSliderTop.setVisibility(state ? View.VISIBLE : View.GONE);
    }

    private void setSliderBot(Boolean state) {
        mSliderBot.setVisibility(state ? View.VISIBLE : View.GONE);
    }

    private void loadEvents(Calendar start, Calendar end, final Boolean sliderTop, final Boolean sliderBot) {
        isLoad = true;
        if (sliderTop && sliderBot) {
            setSliderTop(true);
        } else if (sliderTop) {
            setSliderTop(true);
        } else if (sliderBot) {
            setSliderBot(true);
        }
        new CalendarAPI(start, end) {
            @Override
            public void onSuccess() {
                List<EventModel> events = getEvents();
                for (int i = 0; i < events.size(); i++) {
                    EventModel e = events.get(i);
                    mCalendar.addEvent(e);
                }
                if (sliderTop && sliderBot) {
                    mCalendar.addEvent(new TodayEventModel());
                }
                mCalendar.refreshFiltering();
                setSliderTop(false);
                setSliderBot(false);
                isLoad = false;
            }

            @Override
            public void onError() {
                Log.d("ERROR LOAD EVENTS", response.getData());
                setSliderTop(false);
                setSliderBot(false);
                isLoad = false;
            }
        };
    }

    @Override
    protected int getIdTitle() {
        return R.string.navigation_calendar;
    }

}
