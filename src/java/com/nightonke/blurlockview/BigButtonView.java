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

public class BigButtonView extends FrameLayout {
    private View clickEffect;
    private ObjectAnimator clickEffectAnimator;
    private int duration;
    private FrameLayout frameLayout;
    private OnPressListener onPressListener;
    private TextView subText;
    private String subTextString;
    private TextView text;
    private String textString;

    public interface OnPressListener {
        void onPress(String str);
    }

    public BigButtonView(Context context) {
        this(context, null);
    }

    public BigButtonView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.textString = "";
        this.subTextString = "";
        this.duration = 500;
        LayoutInflater.from(context).inflate(R.layout.big_button_view, this, true);
        Resources resources = getResources();
        this.frameLayout = (FrameLayout) findViewById(R.id.frame_layout);
        this.text = (TextView) findViewById(R.id.text);
        this.text.setText(this.textString);
        this.text.setTextColor(ContextCompat.getColor(context, R.color.default_big_button_text_color));
        this.text.setTextSize((float) resources.getInteger(R.integer.default_big_button_text_size));
        this.subText = (TextView) findViewById(R.id.sub_text);
        this.subText.setText(this.subTextString);
        this.subText.setTextColor(ContextCompat.getColor(context, R.color.default_big_button_sub_text_color));
        this.subText.setTextSize((float) resources.getInteger(R.integer.default_big_button_sub_text_size));
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
    }

    public void setHeight(int i) {
        LayoutParams layoutParams = this.frameLayout.getLayoutParams();
        layoutParams.height = i;
        this.frameLayout.setLayoutParams(layoutParams);
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

    public void setSubTextSize(int i) {
        this.subText.setTextSize(2, (float) i);
    }

    public void setTextColor(int i) {
        this.text.setTextColor(i);
    }

    public void setSubTextColor(int i) {
        this.subText.setTextColor(i);
    }

    public void setTypeFace(Typeface typeface) {
        this.text.setTypeface(typeface);
        this.subText.setTypeface(typeface);
    }

    public void setText(String str) {
        this.textString = str;
        if (this.text != null) {
            this.text.setText(str);
        }
    }

    public void setSubText(String str) {
        this.subTextString = str;
        if (this.subText != null) {
            this.subText.setText(str);
        }
    }

    public void setSubTextVisibility(int i) {
        if (i == 8) {
            this.text.setGravity(17);
        } else {
            this.text.setGravity(81);
        }
        this.subText.setVisibility(i);
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
