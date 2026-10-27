package android.animation;

/** Object animator stub. */
public final class ObjectAnimator extends ValueAnimator {
    public static ObjectAnimator ofInt(Object target, String propertyName, int... values) { return new ObjectAnimator(); }
    public static ObjectAnimator ofFloat(Object target, String propertyName, float... values) { return new ObjectAnimator(); }
    public static ObjectAnimator ofArgb(Object target, String propertyName, int... values) { return new ObjectAnimator(); }
    public static ObjectAnimator ofObject(Object target, String propertyName, TypeEvaluator evaluator, Object... values) { return new ObjectAnimator(); }
    public static ObjectAnimator ofPropertyValuesHolder(Object target, PropertyValuesHolder... values) { return new ObjectAnimator(); }
    public void setPropertyName(String propertyName) {}
    public String getPropertyName() { return null; }
    public void setProperty(Object property) {}
    public Object getProperty() { return null; }
    @Override public void setTarget(Object target) {}
    @Override public Object getTarget() { return null; }
    public void setAutoCancel(boolean autoCancel) {}
    public void setupStartValues() {}
    public void setupEndValues() {}
    @Override public ObjectAnimator clone() { return new ObjectAnimator(); }
}
