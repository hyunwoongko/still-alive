package com.awakers.stillalive.ui.main.setting;

import com.awakers.stillalive.base.BaseViewModel;
import java.lang.ref.WeakReference;
import rx.Observable;
import rx.subscriptions.CompositeSubscription;

public class SettingViewModel extends BaseViewModel {
    private SettingNavigator navigator;
    private CompositeSubscription subscription;

    public SettingViewModel(WeakReference<SettingNavigator> weakReference, CompositeSubscription compositeSubscription) {
        this.navigator = (SettingNavigator) weakReference.get();
        this.subscription = compositeSubscription;
    }

    public void logout() {
        this.subscription.add(Observable.just(Boolean.valueOf(app().checkNetwork())).filter(SettingViewModel$$Lambda$0.$instance).subscribeOn(schedulers().networkThread()).subscribeOn(schedulers().androidThread()).subscribe(new SettingViewModel$$Lambda$1(this)));
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void lambda$logout$1$SettingViewModel(Boolean bool) {
        if (bool.booleanValue()) {
            this.navigator.logout();
        }
    }

    public void withdrawal() {
        this.subscription.add(Observable.just(Boolean.valueOf(app().checkNetwork())).filter(SettingViewModel$$Lambda$2.$instance).subscribeOn(schedulers().networkThread()).subscribeOn(schedulers().androidThread()).subscribe(new SettingViewModel$$Lambda$3(this)));
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void lambda$withdrawal$3$SettingViewModel(Boolean bool) {
        if (bool.booleanValue()) {
            this.navigator.withdrawal();
        }
    }

    public void showTheme() {
        this.subscription.add(Observable.just(Integer.valueOf(0)).observeOn(schedulers().androidThread()).subscribeOn(schedulers().backgroundThread()).subscribe(new SettingViewModel$$Lambda$4(this)));
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void lambda$showTheme$4$SettingViewModel(Integer num) {
        this.navigator.showTheme();
    }

    public void information() {
        this.subscription.add(Observable.just(Integer.valueOf(0)).observeOn(schedulers().androidThread()).subscribeOn(schedulers().backgroundThread()).subscribe(new SettingViewModel$$Lambda$5(this)));
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void lambda$information$5$SettingViewModel(Integer num) {
        this.navigator.information();
    }

    public void onCleared() {
        if (this.subscription != null) {
            this.subscription.unsubscribe();
            this.subscription = null;
        }
        this.navigator = null;
    }
}
