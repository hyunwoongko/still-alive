package com.awakers.stillalive.base;

import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeInfoDialog;

public abstract class BackPressActivity extends BaseActivity implements BaseNavigator {
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            AwesomeInfoDialog awesomeInfoDialog = new AwesomeInfoDialog(this);
            awesomeInfoDialog.setTitle((CharSequence) "앱 종료");
            awesomeInfoDialog.setMessage((CharSequence) "앱을 종료하시겠습니까?");
            awesomeInfoDialog.setPositiveButtonText("확인");
            awesomeInfoDialog.setPositiveButtonTextColor(2131099775);
            awesomeInfoDialog.setPositiveButtonbackgroundColor(2131099689);
            awesomeInfoDialog.setPositiveButtonClick(new BackPressActivity$$Lambda$0(this));
            awesomeInfoDialog.setNegativeButtonTextColor(2131099775);
            awesomeInfoDialog.setNegativeButtonbackgroundColor(2131099689);
            awesomeInfoDialog.setNegativeButtonText("취소");
            awesomeInfoDialog.getClass();
            awesomeInfoDialog.setNegativeButtonClick(BackPressActivity$$Lambda$1.get$Lambda(awesomeInfoDialog));
            awesomeInfoDialog.setCancelable(true);
            awesomeInfoDialog.setColoredCircle(2131099689);
            awesomeInfoDialog.show();
            return;
        }
        getFragmentManager().popBackStack();
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void lambda$onBackPressed$0$BackPressActivity() {
        super.onBackPressed();
    }
}
