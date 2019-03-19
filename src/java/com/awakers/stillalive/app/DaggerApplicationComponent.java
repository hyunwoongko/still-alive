package com.awakers.stillalive.app;

import com.awakers.stillalive.utill.api.FirebaseApi;
import com.awakers.stillalive.utill.pref.Preference;
import com.awakers.stillalive.utill.rx.RxSchedulers;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class DaggerApplicationComponent implements ApplicationComponent {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private Provider<FirebaseApi> apiProvider;
    private Provider<AwakersApplication> provideAppContextProvider;
    private Provider<Preference> providePreferenceProvider;
    private Provider<RxSchedulers> provideRxSchedulersProvider;

    public static final class Builder {
        private ApplicationFactory applicationFactory;

        private Builder() {
        }

        public ApplicationComponent build() {
            if (this.applicationFactory != null) {
                return new DaggerApplicationComponent(this);
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(ApplicationFactory.class.getCanonicalName());
            stringBuilder.append(" must be set");
            throw new IllegalStateException(stringBuilder.toString());
        }

        public Builder applicationFactory(ApplicationFactory applicationFactory) {
            this.applicationFactory = (ApplicationFactory) Preconditions.checkNotNull(applicationFactory);
            return this;
        }
    }

    private DaggerApplicationComponent(Builder builder) {
        initialize(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    private void initialize(Builder builder) {
        this.provideAppContextProvider = DoubleCheck.provider(ApplicationFactory_ProvideAppContextFactory.create(builder.applicationFactory));
        this.provideRxSchedulersProvider = DoubleCheck.provider(ApplicationFactory_ProvideRxSchedulersFactory.create(builder.applicationFactory));
        this.providePreferenceProvider = DoubleCheck.provider(ApplicationFactory_ProvidePreferenceFactory.create(builder.applicationFactory));
        this.apiProvider = DoubleCheck.provider(ApplicationFactory_ApiFactory.create(builder.applicationFactory));
    }

    public AwakersApplication app() {
        return (AwakersApplication) this.provideAppContextProvider.get();
    }

    public RxSchedulers schedulers() {
        return (RxSchedulers) this.provideRxSchedulersProvider.get();
    }

    public Preference preference() {
        return (Preference) this.providePreferenceProvider.get();
    }

    public FirebaseApi api() {
        return (FirebaseApi) this.apiProvider.get();
    }
}
