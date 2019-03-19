package com.awakers.stillalive.ui.theme;

import com.awakers.stillalive.base.BaseRepository;
import java.util.Vector;
import java8.util.function.Consumer;

final /* synthetic */ class ThemeActivity$$Lambda$1 implements Consumer {
    private final ThemeActivity arg$1;
    private final ThemeItemModel arg$2;
    private final Vector arg$3;
    private final String arg$4;

    ThemeActivity$$Lambda$1(ThemeActivity themeActivity, ThemeItemModel themeItemModel, Vector vector, String str) {
        this.arg$1 = themeActivity;
        this.arg$2 = themeItemModel;
        this.arg$3 = vector;
        this.arg$4 = str;
    }

    public void accept(Object obj) {
        this.arg$1.lambda$null$0$ThemeActivity(this.arg$2, this.arg$3, this.arg$4, (BaseRepository) obj);
    }
}
