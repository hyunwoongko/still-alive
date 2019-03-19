package com.awakers.stillalive.ui.init.sign_in;

import dagger.Component;

@Component(dependencies = {SignInFragment.class}, modules = {SignInFactory.class})
public interface SignInComponent {
    void inject(SignInFragment signInFragment);
}
