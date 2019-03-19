package com.nightonke.blurlockview;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

public class Dot extends FrameLayout {
    private boolean isSelected;
    private View selected;
    private ObjectAnimator selectedAnimator;
    private View unselected;
    private ObjectAnimator unselectedAnimator;

    public Dot(Context context) {
        this(context, null);
    }

    public Dot(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isSelected = false;
        LayoutInflater.from(context).inflate(R.layout.dot_view, this, true);
        this.selected = findViewById(R.id.selected);
        this.unselected = findViewById(R.id.unselected);
        clear();
    }

    public void setSelected(boolean z) {
        if ((this.isSelected ^ z) != 0) {
            this.isSelected = z;
            if (z) {
                this.selected.setAlpha(0.0f);
                this.unselected.setAlpha(1.0f);
                if (this.selectedAnimator != null) {
                    this.selectedAnimator.cancel();
                }
                if (this.unselectedAnimator != null) {
                    this.unselectedAnimator.cancel();
                }
                this.selectedAnimator = ObjectAnimator.ofFloat(this.selected, "alpha", new float[]{0.0f, 1.0f});
                this.selectedAnimator.setDuration(300);
                this.selectedAnimator.start();
                this.unselectedAnimator = ObjectAnimator.ofFloat(this.unselected, "alpha", new float[]{1.0f, 0.0f});
                this.unselectedAnimator.setDuration(300);
                this.unselectedAnimator.start();
            } else {
                this.selected.setAlpha(1.0f);
                this.unselected.setAlpha(0.0f);
                if (this.selectedAnimator != null) {
                    this.selectedAnimator.cancel();
                }
                if (this.unselectedAnimator != null) {
                    this.unselectedAnimator.cancel();
                }
                this.selectedAnimator = ObjectAnimator.ofFloat(this.selected, "alpha", new float[]{1.0f, 0.0f});
                this.selectedAnimator.setDuration(300);
                this.selectedAnimator.start();
                this.unselectedAnimator = ObjectAnimator.ofFloat(this.unselected, "alpha", new float[]{0.0f, 1.0f});
                this.unselectedAnimator.setDuration(300);
                this.unselectedAnimator.start();
            }
        }
    }

    public void clear() {
        this.selected.setAlpha(0.0f);
        this.unselected.setAlpha(1.0f);
    }
}
