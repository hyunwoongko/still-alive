package com.nightonke.blurlockview.Eases;

public class EaseInOutElastic extends CubicBezier {
    public float getOffset(float f) {
        if (f == 0.0f) {
            return 0.0f;
        }
        f /= 0.5f;
        if (f == 2.0f) {
            return 1.0f;
        }
        if (f < 1.0f) {
            f -= 1.0f;
            return (((((float) Math.pow(2.0d, (double) (10.0f * f))) * 1.0f) * ((float) Math.sin((double) ((((f * 1.0f) - 0.112500004f) * 6.2831855f) / 0.45000002f)))) * -0.5f) + 0.0f;
        }
        f -= 1.0f;
        return ((((((float) Math.pow(2.0d, (double) (-10.0f * f))) * 1.0f) * ((float) Math.sin((double) ((((f * 1.0f) - 0.112500004f) * 6.2831855f) / 0.45000002f)))) * 0.5f) + 1.0f) + 0.0f;
    }
}
