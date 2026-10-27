package android.view;

import android.graphics.Canvas;
import android.graphics.Rect;

/** Surface wrapper. */
public class Surface {
    private final SurfaceHolder holder;
    public Surface(SurfaceHolder holder) { this.holder = holder; }
    public boolean isValid() { return true; }
    public Canvas lockCanvas(Rect dirty) { return holder.lockCanvas(dirty); }
    public Canvas lockCanvas() { return holder.lockCanvas(); }
    public Canvas lockHardwareCanvas() { return holder.lockHardwareCanvas(); }
    public void unlockCanvasAndPost(Canvas canvas) { holder.unlockCanvasAndPost(canvas); }
    public void release() {}
    public int describeContents() { return 0; }
    public boolean isConsumerClosed() { return false; }
    public String toString() { return "Surface"; }
}
