package com.awakers.stillalive.ui.shop;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.awakers.stillalive.R;
import com.awakers.stillalive.base.BaseActivity;
import com.awakers.stillalive.base.BaseNavigator;
import com.awakers.stillalive.databinding.ShopView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.util.Objects;

public class ShopActivity extends BaseActivity implements BaseNavigator {
    private ShopView shopView;
    private WebSettings webSettings;

    /* Access modifiers changed, original: protected */
    @SuppressLint({"SetJavaScriptEnabled"})
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.shopView = (ShopView) DataBindingUtil.setContentView(this, R.layout.shop_view);
        this.shopView.themeShop.setWebChromeClient(new WebChromeClient());
        this.webSettings = this.shopView.themeShop.getSettings();
        this.webSettings.setJavaScriptEnabled(true);
        WebView webView = this.shopView.themeShop;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://web-market-b542b.firebaseapp.com/theme.html?uid=");
        stringBuilder.append(((FirebaseUser) Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser())).getUid());
        webView.loadUrl(stringBuilder.toString());
    }

    /* Access modifiers changed, original: protected */
    public void onDestroy() {
        this.shopView = null;
        this.webSettings = null;
        super.onDestroy();
    }
}
