package com.awakers.stillalive.app;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class ApplicationFactory_ProvideAppContextFactory implements Factory<AwakersApplication> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final ApplicationFactory module;

    public ApplicationFactory_ProvideAppContextFactory(ApplicationFactory applicationFactory) {
        this.module = applicationFactory;
    }

    public AwakersApplication get() {
        return (AwakersApplication) Preconditions.checkNotNull(this.module.provideAppContext(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<AwakersApplication> create(ApplicationFactory applicationFactory) {
        return new ApplicationFactory_ProvideAppContextFactory(applicationFactory);
    }

    public static AwakersApplication proxyProvideAppContext(ApplicationFactory applicationFactory) {
        return applicationFactory.provideAppContext();
    }
}
