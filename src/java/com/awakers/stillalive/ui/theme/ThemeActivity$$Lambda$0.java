package com.awakers.stillalive.ui.theme;

import com.awakers.stillalive.base.BaseRepository;
import java.util.Vector;
import java8.util.function.Consumer;

final /* synthetic */ class ThemeActivity$$Lambda$0 implements Consumer {
    private final ThemeActivity arg$1;
    private final Vector arg$2;

    ThemeActivity$$Lambda$0(ThemeActivity themeActivity, Vector vector) {
        this.arg$1 = themeActivity;
        this.arg$2 = vector;
    }

    public void accept(Object obj) {
        this.arg$1.lambda$showUserTheme$1$ThemeActivity(this.arg$2, (BaseRepository) obj);
    }
}
