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

public class AwesomeInfoDialog extends AwesomeDialogBuilder<AwesomeInfoDialog> {
    private RelativeLayout dialogBody = ((RelativeLayout) findView(R.id.dialog_body));
    private Button negativeButton = ((Button) findView(R.id.btDialogNo));
    private Button neutralButton = ((Button) findView(R.id.btDialogNeutral));
    private Button positiveButton = ((Button) findView(R.id.btDialogYes));

    public AwesomeInfoDialog(Context context) {
        super(context);
        setColoredCircle(R.color.dialogInfoBackgroundColor);
        setDialogIconAndColor(R.drawable.ic_dialog_info, R.color.white);
        setPositiveButtonbackgroundColor(R.color.dialogInfoBackgroundColor);
        setNegativeButtonbackgroundColor(R.color.dialogInfoBackgroundColor);
        setNeutralButtonbackgroundColor(R.color.dialogInfoBackgroundColor);
        setCancelable(true);
    }

    public AwesomeInfoDialog setDialogBodyBackgroundColor(int i) {
        if (this.dialogBody != null) {
            this.dialogBody.getBackground().setColorFilter(ContextCompat.getColor(getContext(), i), Mode.SRC_IN);
        }
        return this;
    }

    public AwesomeInfoDialog setPositiveButtonClick(@Nullable final Closure closure) {
        this.positiveButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (closure != null) {
                    closure.exec();
                }
                AwesomeInfoDialog.this.hide();
            }
        });
        return this;
    }

    public AwesomeInfoDialog setNegativeButtonClick(@Nullable final Closure closure) {
        this.negativeButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (closure != null) {
                    closure.exec();
                }
                AwesomeInfoDialog.this.hide();
            }
        });
        return this;
    }

    public AwesomeInfoDialog setNeutralButtonClick(@Nullable final Closure closure) {
        this.neutralButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (closure != null) {
                    closure.exec();
                }
                AwesomeInfoDialog.this.hide();
            }
        });
        return this;
    }

    public AwesomeInfoDialog setPositiveButtonbackgroundColor(int i) {
        if (this.positiveButton != null) {
            this.positiveButton.getBackground().setColorFilter(ContextCompat.getColor(getContext(), i), Mode.SRC_IN);
        }
        return this;
    }

    public AwesomeInfoDialog setPositiveButtonTextColor(int i) {
        if (this.positiveButton != null) {
            this.positiveButton.setTextColor(ContextCompat.getColor(getContext(), i));
        }
        return this;
    }

    public AwesomeInfoDialog setPositiveButtonText(String str) {
        if (this.positiveButton != null) {
            this.positiveButton.setText(str);
            this.positiveButton.setVisibility(0);
        }
        return this;
    }

    public AwesomeInfoDialog setNegativeButtonbackgroundColor(int i) {
        if (this.negativeButton != null) {
            this.negativeButton.getBackground().setColorFilter(ContextCompat.getColor(getContext(), i), Mode.SRC_IN);
        }
        return this;
    }

    public AwesomeInfoDialog setNegativeButtonText(String str) {
        if (this.negativeButton != null) {
            this.negativeButton.setText(str);
        }
        return this;
    }

    public AwesomeInfoDialog setNegativeButtonTextColor(int i) {
        if (this.negativeButton != null) {
            this.negativeButton.setTextColor(ContextCompat.getColor(getContext(), i));
            this.negativeButton.setVisibility(0);
        }
        return this;
    }

    public AwesomeInfoDialog setNeutralButtonbackgroundColor(int i) {
        if (this.neutralButton != null) {
            this.neutralButton.getBackground().setColorFilter(ContextCompat.getColor(getContext(), i), Mode.SRC_IN);
        }
        return this;
    }

    public AwesomeInfoDialog setNeutralButtonText(String str) {
        if (this.neutralButton != null) {
            this.neutralButton.setText(str);
        }
        return this;
    }

    public AwesomeInfoDialog setNeutralButtonTextColor(int i) {
        if (this.neutralButton != null) {
            this.neutralButton.setTextColor(ContextCompat.getColor(getContext(), i));
            this.neutralButton.setVisibility(0);
        }
        return this;
    }

    /* Access modifiers changed, original: protected */
    public int getLayout() {
        return R.layout.dialog_info;
    }
}
