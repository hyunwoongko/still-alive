package com.awakers.stillalive.ui.splash;

import dagger.MembersInjector;
import javax.inject.Provider;

public final class SplashActivity_MembersInjector implements MembersInjector<SplashActivity> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<SplashViewModel> viewModelProvider;

    public SplashActivity_MembersInjector(Provider<SplashViewModel> provider) {
        this.viewModelProvider = provider;
    }

    public static MembersInjector<SplashActivity> create(Provider<SplashViewModel> provider) {
        return new SplashActivity_MembersInjector(provider);
    }

    public void injectMembers(SplashActivity splashActivity) {
        if (splashActivity == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        splashActivity.viewModel = (SplashViewModel) this.viewModelProvider.get();
    }

    public static void injectViewModel(SplashActivity splashActivity, Provider<SplashViewModel> provider) {
        splashActivity.viewModel = (SplashViewModel) provider.get();
    }
}
