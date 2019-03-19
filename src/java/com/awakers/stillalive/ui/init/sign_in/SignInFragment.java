package com.awakers.stillalive.ui.init.sign_in;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.awakers.stillalive.R;
import com.awakers.stillalive.base.BaseFragment;
import com.awakers.stillalive.base.BaseRepository;
import com.awakers.stillalive.data.repo.UserCapsule;
import com.awakers.stillalive.data.repo.UserData;
import com.awakers.stillalive.data.repo.UserTheme;
import com.awakers.stillalive.databinding.SignInView;
import com.awakers.stillalive.ui.init.greeting.GreetingFragment;
import com.awakers.stillalive.ui.sign_up.SignUpActivity;
import com.awakers.stillalive.ui.sign_up.SignUpViewModel;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeProgressDialog;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import es.dmoral.toasty.Toasty;
import java.util.Arrays;
import java.util.Objects;
import javax.inject.Inject;

public class SignInFragment extends BaseFragment implements SignInNavigator {
    public AwesomeProgressDialog dialog;
    private SignInView view;
    @Inject
    SignInViewModel viewModel;

    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        this.view = (SignInView) DataBindingUtil.inflate(layoutInflater, R.layout.sign_in_view, viewGroup, false);
        DaggerSignInComponent.builder().signInFragment(this).signInFactory(new SignInFactory(this)).build().inject(this);
        this.view.setViewModel(this.viewModel);
        return this.view.getRoot();
    }

    public void onDestroyView() {
        this.viewModel.onCleared();
        this.viewModel = null;
        this.view = null;
        super.onDestroyView();
    }

    public void replaceToSignUp(boolean z) {
        ((FragmentActivity) Objects.requireNonNull(getActivity())).startActivity(new Intent(getActivity(), SignUpActivity.class));
        getActivity().overridePendingTransition(17432576, 17432577);
    }

    public boolean isValid(String str, String str2) {
        if (str.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(str).matches()) {
            this.view.email.setError("유효한 이메일을 입력해주세요.");
            requestFocus(this.view.email);
            return false;
        }
        this.view.email.setError(null);
        if (str2.isEmpty()) {
            this.view.password.setError("유효한 패스워드를 입력해주세요.");
            requestFocus(this.view.password);
            return false;
        }
        this.view.password.setError(null);
        return true;
    }

    public void requestFocus(View view) {
        if (view.requestFocus()) {
            ((FragmentActivity) Objects.requireNonNull(getActivity())).getWindow().setSoftInputMode(5);
        }
    }

    public void loginToServer(String str, String str2) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(str, str2).addOnCompleteListener((Activity) Objects.requireNonNull(getActivity()), new SignInFragment$$Lambda$0(this));
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void lambda$loginToServer$0$SignInFragment(Task task) {
        if (task.isSuccessful()) {
            serverToModel();
            return;
        }
        Toasty.error((Context) Objects.requireNonNull(getContext()), "로그인에 실패했습니다.", 0).show();
        this.viewModel.loginInterrupted();
    }

    public synchronized void serverToModel() {
        UserData.get().setUid(((FirebaseUser) Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser())).getUid());
        UserData.get().setEmail(FirebaseAuth.getInstance().getCurrentUser().getEmail());
        api().get("userData", UserData.class, new SignInFragment$$Lambda$1(this));
        api().get("userTheme", UserTheme.class, new SignInFragment$$Lambda$2(this));
        api().get("userCapsule", UserCapsule.class, new SignInFragment$$Lambda$3(this));
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void lambda$serverToModel$1$SignInFragment(BaseRepository baseRepository) {
        UserData.set((UserData) baseRepository);
        try {
            UserData.get().setUid(FirebaseAuth.getInstance().getCurrentUser().getUid());
            UserData.get().setEmail(FirebaseAuth.getInstance().getCurrentUser().getEmail());
            if (SignUpViewModel.name != null) {
                UserData.get().setName((String) SignUpViewModel.name.get());
            }
            if (SignUpViewModel.address != null) {
                UserData.get().setAddress((String) SignUpViewModel.address.get());
            }
            if (SignUpViewModel.job != null) {
                UserData.get().setJob((String) SignUpViewModel.job.get());
            }
            if (SignUpViewModel.info != null) {
                UserData.get().setInfo((String) SignUpViewModel.info.get());
            }
        } catch (NullPointerException unused) {
            Process.killProcess(Process.myPid());
            System.exit(1);
        }
        api().set("userData", UserData.get());
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void lambda$serverToModel$2$SignInFragment(BaseRepository baseRepository) {
        UserTheme.set((UserTheme) baseRepository);
        if (UserTheme.get().getUserTheme() == null) {
            UserTheme.get().setUserTheme(Arrays.asList(new String[]{"Tree"}));
        }
        api().set("userTheme", UserTheme.get());
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void lambda$serverToModel$3$SignInFragment(BaseRepository baseRepository) {
        UserCapsule.set((UserCapsule) baseRepository);
        try {
            Toasty.success((Context) Objects.requireNonNull(getContext()), "로그인에 성공했습니다.", 0).show();
            this.viewModel.loginInterrupted();
            replace((BaseFragment) this, (int) R.id.init_container, (Fragment) new GreetingFragment(), false);
            showAnim(getActivity());
        } catch (NullPointerException unused) {
        }
    }

    public void showProgress() {
        if (this.dialog == null) {
            this.dialog = new AwesomeProgressDialog(getActivity());
        }
        this.dialog.setTitle((CharSequence) "로그인");
        this.dialog.setMessage((CharSequence) "로그인중입니다.");
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
