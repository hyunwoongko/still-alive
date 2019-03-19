package com.awakers.stillalive.base;

import android.app.Activity;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import com.awakers.stillalive.app.AwakersApplication;
import com.awakers.stillalive.utill.api.FirebaseApi;
import com.awakers.stillalive.utill.pref.Preference;
import com.awakers.stillalive.utill.rx.RxSchedulers;
import java.util.Objects;

public abstract /* synthetic */ class BaseNavigator$$CC {
    public static void showAnim(BaseNavigator baseNavigator, Activity activity) {
        activity.overridePendingTransition(17432576, 17432577);
    }

    public static void replace(BaseNavigator baseNavigator, BaseActivity baseActivity, int i, Fragment fragment, boolean z) {
        if (z) {
            baseActivity.getSupportFragmentManager().beginTransaction().replace(i, fragment).addToBackStack(null).commit();
        } else {
            baseActivity.getSupportFragmentManager().beginTransaction().replace(i, fragment).commit();
        }
    }

    public static void replace(@NonNull BaseNavigator baseNavigator, BaseFragment baseFragment, int i, Fragment fragment, boolean z) {
        if (z) {
            ((FragmentActivity) Objects.requireNonNull(baseFragment.getActivity())).getSupportFragmentManager().beginTransaction().replace(i, fragment).addToBackStack(null).commit();
        } else {
            ((FragmentActivity) Objects.requireNonNull(baseFragment.getActivity())).getSupportFragmentManager().beginTransaction().replace(i, fragment).commit();
        }
    }

    public static Fragment checkNowFragment(BaseNavigator baseNavigator, BaseActivity baseActivity, int i) {
        return baseActivity.getSupportFragmentManager().findFragmentById(i);
    }

    public static RxSchedulers schedulers(BaseNavigator baseNavigator) {
        return AwakersApplication.appComponent.schedulers();
    }

    public static Preference preference(BaseNavigator baseNavigator) {
        return AwakersApplication.appComponent.preference();
    }

    public static AwakersApplication app(BaseNavigator baseNavigator) {
        return AwakersApplication.appComponent.app();
    }

    public static Resources res(BaseNavigator baseNavigator) {
        return AwakersApplication.appComponent.app().getResources();
    }

    public static FirebaseApi api(BaseNavigator baseNavigator) {
        return AwakersApplication.appComponent.api();
    }
}
