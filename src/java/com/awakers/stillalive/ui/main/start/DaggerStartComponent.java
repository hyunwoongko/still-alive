package com.awakers.stillalive.ui.main.start;

import dagger.MembersInjector;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class DaggerStartComponent implements StartComponent {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private Provider<StartViewModel> provideViewModelProvider;
    private MembersInjector<StartFragment> startFragmentMembersInjector;

    public static final class Builder {
        private StartFactory startFactory;
        private StartFragment startFragment;

        private Builder() {
        }

        public StartComponent build() {
            StringBuilder stringBuilder;
            if (this.startFactory == null) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(StartFactory.class.getCanonicalName());
                stringBuilder.append(" must be set");
                throw new IllegalStateException(stringBuilder.toString());
            } else if (this.startFragment != null) {
                return new DaggerStartComponent(this);
            } else {
                stringBuilder = new StringBuilder();
                stringBuilder.append(StartFragment.class.getCanonicalName());
                stringBuilder.append(" must be set");
                throw new IllegalStateException(stringBuilder.toString());
            }
        }

        public Builder startFactory(StartFactory startFactory) {
            this.startFactory = (StartFactory) Preconditions.checkNotNull(startFactory);
            return this;
        }

        public Builder startFragment(StartFragment startFragment) {
            this.startFragment = (StartFragment) Preconditions.checkNotNull(startFragment);
            return this;
        }
    }

    private DaggerStartComponent(Builder builder) {
        initialize(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    private void initialize(Builder builder) {
        this.provideViewModelProvider = StartFactory_ProvideViewModelFactory.create(builder.startFactory);
        this.startFragmentMembersInjector = StartFragment_MembersInjector.create(this.provideViewModelProvider);
    }

    public void inject(StartFragment startFragment) {
        this.startFragmentMembersInjector.injectMembers(startFragment);
    }
}
