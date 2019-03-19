package com.awakers.stillalive.ui.sign_up;

import dagger.Component;

@Component(dependencies = {SignUpActivity.class}, modules = {SignUpFactory.class})
public interface SignUpComponent {
    void inject(SignUpActivity signUpActivity);
}
