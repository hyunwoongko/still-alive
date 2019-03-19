package com.nightonke.blurlockview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.nightonke.blurlockview.BigButtonView.OnPressListener;
import com.nightonke.blurlockview.Directions.HideType;
import com.nightonke.blurlockview.Directions.ShowType;
import com.nightonke.blurlockview.Eases.EaseType;
import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.Stack;

public class BlurLockView extends FrameLayout implements OnPressListener, SmallButtonView.OnPressListener {
    private final char[][] CHARS;
    private boolean animationIsPlaying;
    private BigButtonView[] bigButtonViews;
    private String correctPassword;
    private int incorrectInputTimes;
    private Indicator indicator;
    private TextView leftButton;
    private BlurView mBlurView;
    private OnLeftButtonClickListener onLeftButtonClickListener;
    private OnPasswordInputListener onPasswordInputListener;
    private int passwordLength;
    private Stack<String> passwordStack;
    private TextView rightButton;
    private SmallButtonView[][] smallButtonViews;
    private TextView title;
    private Password type;
    private Typeface typeface;

    public interface OnLeftButtonClickListener {
        void onClick();
    }

    public interface OnPasswordInputListener {
        void correct(String str);

        void incorrect(String str);

        void input(String str);
    }

    public BlurLockView(Context context) {
        this(context, null);
    }

    public BlurLockView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.CHARS = new char[][]{new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'}, new char[]{'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P'}, new char[]{'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L'}, new char[]{'Z', 'X', 'C', 'V', 'B', 'N', 'M'}};
        this.type = Password.NUMBER;
        this.passwordLength = 4;
        this.correctPassword = null;
        this.incorrectInputTimes = 0;
        this.animationIsPlaying = false;
        this.passwordStack = null;
        init();
    }

    private void init() {
        int i;
        SmallButtonView smallButtonView;
        StringBuilder stringBuilder;
        LayoutParams layoutParams;
        SmallButtonView smallButtonView2;
        LayoutParams layoutParams2;
        LayoutInflater.from(getContext()).inflate(R.layout.number_blur_lock_view, this, true);
        this.bigButtonViews = new BigButtonView[10];
        int i2 = 0;
        this.bigButtonViews[0] = (BigButtonView) findViewById(R.id.button_0);
        this.bigButtonViews[1] = (BigButtonView) findViewById(R.id.button_1);
        this.bigButtonViews[2] = (BigButtonView) findViewById(R.id.button_2);
        this.bigButtonViews[3] = (BigButtonView) findViewById(R.id.button_3);
        this.bigButtonViews[4] = (BigButtonView) findViewById(R.id.button_4);
        this.bigButtonViews[5] = (BigButtonView) findViewById(R.id.button_5);
        this.bigButtonViews[6] = (BigButtonView) findViewById(R.id.button_6);
        this.bigButtonViews[7] = (BigButtonView) findViewById(R.id.button_7);
        this.bigButtonViews[8] = (BigButtonView) findViewById(R.id.button_8);
        this.bigButtonViews[9] = (BigButtonView) findViewById(R.id.button_9);
        String[] stringArray = getResources().getStringArray(R.array.default_big_button_text);
        String[] stringArray2 = getResources().getStringArray(R.array.default_big_button_sub_text);
        for (int i3 = 0; i3 < 10; i3++) {
            this.bigButtonViews[i3].setOnPressListener(this);
            this.bigButtonViews[i3].setText(stringArray[i3]);
            this.bigButtonViews[i3].setSubText(stringArray2[i3]);
        }
        this.bigButtonViews[0].setSubTextVisibility(8);
        this.bigButtonViews[1].setSubTextVisibility(4);
        this.smallButtonViews = (SmallButtonView[][]) Array.newInstance(SmallButtonView.class, new int[]{4, 10});
        Display defaultDisplay = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        int i4 = (point.x - 66) / 10;
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.line_1);
        for (i = 0; i < 10; i++) {
            this.smallButtonViews[0][i] = new SmallButtonView(getContext());
            this.smallButtonViews[0][i].setOnPressListener(this);
            smallButtonView = this.smallButtonViews[0][i];
            stringBuilder = new StringBuilder();
            stringBuilder.append(this.CHARS[0][i]);
            stringBuilder.append("");
            smallButtonView.setText(stringBuilder.toString());
            this.smallButtonViews[0][i].setWidth(i4);
            this.smallButtonViews[0][i].setHeight(i4);
            layoutParams = new LayoutParams(i4, i4);
            if (i == 0) {
                layoutParams.setMargins(6, 12, 3, 12);
            } else if (i == 9) {
                layoutParams.setMargins(3, 12, 6, 12);
            } else {
                layoutParams.setMargins(3, 12, 3, 12);
            }
            linearLayout.addView(this.smallButtonViews[0][i], layoutParams);
        }
        linearLayout = (LinearLayout) findViewById(R.id.line_2);
        for (i = 0; i < 10; i++) {
            this.smallButtonViews[1][i] = new SmallButtonView(getContext());
            this.smallButtonViews[1][i].setOnPressListener(this);
            smallButtonView = this.smallButtonViews[1][i];
            stringBuilder = new StringBuilder();
            stringBuilder.append(this.CHARS[1][i]);
            stringBuilder.append("");
            smallButtonView.setText(stringBuilder.toString());
            this.smallButtonViews[1][i].setWidth(i4);
            this.smallButtonViews[1][i].setHeight(i4);
            layoutParams = new LayoutParams(i4, i4);
            if (i == 0) {
                layoutParams.setMargins(6, 12, 3, 12);
            } else if (i == 9) {
                layoutParams.setMargins(3, 12, 6, 12);
            } else {
                layoutParams.setMargins(3, 12, 3, 12);
            }
            linearLayout.addView(this.smallButtonViews[1][i], layoutParams);
        }
        LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.line_3);
        for (int i5 = 0; i5 < 9; i5++) {
            this.smallButtonViews[2][i5] = new SmallButtonView(getContext());
            this.smallButtonViews[2][i5].setOnPressListener(this);
            smallButtonView2 = this.smallButtonViews[2][i5];
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(this.CHARS[2][i5]);
            stringBuilder2.append("");
            smallButtonView2.setText(stringBuilder2.toString());
            this.smallButtonViews[2][i5].setWidth(i4);
            this.smallButtonViews[2][i5].setHeight(i4);
            layoutParams2 = new LayoutParams(i4, i4);
            if (i5 == 0) {
                layoutParams2.setMargins(6, 12, 3, 12);
            } else if (i5 == 8) {
                layoutParams2.setMargins(3, 12, 6, 12);
            } else {
                layoutParams2.setMargins(3, 12, 3, 12);
            }
            linearLayout2.addView(this.smallButtonViews[2][i5], layoutParams2);
        }
        linearLayout2 = (LinearLayout) findViewById(R.id.line_4);
        while (i2 < 7) {
            this.smallButtonViews[3][i2] = new SmallButtonView(getContext());
            this.smallButtonViews[3][i2].setOnPressListener(this);
            smallButtonView2 = this.smallButtonViews[3][i2];
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append(this.CHARS[3][i2]);
            stringBuilder3.append("");
            smallButtonView2.setText(stringBuilder3.toString());
            this.smallButtonViews[3][i2].setWidth(i4);
            this.smallButtonViews[3][i2].setHeight(i4);
            layoutParams2 = new LayoutParams(i4, i4);
            if (i2 == 0) {
                layoutParams2.setMargins(6, 12, 3, 12);
            } else if (i2 == 6) {
                layoutParams2.setMargins(3, 12, 6, 12);
            } else {
                layoutParams2.setMargins(3, 12, 3, 12);
            }
            linearLayout2.addView(this.smallButtonViews[3][i2], layoutParams2);
            i2++;
        }
        this.passwordStack = new Stack();
        this.mBlurView = (BlurView) findViewById(R.id.blurview);
        this.mBlurView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
            }
        });
        Resources resources = getResources();
        this.indicator = (Indicator) findViewById(R.id.indicator);
        this.indicator.setPasswordLength(this.passwordLength);
        this.title = (TextView) findViewById(R.id.title);
        this.title.setTextColor(ContextCompat.getColor(getContext(), R.color.default_title_text_color));
        this.title.setTextSize((float) resources.getInteger(R.integer.default_title_text_size));
        this.leftButton = (TextView) findViewById(R.id.left_button);
        this.leftButton.setTextColor(ContextCompat.getColor(getContext(), R.color.default_left_button_text_color));
        this.leftButton.setTextSize((float) resources.getInteger(R.integer.default_left_button_text_size));
        this.leftButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (BlurLockView.this.onLeftButtonClickListener != null) {
                    BlurLockView.this.onLeftButtonClickListener.onClick();
                }
            }
        });
        this.rightButton = (TextView) findViewById(R.id.right_button);
        this.rightButton.setTextColor(ContextCompat.getColor(getContext(), R.color.default_right_button_text_color));
        this.rightButton.setTextSize((float) resources.getInteger(R.integer.default_right_button_text_size));
        this.rightButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (BlurLockView.this.passwordStack.size() > 0) {
                    BlurLockView.this.passwordStack.pop();
                    BlurLockView.this.indicator.delete();
                }
            }
        });
    }

    private void showText(boolean z) {
        if (!this.animationIsPlaying) {
            this.animationIsPlaying = true;
            if (z) {
                ObjectAnimator.ofFloat(findViewById(R.id.layout_123), "alpha", new float[]{1.0f, 0.0f}).setDuration(500).start();
                ObjectAnimator.ofFloat(findViewById(R.id.layout_456), "alpha", new float[]{1.0f, 0.0f}).setDuration(500).start();
                ObjectAnimator.ofFloat(findViewById(R.id.layout_789), "alpha", new float[]{1.0f, 0.0f}).setDuration(500).start();
                ObjectAnimator.ofFloat(findViewById(R.id.button_0), "alpha", new float[]{1.0f, 0.0f}).setDuration(500).start();
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(findViewById(R.id.text_layout), "alpha", new float[]{0.0f, 1.0f});
                ofFloat.setDuration(500).addListener(new AnimatorListenerAdapter() {
                    public void onAnimationStart(Animator animator) {
                        super.onAnimationStart(animator);
                        BlurLockView.this.findViewById(R.id.text_layout).setVisibility(0);
                    }

                    public void onAnimationEnd(Animator animator) {
                        super.onAnimationEnd(animator);
                        BlurLockView.this.findViewById(R.id.layout_123).setVisibility(4);
                        BlurLockView.this.findViewById(R.id.layout_456).setVisibility(4);
                        BlurLockView.this.findViewById(R.id.layout_789).setVisibility(4);
                        BlurLockView.this.findViewById(R.id.button_0).setVisibility(4);
                        BlurLockView.this.animationIsPlaying = false;
                    }
                });
                ofFloat.start();
            } else {
                findViewById(R.id.layout_123).setVisibility(4);
                findViewById(R.id.layout_456).setVisibility(4);
                findViewById(R.id.layout_789).setVisibility(4);
                findViewById(R.id.button_0).setVisibility(4);
                findViewById(R.id.text_layout).setVisibility(0);
                this.animationIsPlaying = false;
            }
        }
    }

    private void showNumber(boolean z) {
        if (!this.animationIsPlaying) {
            this.animationIsPlaying = true;
            if (z) {
                ObjectAnimator.ofFloat(findViewById(R.id.layout_123), "alpha", new float[]{0.0f, 1.0f}).setDuration(500).start();
                ObjectAnimator.ofFloat(findViewById(R.id.layout_456), "alpha", new float[]{0.0f, 1.0f}).setDuration(500).start();
                ObjectAnimator.ofFloat(findViewById(R.id.layout_789), "alpha", new float[]{0.0f, 1.0f}).setDuration(500).start();
                ObjectAnimator.ofFloat(findViewById(R.id.button_0), "alpha", new float[]{0.0f, 1.0f}).setDuration(500).start();
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(findViewById(R.id.text_layout), "alpha", new float[]{1.0f, 0.0f});
                ofFloat.setDuration(500).addListener(new AnimatorListenerAdapter() {
                    public void onAnimationStart(Animator animator) {
                        super.onAnimationStart(animator);
                        BlurLockView.this.findViewById(R.id.layout_123).setVisibility(0);
                        BlurLockView.this.findViewById(R.id.layout_456).setVisibility(0);
                        BlurLockView.this.findViewById(R.id.layout_789).setVisibility(0);
                        BlurLockView.this.findViewById(R.id.button_0).setVisibility(0);
                    }

                    public void onAnimationEnd(Animator animator) {
                        super.onAnimationEnd(animator);
                        BlurLockView.this.findViewById(R.id.text_layout).setVisibility(4);
                        BlurLockView.this.animationIsPlaying = false;
                    }
                });
                ofFloat.start();
            } else {
                findViewById(R.id.layout_123).setVisibility(0);
                findViewById(R.id.layout_456).setVisibility(0);
                findViewById(R.id.layout_789).setVisibility(0);
                findViewById(R.id.button_0).setVisibility(0);
                findViewById(R.id.text_layout).setVisibility(4);
                this.animationIsPlaying = false;
            }
        }
    }

    public void setBlurredView(View view) {
        this.mBlurView.setBlurredView(view);
    }

    public void setOnLeftButtonClickListener(OnLeftButtonClickListener onLeftButtonClickListener) {
        this.onLeftButtonClickListener = onLeftButtonClickListener;
    }

    public void setOnPasswordInputListener(OnPasswordInputListener onPasswordInputListener) {
        this.onPasswordInputListener = onPasswordInputListener;
    }

    public void onPress(String str) {
        if (this.correctPassword == null) {
            throw new RuntimeException("The correct password has NOT been set!");
        } else if (this.passwordStack.size() < this.passwordLength) {
            this.passwordStack.push(str);
            this.indicator.add();
            StringBuilder stringBuilder = new StringBuilder("");
            Iterator it = this.passwordStack.iterator();
            while (it.hasNext()) {
                stringBuilder.append((String) it.next());
            }
            str = stringBuilder.toString();
            if (this.correctPassword.equals(str)) {
                if (this.onPasswordInputListener != null) {
                    this.onPasswordInputListener.correct(str);
                }
            } else if (this.correctPassword.length() <= str.length()) {
                if (this.onPasswordInputListener != null) {
                    this.onPasswordInputListener.incorrect(str);
                }
                this.incorrectInputTimes++;
                this.indicator.clear();
                this.passwordStack.clear();
            } else if (this.onPasswordInputListener != null) {
                this.onPasswordInputListener.input(str);
            }
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getPointerCount() <= 1) {
            return super.dispatchTouchEvent(motionEvent);
        }
        if (Password.NUMBER.equals(this.type)) {
            for (BigButtonView clearAnimation : this.bigButtonViews) {
                clearAnimation.clearAnimation();
            }
        } else if (Password.TEXT.equals(this.type)) {
            for (int i = 0; i < this.smallButtonViews.length; i++) {
                for (int i2 = 0; i2 < this.smallButtonViews[i].length; i2++) {
                    if (this.smallButtonViews[i][i2] != null) {
                        this.smallButtonViews[i][i2].clearAnimation();
                    }
                }
            }
        }
        return true;
    }

    public void setBigButtonViewsBackground(int i) {
        for (int i2 = 0; i2 < 10; i2++) {
            this.bigButtonViews[i2].setBackground(i);
        }
    }

    public void setBigButtonViewsClickEffect(int i) {
        for (int i2 = 0; i2 < 10; i2++) {
            this.bigButtonViews[i2].setEffect(i);
        }
    }

    public void setBigButtonViewsClickEffectDuration(int i) {
        for (int i2 = 0; i2 < 10; i2++) {
            this.bigButtonViews[i2].setEffectDuration(i);
        }
    }

    public void setSmallButtonViewsBackground(int i) {
        for (int i2 = 0; i2 < this.smallButtonViews.length; i2++) {
            for (int i3 = 0; i3 < this.smallButtonViews[i2].length; i3++) {
                if (this.smallButtonViews[i2][i3] != null) {
                    this.smallButtonViews[i2][i3].setBackground(i);
                }
            }
        }
    }

    public void setSmallButtonViewsClickEffect(int i) {
        for (int i2 = 0; i2 < this.smallButtonViews.length; i2++) {
            for (int i3 = 0; i3 < this.smallButtonViews[i2].length; i3++) {
                if (this.smallButtonViews[i2][i3] != null) {
                    this.smallButtonViews[i2][i3].setEffect(i);
                }
            }
        }
    }

    public void setSmallButtonViewsClickEffectDuration(int i) {
        for (int i2 = 0; i2 < this.smallButtonViews.length; i2++) {
            for (int i3 = 0; i3 < this.smallButtonViews[i2].length; i3++) {
                if (this.smallButtonViews[i2][i3] != null) {
                    this.smallButtonViews[i2][i3].setEffectDuration(i);
                }
            }
        }
    }

    public void setTypeface(Typeface typeface) {
        this.typeface = typeface;
        if (this.type.equals(Password.NUMBER)) {
            for (int i = 0; i < 10; i++) {
                this.bigButtonViews[i].setTypeFace(typeface);
            }
        } else if (this.type.equals(Password.TEXT)) {
            for (int i2 = 0; i2 < this.smallButtonViews.length; i2++) {
                for (int i3 = 0; i3 < this.smallButtonViews[i2].length; i3++) {
                    if (this.smallButtonViews[i2][i3] != null) {
                        this.smallButtonViews[i2][i3].setTypeFace(typeface);
                    }
                }
            }
        }
        this.title.setTypeface(typeface);
        this.leftButton.setTypeface(typeface);
        this.rightButton.setTypeface(typeface);
    }

    public void setTextColor(int i) {
        if (this.type.equals(Password.NUMBER)) {
            for (int i2 = 0; i2 < 10; i2++) {
                this.bigButtonViews[i2].setTextColor(i);
                this.bigButtonViews[i2].setSubTextColor(i);
            }
        } else if (this.type.equals(Password.TEXT)) {
            for (int i3 = 0; i3 < this.smallButtonViews.length; i3++) {
                for (int i4 = 0; i4 < this.smallButtonViews[i3].length; i4++) {
                    if (this.smallButtonViews[i3][i4] != null) {
                        this.smallButtonViews[i3][i4].setTextColor(i);
                    }
                }
            }
        }
        this.title.setTextColor(i);
        this.leftButton.setTextColor(i);
        this.rightButton.setTextColor(i);
    }

    public void setPasswordLength(int i) {
        this.passwordLength = i;
        this.indicator.setPasswordLength(i);
        this.passwordStack.clear();
        this.correctPassword = null;
    }

    public Password getType() {
        return this.type;
    }

    public void setType(Password password, boolean z) {
        if (!this.animationIsPlaying) {
            this.type = password;
            this.indicator.clear();
            this.passwordStack.clear();
            if (Password.NUMBER.equals(password)) {
                showNumber(z);
            } else if (Password.TEXT.equals(password)) {
                showText(z);
            }
        }
    }

    public void setTitle(String str) {
        this.title.setText(str);
    }

    public void setLeftButton(String str) {
        this.leftButton.setText(str);
    }

    public void setRightButton(String str) {
        this.rightButton.setText(str);
    }

    public void setCorrectPassword(String str) {
        setPasswordLength(str.length());
        this.correctPassword = str;
    }

    public void setIncorrectInputTimes(int i) {
        this.incorrectInputTimes = i;
    }

    public int getIncorrectInputTimes() {
        return this.incorrectInputTimes;
    }

    public void update() {
        this.mBlurView.invalidate();
    }

    public void show(int i, ShowType showType, EaseType easeType) {
        if (!this.animationIsPlaying) {
            this.animationIsPlaying = true;
            this.indicator.clear();
            this.passwordStack.clear();
            ObjectAnimator objectAnimator = null;
            setVisibility(0);
            if (showType.equals(ShowType.FROM_TOP_TO_BOTTOM)) {
                objectAnimator = ObjectAnimator.ofFloat(this, "translationY", new float[]{getTranslationY() - ((float) getHeight()), getTranslationY()});
            } else if (showType.equals(ShowType.FROM_RIGHT_TO_LEFT)) {
                objectAnimator = ObjectAnimator.ofFloat(this, "translationX", new float[]{getTranslationX() + ((float) getWidth()), getTranslationX()});
            } else if (showType.equals(ShowType.FROM_BOTTOM_TO_TOP)) {
                objectAnimator = ObjectAnimator.ofFloat(this, "translationY", new float[]{getTranslationY() + ((float) getHeight()), getTranslationY()});
            } else if (showType.equals(ShowType.FROM_LEFT_TO_RIGHT)) {
                objectAnimator = ObjectAnimator.ofFloat(this, "translationX", new float[]{getTranslationX() - ((float) getWidth()), getTranslationX()});
            } else if (showType.equals(ShowType.FADE_IN)) {
                objectAnimator = ObjectAnimator.ofFloat(this, "alpha", new float[]{0.0f, 1.0f});
            }
            objectAnimator.setDuration((long) i);
            objectAnimator.addUpdateListener(new AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    BlurLockView.this.update();
                }
            });
            objectAnimator.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animator) {
                    super.onAnimationEnd(animator);
                    BlurLockView.this.animationIsPlaying = false;
                }
            });
            objectAnimator.setInterpolator(InterpolatorFactory.getInterpolator(easeType));
            objectAnimator.start();
        }
    }

    public void hide(int i, HideType hideType, EaseType easeType) {
        if (!this.animationIsPlaying) {
            this.animationIsPlaying = true;
            ObjectAnimator objectAnimator = null;
            final float translationX = getTranslationX();
            final float translationY = getTranslationY();
            if (hideType.equals(HideType.FROM_TOP_TO_BOTTOM)) {
                objectAnimator = ObjectAnimator.ofFloat(this, "translationY", new float[]{getTranslationY(), getTranslationY() + ((float) getHeight())});
            } else if (hideType.equals(HideType.FROM_RIGHT_TO_LEFT)) {
                objectAnimator = ObjectAnimator.ofFloat(this, "translationX", new float[]{getTranslationX(), getTranslationX() - ((float) getWidth())});
            } else if (hideType.equals(HideType.FROM_BOTTOM_TO_TOP)) {
                objectAnimator = ObjectAnimator.ofFloat(this, "translationY", new float[]{getTranslationY(), getTranslationY() - ((float) getHeight())});
            } else if (hideType.equals(HideType.FROM_LEFT_TO_RIGHT)) {
                objectAnimator = ObjectAnimator.ofFloat(this, "translationX", new float[]{getTranslationX(), getTranslationX() + ((float) getWidth())});
            } else if (hideType.equals(HideType.FADE_OUT)) {
                objectAnimator = ObjectAnimator.ofFloat(this, "alpha", new float[]{1.0f, 0.0f});
            }
            objectAnimator.setDuration((long) i);
            objectAnimator.addUpdateListener(new AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    BlurLockView.this.update();
                }
            });
            objectAnimator.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animator) {
                    super.onAnimationEnd(animator);
                    BlurLockView.this.setVisibility(4);
                    BlurLockView.this.setTranslationX(translationX);
                    BlurLockView.this.setTranslationY(translationY);
                    BlurLockView.this.setAlpha(1.0f);
                    BlurLockView.this.animationIsPlaying = false;
                }
            });
            objectAnimator.setInterpolator(InterpolatorFactory.getInterpolator(easeType));
            objectAnimator.start();
        }
    }

    public TextView getTitle() {
        return this.title;
    }

    public TextView getLeftButton() {
        return this.leftButton;
    }

    public TextView getRightButton() {
        return this.rightButton;
    }

    public BigButtonView[] getBigButtonViews() {
        return this.bigButtonViews;
    }

    public SmallButtonView[][] getSmallButtonViews() {
        return this.smallButtonViews;
    }

    public void setBlurRadius(int i) {
        this.mBlurView.setBlurRadius(i);
        update();
    }

    public int getBlurRadius() {
        return this.mBlurView.getBlurRadius();
    }

    public void setDownsampleFactor(int i) {
        this.mBlurView.setDownsampleFactor(i);
        update();
    }

    public int getDownsampleFactor() {
        return this.mBlurView.getDownsampleFactor();
    }

    public void setOverlayColor(int i) {
        this.mBlurView.setOverlayColor(i);
        update();
    }

    public int getOverlayColor() {
        return this.mBlurView.getmOverlayColor();
    }
}
