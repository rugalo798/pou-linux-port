package android.view;

import android.content.Context;
import android.graphics.Canvas;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/** SurfaceView backed by a Swing JPanel and a BufferedImage back buffer. */
public class SurfaceView extends View {
    private final SurfaceHolderImpl holder = new SurfaceHolderImpl(this);
    private SurfacePanel panel;
    private int fixedWidth, fixedHeight;
    private boolean created;
    private int frames;
    private static final String SCREENSHOT = System.getProperty("pou.screenshot");

    public SurfaceView(Context context) { super(context); }

    public SurfaceHolder getHolder() { return holder; }

    public void setFixedSize(int width, int height) { fixedWidth = width; fixedHeight = height; }
    public int getSurfaceWidth() { return panel != null && panel.getWidth() > 0 ? panel.getWidth() : (fixedWidth > 0 ? fixedWidth : desktop.DesktopEnv.width); }
    public int getSurfaceHeight() { return panel != null && panel.getHeight() > 0 ? panel.getHeight() : (fixedHeight > 0 ? fixedHeight : desktop.DesktopEnv.height); }

    public void postBuffer(BufferedImage image) {
        if (panel != null) panel.post(image);
        if (SCREENSHOT != null && ++frames == Integer.getInteger("pou.screenshot.frame", 1500)) {
            try {
                ImageIO.write(image, "png", new File(SCREENSHOT));
            } catch (IOException e) {
                System.err.println("[pou-desktop] screenshot failed: " + e);
            }
        }
    }

    @Override
    public JComponent getComponent() {
        if (panel == null) {
            panel = new SurfacePanel();
            panel.setBackground(java.awt.Color.BLACK);
            panel.addComponentListener(new ComponentAdapter() {
                @Override public void componentResized(ComponentEvent e) {
                    if (!created) ensureCreated();
                    holder.changed(panel.getWidth(), panel.getHeight());
                }
                @Override public void componentShown(ComponentEvent e) { ensureCreated(); }
            });
            if (!Boolean.getBoolean("pou.noinput")) {
                panel.addMouseListener(new MouseAdapter() {
                    @Override public void mousePressed(MouseEvent e) {
                        dispatchTouchEvent(MotionEvent.obtain(0, 0, MotionEvent.ACTION_DOWN, e.getX(), e.getY(), 0));
                    }
                    @Override public void mouseReleased(MouseEvent e) {
                        dispatchTouchEvent(MotionEvent.obtain(0, 0, MotionEvent.ACTION_UP, e.getX(), e.getY(), 0));
                    }
                });
                panel.addMouseMotionListener(new MouseMotionAdapter() {
                    @Override public void mouseDragged(MouseEvent e) {
                        dispatchTouchEvent(MotionEvent.obtain(0, 0, MotionEvent.ACTION_MOVE, e.getX(), e.getY(), 0));
                    }
                });
            }
        }
        return panel;
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        getComponent();
        SwingUtilities.invokeLater(this::ensureCreated);
    }

    private void ensureCreated() {
        if (created || panel == null) return;
        int w = panel.getWidth(), h = panel.getHeight();
        if (w <= 0 || h <= 0) { SwingUtilities.invokeLater(this::ensureCreated); return; }
        created = true;
        holder.created();
        holder.changed(w, h);
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (created) { created = false; holder.destroyed(); }
    }

    public void setZOrderOnTop(boolean onTop) {}
    public void setZOrderMediaOverlay(boolean isMediaOverlay) {}
    public void setEGLContextClientVersion(int version) {}
    public void setEGLConfigChooser(int redSize, int greenSize, int blueSize, int alphaSize, int depthSize, int stencilSize) {}
    public void setRenderer(Object renderer) {}
    public void setPreserveEGLContextOnPause(boolean preserve) {}
    public void setWillNotDraw(boolean willNotDraw) {}

    private class SurfacePanel extends JPanel {
        private BufferedImage display;
        private final Object displayLock = new Object();

        SurfacePanel() { setDoubleBuffered(true); }

        void post(BufferedImage image) {
            // The game keeps drawing into its surface buffer after posting. Copy it
            // so Swing never paints a frame that is still being rendered.
            synchronized (displayLock) {
                if (display == null || display.getWidth() != image.getWidth()
                        || display.getHeight() != image.getHeight()) {
                    display = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
                }
                Graphics2D g = display.createGraphics();
                g.setComposite(AlphaComposite.Src);
                g.drawImage(image, 0, 0, null);
                g.dispose();
            }
            repaint();
        }

        @Override protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            synchronized (displayLock) {
                if (display != null) g.drawImage(display, 0, 0, getWidth(), getHeight(), null);
            }
        }
    }
}
