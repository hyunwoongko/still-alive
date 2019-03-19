package com.awesomedialog.blennersilva.awesomedialoglibrary;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog.Builder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public abstract class AwesomeDialogBuilder<T extends AwesomeDialogBuilder> {
    private RelativeLayout coloredCircle;
    private Context context;
    private Dialog dialog;
    private ImageView dialogIcon;
    private View dialogView;
    private TextView tvMessage;
    private TextView tvTitle;

    public abstract int getLayout();

    public AwesomeDialogBuilder(Context context) {
        createDialog(new Builder(context));
        setContext(context);
    }

    public void createDialog(Builder builder) {
        this.dialogView = LayoutInflater.from(builder.getContext()).inflate(getLayout(), null);
        this.dialog = builder.setView(this.dialogView).create();
        if (this.dialog.getWindow() != null) {
            this.dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            this.dialog.getWindow().requestFeature(1);
        }
        this.dialogIcon = (ImageView) findView(R.id.dialog_icon);
        this.tvTitle = (TextView) findView(R.id.dialog_title);
        this.tvMessage = (TextView) findView(R.id.dialog_message);
        this.coloredCircle = (RelativeLayout) findView(R.id.colored_circle);
    }

    public T setTitle(@StringRes int i) {
        return setTitle(string(i));
    }

    public T setTitle(CharSequence charSequence) {
        if (this.tvTitle != null) {
            this.tvTitle.setText(charSequence);
        }
        return this;
    }

    public T setMessage(@StringRes int i) {
        return setMessage(string(i));
    }

    public T setMessage(CharSequence charSequence) {
        if (this.tvMessage != null) {
            this.tvMessage.setText(charSequence);
        }
        return this;
    }

    public T setColoredCircle(int i) {
        if (this.coloredCircle != null) {
            this.coloredCircle.getBackground().setColorFilter(ContextCompat.getColor(getContext(), i), Mode.SRC_IN);
        }
        return this;
    }

    public T setDialogIconAndColor(int i, int i2) {
        if (this.dialogIcon != null) {
            this.dialogIcon.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.rubber_band));
            this.dialogIcon.setImageDrawable(drawableColorChange(getContext(), i, i2));
        }
        return this;
    }

    public T setDialogIconOnly(int i) {
        if (this.dialogIcon != null) {
            this.dialogIcon.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.rubber_band));
            this.dialogIcon.setImageResource(i);
        }
        return this;
    }

    public Dialog show() {
        try {
            if (!(this.context instanceof Activity)) {
                this.dialog.show();
            } else if (!((Activity) this.context).isFinishing()) {
                this.dialog.show();
            }
        } catch (Exception unused) {
            Log.e("[AwSDialog:showAlert]", "Erro ao exibir alert");
        }
        return this.dialog;
    }

    public Dialog hide() {
        try {
            this.dialog.dismiss();
        } catch (Exception unused) {
            Log.d("[AwSDialog : dismiss]", " Erro ao remover diálogo (%s)");
        }
        this.dialog.dismiss();
        return this.dialog;
    }

    public T setCancelable(boolean z) {
        this.dialog.setCancelable(z);
        return this;
    }

    /* Access modifiers changed, original: protected */
    public String string(@StringRes int i) {
        return this.dialogView.getContext().getString(i);
    }

    public static Drawable drawableColorChange(Context context, int i, int i2) {
        Drawable drawable;
        if (VERSION.SDK_INT >= 21) {
            drawable = context.getDrawable(i);
        } else {
            drawable = context.getResources().getDrawable(i);
        }
        if (drawable != null) {
            drawable.setColorFilter(ContextCompat.getColor(context, i2), Mode.SRC_IN);
        }
        return drawable;
    }

    /* Access modifiers changed, original: protected */
    public <ViewClass extends View> ViewClass findView(int i) {
        return this.dialogView.findViewById(i);
    }

    public Context getContext() {
        return this.context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
