package com.eschao.android.widget.pageflip;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.Matrix;

class VertexProgram extends GLProgram {
    static final float[] MVMatrix = new float[16];
    static final float[] MVPMatrix = new float[16];
    static final String VAR_MVP_MATRIX = "u_MVPMatrix";
    static final String VAR_TEXTURE = "u_texture";
    static final String VAR_TEXTURE_COORD = "a_texCoord";
    static final String VAR_VERTEX_POS = "a_vexPosition";
    int mMVPMatrixLoc = -1;
    int mTexCoordLoc = -1;
    int mTextureLoc = -1;
    int mVertexPosLoc = -1;

    public VertexProgram init(Context context) throws PageFlipException {
        super.init(context, R.raw.vertex_shader, R.raw.fragment_shader);
        return this;
    }

    /* Access modifiers changed, original: protected */
    public void getVarsLocation() {
        if (this.mProgramRef != 0) {
            this.mVertexPosLoc = GLES20.glGetAttribLocation(this.mProgramRef, VAR_VERTEX_POS);
            this.mTexCoordLoc = GLES20.glGetAttribLocation(this.mProgramRef, VAR_TEXTURE_COORD);
            this.mMVPMatrixLoc = GLES20.glGetUniformLocation(this.mProgramRef, VAR_MVP_MATRIX);
            this.mTextureLoc = GLES20.glGetUniformLocation(this.mProgramRef, VAR_TEXTURE);
        }
    }

    public void delete() {
        super.delete();
        this.mTextureLoc = -1;
        this.mMVPMatrixLoc = -1;
        this.mTexCoordLoc = -1;
        this.mVertexPosLoc = -1;
    }

    public void initMatrix(float f, float f2, float f3, float f4) {
        float[] fArr = new float[16];
        Matrix.orthoM(fArr, 0, f, f2, f3, f4, 0.0f, 6000.0f);
        Matrix.setIdentityM(MVMatrix, 0);
        Matrix.setLookAtM(MVMatrix, 0, 0.0f, 0.0f, 3000.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);
        Matrix.setIdentityM(MVPMatrix, 0);
        Matrix.multiplyMM(MVPMatrix, 0, fArr, 0, MVMatrix, 0);
    }
}
