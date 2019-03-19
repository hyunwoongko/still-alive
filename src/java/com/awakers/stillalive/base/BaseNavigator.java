package com.awakers.stillalive.base;

import android.app.Activity;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import com.awakers.stillalive.app.AwakersApplication;
import com.awakers.stillalive.utill.api.FirebaseApi;
import com.awakers.stillalive.utill.pref.Preference;
import com.awakers.stillalive.utill.rx.RxSchedulers;

public interface BaseNavigator {
    FirebaseApi api();

    AwakersApplication app();

    Fragment checkNowFragment(BaseActivity baseActivity, int i);

    Preference preference();

    void replace(BaseActivity baseActivity, int i, Fragment fragment, boolean z);

    void replace(@NonNull BaseFragment baseFragment, int i, Fragment fragment, boolean z);

    Resources res();

    RxSchedulers schedulers();

    void showAnim(Activity activity);
}
