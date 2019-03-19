package com.nightonke.blurlockview.Eases;

public class EaseOutBounce extends CubicBezier {
    public float getOffset(float f) {
        f /= 1.0f;
        if (f < 0.36363637f) {
            return (((7.5625f * f) * f) * 1.0f) + 0.0f;
        }
        if (f < 0.72727275f) {
            f -= 0.54545456f;
            return ((((7.5625f * f) * f) + 0.75f) * 1.0f) + 0.0f;
        } else if (((double) f) < 0.9090909090909091d) {
            f -= 0.8181818f;
            return ((((7.5625f * f) * f) + 0.9375f) * 1.0f) + 0.0f;
        } else {
            f -= 0.95454544f;
            return ((((7.5625f * f) * f) + 0.984375f) * 1.0f) + 0.0f;
        }
    }
}
