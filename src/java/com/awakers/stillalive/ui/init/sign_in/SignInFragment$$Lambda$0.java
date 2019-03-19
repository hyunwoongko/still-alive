package com.awakers.stillalive.ui.init.sign_in;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

final /* synthetic */ class SignInFragment$$Lambda$0 implements OnCompleteListener {
    private final SignInFragment arg$1;

    SignInFragment$$Lambda$0(SignInFragment signInFragment) {
        this.arg$1 = signInFragment;
    }

    public void onComplete(Task task) {
        this.arg$1.lambda$loginToServer$0$SignInFragment(task);
    }
}
