package com.awesomedialog.blennersilva.awesomedialoglibrary;

import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.support.v4.content.ContextCompat;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

public class AwesomeProgressDialog extends AwesomeDialogBuilder<AwesomeProgressDialog> {
    private RelativeLayout dialogBody = ((RelativeLayout) findView(R.id.dialog_body));
    private ProgressBar progressBar = ((ProgressBar) findView(R.id.dialog_progress_bar));

    public AwesomeProgressDialog(Context context) {
        super(context);
        setColoredCircle(R.color.dialogProgressBackgroundColor);
        setProgressBarColor(R.color.white);
    }

    public AwesomeProgressDialog setDialogBodyBackgroundColor(int i) {
        if (this.dialogBody != null) {
            this.dialogBody.getBackground().setColorFilter(ContextCompat.getColor(getContext(), i), Mode.SRC_IN);
        }
        return this;
    }

    public AwesomeProgressDialog setProgressBarColor(int i) {
        this.progressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(getContext(), R.color.white), Mode.SRC_IN);
        return this;
    }

    /* Access modifiers changed, original: protected */
    public int getLayout() {
        return R.layout.dialog_progress;
    }
}
