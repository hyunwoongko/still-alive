package com.awakers.stillalive.ui.splash;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class SplashFactory_ProvidePresenterFactory implements Factory<SplashViewModel> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final SplashFactory module;

    public SplashFactory_ProvidePresenterFactory(SplashFactory splashFactory) {
        this.module = splashFactory;
    }

    public SplashViewModel get() {
        return (SplashViewModel) Preconditions.checkNotNull(this.module.providePresenter(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<SplashViewModel> create(SplashFactory splashFactory) {
        return new SplashFactory_ProvidePresenterFactory(splashFactory);
    }

    public static SplashViewModel proxyProvidePresenter(SplashFactory splashFactory) {
        return splashFactory.providePresenter();
    }
}
