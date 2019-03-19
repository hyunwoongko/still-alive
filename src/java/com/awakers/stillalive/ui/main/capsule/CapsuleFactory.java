package com.awakers.stillalive.ui.main.capsule;

import dagger.Module;
import dagger.Provides;
import java.lang.ref.WeakReference;
import rx.subscriptions.CompositeSubscription;

@Module
public class CapsuleFactory {
    private CapsuleNavigator navigator;

    public CapsuleFactory(CapsuleNavigator capsuleNavigator) {
        this.navigator = capsuleNavigator;
    }

    /* Access modifiers changed, original: 0000 */
    @Provides
    public CapsuleViewModel provideViewModel() {
        return new CapsuleViewModel(new WeakReference(this.navigator), new CompositeSubscription());
    }
}
