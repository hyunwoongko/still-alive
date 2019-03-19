package com.awakers.stillalive.ui.main.start;

import android.view.View;

final /* synthetic */ class StartAdapter$$Lambda$0 implements StartClickHandler {
    private final StartAdapter arg$1;
    private final int arg$2;

    StartAdapter$$Lambda$0(StartAdapter startAdapter, int i) {
        this.arg$1 = startAdapter;
        this.arg$2 = i;
    }

    public void itemClicked(View view) {
        this.arg$1.lambda$onBindViewHolder$0$StartAdapter(this.arg$2, view);
    }
}
