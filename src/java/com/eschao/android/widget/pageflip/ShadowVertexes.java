package com.eschao.android.widget.pageflip;

import android.opengl.GLES20;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

class ShadowVertexes {
    private int mBackward;
    ShadowColor mColor;
    private int mForward;
    int mMaxBackward;
    private int mSpaceOfFrontRear;
    float[] mVertexes;
    FloatBuffer mVertexesBuffer;
    int mVertexesSize;
    float vertexZ;

    public ShadowVertexes() {
        release();
        this.mColor = new ShadowColor();
    }

    public ShadowVertexes(int i, float f, float f2, float f3, float f4) {
        release();
        this.mSpaceOfFrontRear = i;
        this.mColor = new ShadowColor(f, f2, f3, f4);
    }

    public ShadowVertexes set(int i) {
        this.mMaxBackward = i << 3;
        i = (i << 4) + (this.mSpaceOfFrontRear << 2);
        this.mVertexes = new float[i];
        this.mVertexesBuffer = ByteBuffer.allocateDirect(i << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();
        reset();
        return this;
    }

    public void release() {
        this.mBackward = 0;
        this.mForward = 0;
        this.mMaxBackward = 0;
        this.mSpaceOfFrontRear = 0;
        this.mVertexes = null;
        this.mVertexesBuffer = null;
    }

    public void reset() {
        this.vertexZ = 0.0f;
        this.mBackward = this.mMaxBackward;
        this.mForward = this.mMaxBackward + (this.mSpaceOfFrontRear << 2);
    }

    public ShadowVertexes setVertexes(int i, float f, float f2, float f3, float f4) {
        int i2 = i + 1;
        this.mVertexes[i] = f;
        int i3 = i2 + 1;
        this.mVertexes[i2] = f2;
        int i4 = i3 + 1;
        this.mVertexes[i3] = this.mColor.startColor;
        i3 = i4 + 1;
        this.mVertexes[i4] = this.mColor.startAlpha;
        i4 = i3 + 1;
        this.mVertexes[i3] = f3;
        i3 = i4 + 1;
        this.mVertexes[i4] = f4;
        i4 = i3 + 1;
        this.mVertexes[i3] = this.mColor.endColor;
        this.mVertexes[i4] = this.mColor.endAlpha;
        return this;
    }

    public ShadowVertexes addVertexesBackward(float f, float f2, float f3, float f4) {
        float[] fArr = this.mVertexes;
        int i = this.mBackward - 1;
        this.mBackward = i;
        fArr[i] = this.mColor.endAlpha;
        fArr = this.mVertexes;
        i = this.mBackward - 1;
        this.mBackward = i;
        fArr[i] = this.mColor.endColor;
        fArr = this.mVertexes;
        i = this.mBackward - 1;
        this.mBackward = i;
        fArr[i] = f4;
        float[] fArr2 = this.mVertexes;
        int i2 = this.mBackward - 1;
        this.mBackward = i2;
        fArr2[i2] = f3;
        float[] fArr3 = this.mVertexes;
        int i3 = this.mBackward - 1;
        this.mBackward = i3;
        fArr3[i3] = this.mColor.startAlpha;
        fArr3 = this.mVertexes;
        i3 = this.mBackward - 1;
        this.mBackward = i3;
        fArr3[i3] = this.mColor.startColor;
        fArr3 = this.mVertexes;
        i3 = this.mBackward - 1;
        this.mBackward = i3;
        fArr3[i3] = f2;
        float[] fArr4 = this.mVertexes;
        int i4 = this.mBackward - 1;
        this.mBackward = i4;
        fArr4[i4] = f;
        return this;
    }

    public ShadowVertexes addVertexesForward(float f, float f2, float f3, float f4) {
        float[] fArr = this.mVertexes;
        int i = this.mForward;
        this.mForward = i + 1;
        fArr[i] = f;
        float[] fArr2 = this.mVertexes;
        int i2 = this.mForward;
        this.mForward = i2 + 1;
        fArr2[i2] = f2;
        fArr2 = this.mVertexes;
        int i3 = this.mForward;
        this.mForward = i3 + 1;
        fArr2[i3] = this.mColor.startColor;
        fArr2 = this.mVertexes;
        i3 = this.mForward;
        this.mForward = i3 + 1;
        fArr2[i3] = this.mColor.startAlpha;
        fArr2 = this.mVertexes;
        i3 = this.mForward;
        this.mForward = i3 + 1;
        fArr2[i3] = f3;
        fArr2 = this.mVertexes;
        i3 = this.mForward;
        this.mForward = i3 + 1;
        fArr2[i3] = f4;
        fArr2 = this.mVertexes;
        i3 = this.mForward;
        this.mForward = i3 + 1;
        fArr2[i3] = this.mColor.endColor;
        fArr2 = this.mVertexes;
        i3 = this.mForward;
        this.mForward = i3 + 1;
        fArr2[i3] = this.mColor.endAlpha;
        return this;
    }

    public ShadowVertexes addVertexes(boolean z, float f, float f2, float f3, float f4) {
        if (z) {
            return addVertexesForward(f, f2, f3, f4);
        }
        return addVertexesBackward(f, f2, f3, f4);
    }

    public void toFloatBuffer() {
        this.mVertexesSize = (this.mForward - this.mBackward) / 4;
        this.mVertexesBuffer.put(this.mVertexes, this.mBackward, this.mForward - this.mBackward).position(0);
    }

    public void toFloatBuffer(int i) {
        this.mVertexesBuffer.put(this.mVertexes, 0, i).position(0);
        this.mVertexesSize = i / 4;
    }

    public void draw(ShadowVertexProgram shadowVertexProgram) {
        if (this.mVertexesSize > 0) {
            GLES20.glUniformMatrix4fv(shadowVertexProgram.mMVPMatrixLoc, 1, false, VertexProgram.MVPMatrix, 0);
            GLES20.glUniform1f(shadowVertexProgram.mVertexZLoc, this.vertexZ);
            GLES20.glDisable(3553);
            GLES20.glEnable(3042);
            GLES20.glBlendFunc(770, 771);
            GLES20.glVertexAttribPointer(shadowVertexProgram.mVertexPosLoc, 4, 5126, false, 0, this.mVertexesBuffer);
            GLES20.glEnableVertexAttribArray(shadowVertexProgram.mVertexPosLoc);
            GLES20.glDrawArrays(5, 0, this.mVertexesSize);
            GLES20.glDisable(3042);
        }
    }
}
