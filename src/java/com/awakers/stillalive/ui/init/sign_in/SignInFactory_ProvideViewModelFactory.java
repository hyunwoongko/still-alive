package com.awakers.stillalive.ui.init.sign_in;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class SignInFactory_ProvideViewModelFactory implements Factory<SignInViewModel> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final SignInFactory module;

    public SignInFactory_ProvideViewModelFactory(SignInFactory signInFactory) {
        this.module = signInFactory;
    }

    public SignInViewModel get() {
        return (SignInViewModel) Preconditions.checkNotNull(this.module.provideViewModel(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<SignInViewModel> create(SignInFactory signInFactory) {
        return new SignInFactory_ProvideViewModelFactory(signInFactory);
    }

    public static SignInViewModel proxyProvideViewModel(SignInFactory signInFactory) {
        return signInFactory.provideViewModel();
    }
}
