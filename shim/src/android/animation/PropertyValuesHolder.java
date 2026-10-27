package android.animation;

/** Property values holder stub. */
public class PropertyValuesHolder {
    public static PropertyValuesHolder ofInt(String propertyName, int... values) { return new PropertyValuesHolder(); }
    public static PropertyValuesHolder ofFloat(String propertyName, float... values) { return new PropertyValuesHolder(); }
    public static PropertyValuesHolder ofObject(String propertyName, TypeEvaluator evaluator, Object... values) { return new PropertyValuesHolder(); }
    public static PropertyValuesHolder ofKeyframe(String propertyName, Keyframe... values) { return new PropertyValuesHolder(); }
    public void setIntValues(int... values) {}
    public void setFloatValues(float... values) {}
    public void setObjectValues(Object... values) {}
    public void setEvaluator(TypeEvaluator evaluator) {}
    public String getPropertyName() { return null; }
    public void setPropertyName(String propertyName) {}
    public Object getAnimatedValue() { return null; }
    public void setConverter(Object converter) {}

    public interface Keyframe {
        float getFraction();
        Object getValue();
        Object getInterpolator();
    }
}
