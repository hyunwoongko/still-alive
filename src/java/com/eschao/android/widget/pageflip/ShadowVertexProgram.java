package com.eschao.android.widget.pageflip;

import android.content.Context;
import android.opengl.GLES20;

public class ShadowVertexProgram extends GLProgram {
    static final String VAR_MVP_MATRIX = "u_MVPMatrix";
    static final String VAR_VERTEX_POS = "a_vexPosition";
    static final String VAR_VERTEX_Z = "u_vexZ";
    int mMVPMatrixLoc = -1;
    int mVertexPosLoc = -1;
    int mVertexZLoc = -1;

    public ShadowVertexProgram init(Context context) throws PageFlipException {
        super.init(context, R.raw.shadow_vertex_shader, R.raw.shadow_fragment_shader);
        return this;
    }

    /* Access modifiers changed, original: protected */
    public void getVarsLocation() {
        if (this.mProgramRef != 0) {
            this.mVertexZLoc = GLES20.glGetUniformLocation(this.mProgramRef, VAR_VERTEX_Z);
            this.mVertexPosLoc = GLES20.glGetAttribLocation(this.mProgramRef, VAR_VERTEX_POS);
            this.mMVPMatrixLoc = GLES20.glGetUniformLocation(this.mProgramRef, VAR_MVP_MATRIX);
        }
    }

    public void delete() {
        super.delete();
        this.mMVPMatrixLoc = -1;
        this.mVertexZLoc = -1;
        this.mVertexPosLoc = -1;
    }
}
