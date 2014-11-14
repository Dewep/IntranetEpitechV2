package net.dewep.intranetepitech;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class NotificationsService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@SuppressLint("NewApi")
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if (android.os.Build.VERSION.SDK_INT >= 16) {
			Intent a_propos = new Intent(this, Act_A_Propos.class);
			PendingIntent pIntent = PendingIntent.getActivity(this, 0, a_propos, 0);
			Intent settings = new Intent(this, Act_Settings.class);
			PendingIntent pIntentSettings = PendingIntent.getActivity(this, 0, settings, 0);
			Notification n = new Notification.Builder(this).setContentTitle("Intranet Epitech Notification").setContentText("Hello world").setSmallIcon(R.drawable.ic_logo)
					.setContentIntent(pIntent).setAutoCancel(true).addAction(R.drawable.ic_menu_settings, "Parametres des notifications", pIntentSettings).build();
			NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
			notificationManager.notify(startId, n);
		}
		return START_STICKY;
	}

}
