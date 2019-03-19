package com.nightonke.blurlockview;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.TextView;

public class SmallButtonView extends FrameLayout {
    private View clickEffect;
    private ObjectAnimator clickEffectAnimator;
    private int duration;
    private FrameLayout frameLayout;
    private OnPressListener onPressListener;
    private TextView text;
    private String textString;

    public interface OnPressListener {
        void onPress(String str);
    }

    public SmallButtonView(Context context) {
        this(context, null);
    }

    public SmallButtonView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.textString = "";
        this.duration = 500;
        LayoutInflater.from(context).inflate(R.layout.small_button_view, this, true);
        Resources resources = getResources();
        this.frameLayout = (FrameLayout) findViewById(R.id.frame_layout);
        this.text = (TextView) findViewById(R.id.text);
        this.text.setText(this.textString);
        this.text.setTextColor(ContextCompat.getColor(context, R.color.default_small_button_text_color));
        this.text.setTextSize((float) resources.getInteger(R.integer.default_small_button_text_size));
        this.clickEffect = findViewById(R.id.click_effect);
        this.clickEffect.setAlpha(0.0f);
        this.clickEffectAnimator = ObjectAnimator.ofFloat(this.clickEffect, "alpha", new float[]{1.0f, 0.0f});
        this.clickEffectAnimator.setDuration((long) this.duration);
    }

    public void setOnPressListener(OnPressListener onPressListener) {
        this.onPressListener = onPressListener;
    }

    public void setWidth(int i) {
        LayoutParams layoutParams = this.frameLayout.getLayoutParams();
        layoutParams.width = i;
        this.frameLayout.setLayoutParams(layoutParams);
        layoutParams = this.clickEffect.getLayoutParams();
        layoutParams.width = i;
        this.clickEffect.setLayoutParams(layoutParams);
    }

    public void setHeight(int i) {
        LayoutParams layoutParams = this.frameLayout.getLayoutParams();
        layoutParams.height = i;
        this.frameLayout.setLayoutParams(layoutParams);
        layoutParams = this.clickEffect.getLayoutParams();
        layoutParams.height = i;
        this.clickEffect.setLayoutParams(layoutParams);
    }

    public void setBackground(int i) {
        this.frameLayout.setBackgroundResource(i);
    }

    public void setEffect(int i) {
        this.clickEffect.setBackgroundResource(i);
    }

    public void setEffectDuration(int i) {
        this.duration = i;
    }

    public void setTextSize(int i) {
        this.text.setTextSize(2, (float) i);
    }

    public void setTextColor(int i) {
        this.text.setTextColor(i);
    }

    public void setTypeFace(Typeface typeface) {
        this.text.setTypeface(typeface);
    }

    public void setText(String str) {
        this.textString = str;
        if (this.text != null) {
            this.text.setText(str);
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                if (this.onPressListener != null) {
                    this.onPressListener.onPress(this.textString);
                }
                this.clickEffectAnimator.cancel();
                this.clickEffect.setAlpha(1.0f);
                break;
            case 1:
                this.clickEffectAnimator.start();
                break;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void clearAnimation() {
        if (this.clickEffect.getAlpha() == 1.0f) {
            this.clickEffectAnimator.cancel();
            this.clickEffectAnimator.start();
        }
    }
}
