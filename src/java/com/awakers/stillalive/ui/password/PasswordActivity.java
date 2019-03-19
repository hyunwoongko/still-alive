package com.awakers.stillalive.ui.password;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog.Builder;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import com.awakers.stillalive.R;
import com.awakers.stillalive.base.BaseActivity;
import com.awakers.stillalive.ui.diary.DiaryActivity;
import com.nightonke.blurlockview.BlurLockView;
import com.nightonke.blurlockview.BlurLockView.OnLeftButtonClickListener;
import com.nightonke.blurlockview.BlurLockView.OnPasswordInputListener;
import com.nightonke.blurlockview.Password;
import es.dmoral.toasty.Toasty;
import java.util.Objects;

public class PasswordActivity extends BaseActivity implements OnPasswordInputListener, OnLeftButtonClickListener {
    private BlurLockView blurLockView;
    private ImageView imageView1;
    private boolean isCorrect;

    public void input(String str) {
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        supportRequestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.password_view);
        this.imageView1 = (ImageView) findViewById(R.id.image_1);
        this.blurLockView = (BlurLockView) findViewById(R.id.blurlockview);
        this.blurLockView.setBlurredView(this.imageView1);
        this.blurLockView.setOverlayColor(Color.parseColor("#555555"));
        this.blurLockView.setCorrectPassword(preference().getString("pin", "0000"));
        this.blurLockView.setRightButton("지우기");
        this.blurLockView.setLeftButton("재설정");
        this.blurLockView.setOnLeftButtonClickListener(new PasswordActivity$$Lambda$0(this));
        this.blurLockView.setTitle("비밀 번호");
        this.blurLockView.setTypeface(Typeface.createFromAsset(getAssets(), "font/font.ttf"));
        this.blurLockView.setType(Password.NUMBER, false);
        this.blurLockView.setOnPasswordInputListener(this);
    }

    public void correct(String str) {
        startActivity(new Intent(this, DiaryActivity.class));
        overridePendingTransition(17432576, 17432577);
        finish();
        overridePendingTransition(17432576, 17432577);
    }

    public void incorrect(String str) {
        Toasty.error(this, "잘못된 비밀번호 입니다.", 0).show();
    }

    private void hideAll(DialogInterface dialogInterface, EditText editText, InputMethodManager inputMethodManager) {
        ((InputMethodManager) Objects.requireNonNull(inputMethodManager)).hideSoftInputFromWindow(editText.getWindowToken(), 0);
        dialogInterface.dismiss();
        dialogInterface.cancel();
    }

    public void onClick() {
        EditText editText = new EditText(this);
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
        if (!preference().getBoolean("pinChanged", false)) {
            Toasty.info(this, "초기 비밀번호는 0000입니다.", 0).show();
        }
        new Builder(this).setTitle("비밀번호 재 설정").setMessage("현재 비밀번호를 입력해주세요.").setView(editText).setPositiveButton("확인", new PasswordActivity$$Lambda$1(this, editText, inputMethodManager)).setNegativeButton("취소", new PasswordActivity$$Lambda$2(this, editText, inputMethodManager)).show();
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void lambda$onClick$0$PasswordActivity(EditText editText, InputMethodManager inputMethodManager, DialogInterface dialogInterface, int i) {
        if (editText.getText().toString().equals(preference().getString("pin", "0000"))) {
            hideAll(dialogInterface, editText, inputMethodManager);
            this.isCorrect = true;
        } else {
            Toasty.error(this, "잘못된 비밀번호입니다.", 0).show();
            hideAll(dialogInterface, editText, inputMethodManager);
            this.isCorrect = false;
        }
        if (this.isCorrect) {
            resetPassword();
        }
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void lambda$onClick$1$PasswordActivity(EditText editText, InputMethodManager inputMethodManager, DialogInterface dialogInterface, int i) {
        hideAll(dialogInterface, editText, inputMethodManager);
        this.isCorrect = false;
    }

    private void resetPassword() {
        EditText editText = new EditText(this);
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
        new Builder(this).setTitle("비밀번호 재 설정").setMessage("새로운 비밀번호를 입력해주세요.").setView(editText).setPositiveButton("확인", new PasswordActivity$$Lambda$3(this, editText, inputMethodManager)).setNegativeButton("취소", new PasswordActivity$$Lambda$4(this, editText, inputMethodManager)).show();
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void lambda$resetPassword$2$PasswordActivity(EditText editText, InputMethodManager inputMethodManager, DialogInterface dialogInterface, int i) {
        try {
            if (editText.getText().length() != 4) {
                hideAll(dialogInterface, editText, inputMethodManager);
                this.isCorrect = false;
                throw new Exception("비밀번호 자릿수 오류");
            }
            Integer.valueOf(editText.getText().toString());
            preference().setString("pin", editText.getText().toString());
            this.blurLockView.setCorrectPassword(String.valueOf(preference().getString("pin", "1234")));
            Toasty.success(this, "비밀번호가 변경되었습니다.", 0).show();
            preference().setBoolean("pinChanged", true);
            hideAll(dialogInterface, editText, inputMethodManager);
        } catch (Exception unused) {
            Toasty.warning(this, "4자리 숫자만 입력해주세요.", 0).show();
            hideAll(dialogInterface, editText, inputMethodManager);
        }
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void lambda$resetPassword$3$PasswordActivity(EditText editText, InputMethodManager inputMethodManager, DialogInterface dialogInterface, int i) {
        hideAll(dialogInterface, editText, inputMethodManager);
    }
}
