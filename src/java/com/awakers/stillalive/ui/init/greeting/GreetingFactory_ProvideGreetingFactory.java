package com.awakers.stillalive.ui.init.greeting;

import com.awakers.stillalive.data.vo.Greeting;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class GreetingFactory_ProvideGreetingFactory implements Factory<Greeting> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final GreetingFactory module;

    public GreetingFactory_ProvideGreetingFactory(GreetingFactory greetingFactory) {
        this.module = greetingFactory;
    }

    public Greeting get() {
        return (Greeting) Preconditions.checkNotNull(this.module.provideGreeting(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<Greeting> create(GreetingFactory greetingFactory) {
        return new GreetingFactory_ProvideGreetingFactory(greetingFactory);
    }

    public static Greeting proxyProvideGreeting(GreetingFactory greetingFactory) {
        return greetingFactory.provideGreeting();
    }
}
