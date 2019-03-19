package com.awakers.stillalive.ui.init.sign_in;

import dagger.MembersInjector;
import javax.inject.Provider;

public final class SignInFragment_MembersInjector implements MembersInjector<SignInFragment> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<SignInViewModel> viewModelProvider;

    public SignInFragment_MembersInjector(Provider<SignInViewModel> provider) {
        this.viewModelProvider = provider;
    }

    public static MembersInjector<SignInFragment> create(Provider<SignInViewModel> provider) {
        return new SignInFragment_MembersInjector(provider);
    }

    public void injectMembers(SignInFragment signInFragment) {
        if (signInFragment == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        signInFragment.viewModel = (SignInViewModel) this.viewModelProvider.get();
    }

    public static void injectViewModel(SignInFragment signInFragment, Provider<SignInViewModel> provider) {
        signInFragment.viewModel = (SignInViewModel) provider.get();
    }
}
