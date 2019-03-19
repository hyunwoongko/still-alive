package com.awakers.stillalive.app;

import com.awakers.stillalive.utill.api.FirebaseApi;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class ApplicationFactory_ApiFactory implements Factory<FirebaseApi> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final ApplicationFactory module;

    public ApplicationFactory_ApiFactory(ApplicationFactory applicationFactory) {
        this.module = applicationFactory;
    }

    public FirebaseApi get() {
        return (FirebaseApi) Preconditions.checkNotNull(this.module.api(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<FirebaseApi> create(ApplicationFactory applicationFactory) {
        return new ApplicationFactory_ApiFactory(applicationFactory);
    }

    public static FirebaseApi proxyApi(ApplicationFactory applicationFactory) {
        return applicationFactory.api();
    }
}
