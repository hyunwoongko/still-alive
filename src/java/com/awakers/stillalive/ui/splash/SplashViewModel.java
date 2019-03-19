package com.awakers.stillalive.ui.splash;

import com.awakers.stillalive.base.BaseViewModel;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.subscriptions.CompositeSubscription;

public class SplashViewModel extends BaseViewModel {
    private SplashNavigator navigator;
    private CompositeSubscription subscription;

    public SplashViewModel(WeakReference<SplashNavigator> weakReference, CompositeSubscription compositeSubscription) {
        this.navigator = (SplashNavigator) weakReference.get();
        this.subscription = compositeSubscription;
    }

    public void showSplash() {
        this.subscription.add(Observable.just(Boolean.valueOf(app().checkNetwork())).filter(SplashViewModel$$Lambda$0.$instance).subscribeOn(schedulers().backgroundThread()).observeOn(schedulers().androidThread()).delay(3000, TimeUnit.MILLISECONDS).subscribe(new SplashViewModel$$Lambda$1(this)));
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void lambda$showSplash$1$SplashViewModel(Boolean bool) {
        this.navigator.showSplash();
    }

    public void onCleared() {
        if (this.subscription != null) {
            this.subscription.unsubscribe();
            this.subscription = null;
        }
        this.navigator = null;
    }
}
