package net.dewep.intranetepitech.landing;

import net.dewep.intranetepitech.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class LandingHomeFragment extends LandingFragment {

	private TextView mLandingHomeState = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.landing_home_fragment, container, false);

		mLandingHomeState = (TextView) rootView.findViewById(R.id.landing_home_state);

		this.updateFragmentData();

		return rootView;
	}

	@Override
	public void updateFragmentData() {
		if (mLandingHomeState != null) {
			mLandingHomeState.setText(getLandingActivity().getMessageState());
		}
	}
}
