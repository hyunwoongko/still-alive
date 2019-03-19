package com.awakers.stillalive.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.awakers.stillalive.R;
import com.awakers.stillalive.ui.main.start.StartClickHandler;
import com.awakers.stillalive.ui.main.start.StartItemModel;

public class StartItem extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags = -1;
    @Nullable
    private StartClickHandler mHandler;
    private OnClickListenerImpl mHandlerItemClickedAndroidViewViewOnClickListener;
    @Nullable
    private StartItemModel mItemModel;
    @NonNull
    private final LinearLayout mboundView0;
    @NonNull
    public final ImageButton startThemeImg;
    @NonNull
    public final TextView stratThemeName;

    public static class OnClickListenerImpl implements OnClickListener {
        private StartClickHandler value;

        public OnClickListenerImpl setValue(StartClickHandler startClickHandler) {
            this.value = startClickHandler;
            return startClickHandler == null ? null : this;
        }

        public void onClick(View view) {
            this.value.itemClicked(view);
        }
    }

    public StartItem(@NonNull DataBindingComponent dataBindingComponent, @NonNull View view) {
        super(dataBindingComponent, view, 2);
        Object[] mapBindings = mapBindings(dataBindingComponent, view, 3, sIncludes, sViewsWithIds);
        this.mboundView0 = (LinearLayout) mapBindings[0];
        this.mboundView0.setTag(null);
        this.startThemeImg = (ImageButton) mapBindings[1];
        this.startThemeImg.setTag(null);
        this.stratThemeName = (TextView) mapBindings[2];
        this.stratThemeName.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 16;
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
            setHandler((StartClickHandler) obj);
            return true;
        } else if (2 != i) {
            return false;
        } else {
            setItemModel((StartItemModel) obj);
            return true;
        }
    }

    public void setHandler(@Nullable StartClickHandler startClickHandler) {
        this.mHandler = startClickHandler;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(1);
        super.requestRebind();
    }

    @Nullable
    public StartClickHandler getHandler() {
        return this.mHandler;
    }

    public void setItemModel(@Nullable StartItemModel startItemModel) {
        this.mItemModel = startItemModel;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(2);
        super.requestRebind();
    }

    @Nullable
    public StartItemModel getItemModel() {
        return this.mItemModel;
    }

    /* Access modifiers changed, original: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        switch (i) {
            case 0:
                return onChangeItemModelThemeName((ObservableField) obj, i2);
            case 1:
                return onChangeItemModelImgSrc((ObservableField) obj, i2);
            default:
                return false;
        }
    }

    private boolean onChangeItemModelThemeName(ObservableField<String> observableField, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeItemModelImgSrc(ObservableField<String> observableField, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    /* Access modifiers changed, original: protected */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0055  */
    public void executeBindings() {
        /*
        r15 = this;
        monitor-enter(r15);
        r0 = r15.mDirtyFlags;	 Catch:{ all -> 0x0087 }
        r2 = 0;
        r15.mDirtyFlags = r2;	 Catch:{ all -> 0x0087 }
        monitor-exit(r15);	 Catch:{ all -> 0x0087 }
        r4 = r15.mHandler;
        r5 = r15.mItemModel;
        r6 = 20;
        r6 = r6 & r0;
        r6 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1));
        r7 = 0;
        if (r6 == 0) goto L_0x0029;
    L_0x0014:
        if (r4 == 0) goto L_0x0029;
    L_0x0016:
        r8 = r15.mHandlerItemClickedAndroidViewViewOnClickListener;
        if (r8 != 0) goto L_0x0022;
    L_0x001a:
        r8 = new com.awakers.stillalive.databinding.StartItem$OnClickListenerImpl;
        r8.<init>();
        r15.mHandlerItemClickedAndroidViewViewOnClickListener = r8;
        goto L_0x0024;
    L_0x0022:
        r8 = r15.mHandlerItemClickedAndroidViewViewOnClickListener;
    L_0x0024:
        r4 = r8.setValue(r4);
        goto L_0x002a;
    L_0x0029:
        r4 = r7;
    L_0x002a:
        r8 = 27;
        r8 = r8 & r0;
        r8 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1));
        r9 = 26;
        r11 = 25;
        if (r8 == 0) goto L_0x0069;
    L_0x0035:
        r13 = r0 & r11;
        r8 = (r13 > r2 ? 1 : (r13 == r2 ? 0 : -1));
        if (r8 == 0) goto L_0x004e;
    L_0x003b:
        if (r5 == 0) goto L_0x0040;
    L_0x003d:
        r8 = r5.themeName;
        goto L_0x0041;
    L_0x0040:
        r8 = r7;
    L_0x0041:
        r13 = 0;
        r15.updateRegistration(r13, r8);
        if (r8 == 0) goto L_0x004e;
    L_0x0047:
        r8 = r8.get();
        r8 = (java.lang.String) r8;
        goto L_0x004f;
    L_0x004e:
        r8 = r7;
    L_0x004f:
        r13 = r0 & r9;
        r13 = (r13 > r2 ? 1 : (r13 == r2 ? 0 : -1));
        if (r13 == 0) goto L_0x006a;
    L_0x0055:
        if (r5 == 0) goto L_0x005a;
    L_0x0057:
        r5 = r5.imgSrc;
        goto L_0x005b;
    L_0x005a:
        r5 = r7;
    L_0x005b:
        r13 = 1;
        r15.updateRegistration(r13, r5);
        if (r5 == 0) goto L_0x006a;
    L_0x0061:
        r5 = r5.get();
        r7 = r5;
        r7 = (java.lang.String) r7;
        goto L_0x006a;
    L_0x0069:
        r8 = r7;
    L_0x006a:
        if (r6 == 0) goto L_0x0071;
    L_0x006c:
        r5 = r15.startThemeImg;
        r5.setOnClickListener(r4);
    L_0x0071:
        r4 = r0 & r9;
        r4 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1));
        if (r4 == 0) goto L_0x007c;
    L_0x0077:
        r4 = r15.startThemeImg;
        com.awakers.stillalive.app.AwakersApplication.setImgSrc(r4, r7);
    L_0x007c:
        r0 = r0 & r11;
        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r0 == 0) goto L_0x0086;
    L_0x0081:
        r0 = r15.stratThemeName;
        android.databinding.adapters.TextViewBindingAdapter.setText(r0, r8);
    L_0x0086:
        return;
    L_0x0087:
        r0 = move-exception;
        monitor-exit(r15);	 Catch:{ all -> 0x0087 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.awakers.stillalive.databinding.StartItem.executeBindings():void");
    }

    @NonNull
    public static StartItem inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static StartItem inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable DataBindingComponent dataBindingComponent) {
        return (StartItem) DataBindingUtil.inflate(layoutInflater, R.layout.start_item, viewGroup, z, dataBindingComponent);
    }

    @NonNull
    public static StartItem inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static StartItem inflate(@NonNull LayoutInflater layoutInflater, @Nullable DataBindingComponent dataBindingComponent) {
        return bind(layoutInflater.inflate(R.layout.start_item, null, false), dataBindingComponent);
    }

    @NonNull
    public static StartItem bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static StartItem bind(@NonNull View view, @Nullable DataBindingComponent dataBindingComponent) {
        if ("layout/start_item_0".equals(view.getTag())) {
            return new StartItem(dataBindingComponent, view);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("view tag isn't correct on view:");
        stringBuilder.append(view.getTag());
        throw new RuntimeException(stringBuilder.toString());
    }
}
