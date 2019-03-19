package com.awakers.stillalive.ui.theme;

import dagger.Module;
import dagger.Provides;
import java.lang.ref.WeakReference;
import rx.subscriptions.CompositeSubscription;

@Module
public class ThemeFactory {
    private ThemeNavigator navigator;

    public ThemeFactory(ThemeNavigator themeNavigator) {
        this.navigator = themeNavigator;
    }

    /* Access modifiers changed, original: 0000 */
    @Provides
    public ThemeViewModel provideViewModel() {
        return new ThemeViewModel(new WeakReference(this.navigator), new CompositeSubscription());
    }
}
