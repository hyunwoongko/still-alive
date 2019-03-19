package com.awakers.stillalive.ui.splash;

import dagger.MembersInjector;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class DaggerSplashComponent implements SplashComponent {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private Provider<SplashViewModel> providePresenterProvider;
    private MembersInjector<SplashActivity> splashActivityMembersInjector;

    public static final class Builder {
        private SplashActivity splashActivity;
        private SplashFactory splashFactory;

        private Builder() {
        }

        public SplashComponent build() {
            StringBuilder stringBuilder;
            if (this.splashFactory == null) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(SplashFactory.class.getCanonicalName());
                stringBuilder.append(" must be set");
                throw new IllegalStateException(stringBuilder.toString());
            } else if (this.splashActivity != null) {
                return new DaggerSplashComponent(this);
            } else {
                stringBuilder = new StringBuilder();
                stringBuilder.append(SplashActivity.class.getCanonicalName());
                stringBuilder.append(" must be set");
                throw new IllegalStateException(stringBuilder.toString());
            }
        }

        public Builder splashFactory(SplashFactory splashFactory) {
            this.splashFactory = (SplashFactory) Preconditions.checkNotNull(splashFactory);
            return this;
        }

        public Builder splashActivity(SplashActivity splashActivity) {
            this.splashActivity = (SplashActivity) Preconditions.checkNotNull(splashActivity);
            return this;
        }
    }

    private DaggerSplashComponent(Builder builder) {
        initialize(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    private void initialize(Builder builder) {
        this.providePresenterProvider = SplashFactory_ProvidePresenterFactory.create(builder.splashFactory);
        this.splashActivityMembersInjector = SplashActivity_MembersInjector.create(this.providePresenterProvider);
    }

    public void inject(SplashActivity splashActivity) {
        this.splashActivityMembersInjector.injectMembers(splashActivity);
    }
}
