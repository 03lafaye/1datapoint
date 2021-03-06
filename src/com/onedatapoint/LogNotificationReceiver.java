package com.onedatapoint;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class LogNotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        int notifID = 1; //getIntent().getExtras().getInt("NotifID");
        String ns = Context.NOTIFICATION_SERVICE;
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(ns);
        int icon = R.drawable.ic_alert_notification;
        CharSequence tickerText = "Feed me";              // ticker-text
        long when = System.currentTimeMillis();         // notification time
        CharSequence contentTitle = "1datapoint";  // message title
        CharSequence contentText = "Feed me data point";      // message text

        final Intent notificationIntent = new Intent(context, CuringDepressionActivity.class);
    
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);

        // the next two lines initialize the Notification, using the configurations above
        Notification notification = new Notification(icon, tickerText, when);
        notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);

        mNotificationManager.notify(notifID, notification);
    }
}
