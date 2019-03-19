package com.awakers.stillalive.cmp.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;

public class AutoStartService extends Service {
    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onStart(Intent intent, int i) {
        FirebaseMessaging.getInstance().setAutoInitEnabled(true);
        FirebaseApp.initializeApp(this);
        super.onStart(intent, i);
    }
}
