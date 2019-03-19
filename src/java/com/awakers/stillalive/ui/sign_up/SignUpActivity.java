package com.awakers.stillalive.ui.sign_up;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Patterns;
import com.awakers.stillalive.R;
import com.awakers.stillalive.base.BaseActivity;
import com.awakers.stillalive.databinding.SignUpView;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeProgressDialog;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import es.dmoral.toasty.Toasty;
import java.util.Objects;
import javax.inject.Inject;

public class SignUpActivity extends BaseActivity implements SignUpNavigator {
    public AwesomeProgressDialog dialog;
    private SignUpView view;
    @Inject
    SignUpViewModel viewModel;

    /* Access modifiers changed, original: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.view = (SignUpView) DataBindingUtil.setContentView(this, R.layout.sign_up_view);
        DaggerSignUpComponent.builder().signUpActivity(this).signUpFactory(new SignUpFactory(this)).build().inject(this);
        this.view.setViewModel(this.viewModel);
    }

    /* Access modifiers changed, original: protected */
    public void onDestroy() {
        super.onDestroy();
        this.viewModel.onCleared();
        this.viewModel = null;
        this.view = null;
    }

    public void saveToServerDB(String str, String str2) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(str2, str).addOnCompleteListener((Activity) Objects.requireNonNull(this), new SignUpActivity$$Lambda$0(this));
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void lambda$saveToServerDB$0$SignUpActivity(Task task) {
        if (task.isSuccessful()) {
            Toasty.success((Context) Objects.requireNonNull(this), "회원가입에 성공했습니다.", 0).show();
            this.viewModel.loginInterrupted();
            finish();
            showAnim(this);
            return;
        }
        Toasty.error((Context) Objects.requireNonNull(this), "네트워크 불량 혹은 중복된 이메일입니다.", 0).show();
        this.viewModel.loginInterrupted();
    }

    public boolean isValid(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        if (str.trim().isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(str).matches()) {
            this.view.signInEmailEditText.setError("유효한 이메일을 입력해주세요.");
            return false;
        }
        this.view.signInEmailEditText.setError(null);
        if (str2.trim().isEmpty() || str2.length() < 6) {
            this.view.signInPasswordEditText.setError("최소 6자리이상의 비밀번호를 입력해주세요.");
            return false;
        }
        this.view.signInPasswordEditText.setError(null);
        if (str3.trim().isEmpty() || str3.length() < 6 || !str3.equals(str2)) {
            this.view.signInNamePasswordCheck.setError("패스워드가 일치하지 않습니다.");
            return false;
        }
        this.view.signInNamePasswordCheck.setError(null);
        if (str4.isEmpty() || str4.trim().equals("") || str4.trim().length() == 0) {
            this.view.name.setError("이름을 입력해주세요.");
            return false;
        }
        this.view.name.setError(null);
        if (str6.isEmpty() || str6.trim().equals("") || str6.trim().length() == 0) {
            this.view.info.setError("한줄로 자신을 소개해보는건 어떨까요?");
            return false;
        }
        this.view.info.setError(null);
        if (str5.isEmpty() || str5.trim().equals("") || str5.trim().length() == 0) {
            this.view.job.setError("직업을 입력해주세요.");
            return false;
        }
        this.view.job.setError(null);
        if (str7.isEmpty() || str7.trim().equals("") || str7.trim().length() == 0) {
            this.view.address.setError("사는 곳을 입력해주세요.");
            return false;
        }
        this.view.address.setError(null);
        return true;
    }

    public void showProgress() {
        if (this.dialog == null) {
            this.dialog = new AwesomeProgressDialog(this);
        }
        this.dialog.setTitle((CharSequence) "회원가입");
        this.dialog.setMessage((CharSequence) "서버에 등록중입니다.");
        this.dialog.setColoredCircle(2131099689);
        this.dialog.setCancelable(false);
        this.dialog.show();
    }

    public void dismissProgress() {
        if (this.dialog != null) {
            this.dialog.hide();
        }
    }
}
