package com.awakers.stillalive.ui.main.capsule;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CapsuleFactory_ProvideViewModelFactory implements Factory<CapsuleViewModel> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final CapsuleFactory module;

    public CapsuleFactory_ProvideViewModelFactory(CapsuleFactory capsuleFactory) {
        this.module = capsuleFactory;
    }

    public CapsuleViewModel get() {
        return (CapsuleViewModel) Preconditions.checkNotNull(this.module.provideViewModel(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<CapsuleViewModel> create(CapsuleFactory capsuleFactory) {
        return new CapsuleFactory_ProvideViewModelFactory(capsuleFactory);
    }

    public static CapsuleViewModel proxyProvideViewModel(CapsuleFactory capsuleFactory) {
        return capsuleFactory.provideViewModel();
    }
}
