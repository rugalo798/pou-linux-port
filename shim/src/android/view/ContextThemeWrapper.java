package android.view;

/** ContextThemeWrapper in the android.view package (as used by the game). */
public class ContextThemeWrapper extends android.content.ContextThemeWrapper {
    public ContextThemeWrapper() { super(); }
    public ContextThemeWrapper(android.content.Context base, int themeResId) { super(base, themeResId); }
    public ContextThemeWrapper(android.content.Context base, android.content.res.Resources.Theme theme) { super(base, theme); }
}
