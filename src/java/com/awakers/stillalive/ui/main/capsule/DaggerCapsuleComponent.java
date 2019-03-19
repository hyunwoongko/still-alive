package com.awakers.stillalive.ui.main.capsule;

import dagger.MembersInjector;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class DaggerCapsuleComponent implements CapsuleComponent {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private MembersInjector<CapsuleFragment> capsuleFragmentMembersInjector;
    private Provider<CapsuleViewModel> provideViewModelProvider;

    public static final class Builder {
        private CapsuleFactory capsuleFactory;
        private CapsuleFragment capsuleFragment;

        private Builder() {
        }

        public CapsuleComponent build() {
            StringBuilder stringBuilder;
            if (this.capsuleFactory == null) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(CapsuleFactory.class.getCanonicalName());
                stringBuilder.append(" must be set");
                throw new IllegalStateException(stringBuilder.toString());
            } else if (this.capsuleFragment != null) {
                return new DaggerCapsuleComponent(this);
            } else {
                stringBuilder = new StringBuilder();
                stringBuilder.append(CapsuleFragment.class.getCanonicalName());
                stringBuilder.append(" must be set");
                throw new IllegalStateException(stringBuilder.toString());
            }
        }

        public Builder capsuleFactory(CapsuleFactory capsuleFactory) {
            this.capsuleFactory = (CapsuleFactory) Preconditions.checkNotNull(capsuleFactory);
            return this;
        }

        public Builder capsuleFragment(CapsuleFragment capsuleFragment) {
            this.capsuleFragment = (CapsuleFragment) Preconditions.checkNotNull(capsuleFragment);
            return this;
        }
    }

    private DaggerCapsuleComponent(Builder builder) {
        initialize(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    private void initialize(Builder builder) {
        this.provideViewModelProvider = CapsuleFactory_ProvideViewModelFactory.create(builder.capsuleFactory);
        this.capsuleFragmentMembersInjector = CapsuleFragment_MembersInjector.create(this.provideViewModelProvider);
    }

    public void inject(CapsuleFragment capsuleFragment) {
        this.capsuleFragmentMembersInjector.injectMembers(capsuleFragment);
    }
}
