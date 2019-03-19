package com.awakers.stillalive.app;

import com.awakers.stillalive.utill.rx.RxSchedulers;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class ApplicationFactory_ProvideRxSchedulersFactory implements Factory<RxSchedulers> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final ApplicationFactory module;

    public ApplicationFactory_ProvideRxSchedulersFactory(ApplicationFactory applicationFactory) {
        this.module = applicationFactory;
    }

    public RxSchedulers get() {
        return (RxSchedulers) Preconditions.checkNotNull(this.module.provideRxSchedulers(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<RxSchedulers> create(ApplicationFactory applicationFactory) {
        return new ApplicationFactory_ProvideRxSchedulersFactory(applicationFactory);
    }

    public static RxSchedulers proxyProvideRxSchedulers(ApplicationFactory applicationFactory) {
        return applicationFactory.provideRxSchedulers();
    }
}
