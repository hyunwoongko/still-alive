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
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.awakers.stillalive.R;
import com.awakers.stillalive.ui.splash.SplashViewModel;

public class SplashView extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags = -1;
    @Nullable
    private SplashViewModel mViewModel;
    @NonNull
    private final LinearLayout mboundView0;
    @NonNull
    public final ImageView splashImage;

    /* Access modifiers changed, original: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    static {
        sViewsWithIds.put(R.id.splash_image, 1);
    }

    public SplashView(@NonNull DataBindingComponent dataBindingComponent, @NonNull View view) {
        super(dataBindingComponent, view, 0);
        Object[] mapBindings = mapBindings(dataBindingComponent, view, 2, sIncludes, sViewsWithIds);
        this.mboundView0 = (LinearLayout) mapBindings[0];
        this.mboundView0.setTag(null);
        this.splashImage = (ImageView) mapBindings[1];
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2;
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

    public boolean setVariable(int i, @Nullable Object obj) {
        if (3 != i) {
            return false;
        }
        setViewModel((SplashViewModel) obj);
        return true;
    }

    public void setViewModel(@Nullable SplashViewModel splashViewModel) {
        this.mViewModel = splashViewModel;
    }

    @Nullable
    public SplashViewModel getViewModel() {
        return this.mViewModel;
    }

    /* Access modifiers changed, original: protected */
    public void executeBindings() {
        synchronized (this) {
            long j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
    }

    @NonNull
    public static SplashView inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static SplashView inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable DataBindingComponent dataBindingComponent) {
        return (SplashView) DataBindingUtil.inflate(layoutInflater, R.layout.splash_view, viewGroup, z, dataBindingComponent);
    }

    @NonNull
    public static SplashView inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static SplashView inflate(@NonNull LayoutInflater layoutInflater, @Nullable DataBindingComponent dataBindingComponent) {
        return bind(layoutInflater.inflate(R.layout.splash_view, null, false), dataBindingComponent);
    }

    @NonNull
    public static SplashView bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static SplashView bind(@NonNull View view, @Nullable DataBindingComponent dataBindingComponent) {
        if ("layout/splash_view_0".equals(view.getTag())) {
            return new SplashView(dataBindingComponent, view);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("view tag isn't correct on view:");
        stringBuilder.append(view.getTag());
        throw new RuntimeException(stringBuilder.toString());
    }
}
