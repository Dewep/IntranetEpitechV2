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
package net.dewep.intranetepitech.ui.mark;

import net.dewep.intranetepitech.EpitechAccount;
import net.dewep.intranetepitech.R;
import net.dewep.intranetepitech.api.model.MarkModel;
import net.dewep.intranetepitech.api.request.MarkAPI;
import net.dewep.intranetepitech.ui.UiFragment;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * TODO: Comments this class
 * 
 * @author Maigret Aurelien
 * @author Colin Julien
 */
public class MarkFragment extends UiFragment {
    LinearLayout mLinearListview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.ui_mark_fragment, container, false);
        mLinearListview = (LinearLayout) rootView.findViewById(R.id.ui_mark_linear_listview);
        new MarkAPI(EpitechAccount.getLogin()) {
            @Override
            public void onSuccess() {
                for (int i = 0; i < getMarks().size(); i++) {
                    addElement(getMarks().get(i));
                }
            }

            @Override
            public void onError() {
            }
        };
        return rootView;
    }

    private void addElement(MarkModel mark) {
        LayoutInflater inflater = (LayoutInflater) getActivity().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View elementView = inflater.inflate(R.layout.ui_mark_mark_element, mLinearListview, false);
        ((TextView) elementView.findViewById(R.id.ui_mark_mark_element_mark)).setText(String.valueOf(mark.getNote()));
        ((TextView) elementView.findViewById(R.id.ui_mark_mark_element_title)).setText(mark.getTitle());
        final TextView commentView = (TextView) elementView.findViewById(R.id.ui_mark_mark_element_comment);
        commentView.setText(mark.getComment());
        elementView.setOnClickListener(new OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    if (commentView.getMaxLines() == 1) {
                        commentView.setMaxLines(Integer.MAX_VALUE);
                    } else {
                        commentView.setMaxLines(1);
                    }
                }
            }
        });
        mLinearListview.addView(elementView);
    }

    @Override
    protected int getIdTitle() {
        return R.string.navigation_mark;
    }

}
