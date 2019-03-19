package com.awakers.stillalive.base;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import com.awakers.stillalive.app.AwakersApplication;
import com.awakers.stillalive.utill.api.FirebaseApi;
import com.awakers.stillalive.utill.pref.Preference;
import com.awakers.stillalive.utill.rx.RxSchedulers;

public abstract class BaseFragment extends Fragment implements BaseNavigator {
    public FirebaseApi api() {
        return BaseNavigator$$CC.api(this);
    }

    public AwakersApplication app() {
        return BaseNavigator$$CC.app(this);
    }

    public Fragment checkNowFragment(BaseActivity baseActivity, int i) {
        return BaseNavigator$$CC.checkNowFragment(this, baseActivity, i);
    }

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

    public void startActivity(Intent intent) {
        super.startActivity(intent);
        showAnim(getActivity());
    }
}
