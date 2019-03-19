package com.awakers.stillalive.ui.theme;

import dagger.MembersInjector;
import javax.inject.Provider;

public final class ThemeActivity_MembersInjector implements MembersInjector<ThemeActivity> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<ThemeViewModel> viewModelProvider;

    public ThemeActivity_MembersInjector(Provider<ThemeViewModel> provider) {
        this.viewModelProvider = provider;
    }

    public static MembersInjector<ThemeActivity> create(Provider<ThemeViewModel> provider) {
        return new ThemeActivity_MembersInjector(provider);
    }

    public void injectMembers(ThemeActivity themeActivity) {
        if (themeActivity == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        themeActivity.viewModel = (ThemeViewModel) this.viewModelProvider.get();
    }

    public static void injectViewModel(ThemeActivity themeActivity, Provider<ThemeViewModel> provider) {
        themeActivity.viewModel = (ThemeViewModel) provider.get();
    }
}
