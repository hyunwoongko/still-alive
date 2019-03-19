package com.awakers.stillalive.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.databinding.adapters.TextViewBindingAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.awakers.stillalive.R;
import com.awakers.stillalive.ui.main.setting.SettingClickHandler;
import com.awakers.stillalive.ui.main.setting.SettingItemModel;

public class SettingItem extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = null;
    @NonNull
    public final TextView capsuleThemeName;
    private long mDirtyFlags = -1;
    @Nullable
    private SettingClickHandler mHandler;
    private OnClickListenerImpl mHandlerItemClickedAndroidViewViewOnClickListener;
    @Nullable
    private SettingItemModel mItemModel;
    @NonNull
    private final LinearLayout mboundView0;

    public static class OnClickListenerImpl implements OnClickListener {
        private SettingClickHandler value;

        public OnClickListenerImpl setValue(SettingClickHandler settingClickHandler) {
            this.value = settingClickHandler;
            return settingClickHandler == null ? null : this;
        }

        public void onClick(View view) {
            this.value.itemClicked(view);
        }
    }

    public SettingItem(@NonNull DataBindingComponent dataBindingComponent, @NonNull View view) {
        super(dataBindingComponent, view, 1);
        Object[] mapBindings = mapBindings(dataBindingComponent, view, 2, sIncludes, sViewsWithIds);
        this.capsuleThemeName = (TextView) mapBindings[1];
        this.capsuleThemeName.setTag(null);
        this.mboundView0 = (LinearLayout) mapBindings[0];
        this.mboundView0.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 8;
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
        if (1 == i) {
            setHandler((SettingClickHandler) obj);
            return true;
        } else if (2 != i) {
            return false;
        } else {
            setItemModel((SettingItemModel) obj);
            return true;
        }
    }

    public void setHandler(@Nullable SettingClickHandler settingClickHandler) {
        this.mHandler = settingClickHandler;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(1);
        super.requestRebind();
    }

    @Nullable
    public SettingClickHandler getHandler() {
        return this.mHandler;
    }

    public void setItemModel(@Nullable SettingItemModel settingItemModel) {
        this.mItemModel = settingItemModel;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(2);
        super.requestRebind();
    }

    @Nullable
    public SettingItemModel getItemModel() {
        return this.mItemModel;
    }

    /* Access modifiers changed, original: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return i != 0 ? false : onChangeItemModelSettingName((ObservableField) obj, i2);
    }

    private boolean onChangeItemModelSettingName(ObservableField<String> observableField, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    /* Access modifiers changed, original: protected */
    public void executeBindings() {
        long j;
        OnClickListener onClickListener;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        SettingClickHandler settingClickHandler = this.mHandler;
        SettingItemModel settingItemModel = this.mItemModel;
        int i = ((10 & j) > 0 ? 1 : ((10 & j) == 0 ? 0 : -1));
        CharSequence charSequence = null;
        if (i == 0 || settingClickHandler == null) {
            onClickListener = null;
        } else {
            OnClickListenerImpl onClickListenerImpl;
            if (this.mHandlerItemClickedAndroidViewViewOnClickListener == null) {
                onClickListenerImpl = new OnClickListenerImpl();
                this.mHandlerItemClickedAndroidViewViewOnClickListener = onClickListenerImpl;
            } else {
                onClickListenerImpl = this.mHandlerItemClickedAndroidViewViewOnClickListener;
            }
            onClickListener = onClickListenerImpl.setValue(settingClickHandler);
        }
        int i2 = ((j & 13) > 0 ? 1 : ((j & 13) == 0 ? 0 : -1));
        if (i2 != 0) {
            Observable observable = settingItemModel != null ? settingItemModel.settingName : null;
            updateRegistration(0, observable);
            if (observable != null) {
                charSequence = (String) observable.get();
            }
        }
        if (i != 0) {
            this.capsuleThemeName.setOnClickListener(onClickListener);
        }
        if (i2 != 0) {
            TextViewBindingAdapter.setText(this.capsuleThemeName, charSequence);
        }
    }

    @NonNull
    public static SettingItem inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static SettingItem inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable DataBindingComponent dataBindingComponent) {
        return (SettingItem) DataBindingUtil.inflate(layoutInflater, R.layout.setting_item, viewGroup, z, dataBindingComponent);
    }

    @NonNull
    public static SettingItem inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static SettingItem inflate(@NonNull LayoutInflater layoutInflater, @Nullable DataBindingComponent dataBindingComponent) {
        return bind(layoutInflater.inflate(R.layout.setting_item, null, false), dataBindingComponent);
    }

    @NonNull
    public static SettingItem bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static SettingItem bind(@NonNull View view, @Nullable DataBindingComponent dataBindingComponent) {
        if ("layout/setting_item_0".equals(view.getTag())) {
            return new SettingItem(dataBindingComponent, view);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("view tag isn't correct on view:");
        stringBuilder.append(view.getTag());
        throw new RuntimeException(stringBuilder.toString());
    }
}
