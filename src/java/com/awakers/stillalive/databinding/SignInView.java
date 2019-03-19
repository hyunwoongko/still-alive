package com.awakers.stillalive.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.InverseBindingListener;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.databinding.adapters.TextViewBindingAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.awakers.stillalive.R;
import com.awakers.stillalive.ui.init.sign_in.SignInViewModel;

public class SignInView extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = null;
    @NonNull
    public final AutoCompleteTextView email;
    @NonNull
    public final LinearLayout emailLoginForm;
    @NonNull
    public final Button emailSignInButton;
    private InverseBindingListener emailandroidTextAttrChanged = new InverseBindingListener() {
        public void onChange() {
            String textString = TextViewBindingAdapter.getTextString(SignInView.this.email);
            SignInViewModel access$000 = SignInView.this.mViewModel;
            Object obj = null;
            if ((access$000 != null ? 1 : null) != null) {
                ObservableField observableField = access$000.email;
                if (observableField != null) {
                    obj = 1;
                }
                if (obj != null) {
                    observableField.set(textString);
                }
            }
        }
    };
    private long mDirtyFlags = -1;
    @Nullable
    private SignInViewModel mViewModel;
    private OnClickListenerImpl mViewModelOnLoginButtonClickedAndroidViewViewOnClickListener;
    private OnClickListenerImpl1 mViewModelOnSignUpButtonClickedAndroidViewViewOnClickListener;
    @NonNull
    private final TextView mboundView4;
    @NonNull
    public final TextInputEditText password;
    private InverseBindingListener passwordandroidTextAttrChanged = new InverseBindingListener() {
        public void onChange() {
            String textString = TextViewBindingAdapter.getTextString(SignInView.this.password);
            SignInViewModel access$000 = SignInView.this.mViewModel;
            Object obj = null;
            if ((access$000 != null ? 1 : null) != null) {
                ObservableField observableField = access$000.password;
                if (observableField != null) {
                    obj = 1;
                }
                if (obj != null) {
                    observableField.set(textString);
                }
            }
        }
    };

    public static class OnClickListenerImpl1 implements OnClickListener {
        private SignInViewModel value;

        public OnClickListenerImpl1 setValue(SignInViewModel signInViewModel) {
            this.value = signInViewModel;
            return signInViewModel == null ? null : this;
        }

        public void onClick(View view) {
            this.value.onSignUpButtonClicked(view);
        }
    }

    public static class OnClickListenerImpl implements OnClickListener {
        private SignInViewModel value;

        public OnClickListenerImpl setValue(SignInViewModel signInViewModel) {
            this.value = signInViewModel;
            return signInViewModel == null ? null : this;
        }

        public void onClick(View view) {
            this.value.onLoginButtonClicked(view);
        }
    }

    public SignInView(@NonNull DataBindingComponent dataBindingComponent, @NonNull View view) {
        super(dataBindingComponent, view, 3);
        Object[] mapBindings = mapBindings(dataBindingComponent, view, 5, sIncludes, sViewsWithIds);
        this.email = (AutoCompleteTextView) mapBindings[1];
        this.email.setTag(null);
        this.emailLoginForm = (LinearLayout) mapBindings[0];
        this.emailLoginForm.setTag(null);
        this.emailSignInButton = (Button) mapBindings[3];
        this.emailSignInButton.setTag(null);
        this.mboundView4 = (TextView) mapBindings[4];
        this.mboundView4.setTag(null);
        this.password = (TextInputEditText) mapBindings[2];
        this.password.setTag(null);
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
        setViewModel((SignInViewModel) obj);
        return true;
    }

    public void setViewModel(@Nullable SignInViewModel signInViewModel) {
        this.mViewModel = signInViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(3);
        super.requestRebind();
    }

    @Nullable
    public SignInViewModel getViewModel() {
        return this.mViewModel;
    }

    /* Access modifiers changed, original: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        switch (i) {
            case 0:
                return onChangeViewModelEmail((ObservableField) obj, i2);
            case 1:
                return onChangeViewModelClickable((ObservableBoolean) obj, i2);
            case 2:
                return onChangeViewModelPassword((ObservableField) obj, i2);
            default:
                return false;
        }
    }

    private boolean onChangeViewModelEmail(ObservableField<String> observableField, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeViewModelClickable(ObservableBoolean observableBoolean, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeViewModelPassword(ObservableField<String> observableField, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    /* Access modifiers changed, original: protected */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00a9  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00b5  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00d2  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00de  */
    /* JADX WARNING: Removed duplicated region for block: B:73:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x00ea  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0087  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00a9  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00b5  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00d2  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00de  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x00ea  */
    /* JADX WARNING: Removed duplicated region for block: B:73:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0069 A:{SKIP} */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0087  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00a9  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00b5  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00d2  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00de  */
    /* JADX WARNING: Removed duplicated region for block: B:73:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x00ea  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00a9  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00b5  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00d2  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00de  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x00ea  */
    /* JADX WARNING: Removed duplicated region for block: B:73:? A:{SYNTHETIC, RETURN} */
    public void executeBindings() {
        /*
        r22 = this;
        r1 = r22;
        monitor-enter(r22);
        r2 = r1.mDirtyFlags;	 Catch:{ all -> 0x00f0 }
        r4 = 0;
        r1.mDirtyFlags = r4;	 Catch:{ all -> 0x00f0 }
        monitor-exit(r22);	 Catch:{ all -> 0x00f0 }
        r6 = r1.mViewModel;
        r7 = 31;
        r7 = r7 & r2;
        r7 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1));
        r10 = 24;
        r12 = 26;
        r14 = 25;
        r8 = 0;
        r9 = 0;
        if (r7 == 0) goto L_0x009f;
    L_0x001b:
        r18 = r2 & r14;
        r7 = (r18 > r4 ? 1 : (r18 == r4 ? 0 : -1));
        if (r7 == 0) goto L_0x0033;
    L_0x0021:
        if (r6 == 0) goto L_0x0026;
    L_0x0023:
        r7 = r6.email;
        goto L_0x0027;
    L_0x0026:
        r7 = r9;
    L_0x0027:
        r1.updateRegistration(r8, r7);
        if (r7 == 0) goto L_0x0033;
    L_0x002c:
        r7 = r7.get();
        r7 = (java.lang.String) r7;
        goto L_0x0034;
    L_0x0033:
        r7 = r9;
    L_0x0034:
        r18 = r2 & r12;
        r18 = (r18 > r4 ? 1 : (r18 == r4 ? 0 : -1));
        if (r18 == 0) goto L_0x0060;
    L_0x003a:
        if (r6 == 0) goto L_0x0051;
    L_0x003c:
        r8 = r1.mViewModelOnLoginButtonClickedAndroidViewViewOnClickListener;
        if (r8 != 0) goto L_0x0048;
    L_0x0040:
        r8 = new com.awakers.stillalive.databinding.SignInView$OnClickListenerImpl;
        r8.<init>();
        r1.mViewModelOnLoginButtonClickedAndroidViewViewOnClickListener = r8;
        goto L_0x004a;
    L_0x0048:
        r8 = r1.mViewModelOnLoginButtonClickedAndroidViewViewOnClickListener;
    L_0x004a:
        r8 = r8.setValue(r6);
        r12 = r6.clickable;
        goto L_0x0053;
    L_0x0051:
        r8 = r9;
        r12 = r8;
    L_0x0053:
        r13 = 1;
        r1.updateRegistration(r13, r12);
        if (r12 == 0) goto L_0x0061;
    L_0x0059:
        r12 = r12.get();
        r20 = r12;
        goto L_0x0063;
    L_0x0060:
        r8 = r9;
    L_0x0061:
        r20 = 0;
    L_0x0063:
        r12 = r2 & r10;
        r12 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1));
        if (r12 == 0) goto L_0x007e;
    L_0x0069:
        if (r6 == 0) goto L_0x007e;
    L_0x006b:
        r12 = r1.mViewModelOnSignUpButtonClickedAndroidViewViewOnClickListener;
        if (r12 != 0) goto L_0x0077;
    L_0x006f:
        r12 = new com.awakers.stillalive.databinding.SignInView$OnClickListenerImpl1;
        r12.<init>();
        r1.mViewModelOnSignUpButtonClickedAndroidViewViewOnClickListener = r12;
        goto L_0x0079;
    L_0x0077:
        r12 = r1.mViewModelOnSignUpButtonClickedAndroidViewViewOnClickListener;
    L_0x0079:
        r12 = r12.setValue(r6);
        goto L_0x007f;
    L_0x007e:
        r12 = r9;
    L_0x007f:
        r16 = 28;
        r18 = r2 & r16;
        r13 = (r18 > r4 ? 1 : (r18 == r4 ? 0 : -1));
        if (r13 == 0) goto L_0x009b;
    L_0x0087:
        if (r6 == 0) goto L_0x008c;
    L_0x0089:
        r6 = r6.password;
        goto L_0x008d;
    L_0x008c:
        r6 = r9;
    L_0x008d:
        r13 = 2;
        r1.updateRegistration(r13, r6);
        if (r6 == 0) goto L_0x009b;
    L_0x0093:
        r6 = r6.get();
        r6 = (java.lang.String) r6;
        r13 = r6;
        goto L_0x009c;
    L_0x009b:
        r13 = r9;
    L_0x009c:
        r6 = r20;
        goto L_0x00a4;
    L_0x009f:
        r7 = r9;
        r8 = r7;
        r12 = r8;
        r13 = r12;
        r6 = 0;
    L_0x00a4:
        r14 = r14 & r2;
        r14 = (r14 > r4 ? 1 : (r14 == r4 ? 0 : -1));
        if (r14 == 0) goto L_0x00ae;
    L_0x00a9:
        r14 = r1.email;
        android.databinding.adapters.TextViewBindingAdapter.setText(r14, r7);
    L_0x00ae:
        r14 = 16;
        r14 = r14 & r2;
        r7 = (r14 > r4 ? 1 : (r14 == r4 ? 0 : -1));
        if (r7 == 0) goto L_0x00cb;
    L_0x00b5:
        r7 = r1.email;
        r14 = r9;
        r14 = (android.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged) r14;
        r15 = r9;
        r15 = (android.databinding.adapters.TextViewBindingAdapter.OnTextChanged) r15;
        r9 = (android.databinding.adapters.TextViewBindingAdapter.AfterTextChanged) r9;
        r10 = r1.emailandroidTextAttrChanged;
        android.databinding.adapters.TextViewBindingAdapter.setTextWatcher(r7, r14, r15, r9, r10);
        r7 = r1.password;
        r10 = r1.passwordandroidTextAttrChanged;
        android.databinding.adapters.TextViewBindingAdapter.setTextWatcher(r7, r14, r15, r9, r10);
    L_0x00cb:
        r9 = 26;
        r9 = r9 & r2;
        r7 = (r9 > r4 ? 1 : (r9 == r4 ? 0 : -1));
        if (r7 == 0) goto L_0x00d7;
    L_0x00d2:
        r7 = r1.emailSignInButton;
        android.databinding.adapters.ViewBindingAdapter.setOnClick(r7, r8, r6);
    L_0x00d7:
        r6 = 24;
        r6 = r6 & r2;
        r6 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1));
        if (r6 == 0) goto L_0x00e3;
    L_0x00de:
        r6 = r1.mboundView4;
        r6.setOnClickListener(r12);
    L_0x00e3:
        r6 = 28;
        r2 = r2 & r6;
        r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r2 == 0) goto L_0x00ef;
    L_0x00ea:
        r2 = r1.password;
        android.databinding.adapters.TextViewBindingAdapter.setText(r2, r13);
    L_0x00ef:
        return;
    L_0x00f0:
        r0 = move-exception;
        r2 = r0;
        monitor-exit(r22);	 Catch:{ all -> 0x00f0 }
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.awakers.stillalive.databinding.SignInView.executeBindings():void");
    }

    @NonNull
    public static SignInView inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static SignInView inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable DataBindingComponent dataBindingComponent) {
        return (SignInView) DataBindingUtil.inflate(layoutInflater, R.layout.sign_in_view, viewGroup, z, dataBindingComponent);
    }

    @NonNull
    public static SignInView inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static SignInView inflate(@NonNull LayoutInflater layoutInflater, @Nullable DataBindingComponent dataBindingComponent) {
        return bind(layoutInflater.inflate(R.layout.sign_in_view, null, false), dataBindingComponent);
    }

    @NonNull
    public static SignInView bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static SignInView bind(@NonNull View view, @Nullable DataBindingComponent dataBindingComponent) {
        if ("layout/sign_in_view_0".equals(view.getTag())) {
            return new SignInView(dataBindingComponent, view);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("view tag isn't correct on view:");
        stringBuilder.append(view.getTag());
        throw new RuntimeException(stringBuilder.toString());
    }
}
