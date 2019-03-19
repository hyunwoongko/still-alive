package com.eschao.android.widget.pageflip;

final class ShadowWidth {
    float mMax;
    float mMin;
    float mRatio;

    public ShadowWidth(float f, float f2, float f3) {
        set(f, f2, f3);
    }

    public void set(float f, float f2, float f3) {
        if (f < 0.0f || f2 < 0.0f || f > f2 || f3 <= 0.0f || f3 > 1.0f) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("One of Min(");
            stringBuilder.append(f);
            stringBuilder.append(") Max(");
            stringBuilder.append(f2);
            stringBuilder.append(") Ration(");
            stringBuilder.append(f3);
            stringBuilder.append(")is invalid!");
            throw new IllegalArgumentException(stringBuilder.toString());
        }
        this.mMin = f;
        this.mMax = f2;
        this.mRatio = f3;
    }

    public float width(float f) {
        f *= this.mRatio;
        if (f < this.mMin) {
            return this.mMin;
        }
        return f > this.mMax ? this.mMax : f;
    }
}
