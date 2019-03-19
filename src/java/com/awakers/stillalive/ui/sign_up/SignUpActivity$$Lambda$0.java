package com.awakers.stillalive.ui.sign_up;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

final /* synthetic */ class SignUpActivity$$Lambda$0 implements OnCompleteListener {
    private final SignUpActivity arg$1;

    SignUpActivity$$Lambda$0(SignUpActivity signUpActivity) {
        this.arg$1 = signUpActivity;
    }

    public void onComplete(Task task) {
        this.arg$1.lambda$saveToServerDB$0$SignUpActivity(task);
    }
}
