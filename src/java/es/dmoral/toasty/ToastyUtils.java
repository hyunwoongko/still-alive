package es.dmoral.toasty;

import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Build.VERSION;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.view.View;

final class ToastyUtils {
    private ToastyUtils() {
    }

    static Drawable tintIcon(@NonNull Drawable drawable, @ColorInt int i) {
        drawable.setColorFilter(i, Mode.SRC_IN);
        return drawable;
    }

    static Drawable tint9PatchDrawableFrame(@NonNull Context context, @ColorInt int i) {
        return tintIcon((NinePatchDrawable) getDrawable(context, R.drawable.toast_frame), i);
    }

    static void setBackground(@NonNull View view, Drawable drawable) {
        if (VERSION.SDK_INT >= 16) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    static Drawable getDrawable(@NonNull Context context, @DrawableRes int i) {
        if (VERSION.SDK_INT >= 21) {
            return context.getDrawable(i);
        }
        return context.getResources().getDrawable(i);
    }
}
