package com.awakers.stillalive.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import com.awakers.stillalive.R;

public class ShopView extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags = -1;
    @NonNull
    private final LinearLayout mboundView0;
    @NonNull
    public final WebView themeShop;

    /* Access modifiers changed, original: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    public boolean setVariable(int i, @Nullable Object obj) {
        return true;
    }

    static {
        sViewsWithIds.put(R.id.theme_shop, 1);
    }

    public ShopView(@NonNull DataBindingComponent dataBindingComponent, @NonNull View view) {
        super(dataBindingComponent, view, 0);
        Object[] mapBindings = mapBindings(dataBindingComponent, view, 2, sIncludes, sViewsWithIds);
        this.mboundView0 = (LinearLayout) mapBindings[0];
        this.mboundView0.setTag(null);
        this.themeShop = (WebView) mapBindings[1];
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 1;
        }
        requestRebind();
    }

    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return false;
        }
    }

    /* Access modifiers changed, original: protected */
    public void executeBindings() {
        synchronized (this) {
            long j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
    }

    @NonNull
    public static ShopView inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ShopView inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable DataBindingComponent dataBindingComponent) {
        return (ShopView) DataBindingUtil.inflate(layoutInflater, R.layout.shop_view, viewGroup, z, dataBindingComponent);
    }

    @NonNull
    public static ShopView inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ShopView inflate(@NonNull LayoutInflater layoutInflater, @Nullable DataBindingComponent dataBindingComponent) {
        return bind(layoutInflater.inflate(R.layout.shop_view, null, false), dataBindingComponent);
    }

    @NonNull
    public static ShopView bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ShopView bind(@NonNull View view, @Nullable DataBindingComponent dataBindingComponent) {
        if ("layout/shop_view_0".equals(view.getTag())) {
            return new ShopView(dataBindingComponent, view);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("view tag isn't correct on view:");
        stringBuilder.append(view.getTag());
        throw new RuntimeException(stringBuilder.toString());
    }
}
