package com.awakers.stillalive.ui.main.capsule;

import com.awakers.stillalive.base.BaseViewModel;
import java.lang.ref.WeakReference;
import rx.Observable;
import rx.subscriptions.CompositeSubscription;

public class CapsuleViewModel extends BaseViewModel {
    private CapsuleNavigator navigator;
    private CompositeSubscription subscription;

    public CapsuleViewModel(WeakReference<CapsuleNavigator> weakReference, CompositeSubscription compositeSubscription) {
        this.navigator = (CapsuleNavigator) weakReference.get();
        this.subscription = compositeSubscription;
    }

    public void showUserCapsule() {
        this.subscription.add(Observable.just(Boolean.valueOf(app().checkNetwork())).filter(CapsuleViewModel$$Lambda$0.$instance).subscribeOn(schedulers().networkThread()).observeOn(schedulers().androidThread()).subscribe(new CapsuleViewModel$$Lambda$1(this)));
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void lambda$showUserCapsule$1$CapsuleViewModel(Boolean bool) {
        if (bool.booleanValue()) {
            this.navigator.showUserCapsule();
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
