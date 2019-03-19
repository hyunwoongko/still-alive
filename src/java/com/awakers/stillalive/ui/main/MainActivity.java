package com.awakers.stillalive.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.awakers.stillalive.R;
import com.awakers.stillalive.app.AwakersApplication;
import com.awakers.stillalive.base.BackPressActivity;
import com.awakers.stillalive.base.BaseNavigator;
import com.awakers.stillalive.base.BaseRepository;
import com.awakers.stillalive.data.repo.UserCapsule;
import com.awakers.stillalive.data.vo.Capsule;
import com.awakers.stillalive.databinding.MainView;
import com.awakers.stillalive.ui.main.capsule.CapsuleFragment;
import com.awakers.stillalive.ui.main.home.HomeFragment;
import com.awakers.stillalive.ui.main.setting.SettingFragment;
import com.awakers.stillalive.ui.main.start.StartFragment;
import com.awakers.stillalive.ui.password.PasswordActivity;
import com.awakers.stillalive.ui.rebirth.RebirthActivity;
import com.awakers.stillalive.ui.splash.SplashActivity;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeInfoDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeProgressDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeWarningDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import java.text.ParseException;
import java.util.Objects;
import rx.Observable;

public class MainActivity extends BackPressActivity implements BaseNavigator {
    public static AwesomeProgressDialog homeDialog;
    private MainView view;

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public int getCount() {
            return 3;
        }

        public SectionsPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    notifyDataSetChanged();
                    return new CapsuleFragment();
                case 1:
                    if (UserCapsule.get().getUserCapsule() == null || !((Capsule) UserCapsule.get().getUserCapsule().get(UserCapsule.get().getUserCapsule().size() - 1)).isOnGoing()) {
                        notifyDataSetChanged();
                        return new StartFragment();
                    }
                    notifyDataSetChanged();
                    return new HomeFragment();
                case 2:
                    notifyDataSetChanged();
                    return new SettingFragment();
                default:
                    return null;
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.view = (MainView) DataBindingUtil.setContentView(this, R.layout.main_view);
        check(this);
        this.view.container.setAdapter(new SectionsPagerAdapter(getSupportFragmentManager()));
        this.view.container.setCurrentItem(1);
    }

    /* Access modifiers changed, original: protected */
    public void onDestroy() {
        super.onDestroy();
        this.view = null;
    }

    public void check(Activity activity) {
        if (UserCapsule.get().getUserCapsule() != null) {
            AwakersApplication.appComponent.api().get("userCapsule", UserCapsule.class, new MainActivity$$Lambda$0(this, activity));
        }
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void lambda$check$5$MainActivity(Activity activity, BaseRepository baseRepository) {
        UserCapsule.set((UserCapsule) baseRepository);
        if (UserCapsule.get().getUserCapsule() != null) {
            Capsule capsule = (Capsule) UserCapsule.get().getUserCapsule().get(UserCapsule.get().getUserCapsule().size() - 1);
            int size = capsule.getMission() != null ? capsule.getMission().size() : 0;
            if (size >= 30) {
                ((AwesomeWarningDialog) ((AwesomeWarningDialog) ((AwesomeWarningDialog) ((AwesomeWarningDialog) ((AwesomeWarningDialog) new AwesomeWarningDialog(activity).setTitle((CharSequence) "생존일지 완성")).setMessage((CharSequence) "축하합니다! 모든 미션을 성공하셨습니다 !")).setColoredCircle(2131099689)).setDialogIconAndColor(2131230865, 2131099775)).setCancelable(false)).setButtonText(activity.getString(2131689528)).setButtonBackgroundColor(2131099689).setButtonText("완성된 생존일지 보러가기").setButtonTextColor(2131099775).setWarningButtonClick(new MainActivity$$Lambda$1(capsule, activity)).show();
            }
            try {
                String dateFromDiff = AwakersApplication.appComponent.app().getDateFromDiff(size, String.valueOf(capsule.getStartDate()));
                String valueOf = String.valueOf(AwakersApplication.appComponent.app().getCurrentDate());
                String valueOf2 = String.valueOf(AwakersApplication.appComponent.app().getDate(dateFromDiff, AwakersApplication.appComponent.app().getDateFromDiff(-1, String.valueOf(AwakersApplication.appComponent.app().getCurrentDate()))));
                if (AwakersApplication.appComponent.app().getDate(dateFromDiff, valueOf) > 1) {
                    AwesomeInfoDialog awesomeInfoDialog = new AwesomeInfoDialog(activity);
                    awesomeInfoDialog.setTitle((CharSequence) "생존 실패");
                    awesomeInfoDialog.setMessage((CharSequence) "미션을 진행하지 않아 생존일지가 소멸됩니다.");
                    awesomeInfoDialog.setPositiveButtonText("회생권 구매하기");
                    awesomeInfoDialog.setPositiveButtonTextColor(2131099775);
                    awesomeInfoDialog.setPositiveButtonbackgroundColor(2131099689);
                    awesomeInfoDialog.setPositiveButtonClick(new MainActivity$$Lambda$2(valueOf2, dateFromDiff, activity));
                    awesomeInfoDialog.setNegativeButtonTextColor(2131099775);
                    awesomeInfoDialog.setNegativeButtonbackgroundColor(2131099689);
                    awesomeInfoDialog.setNegativeButtonText("취소");
                    awesomeInfoDialog.setNegativeButtonClick(new MainActivity$$Lambda$3(this, activity));
                    awesomeInfoDialog.setCancelable(false);
                    awesomeInfoDialog.setColoredCircle(2131099689);
                    awesomeInfoDialog.show();
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    static final /* synthetic */ void lambda$null$0$MainActivity(Capsule capsule, Activity activity) {
        FirebaseDatabase.getInstance().getReference("User").child(((FirebaseUser) Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser())).getUid()).child("userCapsule").child("userCapsule").child(String.valueOf(UserCapsule.get().getUserCapsule().size() - 1)).child("onGoing").setValue(Boolean.valueOf(false));
        capsule.setOnGoing(false);
        AwakersApplication.currentCapsuleName = capsule.getCapsuleName();
        activity.startActivity(new Intent(activity, PasswordActivity.class));
        activity.overridePendingTransition(17432576, 17432577);
    }

    static final /* synthetic */ void lambda$null$1$MainActivity(String str, String str2, Activity activity) {
        for (int i = 0; i <= Integer.parseInt(str); i++) {
            try {
                String dateFromDiff = AwakersApplication.appComponent.app().getDateFromDiff(i, str2);
                FirebaseDatabase.getInstance().getReference("User").child(((FirebaseUser) Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser())).getUid()).child("userCapsule").child("userCapsule").child(String.valueOf(UserCapsule.get().getUserCapsule().size() - 1)).child("mission").child(dateFromDiff).child("text").setValue("회생권 사용");
                FirebaseDatabase.getInstance().getReference("User").child(((FirebaseUser) Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser())).getUid()).child("userCapsule").child("userCapsule").child(String.valueOf(UserCapsule.get().getUserCapsule().size() - 1)).child("mission").child(dateFromDiff).child("url").setValue("https://firebasestorage.googleapis.com/v0/b/stillalive-ff8a9.appspot.com/o/AwakersApp.png?alt=media&token=9d26c55f-6113-4efb-b1f6-7dca9edfd80f");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            activity.startActivity(new Intent(activity, RebirthActivity.class));
            activity.finish();
        }
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void lambda$null$4$MainActivity(Activity activity) {
        FirebaseDatabase.getInstance().getReference("User").child(((FirebaseUser) Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser())).getUid()).child("userCapsule").child("userCapsule").child(String.valueOf(UserCapsule.get().getUserCapsule().size() - 1)).setValue(null);
        Observable.just(Integer.valueOf(0)).doOnTerminate(new MainActivity$$Lambda$4(this)).subscribe(new MainActivity$$Lambda$5(activity));
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void lambda$null$2$MainActivity() {
        startActivity(new Intent(this, SplashActivity.class));
        showAnim(this);
    }
}
