package com.awakers.stillalive.ui.main.setting;

import android.view.View;

final /* synthetic */ class SettingAdapter$$Lambda$0 implements SettingClickHandler {
    private final SettingAdapter arg$1;
    private final SettingViewHolder arg$2;

    SettingAdapter$$Lambda$0(SettingAdapter settingAdapter, SettingViewHolder settingViewHolder) {
        this.arg$1 = settingAdapter;
        this.arg$2 = settingViewHolder;
    }

    public void itemClicked(View view) {
        this.arg$1.lambda$onBindViewHolder$0$SettingAdapter(this.arg$2, view);
    }
}
