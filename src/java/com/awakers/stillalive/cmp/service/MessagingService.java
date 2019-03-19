package com.awakers.stillalive.cmp.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Build.VERSION;
import android.support.v4.app.NotificationCompat.Builder;
import com.awakers.stillalive.app.AwakersApplication;
import com.awakers.stillalive.base.BaseRepository;
import com.awakers.stillalive.data.repo.UserCapsule;
import com.awakers.stillalive.data.vo.Capsule;
import com.awakers.stillalive.ui.main.MainActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import java.text.ParseException;

public class MessagingService extends FirebaseMessagingService {
    private static final String CHANNEL_ID = "com.example.user";
    private static final String CHANNEL_NAME = "BrainTraining Channel";
    private NotificationManager notificationManager;

    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage.getData().size() > 0) {
            AwakersApplication.appComponent.api().get("userCapsule", UserCapsule.class, new MessagingService$$Lambda$0(this, remoteMessage));
        }
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void lambda$onMessageReceived$0$MessagingService(RemoteMessage remoteMessage, BaseRepository baseRepository) {
        try {
            Capsule capsule = (Capsule) UserCapsule.get().getUserCapsule().get(UserCapsule.get().getUserCapsule().size() - 1);
            if (!String.valueOf(AwakersApplication.appComponent.app().getCurrentDate()).equals(AwakersApplication.appComponent.app().getDateFromDiff(capsule.getMission().size(), String.valueOf(capsule.getStartDate())))) {
                sendNotification((String) remoteMessage.getData().get("title"), (String) remoteMessage.getData().get("text"));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void sendNotification(String str, String str2) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(67108864);
        Builder contentIntent = new Builder(this, CHANNEL_ID).setSmallIcon(2131558400).setContentTitle(str).setContentText(str2).setAutoCancel(true).setSound(RingtoneManager.getDefaultUri(2)).setPriority(1).setContentIntent(PendingIntent.getActivity(this, 0, intent, 1073741824));
        this.notificationManager = (NotificationManager) getSystemService("notification");
        if (VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, 4);
            notificationChannel.enableLights(true);
            notificationChannel.enableVibration(true);
            notificationChannel.setLightColor(-1);
            notificationChannel.setLockscreenVisibility(1);
            this.notificationManager.createNotificationChannel(notificationChannel);
            this.notificationManager.notify(0, contentIntent.build());
        }
    }
}
