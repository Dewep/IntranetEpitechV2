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

package net.dewep.intranetepitech.landing;

import net.dewep.intranetepitech.EpitechAccount;
import net.dewep.intranetepitech.R;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

/**
 * TODO: Comments this class
 * 
 * @author Maigret Aurelien
 * @author Colin Julien
 */
public class LandingLoginFragment extends LandingFragment implements OnClickListener, OnEditorActionListener {

    private EditText mLandingLoginLogin = null;
    private EditText mLandingLoginPassword = null;
    private TextView mLandingLoginError = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.landing_login_fragment, container, false);

        mLandingLoginLogin = (EditText) rootView.findViewById(R.id.landing_login_login);
        mLandingLoginPassword = (EditText) rootView.findViewById(R.id.landing_login_password);
        Button landingLoginConnect = (Button) rootView.findViewById(R.id.landing_login_connect);
        mLandingLoginError = (TextView) rootView.findViewById(R.id.landing_login_error);

        mLandingLoginLogin.setText(EpitechAccount.getLogin());
        landingLoginConnect.setOnClickListener(this);

        mLandingLoginPassword.setOnEditorActionListener(this);

        this.updateFragmentData();

        return rootView;
    }

    public void testConnection() {
        if (mLandingLoginLogin != null && mLandingLoginPassword != null && mLandingLoginError != null) {
            EpitechAccount.setLogin(mLandingLoginLogin.getText().toString());
            EpitechAccount.setPassword(mLandingLoginPassword.getText().toString());
            getLandingActivity().testConnection();
        }
    }

    @Override
    public void updateFragmentData() {
        if (mLandingLoginError != null) {
            mLandingLoginError.setText(getLandingActivity().getMessageConnectError());
        }
    }

    @Override
    public void onClick(View v) {
        testConnection();
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        testConnection();
        return true;
    }

}
