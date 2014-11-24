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

import java.util.List;

import fr.qinder.Q;
import fr.qinder.tools.JSON;
import net.dewep.intranetepitech.ActivityMain;
import net.dewep.intranetepitech.EpitechAccount;
import net.dewep.intranetepitech.R;
import net.dewep.intranetepitech.animation.DepthPageTransformer;
import net.dewep.intranetepitech.api.request.LoginAPI;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

/**
 * TODO: Comments this class
 * 
 * @author Maigret Aurelien
 * @author Colin Julien
 */
public class LandingActivity extends FragmentActivity implements OnClickListener, OnPageChangeListener {

    // ViewPager variables
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;

    // View of this activity
    private LinearLayout mLandingLogo;
    private LinearLayout mLandingConnection;
    private ProgressBar mLandingConnectionProgressbar;
    private ImageView mLandingConnectionError;
    private LinearLayout mLandingAbout;

    // Methods variables
    private String mError = null;
    private String mState = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_view_pager);

        // Initial ViewPager
        mPager = (ViewPager) findViewById(R.id.landing_fragment);
        mPagerAdapter = new LandingSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setOnPageChangeListener(this);

        // Set Animation
        mPager.setPageTransformer(true, new DepthPageTransformer());

        // Get views
        mLandingLogo = (LinearLayout) findViewById(R.id.landing_logo);
        mLandingConnection = (LinearLayout) findViewById(R.id.landing_connection);
        mLandingConnectionProgressbar = (ProgressBar) findViewById(R.id.landing_connection_progressbar);
        mLandingConnectionError = (ImageView) findViewById(R.id.landing_connection_error);
        mLandingAbout = (LinearLayout) findViewById(R.id.landing_about);

        // SetOnClickListener of the links in the menu
        mLandingLogo.setOnClickListener(this);
        mLandingConnection.setOnClickListener(this);
        mLandingAbout.setOnClickListener(this);

        // Initial Activity
        mLandingLogo.setVisibility(View.GONE);
        mLandingConnectionError.setVisibility(View.GONE);
        mLandingConnectionProgressbar.setVisibility(View.GONE);
        this.testConnection(false);
    }

    @Override
    public void onClick(View v) {
        // Switch page in function of the click in the menu
        if (v.getId() == R.id.landing_logo) {
            mPager.setCurrentItem(0, true);
        } else if (v.getId() == R.id.landing_connection) {
            mPager.setCurrentItem(1, true);
        } else if (v.getId() == R.id.landing_about) {
            mPager.setCurrentItem(2, true);
        }
    }

    private void setConnectionInProgress() {
        this.setMessageState(Q.getString(R.string.landing_connection_in_progress));
        mLandingConnectionError.setVisibility(View.GONE);
        mLandingConnectionProgressbar.setVisibility(View.VISIBLE);
        mPager.setCurrentItem(0, true);
    }

    private void setConnectionError() {
        setConnectionError(true);
    }

    private void setConnectionError(boolean isStrict) {
        if (isStrict) {
            this.setMessageState(Q.getString(R.string.landing_connection_impossible));
            mLandingConnectionError.setVisibility(View.VISIBLE);
            mLandingConnectionProgressbar.setVisibility(View.GONE);
            mPager.setCurrentItem(1, true);
        } else {
            this.setMessageConnectError(null);
            this.setMessageState(Q.getString(R.string.landing_identity_required));
            mLandingConnectionError.setVisibility(View.GONE);
            mLandingConnectionProgressbar.setVisibility(View.GONE);
            mPager.setCurrentItem(0, true);
        }
    }

    private void updateFragmentData() {
        List<Fragment> allFragments = getSupportFragmentManager().getFragments();
        if (allFragments != null) {
            for (Fragment fragment : allFragments) {
                if (fragment instanceof LandingFragment) {
                    LandingFragment listener = (LandingFragment) fragment;
                    listener.updateFragmentData();
                }
            }
        }
    }

    private void setMessageConnectError(String value) {
        mError = value;
        this.updateFragmentData();
    }

    public String getMessageConnectError() {
        return mError != null ? mError : "";
    }

    private void setMessageState(String value) {
        mState = value;
        this.updateFragmentData();
    }

    public String getMessageState() {
        return mState != null ? mState : "";
    }

    public void testConnection() {
        testConnection(true);
    }

    public void testConnection(boolean isStrict) {
        String login = EpitechAccount.getLogin();
        String password = EpitechAccount.getPassword();
        if (login == "" || login.length() < 2) {
            this.setMessageConnectError(Q.getString(R.string.landing_provide_login));
            this.setConnectionError(isStrict);
        } else if (password == "" || password.length() < 2) {
            this.setMessageConnectError(Q.getString(R.string.landing_provide_password));
            this.setConnectionError(isStrict);
        } else {
            this.setMessageConnectError(null);
            this.setConnectionInProgress();
            new LoginAPI(login, password) {
                @Override
                public void onSuccess() {
                    EpitechAccount.setTitle(JSON.parse(getJSON(), "infos>title", EpitechAccount.getLogin()));
                    EpitechAccount.setLastname(JSON.parse(getJSON(), "infos>lastname", ""));
                    EpitechAccount.setFirstname(JSON.parse(getJSON(), "infos>firstname", ""));
                    EpitechAccount.setLocation(JSON.parse(getJSON(), "infos>location", ""));
                    EpitechAccount.setPromo(JSON.parse(getJSON(), "infos>firstname", 0));
                    Intent intent = new Intent(LandingActivity.this, ActivityMain.class);
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onError() {
                    if (response.code == 403) {
                        setMessageConnectError(Q.getString(R.string.landing_bad_identity));
                        setConnectionError();
                    } else {
                        setMessageConnectError(Q.getString(R.string.landing_connection_impossible));
                        setConnectionError();
                    }
                }
            };
        }
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If in the page 0, go back by the system
            super.onBackPressed();
        } else {
            // Else return in the previous page in the ViewPager
            mPager.setCurrentItem(mPager.getCurrentItem() - 1, true);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        if (position == 0) {
            mLandingLogo.setVisibility(View.GONE);
        } else {
            mLandingLogo.setVisibility(View.VISIBLE);
        }
        if (this.getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
        this.getWindow().getDecorView().clearFocus();
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    // Adapter for the ViewPager
    private class LandingSlidePagerAdapter extends FragmentStatePagerAdapter {

        public LandingSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // Return the good fragment in function of the numero of page
            if (position == 1) {
                return new LandingLoginFragment();
            } else if (position == 2) {
                return new LandingAboutFragment();
            }
            return new LandingHomeFragment();
        }

        @Override
        public int getCount() {
            // Return the number of pages
            return 3;
        }

    }

}