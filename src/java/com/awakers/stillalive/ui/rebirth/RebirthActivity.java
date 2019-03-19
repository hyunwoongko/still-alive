package com.awakers.stillalive.ui.rebirth;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import com.awakers.stillalive.R;
import com.awakers.stillalive.base.BaseActivity;
import com.awakers.stillalive.base.BaseNavigator;
import com.awakers.stillalive.databinding.RebirthView;
import es.dmoral.toasty.Toasty;

public class RebirthActivity extends BaseActivity implements BaseNavigator {
    private RebirthView rebirthView;
    private WebSettings webSettings;

    public void onBackPressed() {
    }

    /* Access modifiers changed, original: protected */
    @SuppressLint({"SetJavaScriptEnabled"})
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.rebirthView = (RebirthView) DataBindingUtil.setContentView(this, R.layout.rebirth_view);
        this.rebirthView.purchase.setWebChromeClient(new WebChromeClient());
        this.webSettings = this.rebirthView.purchase.getSettings();
        this.webSettings.setJavaScriptEnabled(true);
        this.rebirthView.purchase.loadUrl("https://www.iamport.kr/demo");
        Toasty.success(this, "테스트 기능입니다. 무료로 회생권이 지급되었으니, 오늘부터 다시 미션을 올려보세요 !", 1).show();
    }

    /* Access modifiers changed, original: protected */
    public void onDestroy() {
        this.rebirthView = null;
        this.webSettings = null;
        super.onDestroy();
    }
}
