package com.awakers.stillalive.ui.main.setting;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.awakers.stillalive.R;
import com.awakers.stillalive.base.BaseFragment;
import com.awakers.stillalive.databinding.SettingView;
import com.awakers.stillalive.ui.init.InitActivity;
import com.awakers.stillalive.ui.pop_up.PopUpActivity;
import com.awakers.stillalive.ui.splash.SplashActivity;
import com.awakers.stillalive.ui.theme.ThemeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Objects;
import java.util.Vector;
import javax.inject.Inject;

public class SettingFragment extends BaseFragment implements SettingNavigator {
    private SettingView view;
    @Inject
    SettingViewModel viewModel;

    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        this.view = (SettingView) DataBindingUtil.inflate(layoutInflater, R.layout.setting_view, viewGroup, false);
        DaggerSettingComponent.builder().settingFactory(new SettingFactory(this)).settingFragment(this).build().inject(this);
        this.view.setViewModel(this.viewModel);
        settingList();
        return this.view.getRoot();
    }

    public void onDestroyView() {
        this.viewModel.onCleared();
        this.viewModel = null;
        this.view = null;
        super.onDestroyView();
    }

    private synchronized void settingList() {
        Vector vector = new Vector();
        SettingItemModel settingItemModel = new SettingItemModel();
        settingItemModel.settingName.set("보유 테마 보기");
        vector.add(settingItemModel);
        settingItemModel = new SettingItemModel();
        settingItemModel.settingName.set("어플리케이션 정보");
        vector.add(settingItemModel);
        settingItemModel = new SettingItemModel();
        settingItemModel.settingName.set("로그아웃 하기");
        vector.add(settingItemModel);
        settingItemModel = new SettingItemModel();
        settingItemModel.settingName.set("회원 탈퇴");
        vector.add(settingItemModel);
        this.view.recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        this.view.recycler.setAdapter(new SettingAdapter(getContext(), vector, this.viewModel));
        this.view.recycler.addItemDecoration(new DividerItemDecoration((Context) Objects.requireNonNull(getContext()), 1));
    }

    public void logout() {
        startActivity(new Intent(getContext(), InitActivity.class));
        showAnim(getActivity());
        getActivity().finish();
        showAnim(getActivity());
    }

    public synchronized void withdrawal() {
        final String uid = ((FirebaseUser) Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser())).getUid();
        FirebaseAuth.getInstance().getCurrentUser().delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            public void onComplete(@NonNull Task<Void> task) {
                FirebaseDatabase.getInstance().getReference("User").child(uid).setValue(null);
                SettingFragment.this.startActivity(new Intent(SettingFragment.this.getContext(), SplashActivity.class));
                ((FragmentActivity) Objects.requireNonNull(SettingFragment.this.getActivity())).overridePendingTransition(17432576, 17432577);
            }
        });
    }

    public void information() {
        ((Context) Objects.requireNonNull(getContext())).startActivity(new Intent(getContext(), PopUpActivity.class));
        ((FragmentActivity) Objects.requireNonNull(getActivity())).overridePendingTransition(17432576, 17432577);
    }

    public void setPassword(int i) {
        preference().setLong("pin", (long) i);
    }

    public void showTheme() {
        ((Context) Objects.requireNonNull(getContext())).startActivity(new Intent(getContext(), ThemeActivity.class));
        ((FragmentActivity) Objects.requireNonNull(getActivity())).overridePendingTransition(17432576, 17432577);
    }
}
