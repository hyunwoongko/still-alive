package com.awesomedialog.blennersilva.awesomedialoglibrary;

import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;

public class AwesomeNoticeDialog extends AwesomeDialogBuilder<AwesomeNoticeDialog> {
    private Button btDialogOk = ((Button) findView(R.id.btDialogOk));
    private RelativeLayout dialogBody = ((RelativeLayout) findView(R.id.dialog_body));

    public AwesomeNoticeDialog(Context context) {
        super(context);
        setColoredCircle(R.color.dialogNoticeBackgroundColor);
        setDialogIconAndColor(R.drawable.ic_notice, R.color.white);
        setButtonBackgroundColor(R.color.dialogNoticeBackgroundColor);
        setCancelable(true);
    }

    public AwesomeNoticeDialog setDialogBodyBackgroundColor(int i) {
        if (this.dialogBody != null) {
            this.dialogBody.getBackground().setColorFilter(ContextCompat.getColor(getContext(), i), Mode.SRC_IN);
        }
        return this;
    }

    public AwesomeNoticeDialog setNoticeButtonClick(@Nullable final Closure closure) {
        this.btDialogOk.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (closure != null) {
                    closure.exec();
                }
                AwesomeNoticeDialog.this.hide();
            }
        });
        return this;
    }

    public AwesomeNoticeDialog setButtonBackgroundColor(int i) {
        if (this.btDialogOk != null) {
            this.btDialogOk.getBackground().setColorFilter(ContextCompat.getColor(getContext(), i), Mode.SRC_IN);
        }
        return this;
    }

    public AwesomeNoticeDialog setButtonTextColor(int i) {
        if (this.btDialogOk != null) {
            this.btDialogOk.setTextColor(ContextCompat.getColor(getContext(), i));
        }
        return this;
    }

    public AwesomeNoticeDialog setButtonText(String str) {
        if (this.btDialogOk != null) {
            this.btDialogOk.setText(str);
            this.btDialogOk.setVisibility(0);
        }
        return this;
    }

    /* Access modifiers changed, original: protected */
    public int getLayout() {
        return R.layout.dialog_notice;
    }
}
