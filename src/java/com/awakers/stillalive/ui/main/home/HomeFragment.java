package com.awakers.stillalive.ui.main.home;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.awakers.stillalive.R;
import com.awakers.stillalive.app.AwakersApplication;
import com.awakers.stillalive.base.BaseFragment;
import com.awakers.stillalive.base.BaseRepository;
import com.awakers.stillalive.data.repo.ThemeDB;
import com.awakers.stillalive.data.repo.UserCapsule;
import com.awakers.stillalive.data.vo.Capsule;
import com.awakers.stillalive.databinding.HomeView;
import com.awakers.stillalive.ui.mission.MissionActivity;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeWarningDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import javax.inject.Inject;

public class HomeFragment extends BaseFragment implements HomeNavigator {
    private HomeView view;
    @Inject
    HomeViewModel viewModel;

    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        this.view = (HomeView) DataBindingUtil.inflate(layoutInflater, R.layout.home_view, viewGroup, false);
        DaggerHomeComponent.builder().homeFactory(new HomeFactory(this)).homeFragment(this).build().inject(this);
        this.view.setViewModel(this.viewModel);
        this.viewModel.loadImage();
        return this.view.getRoot();
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.viewModel.onCleared();
        this.viewModel = null;
        this.view = null;
    }

    public void moveToMissionActivity(Boolean bool) {
        final AwesomeWarningDialog awesomeWarningDialog = new AwesomeWarningDialog(getContext());
        FirebaseDatabase.getInstance().getReference("Mission").child(String.valueOf(app().getCurrentDate())).addValueEventListener(new ValueEventListener() {
            public void onCancelled(DatabaseError databaseError) {
            }

            public void onDataChange(DataSnapshot dataSnapshot) {
                awesomeWarningDialog.setMessage((CharSequence) dataSnapshot.getValue().toString());
            }
        });
        ((AwesomeWarningDialog) ((AwesomeWarningDialog) ((AwesomeWarningDialog) ((AwesomeWarningDialog) awesomeWarningDialog.setTitle((CharSequence) "오늘의 미션")).setColoredCircle(2131099689)).setDialogIconAndColor(2131230865, 2131099775)).setCancelable(true)).setButtonText(getString(2131689528)).setButtonBackgroundColor(2131099689).setButtonText("미션 시작하기").setButtonTextColor(2131099775).setWarningButtonClick(new Closure() {
            public void exec() {
                HomeFragment.this.startActivity(new Intent(HomeFragment.this.getContext(), MissionActivity.class));
                HomeFragment.this.getActivity().overridePendingTransition(17432576, 17432577);
            }
        }).show();
    }

    public synchronized void loadImage() {
        try {
            api().loadTheme(((Capsule) UserCapsule.get().getUserCapsule().get(UserCapsule.get().getUserCapsule().size() - 1)).getTheme(), new HomeFragment$$Lambda$0(this, AwakersApplication.appComponent.app().getDate(String.valueOf(((Capsule) UserCapsule.get().getUserCapsule().get(UserCapsule.get().getUserCapsule().size() - 1)).getStartDate()), String.valueOf(AwakersApplication.appComponent.app().getCurrentDate() + 1))));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void lambda$loadImage$0$HomeFragment(int i, BaseRepository baseRepository) {
        ThemeDB.set((ThemeDB) baseRepository);
        Picasso.get().load(ThemeDB.get().getSpecial()).into(this.view.special);
        if (i < 30) {
            Picasso.get().load((String) ThemeDB.get().getUrl().get(i / 3)).into(this.view.homeImg);
        } else {
            Picasso.get().load((String) ThemeDB.get().getUrl().get(10)).into(this.view.homeImg);
        }
    }
}
