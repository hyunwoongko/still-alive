package com.eschao.android.widget.pageflip;

import android.content.Context;
import android.opengl.GLES20;

public class GLProgram {
    protected final int INVALID_GL_HANDLE = -1;
    protected GLShader mFragment = new GLShader();
    protected int mProgramRef = -1;
    protected GLShader mVertex = new GLShader();

    /* Access modifiers changed, original: protected */
    public void getVarsLocation() {
    }

    public GLProgram init(Context context, int i, int i2) throws PageFlipException {
        try {
            this.mVertex.compile(context, 35633, i);
            this.mFragment.compile(context, 35632, i2);
            this.mProgramRef = GLES20.glCreateProgram();
            if (this.mProgramRef == 0) {
                this.mVertex.delete();
                this.mFragment.delete();
                throw new PageFlipException("Can't create texture program");
            }
            GLES20.glAttachShader(this.mProgramRef, this.mVertex.getShaderRef());
            GLES20.glAttachShader(this.mProgramRef, this.mFragment.getShaderRef());
            GLES20.glLinkProgram(this.mProgramRef);
            int[] iArr = new int[1];
            GLES20.glGetProgramiv(this.mProgramRef, 35714, iArr, 0);
            if (iArr[0] == 0) {
                delete();
                throw new PageFlipException("Can't link program");
            }
            GLES20.glUseProgram(this.mProgramRef);
            getVarsLocation();
            return this;
        } catch (PageFlipException e) {
            this.mVertex.delete();
            this.mFragment.delete();
            throw e;
        }
    }

    public void delete() {
        this.mVertex.delete();
        this.mFragment.delete();
        if (this.mProgramRef != -1) {
            GLES20.glDeleteProgram(this.mProgramRef);
            this.mProgramRef = -1;
        }
    }

    public int getProgramRef() {
        return this.mProgramRef;
    }
}
