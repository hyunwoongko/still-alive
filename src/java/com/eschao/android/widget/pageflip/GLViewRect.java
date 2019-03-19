package com.eschao.android.widget.pageflip;

public final class GLViewRect {
    float bottom;
    float halfH;
    float halfW;
    float height;
    float left;
    float marginL;
    float marginR;
    float right;
    float surfaceH;
    float surfaceW;
    float top;
    float width;

    public GLViewRect() {
        this.left = 0.0f;
        this.right = 0.0f;
        this.top = 0.0f;
        this.bottom = 0.0f;
        this.width = 0.0f;
        this.height = 0.0f;
        this.halfW = 0.0f;
        this.halfH = 0.0f;
        this.marginL = 0.0f;
        this.marginR = 0.0f;
        this.surfaceW = 0.0f;
        this.surfaceH = 0.0f;
    }

    public GLViewRect(float f, float f2, float f3, float f4) {
        set(f, f2, f3, f4);
    }

    public GLViewRect setMargin(float f, float f2) {
        return set(this.surfaceW, this.surfaceH, f, f2);
    }

    public GLViewRect set(float f, float f2) {
        return set(f, f2, this.marginL, this.marginR);
    }

    public GLViewRect set(float f, float f2, float f3, float f4) {
        this.surfaceW = f;
        this.surfaceH = f2;
        this.marginL = f3;
        this.marginR = f4;
        this.width = (f - f3) - f4;
        this.height = f2;
        this.halfW = this.width * 0.5f;
        this.halfH = this.height * 0.5f;
        this.left = (-this.halfW) + f3;
        this.right = this.halfW - f4;
        this.top = this.halfH;
        this.bottom = -this.halfH;
        return this;
    }

    public float minOfWH() {
        return this.width > this.height ? this.width : this.height;
    }

    public float toOpenGLX(float f) {
        return f - this.halfW;
    }

    public float toOpenGLY(float f) {
        return this.halfH - f;
    }
}
