package com.awakers.stillalive.ui.main.start;

import dagger.Component;

@Component(dependencies = {StartFragment.class}, modules = {StartFactory.class})
public interface StartComponent {
    void inject(StartFragment startFragment);
}
