package com.awakers.stillalive.base;

import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeInfoDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;

final /* synthetic */ class BackPressActivity$$Lambda$1 implements Closure {
    private final AwesomeInfoDialog arg$1;

    private BackPressActivity$$Lambda$1(AwesomeInfoDialog awesomeInfoDialog) {
        this.arg$1 = awesomeInfoDialog;
    }

    static Closure get$Lambda(AwesomeInfoDialog awesomeInfoDialog) {
        return new BackPressActivity$$Lambda$1(awesomeInfoDialog);
    }

    public void exec() {
        this.arg$1.hide();
    }
}
