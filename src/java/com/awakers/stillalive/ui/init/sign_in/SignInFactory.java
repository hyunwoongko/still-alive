package com.awakers.stillalive.ui.init.sign_in;

import dagger.Module;
import dagger.Provides;
import java.lang.ref.WeakReference;
import rx.subscriptions.CompositeSubscription;

@Module
public class SignInFactory {
    private SignInNavigator navigator;

    public SignInFactory(SignInNavigator signInNavigator) {
        this.navigator = signInNavigator;
    }

    /* Access modifiers changed, original: 0000 */
    @Provides
    public SignInViewModel provideViewModel() {
        return new SignInViewModel(new WeakReference(this.navigator), new CompositeSubscription());
    }
}
