package com.awakers.stillalive.base;

import android.app.Activity;
import android.arch.lifecycle.ViewModel;
import android.content.res.Resources;
import android.os.Process;
import android.support.v4.app.Fragment;
import com.awakers.stillalive.app.AwakersApplication;
import com.awakers.stillalive.utill.api.FirebaseApi;
import com.awakers.stillalive.utill.pref.Preference;
import com.awakers.stillalive.utill.rx.RxSchedulers;
import java8.util.function.Consumer;
import rx.Observable;
import rx.Subscription;

public abstract class BaseViewModel extends ViewModel implements BaseNavigator {
    public FirebaseApi api() {
        return BaseNavigator$$CC.api(this);
    }

    public AwakersApplication app() {
        return BaseNavigator$$CC.app(this);
    }

    public Fragment checkNowFragment(BaseActivity baseActivity, int i) {
        return BaseNavigator$$CC.checkNowFragment(this, baseActivity, i);
    }

    public abstract void onCleared();

    public Preference preference() {
        return BaseNavigator$$CC.preference(this);
    }

    public void replace(BaseActivity baseActivity, int i, Fragment fragment, boolean z) {
        BaseNavigator$$CC.replace((BaseNavigator) this, baseActivity, i, fragment, z);
    }

    public void replace(BaseFragment baseFragment, int i, Fragment fragment, boolean z) {
        BaseNavigator$$CC.replace((BaseNavigator) this, baseFragment, i, fragment, z);
    }

    public Resources res() {
        return BaseNavigator$$CC.res(this);
    }

    public RxSchedulers schedulers() {
        return BaseNavigator$$CC.schedulers(this);
    }

    public void showAnim(Activity activity) {
        BaseNavigator$$CC.showAnim(this, activity);
    }

    /* Access modifiers changed, original: protected */
    public Subscription onButtonClicked(Consumer<Boolean> consumer, boolean z) {
        return Observable.just(Boolean.valueOf(app().checkNetwork())).filter(BaseViewModel$$Lambda$0.$instance).subscribeOn(schedulers().backgroundThread()).observeOn(schedulers().androidThread()).subscribe(new BaseViewModel$$Lambda$1(consumer, z));
    }

    static final /* synthetic */ void lambda$onButtonClicked$1$BaseViewModel(Consumer consumer, boolean z, Boolean bool) {
        if (bool.booleanValue()) {
            consumer.accept(Boolean.valueOf(z));
            return;
        }
        Process.killProcess(Process.myPid());
        System.exit(1);
    }
}
