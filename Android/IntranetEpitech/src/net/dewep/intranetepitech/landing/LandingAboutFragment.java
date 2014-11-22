package net.dewep.intranetepitech.landing;

import net.dewep.intranetepitech.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LandingAboutFragment extends LandingFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.landing_about_fragment, container, false);
		return rootView;
	}
}
