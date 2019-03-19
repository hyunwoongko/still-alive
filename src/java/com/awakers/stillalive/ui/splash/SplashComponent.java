package com.awakers.stillalive.ui.splash;

import dagger.Component;

@Component(dependencies = {SplashActivity.class}, modules = {SplashFactory.class})
public interface SplashComponent {
    void inject(SplashActivity splashActivity);
}
