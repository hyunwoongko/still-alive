package com.awakers.stillalive.ui.sign_up;

public interface SignUpNavigator {
    void dismissProgress();

    boolean isValid(String str, String str2, String str3, String str4, String str5, String str6, String str7);

    void saveToServerDB(String str, String str2);

    void showProgress();
}
