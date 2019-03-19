package com.awakers.stillalive.ui.init.sign_in;

import dagger.MembersInjector;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class DaggerSignInComponent implements SignInComponent {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private Provider<SignInViewModel> provideViewModelProvider;
    private MembersInjector<SignInFragment> signInFragmentMembersInjector;

    public static final class Builder {
        private SignInFactory signInFactory;
        private SignInFragment signInFragment;

        private Builder() {
        }

        public SignInComponent build() {
            StringBuilder stringBuilder;
            if (this.signInFactory == null) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(SignInFactory.class.getCanonicalName());
                stringBuilder.append(" must be set");
                throw new IllegalStateException(stringBuilder.toString());
            } else if (this.signInFragment != null) {
                return new DaggerSignInComponent(this);
            } else {
                stringBuilder = new StringBuilder();
                stringBuilder.append(SignInFragment.class.getCanonicalName());
                stringBuilder.append(" must be set");
                throw new IllegalStateException(stringBuilder.toString());
            }
        }

        public Builder signInFactory(SignInFactory signInFactory) {
            this.signInFactory = (SignInFactory) Preconditions.checkNotNull(signInFactory);
            return this;
        }

        public Builder signInFragment(SignInFragment signInFragment) {
            this.signInFragment = (SignInFragment) Preconditions.checkNotNull(signInFragment);
            return this;
        }
    }

    private DaggerSignInComponent(Builder builder) {
        initialize(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    private void initialize(Builder builder) {
        this.provideViewModelProvider = SignInFactory_ProvideViewModelFactory.create(builder.signInFactory);
        this.signInFragmentMembersInjector = SignInFragment_MembersInjector.create(this.provideViewModelProvider);
    }

    public void inject(SignInFragment signInFragment) {
        this.signInFragmentMembersInjector.injectMembers(signInFragment);
    }
}
