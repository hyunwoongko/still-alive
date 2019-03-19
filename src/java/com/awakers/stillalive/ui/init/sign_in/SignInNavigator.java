package com.awakers.stillalive.ui.init.sign_in;

import android.view.View;
import com.awakers.stillalive.base.BaseNavigator;

public interface SignInNavigator extends BaseNavigator {
    void dismissProgress();

    boolean isValid(String str, String str2);

    void loginToServer(String str, String str2);

    void replaceToSignUp(boolean z);

    void requestFocus(View view);

    void showProgress();
}
