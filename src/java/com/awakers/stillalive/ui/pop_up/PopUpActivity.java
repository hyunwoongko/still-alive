package com.awakers.stillalive.ui.pop_up;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import com.awakers.stillalive.R;
import com.awakers.stillalive.base.BaseActivity;

public class PopUpActivity extends BaseActivity {
    /* Access modifiers changed, original: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.pop_up_view);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        getWindow().setLayout((int) (((double) displayMetrics.widthPixels) * 0.9d), (int) (((double) displayMetrics.heightPixels) * 0.85d));
        getWindow().setFlags(32, 32);
        getWindow().setFlags(262144, 262144);
    }
}
