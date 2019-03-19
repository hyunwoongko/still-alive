package com.awakers.stillalive.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.InverseBindingListener;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.databinding.adapters.TextViewBindingAdapter;
import android.databinding.adapters.TextViewBindingAdapter.AfterTextChanged;
import android.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged;
import android.databinding.adapters.TextViewBindingAdapter.OnTextChanged;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.awakers.stillalive.R;
import com.awakers.stillalive.ui.main.start.StartViewModel;

public class StartView extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags = -1;
    @Nullable
    private StartViewModel mViewModel;
    private OnClickListenerImpl mViewModelOnStartButtonClickedAndroidViewViewOnClickListener;
    @NonNull
    private final LinearLayout mboundView0;
    @NonNull
    private final EditText mboundView1;
    private InverseBindingListener mboundView1androidTextAttrChanged = new InverseBindingListener() {
        public void onChange() {
            String textString = TextViewBindingAdapter.getTextString(StartView.this.mboundView1);
            StartViewModel access$100 = StartView.this.mViewModel;
            Object obj = null;
            if ((access$100 != null ? 1 : null) != null) {
                ObservableField observableField = access$100.capsuleName;
                if (observableField != null) {
                    obj = 1;
                }
                if (obj != null) {
                    observableField.set(textString);
                }
            }
        }
    };
    @NonNull
    private final Button mboundView2;
    @NonNull
    public final RecyclerView recycler;

    public static class OnClickListenerImpl implements OnClickListener {
        private StartViewModel value;

        public OnClickListenerImpl setValue(StartViewModel startViewModel) {
            this.value = startViewModel;
            return startViewModel == null ? null : this;
        }

        public void onClick(View view) {
            this.value.onStartButtonClicked(view);
        }
    }

    static {
        sViewsWithIds.put(R.id.recycler, 3);
    }

    public StartView(@NonNull DataBindingComponent dataBindingComponent, @NonNull View view) {
        super(dataBindingComponent, view, 1);
        Object[] mapBindings = mapBindings(dataBindingComponent, view, 4, sIncludes, sViewsWithIds);
        this.mboundView0 = (LinearLayout) mapBindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (EditText) mapBindings[1];
        this.mboundView1.setTag(null);
        this.mboundView2 = (Button) mapBindings[2];
        this.mboundView2.setTag(null);
        this.recycler = (RecyclerView) mapBindings[3];
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4;
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
        setViewModel((StartViewModel) obj);
        return true;
    }

    public void setViewModel(@Nullable StartViewModel startViewModel) {
        this.mViewModel = startViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(3);
        super.requestRebind();
    }

    @Nullable
    public StartViewModel getViewModel() {
        return this.mViewModel;
    }

    /* Access modifiers changed, original: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return i != 0 ? false : onChangeViewModelCapsuleName((ObservableField) obj, i2);
    }

    private boolean onChangeViewModelCapsuleName(ObservableField<String> observableField, int i) {
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
        CharSequence charSequence;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        StartViewModel startViewModel = this.mViewModel;
        int i = ((7 & j) > 0 ? 1 : ((7 & j) == 0 ? 0 : -1));
        if (i != 0) {
            if ((j & 6) == 0 || startViewModel == null) {
                onClickListener = null;
            } else {
                OnClickListenerImpl onClickListenerImpl;
                if (this.mViewModelOnStartButtonClickedAndroidViewViewOnClickListener == null) {
                    onClickListenerImpl = new OnClickListenerImpl();
                    this.mViewModelOnStartButtonClickedAndroidViewViewOnClickListener = onClickListenerImpl;
                } else {
                    onClickListenerImpl = this.mViewModelOnStartButtonClickedAndroidViewViewOnClickListener;
                }
                onClickListener = onClickListenerImpl.setValue(startViewModel);
            }
            Observable observable = startViewModel != null ? startViewModel.capsuleName : null;
            updateRegistration(0, observable);
            charSequence = observable != null ? (String) observable.get() : null;
        } else {
            charSequence = null;
            onClickListener = charSequence;
        }
        if (i != 0) {
            TextViewBindingAdapter.setText(this.mboundView1, charSequence);
        }
        if ((4 & j) != 0) {
            TextViewBindingAdapter.setTextWatcher(this.mboundView1, (BeforeTextChanged) null, (OnTextChanged) null, (AfterTextChanged) null, this.mboundView1androidTextAttrChanged);
        }
        if ((j & 6) != 0) {
            this.mboundView2.setOnClickListener(onClickListener);
        }
    }

    @NonNull
    public static StartView inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static StartView inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable DataBindingComponent dataBindingComponent) {
        return (StartView) DataBindingUtil.inflate(layoutInflater, R.layout.start_view, viewGroup, z, dataBindingComponent);
    }

    @NonNull
    public static StartView inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static StartView inflate(@NonNull LayoutInflater layoutInflater, @Nullable DataBindingComponent dataBindingComponent) {
        return bind(layoutInflater.inflate(R.layout.start_view, null, false), dataBindingComponent);
    }

    @NonNull
    public static StartView bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static StartView bind(@NonNull View view, @Nullable DataBindingComponent dataBindingComponent) {
        if ("layout/start_view_0".equals(view.getTag())) {
            return new StartView(dataBindingComponent, view);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("view tag isn't correct on view:");
        stringBuilder.append(view.getTag());
        throw new RuntimeException(stringBuilder.toString());
    }
}
