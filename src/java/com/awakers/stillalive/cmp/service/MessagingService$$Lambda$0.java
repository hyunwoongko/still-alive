package com.awakers.stillalive.cmp.service;

import com.awakers.stillalive.base.BaseRepository;
import com.google.firebase.messaging.RemoteMessage;
import java8.util.function.Consumer;

final /* synthetic */ class MessagingService$$Lambda$0 implements Consumer {
    private final MessagingService arg$1;
    private final RemoteMessage arg$2;

    MessagingService$$Lambda$0(MessagingService messagingService, RemoteMessage remoteMessage) {
        this.arg$1 = messagingService;
        this.arg$2 = remoteMessage;
    }

    public void accept(Object obj) {
        this.arg$1.lambda$onMessageReceived$0$MessagingService(this.arg$2, (BaseRepository) obj);
    }
}
