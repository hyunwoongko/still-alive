package com.awakers.stillalive.ui.theme;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class ThemeFactory_ProvideViewModelFactory implements Factory<ThemeViewModel> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final ThemeFactory module;

    public ThemeFactory_ProvideViewModelFactory(ThemeFactory themeFactory) {
        this.module = themeFactory;
    }

    public ThemeViewModel get() {
        return (ThemeViewModel) Preconditions.checkNotNull(this.module.provideViewModel(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<ThemeViewModel> create(ThemeFactory themeFactory) {
        return new ThemeFactory_ProvideViewModelFactory(themeFactory);
    }

    public static ThemeViewModel proxyProvideViewModel(ThemeFactory themeFactory) {
        return themeFactory.provideViewModel();
    }
}
