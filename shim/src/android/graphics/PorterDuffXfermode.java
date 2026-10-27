package android.graphics;

/** Porter-Duff xfermode. */
public class PorterDuffXfermode extends Xfermode {
    public final PorterDuff.Mode mode;

    public PorterDuffXfermode(PorterDuff.Mode mode) {
        this.mode = mode;
    }
}
