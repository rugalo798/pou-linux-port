package android.view;

import android.graphics.Canvas;
import android.graphics.Rect;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/** SurfaceHolder implementation. */
class SurfaceHolderImpl implements SurfaceHolder {
    private final SurfaceView view;
    private final List<Callback> callbacks = new CopyOnWriteArrayList<>();
    private final Surface surface = new Surface(this);
    private BufferedImage buffer;
    private int width, height;
    private boolean creating;

    SurfaceHolderImpl(SurfaceView view) { this.view = view; }

    public void addCallback(Callback callback) { if (callback != null && !callbacks.contains(callback)) callbacks.add(callback); }
    public void removeCallback(Callback callback) { callbacks.remove(callback); }
    public boolean isCreating() { return creating; }
    public void setType(int type) {}
    public void setFixedSize(int width, int height) { view.setFixedSize(width, height); }
    public void setSizeFromLayout() {}
    public void setFormat(int format) {}
    public void setKeepScreenOn(boolean screenOn) {}

    public Canvas lockCanvas() { return lockCanvas(null); }
    public Canvas lockCanvas(Rect dirty) {
        ensureBuffer();
        return new Canvas(buffer);
    }
    public Canvas lockHardwareCanvas() { return lockCanvas(); }
    public void unlockCanvasAndPost(Canvas canvas) {
        try {
            if (buffer != null) view.postBuffer(buffer);
        } finally {
            if (canvas != null) canvas.dispose();
        }
    }
    public Rect getSurfaceFrame() { return new Rect(0, 0, width, height); }
    public Surface getSurface() { return surface; }

    void created() {
        if (creating) return;
        creating = true;
        for (Callback callback : callbacks) callback.surfaceCreated(this);
        creating = false;
    }
    void changed(int w, int h) {
        width = w;
        height = h;
        ensureBuffer();
        for (Callback callback : callbacks) callback.surfaceChanged(this, PixelFormat.RGBA_8888, w, h);
    }
    void destroyed() {
        for (Callback callback : callbacks) callback.surfaceDestroyed(this);
    }
    private void ensureBuffer() {
        int w = view.getSurfaceWidth();
        int h = view.getSurfaceHeight();
        if (w <= 0) w = desktop.DesktopEnv.width;
        if (h <= 0) h = desktop.DesktopEnv.height;
        if (buffer == null || buffer.getWidth() != w || buffer.getHeight() != h) {
            buffer = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            width = w;
            height = h;
        }
    }
}
