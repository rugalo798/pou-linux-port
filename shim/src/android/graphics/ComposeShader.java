package android.graphics;

/** Compose shader; uses the first shader for now. */
public class ComposeShader extends Shader {
    private final Shader shaderA;
    private final Shader shaderB;
    public ComposeShader(Shader shaderA, Shader shaderB, PorterDuff.Mode mode) { this.shaderA = shaderA; this.shaderB = shaderB; }
    @Override
    public java.awt.Paint toAwtPaint() { return shaderA != null ? shaderA.toAwtPaint() : java.awt.Color.BLACK; }
}
