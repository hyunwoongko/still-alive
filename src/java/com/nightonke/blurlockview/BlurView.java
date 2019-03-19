package com.nightonke.blurlockview;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.renderscript.Allocation;
import android.renderscript.Allocation.MipmapControl;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.AttributeSet;
import android.view.View;

public class BlurView extends View {
    private Bitmap mBitmapToBlur;
    private Allocation mBlurInput;
    private Allocation mBlurOutput;
    private int mBlurRadius;
    private ScriptIntrinsicBlur mBlurScript;
    private Bitmap mBlurredBitmap;
    private View mBlurredView;
    private int mBlurredViewHeight;
    private int mBlurredViewWidth;
    private Canvas mBlurringCanvas;
    private int mDownsampleFactor;
    private boolean mDownsampleFactorChanged;
    private int mOverlayColor;
    private RenderScript mRenderScript;

    public BlurView(Context context) {
        this(context, null);
    }

    public BlurView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Resources resources = getResources();
        int integer = resources.getInteger(R.integer.default_blur_radius);
        int integer2 = resources.getInteger(R.integer.default_downsample_factor);
        int color = resources.getColor(R.color.default_overlay_color);
        initializeRenderScript(context);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.BlurView);
        setBlurRadius(obtainStyledAttributes.getInt(R.styleable.BlurView_blurRadius, integer));
        setDownsampleFactor(obtainStyledAttributes.getInt(R.styleable.BlurView_downsampleFactor, integer2));
        setOverlayColor(obtainStyledAttributes.getColor(R.styleable.BlurView_overlayColor, color));
        obtainStyledAttributes.recycle();
    }

    public void setBlurredView(View view) {
        this.mBlurredView = view;
    }

    /* Access modifiers changed, original: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mBlurredView != null) {
            if (prepare()) {
                if (this.mBlurredView.getBackground() == null || !(this.mBlurredView.getBackground() instanceof ColorDrawable)) {
                    this.mBitmapToBlur.eraseColor(0);
                } else {
                    this.mBitmapToBlur.eraseColor(((ColorDrawable) this.mBlurredView.getBackground()).getColor());
                }
                int[] iArr = new int[2];
                this.mBlurredView.getLocationOnScreen(iArr);
                int[] iArr2 = new int[2];
                getLocationOnScreen(iArr2);
                this.mBlurredView.draw(this.mBlurringCanvas);
                blur();
                canvas.save();
                canvas.translate((float) (iArr[0] - iArr2[0]), (float) (iArr[1] - iArr2[1]));
                canvas.scale((float) this.mDownsampleFactor, (float) this.mDownsampleFactor);
                canvas.drawBitmap(this.mBlurredBitmap, 0.0f, 0.0f, null);
                canvas.restore();
            }
            canvas.drawColor(this.mOverlayColor);
        }
    }

    public void setBlurRadius(int i) {
        this.mBlurRadius = i;
        this.mBlurScript.setRadius((float) this.mBlurRadius);
    }

    public int getBlurRadius() {
        return this.mBlurRadius;
    }

    public void setDownsampleFactor(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("Downsample factor must be greater than 0.");
        } else if (this.mDownsampleFactor != i) {
            this.mDownsampleFactor = i;
            this.mDownsampleFactorChanged = true;
        }
    }

    public int getDownsampleFactor() {
        return this.mDownsampleFactor;
    }

    public void setOverlayColor(int i) {
        this.mOverlayColor = i;
    }

    public int getmOverlayColor() {
        return this.mOverlayColor;
    }

    private void initializeRenderScript(Context context) {
        this.mRenderScript = RenderScript.create(context);
        this.mBlurScript = ScriptIntrinsicBlur.create(this.mRenderScript, Element.U8_4(this.mRenderScript));
    }

    /* Access modifiers changed, original: protected */
    public boolean prepare() {
        int width = this.mBlurredView.getWidth();
        int height = this.mBlurredView.getHeight();
        if (this.mBlurringCanvas == null || this.mDownsampleFactorChanged || this.mBlurredViewWidth != width || this.mBlurredViewHeight != height) {
            this.mDownsampleFactorChanged = false;
            this.mBlurredViewWidth = width;
            this.mBlurredViewHeight = height;
            width /= this.mDownsampleFactor;
            height /= this.mDownsampleFactor;
            width = (width - (width % 4)) + 4;
            height = (height - (height % 4)) + 4;
            if (!(this.mBlurredBitmap != null && this.mBlurredBitmap.getWidth() == width && this.mBlurredBitmap.getHeight() == height)) {
                this.mBitmapToBlur = Bitmap.createBitmap(width, height, Config.ARGB_8888);
                if (this.mBitmapToBlur == null) {
                    return false;
                }
                this.mBlurredBitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
                if (this.mBlurredBitmap == null) {
                    return false;
                }
            }
            this.mBlurringCanvas = new Canvas(this.mBitmapToBlur);
            this.mBlurringCanvas.scale(1.0f / ((float) this.mDownsampleFactor), 1.0f / ((float) this.mDownsampleFactor));
            this.mBlurInput = Allocation.createFromBitmap(this.mRenderScript, this.mBitmapToBlur, MipmapControl.MIPMAP_NONE, 1);
            this.mBlurOutput = Allocation.createTyped(this.mRenderScript, this.mBlurInput.getType());
        }
        return true;
    }

    /* Access modifiers changed, original: protected */
    public void blur() {
        this.mBlurInput.copyFrom(this.mBitmapToBlur);
        this.mBlurScript.setInput(this.mBlurInput);
        this.mBlurScript.forEach(this.mBlurOutput);
        this.mBlurOutput.copyTo(this.mBlurredBitmap);
    }

    /* Access modifiers changed, original: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mRenderScript != null) {
            this.mRenderScript.destroy();
        }
    }
}
