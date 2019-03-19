package com.eschao.android.widget.pageflip;

import android.opengl.GLES20;

final class FoldBackVertexes extends Vertexes {
    private static final String TAG = "FoldBackVertexes";
    float mMaskAlpha;

    public FoldBackVertexes() {
        this.mSizeOfPerVex = 4;
        this.mMaskAlpha = 0.6f;
    }

    public void set(int i) {
        super.set(i << 1, 4, true);
        this.mNext = 0;
    }

    public void setMaskAlpha(int i) {
        if (i < 0 || i > 255) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Alpha: ");
            stringBuilder.append(i);
            stringBuilder.append("is out of [0 .. 255]!");
            throw new IllegalArgumentException(stringBuilder.toString());
        }
        this.mMaskAlpha = ((float) i) / 255.0f;
    }

    public void setMaskAlpha(float f) {
        if (f < 0.0f || f > 1.0f) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Alpha: ");
            stringBuilder.append(f);
            stringBuilder.append("is out of [0 .. 1]!");
            throw new IllegalArgumentException(stringBuilder.toString());
        }
        this.mMaskAlpha = f;
    }

    public void draw(FoldBackVertexProgram foldBackVertexProgram, Page page, boolean z, int i) {
        GLES20.glUniformMatrix4fv(foldBackVertexProgram.mMVPMatrixLoc, 1, false, VertexProgram.MVPMatrix, 0);
        GLES20.glBindTexture(3553, page.getBackTextureID());
        GLES20.glUniform1i(foldBackVertexProgram.mTextureLoc, 0);
        GLES20.glActiveTexture(33985);
        GLES20.glBindTexture(3553, i);
        GLES20.glUniform1i(foldBackVertexProgram.mShadowLoc, 1);
        float f = 0.0f;
        GLES20.glUniform1f(foldBackVertexProgram.mTexXOffsetLoc, z ? 1.0f : 0.0f);
        i = foldBackVertexProgram.mMaskColorLoc;
        float f2 = page.maskColor[0][0];
        float f3 = page.maskColor[0][1];
        float f4 = page.maskColor[0][2];
        if (!z) {
            f = this.mMaskAlpha;
        }
        GLES20.glUniform4f(i, f2, f3, f4, f);
        drawWith(5, foldBackVertexProgram.mVertexPosLoc, foldBackVertexProgram.mTexCoordLoc);
    }
}
