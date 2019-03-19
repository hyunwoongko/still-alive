package com.awakers.stillalive.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import com.awakers.stillalive.R;
import com.awakers.stillalive.ui.theme.ThemeViewModel;

public class ThemeView extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags = -1;
    @Nullable
    private ThemeViewModel mViewModel;
    private OnClickListenerImpl mViewModelConnectWithThemeShopAndroidViewViewOnClickListener;
    @NonNull
    private final LinearLayout mboundView0;
    @NonNull
    public final RecyclerView recycler;
    @NonNull
    public final Button shopButton;

    public static class OnClickListenerImpl implements OnClickListener {
        private ThemeViewModel value;

        public OnClickListenerImpl setValue(ThemeViewModel themeViewModel) {
            this.value = themeViewModel;
            return themeViewModel == null ? null : this;
        }

        public void onClick(View view) {
            this.value.connectWithThemeShop(view);
        }
    }

    /* Access modifiers changed, original: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    static {
        sViewsWithIds.put(R.id.recycler, 2);
    }

    public ThemeView(@NonNull DataBindingComponent dataBindingComponent, @NonNull View view) {
        super(dataBindingComponent, view, 0);
        Object[] mapBindings = mapBindings(dataBindingComponent, view, 3, sIncludes, sViewsWithIds);
        this.mboundView0 = (LinearLayout) mapBindings[0];
        this.mboundView0.setTag(null);
        this.recycler = (RecyclerView) mapBindings[2];
        this.shopButton = (Button) mapBindings[1];
        this.shopButton.setTag(null);
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
        setViewModel((ThemeViewModel) obj);
        return true;
    }

    public void setViewModel(@Nullable ThemeViewModel themeViewModel) {
        this.mViewModel = themeViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(3);
        super.requestRebind();
    }

    @Nullable
    public ThemeViewModel getViewModel() {
        return this.mViewModel;
    }

    /* Access modifiers changed, original: protected */
    public void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        OnClickListener onClickListener = null;
        ThemeViewModel themeViewModel = this.mViewModel;
        int i = ((j & 3) > 0 ? 1 : ((j & 3) == 0 ? 0 : -1));
        if (!(i == 0 || themeViewModel == null)) {
            OnClickListenerImpl onClickListenerImpl;
            if (this.mViewModelConnectWithThemeShopAndroidViewViewOnClickListener == null) {
                onClickListenerImpl = new OnClickListenerImpl();
                this.mViewModelConnectWithThemeShopAndroidViewViewOnClickListener = onClickListenerImpl;
            } else {
                onClickListenerImpl = this.mViewModelConnectWithThemeShopAndroidViewViewOnClickListener;
            }
            onClickListener = onClickListenerImpl.setValue(themeViewModel);
        }
        if (i != 0) {
            this.shopButton.setOnClickListener(onClickListener);
        }
    }

    @NonNull
    public static ThemeView inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ThemeView inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable DataBindingComponent dataBindingComponent) {
        return (ThemeView) DataBindingUtil.inflate(layoutInflater, R.layout.theme_view, viewGroup, z, dataBindingComponent);
    }

    @NonNull
    public static ThemeView inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ThemeView inflate(@NonNull LayoutInflater layoutInflater, @Nullable DataBindingComponent dataBindingComponent) {
        return bind(layoutInflater.inflate(R.layout.theme_view, null, false), dataBindingComponent);
    }

    @NonNull
    public static ThemeView bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ThemeView bind(@NonNull View view, @Nullable DataBindingComponent dataBindingComponent) {
        if ("layout/theme_view_0".equals(view.getTag())) {
            return new ThemeView(dataBindingComponent, view);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("view tag isn't correct on view:");
        stringBuilder.append(view.getTag());
        throw new RuntimeException(stringBuilder.toString());
    }
}
