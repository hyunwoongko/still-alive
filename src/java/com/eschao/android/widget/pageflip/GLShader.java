package com.eschao.android.widget.pageflip;

import android.content.Context;
import android.opengl.GLES20;
import android.util.Log;

public class GLShader {
    private static final String TAG = "GLShader";
    private final int INVALID_GL_HANDLE = -1;
    int mShaderRef = -1;

    public GLShader compile(Context context, int i, int i2) throws PageFlipException {
        String readGLSLFromResource = readGLSLFromResource(context, i2);
        StringBuilder stringBuilder;
        if (readGLSLFromResource.length() < 1) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Empty GLSL shader for resource id:");
            stringBuilder.append(i2);
            throw new PageFlipException(stringBuilder.toString());
        }
        this.mShaderRef = GLES20.glCreateShader(i);
        if (this.mShaderRef != -1) {
            GLES20.glShaderSource(this.mShaderRef, readGLSLFromResource);
            GLES20.glCompileShader(this.mShaderRef);
            int[] iArr = new int[1];
            GLES20.glGetShaderiv(this.mShaderRef, 35713, iArr, 0);
            if (iArr[0] != 0) {
                return this;
            }
            readGLSLFromResource = TAG;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Can'top compile shader for type: ");
            stringBuilder2.append(i);
            stringBuilder2.append("Error: ");
            stringBuilder2.append(GLES20.glGetError());
            Log.e(readGLSLFromResource, stringBuilder2.toString());
            readGLSLFromResource = TAG;
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Compile shader error: ");
            stringBuilder2.append(GLES20.glGetShaderInfoLog(this.mShaderRef));
            Log.e(readGLSLFromResource, stringBuilder2.toString());
            GLES20.glDeleteShader(this.mShaderRef);
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Can't compile shader fortype: ");
            stringBuilder2.append(i);
            throw new PageFlipException(stringBuilder2.toString());
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append("Can't create shader. Error: ");
        stringBuilder.append(GLES20.glGetError());
        throw new PageFlipException(stringBuilder.toString());
    }

    public void delete() {
        if (this.mShaderRef != -1) {
            GLES20.glDeleteShader(this.mShaderRef);
            this.mShaderRef = -1;
        }
    }

    public int getShaderRef() {
        return this.mShaderRef;
    }

    /* Access modifiers changed, original: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0053 A:{SYNTHETIC, Splitter:B:24:0x0053} */
    public java.lang.String readGLSLFromResource(android.content.Context r5, int r6) throws com.eschao.android.widget.pageflip.PageFlipException {
        /*
        r4 = this;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = 0;
        r2 = new java.io.BufferedReader;	 Catch:{ IOException -> 0x0039 }
        r3 = new java.io.InputStreamReader;	 Catch:{ IOException -> 0x0039 }
        r5 = r5.getResources();	 Catch:{ IOException -> 0x0039 }
        r5 = r5.openRawResource(r6);	 Catch:{ IOException -> 0x0039 }
        r3.<init>(r5);	 Catch:{ IOException -> 0x0039 }
        r2.<init>(r3);	 Catch:{ IOException -> 0x0039 }
    L_0x0018:
        r5 = r2.readLine();	 Catch:{ IOException -> 0x0034, all -> 0x0031 }
        if (r5 == 0) goto L_0x0027;
    L_0x001e:
        r0.append(r5);	 Catch:{ IOException -> 0x0034, all -> 0x0031 }
        r5 = "\n";
        r0.append(r5);	 Catch:{ IOException -> 0x0034, all -> 0x0031 }
        goto L_0x0018;
    L_0x0027:
        if (r2 == 0) goto L_0x002c;
    L_0x0029:
        r2.close();	 Catch:{ IOException -> 0x002c }
    L_0x002c:
        r5 = r0.toString();
        return r5;
    L_0x0031:
        r5 = move-exception;
        r1 = r2;
        goto L_0x0051;
    L_0x0034:
        r5 = move-exception;
        r1 = r2;
        goto L_0x003a;
    L_0x0037:
        r5 = move-exception;
        goto L_0x0051;
    L_0x0039:
        r5 = move-exception;
    L_0x003a:
        r0 = new com.eschao.android.widget.pageflip.PageFlipException;	 Catch:{ all -> 0x0037 }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0037 }
        r2.<init>();	 Catch:{ all -> 0x0037 }
        r3 = "Could not open resource: ";
        r2.append(r3);	 Catch:{ all -> 0x0037 }
        r2.append(r6);	 Catch:{ all -> 0x0037 }
        r6 = r2.toString();	 Catch:{ all -> 0x0037 }
        r0.<init>(r6, r5);	 Catch:{ all -> 0x0037 }
        throw r0;	 Catch:{ all -> 0x0037 }
    L_0x0051:
        if (r1 == 0) goto L_0x0056;
    L_0x0053:
        r1.close();	 Catch:{ IOException -> 0x0056 }
    L_0x0056:
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eschao.android.widget.pageflip.GLShader.readGLSLFromResource(android.content.Context, int):java.lang.String");
    }
}
