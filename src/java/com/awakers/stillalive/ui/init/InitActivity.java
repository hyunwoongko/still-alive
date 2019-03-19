package com.awakers.stillalive.ui.init;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import com.awakers.stillalive.R;
import com.awakers.stillalive.app.AwakersApplication;
import com.awakers.stillalive.base.BackPressActivity;
import com.awakers.stillalive.base.BaseActivity;
import com.awakers.stillalive.base.BaseNavigator;
import com.awakers.stillalive.databinding.InitView;
import com.awakers.stillalive.ui.init.sign_in.SignInFragment;

public class InitActivity extends BackPressActivity implements BaseNavigator {
    private InitView view;

    /* Access modifiers changed, original: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.view = (InitView) DataBindingUtil.setContentView(this, R.layout.init_view);
        replace((BaseActivity) this, (int) R.id.init_container, (Fragment) new SignInFragment(), false);
        showAnim(this);
        getPermission();
    }

    /* Access modifiers changed, original: protected */
    public void onDestroy() {
        this.view = null;
        super.onDestroy();
    }

    private void getPermission() {
        if (!app().hasPermission(this, AwakersApplication.Permission)) {
            ActivityCompat.requestPermissions(this, AwakersApplication.Permission, 1);
        }
    }
}
