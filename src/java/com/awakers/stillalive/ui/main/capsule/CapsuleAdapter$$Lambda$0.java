package com.awakers.stillalive.ui.main.capsule;

import android.view.View;

final /* synthetic */ class CapsuleAdapter$$Lambda$0 implements CapsuleClickHandler {
    private final CapsuleAdapter arg$1;
    private final int arg$2;
    private final CapsuleViewHolder arg$3;

    CapsuleAdapter$$Lambda$0(CapsuleAdapter capsuleAdapter, int i, CapsuleViewHolder capsuleViewHolder) {
        this.arg$1 = capsuleAdapter;
        this.arg$2 = i;
        this.arg$3 = capsuleViewHolder;
    }

    public void itemClicked(View view) {
        this.arg$1.lambda$onBindViewHolder$0$CapsuleAdapter(this.arg$2, this.arg$3, view);
    }
}
