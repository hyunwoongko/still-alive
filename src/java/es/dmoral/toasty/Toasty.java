package es.dmoral.toasty;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.CheckResult;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint({"InflateParams"})
public class Toasty {
    @ColorInt
    private static int DEFAULT_TEXT_COLOR = Color.parseColor("#FFFFFF");
    @ColorInt
    private static int ERROR_COLOR = Color.parseColor("#D50000");
    @ColorInt
    private static int INFO_COLOR = Color.parseColor("#3F51B5");
    private static final Typeface LOADED_TOAST_TYPEFACE = Typeface.create("sans-serif-condensed", 0);
    @ColorInt
    private static int NORMAL_COLOR = Color.parseColor("#353A3E");
    @ColorInt
    private static int SUCCESS_COLOR = Color.parseColor("#388E3C");
    @ColorInt
    private static int WARNING_COLOR = Color.parseColor("#FFA900");
    private static Typeface currentTypeface = LOADED_TOAST_TYPEFACE;
    private static int textSize = 16;
    private static boolean tintIcon = true;

    public static class Config {
        @ColorInt
        private int DEFAULT_TEXT_COLOR = Toasty.DEFAULT_TEXT_COLOR;
        @ColorInt
        private int ERROR_COLOR = Toasty.ERROR_COLOR;
        @ColorInt
        private int INFO_COLOR = Toasty.INFO_COLOR;
        @ColorInt
        private int SUCCESS_COLOR = Toasty.SUCCESS_COLOR;
        @ColorInt
        private int WARNING_COLOR = Toasty.WARNING_COLOR;
        private int textSize = Toasty.textSize;
        private boolean tintIcon = Toasty.tintIcon;
        private Typeface typeface = Toasty.currentTypeface;

        private Config() {
        }

        @CheckResult
        public static Config getInstance() {
            return new Config();
        }

        public static void reset() {
            Toasty.DEFAULT_TEXT_COLOR = Color.parseColor("#FFFFFF");
            Toasty.ERROR_COLOR = Color.parseColor("#D50000");
            Toasty.INFO_COLOR = Color.parseColor("#3F51B5");
            Toasty.SUCCESS_COLOR = Color.parseColor("#388E3C");
            Toasty.WARNING_COLOR = Color.parseColor("#FFA900");
            Toasty.currentTypeface = Toasty.LOADED_TOAST_TYPEFACE;
            Toasty.textSize = 16;
            Toasty.tintIcon = true;
        }

        @CheckResult
        public Config setTextColor(@ColorInt int i) {
            this.DEFAULT_TEXT_COLOR = i;
            return this;
        }

        @CheckResult
        public Config setErrorColor(@ColorInt int i) {
            this.ERROR_COLOR = i;
            return this;
        }

        @CheckResult
        public Config setInfoColor(@ColorInt int i) {
            this.INFO_COLOR = i;
            return this;
        }

        @CheckResult
        public Config setSuccessColor(@ColorInt int i) {
            this.SUCCESS_COLOR = i;
            return this;
        }

        @CheckResult
        public Config setWarningColor(@ColorInt int i) {
            this.WARNING_COLOR = i;
            return this;
        }

        @CheckResult
        public Config setToastTypeface(@NonNull Typeface typeface) {
            this.typeface = typeface;
            return this;
        }

        @CheckResult
        public Config setTextSize(int i) {
            this.textSize = i;
            return this;
        }

        @CheckResult
        public Config tintIcon(boolean z) {
            this.tintIcon = z;
            return this;
        }

        public void apply() {
            Toasty.DEFAULT_TEXT_COLOR = this.DEFAULT_TEXT_COLOR;
            Toasty.ERROR_COLOR = this.ERROR_COLOR;
            Toasty.INFO_COLOR = this.INFO_COLOR;
            Toasty.SUCCESS_COLOR = this.SUCCESS_COLOR;
            Toasty.WARNING_COLOR = this.WARNING_COLOR;
            Toasty.currentTypeface = this.typeface;
            Toasty.textSize = this.textSize;
            Toasty.tintIcon = this.tintIcon;
        }
    }

    private Toasty() {
    }

    @CheckResult
    public static Toast normal(@NonNull Context context, @NonNull CharSequence charSequence) {
        return normal(context, charSequence, 0, null, false);
    }

    @CheckResult
    public static Toast normal(@NonNull Context context, @NonNull CharSequence charSequence, Drawable drawable) {
        return normal(context, charSequence, 0, drawable, true);
    }

    @CheckResult
    public static Toast normal(@NonNull Context context, @NonNull CharSequence charSequence, int i) {
        return normal(context, charSequence, i, null, false);
    }

    @CheckResult
    public static Toast normal(@NonNull Context context, @NonNull CharSequence charSequence, int i, Drawable drawable) {
        return normal(context, charSequence, i, drawable, true);
    }

    @CheckResult
    public static Toast normal(@NonNull Context context, @NonNull CharSequence charSequence, int i, Drawable drawable, boolean z) {
        return custom(context, charSequence, drawable, NORMAL_COLOR, i, z, true);
    }

    @CheckResult
    public static Toast warning(@NonNull Context context, @NonNull CharSequence charSequence) {
        return warning(context, charSequence, 0, true);
    }

    @CheckResult
    public static Toast warning(@NonNull Context context, @NonNull CharSequence charSequence, int i) {
        return warning(context, charSequence, i, true);
    }

    @CheckResult
    public static Toast warning(@NonNull Context context, @NonNull CharSequence charSequence, int i, boolean z) {
        return custom(context, charSequence, ToastyUtils.getDrawable(context, R.drawable.ic_error_outline_white_48dp), WARNING_COLOR, i, z, true);
    }

    @CheckResult
    public static Toast info(@NonNull Context context, @NonNull CharSequence charSequence) {
        return info(context, charSequence, 0, true);
    }

    @CheckResult
    public static Toast info(@NonNull Context context, @NonNull CharSequence charSequence, int i) {
        return info(context, charSequence, i, true);
    }

    @CheckResult
    public static Toast info(@NonNull Context context, @NonNull CharSequence charSequence, int i, boolean z) {
        return custom(context, charSequence, ToastyUtils.getDrawable(context, R.drawable.ic_info_outline_white_48dp), INFO_COLOR, i, z, true);
    }

    @CheckResult
    public static Toast success(@NonNull Context context, @NonNull CharSequence charSequence) {
        return success(context, charSequence, 0, true);
    }

    @CheckResult
    public static Toast success(@NonNull Context context, @NonNull CharSequence charSequence, int i) {
        return success(context, charSequence, i, true);
    }

    @CheckResult
    public static Toast success(@NonNull Context context, @NonNull CharSequence charSequence, int i, boolean z) {
        return custom(context, charSequence, ToastyUtils.getDrawable(context, R.drawable.ic_check_white_48dp), SUCCESS_COLOR, i, z, true);
    }

    @CheckResult
    public static Toast error(@NonNull Context context, @NonNull CharSequence charSequence) {
        return error(context, charSequence, 0, true);
    }

    @CheckResult
    public static Toast error(@NonNull Context context, @NonNull CharSequence charSequence, int i) {
        return error(context, charSequence, i, true);
    }

    @CheckResult
    public static Toast error(@NonNull Context context, @NonNull CharSequence charSequence, int i, boolean z) {
        return custom(context, charSequence, ToastyUtils.getDrawable(context, R.drawable.ic_clear_white_48dp), ERROR_COLOR, i, z, true);
    }

    @CheckResult
    public static Toast custom(@NonNull Context context, @NonNull CharSequence charSequence, Drawable drawable, int i, boolean z) {
        return custom(context, charSequence, drawable, -1, i, z, false);
    }

    @CheckResult
    public static Toast custom(@NonNull Context context, @NonNull CharSequence charSequence, @DrawableRes int i, @ColorInt int i2, int i3, boolean z, boolean z2) {
        return custom(context, charSequence, ToastyUtils.getDrawable(context, i), i2, i3, z, z2);
    }

    @SuppressLint({"ShowToast"})
    @CheckResult
    public static Toast custom(@NonNull Context context, @NonNull CharSequence charSequence, Drawable drawable, @ColorInt int i, int i2, boolean z, boolean z2) {
        Drawable tint9PatchDrawableFrame;
        Toast makeText = Toast.makeText(context, "", i2);
        View inflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.toast_layout, null);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.toast_icon);
        TextView textView = (TextView) inflate.findViewById(R.id.toast_text);
        if (z2) {
            tint9PatchDrawableFrame = ToastyUtils.tint9PatchDrawableFrame(context, i);
        } else {
            tint9PatchDrawableFrame = ToastyUtils.getDrawable(context, R.drawable.toast_frame);
        }
        ToastyUtils.setBackground(inflate, tint9PatchDrawableFrame);
        if (!z) {
            imageView.setVisibility(8);
        } else if (drawable == null) {
            throw new IllegalArgumentException("Avoid passing 'icon' as null if 'withIcon' is set to true");
        } else {
            if (tintIcon) {
                drawable = ToastyUtils.tintIcon(drawable, DEFAULT_TEXT_COLOR);
            }
            ToastyUtils.setBackground(imageView, drawable);
        }
        textView.setText(charSequence);
        textView.setTextColor(DEFAULT_TEXT_COLOR);
        textView.setTypeface(currentTypeface);
        textView.setTextSize(2, (float) textSize);
        makeText.setView(inflate);
        return makeText;
    }
}
