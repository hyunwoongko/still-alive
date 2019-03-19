package com.awakers.stillalive.ui.main.home;

import dagger.Component;

@Component(dependencies = {HomeFragment.class}, modules = {HomeFactory.class})
public interface HomeComponent {
    void inject(HomeFragment homeFragment);
}
