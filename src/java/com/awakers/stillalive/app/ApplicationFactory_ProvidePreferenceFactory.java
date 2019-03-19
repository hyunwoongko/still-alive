package com.awakers.stillalive.app;

import com.awakers.stillalive.utill.pref.Preference;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class ApplicationFactory_ProvidePreferenceFactory implements Factory<Preference> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final ApplicationFactory module;

    public ApplicationFactory_ProvidePreferenceFactory(ApplicationFactory applicationFactory) {
        this.module = applicationFactory;
    }

    public Preference get() {
        return (Preference) Preconditions.checkNotNull(this.module.providePreference(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<Preference> create(ApplicationFactory applicationFactory) {
        return new ApplicationFactory_ProvidePreferenceFactory(applicationFactory);
    }

    public static Preference proxyProvidePreference(ApplicationFactory applicationFactory) {
        return applicationFactory.providePreference();
    }
}
