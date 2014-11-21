package net.dewep.intranetepitech;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LandingLoginFragment extends Fragment {

	LandingActivity activity = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		activity = (LandingActivity) this.getActivity();
		ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.landing_login_fragment, container, false);
		return rootView;
	}
}
