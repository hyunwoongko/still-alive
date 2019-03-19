package com.awakers.stillalive.ui.theme;

import java8.util.function.Consumer;

final /* synthetic */ class ThemeViewModel$$Lambda$0 implements Consumer {
    private final ThemeNavigator arg$1;

    private ThemeViewModel$$Lambda$0(ThemeNavigator themeNavigator) {
        this.arg$1 = themeNavigator;
    }

    static Consumer get$Lambda(ThemeNavigator themeNavigator) {
        return new ThemeViewModel$$Lambda$0(themeNavigator);
    }

    public void accept(Object obj) {
        this.arg$1.replaceToShop((Boolean) obj);
    }
}
