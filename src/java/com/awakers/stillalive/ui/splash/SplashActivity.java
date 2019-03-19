package com.awakers.stillalive.ui.splash;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import com.awakers.stillalive.R;
import com.awakers.stillalive.app.AwakersApplication;
import com.awakers.stillalive.base.BaseActivity;
import com.awakers.stillalive.base.BaseFragment;
import com.awakers.stillalive.base.BaseNavigator;
import com.awakers.stillalive.base.BaseNavigator$$CC;
import com.awakers.stillalive.databinding.SplashView;
import com.awakers.stillalive.ui.init.InitActivity;
import com.awakers.stillalive.utill.api.FirebaseApi;
import com.awakers.stillalive.utill.pref.Preference;
import com.awakers.stillalive.utill.rx.RxSchedulers;
import javax.inject.Inject;

public class SplashActivity extends AppCompatActivity implements SplashNavigator {
    private SplashView view;
    @Inject
    SplashViewModel viewModel;

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

    /* Access modifiers changed, original: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.view = (SplashView) DataBindingUtil.setContentView(this, R.layout.splash_view);
        DaggerSplashComponent.builder().splashFactory(new SplashFactory(this)).splashActivity(this).build().inject(this);
        this.view.setViewModel(this.viewModel);
        this.viewModel.showSplash();
    }

    /* Access modifiers changed, original: protected */
    public void onDestroy() {
        super.onDestroy();
        this.viewModel.onCleared();
        this.viewModel = null;
        this.view = null;
    }

    public void showSplash() {
        startActivity(new Intent(this, InitActivity.class));
        overridePendingTransition(17432576, 17432577);
        finish();
        overridePendingTransition(17432577, 17432576);
        this.viewModel.onCleared();
    }
}
