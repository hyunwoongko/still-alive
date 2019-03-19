package com.awakers.stillalive.ui.main.start;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class StartFactory_ProvideViewModelFactory implements Factory<StartViewModel> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final StartFactory module;

    public StartFactory_ProvideViewModelFactory(StartFactory startFactory) {
        this.module = startFactory;
    }

    public StartViewModel get() {
        return (StartViewModel) Preconditions.checkNotNull(this.module.provideViewModel(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<StartViewModel> create(StartFactory startFactory) {
        return new StartFactory_ProvideViewModelFactory(startFactory);
    }

    public static StartViewModel proxyProvideViewModel(StartFactory startFactory) {
        return startFactory.provideViewModel();
    }
}
