package com.nightonke.blurlockview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class Indicator extends LinearLayout {
    private Dot[] dots;
    private int number = 0;

    public Indicator(Context context) {
        super(context);
    }

    public Indicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setPasswordLength(int i) {
        removeAllViews();
        this.dots = new Dot[i];
        for (int i2 = 0; i2 < i; i2++) {
            Dot dot = new Dot(getContext());
            dot.setBackgroundResource(R.drawable.indicator_background);
            LayoutParams layoutParams = new LayoutParams(30, 30);
            layoutParams.setMargins(20, 10, 20, 10);
            addView(dot, layoutParams);
            this.dots[i2] = dot;
        }
    }

    public void add() {
        if (this.number != this.dots.length) {
            Dot[] dotArr = this.dots;
            int i = this.number;
            this.number = i + 1;
            dotArr[i].setSelected(true);
        }
    }

    public void delete() {
        if (this.number != 0) {
            Dot[] dotArr = this.dots;
            int i = this.number - 1;
            this.number = i;
            dotArr[i].setSelected(false);
        }
    }

    public void clear() {
        this.number = 0;
        for (Dot selected : this.dots) {
            selected.setSelected(false);
        }
    }
}
