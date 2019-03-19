package com.awakers.stillalive.ui.init.greeting;

import dagger.Component;

@Component(dependencies = {GreetingFragment.class}, modules = {GreetingFactory.class})
public interface GreetingComponent {
    void inject(GreetingFragment greetingFragment);
}
