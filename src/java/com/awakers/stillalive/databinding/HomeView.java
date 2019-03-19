package com.awakers.stillalive.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableBoolean;
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
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.awakers.stillalive.R;
import com.awakers.stillalive.ui.main.home.HomeViewModel;

public class HomeView extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @NonNull
    public final ImageView homeImg;
    private long mDirtyFlags = -1;
    @Nullable
    private HomeViewModel mViewModel;
    private OnClickListenerImpl1 mViewModelOnObjectClickedAndroidViewViewOnClickListener;
    private OnClickListenerImpl mViewModelOnTextClickedAndroidViewViewOnClickListener;
    @NonNull
    private final FrameLayout mboundView0;
    @NonNull
    public final ImageView special;
    @NonNull
    public final TextView textView;

    public static class OnClickListenerImpl1 implements OnClickListener {
        private HomeViewModel value;

        public OnClickListenerImpl1 setValue(HomeViewModel homeViewModel) {
            this.value = homeViewModel;
            return homeViewModel == null ? null : this;
        }

        public void onClick(View view) {
            this.value.onObjectClicked(view);
        }
    }

    public static class OnClickListenerImpl implements OnClickListener {
        private HomeViewModel value;

        public OnClickListenerImpl setValue(HomeViewModel homeViewModel) {
            this.value = homeViewModel;
            return homeViewModel == null ? null : this;
        }

        public void onClick(View view) {
            this.value.onTextClicked(view);
        }
    }

    static {
        sViewsWithIds.put(R.id.special, 3);
    }

    public HomeView(@NonNull DataBindingComponent dataBindingComponent, @NonNull View view) {
        super(dataBindingComponent, view, 2);
        Object[] mapBindings = mapBindings(dataBindingComponent, view, 4, sIncludes, sViewsWithIds);
        this.homeImg = (ImageView) mapBindings[2];
        this.homeImg.setTag(null);
        this.mboundView0 = (FrameLayout) mapBindings[0];
        this.mboundView0.setTag(null);
        this.special = (ImageView) mapBindings[3];
        this.textView = (TextView) mapBindings[1];
        this.textView.setTag(null);
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
        if (3 != i) {
            return false;
        }
        setViewModel((HomeViewModel) obj);
        return true;
    }

    public void setViewModel(@Nullable HomeViewModel homeViewModel) {
        this.mViewModel = homeViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(3);
        super.requestRebind();
    }

    @Nullable
    public HomeViewModel getViewModel() {
        return this.mViewModel;
    }

    /* Access modifiers changed, original: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        switch (i) {
            case 0:
                return onChangeViewModelClickable((ObservableBoolean) obj, i2);
            case 1:
                return onChangeViewModelText((ObservableField) obj, i2);
            default:
                return false;
        }
    }

    private boolean onChangeViewModelClickable(ObservableBoolean observableBoolean, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeViewModelText(ObservableField<String> observableField, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    /* Access modifiers changed, original: protected */
    public void executeBindings() {
        long j;
        long j2;
        Object obj;
        OnClickListener onClickListener;
        OnClickListener onClickListener2;
        CharSequence charSequence;
        synchronized (this) {
            try {
                j = this.mDirtyFlags;
                j2 = 0;
                this.mDirtyFlags = 0;
            } finally {
                j = 
/*
Method generation error in method: com.awakers.stillalive.databinding.HomeView.executeBindings():void, dex: 
jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x009b: MERGE  (r2_5 'j' long) = (r0_1 java.lang.Object), (r6_10 'obj' java.lang.Object) in method: com.awakers.stillalive.databinding.HomeView.executeBindings():void, dex: 
	at jadx.core.a.f.a(InsnGen.java:228)
	at jadx.core.a.f.a(InsnGen.java:205)
	at jadx.core.a.i.a(RegionGen.java:102)
	at jadx.core.a.i.a(RegionGen.java:52)
	at jadx.core.a.i.a(RegionGen.java:89)
	at jadx.core.a.i.a(RegionGen.java:55)
	at jadx.core.a.i.b(RegionGen.java:95)
	at jadx.core.a.i.a(RegionGen.java:300)
	at jadx.core.a.i.a(RegionGen.java:65)
	at jadx.core.a.i.a(RegionGen.java:89)
	at jadx.core.a.i.a(RegionGen.java:55)
	at jadx.core.a.i.a(RegionGen.java:89)
	at jadx.core.a.i.a(RegionGen.java:55)
	at jadx.core.a.i.b(RegionGen.java:95)
	at jadx.core.a.i.a(RegionGen.java:230)
	at jadx.core.a.i.a(RegionGen.java:67)
	at jadx.core.a.i.a(RegionGen.java:89)
	at jadx.core.a.i.a(RegionGen.java:55)
	at jadx.core.a.g.b(MethodGen.java:183)
	at jadx.core.a.b.a(ClassGen.java:321)
	at jadx.core.a.b.d(ClassGen.java:259)
	at jadx.core.a.b.c(ClassGen.java:221)
	at jadx.core.a.b.a(ClassGen.java:111)
	at jadx.core.a.b.b(ClassGen.java:77)
	at jadx.core.a.c.a(CodeGen.java:10)
	at jadx.core.b.a(ProcessClass.java:38)
	at jadx.a.d.a(JadxDecompiler.java:292)
	at jadx.a.e.a(JavaClass.java:62)
	at jadx.a.d.a(JadxDecompiler.java:200)
	at jadx.a.d.lambda$rWZLWBP0-oQoClOyCzw_TOCTGIQ(Unknown Source:0)
	at jadx.a.-$$Lambda$d$rWZLWBP0-oQoClOyCzw_TOCTGIQ.run(Unknown Source:6)
Caused by: jadx.core.utils.exceptions.CodegenException: MERGE can be used only in fallback mode
	at jadx.core.a.f.a(InsnGen.java:539)
	at jadx.core.a.f.a(InsnGen.java:511)
	at jadx.core.a.f.a(InsnGen.java:222)
	... 30 more

*/

    @NonNull
    public static HomeView inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static HomeView inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable DataBindingComponent dataBindingComponent) {
        return (HomeView) DataBindingUtil.inflate(layoutInflater, R.layout.home_view, viewGroup, z, dataBindingComponent);
    }

    @NonNull
    public static HomeView inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static HomeView inflate(@NonNull LayoutInflater layoutInflater, @Nullable DataBindingComponent dataBindingComponent) {
        return bind(layoutInflater.inflate(R.layout.home_view, null, false), dataBindingComponent);
    }

    @NonNull
    public static HomeView bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static HomeView bind(@NonNull View view, @Nullable DataBindingComponent dataBindingComponent) {
        if ("layout/home_view_0".equals(view.getTag())) {
            return new HomeView(dataBindingComponent, view);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("view tag isn't correct on view:");
        stringBuilder.append(view.getTag());
        throw new RuntimeException(stringBuilder.toString());
    }
}
