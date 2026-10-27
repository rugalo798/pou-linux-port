package android.hardware;

/** Sensor event. */
public class SensorEvent {
    public final float[] values;
    public Sensor sensor;
    public int accuracy;
    public long timestamp;

    public SensorEvent(int valueArraySize) { values = new float[valueArraySize]; }
}
