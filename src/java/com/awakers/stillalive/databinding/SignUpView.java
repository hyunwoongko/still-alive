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
import android.widget.Button;
import android.widget.LinearLayout;
import com.awakers.stillalive.R;
import com.awakers.stillalive.ui.sign_up.SignUpViewModel;

public class SignUpView extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = null;
    @NonNull
    public final TextInputEditText address;
    private InverseBindingListener addressandroidTextAttrChanged = new InverseBindingListener() {
        public void onChange() {
            String textString = TextViewBindingAdapter.getTextString(SignUpView.this.address);
            SignUpView.this.mViewModel;
            ObservableField observableField = SignUpViewModel.address;
            if ((observableField != null ? 1 : null) != null) {
                observableField.set(textString);
            }
        }
    };
    @NonNull
    public final Button emailSignInButton;
    @NonNull
    public final TextInputEditText info;
    private InverseBindingListener infoandroidTextAttrChanged = new InverseBindingListener() {
        public void onChange() {
            String textString = TextViewBindingAdapter.getTextString(SignUpView.this.info);
            SignUpView.this.mViewModel;
            ObservableField observableField = SignUpViewModel.info;
            if ((observableField != null ? 1 : null) != null) {
                observableField.set(textString);
            }
        }
    };
    @NonNull
    public final TextInputEditText job;
    private InverseBindingListener jobandroidTextAttrChanged = new InverseBindingListener() {
        public void onChange() {
            String textString = TextViewBindingAdapter.getTextString(SignUpView.this.job);
            SignUpView.this.mViewModel;
            ObservableField observableField = SignUpViewModel.job;
            if ((observableField != null ? 1 : null) != null) {
                observableField.set(textString);
            }
        }
    };
    private long mDirtyFlags = -1;
    @Nullable
    private SignUpViewModel mViewModel;
    private OnClickListenerImpl mViewModelOnSignUpButtonClickedAndroidViewViewOnClickListener;
    @NonNull
    private final LinearLayout mboundView0;
    @NonNull
    public final TextInputEditText name;
    private InverseBindingListener nameandroidTextAttrChanged = new InverseBindingListener() {
        public void onChange() {
            String textString = TextViewBindingAdapter.getTextString(SignUpView.this.name);
            SignUpView.this.mViewModel;
            ObservableField observableField = SignUpViewModel.name;
            if ((observableField != null ? 1 : null) != null) {
                observableField.set(textString);
            }
        }
    };
    @NonNull
    public final TextInputEditText signInEmailEditText;
    private InverseBindingListener signInEmailEditTextandroidTextAttrChanged = new InverseBindingListener() {
        public void onChange() {
            String textString = TextViewBindingAdapter.getTextString(SignUpView.this.signInEmailEditText);
            SignUpViewModel access$000 = SignUpView.this.mViewModel;
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
    @NonNull
    public final TextInputEditText signInNamePasswordCheck;
    private InverseBindingListener signInNamePasswordCheckandroidTextAttrChanged = new InverseBindingListener() {
        public void onChange() {
            String textString = TextViewBindingAdapter.getTextString(SignUpView.this.signInNamePasswordCheck);
            SignUpViewModel access$000 = SignUpView.this.mViewModel;
            Object obj = null;
            if ((access$000 != null ? 1 : null) != null) {
                ObservableField observableField = access$000.passwordCheck;
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
    public final TextInputEditText signInPasswordEditText;
    private InverseBindingListener signInPasswordEditTextandroidTextAttrChanged = new InverseBindingListener() {
        public void onChange() {
            String textString = TextViewBindingAdapter.getTextString(SignUpView.this.signInPasswordEditText);
            SignUpViewModel access$000 = SignUpView.this.mViewModel;
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

    public static class OnClickListenerImpl implements OnClickListener {
        private SignUpViewModel value;

        public OnClickListenerImpl setValue(SignUpViewModel signUpViewModel) {
            this.value = signUpViewModel;
            return signUpViewModel == null ? null : this;
        }

        public void onClick(View view) {
            this.value.onSignUpButtonClicked(view);
        }
    }

    public SignUpView(@NonNull DataBindingComponent dataBindingComponent, @NonNull View view) {
        super(dataBindingComponent, view, 8);
        Object[] mapBindings = mapBindings(dataBindingComponent, view, 9, sIncludes, sViewsWithIds);
        this.address = (TextInputEditText) mapBindings[6];
        this.address.setTag(null);
        this.emailSignInButton = (Button) mapBindings[8];
        this.emailSignInButton.setTag(null);
        this.info = (TextInputEditText) mapBindings[7];
        this.info.setTag(null);
        this.job = (TextInputEditText) mapBindings[5];
        this.job.setTag(null);
        this.mboundView0 = (LinearLayout) mapBindings[0];
        this.mboundView0.setTag(null);
        this.name = (TextInputEditText) mapBindings[4];
        this.name.setTag(null);
        this.signInEmailEditText = (TextInputEditText) mapBindings[1];
        this.signInEmailEditText.setTag(null);
        this.signInNamePasswordCheck = (TextInputEditText) mapBindings[3];
        this.signInNamePasswordCheck.setTag(null);
        this.signInPasswordEditText = (TextInputEditText) mapBindings[2];
        this.signInPasswordEditText.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 512;
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
        setViewModel((SignUpViewModel) obj);
        return true;
    }

    public void setViewModel(@Nullable SignUpViewModel signUpViewModel) {
        this.mViewModel = signUpViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        notifyPropertyChanged(3);
        super.requestRebind();
    }

    @Nullable
    public SignUpViewModel getViewModel() {
        return this.mViewModel;
    }

    /* Access modifiers changed, original: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        switch (i) {
            case 0:
                return onChangeViewModelPasswordCheck((ObservableField) obj, i2);
            case 1:
                return onChangeViewModelEmail((ObservableField) obj, i2);
            case 2:
                return onChangeViewModelAddress((ObservableField) obj, i2);
            case 3:
                return onChangeViewModelName((ObservableField) obj, i2);
            case 4:
                return onChangeViewModelJob((ObservableField) obj, i2);
            case 5:
                return onChangeViewModelClickable((ObservableBoolean) obj, i2);
            case 6:
                return onChangeViewModelPassword((ObservableField) obj, i2);
            case 7:
                return onChangeViewModelInfo((ObservableField) obj, i2);
            default:
                return false;
        }
    }

    private boolean onChangeViewModelPasswordCheck(ObservableField<String> observableField, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeViewModelEmail(ObservableField<String> observableField, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeViewModelAddress(ObservableField<String> observableField, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeViewModelName(ObservableField<String> observableField, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeViewModelJob(ObservableField<String> observableField, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeViewModelClickable(ObservableBoolean observableBoolean, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeViewModelPassword(ObservableField<String> observableField, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    private boolean onChangeViewModelInfo(ObservableField<String> observableField, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        return true;
    }

    /* Access modifiers changed, original: protected */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00e6  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00b8  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x00f4  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x009c  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00b8  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00e6  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x00f4  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x011a  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x016a  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0129  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0178  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x017f  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x0186  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x018d  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x019c  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x01a9  */
    /* JADX WARNING: Removed duplicated region for block: B:107:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x01b6  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x0112  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0079  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x011a  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0129  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x016a  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0178  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x017f  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x0186  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x018d  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x019c  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x01a9  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x01b6  */
    /* JADX WARNING: Removed duplicated region for block: B:107:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0058  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0079  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x0112  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x011a  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x016a  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0129  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0178  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x017f  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x0186  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x018d  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x019c  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x01a9  */
    /* JADX WARNING: Removed duplicated region for block: B:107:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x01b6  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0058  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x0112  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0079  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x011a  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0129  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x016a  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0178  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x017f  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x0186  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x018d  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x019c  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x01a9  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x01b6  */
    /* JADX WARNING: Removed duplicated region for block: B:107:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x002a  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0058  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0079  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x0112  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x011a  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x016a  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0129  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0178  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x017f  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x0186  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x018d  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x019c  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x01a9  */
    /* JADX WARNING: Removed duplicated region for block: B:107:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x01b6  */
    public void executeBindings() {
        /*
        r34 = this;
        r1 = r34;
        monitor-enter(r34);
        r2 = r1.mDirtyFlags;	 Catch:{ all -> 0x01be }
        r4 = 0;
        r1.mDirtyFlags = r4;	 Catch:{ all -> 0x01be }
        monitor-exit(r34);	 Catch:{ all -> 0x01be }
        r6 = r1.mViewModel;
        r7 = 516; // 0x204 float:7.23E-43 double:2.55E-321;
        r7 = r7 & r2;
        r7 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1));
        if (r7 == 0) goto L_0x0022;
    L_0x0013:
        r9 = com.awakers.stillalive.ui.sign_up.SignUpViewModel.address;
        r10 = 2;
        r1.updateRegistration(r10, r9);
        if (r9 == 0) goto L_0x0022;
    L_0x001b:
        r9 = r9.get();
        r9 = (java.lang.String) r9;
        goto L_0x0023;
    L_0x0022:
        r9 = 0;
    L_0x0023:
        r10 = 520; // 0x208 float:7.29E-43 double:2.57E-321;
        r10 = r10 & r2;
        r10 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1));
        if (r10 == 0) goto L_0x0039;
    L_0x002a:
        r11 = com.awakers.stillalive.ui.sign_up.SignUpViewModel.name;
        r12 = 3;
        r1.updateRegistration(r12, r11);
        if (r11 == 0) goto L_0x0039;
    L_0x0032:
        r11 = r11.get();
        r11 = (java.lang.String) r11;
        goto L_0x003a;
    L_0x0039:
        r11 = 0;
    L_0x003a:
        r12 = 528; // 0x210 float:7.4E-43 double:2.61E-321;
        r12 = r12 & r2;
        r12 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1));
        if (r12 == 0) goto L_0x0050;
    L_0x0041:
        r13 = com.awakers.stillalive.ui.sign_up.SignUpViewModel.job;
        r14 = 4;
        r1.updateRegistration(r14, r13);
        if (r13 == 0) goto L_0x0050;
    L_0x0049:
        r13 = r13.get();
        r13 = (java.lang.String) r13;
        goto L_0x0051;
    L_0x0050:
        r13 = 0;
    L_0x0051:
        r14 = 640; // 0x280 float:8.97E-43 double:3.16E-321;
        r14 = r14 & r2;
        r14 = (r14 > r4 ? 1 : (r14 == r4 ? 0 : -1));
        if (r14 == 0) goto L_0x0067;
    L_0x0058:
        r15 = com.awakers.stillalive.ui.sign_up.SignUpViewModel.info;
        r8 = 7;
        r1.updateRegistration(r8, r15);
        if (r15 == 0) goto L_0x0067;
    L_0x0060:
        r8 = r15.get();
        r8 = (java.lang.String) r8;
        goto L_0x0068;
    L_0x0067:
        r8 = 0;
    L_0x0068:
        r16 = 867; // 0x363 float:1.215E-42 double:4.284E-321;
        r16 = r2 & r16;
        r15 = (r16 > r4 ? 1 : (r16 == r4 ? 0 : -1));
        r16 = 832; // 0x340 float:1.166E-42 double:4.11E-321;
        r18 = 800; // 0x320 float:1.121E-42 double:3.953E-321;
        r20 = 770; // 0x302 float:1.079E-42 double:3.804E-321;
        r22 = 769; // 0x301 float:1.078E-42 double:3.8E-321;
        r4 = 0;
        if (r15 == 0) goto L_0x0112;
    L_0x0079:
        r26 = r2 & r22;
        r24 = 0;
        r5 = (r26 > r24 ? 1 : (r26 == r24 ? 0 : -1));
        if (r5 == 0) goto L_0x0093;
    L_0x0081:
        if (r6 == 0) goto L_0x0086;
    L_0x0083:
        r5 = r6.passwordCheck;
        goto L_0x0087;
    L_0x0086:
        r5 = 0;
    L_0x0087:
        r1.updateRegistration(r4, r5);
        if (r5 == 0) goto L_0x0093;
    L_0x008c:
        r5 = r5.get();
        r5 = (java.lang.String) r5;
        goto L_0x0094;
    L_0x0093:
        r5 = 0;
    L_0x0094:
        r26 = r2 & r20;
        r24 = 0;
        r15 = (r26 > r24 ? 1 : (r26 == r24 ? 0 : -1));
        if (r15 == 0) goto L_0x00af;
    L_0x009c:
        if (r6 == 0) goto L_0x00a1;
    L_0x009e:
        r15 = r6.email;
        goto L_0x00a2;
    L_0x00a1:
        r15 = 0;
    L_0x00a2:
        r4 = 1;
        r1.updateRegistration(r4, r15);
        if (r15 == 0) goto L_0x00af;
    L_0x00a8:
        r4 = r15.get();
        r4 = (java.lang.String) r4;
        goto L_0x00b0;
    L_0x00af:
        r4 = 0;
    L_0x00b0:
        r26 = r2 & r18;
        r24 = 0;
        r15 = (r26 > r24 ? 1 : (r26 == r24 ? 0 : -1));
        if (r15 == 0) goto L_0x00e6;
    L_0x00b8:
        if (r6 == 0) goto L_0x00d3;
    L_0x00ba:
        r15 = r1.mViewModelOnSignUpButtonClickedAndroidViewViewOnClickListener;
        if (r15 != 0) goto L_0x00c6;
    L_0x00be:
        r15 = new com.awakers.stillalive.databinding.SignUpView$OnClickListenerImpl;
        r15.<init>();
        r1.mViewModelOnSignUpButtonClickedAndroidViewViewOnClickListener = r15;
        goto L_0x00c8;
    L_0x00c6:
        r15 = r1.mViewModelOnSignUpButtonClickedAndroidViewViewOnClickListener;
    L_0x00c8:
        r15 = r15.setValue(r6);
        r28 = r4;
        r4 = r6.clickable;
        r29 = r5;
        goto L_0x00d9;
    L_0x00d3:
        r28 = r4;
        r29 = r5;
        r4 = 0;
        r15 = 0;
    L_0x00d9:
        r5 = 5;
        r1.updateRegistration(r5, r4);
        if (r4 == 0) goto L_0x00e4;
    L_0x00df:
        r4 = r4.get();
        goto L_0x00ec;
    L_0x00e4:
        r4 = 0;
        goto L_0x00ec;
    L_0x00e6:
        r28 = r4;
        r29 = r5;
        r4 = 0;
        r15 = 0;
    L_0x00ec:
        r26 = r2 & r16;
        r24 = 0;
        r5 = (r26 > r24 ? 1 : (r26 == r24 ? 0 : -1));
        if (r5 == 0) goto L_0x010d;
    L_0x00f4:
        if (r6 == 0) goto L_0x00f9;
    L_0x00f6:
        r5 = r6.password;
        goto L_0x00fa;
    L_0x00f9:
        r5 = 0;
    L_0x00fa:
        r6 = 6;
        r1.updateRegistration(r6, r5);
        if (r5 == 0) goto L_0x010d;
    L_0x0100:
        r5 = r5.get();
        r5 = (java.lang.String) r5;
        r30 = r5;
        r5 = r28;
        r6 = r29;
        goto L_0x0118;
    L_0x010d:
        r5 = r28;
        r6 = r29;
        goto L_0x0116;
    L_0x0112:
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r15 = 0;
    L_0x0116:
        r30 = 0;
    L_0x0118:
        if (r7 == 0) goto L_0x011f;
    L_0x011a:
        r7 = r1.address;
        android.databinding.adapters.TextViewBindingAdapter.setText(r7, r9);
    L_0x011f:
        r26 = 512; // 0x200 float:7.175E-43 double:2.53E-321;
        r26 = r2 & r26;
        r24 = 0;
        r7 = (r26 > r24 ? 1 : (r26 == r24 ? 0 : -1));
        if (r7 == 0) goto L_0x016a;
    L_0x0129:
        r7 = r1.address;
        r31 = r6;
        r9 = 0;
        r6 = r9;
        r6 = (android.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged) r6;
        r32 = r5;
        r5 = r9;
        r5 = (android.databinding.adapters.TextViewBindingAdapter.OnTextChanged) r5;
        r9 = (android.databinding.adapters.TextViewBindingAdapter.AfterTextChanged) r9;
        r33 = r11;
        r11 = r1.addressandroidTextAttrChanged;
        android.databinding.adapters.TextViewBindingAdapter.setTextWatcher(r7, r6, r5, r9, r11);
        r7 = r1.info;
        r11 = r1.infoandroidTextAttrChanged;
        android.databinding.adapters.TextViewBindingAdapter.setTextWatcher(r7, r6, r5, r9, r11);
        r7 = r1.job;
        r11 = r1.jobandroidTextAttrChanged;
        android.databinding.adapters.TextViewBindingAdapter.setTextWatcher(r7, r6, r5, r9, r11);
        r7 = r1.name;
        r11 = r1.nameandroidTextAttrChanged;
        android.databinding.adapters.TextViewBindingAdapter.setTextWatcher(r7, r6, r5, r9, r11);
        r7 = r1.signInEmailEditText;
        r11 = r1.signInEmailEditTextandroidTextAttrChanged;
        android.databinding.adapters.TextViewBindingAdapter.setTextWatcher(r7, r6, r5, r9, r11);
        r7 = r1.signInNamePasswordCheck;
        r11 = r1.signInNamePasswordCheckandroidTextAttrChanged;
        android.databinding.adapters.TextViewBindingAdapter.setTextWatcher(r7, r6, r5, r9, r11);
        r7 = r1.signInPasswordEditText;
        r11 = r1.signInPasswordEditTextandroidTextAttrChanged;
        android.databinding.adapters.TextViewBindingAdapter.setTextWatcher(r7, r6, r5, r9, r11);
        goto L_0x0170;
    L_0x016a:
        r32 = r5;
        r31 = r6;
        r33 = r11;
    L_0x0170:
        r5 = r2 & r18;
        r18 = 0;
        r5 = (r5 > r18 ? 1 : (r5 == r18 ? 0 : -1));
        if (r5 == 0) goto L_0x017d;
    L_0x0178:
        r5 = r1.emailSignInButton;
        android.databinding.adapters.ViewBindingAdapter.setOnClick(r5, r15, r4);
    L_0x017d:
        if (r14 == 0) goto L_0x0184;
    L_0x017f:
        r4 = r1.info;
        android.databinding.adapters.TextViewBindingAdapter.setText(r4, r8);
    L_0x0184:
        if (r12 == 0) goto L_0x018b;
    L_0x0186:
        r4 = r1.job;
        android.databinding.adapters.TextViewBindingAdapter.setText(r4, r13);
    L_0x018b:
        if (r10 == 0) goto L_0x0194;
    L_0x018d:
        r4 = r1.name;
        r8 = r33;
        android.databinding.adapters.TextViewBindingAdapter.setText(r4, r8);
    L_0x0194:
        r4 = r2 & r20;
        r6 = 0;
        r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r4 == 0) goto L_0x01a3;
    L_0x019c:
        r4 = r1.signInEmailEditText;
        r5 = r32;
        android.databinding.adapters.TextViewBindingAdapter.setText(r4, r5);
    L_0x01a3:
        r4 = r2 & r22;
        r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r4 == 0) goto L_0x01b0;
    L_0x01a9:
        r4 = r1.signInNamePasswordCheck;
        r5 = r31;
        android.databinding.adapters.TextViewBindingAdapter.setText(r4, r5);
    L_0x01b0:
        r2 = r2 & r16;
        r2 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1));
        if (r2 == 0) goto L_0x01bd;
    L_0x01b6:
        r2 = r1.signInPasswordEditText;
        r5 = r30;
        android.databinding.adapters.TextViewBindingAdapter.setText(r2, r5);
    L_0x01bd:
        return;
    L_0x01be:
        r0 = move-exception;
        r2 = r0;
        monitor-exit(r34);	 Catch:{ all -> 0x01be }
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.awakers.stillalive.databinding.SignUpView.executeBindings():void");
    }

    @NonNull
    public static SignUpView inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static SignUpView inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable DataBindingComponent dataBindingComponent) {
        return (SignUpView) DataBindingUtil.inflate(layoutInflater, R.layout.sign_up_view, viewGroup, z, dataBindingComponent);
    }

    @NonNull
    public static SignUpView inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static SignUpView inflate(@NonNull LayoutInflater layoutInflater, @Nullable DataBindingComponent dataBindingComponent) {
        return bind(layoutInflater.inflate(R.layout.sign_up_view, null, false), dataBindingComponent);
    }

    @NonNull
    public static SignUpView bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static SignUpView bind(@NonNull View view, @Nullable DataBindingComponent dataBindingComponent) {
        if ("layout/sign_up_view_0".equals(view.getTag())) {
            return new SignUpView(dataBindingComponent, view);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("view tag isn't correct on view:");
        stringBuilder.append(view.getTag());
        throw new RuntimeException(stringBuilder.toString());
    }
}
