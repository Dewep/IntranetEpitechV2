package net.dewep.intranetepitech;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class NotifMessagesReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d("NotifMessagesReceiver", "Alarm startService : NotificationsService.");
		if (Act_Settings.isNotifMessages(context))
			context.startService(new Intent(context, NotificationsService.class));
		else
			NotifMessagesReceiver.CancelAlarm(context);
	}

	public static void SetAlarm(Context context) {
		Intent service = new Intent(context, NotifMessagesReceiver.class);
		PendingIntent pi = PendingIntent.getBroadcast(context, 0, service, 0);
		AlarmManager alarms = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		alarms.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1000 * 60 , pi);
	}

	public static void CancelAlarm(Context context) {
		Intent service = new Intent(context, NotifMessagesReceiver.class);
		PendingIntent pi = PendingIntent.getBroadcast(context, 0, service, 0);
		AlarmManager alarms = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		alarms.cancel(pi);
	}
}
