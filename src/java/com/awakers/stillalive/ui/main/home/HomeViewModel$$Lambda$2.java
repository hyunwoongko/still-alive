package com.awakers.stillalive.ui.main.home;

import java8.util.function.Consumer;

final /* synthetic */ class HomeViewModel$$Lambda$2 implements Consumer {
    private final HomeNavigator arg$1;

    private HomeViewModel$$Lambda$2(HomeNavigator homeNavigator) {
        this.arg$1 = homeNavigator;
    }

    static Consumer get$Lambda(HomeNavigator homeNavigator) {
        return new HomeViewModel$$Lambda$2(homeNavigator);
    }

    public void accept(Object obj) {
        this.arg$1.moveToMissionActivity((Boolean) obj);
    }
}
