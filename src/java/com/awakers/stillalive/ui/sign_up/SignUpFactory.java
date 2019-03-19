package com.awakers.stillalive.ui.sign_up;

import dagger.Module;
import dagger.Provides;
import java.lang.ref.WeakReference;
import rx.subscriptions.CompositeSubscription;

@Module
public class SignUpFactory {
    private SignUpNavigator navigator;

    SignUpFactory(SignUpNavigator signUpNavigator) {
        this.navigator = signUpNavigator;
    }

    /* Access modifiers changed, original: 0000 */
    @Provides
    public SignUpViewModel provideViewModel() {
        return new SignUpViewModel(new WeakReference(this.navigator), new CompositeSubscription());
    }
}
