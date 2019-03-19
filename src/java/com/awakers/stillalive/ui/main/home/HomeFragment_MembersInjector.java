package com.awakers.stillalive.ui.main.home;

import dagger.MembersInjector;
import javax.inject.Provider;

public final class HomeFragment_MembersInjector implements MembersInjector<HomeFragment> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<HomeViewModel> viewModelProvider;

    public HomeFragment_MembersInjector(Provider<HomeViewModel> provider) {
        this.viewModelProvider = provider;
    }

    public static MembersInjector<HomeFragment> create(Provider<HomeViewModel> provider) {
        return new HomeFragment_MembersInjector(provider);
    }

    public void injectMembers(HomeFragment homeFragment) {
        if (homeFragment == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        homeFragment.viewModel = (HomeViewModel) this.viewModelProvider.get();
    }

    public static void injectViewModel(HomeFragment homeFragment, Provider<HomeViewModel> provider) {
        homeFragment.viewModel = (HomeViewModel) provider.get();
    }
}
