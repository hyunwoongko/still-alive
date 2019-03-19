package com.awakers.stillalive.ui.splash;

import dagger.Module;
import dagger.Provides;
import java.lang.ref.WeakReference;
import rx.subscriptions.CompositeSubscription;

@Module
public class SplashFactory {
    private SplashNavigator view;

    public SplashFactory(SplashNavigator splashNavigator) {
        this.view = splashNavigator;
    }

    /* Access modifiers changed, original: 0000 */
    @Provides
    public SplashViewModel providePresenter() {
        return new SplashViewModel(new WeakReference(this.view), new CompositeSubscription());
    }
}
