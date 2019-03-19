package com.eschao.android.widget.pageflip;

public final class ShadowColor {
    float endAlpha;
    float endColor;
    float startAlpha;
    float startColor;

    public ShadowColor() {
        this.startColor = 0.0f;
        this.startAlpha = 0.0f;
        this.endColor = 0.0f;
        this.endAlpha = 0.0f;
    }

    public ShadowColor(float f, float f2, float f3, float f4) {
        set(f, f2, f3, f4);
    }

    public void set(float f, float f2, float f3, float f4) {
        if (f < 0.0f || f > 1.0f || f2 < 0.0f || f2 > 1.0f || f3 < 0.0f || f3 > 1.0f || f4 < 0.0f || f4 > 1.0f) {
            throw new IllegalArgumentException("Illegal color or alpha value!");
        }
        this.startColor = f;
        this.startAlpha = f2;
        this.endColor = f3;
        this.endAlpha = f4;
    }
}
