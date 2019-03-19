package com.awakers.stillalive.ui.init.sign_in;

import java8.util.function.Consumer;

final /* synthetic */ class SignInViewModel$$Lambda$1 implements Consumer {
    private final SignInNavigator arg$1;

    private SignInViewModel$$Lambda$1(SignInNavigator signInNavigator) {
        this.arg$1 = signInNavigator;
    }

    static Consumer get$Lambda(SignInNavigator signInNavigator) {
        return new SignInViewModel$$Lambda$1(signInNavigator);
    }

    public void accept(Object obj) {
        this.arg$1.replaceToSignUp(((Boolean) obj).booleanValue());
    }
}
