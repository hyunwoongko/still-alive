package com.awakers.stillalive.ui.main.capsule;

import dagger.Component;

@Component(dependencies = {CapsuleFragment.class}, modules = {CapsuleFactory.class})
public interface CapsuleComponent {
    void inject(CapsuleFragment capsuleFragment);
}
