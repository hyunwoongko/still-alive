package com.awakers.stillalive.ui.init.greeting;

import dagger.MembersInjector;
import javax.inject.Provider;

public final class GreetingFragment_MembersInjector implements MembersInjector<GreetingFragment> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<GreetingViewModel> viewModelProvider;

    public GreetingFragment_MembersInjector(Provider<GreetingViewModel> provider) {
        this.viewModelProvider = provider;
    }

    public static MembersInjector<GreetingFragment> create(Provider<GreetingViewModel> provider) {
        return new GreetingFragment_MembersInjector(provider);
    }

    public void injectMembers(GreetingFragment greetingFragment) {
        if (greetingFragment == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        greetingFragment.viewModel = (GreetingViewModel) this.viewModelProvider.get();
    }

    public static void injectViewModel(GreetingFragment greetingFragment, Provider<GreetingViewModel> provider) {
        greetingFragment.viewModel = (GreetingViewModel) provider.get();
    }
}
