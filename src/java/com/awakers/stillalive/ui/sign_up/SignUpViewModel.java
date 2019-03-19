package com.awakers.stillalive.ui.sign_up;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.view.View;
import com.awakers.stillalive.base.BaseViewModel;
import java.lang.ref.WeakReference;
import rx.Observable;
import rx.subscriptions.CompositeSubscription;

public class SignUpViewModel extends BaseViewModel implements Cloneable {
    public static ObservableField<String> address;
    public static ObservableField<String> info;
    public static ObservableField<String> job;
    public static ObservableField<String> name;
    public ObservableBoolean clickable;
    public ObservableField<String> email = new ObservableField("");
    private SignUpNavigator navigator;
    public ObservableField<String> password = new ObservableField("");
    public ObservableField<String> passwordCheck = new ObservableField("");
    private CompositeSubscription subscription;

    public SignUpViewModel(WeakReference<SignUpNavigator> weakReference, CompositeSubscription compositeSubscription) {
        this.navigator = (SignUpNavigator) weakReference.get();
        this.subscription = compositeSubscription;
        name = new ObservableField("");
        address = new ObservableField("");
        job = new ObservableField("");
        info = new ObservableField("");
        this.clickable = new ObservableBoolean(true);
    }

    public void onLogining() {
        this.navigator.showProgress();
        this.clickable.set(false);
    }

    public void loginInterrupted() {
        this.navigator.dismissProgress();
        this.clickable.set(true);
    }

    public void onSignUpButtonClicked(View view) {
        try {
            onLogining();
            this.subscription.add(Observable.just(Boolean.valueOf(this.navigator.isValid((String) this.email.get(), (String) this.password.get(), (String) this.passwordCheck.get(), (String) name.get(), (String) job.get(), (String) info.get(), (String) address.get()))).subscribeOn(schedulers().backgroundThread()).observeOn(schedulers().androidThread()).subscribe(new SignUpViewModel$$Lambda$0(this)));
        } catch (Exception unused) {
            onSignUpButtonClicked(view);
        }
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void lambda$onSignUpButtonClicked$0$SignUpViewModel(Boolean bool) {
        if (bool.booleanValue()) {
            this.navigator.saveToServerDB((String) this.password.get(), (String) this.email.get());
        } else {
            loginInterrupted();
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
