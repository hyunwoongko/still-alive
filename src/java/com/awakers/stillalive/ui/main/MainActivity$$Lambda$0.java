package com.awakers.stillalive.ui.main;

import android.app.Activity;
import com.awakers.stillalive.base.BaseRepository;
import java8.util.function.Consumer;

final /* synthetic */ class MainActivity$$Lambda$0 implements Consumer {
    private final MainActivity arg$1;
    private final Activity arg$2;

    MainActivity$$Lambda$0(MainActivity mainActivity, Activity activity) {
        this.arg$1 = mainActivity;
        this.arg$2 = activity;
    }

    public void accept(Object obj) {
        this.arg$1.lambda$check$5$MainActivity(this.arg$2, (BaseRepository) obj);
    }
}
