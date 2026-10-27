package android.content;

import android.content.res.Configuration;
import android.content.res.Resources;

/** Context with an overlay theme. */
public class ContextThemeWrapper extends ContextWrapper {
    private int themeResId;
    private Resources.Theme theme;

    public ContextThemeWrapper() { super(null); }
    public ContextThemeWrapper(Context base, int themeResId) { super(base); this.themeResId = themeResId; }
    public ContextThemeWrapper(Context base, Resources.Theme theme) { super(base); this.theme = theme; }

    @Override
    public Resources.Theme getTheme() {
        if (theme != null) return theme;
        if (base != null) return base.getTheme();
        return super.getTheme();
    }

    @Override
    public void setTheme(int resid) {
        themeResId = resid;
        theme = null;
    }

    public int getThemeResId() { return themeResId; }

    public void applyOverrideConfiguration(Configuration overrideConfiguration) {}

    public void attachBaseContext(Context newBase) { this.base = newBase; }
}
