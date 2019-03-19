package com.awakers.stillalive.ui.main.capsule;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.awakers.stillalive.R;
import com.awakers.stillalive.base.BaseFragment;
import com.awakers.stillalive.base.BaseRepository;
import com.awakers.stillalive.data.repo.ThemeDB;
import com.awakers.stillalive.data.repo.UserCapsule;
import com.awakers.stillalive.data.vo.Capsule;
import com.awakers.stillalive.databinding.CapsuleView;
import com.awakers.stillalive.ui.main.MainActivity;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeProgressDialog;
import java.util.Vector;
import javax.inject.Inject;

public class CapsuleFragment extends BaseFragment implements CapsuleNavigator {
    public AwesomeProgressDialog dialog;
    private CapsuleView view;
    @Inject
    CapsuleViewModel viewModel;

    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        this.view = (CapsuleView) DataBindingUtil.inflate(layoutInflater, R.layout.capsule_view, viewGroup, false);
        DaggerCapsuleComponent.builder().capsuleFragment(this).capsuleFactory(new CapsuleFactory(this)).build().inject(this);
        this.view.setViewModel(this.viewModel);
        this.view.recycler.setLayoutManager(new GridLayoutManager(getContext(), 3));
        this.view.recycler.setAdapter(new CapsuleAdapter(getActivity(), new Vector()));
        this.viewModel.showUserCapsule();
        return this.view.getRoot();
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.viewModel.onCleared();
        this.viewModel = null;
        this.view = null;
    }

    public synchronized void showUserCapsule() {
        Vector vector = new Vector();
        if (UserCapsule.get().getUserCapsule() != null) {
            for (Capsule capsule : UserCapsule.get().getUserCapsule()) {
                api().loadTheme(capsule.getTheme(), new CapsuleFragment$$Lambda$0(this, new CapsuleItemModel(), capsule, vector));
            }
        } else {
            this.view.recycler.setAdapter(new CapsuleAdapter(getActivity(), new Vector()));
        }
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void lambda$showUserCapsule$0$CapsuleFragment(CapsuleItemModel capsuleItemModel, Capsule capsule, Vector vector, BaseRepository baseRepository) {
        ThemeDB.set((ThemeDB) baseRepository);
        showProgress();
        capsuleItemModel.capsuleName.set(capsule.getCapsuleName());
        capsuleItemModel.imgSrc.set(ThemeDB.get().getCapsule());
        vector.add(capsuleItemModel);
        if (capsule.equals(UserCapsule.get().getUserCapsule().get(UserCapsule.get().getUserCapsule().size() - 1))) {
            this.view.recycler.setAdapter(new CapsuleAdapter(getActivity(), vector));
        }
        dismissProgress();
    }

    public void showProgress() {
        if (MainActivity.homeDialog == null) {
            MainActivity.homeDialog = new AwesomeProgressDialog(getActivity());
        }
        MainActivity.homeDialog.setTitle((CharSequence) "이미지 로딩");
        MainActivity.homeDialog.setMessage((CharSequence) "이미지 데이터 로드중입니다.");
        MainActivity.homeDialog.setColoredCircle(2131099689);
        MainActivity.homeDialog.setCancelable(false);
        MainActivity.homeDialog.show();
    }

    public void dismissProgress() {
        if (MainActivity.homeDialog != null) {
            MainActivity.homeDialog.hide();
        }
    }
}
