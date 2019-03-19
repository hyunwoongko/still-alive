package com.awakers.stillalive.ui.sign_up;

import dagger.MembersInjector;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class DaggerSignUpComponent implements SignUpComponent {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private Provider<SignUpViewModel> provideViewModelProvider;
    private MembersInjector<SignUpActivity> signUpActivityMembersInjector;

    public static final class Builder {
        private SignUpActivity signUpActivity;
        private SignUpFactory signUpFactory;

        private Builder() {
        }

        public SignUpComponent build() {
            StringBuilder stringBuilder;
            if (this.signUpFactory == null) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(SignUpFactory.class.getCanonicalName());
                stringBuilder.append(" must be set");
                throw new IllegalStateException(stringBuilder.toString());
            } else if (this.signUpActivity != null) {
                return new DaggerSignUpComponent(this);
            } else {
                stringBuilder = new StringBuilder();
                stringBuilder.append(SignUpActivity.class.getCanonicalName());
                stringBuilder.append(" must be set");
                throw new IllegalStateException(stringBuilder.toString());
            }
        }

        public Builder signUpFactory(SignUpFactory signUpFactory) {
            this.signUpFactory = (SignUpFactory) Preconditions.checkNotNull(signUpFactory);
            return this;
        }

        public Builder signUpActivity(SignUpActivity signUpActivity) {
            this.signUpActivity = (SignUpActivity) Preconditions.checkNotNull(signUpActivity);
            return this;
        }
    }

    private DaggerSignUpComponent(Builder builder) {
        initialize(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    private void initialize(Builder builder) {
        this.provideViewModelProvider = SignUpFactory_ProvideViewModelFactory.create(builder.signUpFactory);
        this.signUpActivityMembersInjector = SignUpActivity_MembersInjector.create(this.provideViewModelProvider);
    }

    public void inject(SignUpActivity signUpActivity) {
        this.signUpActivityMembersInjector.injectMembers(signUpActivity);
    }
}
