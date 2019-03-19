package com.awakers.stillalive.ui.main.start;

import dagger.MembersInjector;
import javax.inject.Provider;

public final class StartFragment_MembersInjector implements MembersInjector<StartFragment> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<StartViewModel> viewModelProvider;

    public StartFragment_MembersInjector(Provider<StartViewModel> provider) {
        this.viewModelProvider = provider;
    }

    public static MembersInjector<StartFragment> create(Provider<StartViewModel> provider) {
        return new StartFragment_MembersInjector(provider);
    }

    public void injectMembers(StartFragment startFragment) {
        if (startFragment == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        startFragment.viewModel = (StartViewModel) this.viewModelProvider.get();
    }

    public static void injectViewModel(StartFragment startFragment, Provider<StartViewModel> provider) {
        startFragment.viewModel = (StartViewModel) provider.get();
    }
}
