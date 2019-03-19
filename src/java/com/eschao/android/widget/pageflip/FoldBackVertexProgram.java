package com.eschao.android.widget.pageflip;

import android.content.Context;
import android.opengl.GLES20;

public class FoldBackVertexProgram extends VertexProgram {
    static final String VAR_MASK_COLOR = "u_maskColor";
    static final String VAR_SHADOW_TEXTURE = "u_shadow";
    static final String VAR_TEXTRUE_OFFSET = "u_texXOffset";
    int mMaskColorLoc = -1;
    int mShadowLoc = -1;
    int mTexXOffsetLoc = -1;

    public /* bridge */ /* synthetic */ void initMatrix(float f, float f2, float f3, float f4) {
        super.initMatrix(f, f2, f3, f4);
    }

    public FoldBackVertexProgram init(Context context) throws PageFlipException {
        super.init(context, R.raw.fold_back_vertex_shader, R.raw.fold_back_fragment_shader);
        return this;
    }

    /* Access modifiers changed, original: protected */
    public void getVarsLocation() {
        super.getVarsLocation();
        if (this.mProgramRef != 0) {
            this.mShadowLoc = GLES20.glGetUniformLocation(this.mProgramRef, VAR_SHADOW_TEXTURE);
            this.mMaskColorLoc = GLES20.glGetUniformLocation(this.mProgramRef, VAR_MASK_COLOR);
            this.mTexXOffsetLoc = GLES20.glGetUniformLocation(this.mProgramRef, VAR_TEXTRUE_OFFSET);
        }
    }

    public void delete() {
        super.delete();
        this.mShadowLoc = -1;
        this.mMaskColorLoc = -1;
        this.mTexXOffsetLoc = -1;
    }
}
