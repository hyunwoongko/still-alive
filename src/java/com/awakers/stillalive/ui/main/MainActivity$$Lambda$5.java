package com.awakers.stillalive.ui.main;

import android.app.Activity;
import es.dmoral.toasty.Toasty;
import rx.functions.Action1;

final /* synthetic */ class MainActivity$$Lambda$5 implements Action1 {
    private final Activity arg$1;

    MainActivity$$Lambda$5(Activity activity) {
        this.arg$1 = activity;
    }

    public void call(Object obj) {
        Toasty.warning(this.arg$1, "생존일지가 소멸되었습니다.", 0).show();
    }
}
