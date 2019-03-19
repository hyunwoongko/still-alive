package com.awakers.stillalive.ui.sign_up;

import dagger.MembersInjector;
import javax.inject.Provider;

public final class SignUpActivity_MembersInjector implements MembersInjector<SignUpActivity> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<SignUpViewModel> viewModelProvider;

    public SignUpActivity_MembersInjector(Provider<SignUpViewModel> provider) {
        this.viewModelProvider = provider;
    }

    public static MembersInjector<SignUpActivity> create(Provider<SignUpViewModel> provider) {
        return new SignUpActivity_MembersInjector(provider);
    }

    public void injectMembers(SignUpActivity signUpActivity) {
        if (signUpActivity == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        signUpActivity.viewModel = (SignUpViewModel) this.viewModelProvider.get();
    }

    public static void injectViewModel(SignUpActivity signUpActivity, Provider<SignUpViewModel> provider) {
        signUpActivity.viewModel = (SignUpViewModel) provider.get();
    }
}
