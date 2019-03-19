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
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.awakers.stillalive.R;
import com.awakers.stillalive.ui.init.greeting.GreetingViewModel;

public class GreetingView extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = null;
    @NonNull
    public final TextView date;
    private long mDirtyFlags = -1;
    @Nullable
    private GreetingViewModel mViewModel;
    @NonNull
    private final LinearLayout mboundView0;
    @NonNull
    private final TextView mboundView3;
    @NonNull
    public final TextView userOrange;

    public GreetingView(@NonNull DataBindingComponent dataBindingComponent, @NonNull View view) {
        super(dataBindingComponent, view, 3);
        Object[] mapBindings = mapBindings(dataBindingComponent, view, 4, sIncludes, sViewsWithIds);
        this.date = (TextView) mapBindings[2];
        this.date.setTag(null);
        this.mboundView0 = (LinearLayout) mapBindings[0];
        this.mboundView0.setTag(null);
        this.mboundView3 = (TextView) mapBindings[3];
        this.mboundView3.setTag(null);
        this.userOrange = (TextView) mapBindings[1];
        this.userOrange.setTag(null);
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
        if (3 != i) {
            return false;
        }
        setViewModel((GreetingViewModel) obj);
        return true;
    }

    public void setViewModel(@Nullable GreetingViewModel greetingViewModel) {
        this.mViewModel = greetingViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(3);
        super.requestRebind();
    }

    @Nullable
    public GreetingViewModel getViewModel() {
        return this.mViewModel;
    }

    /* Access modifiers changed, original: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        switch (i) {
            case 0:
                return onChangeViewModelGreeting1((ObservableField) obj, i2);
            case 1:
                return onChangeViewModelGreeting2((ObservableField) obj, i2);
            case 2:
                return onChangeViewModelDate((ObservableField) obj, i2);
            default:
                return false;
        }
    }

    private boolean onChangeViewModelGreeting1(ObservableField<String> observableField, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeViewModelGreeting2(ObservableField<String> observableField, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeViewModelDate(ObservableField<String> observableField, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    /* Access modifiers changed, original: protected */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0053  */
    public void executeBindings() {
        /*
        r18 = this;
        r1 = r18;
        monitor-enter(r18);
        r2 = r1.mDirtyFlags;	 Catch:{ all -> 0x008b }
        r4 = 0;
        r1.mDirtyFlags = r4;	 Catch:{ all -> 0x008b }
        monitor-exit(r18);	 Catch:{ all -> 0x008b }
        r6 = r1.mViewModel;
        r7 = 31;
        r7 = r7 & r2;
        r7 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1));
        r8 = 28;
        r10 = 26;
        r12 = 25;
        if (r7 == 0) goto L_0x0068;
    L_0x0019:
        r15 = r2 & r12;
        r7 = (r15 > r4 ? 1 : (r15 == r4 ? 0 : -1));
        if (r7 == 0) goto L_0x0032;
    L_0x001f:
        if (r6 == 0) goto L_0x0024;
    L_0x0021:
        r7 = r6.greeting1;
        goto L_0x0025;
    L_0x0024:
        r7 = 0;
    L_0x0025:
        r15 = 0;
        r1.updateRegistration(r15, r7);
        if (r7 == 0) goto L_0x0032;
    L_0x002b:
        r7 = r7.get();
        r7 = (java.lang.String) r7;
        goto L_0x0033;
    L_0x0032:
        r7 = 0;
    L_0x0033:
        r15 = r2 & r10;
        r15 = (r15 > r4 ? 1 : (r15 == r4 ? 0 : -1));
        if (r15 == 0) goto L_0x004c;
    L_0x0039:
        if (r6 == 0) goto L_0x003e;
    L_0x003b:
        r15 = r6.greeting2;
        goto L_0x003f;
    L_0x003e:
        r15 = 0;
    L_0x003f:
        r14 = 1;
        r1.updateRegistration(r14, r15);
        if (r15 == 0) goto L_0x004c;
    L_0x0045:
        r14 = r15.get();
        r14 = (java.lang.String) r14;
        goto L_0x004d;
    L_0x004c:
        r14 = 0;
    L_0x004d:
        r15 = r2 & r8;
        r15 = (r15 > r4 ? 1 : (r15 == r4 ? 0 : -1));
        if (r15 == 0) goto L_0x0066;
    L_0x0053:
        if (r6 == 0) goto L_0x0058;
    L_0x0055:
        r6 = r6.date;
        goto L_0x0059;
    L_0x0058:
        r6 = 0;
    L_0x0059:
        r15 = 2;
        r1.updateRegistration(r15, r6);
        if (r6 == 0) goto L_0x0066;
    L_0x005f:
        r6 = r6.get();
        r6 = (java.lang.String) r6;
        goto L_0x006b;
    L_0x0066:
        r6 = 0;
        goto L_0x006b;
    L_0x0068:
        r6 = 0;
        r7 = 0;
        r14 = 0;
    L_0x006b:
        r8 = r8 & r2;
        r8 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1));
        if (r8 == 0) goto L_0x0075;
    L_0x0070:
        r8 = r1.date;
        android.databinding.adapters.TextViewBindingAdapter.setText(r8, r6);
    L_0x0075:
        r8 = r2 & r10;
        r6 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1));
        if (r6 == 0) goto L_0x0080;
    L_0x007b:
        r6 = r1.mboundView3;
        android.databinding.adapters.TextViewBindingAdapter.setText(r6, r14);
    L_0x0080:
        r2 = r2 & r12;
        r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r2 == 0) goto L_0x008a;
    L_0x0085:
        r2 = r1.userOrange;
        android.databinding.adapters.TextViewBindingAdapter.setText(r2, r7);
    L_0x008a:
        return;
    L_0x008b:
        r0 = move-exception;
        r2 = r0;
        monitor-exit(r18);	 Catch:{ all -> 0x008b }
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.awakers.stillalive.databinding.GreetingView.executeBindings():void");
    }

    @NonNull
    public static GreetingView inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static GreetingView inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable DataBindingComponent dataBindingComponent) {
        return (GreetingView) DataBindingUtil.inflate(layoutInflater, R.layout.greeting_view, viewGroup, z, dataBindingComponent);
    }

    @NonNull
    public static GreetingView inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static GreetingView inflate(@NonNull LayoutInflater layoutInflater, @Nullable DataBindingComponent dataBindingComponent) {
        return bind(layoutInflater.inflate(R.layout.greeting_view, null, false), dataBindingComponent);
    }

    @NonNull
    public static GreetingView bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static GreetingView bind(@NonNull View view, @Nullable DataBindingComponent dataBindingComponent) {
        if ("layout/greeting_view_0".equals(view.getTag())) {
            return new GreetingView(dataBindingComponent, view);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("view tag isn't correct on view:");
        stringBuilder.append(view.getTag());
        throw new RuntimeException(stringBuilder.toString());
    }
}
