package com.eschao.android.widget.pageflip;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.PointF;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.Log;
import android.widget.Scroller;

public class PageFlip {
    private static final int AUTO_PAGE_MODE = 1;
    private static final int DEFAULT_MESH_VERTEX_PIXELS = 10;
    private static final int FIRST_PAGE = 0;
    private static final float FOLD_BASE_SHADOW_END_ALPHA = 0.0f;
    private static final float FOLD_BASE_SHADOW_END_COLOR = 0.3f;
    private static final float FOLD_BASE_SHADOW_START_ALPHA = 0.4f;
    private static final float FOLD_BASE_SHADOW_START_COLOR = 0.05f;
    private static final float FOLD_EDGE_SHADOW_END_ALPHA = 0.0f;
    private static final float FOLD_EDGE_SHADOW_END_COLOR = 0.3f;
    private static final float FOLD_EDGE_SHADOW_START_ALPHA = 0.25f;
    private static final float FOLD_EDGE_SHADOW_START_COLOR = 0.1f;
    private static final int FOLD_TOP_EDGE_SHADOW_VEX_COUNT = 22;
    private static final int MAX_PAGE_CURL_ANGLE = 65;
    private static final float MAX_PAGE_CURL_ANGLE_RATIO = 0.7222222f;
    private static final float MAX_PAGE_CURL_RADIAN = 1.134464f;
    private static final float MAX_PAGE_CURL_TAN_OF_ANGEL = ((float) Math.tan(1.1344640254974365d));
    private static final float MAX_TAN_OF_BACKWARD_FLIP = ((float) Math.tan(0.15707963267948966d));
    private static final float MAX_TAN_OF_FORWARD_FLIP = ((float) Math.tan(0.5235987755982988d));
    private static final int MESH_COUNT_THRESHOLD = 20;
    private static final int MIN_PAGE_CURL_ANGLE = 5;
    private static final float MIN_PAGE_CURL_RADIAN = 0.08726646f;
    private static final float MIN_PAGE_CURL_TAN_OF_ANGLE = ((float) Math.tan(0.0872664600610733d));
    private static final int PAGE_CURL_ANGEL_DIFF = 60;
    private static final int PAGE_SIZE = 2;
    private static final int SECOND_PAGE = 1;
    private static final int SINGLE_PAGE_MODE = 0;
    static final String TAG = "PageFlip";
    private static final float WIDTH_RATIO_OF_CLICK_TO_FLIP = 0.5f;
    private static final float WIDTH_RATIO_OF_RESTORE_FLIP = 0.4f;
    private Context mContext;
    private PageFlipState mFlipState = PageFlipState.END_FLIP;
    private FoldBackVertexProgram mFoldBackVertexProgram = new FoldBackVertexProgram();
    private FoldBackVertexes mFoldBackVertexes = new FoldBackVertexes();
    private ShadowVertexes mFoldBaseShadow = new ShadowVertexes(0, FOLD_BASE_SHADOW_START_COLOR, 0.4f, 0.3f, 0.0f);
    private ShadowWidth mFoldBaseShadowWidth = new ShadowWidth(2.0f, 40.0f, 0.4f);
    private ShadowVertexes mFoldEdgesShadow = new ShadowVertexes(22, FOLD_EDGE_SHADOW_START_COLOR, FOLD_EDGE_SHADOW_START_ALPHA, 0.3f, 0.0f);
    private ShadowWidth mFoldEdgesShadowWidth = new ShadowWidth(5.0f, 30.0f, FOLD_EDGE_SHADOW_START_ALPHA);
    private Vertexes mFoldFrontVertexes = new Vertexes();
    private int mGradientShadowTextureID;
    private boolean mIsClickToFlip = true;
    private boolean mIsVertical = false;
    private float mKValue;
    private PointF mLastTouchP = new PointF();
    private float mLenOfTouchOrigin;
    private OnPageFlipListener mListener = null;
    private float mMaxT2DAngleTan;
    private float mMaxT2OAngleTan;
    private int mMeshCount;
    private PointF mMiddleP = new PointF();
    private int mPageMode = 0;
    private Page[] mPages = new Page[2];
    private int mPixelsOfMesh = 10;
    private float mR;
    private Scroller mScroller;
    private float mSemiPerimeterRatio = 0.8f;
    private ShadowVertexProgram mShadowVertexProgram = new ShadowVertexProgram();
    private PointF mStartTouchP = new PointF();
    private PointF mTouchP = new PointF();
    private VertexProgram mVertexProgram = new VertexProgram();
    private GLViewRect mViewRect = new GLViewRect();
    private float mWidthRationOfClickToFlip = WIDTH_RATIO_OF_CLICK_TO_FLIP;
    private PointF mXFoldP = new PointF();
    private PointF mXFoldP0 = new PointF();
    private PointF mXFoldP1 = new PointF();
    private PointF mYFoldP = new PointF();
    private PointF mYFoldP0 = new PointF();
    private PointF mYFoldP1 = new PointF();

    public PageFlip(Context context) {
        this.mContext = context;
        this.mScroller = new Scroller(context);
    }

    public boolean enableAutoPage(boolean z) {
        if (this.mPageMode != z) {
            this.mPageMode = z;
            if ((z && this.mViewRect.surfaceW > this.mViewRect.surfaceH && this.mPages[1] == null) || !(z || this.mPages[1] == null)) {
                createPages();
                return true;
            }
        }
        return false;
    }

    public boolean isAutoPageEnabled() {
        return this.mPageMode == 1;
    }

    public PageFlip enableClickToFlip(boolean z) {
        this.mIsClickToFlip = z;
        return this;
    }

    public PageFlip setWidthRatioOfClickToFlip(float f) {
        if (f <= 0.0f || f > WIDTH_RATIO_OF_CLICK_TO_FLIP) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Invalid ratio value: ");
            stringBuilder.append(f);
            throw new IllegalArgumentException(stringBuilder.toString());
        }
        this.mWidthRationOfClickToFlip = f;
        return this;
    }

    public PageFlip setListener(OnPageFlipListener onPageFlipListener) {
        this.mListener = onPageFlipListener;
        return this;
    }

    public PageFlip setPixelsOfMesh(int i) {
        if (i <= 0) {
            i = 10;
        }
        this.mPixelsOfMesh = i;
        return this;
    }

    public int getPixelsOfMesh() {
        return this.mPixelsOfMesh;
    }

    public PageFlip setSemiPerimeterRatio(float f) {
        if (f <= 0.0f || f > 1.0f) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Invalid ratio value: ");
            stringBuilder.append(f);
            throw new IllegalArgumentException(stringBuilder.toString());
        }
        this.mSemiPerimeterRatio = f;
        return this;
    }

    public PageFlip setMaskAlphaOfFold(int i) {
        this.mFoldBackVertexes.setMaskAlpha(i);
        return this;
    }

    public PageFlip setShadowColorOfFoldEdges(float f, float f2, float f3, float f4) {
        this.mFoldEdgesShadow.mColor.set(f, f2, f3, f4);
        return this;
    }

    public PageFlip setShadowColorOfFoldBase(float f, float f2, float f3, float f4) {
        this.mFoldBaseShadow.mColor.set(f, f2, f3, f4);
        return this;
    }

    public PageFlip setShadowWidthOfFoldEdges(float f, float f2, float f3) {
        this.mFoldEdgesShadowWidth.set(f, f2, f3);
        return this;
    }

    public PageFlip setShadowWidthOfFoldBase(float f, float f2, float f3) {
        this.mFoldBaseShadowWidth.set(f, f2, f3);
        return this;
    }

    public int getSurfaceWidth() {
        return (int) this.mViewRect.surfaceW;
    }

    public int getSurfaceHeight() {
        return (int) this.mViewRect.surfaceH;
    }

    public PageFlipState getFlipState() {
        return this.mFlipState;
    }

    public void onSurfaceCreated() throws PageFlipException {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        GLES20.glClearDepthf(1.0f);
        GLES20.glEnable(2929);
        try {
            this.mVertexProgram.init(this.mContext);
            this.mFoldBackVertexProgram.init(this.mContext);
            this.mShadowVertexProgram.init(this.mContext);
            createGradientShadowTexture();
        } catch (PageFlipException e) {
            this.mVertexProgram.delete();
            this.mFoldBackVertexProgram.delete();
            this.mShadowVertexProgram.delete();
            throw e;
        }
    }

    public void onSurfaceChanged(int i, int i2) throws PageFlipException {
        this.mViewRect.set((float) i, (float) i2);
        GLES20.glViewport(0, 0, i, i2);
        this.mVertexProgram.initMatrix(-this.mViewRect.halfW, this.mViewRect.halfW, -this.mViewRect.halfH, this.mViewRect.halfH);
        computeMaxMeshCount();
        createPages();
    }

    private void createPages() {
        if (this.mPages[0] != null) {
            this.mPages[0].deleteAllTextures();
        }
        if (this.mPages[1] != null) {
            this.mPages[1].deleteAllTextures();
        }
        if (this.mPageMode != 1 || this.mViewRect.surfaceW <= this.mViewRect.surfaceH) {
            this.mPages[0] = new Page(this.mViewRect.left, this.mViewRect.right, this.mViewRect.top, this.mViewRect.bottom);
            this.mPages[1] = null;
            return;
        }
        this.mPages[0] = new Page(this.mViewRect.left, 0.0f, this.mViewRect.top, this.mViewRect.bottom);
        this.mPages[1] = new Page(0.0f, this.mViewRect.right, this.mViewRect.top, this.mViewRect.bottom);
    }

    public void onFingerDown(float f, float f2) {
        f = this.mViewRect.toOpenGLX(f);
        f2 = this.mViewRect.toOpenGLY(f2);
        int i = 1;
        if (!this.mPages[0].contains(f, f2)) {
            if (this.mPages[1] == null || !this.mPages[1].contains(f, f2)) {
                i = 0;
            } else {
                Page page = this.mPages[1];
                this.mPages[1] = this.mPages[0];
                this.mPages[0] = page;
            }
        }
        if (i != 0) {
            this.mMaxT2OAngleTan = 0.0f;
            this.mMaxT2DAngleTan = 0.0f;
            this.mLastTouchP.set(f, f2);
            this.mStartTouchP.set(f, f2);
            this.mTouchP.set(f, f2);
            this.mFlipState = PageFlipState.BEGIN_FLIP;
        }
    }

    public boolean onFingerMove(float f, float f2) {
        float abs;
        f = this.mViewRect.toOpenGLX(f);
        f2 = this.mViewRect.toOpenGLY(f2);
        float f3 = f2 - this.mStartTouchP.y;
        float f4 = f - this.mStartTouchP.x;
        Page page = this.mPages[0];
        GLPoint gLPoint = page.originP;
        GLPoint gLPoint2 = page.diagonalP;
        if (this.mFlipState == PageFlipState.BEGIN_FLIP && Math.abs(f4) > this.mViewRect.width * FOLD_BASE_SHADOW_START_COLOR) {
            page.setOriginAndDiagonalPoints(this.mPages[1] != null, f3);
            abs = Math.abs(this.mStartTouchP.y - gLPoint.y);
            float abs2 = Math.abs(this.mStartTouchP.y - gLPoint2.y);
            this.mMaxT2OAngleTan = computeTanOfCurlAngle(abs);
            this.mMaxT2DAngleTan = computeTanOfCurlAngle(abs2);
            if ((gLPoint.y >= 0.0f || page.right <= 0.0f) && (gLPoint.y <= 0.0f || page.right > 0.0f)) {
                this.mMaxT2DAngleTan = -this.mMaxT2DAngleTan;
            } else {
                this.mMaxT2OAngleTan = -this.mMaxT2OAngleTan;
            }
            if (this.mPages[1] == null && f4 > 0.0f && this.mListener != null && this.mListener.canFlipBackward()) {
                this.mStartTouchP.x = gLPoint.x;
                f4 = f - this.mStartTouchP.x;
                this.mFlipState = PageFlipState.BACKWARD_FLIP;
            } else if (this.mListener != null && this.mListener.canFlipForward() && ((f4 < 0.0f && gLPoint.x > 0.0f) || (f4 > 0.0f && gLPoint.x < 0.0f))) {
                this.mFlipState = PageFlipState.FORWARD_FLIP;
            }
        }
        if (this.mFlipState != PageFlipState.FORWARD_FLIP && this.mFlipState != PageFlipState.BACKWARD_FLIP && this.mFlipState != PageFlipState.RESTORE_FLIP) {
            return false;
        }
        this.mIsVertical = Math.abs(f3) <= 1.0f;
        f4 = PageFlipState.FORWARD_FLIP == this.mFlipState ? f4 * 1.2f : f4 * 1.1f;
        if ((f3 < 0.0f && gLPoint.y < 0.0f) || (f3 > 0.0f && gLPoint.y > 0.0f)) {
            abs = this.mMaxT2DAngleTan;
            this.mMaxT2DAngleTan = this.mMaxT2OAngleTan;
            this.mMaxT2OAngleTan = abs;
            page.invertYOfOriginPoint();
        }
        abs = this.mMaxT2OAngleTan * f4;
        if (Math.abs(f3) > Math.abs(abs)) {
            f3 = abs;
        }
        float f5 = (this.mSemiPerimeterRatio + 1.0f) * WIDTH_RATIO_OF_CLICK_TO_FLIP;
        if (Math.abs((((f3 / f4) * f3) + f4) * f5) + 2.0f >= page.width) {
            f3 = (((gLPoint2.x - gLPoint.x) / f5) - f4) * f4;
            if (f3 < 0.0f) {
                return false;
            }
            double sqrt = Math.sqrt((double) f3);
            if (gLPoint.y > 0.0f) {
                f3 = (float) ((int) Math.ceil(-sqrt));
            } else {
                f3 = (float) ((int) Math.floor(sqrt));
            }
        }
        this.mLastTouchP.set(f, f2);
        this.mTouchP.set(f4 + gLPoint.x, f3 + gLPoint.y);
        this.mMiddleP.x = (this.mTouchP.x + gLPoint.x) * WIDTH_RATIO_OF_CLICK_TO_FLIP;
        this.mMiddleP.y = (this.mTouchP.y + gLPoint.y) * WIDTH_RATIO_OF_CLICK_TO_FLIP;
        computeVertexesAndBuildPage();
        return true;
    }

    public boolean onFingerUp(float f, float f2, int i) {
        float toOpenGLX = this.mViewRect.toOpenGLX(f);
        float toOpenGLY = this.mViewRect.toOpenGLY(f2);
        Page page = this.mPages[0];
        GLPoint gLPoint = page.originP;
        GLPoint gLPoint2 = page.diagonalP;
        boolean z = this.mPages[1] != null;
        Point point = new Point((int) this.mTouchP.x, (int) this.mTouchP.y);
        Point point2 = new Point(0, 0);
        if (this.mFlipState == PageFlipState.FORWARD_FLIP) {
            if (page.isXInRange(toOpenGLX, 0.4f)) {
                point2.x = (int) gLPoint.x;
                this.mFlipState = PageFlipState.RESTORE_FLIP;
            } else if (!z || gLPoint.x >= 0.0f) {
                point2.x = (int) (gLPoint2.x - page.width);
            } else {
                point2.x = (int) (gLPoint2.x + page.width);
            }
            point2.y = (int) gLPoint.y;
        } else if (this.mFlipState == PageFlipState.BACKWARD_FLIP) {
            if (page.isXInRange(toOpenGLX, WIDTH_RATIO_OF_CLICK_TO_FLIP)) {
                this.mMaxT2OAngleTan = (this.mTouchP.y - gLPoint.y) / (this.mTouchP.x - gLPoint.x);
                point2.set((int) gLPoint.x, (int) gLPoint.y);
            } else {
                this.mFlipState = PageFlipState.FORWARD_FLIP;
                point2.set((int) (gLPoint2.x - page.width), (int) gLPoint.y);
            }
        } else if (this.mFlipState == PageFlipState.BEGIN_FLIP) {
            this.mIsVertical = false;
            this.mFlipState = PageFlipState.END_FLIP;
            page.setOriginAndDiagonalPoints(z, -toOpenGLY);
            if (this.mIsClickToFlip && Math.abs(toOpenGLX - this.mStartTouchP.x) < 2.0f) {
                computeScrollPointsForClickingFlip(toOpenGLX, point, point2);
            }
        }
        if (this.mFlipState != PageFlipState.FORWARD_FLIP && this.mFlipState != PageFlipState.BACKWARD_FLIP && this.mFlipState != PageFlipState.RESTORE_FLIP) {
            return false;
        }
        this.mScroller.startScroll(point.x, point.y, point2.x - point.x, point2.y - point.y, i);
        return true;
    }

    public boolean canAnimate(float f, float f2) {
        if (this.mFlipState != PageFlipState.FORWARD_FLIP || this.mPages[0].contains(this.mViewRect.toOpenGLX(f), this.mViewRect.toOpenGLY(f2))) {
            return false;
        }
        return true;
    }

    private void computeScrollPointsForClickingFlip(float f, Point point, Point point2) {
        int i = 0;
        Page page = this.mPages[0];
        GLPoint gLPoint = page.originP;
        GLPoint gLPoint2 = page.diagonalP;
        if (this.mPages[1] != null) {
            i = 1;
        }
        float f2 = MAX_TAN_OF_FORWARD_FLIP;
        float f3 = MAX_TAN_OF_BACKWARD_FLIP;
        if ((gLPoint.y < 0.0f && gLPoint.x > 0.0f) || (gLPoint.y > 0.0f && gLPoint.x < 0.0f)) {
            f2 = -f2;
            f3 = -f3;
        }
        if (i == 0 && f < gLPoint2.x + (page.width * this.mWidthRationOfClickToFlip) && this.mListener != null && this.mListener.canFlipBackward()) {
            this.mFlipState = PageFlipState.BACKWARD_FLIP;
            this.mKValue = f3;
            point.set((int) gLPoint2.x, (int) (gLPoint.y + ((((float) point.x) - gLPoint.x) * this.mKValue)));
            point2.set(((int) gLPoint.x) - 5, (int) gLPoint.y);
        } else if (this.mListener != null && this.mListener.canFlipForward() && page.isXInRange(f, this.mWidthRationOfClickToFlip)) {
            this.mFlipState = PageFlipState.FORWARD_FLIP;
            this.mKValue = f2;
            if (gLPoint.x < 0.0f) {
                point.x = (int) (gLPoint.x + (page.width * FOLD_EDGE_SHADOW_START_ALPHA));
            } else {
                point.x = (int) (gLPoint.x - (page.width * FOLD_EDGE_SHADOW_START_ALPHA));
            }
            point.y = (int) (gLPoint.y + ((((float) point.x) - gLPoint.x) * this.mKValue));
            if (i == 0 || gLPoint.x >= 0.0f) {
                point2.x = (int) (gLPoint2.x - page.width);
            } else {
                point2.x = (int) (gLPoint2.x + page.width);
            }
            point2.y = (int) gLPoint.y;
        }
    }

    /* JADX WARNING: Missing block: B:33:0x0144, code skipped:
            if (java.lang.Math.abs(r13.mXFoldP0.x - r3.x) >= 2.0f) goto L_0x0146;
     */
    /* JADX WARNING: Missing block: B:39:0x016c, code skipped:
            if ((((r13.mYFoldP1.y - r3.y) * r13.mKValue) + ((float) (((double) (r13.mLenOfTouchOrigin * r13.mSemiPerimeterRatio)) / 3.141592653589793d))) > (r3.x - r2.x)) goto L_0x0146;
     */
    public boolean animating() {
        /*
        r13 = this;
        r0 = r13.mPages;
        r1 = 0;
        r0 = r0[r1];
        r2 = r0.originP;
        r3 = r0.diagonalP;
        r4 = r13.mScroller;
        r4 = r4.isFinished();
        r5 = 1;
        r4 = r4 ^ r5;
        if (r4 == 0) goto L_0x016f;
    L_0x0013:
        r6 = r13.mScroller;
        r6.computeScrollOffset();
        r6 = r13.mTouchP;
        r7 = r13.mScroller;
        r7 = r7.getCurrX();
        r7 = (float) r7;
        r8 = r13.mScroller;
        r8 = r8.getCurrY();
        r8 = (float) r8;
        r6.set(r7, r8);
        r6 = r13.mFlipState;
        r7 = com.eschao.android.widget.pageflip.PageFlipState.BACKWARD_FLIP;
        r8 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        if (r6 == r7) goto L_0x004f;
    L_0x0033:
        r6 = r13.mFlipState;
        r7 = com.eschao.android.widget.pageflip.PageFlipState.RESTORE_FLIP;
        if (r6 != r7) goto L_0x003a;
    L_0x0039:
        goto L_0x004f;
    L_0x003a:
        r6 = r13.mTouchP;
        r6 = r6.y;
        r7 = r2.y;
        r6 = r6 - r7;
        r6 = java.lang.Math.abs(r6);
        r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        if (r6 >= 0) goto L_0x004b;
    L_0x0049:
        r6 = 1;
        goto L_0x004c;
    L_0x004b:
        r6 = 0;
    L_0x004c:
        r13.mIsVertical = r6;
        goto L_0x0075;
    L_0x004f:
        r4 = r13.mTouchP;
        r6 = r13.mTouchP;
        r6 = r6.x;
        r7 = r2.x;
        r6 = r6 - r7;
        r7 = r13.mKValue;
        r6 = r6 * r7;
        r7 = r2.y;
        r6 = r6 + r7;
        r4.y = r6;
        r4 = r13.mTouchP;
        r4 = r4.x;
        r6 = r2.x;
        r4 = r4 - r6;
        r4 = java.lang.Math.abs(r4);
        r6 = 1092616192; // 0x41200000 float:10.0 double:5.398241246E-315;
        r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r4 <= 0) goto L_0x0074;
    L_0x0072:
        r4 = 1;
        goto L_0x0075;
    L_0x0074:
        r4 = 0;
    L_0x0075:
        r6 = r13.mMiddleP;
        r7 = r13.mTouchP;
        r7 = r7.x;
        r9 = r2.x;
        r7 = r7 + r9;
        r9 = 1056964608; // 0x3f000000 float:0.5 double:5.222099017E-315;
        r7 = r7 * r9;
        r10 = r13.mTouchP;
        r10 = r10.y;
        r11 = r2.y;
        r10 = r10 + r11;
        r10 = r10 * r9;
        r6.set(r7, r10);
        r6 = r13.mIsVertical;
        if (r6 == 0) goto L_0x0096;
    L_0x0092:
        r13.computeKeyVertexesWhenVertical();
        goto L_0x0099;
    L_0x0096:
        r13.computeKeyVertexesWhenSlope();
    L_0x0099:
        r6 = r13.mPages;
        r6 = r6[r5];
        r9 = 4614256656552045848; // 0x400921fb54442d18 float:3.37028055E12 double:3.141592653589793;
        if (r6 == 0) goto L_0x014a;
    L_0x00a4:
        r6 = r13.mXFoldP1;
        r6 = r6.x;
        r6 = r0.isXOutsidePage(r6);
        if (r6 == 0) goto L_0x016f;
    L_0x00ae:
        r4 = r13.mXFoldP1;
        r6 = r3.x;
        r4.x = r6;
        r4 = r13.mTouchP;
        r4 = r4.x;
        r6 = r2.x;
        r4 = r4 - r6;
        r6 = r13.mLenOfTouchOrigin;
        r4 = r4 / r6;
        r0 = r0.width;
        r6 = java.lang.Math.abs(r4);
        r0 = r0 * r6;
        r6 = r13.mLenOfTouchOrigin;
        r0 = r0 / r6;
        r0 = r8 - r0;
        r6 = r13.mLenOfTouchOrigin;
        r7 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r11 = r0 * r7;
        r8 = r8 - r11;
        r6 = r6 * r8;
        r11 = (double) r6;
        r11 = r11 / r9;
        r6 = (float) r11;
        r13.mR = r6;
        r6 = r13.mXFoldP0;
        r8 = r13.mLenOfTouchOrigin;
        r8 = r8 * r0;
        r8 = r8 / r4;
        r0 = r2.x;
        r8 = r8 + r0;
        r6.x = r8;
        r0 = r13.mIsVertical;
        if (r0 == 0) goto L_0x00fa;
    L_0x00e9:
        r0 = r13.mYFoldP0;
        r2 = r13.mXFoldP0;
        r2 = r2.x;
        r0.x = r2;
        r0 = r13.mYFoldP1;
        r2 = r13.mXFoldP1;
        r2 = r2.x;
        r0.x = r2;
        goto L_0x011c;
    L_0x00fa:
        r0 = r13.mYFoldP1;
        r4 = r2.y;
        r6 = r13.mXFoldP1;
        r6 = r6.x;
        r8 = r2.x;
        r6 = r6 - r8;
        r8 = r13.mKValue;
        r6 = r6 / r8;
        r4 = r4 + r6;
        r0.y = r4;
        r0 = r13.mYFoldP0;
        r4 = r2.y;
        r6 = r13.mXFoldP0;
        r6 = r6.x;
        r2 = r2.x;
        r6 = r6 - r2;
        r2 = r13.mKValue;
        r6 = r6 / r2;
        r4 = r4 + r6;
        r0.y = r4;
    L_0x011c:
        r0 = r13.mMiddleP;
        r0 = r0.x;
        r2 = r13.mXFoldP0;
        r2 = r2.x;
        r0 = r0 - r2;
        r0 = java.lang.Math.abs(r0);
        r2 = r13.mMeshCount;
        r2 = (float) r2;
        r2 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1));
        if (r2 <= 0) goto L_0x0133;
    L_0x0130:
        r0 = (int) r0;
        r13.mMeshCount = r0;
    L_0x0133:
        r0 = r13.mMeshCount;
        if (r0 <= 0) goto L_0x0148;
    L_0x0137:
        r0 = r13.mXFoldP0;
        r0 = r0.x;
        r2 = r3.x;
        r0 = r0 - r2;
        r0 = java.lang.Math.abs(r0);
        r0 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1));
        if (r0 < 0) goto L_0x0148;
    L_0x0146:
        r4 = 1;
        goto L_0x016f;
    L_0x0148:
        r4 = 0;
        goto L_0x016f;
    L_0x014a:
        r0 = r13.mFlipState;
        r6 = com.eschao.android.widget.pageflip.PageFlipState.FORWARD_FLIP;
        if (r0 != r6) goto L_0x016f;
    L_0x0150:
        r0 = r13.mLenOfTouchOrigin;
        r4 = r13.mSemiPerimeterRatio;
        r0 = r0 * r4;
        r6 = (double) r0;
        r6 = r6 / r9;
        r0 = (float) r6;
        r4 = r13.mYFoldP1;
        r4 = r4.y;
        r6 = r3.y;
        r4 = r4 - r6;
        r6 = r13.mKValue;
        r4 = r4 * r6;
        r4 = r4 + r0;
        r0 = r3.x;
        r2 = r2.x;
        r0 = r0 - r2;
        r0 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1));
        if (r0 <= 0) goto L_0x0148;
    L_0x016e:
        goto L_0x0146;
    L_0x016f:
        if (r4 != 0) goto L_0x0175;
    L_0x0171:
        r13.abortAnimating();
        goto L_0x0180;
    L_0x0175:
        r0 = r13.mIsVertical;
        if (r0 == 0) goto L_0x017d;
    L_0x0179:
        r13.computeVertexesWhenVertical();
        goto L_0x0180;
    L_0x017d:
        r13.computeVertexesWhenSlope();
    L_0x0180:
        return r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eschao.android.widget.pageflip.PageFlip.animating():boolean");
    }

    public boolean isAnimating() {
        return this.mScroller.isFinished() ^ 1;
    }

    public void abortAnimating() {
        this.mScroller.abortAnimation();
        if (this.mFlipState == PageFlipState.FORWARD_FLIP) {
            this.mFlipState = PageFlipState.END_WITH_FORWARD;
        } else if (this.mFlipState == PageFlipState.BACKWARD_FLIP) {
            this.mFlipState = PageFlipState.END_WITH_BACKWARD;
        } else if (this.mFlipState == PageFlipState.RESTORE_FLIP) {
            this.mFlipState = PageFlipState.END_WITH_RESTORE;
        }
    }

    public boolean isStartedFlip() {
        return this.mFlipState == PageFlipState.BACKWARD_FLIP || this.mFlipState == PageFlipState.FORWARD_FLIP || this.mFlipState == PageFlipState.RESTORE_FLIP;
    }

    public boolean isEndedFlip() {
        return this.mFlipState == PageFlipState.END_FLIP || this.mFlipState == PageFlipState.END_WITH_RESTORE || this.mFlipState == PageFlipState.END_WITH_BACKWARD || this.mFlipState == PageFlipState.END_WITH_FORWARD;
    }

    public Page getFirstPage() {
        return this.mPages[0];
    }

    public Page getSecondPage() {
        return this.mPages[1];
    }

    public void deleteUnusedTextures() {
        this.mPages[0].deleteUnusedTextures();
        if (this.mPages[1] != null) {
            this.mPages[1].deleteUnusedTextures();
        }
    }

    public void drawFlipFrame() {
        GLES20.glClear(16640);
        boolean z = this.mPages[1] != null;
        GLES20.glUseProgram(this.mFoldBackVertexProgram.mProgramRef);
        GLES20.glActiveTexture(33984);
        this.mFoldBackVertexes.draw(this.mFoldBackVertexProgram, this.mPages[0], z, this.mGradientShadowTextureID);
        GLES20.glUseProgram(this.mVertexProgram.mProgramRef);
        GLES20.glActiveTexture(33984);
        this.mPages[0].drawFrontPage(this.mVertexProgram, this.mFoldFrontVertexes);
        if (z) {
            this.mPages[1].drawFullPage(this.mVertexProgram, true);
        }
        GLES20.glUseProgram(this.mShadowVertexProgram.mProgramRef);
        this.mFoldBaseShadow.draw(this.mShadowVertexProgram);
        this.mFoldEdgesShadow.draw(this.mShadowVertexProgram);
    }

    public void drawPageFrame() {
        GLES20.glClear(16640);
        GLES20.glUseProgram(this.mVertexProgram.mProgramRef);
        GLES20.glUniformMatrix4fv(this.mVertexProgram.mMVPMatrixLoc, 1, false, VertexProgram.MVPMatrix, 0);
        GLES20.glActiveTexture(33984);
        this.mPages[0].drawFullPage(this.mVertexProgram, true);
        if (this.mPages[1] != null) {
            this.mPages[1].drawFullPage(this.mVertexProgram, true);
        }
    }

    private void computeMaxMeshCount() {
        int minOfWH = ((int) this.mViewRect.minOfWH()) / this.mPixelsOfMesh;
        if (minOfWH % 2 != 0) {
            minOfWH++;
        }
        int i = minOfWH + 2;
        this.mFoldBackVertexes.set(i);
        this.mFoldFrontVertexes.set((minOfWH << 1) + 8, 3, true);
        this.mFoldEdgesShadow.set(i);
        this.mFoldBaseShadow.set(i);
    }

    private void createGradientShadowTexture() {
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        GLES20.glActiveTexture(33984);
        this.mGradientShadowTextureID = iArr[0];
        Bitmap createGradientBitmap = PageFlipUtils.createGradientBitmap();
        GLES20.glBindTexture(3553, this.mGradientShadowTextureID);
        GLES20.glTexParameterf(3553, 10241, 9729.0f);
        GLES20.glTexParameterf(3553, 10240, 9729.0f);
        GLUtils.texImage2D(3553, 0, createGradientBitmap, 0);
        createGradientBitmap.recycle();
    }

    private void computeVertexesAndBuildPage() {
        if (this.mIsVertical) {
            computeKeyVertexesWhenVertical();
            computeVertexesWhenVertical();
            return;
        }
        computeKeyVertexesWhenSlope();
        computeVertexesWhenSlope();
    }

    private void computeKeyVertexesWhenVertical() {
        float f = this.mPages[0].originP.x;
        float f2 = this.mPages[0].originP.y;
        float f3 = this.mPages[0].diagonalP.y;
        this.mTouchP.y = f2;
        this.mMiddleP.y = f2;
        float f4 = 1.0f - this.mSemiPerimeterRatio;
        float f5 = this.mSemiPerimeterRatio + 1.0f;
        this.mXFoldP.set(this.mMiddleP.x, f2);
        this.mXFoldP0.set(((this.mXFoldP.x - f) * f4) + f, this.mXFoldP.y);
        this.mXFoldP1.set((f5 * (this.mXFoldP.x - f)) + f, this.mXFoldP.y);
        this.mYFoldP.set(this.mMiddleP.x, f3);
        this.mYFoldP0.set(this.mXFoldP0.x, this.mYFoldP.y);
        this.mYFoldP1.set(this.mXFoldP1.x, this.mYFoldP.y);
        this.mLenOfTouchOrigin = Math.abs(this.mTouchP.x - f);
        this.mR = (float) (((double) (this.mLenOfTouchOrigin * this.mSemiPerimeterRatio)) / 3.141592653589793d);
        computeMeshCount();
    }

    private void computeVertexesWhenVertical() {
        float sin;
        float f;
        float f2;
        float f3 = this.mMiddleP.x;
        float f4 = (this.mMiddleP.x - this.mXFoldP0.x) / ((float) this.mMeshCount);
        Page page = this.mPages[0];
        float f5 = page.originP.y;
        float f6 = page.diagonalP.y;
        float f7 = page.diagonalP.texY;
        float f8 = page.originP.texY;
        float f9 = page.originP.texX;
        this.mFoldBackVertexes.reset();
        float f10 = f3;
        int i = 0;
        while (i <= this.mMeshCount) {
            double d = (double) ((f10 - this.mXFoldP1.x) / this.mR);
            sin = (float) Math.sin(d);
            float textureX = page.textureX(f10);
            float f11 = this.mXFoldP1.x + (this.mR * sin);
            float cos = (float) (((double) this.mR) * (1.0d - Math.cos(d)));
            float f12 = f10;
            f = f9;
            f2 = f8;
            this.mFoldBackVertexes.addVertex(f11, f6, cos, sin, textureX, f7).addVertex(f11, f5, cos, sin, textureX, f2);
            i++;
            f10 = f12 - f4;
            f9 = f;
            f8 = f2;
        }
        f2 = f8;
        f = f9;
        f3 = this.mTouchP.x;
        this.mFoldBackVertexes.addVertex(f3, f6, 1.0f, 0.0f, f, f7).addVertex(f3, f5, 1.0f, 0.0f, f, f2).toFloatBuffer();
        f4 = -this.mFoldEdgesShadowWidth.width(this.mR);
        f8 = this.mFoldBaseShadowWidth.width(this.mR);
        if (page.originP.x < 0.0f) {
            f4 = -f4;
            f8 = -f8;
        }
        float f13 = this.mFoldBackVertexes.mVertexes[0];
        f10 = f13 + f8;
        sin = f10;
        f10 = f6;
        this.mFoldBaseShadow.setVertexes(0, f13, f5, f10, f5).setVertexes(8, f13, f6, sin, f10).toFloatBuffer(16);
        f4 += f3;
        this.mFoldEdgesShadow.setVertexes(0, f3, f5, f4, f5).setVertexes(8, f3, f6, f4, f10).toFloatBuffer(16);
        this.mFoldFrontVertexes.reset();
        page.buildVertexesOfPageWhenVertical(this.mFoldFrontVertexes, this.mXFoldP1);
        this.mFoldFrontVertexes.toFloatBuffer();
    }

    private void computeKeyVertexesWhenSlope() {
        float f = this.mPages[0].originP.x;
        float f2 = this.mPages[0].originP.y;
        float f3 = this.mMiddleP.x - f;
        float f4 = this.mMiddleP.y - f2;
        float f5 = 1.0f - this.mSemiPerimeterRatio;
        float f6 = this.mSemiPerimeterRatio + 1.0f;
        this.mXFoldP.set(this.mMiddleP.x + ((f4 * f4) / f3), f2);
        this.mXFoldP0.set(((this.mXFoldP.x - f) * f5) + f, this.mXFoldP.y);
        this.mXFoldP1.set(((this.mXFoldP.x - f) * f6) + f, this.mXFoldP.y);
        this.mYFoldP.set(f, this.mMiddleP.y + ((f3 * f3) / f4));
        this.mYFoldP0.set(this.mYFoldP.x, ((this.mYFoldP.y - f2) * f5) + f2);
        this.mYFoldP1.set(this.mYFoldP.x, (f6 * (this.mYFoldP.y - f2)) + f2);
        this.mLenOfTouchOrigin = (float) Math.hypot((double) (this.mTouchP.x - f), (double) (this.mTouchP.y - f2));
        this.mR = (float) (((double) (this.mLenOfTouchOrigin * this.mSemiPerimeterRatio)) / 3.141592653589793d);
        this.mKValue = (this.mTouchP.y - f2) / (this.mTouchP.x - f);
        computeMeshCount();
    }

    private void computeBackVertex(boolean z, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11) {
        float f12 = f5;
        float f13 = (f * f6) + (f2 * f7);
        float f14 = (f3 * f7) - (f4 * f6);
        float f15 = (f3 * f6) + (f4 * f7);
        double d = (double) ((((f * f7) - (f2 * f6)) - f12) / this.mR);
        double sin = Math.sin(d);
        double d2 = (double) f12;
        float f16 = f15;
        f15 = (float) ((((double) this.mR) * sin) + d2);
        double d3 = d2;
        float cos = (float) (((double) this.mR) * (1.0d - Math.cos(d)));
        float f17 = ((f15 * f7) + (f13 * f6)) + f10;
        f13 = ((f13 * f7) - (f15 * f6)) + f11;
        this.mFoldBackVertexes.addVertex(f17, f13, cos, (float) sin, f8, f9);
        f14 = (float) (d3 + (((double) this.mR) * Math.sin((double) ((f14 - f12) / this.mR))));
        this.mFoldEdgesShadow.addVertexes(z, f17, f13, ((f14 * f7) + (f16 * f6)) + f10, ((f16 * f7) - (f14 * f6)) + f11);
    }

    private void computeBackVertex(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        float f10 = f3;
        float f11 = (f * f4) + (f2 * f5);
        double d = (double) ((((f * f5) - (f2 * f4)) - f10) / this.mR);
        double sin = Math.sin(d);
        float f12 = (float) (((double) f10) + (((double) this.mR) * sin));
        float f13 = ((f12 * f5) + (f11 * f4)) + f8;
        float f14 = ((f11 * f5) - (f12 * f4)) + f9;
        this.mFoldBackVertexes.addVertex(f13, f14, (float) (((double) this.mR) * (1.0d - Math.cos(d))), (float) sin, f6, f7);
    }

    private void computeFrontVertex(boolean z, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12) {
        float f13 = f3;
        float f14 = (f * f4) + (f2 * f5);
        double d = (double) ((((f * f5) - (f2 * f4)) - f13) / this.mR);
        float sin = (float) (((double) f13) + (((double) this.mR) * Math.sin(d)));
        float f15 = ((sin * f5) + (f14 * f4)) + f10;
        float f16 = ((f14 * f5) - (sin * f4)) + f11;
        this.mFoldFrontVertexes.addVertex(f15, f16, (float) (((double) this.mR) * (1.0d - Math.cos(d))), f8, f9);
        this.mFoldBaseShadow.addVertexes(z, f15, f16, f15 + f6, f16 - f7);
    }

    private void computeFrontVertex(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        float f10 = f3;
        float f11 = (f * f4) + (f2 * f5);
        double d = (double) ((((f * f5) - (f2 * f4)) - f10) / this.mR);
        float sin = (float) (((double) f10) + (((double) this.mR) * Math.sin(d)));
        float cos = (float) (((double) this.mR) * (1.0d - Math.cos(d)));
        this.mFoldFrontVertexes.addVertex(((sin * f5) + (f11 * f4)) + f8, ((f11 * f5) - (sin * f4)) + f9, cos, f6, f7);
    }

    private void computeBaseShadowLastVertex(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10) {
        float f11 = f3;
        float f12 = (f * f4) + (f2 * f5);
        float sin = (float) (((double) f11) + (((double) this.mR) * Math.sin((double) ((((f * f5) - (f2 * f4)) - f11) / this.mR))));
        f11 = ((sin * f5) + (f12 * f4)) + f8;
        f12 = ((f12 * f5) - (sin * f4)) + f9;
        this.mFoldBaseShadow.addVertexes(false, f11 + (this.mKValue * (f12 - f10)), f10, (f11 + f6) + (this.mKValue * ((f12 - f7) - f10)), f10);
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x01ce A:{LOOP_END, LOOP:1: B:23:0x01cc->B:24:0x01ce} */
    private void computeVertexesWhenSlope() {
        /*
        r50 = this;
        r14 = r50;
        r0 = r14.mPages;
        r13 = 0;
        r15 = r0[r13];
        r0 = r15.originP;
        r12 = r0.x;
        r0 = r15.originP;
        r11 = r0.y;
        r0 = r15.diagonalP;
        r10 = r0.y;
        r0 = r15.originP;
        r9 = r0.texX;
        r0 = r15.originP;
        r8 = r0.texY;
        r0 = r15.diagonalP;
        r0 = r0.texY;
        r7 = r15.height;
        r16 = r10 - r11;
        r1 = r14.mTouchP;
        r1 = r1.y;
        r1 = r1 - r11;
        r2 = r14.mLenOfTouchOrigin;
        r17 = r1 / r2;
        r1 = r14.mTouchP;
        r1 = r1.x;
        r1 = r12 - r1;
        r2 = r14.mLenOfTouchOrigin;
        r18 = r1 / r2;
        r6 = r14.mMeshCount;
        r1 = r14.mXFoldP1;
        r1 = r1.x;
        r1 = r1 - r12;
        r19 = r1 * r18;
        r1 = r14.mFoldEdgesShadowWidth;
        r2 = r14.mR;
        r1 = r1.width(r2);
        r2 = r14.mFoldBaseShadowWidth;
        r3 = r14.mR;
        r2 = r2.width(r3);
        r20 = r2 * r18;
        r21 = r2 * r17;
        r2 = 0;
        r3 = (r11 > r2 ? 1 : (r11 == r2 ? 0 : -1));
        if (r3 <= 0) goto L_0x005b;
    L_0x0058:
        r22 = r1;
        goto L_0x005e;
    L_0x005b:
        r3 = -r1;
        r22 = r3;
    L_0x005e:
        r2 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1));
        if (r2 <= 0) goto L_0x0064;
    L_0x0062:
        r5 = r1;
        goto L_0x0066;
    L_0x0064:
        r1 = -r1;
        goto L_0x0062;
    L_0x0066:
        r4 = (float) r6;
        r23 = r22 / r4;
        r24 = r5 / r4;
        r1 = r14.mFoldEdgesShadow;
        r1.reset();
        r1 = r14.mFoldBaseShadow;
        r1.reset();
        r1 = r14.mFoldFrontVertexes;
        r1.reset();
        r1 = r14.mFoldBackVertexes;
        r1.reset();
        r1 = r14.mFoldBackVertexes;
        r2 = r14.mTouchP;
        r2 = r2.x;
        r3 = r14.mTouchP;
        r3 = r3.y;
        r25 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r26 = 0;
        r27 = r4;
        r4 = r25;
        r28 = r5;
        r5 = r26;
        r29 = r6;
        r6 = r9;
        r25 = r7;
        r7 = r8;
        r1.addVertex(r2, r3, r4, r5, r6, r7);
        r1 = r14.mXFoldP0;
        r1 = r1.x;
        r2 = r14.mXFoldP;
        r2 = r2.x;
        r1 = r1 - r2;
        r26 = r1 / r27;
        r1 = r14.mYFoldP0;
        r1 = r1.y;
        r2 = r14.mYFoldP;
        r2 = r2.y;
        r1 = r1 - r2;
        r30 = r1 / r27;
        r1 = r14.mXFoldP0;
        r1 = r1.x;
        r1 = r1 - r12;
        r2 = r14.mYFoldP0;
        r2 = r2.y;
        r2 = r2 - r11;
        r5 = r2;
        r31 = r22;
        r32 = r28;
        r7 = r29;
        r6 = 0;
        r29 = r1;
    L_0x00c8:
        if (r6 > r7) goto L_0x012f;
    L_0x00ca:
        r1 = java.lang.Math.abs(r5);
        r1 = (r1 > r25 ? 1 : (r1 == r25 ? 0 : -1));
        if (r1 >= 0) goto L_0x012f;
    L_0x00d2:
        r1 = 1;
        r3 = 0;
        r2 = r29 + r12;
        r33 = r15.textureX(r2);
        r34 = r0;
        r0 = r14;
        r2 = r29;
        r4 = r29;
        r13 = r5;
        r5 = r31;
        r36 = r6;
        r6 = r19;
        r37 = r7;
        r7 = r17;
        r38 = r8;
        r8 = r18;
        r39 = r9;
        r9 = r33;
        r33 = r10;
        r10 = r38;
        r40 = r11;
        r11 = r12;
        r41 = r12;
        r12 = r40;
        r0.computeBackVertex(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12);
        r1 = 0;
        r2 = 0;
        r5 = r13 + r40;
        r10 = r15.textureY(r5);
        r3 = r13;
        r4 = r32;
        r5 = r13;
        r9 = r39;
        r11 = r41;
        r0.computeBackVertex(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12);
        r12 = r36;
        r6 = r12 + 1;
        r29 = r29 - r26;
        r5 = r13 - r30;
        r31 = r31 - r23;
        r32 = r32 - r24;
        r10 = r33;
        r0 = r34;
        r7 = r37;
        r8 = r38;
        r11 = r40;
        r12 = r41;
        r13 = 0;
        goto L_0x00c8;
    L_0x012f:
        r34 = r0;
        r13 = r5;
        r38 = r8;
        r39 = r9;
        r33 = r10;
        r40 = r11;
        r41 = r12;
        r12 = r6;
        r11 = r7;
        if (r12 > r11) goto L_0x0217;
    L_0x0140:
        r0 = java.lang.Math.abs(r13);
        r0 = (r0 > r25 ? 1 : (r0 == r25 ? 0 : -1));
        if (r0 == 0) goto L_0x01c2;
    L_0x0148:
        r0 = r14.mYFoldP0;
        r0 = r0.y;
        r0 = r0 - r40;
        r0 = java.lang.Math.abs(r0);
        r0 = (r0 > r25 ? 1 : (r0 == r25 ? 0 : -1));
        if (r0 <= 0) goto L_0x018f;
    L_0x0156:
        r0 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r1 = r14.mKValue;
        r1 = r1 * r0;
        r0 = r14.mYFoldP;
        r0 = r0.y;
        r0 = r0 - r33;
        r1 = r1 * r0;
        r0 = r41 + r1;
        r1 = r14.mKValue;
        r2 = r0 - r41;
        r1 = r1 * r2;
        r8 = r33 + r1;
        r1 = r14.mFoldBackVertexes;
        r4 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r5 = 0;
        r2 = r0;
        r3 = r8;
        r6 = r39;
        r7 = r34;
        r1.addVertex(r2, r3, r4, r5, r6, r7);
        r6 = r0 - r32;
        r1 = r14.mKValue;
        r2 = r6 - r41;
        r1 = r1 * r2;
        r7 = r33 + r1;
        r2 = r14.mFoldEdgesShadow;
        r3 = 0;
        r4 = r0;
        r5 = r8;
        r2.addVertexes(r3, r4, r5, r6, r7);
        goto L_0x01c2;
    L_0x018f:
        r0 = r14.mKValue;
        r4 = r0 * r16;
        r1 = 1;
        r3 = 0;
        r0 = r4 + r41;
        r9 = r15.textureX(r0);
        r0 = r14;
        r2 = r4;
        r5 = r31;
        r6 = r19;
        r7 = r17;
        r8 = r18;
        r10 = r38;
        r42 = r11;
        r11 = r41;
        r24 = r12;
        r12 = r40;
        r0.computeBackVertex(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12);
        r1 = 0;
        r2 = 0;
        r3 = r16;
        r4 = r32;
        r5 = r16;
        r9 = r39;
        r10 = r34;
        r0.computeBackVertex(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12);
        goto L_0x01c6;
    L_0x01c2:
        r42 = r11;
        r24 = r12;
    L_0x01c6:
        r12 = r24;
        r24 = r13;
        r13 = r42;
    L_0x01cc:
        if (r12 > r13) goto L_0x0218;
    L_0x01ce:
        r1 = 1;
        r3 = 0;
        r0 = r29 + r41;
        r9 = r15.textureX(r0);
        r0 = r14;
        r2 = r29;
        r4 = r29;
        r5 = r31;
        r6 = r19;
        r7 = r17;
        r8 = r18;
        r10 = r38;
        r11 = r41;
        r32 = r12;
        r12 = r40;
        r0.computeBackVertex(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12);
        r0 = r14.mKValue;
        r11 = r24 + r40;
        r11 = r11 - r33;
        r1 = r0 * r11;
        r12 = r1 + r41;
        r6 = r15.textureX(r12);
        r0 = r14;
        r2 = r16;
        r3 = r19;
        r4 = r17;
        r5 = r18;
        r7 = r34;
        r8 = r41;
        r9 = r40;
        r0.computeBackVertex(r1, r2, r3, r4, r5, r6, r7, r8, r9);
        r12 = r32 + 1;
        r29 = r29 - r26;
        r24 = r24 - r30;
        r31 = r31 - r23;
        goto L_0x01cc;
    L_0x0217:
        r13 = r11;
    L_0x0218:
        r0 = r14.mFoldBackVertexes;
        r0.toFloatBuffer();
        r0 = r14.mXFoldP;
        r0 = r0.x;
        r1 = r14.mXFoldP1;
        r1 = r1.x;
        r0 = r0 - r1;
        r23 = r0 / r27;
        r0 = r14.mYFoldP;
        r0 = r0.y;
        r1 = r14.mYFoldP1;
        r1 = r1.y;
        r0 = r0 - r1;
        r24 = r0 / r27;
        r0 = r14.mXFoldP;
        r0 = r0.x;
        r0 = r0 - r41;
        r0 = r0 - r23;
        r1 = r14.mYFoldP;
        r1 = r1.y;
        r1 = r1 - r40;
        r1 = r1 - r24;
        r26 = r0;
        r11 = r1;
        r12 = 0;
    L_0x0247:
        if (r12 >= r13) goto L_0x029b;
    L_0x0249:
        r0 = java.lang.Math.abs(r11);
        r0 = (r0 > r25 ? 1 : (r0 == r25 ? 0 : -1));
        if (r0 >= 0) goto L_0x029b;
    L_0x0251:
        r1 = 1;
        r3 = 0;
        r0 = r26 + r41;
        r9 = r15.textureX(r0);
        r0 = r14;
        r2 = r26;
        r4 = r19;
        r5 = r17;
        r6 = r18;
        r7 = r20;
        r8 = r21;
        r10 = r38;
        r43 = r11;
        r11 = r41;
        r44 = r12;
        r12 = r40;
        r45 = r13;
        r13 = r33;
        r0.computeFrontVertex(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13);
        r1 = 0;
        r2 = 0;
        r13 = r43;
        r11 = r13 + r40;
        r10 = r15.textureY(r11);
        r3 = r13;
        r9 = r39;
        r11 = r41;
        r46 = r15;
        r15 = r13;
        r13 = r33;
        r0.computeFrontVertex(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13);
        r13 = r44;
        r12 = r13 + 1;
        r26 = r26 - r23;
        r11 = r15 - r24;
        r13 = r45;
        r15 = r46;
        goto L_0x0247;
    L_0x029b:
        r45 = r13;
        r46 = r15;
        r15 = r11;
        r13 = r12;
        r12 = r45;
        if (r13 >= r12) goto L_0x0369;
    L_0x02a5:
        r0 = java.lang.Math.abs(r15);
        r0 = (r0 > r25 ? 1 : (r0 == r25 ? 0 : -1));
        if (r0 == 0) goto L_0x02f4;
    L_0x02ad:
        if (r13 <= 0) goto L_0x02f4;
    L_0x02af:
        r0 = r14.mKValue;
        r2 = r0 * r16;
        r1 = 1;
        r3 = 0;
        r0 = r2 + r41;
        r11 = r46;
        r9 = r11.textureX(r0);
        r0 = r14;
        r4 = r19;
        r5 = r17;
        r6 = r18;
        r7 = r20;
        r8 = r21;
        r10 = r38;
        r47 = r11;
        r11 = r41;
        r48 = r12;
        r12 = r40;
        r35 = r13;
        r13 = r33;
        r0.computeFrontVertex(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13);
        r1 = 0;
        r11 = r16 + r40;
        r13 = r47;
        r7 = r13.textureY(r11);
        r2 = r16;
        r3 = r19;
        r4 = r17;
        r5 = r18;
        r6 = r39;
        r8 = r41;
        r9 = r40;
        r0.computeFrontVertex(r1, r2, r3, r4, r5, r6, r7, r8, r9);
        goto L_0x02fa;
    L_0x02f4:
        r48 = r12;
        r35 = r13;
        r13 = r46;
    L_0x02fa:
        r1 = 0;
        r0 = r14;
        r2 = r15;
        r3 = r19;
        r4 = r17;
        r5 = r18;
        r6 = r20;
        r7 = r21;
        r8 = r41;
        r9 = r40;
        r10 = r33;
        r0.computeBaseShadowLastVertex(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10);
        r25 = r15;
        r12 = r35;
        r15 = r48;
    L_0x0316:
        if (r12 >= r15) goto L_0x0367;
    L_0x0318:
        r1 = 1;
        r3 = 0;
        r0 = r26 + r41;
        r9 = r13.textureX(r0);
        r0 = r14;
        r2 = r26;
        r4 = r19;
        r5 = r17;
        r6 = r18;
        r7 = r20;
        r8 = r21;
        r10 = r38;
        r11 = r41;
        r35 = r12;
        r12 = r40;
        r49 = r15;
        r15 = r13;
        r13 = r33;
        r0.computeFrontVertex(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13);
        r0 = r14.mKValue;
        r11 = r25 + r40;
        r11 = r11 - r33;
        r1 = r0 * r11;
        r12 = r1 + r41;
        r6 = r15.textureX(r12);
        r0 = r14;
        r2 = r16;
        r3 = r19;
        r4 = r17;
        r5 = r18;
        r7 = r34;
        r8 = r41;
        r9 = r40;
        r0.computeFrontVertex(r1, r2, r3, r4, r5, r6, r7, r8, r9);
        r12 = r35 + 1;
        r26 = r26 - r23;
        r25 = r25 - r24;
        r13 = r15;
        r15 = r49;
        goto L_0x0316;
    L_0x0367:
        r15 = r13;
        goto L_0x036b;
    L_0x0369:
        r15 = r46;
    L_0x036b:
        r0 = r14.mFoldEdgesShadow;
        r1 = r14.mFoldFrontVertexes;
        r2 = 2;
        r1 = r1.getFloatAt(r2);
        r0.vertexZ = r1;
        r0 = r14.mFoldBaseShadow;
        r1 = -1090519040; // 0xffffffffbf000000 float:-0.5 double:NaN;
        r0.vertexZ = r1;
        r0 = r14.mFoldFrontVertexes;
        r1 = r14.mXFoldP1;
        r2 = r14.mYFoldP1;
        r3 = r14.mKValue;
        r15.buildVertexesOfPageWhenSlope(r0, r1, r2, r3);
        r0 = r14.mFoldFrontVertexes;
        r0.toFloatBuffer();
        r0 = r14.mFoldBaseShadow;
        r0.toFloatBuffer();
        r0 = r14.mTouchP;
        r1 = r0.x;
        r0 = r14.mTouchP;
        r2 = r0.y;
        r0 = r28;
        r5 = -r0;
        r0 = r14;
        r3 = r17;
        r4 = r18;
        r6 = r22;
        r0.computeVertexesOfFoldTopEdgeShadow(r1, r2, r3, r4, r5, r6);
        r0 = r14.mFoldEdgesShadow;
        r0.toFloatBuffer();
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eschao.android.widget.pageflip.PageFlip.computeVertexesWhenSlope():void");
    }

    private void computeVertexesOfFoldTopEdgeShadow(float f, float f2, float f3, float f4, float f5, float f6) {
        float f7 = (2.0f * f3) * f4;
        f3 = (float) (1.0d - (Math.pow((double) f3, 2.0d) * 2.0d));
        int i = this.mFoldEdgesShadow.mMaxBackward;
        float f8 = 0.0f;
        int i2 = 0;
        while (i2 < 11) {
            double d = (double) f8;
            float cos = (float) (((double) f5) * Math.cos(d));
            float sin = (float) (((double) f6) * Math.sin(d));
            this.mFoldEdgesShadow.setVertexes(i, f, f2, ((cos * f3) + (sin * f7)) + f, ((sin * f3) - (cos * f7)) + f2);
            i2++;
            f8 += 0.15707964f;
            i += 8;
        }
    }

    private void computeMeshCount() {
        float abs = Math.abs(this.mXFoldP0.x - this.mXFoldP1.x);
        float abs2 = Math.abs(this.mYFoldP0.y - this.mYFoldP1.y);
        if (!this.mIsVertical) {
            abs = Math.min(abs, abs2);
        }
        int i = (int) abs;
        this.mMeshCount = 0;
        for (int i2 = this.mPixelsOfMesh; i2 >= 1 && this.mMeshCount < 20; i2 >>= 1) {
            this.mMeshCount = i / i2;
        }
        if (this.mMeshCount % 2 != 0) {
            this.mMeshCount++;
        }
        this.mMeshCount >>= 1;
    }

    private float computeTanOfCurlAngle(float f) {
        f /= this.mViewRect.halfH;
        if (f <= 0.2777778f) {
            return MAX_PAGE_CURL_TAN_OF_ANGEL;
        }
        float f2 = 65.0f - (f * 60.0f);
        if (f2 < 5.0f) {
            return MIN_PAGE_CURL_TAN_OF_ANGLE;
        }
        return (float) Math.tan((((double) f2) * 3.141592653589793d) / 180.0d);
    }

    private void debugInfo() {
        GLPoint gLPoint = this.mPages[0].originP;
        GLPoint gLPoint2 = this.mPages[0].diagonalP;
        Log.d(TAG, "************************************");
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" Mesh Count:    ");
        stringBuilder.append(this.mMeshCount);
        Log.d(str, stringBuilder.toString());
        str = TAG;
        stringBuilder = new StringBuilder();
        stringBuilder.append(" Mesh Pixels:   ");
        stringBuilder.append(this.mPixelsOfMesh);
        Log.d(str, stringBuilder.toString());
        str = TAG;
        stringBuilder = new StringBuilder();
        stringBuilder.append(" Origin:        ");
        stringBuilder.append(gLPoint.x);
        stringBuilder.append(", ");
        stringBuilder.append(gLPoint.y);
        Log.d(str, stringBuilder.toString());
        String str2 = TAG;
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(" Diagonal:      ");
        stringBuilder2.append(gLPoint2.x);
        stringBuilder2.append(", ");
        stringBuilder2.append(gLPoint2.y);
        Log.d(str2, stringBuilder2.toString());
        str2 = TAG;
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append(" OriginTouchP:  ");
        stringBuilder3.append(this.mStartTouchP.x);
        stringBuilder3.append(", ");
        stringBuilder3.append(this.mStartTouchP.y);
        Log.d(str2, stringBuilder3.toString());
        str2 = TAG;
        stringBuilder3 = new StringBuilder();
        stringBuilder3.append(" TouchP:        ");
        stringBuilder3.append(this.mTouchP.x);
        stringBuilder3.append(", ");
        stringBuilder3.append(this.mTouchP.y);
        Log.d(str2, stringBuilder3.toString());
        str2 = TAG;
        stringBuilder3 = new StringBuilder();
        stringBuilder3.append(" MiddleP:       ");
        stringBuilder3.append(this.mMiddleP.x);
        stringBuilder3.append(", ");
        stringBuilder3.append(this.mMiddleP.y);
        Log.d(str2, stringBuilder3.toString());
        str2 = TAG;
        stringBuilder3 = new StringBuilder();
        stringBuilder3.append(" XFoldP:        ");
        stringBuilder3.append(this.mXFoldP.x);
        stringBuilder3.append(", ");
        stringBuilder3.append(this.mXFoldP.y);
        Log.d(str2, stringBuilder3.toString());
        str2 = TAG;
        stringBuilder3 = new StringBuilder();
        stringBuilder3.append(" XFoldP0:       ");
        stringBuilder3.append(this.mXFoldP0.x);
        stringBuilder3.append(", ");
        stringBuilder3.append(this.mXFoldP0.y);
        Log.d(str2, stringBuilder3.toString());
        str2 = TAG;
        stringBuilder3 = new StringBuilder();
        stringBuilder3.append(" XFoldP1:       ");
        stringBuilder3.append(this.mXFoldP1.x);
        stringBuilder3.append(", ");
        stringBuilder3.append(this.mXFoldP1.y);
        Log.d(str2, stringBuilder3.toString());
        str2 = TAG;
        stringBuilder3 = new StringBuilder();
        stringBuilder3.append(" YFoldP:        ");
        stringBuilder3.append(this.mYFoldP.x);
        stringBuilder3.append(", ");
        stringBuilder3.append(this.mYFoldP.y);
        Log.d(str2, stringBuilder3.toString());
        str2 = TAG;
        stringBuilder3 = new StringBuilder();
        stringBuilder3.append(" YFoldP0:       ");
        stringBuilder3.append(this.mYFoldP0.x);
        stringBuilder3.append(", ");
        stringBuilder3.append(this.mYFoldP0.y);
        Log.d(str2, stringBuilder3.toString());
        str2 = TAG;
        stringBuilder3 = new StringBuilder();
        stringBuilder3.append(" YFoldP1:       ");
        stringBuilder3.append(this.mYFoldP1.x);
        stringBuilder3.append(", ");
        stringBuilder3.append(this.mYFoldP1.y);
        Log.d(str2, stringBuilder3.toString());
        str2 = TAG;
        stringBuilder3 = new StringBuilder();
        stringBuilder3.append(" LengthT->O:    ");
        stringBuilder3.append(this.mLenOfTouchOrigin);
        Log.d(str2, stringBuilder3.toString());
    }
}
