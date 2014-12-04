package net.dewep.intranetepitech.ui;

import net.dewep.intranetepitech.R;
import net.dewep.intranetepitech.ui.about.AboutFragment;
import net.dewep.intranetepitech.ui.calendar.CalendarFragment;
import net.dewep.intranetepitech.ui.dashboard.DashboardFragment;
import net.dewep.intranetepitech.ui.mark.MarkFragment;
import net.dewep.intranetepitech.ui.netsoul.NetsoulFragment;
import net.dewep.intranetepitech.ui.project.ProjectFragment;
import net.dewep.intranetepitech.ui.setting.SettingFragment;
import net.dewep.intranetepitech.ui.susie.SusieFragment;
import net.dewep.intranetepitech.ui.trombi.TrombiFragment;
import fr.qinder.layout.DrawerLayoutActivity;
import android.os.Bundle;

public class UiActivity extends DrawerLayoutActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_drawerlayout);

        setConfigDrawerLayout(R.id.ui_toolbar, R.id.ui_drawerlayout, R.id.ui_drawerlayout_navigation, R.id.ui_drawerlayout_content, DashboardFragment.class.getName());

        addListenerDrawerNavigation(R.id.ui_navigation_about, AboutFragment.class.getName());
        addListenerDrawerNavigation(R.id.ui_navigation_calendar, CalendarFragment.class.getName());
        addListenerDrawerNavigation(R.id.ui_navigation_dashboard, DashboardFragment.class.getName());
        addListenerDrawerNavigation(R.id.ui_navigation_mark, MarkFragment.class.getName());
        addListenerDrawerNavigation(R.id.ui_navigation_netsoul, NetsoulFragment.class.getName());
        addListenerDrawerNavigation(R.id.ui_navigation_project, ProjectFragment.class.getName());
        addListenerDrawerNavigation(R.id.ui_navigation_setting, SettingFragment.class.getName());
        addListenerDrawerNavigation(R.id.ui_navigation_susie, SusieFragment.class.getName());
        addListenerDrawerNavigation(R.id.ui_navigation_trombi, TrombiFragment.class.getName());
    }

}
