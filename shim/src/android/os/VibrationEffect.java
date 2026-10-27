package android.os;

/** Vibration effect. */
public class VibrationEffect {
    public static final int DEFAULT_AMPLITUDE = -1;
    public static final int EFFECT_CLICK = 0;
    public static final int EFFECT_DOUBLE_CLICK = 1;
    public static final int EFFECT_TICK = 2;
    public static final int EFFECT_THUD = 3;
    public static final int EFFECT_POP = 4;
    public static final int EFFECT_HEAVY_CLICK = 5;

    private VibrationEffect() {}

    public static VibrationEffect createOneShot(long milliseconds, int amplitude) { return new VibrationEffect(); }
    public static VibrationEffect createWaveform(long[] timings, int repeat) { return new VibrationEffect(); }
    public static VibrationEffect createWaveform(long[] timings, int[] amplitudes, int repeat) { return new VibrationEffect(); }
    public static VibrationEffect createPredefined(int effectId) { return new VibrationEffect(); }
    public static VibrationEffect startWaveform(long startTime, long[] timings, int[] amplitudes, int repeat) { return new VibrationEffect(); }
    public static VibrationEffect startWaveform(long startTime, long[] timings, int repeat) { return new VibrationEffect(); }
}
