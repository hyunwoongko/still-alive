package com.awakers.stillalive.ui.main.setting;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class SettingFactory_ProvideViewModelFactory implements Factory<SettingViewModel> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final SettingFactory module;

    public SettingFactory_ProvideViewModelFactory(SettingFactory settingFactory) {
        this.module = settingFactory;
    }

    public SettingViewModel get() {
        return (SettingViewModel) Preconditions.checkNotNull(this.module.provideViewModel(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<SettingViewModel> create(SettingFactory settingFactory) {
        return new SettingFactory_ProvideViewModelFactory(settingFactory);
    }

    public static SettingViewModel proxyProvideViewModel(SettingFactory settingFactory) {
        return settingFactory.provideViewModel();
    }
}
