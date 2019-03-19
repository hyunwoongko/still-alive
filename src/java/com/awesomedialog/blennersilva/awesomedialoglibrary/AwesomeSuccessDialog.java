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

public class AwesomeSuccessDialog extends AwesomeDialogBuilder<AwesomeSuccessDialog> {
    private RelativeLayout dialogBody = ((RelativeLayout) findView(R.id.dialog_body));
    private Button doneButton = ((Button) findView(R.id.btDialogDone));
    private Button negativeButton = ((Button) findView(R.id.btDialogNo));
    private Button positiveButton = ((Button) findView(R.id.btDialogYes));

    public AwesomeSuccessDialog(Context context) {
        super(context);
        setColoredCircle(R.color.dialogSuccessBackgroundColor);
        setDialogIconAndColor(R.drawable.ic_success, R.color.white);
        setNegativeButtonbackgroundColor(R.color.dialogSuccessBackgroundColor);
        setPositiveButtonbackgroundColor(R.color.dialogSuccessBackgroundColor);
        setDoneButtonbackgroundColor(R.color.dialogSuccessBackgroundColor);
    }

    public AwesomeSuccessDialog setDialogBodyBackgroundColor(int i) {
        if (this.dialogBody != null) {
            this.dialogBody.getBackground().setColorFilter(ContextCompat.getColor(getContext(), i), Mode.SRC_IN);
        }
        return this;
    }

    public AwesomeSuccessDialog setPositiveButtonClick(@Nullable final Closure closure) {
        this.positiveButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (closure != null) {
                    closure.exec();
                }
                AwesomeSuccessDialog.this.hide();
            }
        });
        return this;
    }

    public AwesomeSuccessDialog setNegativeButtonClick(@Nullable final Closure closure) {
        this.negativeButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (closure != null) {
                    closure.exec();
                }
                AwesomeSuccessDialog.this.hide();
            }
        });
        return this;
    }

    public AwesomeSuccessDialog setDoneButtonClick(@Nullable final Closure closure) {
        this.doneButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (closure != null) {
                    closure.exec();
                }
                AwesomeSuccessDialog.this.hide();
            }
        });
        return this;
    }

    public AwesomeSuccessDialog showPositiveButton(boolean z) {
        if (this.positiveButton != null) {
            this.positiveButton.setVisibility(0);
        }
        return this;
    }

    public AwesomeSuccessDialog showNegativeButton(boolean z) {
        if (this.negativeButton != null) {
            this.negativeButton.setVisibility(0);
        }
        return this;
    }

    public AwesomeSuccessDialog showDoneButton(boolean z) {
        if (this.doneButton != null) {
            this.doneButton.setVisibility(0);
        }
        return this;
    }

    public AwesomeSuccessDialog setPositiveButtonbackgroundColor(int i) {
        if (this.positiveButton != null) {
            this.positiveButton.getBackground().setColorFilter(ContextCompat.getColor(getContext(), i), Mode.SRC_IN);
        }
        return this;
    }

    public AwesomeSuccessDialog setPositiveButtonTextColor(int i) {
        if (this.positiveButton != null) {
            this.positiveButton.setTextColor(ContextCompat.getColor(getContext(), i));
        }
        return this;
    }

    public AwesomeSuccessDialog setPositiveButtonText(String str) {
        if (this.positiveButton != null) {
            this.positiveButton.setText(str);
            this.positiveButton.setVisibility(0);
        }
        return this;
    }

    public AwesomeSuccessDialog setNegativeButtonbackgroundColor(int i) {
        if (this.negativeButton != null) {
            this.negativeButton.getBackground().setColorFilter(ContextCompat.getColor(getContext(), i), Mode.SRC_IN);
        }
        return this;
    }

    public AwesomeSuccessDialog setNegativeButtonText(String str) {
        if (this.negativeButton != null) {
            this.negativeButton.setText(str);
            this.negativeButton.setVisibility(0);
        }
        return this;
    }

    public AwesomeSuccessDialog setNegativeButtonTextColor(int i) {
        if (this.negativeButton != null) {
            this.negativeButton.setTextColor(ContextCompat.getColor(getContext(), i));
        }
        return this;
    }

    public AwesomeSuccessDialog setDoneButtonbackgroundColor(int i) {
        if (this.doneButton != null) {
            this.doneButton.getBackground().setColorFilter(ContextCompat.getColor(getContext(), i), Mode.SRC_IN);
        }
        return this;
    }

    public AwesomeSuccessDialog setDoneButtonText(String str) {
        if (this.doneButton != null) {
            this.doneButton.setText(str);
            this.doneButton.setVisibility(0);
        }
        return this;
    }

    public AwesomeSuccessDialog setDoneButtonTextColor(int i) {
        if (this.doneButton != null) {
            this.doneButton.setTextColor(ContextCompat.getColor(getContext(), i));
        }
        return this;
    }

    /* Access modifiers changed, original: protected */
    public int getLayout() {
        return R.layout.dialog_success;
    }
}
