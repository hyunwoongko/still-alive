package com.awakers.stillalive.ui.sign_up;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class SignUpFactory_ProvideViewModelFactory implements Factory<SignUpViewModel> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final SignUpFactory module;

    public SignUpFactory_ProvideViewModelFactory(SignUpFactory signUpFactory) {
        this.module = signUpFactory;
    }

    public SignUpViewModel get() {
        return (SignUpViewModel) Preconditions.checkNotNull(this.module.provideViewModel(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<SignUpViewModel> create(SignUpFactory signUpFactory) {
        return new SignUpFactory_ProvideViewModelFactory(signUpFactory);
    }

    public static SignUpViewModel proxyProvideViewModel(SignUpFactory signUpFactory) {
        return signUpFactory.provideViewModel();
    }
}
