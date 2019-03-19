package com.awakers.stillalive.ui.main.setting;

import dagger.Module;
import dagger.Provides;
import java.lang.ref.WeakReference;
import rx.subscriptions.CompositeSubscription;

@Module
public class SettingFactory {
    private SettingNavigator navigator;

    public SettingFactory(SettingNavigator settingNavigator) {
        this.navigator = settingNavigator;
    }

    /* Access modifiers changed, original: 0000 */
    @Provides
    public SettingViewModel provideViewModel() {
        return new SettingViewModel(new WeakReference(this.navigator), new CompositeSubscription());
    }
}
