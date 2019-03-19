package com.awakers.stillalive.ui.main.capsule;

import dagger.MembersInjector;
import javax.inject.Provider;

public final class CapsuleFragment_MembersInjector implements MembersInjector<CapsuleFragment> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<CapsuleViewModel> viewModelProvider;

    public CapsuleFragment_MembersInjector(Provider<CapsuleViewModel> provider) {
        this.viewModelProvider = provider;
    }

    public static MembersInjector<CapsuleFragment> create(Provider<CapsuleViewModel> provider) {
        return new CapsuleFragment_MembersInjector(provider);
    }

    public void injectMembers(CapsuleFragment capsuleFragment) {
        if (capsuleFragment == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        capsuleFragment.viewModel = (CapsuleViewModel) this.viewModelProvider.get();
    }

    public static void injectViewModel(CapsuleFragment capsuleFragment, Provider<CapsuleViewModel> provider) {
        capsuleFragment.viewModel = (CapsuleViewModel) provider.get();
    }
}
