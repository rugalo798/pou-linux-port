package android.os;

/** Vibrator stub. */
public class Vibrator {
    public boolean hasVibrator() { return false; }
    public boolean hasAmplitudeControl() { return false; }
    public void vibrate(long milliseconds) {}
    public void vibrate(long milliseconds, android.media.AudioAttributes attributes) {}
    public void vibrate(long[] pattern, int repeat) {}
    public void vibrate(long[] pattern, int repeat, android.media.AudioAttributes attributes) {}
    public void vibrate(VibrationEffect vibe) {}
    public void vibrate(VibrationEffect vibe, android.media.AudioAttributes attributes) {}
    public void cancel() {}
    public int getId() { return 0; }
}
