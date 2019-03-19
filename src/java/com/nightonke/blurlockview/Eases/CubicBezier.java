package com.nightonke.blurlockview.Eases;

import android.graphics.PointF;

public abstract class CubicBezier {
    private PointF a = new PointF();
    private PointF b = new PointF();
    private PointF c = new PointF();
    private PointF end;
    private PointF start;

    public void init(float f, float f2, float f3, float f4) {
        setStart(new PointF(f, f2));
        setEnd(new PointF(f3, f4));
    }

    public void init(double d, double d2, double d3, double d4) {
        init((float) d, (float) d2, (float) d3, (float) d4);
    }

    public float getOffset(float f) {
        return getBezierCoordinateY(getXForTime(f));
    }

    private float getBezierCoordinateY(float f) {
        this.c.y = this.start.y * 3.0f;
        this.b.y = ((this.end.y - this.start.y) * 3.0f) - this.c.y;
        this.a.y = (1.0f - this.c.y) - this.b.y;
        return f * (this.c.y + ((this.b.y + (this.a.y * f)) * f));
    }

    private float getXForTime(float f) {
        float f2 = f;
        for (int i = 1; i < 14; i++) {
            float bezierCoordinateX = getBezierCoordinateX(f2) - f;
            if (((double) Math.abs(bezierCoordinateX)) < 0.001d) {
                break;
            }
            f2 -= bezierCoordinateX / getXDerivate(f2);
        }
        return f2;
    }

    private float getXDerivate(float f) {
        return this.c.x + (f * ((this.b.x * 2.0f) + ((this.a.x * 3.0f) * f)));
    }

    private float getBezierCoordinateX(float f) {
        this.c.x = this.start.x * 3.0f;
        this.b.x = ((this.end.x - this.start.x) * 3.0f) - this.c.x;
        this.a.x = (1.0f - this.c.x) - this.b.x;
        return f * (this.c.x + ((this.b.x + (this.a.x * f)) * f));
    }

    public PointF getStart() {
        return this.start;
    }

    public void setStart(PointF pointF) {
        this.start = pointF;
    }

    public PointF getEnd() {
        return this.end;
    }

    public void setEnd(PointF pointF) {
        this.end = pointF;
    }
}
