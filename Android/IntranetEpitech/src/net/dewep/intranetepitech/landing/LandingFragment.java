package net.dewep.intranetepitech.landing;

import android.app.Activity;
import android.support.v4.app.Fragment;

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
