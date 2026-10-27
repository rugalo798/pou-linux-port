package android.view;

import android.graphics.Canvas;
import android.graphics.Rect;

/** SurfaceHolder interface. */
public interface SurfaceHolder {
    int SURFACE_TYPE_NORMAL = 0;
    int SURFACE_TYPE_HARDWARE = 1;
    int SURFACE_TYPE_GPU = 2;

    void addCallback(Callback callback);
    void removeCallback(Callback callback);
    boolean isCreating();
    void setType(int type);
    void setFixedSize(int width, int height);
    void setSizeFromLayout();
    void setFormat(int format);
    void setKeepScreenOn(boolean screenOn);
    Canvas lockCanvas();
    Canvas lockCanvas(Rect dirty);
    Canvas lockHardwareCanvas();
    void unlockCanvasAndPost(Canvas canvas);
    Rect getSurfaceFrame();
    Surface getSurface();

    interface Callback {
        void surfaceCreated(SurfaceHolder holder);
        void surfaceChanged(SurfaceHolder holder, int format, int width, int height);
        void surfaceDestroyed(SurfaceHolder holder);
    }
}
