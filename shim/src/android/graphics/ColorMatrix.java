package android.graphics;

/** 4x5 color matrix. */
public class ColorMatrix {
    final float[] m = new float[20];

    public ColorMatrix() { reset(); }
    public ColorMatrix(float[] src) { System.arraycopy(src, 0, m, 0, 20); }
    public ColorMatrix(ColorMatrix src) { System.arraycopy(src.m, 0, m, 0, 20); }

    public void reset() {
        java.util.Arrays.fill(m, 0f);
        m[0] = m[6] = m[12] = m[18] = 1f;
    }
    public void set(float[] src) { System.arraycopy(src, 0, m, 0, 20); }
    public void set(ColorMatrix src) { System.arraycopy(src.m, 0, m, 0, 20); }
    public void setScale(float rScale, float gScale, float bScale, float aScale) {
        reset();
        m[0] = rScale; m[6] = gScale; m[12] = bScale; m[18] = aScale;
    }
    public void setSaturation(float sat) {
        reset();
        float r = 0.213f, g = 0.715f, b = 0.072f;
        float inv = 1f - sat;
        m[0] = inv * r + sat; m[1] = inv * g; m[2] = inv * b;
        m[5] = inv * r; m[6] = inv * g + sat; m[7] = inv * b;
        m[10] = inv * r; m[11] = inv * g; m[12] = inv * b + sat;
    }
    public void setRotate(int axis, float degrees) {
        reset();
        double rad = Math.toRadians(degrees);
        float c = (float) Math.cos(rad), s = (float) Math.sin(rad);
        switch (axis) {
            case 0: m[6] = c; m[7] = -s; m[11] = s; m[12] = c; break;
            case 1: m[0] = c; m[2] = s; m[10] = -s; m[12] = c; break;
            case 2: m[0] = c; m[1] = -s; m[5] = s; m[6] = c; break;
        }
    }
    public void setConcat(ColorMatrix matA, ColorMatrix matB) {
        float[] tmp = new float[20];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                tmp[i * 5 + j] = 0;
                for (int k = 0; k < 4; k++) tmp[i * 5 + j] += matA.m[i * 5 + k] * matB.m[k * 5 + j];
            }
            tmp[i * 5 + 4] = matA.m[i * 5 + 4];
            for (int k = 0; k < 4; k++) tmp[i * 5 + 4] += matA.m[i * 5 + k] * matB.m[k * 5 + 4];
        }
        System.arraycopy(tmp, 0, m, 0, 20);
    }
    public void preConcat(ColorMatrix prematrix) { setConcat(prematrix, this); }
    public void postConcat(ColorMatrix postmatrix) { setConcat(this, postmatrix); }
    public void setRGB2YUV() { reset(); }
    public void setYUV2RGB() { reset(); }
    public float[] getArray() { return m; }
    public String toString() { return java.util.Arrays.toString(m); }
}
