package com.awakers.stillalive.ui.main.home;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class HomeFactory_ProvideViewModelFactory implements Factory<HomeViewModel> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final HomeFactory module;

    public HomeFactory_ProvideViewModelFactory(HomeFactory homeFactory) {
        this.module = homeFactory;
    }

    public HomeViewModel get() {
        return (HomeViewModel) Preconditions.checkNotNull(this.module.provideViewModel(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<HomeViewModel> create(HomeFactory homeFactory) {
        return new HomeFactory_ProvideViewModelFactory(homeFactory);
    }

    public static HomeViewModel proxyProvideViewModel(HomeFactory homeFactory) {
        return homeFactory.provideViewModel();
    }
}
