package com.awakers.stillalive.ui.init.greeting;

import com.awakers.stillalive.data.vo.Greeting;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class GreetingFactory_ProvideViewModelFactory implements Factory<GreetingViewModel> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<Greeting> greetingProvider;
    private final GreetingFactory module;

    public GreetingFactory_ProvideViewModelFactory(GreetingFactory greetingFactory, Provider<Greeting> provider) {
        this.module = greetingFactory;
        this.greetingProvider = provider;
    }

    public GreetingViewModel get() {
        return (GreetingViewModel) Preconditions.checkNotNull(this.module.provideViewModel((Greeting) this.greetingProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<GreetingViewModel> create(GreetingFactory greetingFactory, Provider<Greeting> provider) {
        return new GreetingFactory_ProvideViewModelFactory(greetingFactory, provider);
    }

    public static GreetingViewModel proxyProvideViewModel(GreetingFactory greetingFactory, Greeting greeting) {
        return greetingFactory.provideViewModel(greeting);
    }
}
