package com.awakers.stillalive.ui.main.start;

import android.databinding.ObservableField;
import android.view.View;
import com.awakers.stillalive.app.AwakersApplication;
import com.awakers.stillalive.base.BaseViewModel;
import com.awakers.stillalive.data.vo.Capsule;
import java.lang.ref.WeakReference;
import java.util.Objects;
import rx.Observable;
import rx.subscriptions.CompositeSubscription;

public class StartViewModel extends BaseViewModel {
    public ObservableField<String> capsuleName = new ObservableField();
    private StartNavigator navigator;
    private CompositeSubscription subscription;

    public StartViewModel(WeakReference<StartNavigator> weakReference, CompositeSubscription compositeSubscription) {
        this.navigator = (StartNavigator) weakReference.get();
        this.subscription = compositeSubscription;
    }

    public void showUserTheme() {
        this.subscription.add(Observable.just(Boolean.valueOf(AwakersApplication.appComponent.app().checkNetwork())).filter(StartViewModel$$Lambda$0.$instance).subscribeOn(schedulers().networkThread()).observeOn(schedulers().androidThread()).subscribe(new StartViewModel$$Lambda$1(this)));
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void lambda$showUserTheme$1$StartViewModel(Boolean bool) {
        if (bool.booleanValue()) {
            this.navigator.showUserTheme();
        }
    }

    public void onStartButtonClicked(View view) {
        if (this.capsuleName.get() == null || ((String) Objects.requireNonNull(this.capsuleName.get())).trim().equals("")) {
            this.navigator.nonNameInputed();
        } else if (Capsule.get().getTheme() == null || Capsule.get().getTheme().trim().equals("")) {
            this.navigator.nonThemeSelected();
        } else {
            this.subscription.add(Observable.just(Boolean.valueOf(AwakersApplication.appComponent.app().checkNetwork())).filter(StartViewModel$$Lambda$2.$instance).subscribeOn(schedulers().networkThread()).observeOn(schedulers().androidThread()).subscribe(new StartViewModel$$Lambda$3(this)));
        }
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void lambda$onStartButtonClicked$3$StartViewModel(Boolean bool) {
        Capsule.get().setCapsuleName((String) this.capsuleName.get());
        this.navigator.makeCapsule();
        this.navigator.replaceToHome(Boolean.valueOf(false));
    }

    public void onCleared() {
        if (this.subscription != null) {
            this.subscription.unsubscribe();
            this.subscription = null;
        }
        this.navigator = null;
    }
}
