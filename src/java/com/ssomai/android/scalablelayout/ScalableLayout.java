package com.ssomai.android.scalablelayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import net.glxn.qrgen.android.MatrixToImageConfig;
import net.glxn.qrgen.core.scheme.SchemeUtil;

public class ScalableLayout extends FrameLayout {
    private static final float Default_Scale_Base_Height = 100.0f;
    private static final float Default_Scale_Base_Width = 100.0f;
    private static final float Default_Scale_Height = 100.0f;
    private static final float Default_Scale_Left = 0.0f;
    private static final int Default_Scale_Left_BasePosition = 0;
    private static final float Default_Scale_TextSize = 100.0f;
    private static final float Default_Scale_Top = 0.0f;
    private static final int Default_Scale_Top_BasePosition = 0;
    private static final float Default_Scale_Width = 100.0f;
    private static final float Default_TextView_WrapContent_Scale_MaxWidth = -1.0f;
    private static String sLogTag_Global;
    private long mLastRequestPostTime;
    private String mLogTag_This;
    private float mRatioOfWidthHeight;
    private Runnable mRunnable;
    private float mScale_Root_Height;
    private float mScale_Root_Width;
    private TextWatcher mTextWatcher;

    public static class LayoutParams extends android.widget.FrameLayout.LayoutParams {
        private float mScale_Height;
        private float mScale_Left;
        private int mScale_Left_BasePosition;
        private float mScale_TextSize;
        private float mScale_Top;
        private int mScale_Top_BasePosition;
        private float mScale_Width;
        private TextView_WrapContent_Direction mTextView_WrapContent_Direction;
        private boolean mTextView_WrapContent_MoveSiblings;
        private boolean mTextView_WrapContent_ResizeSurrounded;
        private float mTextView_WrapContent_Scale_MaxWidth;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.mScale_Left = 0.0f;
            this.mScale_Top = 0.0f;
            this.mScale_Width = 100.0f;
            this.mScale_Height = 100.0f;
            this.mScale_TextSize = ScalableLayout.Default_TextView_WrapContent_Scale_MaxWidth;
            this.mTextView_WrapContent_Scale_MaxWidth = ScalableLayout.Default_TextView_WrapContent_Scale_MaxWidth;
            this.mTextView_WrapContent_Direction = TextView_WrapContent_Direction.None;
            this.mTextView_WrapContent_ResizeSurrounded = false;
            this.mTextView_WrapContent_MoveSiblings = true;
            TextView_WrapContent_Direction textView_WrapContent_Direction = TextView_WrapContent_Direction.None;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.View2);
            float f = obtainStyledAttributes.getFloat(R.styleable.View2_scale_left, 0.0f);
            int integer = obtainStyledAttributes.getInteger(R.styleable.View2_scale_left_baseposition, 0);
            float f2 = obtainStyledAttributes.getFloat(R.styleable.View2_scale_top, 0.0f);
            int integer2 = obtainStyledAttributes.getInteger(R.styleable.View2_scale_top_baseposition, 0);
            setScale_Left(f);
            setScale_Left_BasePosition(integer);
            setScale_Top(f2);
            setScale_Top_BasePosition(integer2);
            setScale_Width(obtainStyledAttributes.getFloat(R.styleable.View2_scale_width, 100.0f));
            setScale_Height(obtainStyledAttributes.getFloat(R.styleable.View2_scale_height, 100.0f));
            setScale_TextSize(obtainStyledAttributes.getFloat(R.styleable.View2_scale_textsize, 100.0f));
            TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, R.styleable.TextView);
            int integer3 = obtainStyledAttributes2.getInteger(R.styleable.TextView_textview_wrapcontent_direction, 0);
            for (TextView_WrapContent_Direction textView_WrapContent_Direction2 : TextView_WrapContent_Direction.values()) {
                if (textView_WrapContent_Direction2.mValue == integer3) {
                    textView_WrapContent_Direction = textView_WrapContent_Direction2;
                    break;
                }
            }
            setTextView_WrapContent(textView_WrapContent_Direction, obtainStyledAttributes2.getBoolean(R.styleable.TextView_textview_wrapcontent_resizesurrounded, false), obtainStyledAttributes2.getBoolean(R.styleable.TextView_textview_wrapcontent_movesiblings, true));
            setTextView_WrapContent_Scale_MaxWidth(obtainStyledAttributes2.getFloat(R.styleable.TextView_textview_wrapcontent_scale_maxwidth, ScalableLayout.Default_TextView_WrapContent_Scale_MaxWidth));
        }

        public LayoutParams(float f, float f2, float f3, float f4) {
            this(f, 0, f2, 0, f3, f4);
        }

        public LayoutParams(float f, int i, float f2, int i2, float f3, float f4) {
            this(f, i, f2, i2, f3, f4, 100.0f, TextView_WrapContent_Direction.None, false, true);
        }

        private LayoutParams(float f, int i, float f2, int i2, float f3, float f4, float f5, TextView_WrapContent_Direction textView_WrapContent_Direction, boolean z, boolean z2) {
            super(-2, -2, 51);
            this.mScale_Left = 0.0f;
            this.mScale_Top = 0.0f;
            this.mScale_Width = 100.0f;
            this.mScale_Height = 100.0f;
            this.mScale_TextSize = ScalableLayout.Default_TextView_WrapContent_Scale_MaxWidth;
            this.mTextView_WrapContent_Scale_MaxWidth = ScalableLayout.Default_TextView_WrapContent_Scale_MaxWidth;
            this.mTextView_WrapContent_Direction = TextView_WrapContent_Direction.None;
            this.mTextView_WrapContent_ResizeSurrounded = false;
            this.mTextView_WrapContent_MoveSiblings = true;
            setScale_Left(f);
            setScale_Left_BasePosition(i);
            setScale_Top(f2);
            setScale_Top_BasePosition(i2);
            setScale_Width(f3);
            setScale_Height(f4);
            setScale_TextSize(f5);
            setTextView_WrapContent(textView_WrapContent_Direction, z, z2);
        }

        private LayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
            this(0.0f, 0.0f, 100.0f, 100.0f);
            this.width = layoutParams.width;
            this.height = layoutParams.height;
            this.layoutAnimationParameters = layoutParams.layoutAnimationParameters;
            this.gravity = 51;
        }

        public String toString() {
            return String.format("%08x (%6.3f, %6.3f) (%6.3f, %6.3f)", new Object[]{Integer.valueOf(hashCode()), Float.valueOf(getScale_Left()), Float.valueOf(getScale_Top()), Float.valueOf(getScale_Right()), Float.valueOf(getScale_Bottom())});
        }

        public float getScale_Left() {
            return this.mScale_Left;
        }

        public void setScale_Left(float f) {
            this.mScale_Left = f;
        }

        public float getScale_Right() {
            return getScale_Left() + getScale_Width();
        }

        public int getScale_Left_BasePosition() {
            return this.mScale_Left_BasePosition;
        }

        public void setScale_Left_BasePosition(int i) {
            this.mScale_Left_BasePosition = i;
        }

        public float getScale_Top() {
            return this.mScale_Top;
        }

        public void setScale_Top(float f) {
            this.mScale_Top = f;
        }

        public float getScale_Bottom() {
            return getScale_Top() + getScale_Height();
        }

        public int getScale_Top_BasePosition() {
            return this.mScale_Top_BasePosition;
        }

        public void setScale_Top_BasePosition(int i) {
            this.mScale_Top_BasePosition = i;
        }

        public float getScale_Width() {
            return this.mScale_Width;
        }

        public void setScale_Width(float f) {
            this.mScale_Width = f;
        }

        public float getScale_Height() {
            return this.mScale_Height;
        }

        public void setScale_Height(float f) {
            this.mScale_Height = f;
        }

        public float getScale_TextSize() {
            return this.mScale_TextSize;
        }

        public void setScale_TextSize(float f) {
            this.mScale_TextSize = f;
        }

        public float getTextView_WrapContent_Scale_MaxWidth() {
            return this.mTextView_WrapContent_Scale_MaxWidth;
        }

        public void setTextView_WrapContent_Scale_MaxWidth(float f) {
            this.mTextView_WrapContent_Scale_MaxWidth = f;
        }

        public void setTextView_WrapContent(TextView_WrapContent_Direction textView_WrapContent_Direction, boolean z, boolean z2) {
            this.mTextView_WrapContent_Direction = textView_WrapContent_Direction;
            this.mTextView_WrapContent_ResizeSurrounded = z;
            this.mTextView_WrapContent_MoveSiblings = z2;
        }
    }

    public enum TextView_WrapContent_Direction {
        None(0),
        Left(10),
        Center_Horizontal(20),
        Right(30),
        Top(100),
        Center_Vertical(200),
        Bottom(300);
        
        int mValue;

        private TextView_WrapContent_Direction(int i) {
            this.mValue = 0;
            this.mValue = i;
        }
    }

    private enum ViewPosition {
        Top,
        Bottom,
        Left,
        Right,
        Surrounded,
        Nothing
    }

    private static void ex(Throwable th) {
    }

    private boolean isDifferentSufficiently(float f, float f2, float f3) {
        return f < f2 / f3 || f2 * f3 < f;
    }

    public float getScaleWidth() {
        return this.mScale_Root_Width;
    }

    public float getScaleHeight() {
        return this.mScale_Root_Height;
    }

    public void setScaleWidth(float f) {
        setScaleSize(f, this.mScale_Root_Height);
    }

    public void setScaleHeight(float f) {
        setScaleSize(this.mScale_Root_Width, f);
    }

    public void setScaleSize(float f, float f2) {
        setScaleSize(f, f2, true);
    }

    private void setScaleSize(float f, float f2, boolean z) {
        this.mScale_Root_Width = f;
        this.mScale_Root_Height = f2;
        this.mRatioOfWidthHeight = this.mScale_Root_Height / this.mScale_Root_Width;
        if (z) {
            postDelayedRequestLayout();
        }
    }

    private void postDelayedRequestLayout() {
        if (!isInEditMode()) {
            long currentTimeMillis = System.currentTimeMillis();
            if (this.mLastRequestPostTime < currentTimeMillis - 50 || currentTimeMillis < this.mLastRequestPostTime) {
                this.mLastRequestPostTime = currentTimeMillis;
                postDelayed(this.mRunnable, 10);
            }
        }
    }

    public ScalableLayout(Context context) {
        this(context, 100.0f, 100.0f);
    }

    public ScalableLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, context.obtainStyledAttributes(attributeSet, R.styleable.ScalableLayout).getFloat(R.styleable.ScalableLayout_scale_base_width, 100.0f), context.obtainStyledAttributes(attributeSet, R.styleable.ScalableLayout).getFloat(R.styleable.ScalableLayout_scale_base_height, 100.0f));
    }

    public ScalableLayout(Context context, float f, float f2) {
        this(context, null, f, f2);
    }

    private ScalableLayout(Context context, AttributeSet attributeSet, float f, float f2) {
        super(context, attributeSet);
        this.mScale_Root_Width = 100.0f;
        this.mScale_Root_Height = 100.0f;
        this.mRatioOfWidthHeight = this.mScale_Root_Height / this.mScale_Root_Width;
        this.mRunnable = new Runnable() {
            public void run() {
                ScalableLayout.this.requestLayout();
                ScalableLayout.this.forceLayout();
            }
        };
        this.mLastRequestPostTime = 0;
        this.mTextWatcher = new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                ScalableLayout.this.postDelayedRequestLayout();
            }

            public void afterTextChanged(Editable editable) {
                ScalableLayout.this.postDelayedRequestLayout();
            }
        };
        this.mLogTag_This = null;
        setScaleSize(f, f2, true);
    }

    public LayoutParams getChildLayoutParams(View view) {
        android.view.ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = generateDefaultLayoutParams();
            view.setLayoutParams(layoutParams);
        }
        if (layoutParams instanceof LayoutParams) {
            return (LayoutParams) view.getLayoutParams();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("pChild has not ScalableLayout.LayoutParams ");
        stringBuilder.append(view.getLayoutParams());
        throw new IllegalArgumentException(stringBuilder.toString());
    }

    public void setScale_TextSize(TextView textView, float f) {
        getChildLayoutParams(textView).setScale_TextSize(f);
    }

    public void setTextView_WrapContent(TextView textView, TextView_WrapContent_Direction textView_WrapContent_Direction, boolean z) {
        setTextView_WrapContent(textView, textView_WrapContent_Direction, z, true);
    }

    public void setTextView_WrapContent(TextView textView, TextView_WrapContent_Direction textView_WrapContent_Direction, boolean z, boolean z2) {
        getChildLayoutParams(textView).setTextView_WrapContent(textView_WrapContent_Direction, z, z2);
        refreshTextChangedListener(textView);
    }

    private void refreshTextChangedListener(TextView textView) {
        LayoutParams childLayoutParams = getChildLayoutParams(textView);
        try {
            textView.removeTextChangedListener(this.mTextWatcher);
        } catch (Throwable th) {
            ex(th);
        }
        if (childLayoutParams.mTextView_WrapContent_Direction != TextView_WrapContent_Direction.None) {
            textView.addTextChangedListener(this.mTextWatcher);
        }
    }

    public void addView(View view) {
        addView(view, getChildCount());
    }

    public void addView(View view, int i) {
        addView(view, i, generateDefaultLayoutParams());
    }

    public void addView(View view, int i, int i2) {
        addView(view, new android.view.ViewGroup.LayoutParams(i, i2));
    }

    public void addView(View view, android.view.ViewGroup.LayoutParams layoutParams) {
        addView(view, getChildCount(), layoutParams);
    }

    public void addView(View view, int i, android.view.ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof LayoutParams) {
            addView_Final(view, i, (LayoutParams) layoutParams);
        } else {
            addView_Final(view, i, generateLayoutParams(layoutParams));
        }
    }

    public void addView(View view, float f, float f2, float f3, float f4) {
        addView_Final(view, getChildCount(), new LayoutParams(f, f2, f3, f4));
    }

    private final void addView_Final(View view, int i, LayoutParams layoutParams) {
        super.addView(view, i, layoutParams);
    }

    /* Access modifiers changed, original: protected */
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(0.0f, 0.0f, getScaleWidth(), getScaleHeight());
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    /* Access modifiers changed, original: protected */
    public LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof LayoutParams) {
            return (LayoutParams) layoutParams;
        }
        return new LayoutParams(layoutParams, null);
    }

    public void moveChildView(View view, float f, float f2) {
        LayoutParams childLayoutParams = getChildLayoutParams(view);
        childLayoutParams.mScale_Left = f;
        childLayoutParams.mScale_Top = f2;
        postInvalidate();
    }

    public void moveChildView(View view, float f, float f2, float f3, float f4) {
        LayoutParams childLayoutParams = getChildLayoutParams(view);
        childLayoutParams.mScale_Left = f;
        childLayoutParams.mScale_Top = f2;
        childLayoutParams.mScale_Width = f3;
        childLayoutParams.mScale_Height = f4;
        postInvalidate();
    }

    /* Access modifiers changed, original: protected */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x012f A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x012c  */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x002e  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0029  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0029  */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x002e  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0039  */
    public void onMeasure(int r19, int r20) {
        /*
        r18 = this;
        r0 = r18;
        r1 = android.view.View.MeasureSpec.getMode(r19);
        r2 = android.view.View.MeasureSpec.getMode(r20);
        r3 = android.view.View.MeasureSpec.getSize(r19);
        r4 = android.view.View.MeasureSpec.getSize(r20);
        r5 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r6 = 2139095039; // 0x7f7fffff float:3.4028235E38 double:1.056853372E-314;
        r7 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        if (r1 == r5) goto L_0x0023;
    L_0x001b:
        if (r1 == r7) goto L_0x0021;
    L_0x001d:
        r3 = 2139095039; // 0x7f7fffff float:3.4028235E38 double:1.056853372E-314;
        goto L_0x0027;
    L_0x0021:
        r3 = (float) r3;
        goto L_0x0027;
    L_0x0023:
        if (r2 != r7) goto L_0x0026;
    L_0x0025:
        goto L_0x001d;
    L_0x0026:
        r3 = (float) r3;
    L_0x0027:
        if (r2 == r5) goto L_0x002e;
    L_0x0029:
        if (r2 == r7) goto L_0x002c;
    L_0x002b:
        goto L_0x0032;
    L_0x002c:
        r6 = (float) r4;
        goto L_0x0032;
    L_0x002e:
        if (r1 != r7) goto L_0x0031;
    L_0x0030:
        goto L_0x0032;
    L_0x0031:
        r6 = (float) r4;
    L_0x0032:
        r4 = 0;
        r4 = 0;
        r7 = 0;
        r8 = 0;
    L_0x0036:
        r9 = 3;
        if (r4 >= r9) goto L_0x014c;
    L_0x0039:
        r7 = r0.mScale_Root_Width;
        r7 = r3 / r7;
        r8 = r0.mScale_Root_Height;
        r8 = r6 / r8;
        r7 = java.lang.Math.min(r7, r8);
        r8 = 0;
    L_0x0046:
        r9 = r18.getChildCount();
        if (r8 >= r9) goto L_0x005c;
    L_0x004c:
        r9 = r0.getChildAt(r8);
        r10 = r9 instanceof android.widget.TextView;
        if (r10 == 0) goto L_0x0059;
    L_0x0054:
        r9 = (android.widget.TextView) r9;
        r0.updateTextViewSize(r9, r7);
    L_0x0059:
        r8 = r8 + 1;
        goto L_0x0046;
    L_0x005c:
        r8 = r0.mScale_Root_Width;
        r8 = r3 / r8;
        r9 = r0.mScale_Root_Height;
        r9 = r6 / r9;
        r8 = java.lang.Math.min(r8, r9);
        r9 = r0.mScale_Root_Width;
        r9 = r9 * r8;
        r10 = r0.mScale_Root_Height;
        r10 = r10 * r8;
        r11 = 0;
    L_0x0071:
        r12 = r18.getChildCount();
        if (r11 >= r12) goto L_0x0135;
    L_0x0077:
        r12 = r0.getChildAt(r11);
        r13 = r0.getChildLayoutParams(r12);
        r14 = r13.getScale_Left();
        r14 = r14 * r8;
        r14 = java.lang.Math.round(r14);
        r15 = r13.leftMargin;
        r16 = 1;
        if (r15 == r14) goto L_0x0091;
    L_0x008f:
        r15 = 1;
        goto L_0x0092;
    L_0x0091:
        r15 = 0;
    L_0x0092:
        r13.leftMargin = r14;
        r14 = r13.getScale_Width();
        r14 = r14 * r8;
        r14 = java.lang.Math.round(r14);
        r5 = r13.width;
        if (r5 == r14) goto L_0x00a3;
    L_0x00a2:
        r15 = 1;
    L_0x00a3:
        r13.width = r14;
        r5 = r13.mScale_Left_BasePosition;
        switch(r5) {
            case 0: goto L_0x00be;
            case 1: goto L_0x00b5;
            case 2: goto L_0x00ad;
            default: goto L_0x00ac;
        };
    L_0x00ac:
        goto L_0x00be;
    L_0x00ad:
        r5 = r13.leftMargin;
        r14 = r13.width;
        r5 = r5 - r14;
        r13.leftMargin = r5;
        goto L_0x00be;
    L_0x00b5:
        r5 = r13.leftMargin;
        r14 = r13.width;
        r14 = r14 / 2;
        r5 = r5 - r14;
        r13.leftMargin = r5;
    L_0x00be:
        r5 = r13.getScale_Top();
        r5 = r5 * r8;
        r5 = java.lang.Math.round(r5);
        r14 = r13.topMargin;
        if (r14 == r5) goto L_0x00cd;
    L_0x00cc:
        r15 = 1;
    L_0x00cd:
        r13.topMargin = r5;
        r5 = r13.getScale_Height();
        r5 = r5 * r8;
        r5 = java.lang.Math.round(r5);
        r14 = r13.height;
        if (r14 == r5) goto L_0x00de;
    L_0x00dd:
        r15 = 1;
    L_0x00de:
        r13.height = r5;
        r5 = r13.mScale_Top_BasePosition;
        switch(r5) {
            case 0: goto L_0x00f9;
            case 1: goto L_0x00f0;
            case 2: goto L_0x00e8;
            default: goto L_0x00e7;
        };
    L_0x00e7:
        goto L_0x00f9;
    L_0x00e8:
        r5 = r13.topMargin;
        r14 = r13.height;
        r5 = r5 - r14;
        r13.topMargin = r5;
        goto L_0x00f9;
    L_0x00f0:
        r5 = r13.topMargin;
        r14 = r13.height;
        r14 = r14 / 2;
        r5 = r5 - r14;
        r13.topMargin = r5;
    L_0x00f9:
        r5 = r13.mScale_TextSize;
        r14 = -1082130432; // 0xffffffffbf800000 float:-1.0 double:NaN;
        r5 = (r5 > r14 ? 1 : (r5 == r14 ? 0 : -1));
        if (r5 == 0) goto L_0x0127;
    L_0x0103:
        r5 = r12 instanceof android.widget.TextView;
        if (r5 == 0) goto L_0x0127;
    L_0x0107:
        r5 = r12;
        r5 = (android.widget.TextView) r5;
        r14 = r13.mScale_TextSize;
        r14 = r14 * r8;
        r17 = r3;
        r3 = r5.getTextSize();
        r3 = r0.isDifferentSufficiently(r14, r3);
        if (r3 == 0) goto L_0x0129;
    L_0x011c:
        r3 = r13.mScale_TextSize;
        r3 = r3 * r8;
        r14 = 0;
        r5.setTextSize(r14, r3);
        goto L_0x012a;
    L_0x0127:
        r17 = r3;
    L_0x0129:
        r14 = 0;
    L_0x012a:
        if (r15 == 0) goto L_0x012f;
    L_0x012c:
        r12.setLayoutParams(r13);
    L_0x012f:
        r11 = r11 + 1;
        r3 = r17;
        goto L_0x0071;
    L_0x0135:
        r17 = r3;
        r14 = 0;
        r3 = 1065437102; // 0x3f8147ae float:1.01 double:5.2639587E-315;
        r3 = r0.isDifferentSufficiently(r8, r7, r3);
        if (r3 != 0) goto L_0x0144;
    L_0x0141:
        r7 = r9;
        r8 = r10;
        goto L_0x014c;
    L_0x0144:
        r4 = r4 + 1;
        r7 = r9;
        r8 = r10;
        r3 = r17;
        goto L_0x0036;
    L_0x014c:
        r3 = java.lang.Math.round(r7);
        r1 = android.view.View.MeasureSpec.makeMeasureSpec(r3, r1);
        r3 = java.lang.Math.round(r8);
        r2 = android.view.View.MeasureSpec.makeMeasureSpec(r3, r2);
        super.onMeasure(r1, r2);
        r1 = java.lang.Math.round(r7);
        r2 = java.lang.Math.round(r8);
        r0.setMeasuredDimension(r1, r2);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ssomai.android.scalablelayout.ScalableLayout.onMeasure(int, int):void");
    }

    private boolean isDifferentSufficiently(float f, float f2) {
        return isDifferentSufficiently(f, f2, 1.1f);
    }

    private ViewPosition getViewPosition(LayoutParams layoutParams, LayoutParams layoutParams2) {
        if (layoutParams.getScale_Top() >= layoutParams2.getScale_Bottom() && ((layoutParams.getScale_Left() <= layoutParams2.getScale_Left() && layoutParams2.getScale_Left() <= layoutParams.getScale_Right()) || ((layoutParams.getScale_Left() <= layoutParams2.getScale_Right() && layoutParams2.getScale_Right() <= layoutParams.getScale_Right()) || (layoutParams2.getScale_Left() <= layoutParams.getScale_Left() && layoutParams.getScale_Right() <= layoutParams2.getScale_Right())))) {
            return ViewPosition.Top;
        }
        if (layoutParams.getScale_Bottom() <= layoutParams2.getScale_Top() && ((layoutParams.getScale_Left() <= layoutParams2.getScale_Left() && layoutParams2.getScale_Left() <= layoutParams.getScale_Right()) || ((layoutParams.getScale_Left() <= layoutParams2.getScale_Right() && layoutParams2.getScale_Right() <= layoutParams.getScale_Right()) || (layoutParams2.getScale_Left() <= layoutParams.getScale_Left() && layoutParams.getScale_Right() <= layoutParams2.getScale_Right())))) {
            return ViewPosition.Bottom;
        }
        if (layoutParams.getScale_Left() >= layoutParams2.getScale_Right() && ((layoutParams.getScale_Top() <= layoutParams2.getScale_Top() && layoutParams2.getScale_Top() <= layoutParams.getScale_Bottom()) || ((layoutParams.getScale_Top() <= layoutParams2.getScale_Bottom() && layoutParams2.getScale_Bottom() <= layoutParams.getScale_Bottom()) || (layoutParams.getScale_Top() >= layoutParams2.getScale_Top() && layoutParams2.getScale_Bottom() >= layoutParams.getScale_Bottom())))) {
            return ViewPosition.Left;
        }
        if (layoutParams.getScale_Right() <= layoutParams2.getScale_Left() && ((layoutParams.getScale_Top() <= layoutParams2.getScale_Top() && layoutParams2.getScale_Top() <= layoutParams.getScale_Bottom()) || ((layoutParams.getScale_Top() <= layoutParams2.getScale_Bottom() && layoutParams2.getScale_Bottom() <= layoutParams.getScale_Bottom()) || (layoutParams.getScale_Top() >= layoutParams2.getScale_Top() && layoutParams2.getScale_Bottom() >= layoutParams.getScale_Bottom())))) {
            return ViewPosition.Right;
        }
        if (layoutParams2.getScale_Top() > layoutParams.getScale_Top() || layoutParams2.getScale_Left() > layoutParams.getScale_Left() || layoutParams2.getScale_Right() < layoutParams.getScale_Right() || layoutParams2.getScale_Bottom() < layoutParams.getScale_Bottom()) {
            return ViewPosition.Nothing;
        }
        return ViewPosition.Surrounded;
    }

    private void updateTextViewSize(android.widget.TextView r21, float r22) {
        /*
        r20 = this;
        r6 = r20;
        r7 = r21;
        r20.refreshTextChangedListener(r21);
        r8 = r20.getChildLayoutParams(r21);
        r9 = r8.mTextView_WrapContent_Direction;
        r1 = com.ssomai.android.scalablelayout.ScalableLayout.TextView_WrapContent_Direction.None;
        if (r9 != r1) goto L_0x0014;
    L_0x0013:
        return;
    L_0x0014:
        r1 = r8.getScale_Width();
        r2 = r8.getScale_Height();
        r3 = r8.mScale_TextSize;
        r3 = r3 * r22;
        r4 = r21.getTextSize();
        r4 = r6.isDifferentSufficiently(r3, r4);
        r10 = 0;
        if (r4 == 0) goto L_0x0030;
    L_0x002d:
        r7.setTextSize(r10, r3);
    L_0x0030:
        r3 = com.ssomai.android.scalablelayout.ScalableLayout.AnonymousClass3.$SwitchMap$com$ssomai$android$scalablelayout$ScalableLayout$TextView_WrapContent_Direction;
        r4 = r9.ordinal();
        r3 = r3[r4];
        r4 = 1065437102; // 0x3f8147ae float:1.01 double:5.2639587E-315;
        r5 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        switch(r3) {
            case 1: goto L_0x0073;
            case 2: goto L_0x0073;
            case 3: goto L_0x0073;
            case 4: goto L_0x0043;
            case 5: goto L_0x0043;
            case 6: goto L_0x0043;
            default: goto L_0x0040;
        };
    L_0x0040:
        r11 = r1;
        r12 = r2;
        goto L_0x008c;
    L_0x0043:
        r3 = android.view.View.MeasureSpec.makeMeasureSpec(r10, r10);
        r11 = r2 * r22;
        r11 = (int) r11;
        r5 = android.view.View.MeasureSpec.makeMeasureSpec(r11, r5);
        r7.measure(r3, r5);
        r3 = r21.getMeasuredWidth();
        r3 = (float) r3;
        r3 = r3 * r4;
        r3 = r3 / r22;
        r0 = r8.getTextView_WrapContent_Scale_MaxWidth();
        r4 = -1082130432; // 0xffffffffbf800000 float:-1.0 double:NaN;
        r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
        if (r0 == 0) goto L_0x0070;
    L_0x0064:
        r0 = r8.getTextView_WrapContent_Scale_MaxWidth();
        r0 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1));
        if (r0 <= 0) goto L_0x0070;
    L_0x006c:
        r3 = r8.getTextView_WrapContent_Scale_MaxWidth();
    L_0x0070:
        r12 = r2;
        r11 = r3;
        goto L_0x008c;
    L_0x0073:
        r3 = r1 * r22;
        r3 = (int) r3;
        r3 = android.view.View.MeasureSpec.makeMeasureSpec(r3, r5);
        r5 = android.view.View.MeasureSpec.makeMeasureSpec(r10, r10);
        r7.measure(r3, r5);
        r3 = r21.getMeasuredHeight();
        r3 = (float) r3;
        r3 = r3 * r4;
        r3 = r3 / r22;
        r11 = r1;
        r12 = r3;
    L_0x008c:
        r0 = r6.isDifferentSufficiently(r11, r1);
        if (r0 != 0) goto L_0x0099;
    L_0x0092:
        r0 = r6.isDifferentSufficiently(r12, r2);
        if (r0 != 0) goto L_0x0099;
    L_0x0098:
        return;
    L_0x0099:
        r13 = r11 - r1;
        r14 = r12 - r2;
        r0 = r8.mTextView_WrapContent_MoveSiblings;
        r15 = 1;
        r5 = 2;
        r16 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        if (r0 != r15) goto L_0x032d;
    L_0x00a7:
        r4 = 0;
    L_0x00a8:
        r0 = r20.getChildCount();
        if (r4 >= r0) goto L_0x0494;
    L_0x00ae:
        r1 = r6.getChildAt(r4);
        if (r1 != r7) goto L_0x00b8;
    L_0x00b4:
        r18 = r4;
        goto L_0x0327;
    L_0x00b8:
        r0 = r6.getChildLayoutParams(r1);
        r2 = r6.getViewPosition(r8, r0);
        r3 = com.ssomai.android.scalablelayout.ScalableLayout.AnonymousClass3.$SwitchMap$com$ssomai$android$scalablelayout$ScalableLayout$TextView_WrapContent_Direction;
        r17 = r9.ordinal();
        r3 = r3[r17];
        r10 = 3;
        switch(r3) {
            case 1: goto L_0x02d9;
            case 2: goto L_0x0287;
            case 3: goto L_0x0206;
            case 4: goto L_0x01b1;
            case 5: goto L_0x015a;
            case 6: goto L_0x00cd;
            default: goto L_0x00cc;
        };
    L_0x00cc:
        goto L_0x00b4;
    L_0x00cd:
        r3 = r8.mTextView_WrapContent_ResizeSurrounded;
        if (r3 == 0) goto L_0x012a;
    L_0x00d3:
        r3 = com.ssomai.android.scalablelayout.ScalableLayout.AnonymousClass3.$SwitchMap$com$ssomai$android$scalablelayout$ScalableLayout$ViewPosition;
        r2 = r2.ordinal();
        r2 = r3[r2];
        if (r2 == r5) goto L_0x0110;
    L_0x00dd:
        switch(r2) {
            case 4: goto L_0x00b4;
            case 5: goto L_0x00ff;
            default: goto L_0x00e0;
        };
    L_0x00e0:
        r2 = r0.getScale_Left();
        r3 = r13 / r16;
        r2 = r2 + r3;
        r3 = r0.getScale_Top();
        r10 = r0.getScale_Width();
        r17 = r0.getScale_Height();
        r0 = r6;
        r18 = r4;
        r4 = r10;
        r10 = 2;
        r5 = r17;
        r0.moveChildView(r1, r2, r3, r4, r5);
        goto L_0x0327;
    L_0x00ff:
        r18 = r4;
        r10 = 2;
        r2 = r0.getScale_Left();
        r2 = r2 + r13;
        r0 = r0.getScale_Top();
        r6.moveChildView(r1, r2, r0);
        goto L_0x0327;
    L_0x0110:
        r18 = r4;
        r10 = 2;
        r2 = r0.getScale_Left();
        r3 = r0.getScale_Top();
        r4 = r0.getScale_Width();
        r4 = r4 + r13;
        r5 = r0.getScale_Height();
        r0 = r6;
        r0.moveChildView(r1, r2, r3, r4, r5);
        goto L_0x0327;
    L_0x012a:
        r18 = r4;
        r10 = 2;
        r3 = com.ssomai.android.scalablelayout.ScalableLayout.AnonymousClass3.$SwitchMap$com$ssomai$android$scalablelayout$ScalableLayout$ViewPosition;
        r2 = r2.ordinal();
        r2 = r3[r2];
        switch(r2) {
            case 4: goto L_0x014a;
            case 5: goto L_0x013a;
            default: goto L_0x0138;
        };
    L_0x0138:
        goto L_0x0327;
    L_0x013a:
        r2 = r0.getScale_Left();
        r3 = r13 / r16;
        r2 = r2 + r3;
        r0 = r0.getScale_Top();
        r6.moveChildView(r1, r2, r0);
        goto L_0x0327;
    L_0x014a:
        r2 = r0.getScale_Left();
        r3 = r13 / r16;
        r2 = r2 - r3;
        r0 = r0.getScale_Top();
        r6.moveChildView(r1, r2, r0);
        goto L_0x0327;
    L_0x015a:
        r18 = r4;
        r10 = 2;
        r3 = r8.mTextView_WrapContent_ResizeSurrounded;
        r4 = 5;
        if (r3 == 0) goto L_0x0197;
    L_0x0164:
        r3 = com.ssomai.android.scalablelayout.ScalableLayout.AnonymousClass3.$SwitchMap$com$ssomai$android$scalablelayout$ScalableLayout$ViewPosition;
        r2 = r2.ordinal();
        r2 = r3[r2];
        if (r2 == r10) goto L_0x0180;
    L_0x016e:
        if (r2 == r4) goto L_0x0172;
    L_0x0170:
        goto L_0x0327;
    L_0x0172:
        r2 = r0.getScale_Left();
        r2 = r2 + r13;
        r0 = r0.getScale_Top();
        r6.moveChildView(r1, r2, r0);
        goto L_0x0327;
    L_0x0180:
        r2 = r0.getScale_Left();
        r3 = r0.getScale_Top();
        r4 = r0.getScale_Width();
        r4 = r4 + r13;
        r5 = r0.getScale_Height();
        r0 = r6;
        r0.moveChildView(r1, r2, r3, r4, r5);
        goto L_0x0327;
    L_0x0197:
        r3 = com.ssomai.android.scalablelayout.ScalableLayout.AnonymousClass3.$SwitchMap$com$ssomai$android$scalablelayout$ScalableLayout$ViewPosition;
        r2 = r2.ordinal();
        r2 = r3[r2];
        if (r2 == r4) goto L_0x01a3;
    L_0x01a1:
        goto L_0x0327;
    L_0x01a3:
        r2 = r0.getScale_Left();
        r2 = r2 + r13;
        r0 = r0.getScale_Top();
        r6.moveChildView(r1, r2, r0);
        goto L_0x0327;
    L_0x01b1:
        r18 = r4;
        r10 = 2;
        r3 = r8.mTextView_WrapContent_ResizeSurrounded;
        r4 = 4;
        if (r3 == 0) goto L_0x01ec;
    L_0x01bb:
        r3 = com.ssomai.android.scalablelayout.ScalableLayout.AnonymousClass3.$SwitchMap$com$ssomai$android$scalablelayout$ScalableLayout$ViewPosition;
        r2 = r2.ordinal();
        r2 = r3[r2];
        if (r2 == r10) goto L_0x01d5;
    L_0x01c5:
        if (r2 == r4) goto L_0x0327;
    L_0x01c7:
        r2 = r0.getScale_Left();
        r2 = r2 + r13;
        r0 = r0.getScale_Top();
        r6.moveChildView(r1, r2, r0);
        goto L_0x0327;
    L_0x01d5:
        r2 = r0.getScale_Left();
        r3 = r0.getScale_Top();
        r4 = r0.getScale_Width();
        r4 = r4 + r13;
        r5 = r0.getScale_Height();
        r0 = r6;
        r0.moveChildView(r1, r2, r3, r4, r5);
        goto L_0x0327;
    L_0x01ec:
        r3 = com.ssomai.android.scalablelayout.ScalableLayout.AnonymousClass3.$SwitchMap$com$ssomai$android$scalablelayout$ScalableLayout$ViewPosition;
        r2 = r2.ordinal();
        r2 = r3[r2];
        if (r2 == r4) goto L_0x01f8;
    L_0x01f6:
        goto L_0x0327;
    L_0x01f8:
        r2 = r0.getScale_Left();
        r2 = r2 - r13;
        r0 = r0.getScale_Top();
        r6.moveChildView(r1, r2, r0);
        goto L_0x0327;
    L_0x0206:
        r18 = r4;
        r3 = r8.mTextView_WrapContent_ResizeSurrounded;
        if (r3 == 0) goto L_0x0259;
    L_0x020e:
        r3 = com.ssomai.android.scalablelayout.ScalableLayout.AnonymousClass3.$SwitchMap$com$ssomai$android$scalablelayout$ScalableLayout$ViewPosition;
        r2 = r2.ordinal();
        r2 = r3[r2];
        switch(r2) {
            case 1: goto L_0x0327;
            case 2: goto L_0x0240;
            case 3: goto L_0x0232;
            default: goto L_0x0219;
        };
    L_0x0219:
        r2 = r0.getScale_Left();
        r3 = r0.getScale_Top();
        r4 = r14 / r16;
        r3 = r3 + r4;
        r4 = r0.getScale_Width();
        r5 = r0.getScale_Height();
        r0 = r6;
        r0.moveChildView(r1, r2, r3, r4, r5);
        goto L_0x0327;
    L_0x0232:
        r2 = r0.getScale_Left();
        r0 = r0.getScale_Top();
        r0 = r0 + r14;
        r6.moveChildView(r1, r2, r0);
        goto L_0x0327;
    L_0x0240:
        r2 = r0.getScale_Left();
        r3 = r0.getScale_Top();
        r4 = r0.getScale_Width();
        r0 = r0.getScale_Height();
        r10 = r0 + r14;
        r0 = r6;
        r5 = r10;
        r0.moveChildView(r1, r2, r3, r4, r5);
        goto L_0x0327;
    L_0x0259:
        r3 = com.ssomai.android.scalablelayout.ScalableLayout.AnonymousClass3.$SwitchMap$com$ssomai$android$scalablelayout$ScalableLayout$ViewPosition;
        r2 = r2.ordinal();
        r2 = r3[r2];
        if (r2 == r15) goto L_0x0277;
    L_0x0263:
        if (r2 == r10) goto L_0x0267;
    L_0x0265:
        goto L_0x0327;
    L_0x0267:
        r2 = r0.getScale_Left();
        r0 = r0.getScale_Top();
        r3 = r14 / r16;
        r0 = r0 + r3;
        r6.moveChildView(r1, r2, r0);
        goto L_0x0327;
    L_0x0277:
        r2 = r0.getScale_Left();
        r0 = r0.getScale_Top();
        r3 = r14 / r16;
        r0 = r0 - r3;
        r6.moveChildView(r1, r2, r0);
        goto L_0x0327;
    L_0x0287:
        r18 = r4;
        r3 = r8.mTextView_WrapContent_ResizeSurrounded;
        if (r3 == 0) goto L_0x02c1;
    L_0x028f:
        r3 = com.ssomai.android.scalablelayout.ScalableLayout.AnonymousClass3.$SwitchMap$com$ssomai$android$scalablelayout$ScalableLayout$ViewPosition;
        r2 = r2.ordinal();
        r2 = r3[r2];
        switch(r2) {
            case 2: goto L_0x02aa;
            case 3: goto L_0x029c;
            default: goto L_0x029a;
        };
    L_0x029a:
        goto L_0x0327;
    L_0x029c:
        r2 = r0.getScale_Left();
        r0 = r0.getScale_Top();
        r0 = r0 + r14;
        r6.moveChildView(r1, r2, r0);
        goto L_0x0327;
    L_0x02aa:
        r2 = r0.getScale_Left();
        r3 = r0.getScale_Top();
        r4 = r0.getScale_Width();
        r0 = r0.getScale_Height();
        r5 = r0 + r14;
        r0 = r6;
        r0.moveChildView(r1, r2, r3, r4, r5);
        goto L_0x0327;
    L_0x02c1:
        r3 = com.ssomai.android.scalablelayout.ScalableLayout.AnonymousClass3.$SwitchMap$com$ssomai$android$scalablelayout$ScalableLayout$ViewPosition;
        r2 = r2.ordinal();
        r2 = r3[r2];
        if (r2 == r10) goto L_0x02cc;
    L_0x02cb:
        goto L_0x0327;
    L_0x02cc:
        r2 = r0.getScale_Left();
        r0 = r0.getScale_Top();
        r0 = r0 + r14;
        r6.moveChildView(r1, r2, r0);
        goto L_0x0327;
    L_0x02d9:
        r18 = r4;
        r3 = r8.mTextView_WrapContent_ResizeSurrounded;
        if (r3 == 0) goto L_0x0310;
    L_0x02e1:
        r3 = com.ssomai.android.scalablelayout.ScalableLayout.AnonymousClass3.$SwitchMap$com$ssomai$android$scalablelayout$ScalableLayout$ViewPosition;
        r2 = r2.ordinal();
        r2 = r3[r2];
        switch(r2) {
            case 1: goto L_0x0327;
            case 2: goto L_0x02f9;
            default: goto L_0x02ec;
        };
    L_0x02ec:
        r2 = r0.getScale_Left();
        r0 = r0.getScale_Top();
        r0 = r0 + r14;
        r6.moveChildView(r1, r2, r0);
        goto L_0x0327;
    L_0x02f9:
        r2 = r0.getScale_Left();
        r3 = r0.getScale_Top();
        r4 = r0.getScale_Width();
        r0 = r0.getScale_Height();
        r5 = r0 + r14;
        r0 = r6;
        r0.moveChildView(r1, r2, r3, r4, r5);
        goto L_0x0327;
    L_0x0310:
        r3 = com.ssomai.android.scalablelayout.ScalableLayout.AnonymousClass3.$SwitchMap$com$ssomai$android$scalablelayout$ScalableLayout$ViewPosition;
        r2 = r2.ordinal();
        r2 = r3[r2];
        if (r2 == r15) goto L_0x031b;
    L_0x031a:
        goto L_0x0327;
    L_0x031b:
        r2 = r0.getScale_Left();
        r0 = r0.getScale_Top();
        r0 = r0 - r14;
        r6.moveChildView(r1, r2, r0);
    L_0x0327:
        r4 = r18 + 1;
        r5 = 2;
        r10 = 0;
        goto L_0x00a8;
    L_0x032d:
        r10 = 0;
    L_0x032e:
        r0 = r20.getChildCount();
        if (r10 >= r0) goto L_0x0494;
    L_0x0334:
        r1 = r6.getChildAt(r10);
        if (r1 != r7) goto L_0x033d;
    L_0x033a:
        r15 = 2;
        goto L_0x0490;
    L_0x033d:
        r0 = r6.getChildLayoutParams(r1);
        r2 = r6.getViewPosition(r8, r0);
        r3 = com.ssomai.android.scalablelayout.ScalableLayout.AnonymousClass3.$SwitchMap$com$ssomai$android$scalablelayout$ScalableLayout$TextView_WrapContent_Direction;
        r4 = r9.ordinal();
        r3 = r3[r4];
        switch(r3) {
            case 1: goto L_0x045c;
            case 2: goto L_0x0433;
            case 3: goto L_0x03f2;
            case 4: goto L_0x03bc;
            case 5: goto L_0x0392;
            case 6: goto L_0x0351;
            default: goto L_0x0350;
        };
    L_0x0350:
        goto L_0x033a;
    L_0x0351:
        r3 = r8.mTextView_WrapContent_ResizeSurrounded;
        if (r3 == 0) goto L_0x033a;
    L_0x0357:
        r3 = com.ssomai.android.scalablelayout.ScalableLayout.AnonymousClass3.$SwitchMap$com$ssomai$android$scalablelayout$ScalableLayout$ViewPosition;
        r2 = r2.ordinal();
        r2 = r3[r2];
        r15 = 2;
        if (r2 == r15) goto L_0x037b;
    L_0x0362:
        r2 = r0.getScale_Left();
        r3 = r13 / r16;
        r2 = r2 + r3;
        r3 = r0.getScale_Top();
        r4 = r0.getScale_Width();
        r5 = r0.getScale_Height();
        r0 = r6;
        r0.moveChildView(r1, r2, r3, r4, r5);
        goto L_0x0490;
    L_0x037b:
        r2 = r0.getScale_Left();
        r3 = r0.getScale_Top();
        r4 = r0.getScale_Width();
        r4 = r4 + r13;
        r5 = r0.getScale_Height();
        r0 = r6;
        r0.moveChildView(r1, r2, r3, r4, r5);
        goto L_0x0490;
    L_0x0392:
        r15 = 2;
        r3 = r8.mTextView_WrapContent_ResizeSurrounded;
        if (r3 == 0) goto L_0x0490;
    L_0x0399:
        r3 = com.ssomai.android.scalablelayout.ScalableLayout.AnonymousClass3.$SwitchMap$com$ssomai$android$scalablelayout$ScalableLayout$ViewPosition;
        r2 = r2.ordinal();
        r2 = r3[r2];
        if (r2 == r15) goto L_0x03a5;
    L_0x03a3:
        goto L_0x0490;
    L_0x03a5:
        r2 = r0.getScale_Left();
        r3 = r0.getScale_Top();
        r4 = r0.getScale_Width();
        r4 = r4 + r13;
        r5 = r0.getScale_Height();
        r0 = r6;
        r0.moveChildView(r1, r2, r3, r4, r5);
        goto L_0x0490;
    L_0x03bc:
        r15 = 2;
        r3 = r8.mTextView_WrapContent_ResizeSurrounded;
        if (r3 == 0) goto L_0x0490;
    L_0x03c3:
        r3 = com.ssomai.android.scalablelayout.ScalableLayout.AnonymousClass3.$SwitchMap$com$ssomai$android$scalablelayout$ScalableLayout$ViewPosition;
        r2 = r2.ordinal();
        r2 = r3[r2];
        if (r2 == r15) goto L_0x03db;
    L_0x03cd:
        r2 = r0.getScale_Left();
        r2 = r2 + r13;
        r0 = r0.getScale_Top();
        r6.moveChildView(r1, r2, r0);
        goto L_0x0490;
    L_0x03db:
        r2 = r0.getScale_Left();
        r3 = r0.getScale_Top();
        r4 = r0.getScale_Width();
        r4 = r4 + r13;
        r5 = r0.getScale_Height();
        r0 = r6;
        r0.moveChildView(r1, r2, r3, r4, r5);
        goto L_0x0490;
    L_0x03f2:
        r15 = 2;
        r3 = r8.mTextView_WrapContent_ResizeSurrounded;
        if (r3 == 0) goto L_0x0490;
    L_0x03f9:
        r3 = com.ssomai.android.scalablelayout.ScalableLayout.AnonymousClass3.$SwitchMap$com$ssomai$android$scalablelayout$ScalableLayout$ViewPosition;
        r2 = r2.ordinal();
        r2 = r3[r2];
        if (r2 == r15) goto L_0x041c;
    L_0x0403:
        r2 = r0.getScale_Left();
        r3 = r0.getScale_Top();
        r4 = r14 / r16;
        r3 = r3 + r4;
        r4 = r0.getScale_Width();
        r5 = r0.getScale_Height();
        r0 = r6;
        r0.moveChildView(r1, r2, r3, r4, r5);
        goto L_0x0490;
    L_0x041c:
        r2 = r0.getScale_Left();
        r3 = r0.getScale_Top();
        r4 = r0.getScale_Width();
        r0 = r0.getScale_Height();
        r5 = r0 + r14;
        r0 = r6;
        r0.moveChildView(r1, r2, r3, r4, r5);
        goto L_0x0490;
    L_0x0433:
        r15 = 2;
        r3 = r8.mTextView_WrapContent_ResizeSurrounded;
        if (r3 == 0) goto L_0x0490;
    L_0x043a:
        r3 = com.ssomai.android.scalablelayout.ScalableLayout.AnonymousClass3.$SwitchMap$com$ssomai$android$scalablelayout$ScalableLayout$ViewPosition;
        r2 = r2.ordinal();
        r2 = r3[r2];
        if (r2 == r15) goto L_0x0445;
    L_0x0444:
        goto L_0x0490;
    L_0x0445:
        r2 = r0.getScale_Left();
        r3 = r0.getScale_Top();
        r4 = r0.getScale_Width();
        r0 = r0.getScale_Height();
        r5 = r0 + r14;
        r0 = r6;
        r0.moveChildView(r1, r2, r3, r4, r5);
        goto L_0x0490;
    L_0x045c:
        r15 = 2;
        r3 = r8.mTextView_WrapContent_ResizeSurrounded;
        if (r3 == 0) goto L_0x0490;
    L_0x0463:
        r3 = com.ssomai.android.scalablelayout.ScalableLayout.AnonymousClass3.$SwitchMap$com$ssomai$android$scalablelayout$ScalableLayout$ViewPosition;
        r2 = r2.ordinal();
        r2 = r3[r2];
        if (r2 == r15) goto L_0x047a;
    L_0x046d:
        r2 = r0.getScale_Left();
        r0 = r0.getScale_Top();
        r0 = r0 + r14;
        r6.moveChildView(r1, r2, r0);
        goto L_0x0490;
    L_0x047a:
        r2 = r0.getScale_Left();
        r3 = r0.getScale_Top();
        r4 = r0.getScale_Width();
        r0 = r0.getScale_Height();
        r5 = r0 + r14;
        r0 = r6;
        r0.moveChildView(r1, r2, r3, r4, r5);
    L_0x0490:
        r10 = r10 + 1;
        goto L_0x032e;
    L_0x0494:
        r0 = com.ssomai.android.scalablelayout.ScalableLayout.AnonymousClass3.$SwitchMap$com$ssomai$android$scalablelayout$ScalableLayout$TextView_WrapContent_Direction;
        r1 = r9.ordinal();
        r0 = r0[r1];
        switch(r0) {
            case 1: goto L_0x055a;
            case 2: goto L_0x0547;
            case 3: goto L_0x0517;
            case 4: goto L_0x04e7;
            case 5: goto L_0x04d3;
            case 6: goto L_0x04a1;
            default: goto L_0x049f;
        };
    L_0x049f:
        goto L_0x0587;
    L_0x04a1:
        r0 = r8.mTextView_WrapContent_ResizeSurrounded;
        if (r0 == 0) goto L_0x04bb;
    L_0x04a7:
        r2 = r8.getScale_Left();
        r3 = r8.getScale_Top();
        r5 = r8.getScale_Height();
        r0 = r6;
        r1 = r7;
        r4 = r11;
        r0.moveChildView(r1, r2, r3, r4, r5);
        goto L_0x0587;
    L_0x04bb:
        r0 = r8.getScale_Left();
        r1 = r13 / r16;
        r2 = r0 - r1;
        r3 = r8.getScale_Top();
        r5 = r8.getScale_Height();
        r0 = r6;
        r1 = r7;
        r4 = r11;
        r0.moveChildView(r1, r2, r3, r4, r5);
        goto L_0x0587;
    L_0x04d3:
        r2 = r8.getScale_Left();
        r3 = r8.getScale_Top();
        r5 = r8.getScale_Height();
        r0 = r6;
        r1 = r7;
        r4 = r11;
        r0.moveChildView(r1, r2, r3, r4, r5);
        goto L_0x0587;
    L_0x04e7:
        r0 = r8.mTextView_WrapContent_ResizeSurrounded;
        if (r0 == 0) goto L_0x0501;
    L_0x04ed:
        r2 = r8.getScale_Left();
        r3 = r8.getScale_Top();
        r5 = r8.getScale_Height();
        r0 = r6;
        r1 = r7;
        r4 = r11;
        r0.moveChildView(r1, r2, r3, r4, r5);
        goto L_0x0587;
    L_0x0501:
        r0 = r8.getScale_Left();
        r2 = r0 - r13;
        r3 = r8.getScale_Top();
        r5 = r8.getScale_Height();
        r0 = r6;
        r1 = r7;
        r4 = r11;
        r0.moveChildView(r1, r2, r3, r4, r5);
        goto L_0x0587;
    L_0x0517:
        r0 = r8.mTextView_WrapContent_ResizeSurrounded;
        if (r0 == 0) goto L_0x0530;
    L_0x051d:
        r2 = r8.getScale_Left();
        r3 = r8.getScale_Top();
        r4 = r8.getScale_Width();
        r0 = r6;
        r1 = r7;
        r5 = r12;
        r0.moveChildView(r1, r2, r3, r4, r5);
        goto L_0x0587;
    L_0x0530:
        r2 = r8.getScale_Left();
        r0 = r8.getScale_Top();
        r1 = r14 / r16;
        r3 = r0 - r1;
        r4 = r8.getScale_Width();
        r0 = r6;
        r1 = r7;
        r5 = r12;
        r0.moveChildView(r1, r2, r3, r4, r5);
        goto L_0x0587;
    L_0x0547:
        r2 = r8.getScale_Left();
        r3 = r8.getScale_Top();
        r4 = r8.getScale_Width();
        r0 = r6;
        r1 = r7;
        r5 = r12;
        r0.moveChildView(r1, r2, r3, r4, r5);
        goto L_0x0587;
    L_0x055a:
        r0 = r8.mTextView_WrapContent_ResizeSurrounded;
        if (r0 == 0) goto L_0x0573;
    L_0x0560:
        r2 = r8.getScale_Left();
        r3 = r8.getScale_Top();
        r4 = r8.getScale_Width();
        r0 = r6;
        r1 = r7;
        r5 = r12;
        r0.moveChildView(r1, r2, r3, r4, r5);
        goto L_0x0587;
    L_0x0573:
        r2 = r8.getScale_Left();
        r0 = r8.getScale_Top();
        r3 = r0 - r14;
        r4 = r8.getScale_Width();
        r0 = r6;
        r1 = r7;
        r5 = r12;
        r0.moveChildView(r1, r2, r3, r4, r5);
    L_0x0587:
        r0 = r8.mTextView_WrapContent_ResizeSurrounded;
        if (r0 == 0) goto L_0x059b;
    L_0x058d:
        r0 = r20.getScaleWidth();
        r0 = r0 + r13;
        r1 = r20.getScaleHeight();
        r1 = r1 + r14;
        r2 = 0;
        r6.setScaleSize(r0, r1, r2);
    L_0x059b:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ssomai.android.scalablelayout.ScalableLayout.updateTextViewSize(android.widget.TextView, float):void");
    }

    public TextView addNewTextView(String str, float f, float f2, float f3, float f4, float f5) {
        return addNewTextView(str, f, f2, f3, f4, f5, false);
    }

    public TextView addNewTextView(String str, float f, float f2, float f3, float f4, float f5, boolean z) {
        View textView = new TextView(getContext());
        addView(textView, f2, f3, f4, f5);
        setScale_TextSize(textView, f);
        textView.setText(str);
        textView.setGravity(17);
        textView.setTextColor(MatrixToImageConfig.BLACK);
        textView.setDuplicateParentStateEnabled(z);
        return textView;
    }

    public EditText addNewEditText(float f, float f2, float f3, float f4, float f5) {
        View editText = new EditText(getContext());
        addView(editText, f2, f3, f4, f5);
        setScale_TextSize(editText, f);
        editText.setGravity(17);
        editText.setTextColor(MatrixToImageConfig.BLACK);
        return editText;
    }

    public ImageView addNewImageView(float f, float f2, float f3, float f4) {
        return addNewImageView((Drawable) null, f, f2, f3, f4, false);
    }

    public ImageView addNewImageView(Bitmap bitmap, float f, float f2, float f3, float f4) {
        return addNewImageView(new BitmapDrawable(getResources(), bitmap), f, f2, f3, f4, false);
    }

    public ImageView addNewImageView(int i, float f, float f2, float f3, float f4) {
        return addNewImageView(i, f, f2, f3, f4, false);
    }

    public ImageView addNewImageView(int i, float f, float f2, float f3, float f4, boolean z) {
        Options options = new Options();
        options.inPreferredConfig = Config.ARGB_8888;
        return addNewImageView(new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(), i, options)), f, f2, f3, f4, z);
    }

    public ImageView addNewImageView(Drawable drawable, float f, float f2, float f3, float f4) {
        return addNewImageView(drawable, f, f2, f3, f4, false);
    }

    public ImageView addNewImageView(Drawable drawable, float f, float f2, float f3, float f4, boolean z) {
        View imageView = new ImageView(getContext());
        imageView.setImageDrawable(drawable);
        imageView.setScaleType(ScaleType.FIT_XY);
        imageView.setDuplicateParentStateEnabled(z);
        addView(imageView, f, f2, f3, f4);
        return imageView;
    }

    public String toString() {
        return String.format("{ScalableLayout:%08x}", new Object[]{Integer.valueOf(hashCode())});
    }

    /* Access modifiers changed, original: 0000 */
    public void log(String str) {
        String str2;
        StringBuilder stringBuilder;
        if (sLogTag_Global != null) {
            str2 = sLogTag_Global;
            stringBuilder = new StringBuilder();
            stringBuilder.append(this);
            stringBuilder.append("] ");
            stringBuilder.append(str);
            Log.e(str2, stringBuilder.toString());
        }
        if (this.mLogTag_This != null) {
            str2 = this.mLogTag_This;
            stringBuilder = new StringBuilder();
            stringBuilder.append(this);
            stringBuilder.append("] ");
            stringBuilder.append(str);
            Log.e(str2, stringBuilder.toString());
        }
    }

    public String getLogTag_This() {
        return this.mLogTag_This;
    }

    public void setThisLoggable() {
        setThisLoggable("ScalableLayout");
    }

    public void setThisLoggable(String str) {
        this.mLogTag_This = str;
    }

    private static String getStackTrace(StackTraceElement[] stackTraceElementArr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (StackTraceElement stackTraceElement : stackTraceElementArr) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("\t");
            stringBuilder2.append(stackTraceElement.toString());
            stringBuilder2.append(SchemeUtil.LINE_FEED);
            stringBuilder.append(stringBuilder2.toString());
        }
        return stringBuilder.toString();
    }

    public static void setLoggable() {
        setLoggable("ScalableLayout");
    }

    public static void setLoggable(String str) {
        sLogTag_Global = str;
    }
}
