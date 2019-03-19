package com.awakers.stillalive.ui.theme;

import android.view.View;
import com.awakers.stillalive.base.BaseViewModel;
import java.lang.ref.WeakReference;
import rx.Observable;
import rx.subscriptions.CompositeSubscription;

public class ThemeViewModel extends BaseViewModel {
    private ThemeNavigator navigator;
    private CompositeSubscription subscription;

    public ThemeViewModel(WeakReference<ThemeNavigator> weakReference, CompositeSubscription compositeSubscription) {
        this.navigator = (ThemeNavigator) weakReference.get();
        this.subscription = compositeSubscription;
    }

    public void connectWithThemeShop(View view) {
        CompositeSubscription compositeSubscription = this.subscription;
        ThemeNavigator themeNavigator = this.navigator;
        themeNavigator.getClass();
        compositeSubscription.add(onButtonClicked(ThemeViewModel$$Lambda$0.get$Lambda(themeNavigator), true));
    }

    public void showUserTheme() {
        this.subscription.add(Observable.just(Boolean.valueOf(app().checkNetwork())).filter(ThemeViewModel$$Lambda$1.$instance).subscribeOn(schedulers().networkThread()).observeOn(schedulers().androidThread()).subscribe(new ThemeViewModel$$Lambda$2(this)));
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void lambda$showUserTheme$1$ThemeViewModel(Boolean bool) {
        if (bool.booleanValue()) {
            this.navigator.showUserTheme();
        }
    }

    public void onCleared() {
        if (this.subscription != null) {
            this.subscription.unsubscribe();
            this.subscription = null;
        }
        this.navigator = null;
    }
}
