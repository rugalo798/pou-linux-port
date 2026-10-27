package android.app;

import android.content.Context;
import android.content.res.Configuration;

/** Application. */
public class Application extends Context {
    public void onCreate() {}
    public void onTerminate() {}
    public void onLowMemory() {}
    public void onTrimMemory(int level) {}
    public void onConfigurationChanged(Configuration newConfig) {}
    public void registerActivityLifecycleCallbacks(Object callbacks) {}
    public void unregisterActivityLifecycleCallbacks(Object callbacks) {}
    public void registerComponentCallbacks(Object callbacks) {}
    public void unregisterComponentCallbacks(Object callbacks) {}
    public void registerOnProvideAssistDataListener(Object callback) {}
    public void unregisterOnProvideAssistDataListener(Object callback) {}
}
