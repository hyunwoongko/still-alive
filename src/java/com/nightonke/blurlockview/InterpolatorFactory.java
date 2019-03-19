package com.nightonke.blurlockview;

import android.view.animation.Interpolator;
import com.nightonke.blurlockview.Eases.EaseType;

public class InterpolatorFactory {

    public static class BLVInterpolator implements Interpolator {
        private EaseType easeType;

        public BLVInterpolator(EaseType easeType) {
            this.easeType = easeType;
        }

        public float getInterpolation(float f) {
            return this.easeType.getOffset(f);
        }
    }

    public static BLVInterpolator getInterpolator(EaseType easeType) {
        return new BLVInterpolator(easeType);
    }
}
