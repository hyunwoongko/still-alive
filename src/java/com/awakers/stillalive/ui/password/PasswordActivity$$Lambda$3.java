package com.awakers.stillalive.ui.password;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

final /* synthetic */ class PasswordActivity$$Lambda$3 implements OnClickListener {
    private final PasswordActivity arg$1;
    private final EditText arg$2;
    private final InputMethodManager arg$3;

    PasswordActivity$$Lambda$3(PasswordActivity passwordActivity, EditText editText, InputMethodManager inputMethodManager) {
        this.arg$1 = passwordActivity;
        this.arg$2 = editText;
        this.arg$3 = inputMethodManager;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.arg$1.lambda$resetPassword$2$PasswordActivity(this.arg$2, this.arg$3, dialogInterface, i);
    }
}
