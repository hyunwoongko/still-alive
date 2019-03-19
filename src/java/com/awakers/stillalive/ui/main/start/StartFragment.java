package com.awakers.stillalive.ui.main.start;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.awakers.stillalive.R;
import com.awakers.stillalive.base.BaseFragment;
import com.awakers.stillalive.base.BaseRepository;
import com.awakers.stillalive.data.repo.ThemeDB;
import com.awakers.stillalive.data.repo.UserCapsule;
import com.awakers.stillalive.data.repo.UserTheme;
import com.awakers.stillalive.data.vo.Capsule;
import com.awakers.stillalive.databinding.StartView;
import com.awakers.stillalive.ui.init.greeting.GreetingFragment;
import com.awakers.stillalive.ui.main.capsule.CapsuleAdapter;
import es.dmoral.toasty.Toasty;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Vector;
import javax.inject.Inject;

public class StartFragment extends BaseFragment implements StartNavigator {
    private StartView view;
    @Inject
    StartViewModel viewModel;

    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        this.view = (StartView) DataBindingUtil.inflate(layoutInflater, R.layout.start_view, viewGroup, false);
        DaggerStartComponent.builder().startFactory(new StartFactory(this)).startFragment(this).build().inject(this);
        this.view.setViewModel(this.viewModel);
        this.view.recycler.setLayoutManager(new GridLayoutManager(getContext(), 3));
        this.view.recycler.setAdapter(new CapsuleAdapter(getActivity(), new Vector()));
        this.viewModel.showUserTheme();
        return this.view.getRoot();
    }

    public void onDestroyView() {
        this.viewModel.onCleared();
        this.viewModel = null;
        this.view = null;
        super.onDestroyView();
    }

    public void replaceToHome(Boolean bool) {
        Toasty.success((Context) Objects.requireNonNull(getContext()), "캡슐을 생성하였습니다.", 0).show();
        showAnim(getActivity());
        replace((BaseFragment) this, (int) R.id.container, (Fragment) new GreetingFragment(), bool.booleanValue());
        showAnim(getActivity());
    }

    public synchronized void showUserTheme() {
        Vector vector = new Vector();
        for (String str : UserTheme.get().getUserTheme()) {
            api().loadTheme(str, new StartFragment$$Lambda$0(this, new StartItemModel(), vector, str));
        }
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void lambda$showUserTheme$0$StartFragment(StartItemModel startItemModel, Vector vector, String str, BaseRepository baseRepository) {
        ThemeDB.set((ThemeDB) baseRepository);
        startItemModel.themeName.set(ThemeDB.get().getName());
        startItemModel.imgSrc.set(ThemeDB.get().getThumb());
        vector.add(startItemModel);
        if (str.equals(UserTheme.get().getUserTheme().get(UserTheme.get().getUserTheme().size() - 1))) {
            this.view.recycler.setAdapter(new StartAdapter(getContext(), vector));
        }
    }

    public void makeCapsule() {
        Capsule.get().setStartDate(app().getCurrentDate());
        Capsule.get().setOnGoing(true);
        if (UserCapsule.get().getUserCapsule() == null) {
            UserCapsule.get().setUserCapsule(Arrays.asList(new Capsule[]{Capsule.get()}));
            api().set("userCapsule", UserCapsule.get());
            return;
        }
        List userCapsule = UserCapsule.get().getUserCapsule();
        userCapsule.add(Capsule.get());
        UserCapsule.get().setUserCapsule(userCapsule);
        api().set("userCapsule", UserCapsule.get());
    }

    public void nonThemeSelected() {
        Toasty.warning((Context) Objects.requireNonNull(getContext()), "사용할 테마가 선택되지 않았습니다.").show();
    }

    public void nonNameInputed() {
        Toasty.warning((Context) Objects.requireNonNull(getContext()), "사용할 테마가 선택되지 않았습니다.").show();
    }
}
