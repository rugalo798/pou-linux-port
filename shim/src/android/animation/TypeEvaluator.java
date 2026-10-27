package android.animation;

/** Type evaluator. */
public interface TypeEvaluator<T> {
    T evaluate(float fraction, T startValue, T endValue);
}
