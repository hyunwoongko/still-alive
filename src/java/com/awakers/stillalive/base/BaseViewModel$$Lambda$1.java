package com.awakers.stillalive.base;

import java8.util.function.Consumer;
import rx.functions.Action1;

final /* synthetic */ class BaseViewModel$$Lambda$1 implements Action1 {
    private final Consumer arg$1;
    private final boolean arg$2;

    BaseViewModel$$Lambda$1(Consumer consumer, boolean z) {
        this.arg$1 = consumer;
        this.arg$2 = z;
    }

    public void call(Object obj) {
        BaseViewModel.lambda$onButtonClicked$1$BaseViewModel(this.arg$1, this.arg$2, (Boolean) obj);
    }
}
