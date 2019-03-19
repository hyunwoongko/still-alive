package com.awakers.stillalive.cmp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import com.awakers.stillalive.cmp.service.AutoStartService;
import com.awakers.stillalive.cmp.service.MessagingService;

public class BootReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (VERSION.SDK_INT >= 26) {
            context.startForegroundService(new Intent(context, AutoStartService.class));
            context.startForegroundService(new Intent(context, MessagingService.class));
            return;
        }
        context.startService(new Intent(context, AutoStartService.class));
        context.startService(new Intent(context, MessagingService.class));
    }
}
