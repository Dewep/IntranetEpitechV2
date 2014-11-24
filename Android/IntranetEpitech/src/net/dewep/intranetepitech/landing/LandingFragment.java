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

import android.app.Activity;
import android.support.v4.app.Fragment;

/**
 * TODO: Comments this class
 * 
 * @author Maigret Aurelien
 * @author Colin Julien
 */
public abstract class LandingFragment extends Fragment {

    private LandingActivity mLandingActivity = null;

    public LandingActivity getLandingActivity() {
        if (mLandingActivity == null)
            mLandingActivity = (LandingActivity) this.getActivity();
        return mLandingActivity;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mLandingActivity = (LandingActivity) activity;
    }

    void updateFragmentData() {
    }

}
