package com.awakers.stillalive.ui.main.start;

import dagger.Module;
import dagger.Provides;
import java.lang.ref.WeakReference;
import rx.subscriptions.CompositeSubscription;

@Module
public class StartFactory {
    private StartNavigator navigator;

    public StartFactory(StartNavigator startNavigator) {
        this.navigator = startNavigator;
    }

    /* Access modifiers changed, original: 0000 */
    @Provides
    public StartViewModel provideViewModel() {
        return new StartViewModel(new WeakReference(this.navigator), new CompositeSubscription());
    }
}
