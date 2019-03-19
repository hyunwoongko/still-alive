package com.eschao.android.widget.pageflip;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PointF;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class Page {
    private static final int BACK_TEXTURE_ID = 2;
    private static final int FIRST_TEXTURE_ID = 0;
    private static final int INVALID_TEXTURE_ID = -1;
    private static final int SECOND_TEXTURE_ID = 1;
    private static final int TEXTURE_SIZE = 3;
    private static final int[][] mFoldVexOrders = new int[][]{new int[]{4, 3, 1, 2, 0}, new int[]{3, 3, 2, 0, 1}, new int[]{3, 2, 1, 3, 0}, new int[]{2, 2, 3, 1, 0}, new int[]{1, 0, 1, 3, 2}};
    private static final int[][] mPageApexOrders = new int[][]{new int[]{0, 1, 2, 3}, new int[]{1, 0, 3, 2}, new int[]{2, 3, 0, 1}, new int[]{3, 2, 1, 0}};
    float bottom;
    GLPoint diagonalP;
    float height;
    float left;
    private int mApexOrderIndex;
    private float[] mApexTexCoords;
    private float[] mApexes;
    private int mFrontVertexSize;
    private FloatBuffer mFullPageTexCoordsBuf;
    private FloatBuffer mFullPageVexBuf;
    private int[] mTexIDs;
    private int[] mUnusedTexIDs;
    private int mUnusedTexSize;
    private GLPoint mXFoldP;
    private GLPoint mYFoldP;
    float[][] maskColor;
    GLPoint originP;
    float right;
    float texHeight;
    float texWidth;
    float top;
    float width;

    public Page() {
        init(0.0f, 0.0f, 0.0f, 0.0f);
    }

    public Page(float f, float f2, float f3, float f4) {
        init(f, f2, f3, f4);
    }

    private void init(float f, float f2, float f3, float f4) {
        this.top = f3;
        this.left = f;
        this.right = f2;
        this.bottom = f4;
        this.width = this.right - this.left;
        this.height = this.top - this.bottom;
        this.texWidth = this.width;
        this.texHeight = this.height;
        this.mFrontVertexSize = 0;
        this.mApexOrderIndex = 0;
        this.mXFoldP = new GLPoint();
        this.mYFoldP = new GLPoint();
        this.originP = new GLPoint();
        this.diagonalP = new GLPoint();
        this.maskColor = new float[][]{new float[]{0.0f, 0.0f, 0.0f}, new float[]{0.0f, 0.0f, 0.0f}, new float[]{0.0f, 0.0f, 0.0f}};
        this.mTexIDs = new int[]{-1, -1, -1};
        this.mUnusedTexSize = 0;
        this.mUnusedTexIDs = new int[]{-1, -1, -1};
        createVertexesBuffer();
        buildVertexesOfFullPage();
    }

    public boolean isLeftPage() {
        return this.right <= 0.0f;
    }

    public boolean isRightPage() {
        return this.left >= 0.0f;
    }

    public float width() {
        return this.width;
    }

    public float height() {
        return this.height;
    }

    public boolean isFirstTextureSet() {
        return this.mTexIDs[0] != -1;
    }

    public boolean isSecondTextureSet() {
        return this.mTexIDs[1] != -1;
    }

    public boolean isBackTextureSet() {
        return this.mTexIDs[2] != -1;
    }

    public void deleteUnusedTextures() {
        if (this.mUnusedTexSize > 0) {
            GLES20.glDeleteTextures(this.mUnusedTexSize, this.mUnusedTexIDs, 0);
            this.mUnusedTexSize = 0;
        }
    }

    public Page setFirstTextureWithSecond() {
        if (this.mTexIDs[0] > -1) {
            int[] iArr = this.mUnusedTexIDs;
            int i = this.mUnusedTexSize;
            this.mUnusedTexSize = i + 1;
            iArr[i] = this.mTexIDs[0];
        }
        this.maskColor[0][0] = this.maskColor[1][0];
        this.maskColor[0][1] = this.maskColor[1][1];
        this.maskColor[0][2] = this.maskColor[1][2];
        this.mTexIDs[0] = this.mTexIDs[1];
        this.mTexIDs[1] = -1;
        return this;
    }

    public Page setSecondTextureWithFirst() {
        if (this.mTexIDs[1] > -1) {
            int[] iArr = this.mUnusedTexIDs;
            int i = this.mUnusedTexSize;
            this.mUnusedTexSize = i + 1;
            iArr[i] = this.mTexIDs[1];
        }
        this.maskColor[1][0] = this.maskColor[0][0];
        this.maskColor[1][1] = this.maskColor[0][1];
        this.maskColor[1][2] = this.maskColor[0][2];
        this.mTexIDs[1] = this.mTexIDs[0];
        this.mTexIDs[0] = -1;
        return this;
    }

    public Page swapTexturesWithPage(Page page) {
        int[] iArr = this.mUnusedTexIDs;
        int i = this.mUnusedTexSize;
        this.mUnusedTexSize = i + 1;
        iArr[i] = this.mTexIDs[1];
        this.mTexIDs[1] = this.mTexIDs[0];
        iArr = this.mUnusedTexIDs;
        i = this.mUnusedTexSize;
        this.mUnusedTexSize = i + 1;
        iArr[i] = this.mTexIDs[2];
        this.mTexIDs[2] = page.mTexIDs[0];
        this.mTexIDs[0] = page.mTexIDs[2];
        page.mTexIDs[2] = -1;
        page.mTexIDs[0] = page.mTexIDs[1];
        page.mTexIDs[1] = -1;
        return this;
    }

    /* Access modifiers changed, original: 0000 */
    public int getBackTextureID() {
        if (this.mTexIDs[2] == -1) {
            return this.mTexIDs[0];
        }
        return this.mTexIDs[2];
    }

    /* Access modifiers changed, original: 0000 */
    public boolean contains(float f, float f2) {
        return this.left < this.right && this.bottom < this.top && this.left <= f && f < this.right && this.bottom <= f2 && f2 < this.top;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean isXInRange(float f, float f2) {
        float f3 = this.width * f2;
        if (this.originP.x < 0.0f) {
            if (f >= this.originP.x + f3) {
                return false;
            }
        } else if (f <= this.originP.x - f3) {
            return false;
        }
        return true;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean isXOutsidePage(float f) {
        if (this.originP.x < 0.0f) {
            if (f <= this.diagonalP.x) {
                return false;
            }
        } else if (f >= this.diagonalP.x) {
            return false;
        }
        return true;
    }

    private void computeIndexOfApexOrder() {
        this.mApexOrderIndex = 0;
        if (this.originP.x >= this.right || this.originP.y >= 0.0f) {
            if (this.originP.y > 0.0f) {
                this.mApexOrderIndex++;
            }
            if (this.originP.x < this.right) {
                this.mApexOrderIndex++;
                return;
            }
            return;
        }
        this.mApexOrderIndex = 3;
    }

    /* Access modifiers changed, original: 0000 */
    public Page setOriginAndDiagonalPoints(boolean z, float f) {
        if (!z || this.left >= 0.0f) {
            this.originP.x = this.right;
            this.diagonalP.x = this.left;
        } else {
            this.originP.x = this.left;
            this.diagonalP.x = this.right;
        }
        if (f > 0.0f) {
            this.originP.y = this.bottom;
            this.diagonalP.y = this.top;
        } else {
            this.originP.y = this.top;
            this.diagonalP.y = this.bottom;
        }
        computeIndexOfApexOrder();
        this.originP.texX = (this.originP.x - this.left) / this.texWidth;
        this.originP.texY = (this.top - this.originP.y) / this.texHeight;
        this.diagonalP.texX = (this.diagonalP.x - this.left) / this.texWidth;
        this.diagonalP.texY = (this.top - this.diagonalP.y) / this.texHeight;
        return this;
    }

    /* Access modifiers changed, original: 0000 */
    public void invertYOfOriginPoint() {
        float f = this.originP.y;
        this.originP.y = this.diagonalP.y;
        this.diagonalP.y = f;
        f = this.originP.texY;
        this.originP.texY = this.diagonalP.texY;
        this.diagonalP.texY = f;
        computeIndexOfApexOrder();
    }

    public float textureX(float f) {
        return (f - this.left) / this.texWidth;
    }

    public float textureY(float f) {
        return (this.top - f) / this.texHeight;
    }

    public void deleteAllTextures() {
        GLES20.glDeleteTextures(3, this.mTexIDs, 0);
        this.mTexIDs[0] = -1;
        this.mTexIDs[1] = -1;
        this.mTexIDs[2] = -1;
    }

    public void setFirstTexture(Bitmap bitmap) {
        int computeAverageColor = PageFlipUtils.computeAverageColor(bitmap, 30);
        this.maskColor[0][0] = ((float) Color.red(computeAverageColor)) / 255.0f;
        this.maskColor[0][1] = ((float) Color.green(computeAverageColor)) / 255.0f;
        this.maskColor[0][2] = ((float) Color.blue(computeAverageColor)) / 255.0f;
        GLES20.glGenTextures(1, this.mTexIDs, 0);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, this.mTexIDs[0]);
        GLES20.glTexParameterf(3553, 10241, 9729.0f);
        GLES20.glTexParameterf(3553, 10240, 9729.0f);
        GLUtils.texImage2D(3553, 0, bitmap, 0);
    }

    public void setSecondTexture(Bitmap bitmap) {
        int computeAverageColor = PageFlipUtils.computeAverageColor(bitmap, 30);
        this.maskColor[1][0] = ((float) Color.red(computeAverageColor)) / 255.0f;
        this.maskColor[1][1] = ((float) Color.green(computeAverageColor)) / 255.0f;
        this.maskColor[1][2] = ((float) Color.blue(computeAverageColor)) / 255.0f;
        GLES20.glGenTextures(1, this.mTexIDs, 1);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, this.mTexIDs[1]);
        GLES20.glTexParameterf(3553, 10241, 9729.0f);
        GLES20.glTexParameterf(3553, 10240, 9729.0f);
        GLUtils.texImage2D(3553, 0, bitmap, 0);
    }

    public void setBackTexture(Bitmap bitmap) {
        if (bitmap == null) {
            if (this.mTexIDs[2] != -1) {
                int[] iArr = this.mUnusedTexIDs;
                int i = this.mUnusedTexSize;
                this.mUnusedTexSize = i + 1;
                iArr[i] = this.mTexIDs[2];
            }
            this.mTexIDs[2] = -1;
            return;
        }
        int computeAverageColor = PageFlipUtils.computeAverageColor(bitmap, 50);
        this.maskColor[2][0] = ((float) Color.red(computeAverageColor)) / 255.0f;
        this.maskColor[2][1] = ((float) Color.green(computeAverageColor)) / 255.0f;
        this.maskColor[2][2] = ((float) Color.blue(computeAverageColor)) / 255.0f;
        GLES20.glGenTextures(1, this.mTexIDs, 2);
        GLES20.glBindTexture(3553, this.mTexIDs[2]);
        GLES20.glTexParameterf(3553, 10241, 9729.0f);
        GLES20.glTexParameterf(3553, 10240, 9729.0f);
        GLUtils.texImage2D(3553, 0, bitmap, 0);
    }

    public void drawFrontPage(VertexProgram vertexProgram, Vertexes vertexes) {
        GLES20.glUniformMatrix4fv(vertexProgram.mMVPMatrixLoc, 1, false, VertexProgram.MVPMatrix, 0);
        GLES20.glBindTexture(3553, this.mTexIDs[0]);
        GLES20.glUniform1i(vertexProgram.mTextureLoc, 0);
        vertexes.drawWith(5, vertexProgram.mVertexPosLoc, vertexProgram.mTexCoordLoc, 0, this.mFrontVertexSize);
        GLES20.glBindTexture(3553, this.mTexIDs[1]);
        GLES20.glUniform1i(vertexProgram.mTextureLoc, 0);
        GLES20.glDrawArrays(5, this.mFrontVertexSize, vertexes.mVertexesSize - this.mFrontVertexSize);
    }

    public void drawFullPage(VertexProgram vertexProgram, boolean z) {
        if (z) {
            drawFullPage(vertexProgram, this.mTexIDs[0]);
        } else {
            drawFullPage(vertexProgram, this.mTexIDs[1]);
        }
    }

    private void drawFullPage(VertexProgram vertexProgram, int i) {
        GLES20.glBindTexture(3553, i);
        GLES20.glUniform1i(vertexProgram.mTextureLoc, 0);
        GLES20.glVertexAttribPointer(vertexProgram.mVertexPosLoc, 3, 5126, false, 0, this.mFullPageVexBuf);
        GLES20.glEnableVertexAttribArray(vertexProgram.mVertexPosLoc);
        GLES20.glVertexAttribPointer(vertexProgram.mTexCoordLoc, 2, 5126, false, 0, this.mFullPageTexCoordsBuf);
        GLES20.glEnableVertexAttribArray(vertexProgram.mTexCoordLoc);
        GLES20.glDrawArrays(6, 0, 4);
    }

    private void createVertexesBuffer() {
        this.mFullPageVexBuf = ByteBuffer.allocateDirect(48).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.mFullPageTexCoordsBuf = ByteBuffer.allocateDirect(32).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.mApexes = new float[12];
        this.mApexTexCoords = new float[8];
    }

    public void buildVertexesOfPageWhenVertical(Vertexes vertexes, PointF pointF) {
        int i;
        int i2;
        int i3;
        if (isXOutsidePage(pointF.x)) {
            i = 4;
        } else {
            float textureX = textureX(pointF.x);
            float f = textureX;
            this.mXFoldP.set(pointF.x, this.originP.y, 0.0f, f, this.originP.texY);
            this.mYFoldP.set(pointF.x, this.diagonalP.y, 0.0f, f, this.diagonalP.texY);
            i = 1;
        }
        int[] iArr = mPageApexOrders[this.mApexOrderIndex];
        int[] iArr2 = mFoldVexOrders[i];
        if (iArr2[0] > 1) {
            vertexes.addVertex(this.mXFoldP).addVertex(this.mYFoldP);
        }
        for (i2 = 1; i2 < iArr2[0]; i2++) {
            i3 = iArr[iArr2[i2]];
            int i4 = i3 * 3;
            i3 <<= 1;
            vertexes.addVertex(this.mApexes[i4], this.mApexes[i4 + 1], 0.0f, this.mApexTexCoords[i3], this.mApexTexCoords[i3 + 1]);
        }
        this.mFrontVertexSize = vertexes.mNext / 3;
        if (iArr2[0] > 1) {
            GLPoint gLPoint = this.mXFoldP;
            this.mYFoldP.z = -1.0f;
            gLPoint.z = -1.0f;
            vertexes.addVertex(this.mXFoldP).addVertex(this.mYFoldP);
        }
        for (int i5 = iArr2[0]; i5 < iArr2.length; i5++) {
            i2 = iArr[iArr2[i5]];
            i3 = i2 * 3;
            i2 <<= 1;
            vertexes.addVertex(this.mApexes[i3], this.mApexes[i3 + 1], -1.0f, this.mApexTexCoords[i2], this.mApexTexCoords[i2 + 1]);
        }
    }

    public void buildVertexesOfPageWhenSlope(Vertexes vertexes, PointF pointF, PointF pointF2, float f) {
        int i;
        int i2;
        float f2 = this.height * 0.5f;
        this.mXFoldP.set(pointF.x, this.originP.y, 0.0f, textureX(pointF.x), this.originP.texY);
        if (isXOutsidePage(pointF.x)) {
            i = 2;
            this.mXFoldP.x = this.diagonalP.x;
            this.mXFoldP.y = this.originP.y + ((pointF.x - this.diagonalP.x) / f);
            this.mXFoldP.texX = this.diagonalP.texX;
            this.mXFoldP.texY = textureY(this.mXFoldP.y);
        } else {
            i = 0;
        }
        this.mYFoldP.set(this.originP.x, pointF2.y, 0.0f, this.originP.texX, textureY(pointF2.y));
        if (Math.abs(pointF2.y) > f2) {
            i++;
            this.mYFoldP.x = this.originP.x + (f * (pointF2.y - this.diagonalP.y));
            if (isXOutsidePage(this.mYFoldP.x)) {
                i++;
            } else {
                this.mYFoldP.y = this.diagonalP.y;
                this.mYFoldP.texX = textureX(this.mYFoldP.x);
                this.mYFoldP.texY = this.diagonalP.texY;
            }
        }
        int[] iArr = mPageApexOrders[this.mApexOrderIndex];
        int[] iArr2 = mFoldVexOrders[i];
        if (iArr2[0] > 1) {
            vertexes.addVertex(this.mXFoldP).addVertex(this.mYFoldP);
        }
        for (i2 = 1; i2 < iArr2[0]; i2++) {
            i = iArr[iArr2[i2]];
            int i3 = i * 3;
            i <<= 1;
            vertexes.addVertex(this.mApexes[i3], this.mApexes[i3 + 1], 0.0f, this.mApexTexCoords[i], this.mApexTexCoords[i + 1]);
        }
        this.mFrontVertexSize = vertexes.mNext / 3;
        if (iArr2[0] > 1) {
            GLPoint gLPoint = this.mXFoldP;
            this.mYFoldP.z = -1.0f;
            gLPoint.z = -1.0f;
            vertexes.addVertex(this.mXFoldP).addVertex(this.mYFoldP);
        }
        for (i2 = iArr2[0]; i2 < iArr2.length; i2++) {
            i = iArr[iArr2[i2]];
            int i4 = i * 3;
            i <<= 1;
            vertexes.addVertex(this.mApexes[i4], this.mApexes[i4 + 1], -1.0f, this.mApexTexCoords[i], this.mApexTexCoords[i + 1]);
        }
    }

    private void buildVertexesOfFullPage() {
        this.mApexes[0] = this.right;
        this.mApexes[1] = this.bottom;
        this.mApexes[2] = 0.0f;
        this.mApexTexCoords[0] = textureX(this.right);
        this.mApexTexCoords[1] = textureY(this.bottom);
        this.mApexes[3] = this.right;
        this.mApexes[4] = this.top;
        this.mApexes[5] = 0.0f;
        this.mApexTexCoords[2] = textureX(this.right);
        this.mApexTexCoords[3] = textureY(this.top);
        this.mApexes[6] = this.left;
        this.mApexes[7] = this.top;
        this.mApexes[8] = 0.0f;
        this.mApexTexCoords[4] = textureX(this.left);
        this.mApexTexCoords[5] = textureY(this.top);
        this.mApexes[9] = this.left;
        this.mApexes[10] = this.bottom;
        this.mApexes[11] = 0.0f;
        this.mApexTexCoords[6] = textureX(this.left);
        this.mApexTexCoords[7] = textureY(this.bottom);
        this.mFullPageVexBuf.put(this.mApexes, 0, 12).position(0);
        this.mFullPageTexCoordsBuf.put(this.mApexTexCoords, 0, 8).position(0);
    }
}
