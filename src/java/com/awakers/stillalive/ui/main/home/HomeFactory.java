package com.awakers.stillalive.ui.main.home;

import dagger.Module;
import dagger.Provides;
import java.lang.ref.WeakReference;
import rx.subscriptions.CompositeSubscription;

@Module
public class HomeFactory {
    private HomeNavigator navigator;

    public HomeFactory(HomeNavigator homeNavigator) {
        this.navigator = homeNavigator;
    }

    /* Access modifiers changed, original: 0000 */
    @Provides
    public HomeViewModel provideViewModel() {
        return new HomeViewModel(new WeakReference(this.navigator), new CompositeSubscription());
    }
}
