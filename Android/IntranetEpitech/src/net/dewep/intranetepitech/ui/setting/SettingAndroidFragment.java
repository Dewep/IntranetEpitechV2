package net.dewep.intranetepitech.ui.setting;

import net.dewep.intranetepitech.R;
import net.dewep.intranetepitech.ui.UiActivity;
import net.dewep.intranetepitech.ui.netsoul.NetsoulFragment;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.widget.Toast;

public class SettingAndroidFragment extends PreferenceFragment {
    private CheckBoxPreference mSettingService;
    private ListPreference mSettingServiceFrequency;
    private Preference mSettingNetsoul;
    private Preference mSettingCache;
    private Preference mSettingRelog;
    private Preference mSettingLogout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
        mSettingService = (CheckBoxPreference) getPreferenceScreen().findPreference("settingService");
        mSettingServiceFrequency = (ListPreference) getPreferenceScreen().findPreference("settingServiceFrequency");
        mSettingNetsoul = (Preference) getPreferenceScreen().findPreference("settingNetsoul");
        mSettingCache = (Preference) getPreferenceScreen().findPreference("settingCache");
        mSettingRelog = (Preference) getPreferenceScreen().findPreference("settingRelog");
        mSettingLogout = (Preference) getPreferenceScreen().findPreference("settingLogout");
        mSettingServiceFrequency.setEnabled(mSettingService.isChecked());
    }

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        if (preference == mSettingService) {
            mSettingServiceFrequency.setEnabled(mSettingService.isChecked());
            return true;
        }
        if (preference == mSettingNetsoul) {
            ((UiActivity) getActivity()).setContentFragment(NetsoulFragment.class.getName(), true);
            return true;
        }
        if (preference == mSettingCache) {
            Toast.makeText(getActivity(), "Pas de système de cache actuellement.", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (preference == mSettingRelog) {
            Toast.makeText(getActivity(), "Pas encore implémenté.", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (preference == mSettingLogout) {
            Toast.makeText(getActivity(), "Vous avez été déconnecté.", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
}
