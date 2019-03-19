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

public class AwesomeErrorDialog extends AwesomeDialogBuilder<AwesomeErrorDialog> {
    private Button btDialogOk = ((Button) findView(R.id.btDialogOk));
    private RelativeLayout dialogBody = ((RelativeLayout) findView(R.id.dialog_body));

    public AwesomeErrorDialog(Context context) {
        super(context);
        setColoredCircle(R.color.dialogErrorBackgroundColor);
        setDialogIconAndColor(R.drawable.ic_dialog_error, R.color.white);
        setButtonBackgroundColor(R.color.dialogErrorBackgroundColor);
        setCancelable(true);
    }

    public AwesomeErrorDialog setDialogBodyBackgroundColor(int i) {
        if (this.dialogBody != null) {
            this.dialogBody.getBackground().setColorFilter(ContextCompat.getColor(getContext(), i), Mode.SRC_IN);
        }
        return this;
    }

    public AwesomeErrorDialog setErrorButtonClick(@Nullable final Closure closure) {
        this.btDialogOk.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (closure != null) {
                    closure.exec();
                }
                AwesomeErrorDialog.this.hide();
            }
        });
        return this;
    }

    public AwesomeErrorDialog setButtonBackgroundColor(int i) {
        if (this.btDialogOk != null) {
            this.btDialogOk.getBackground().setColorFilter(ContextCompat.getColor(getContext(), i), Mode.SRC_IN);
        }
        return this;
    }

    public AwesomeErrorDialog setButtonTextColor(int i) {
        if (this.btDialogOk != null) {
            this.btDialogOk.setTextColor(ContextCompat.getColor(getContext(), i));
        }
        return this;
    }

    public AwesomeErrorDialog setButtonText(String str) {
        if (this.btDialogOk != null) {
            this.btDialogOk.setText(str);
            this.btDialogOk.setVisibility(0);
        }
        return this;
    }

    /* Access modifiers changed, original: protected */
    public int getLayout() {
        return R.layout.dialog_error;
    }
}
