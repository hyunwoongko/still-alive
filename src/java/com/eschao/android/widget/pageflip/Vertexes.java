package com.eschao.android.widget.pageflip;

import android.opengl.GLES20;
import android.util.Log;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

class Vertexes {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String TAG = "Vertexes";
    int mNext;
    int mSizeOfPerVex;
    float[] mTextureCoords;
    FloatBuffer mTextureCoordsBuf;
    float[] mVertexes;
    FloatBuffer mVertexesBuf;
    int mVertexesSize;

    public Vertexes() {
        this.mNext = 0;
        this.mVertexesSize = 0;
        this.mSizeOfPerVex = 0;
        this.mVertexes = null;
        this.mVertexesBuf = null;
        this.mTextureCoords = null;
        this.mTextureCoordsBuf = null;
    }

    public Vertexes(int i, int i2) {
        set(i, i2, true);
    }

    public Vertexes(int i, int i2, boolean z) {
        set(i, i2, z);
    }

    public Vertexes set(int i, int i2, boolean z) {
        if (i2 < 2) {
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("sizeOfPerVex is invalid: ");
            stringBuilder.append(i2);
            Log.w(str, stringBuilder.toString());
            stringBuilder = new StringBuilder();
            stringBuilder.append("sizeOfPerVex:");
            stringBuilder.append(i2);
            stringBuilder.append("is less than 2!");
            throw new IllegalArgumentException(stringBuilder.toString());
        }
        this.mNext = 0;
        this.mVertexes = null;
        this.mVertexesBuf = null;
        this.mTextureCoords = null;
        this.mTextureCoordsBuf = null;
        this.mSizeOfPerVex = i2;
        i2 *= i;
        this.mVertexes = new float[i2];
        this.mVertexesBuf = ByteBuffer.allocateDirect(i2 * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        if (z) {
            this.mTextureCoords = new float[(i << 1)];
            this.mTextureCoordsBuf = ByteBuffer.allocateDirect(i << 3).order(ByteOrder.nativeOrder()).asFloatBuffer();
        }
        return this;
    }

    public Vertexes release() {
        this.mNext = 0;
        this.mVertexesSize = 0;
        this.mSizeOfPerVex = 0;
        this.mVertexes = null;
        this.mVertexesBuf = null;
        this.mTextureCoords = null;
        this.mTextureCoordsBuf = null;
        return this;
    }

    public int capacity() {
        return this.mVertexes == null ? 0 : this.mVertexes.length / this.mSizeOfPerVex;
    }

    public void reset() {
        this.mNext = 0;
    }

    public float getFloatAt(int i) {
        return (i < 0 || i >= this.mNext) ? 0.0f : this.mVertexes[i];
    }

    public Vertexes setVertex(int i, float f, float f2, float f3) {
        this.mVertexes[i] = f;
        this.mVertexes[i + 1] = f2;
        this.mVertexes[i + 2] = f3;
        return this;
    }

    public Vertexes setVertex(int i, float f, float f2, float f3, float f4) {
        this.mVertexes[i] = f;
        this.mVertexes[i + 1] = f2;
        this.mVertexes[i + 2] = f3;
        this.mVertexes[i + 3] = f4;
        return this;
    }

    public Vertexes setTextureCoord(int i, float f, float f2) {
        this.mTextureCoords[i] = f;
        this.mTextureCoords[i + 1] = f2;
        return this;
    }

    public Vertexes addVertex(float f, float f2, float f3) {
        float[] fArr = this.mVertexes;
        int i = this.mNext;
        this.mNext = i + 1;
        fArr[i] = f;
        float[] fArr2 = this.mVertexes;
        int i2 = this.mNext;
        this.mNext = i2 + 1;
        fArr2[i2] = f2;
        fArr2 = this.mVertexes;
        int i3 = this.mNext;
        this.mNext = i3 + 1;
        fArr2[i3] = f3;
        return this;
    }

    public Vertexes addVertex(float f, float f2, float f3, float f4, float f5) {
        int i = (this.mNext / this.mSizeOfPerVex) * 2;
        float[] fArr = this.mVertexes;
        int i2 = this.mNext;
        this.mNext = i2 + 1;
        fArr[i2] = f;
        float[] fArr2 = this.mVertexes;
        int i3 = this.mNext;
        this.mNext = i3 + 1;
        fArr2[i3] = f2;
        fArr2 = this.mVertexes;
        int i4 = this.mNext;
        this.mNext = i4 + 1;
        fArr2[i4] = f3;
        i4 = i + 1;
        this.mTextureCoords[i] = f4;
        this.mTextureCoords[i4] = f5;
        return this;
    }

    public Vertexes addVertex(float f, float f2, float f3, float f4) {
        float[] fArr = this.mVertexes;
        int i = this.mNext;
        this.mNext = i + 1;
        fArr[i] = f;
        float[] fArr2 = this.mVertexes;
        int i2 = this.mNext;
        this.mNext = i2 + 1;
        fArr2[i2] = f2;
        fArr2 = this.mVertexes;
        int i3 = this.mNext;
        this.mNext = i3 + 1;
        fArr2[i3] = f3;
        fArr2 = this.mVertexes;
        i3 = this.mNext;
        this.mNext = i3 + 1;
        fArr2[i3] = f4;
        return this;
    }

    public Vertexes addVertex(float f, float f2, float f3, float f4, float f5, float f6) {
        int i = (this.mNext / this.mSizeOfPerVex) * 2;
        float[] fArr = this.mVertexes;
        int i2 = this.mNext;
        this.mNext = i2 + 1;
        fArr[i2] = f;
        float[] fArr2 = this.mVertexes;
        int i3 = this.mNext;
        this.mNext = i3 + 1;
        fArr2[i3] = f2;
        fArr2 = this.mVertexes;
        int i4 = this.mNext;
        this.mNext = i4 + 1;
        fArr2[i4] = f3;
        fArr2 = this.mVertexes;
        i4 = this.mNext;
        this.mNext = i4 + 1;
        fArr2[i4] = f4;
        i4 = i + 1;
        this.mTextureCoords[i] = f5;
        this.mTextureCoords[i4] = f6;
        return this;
    }

    public Vertexes addVertex(GLPoint gLPoint) {
        int i = (this.mNext / this.mSizeOfPerVex) * 2;
        float[] fArr = this.mVertexes;
        int i2 = this.mNext;
        this.mNext = i2 + 1;
        fArr[i2] = gLPoint.x;
        fArr = this.mVertexes;
        i2 = this.mNext;
        this.mNext = i2 + 1;
        fArr[i2] = gLPoint.y;
        fArr = this.mVertexes;
        i2 = this.mNext;
        this.mNext = i2 + 1;
        fArr[i2] = gLPoint.z;
        i2 = i + 1;
        this.mTextureCoords[i] = gLPoint.texX;
        this.mTextureCoords[i2] = gLPoint.texY;
        return this;
    }

    public void toFloatBuffer(int i, int i2) {
        this.mVertexesBuf.put(this.mVertexes, i, i2).position(0);
        this.mVertexesSize = i2 / this.mSizeOfPerVex;
        if (this.mTextureCoords != null) {
            this.mTextureCoordsBuf.put(this.mTextureCoords, (i / this.mSizeOfPerVex) * 2, this.mVertexesSize * 2).position(0);
        }
    }

    public void toFloatBuffer() {
        this.mVertexesBuf.put(this.mVertexes, 0, this.mNext).position(0);
        this.mVertexesSize = this.mNext / this.mSizeOfPerVex;
        if (this.mTextureCoords != null) {
            this.mTextureCoordsBuf.put(this.mTextureCoords, 0, this.mVertexesSize << 1).position(0);
        }
    }

    public void drawWith(int i, int i2, int i3) {
        GLES20.glVertexAttribPointer(i2, this.mSizeOfPerVex, 5126, false, 0, this.mVertexesBuf);
        GLES20.glEnableVertexAttribArray(i2);
        GLES20.glVertexAttribPointer(i3, 2, 5126, false, 0, this.mTextureCoordsBuf);
        GLES20.glEnableVertexAttribArray(i3);
        GLES20.glDrawArrays(i, 0, this.mVertexesSize);
    }

    public void drawWith(int i, int i2, int i3, int i4, int i5) {
        GLES20.glVertexAttribPointer(i2, this.mSizeOfPerVex, 5126, false, 0, this.mVertexesBuf);
        GLES20.glEnableVertexAttribArray(i2);
        GLES20.glVertexAttribPointer(i3, 2, 5126, false, 0, this.mTextureCoordsBuf);
        GLES20.glEnableVertexAttribArray(i3);
        GLES20.glDrawArrays(i, i4, i5);
    }
}
