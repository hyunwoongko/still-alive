package com.awakers.stillalive.ui.init.sign_in;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.view.View;
import com.awakers.stillalive.base.BaseViewModel;
import java.lang.ref.WeakReference;
import rx.Observable;
import rx.subscriptions.CompositeSubscription;

public class SignInViewModel extends BaseViewModel {
    public ObservableBoolean clickable = new ObservableBoolean(true);
    public ObservableField<String> email = new ObservableField("");
    private SignInNavigator navigator;
    public ObservableField<String> password = new ObservableField("");
    public ObservableInt progressbar;
    private CompositeSubscription subscription;

    public SignInViewModel(WeakReference<SignInNavigator> weakReference, CompositeSubscription compositeSubscription) {
        this.navigator = (SignInNavigator) weakReference.get();
        this.subscription = compositeSubscription;
    }

    public void onLogining() {
        this.navigator.showProgress();
        this.clickable.set(false);
    }

    public void loginInterrupted() {
        this.navigator.dismissProgress();
        this.clickable.set(true);
    }

    public void onLoginButtonClicked(View view) {
        try {
            onLogining();
            this.subscription.add(Observable.just(Boolean.valueOf(this.navigator.isValid((String) this.email.get(), (String) this.password.get()))).subscribeOn(schedulers().backgroundThread()).observeOn(schedulers().androidThread()).subscribe(new SignInViewModel$$Lambda$0(this)));
        } catch (Exception unused) {
            onLoginButtonClicked(view);
        }
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void lambda$onLoginButtonClicked$0$SignInViewModel(Boolean bool) {
        if (bool.booleanValue()) {
            this.navigator.loginToServer((String) this.email.get(), (String) this.password.get());
        } else {
            loginInterrupted();
        }
    }

    public void onSignUpButtonClicked(View view) {
        CompositeSubscription compositeSubscription = this.subscription;
        SignInNavigator signInNavigator = this.navigator;
        signInNavigator.getClass();
        compositeSubscription.add(onButtonClicked(SignInViewModel$$Lambda$1.get$Lambda(signInNavigator), true));
    }

    public void onCleared() {
        if (this.subscription != null) {
            this.subscription.unsubscribe();
            this.subscription = null;
        }
        this.navigator = null;
    }
}
