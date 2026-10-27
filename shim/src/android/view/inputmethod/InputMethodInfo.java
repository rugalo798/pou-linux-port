package android.view.inputmethod;

import android.content.Context;
import android.content.pm.ServiceInfo;
import android.content.pm.ResolveInfo;

/** Input method info stub. */
public final class InputMethodInfo {
    private final String id;
    private final String packageName;
    private final CharSequence label;
    private final String serviceName;
    private final ServiceInfo serviceInfo;

    public InputMethodInfo(Context context, ResolveInfo ri) {
        this.id = ri != null && ri.serviceInfo != null ? ri.serviceInfo.packageName + "/" + ri.serviceInfo.name : "";
        this.packageName = ri != null && ri.serviceInfo != null ? ri.serviceInfo.packageName : "";
        this.label = ri != null ? ri.loadLabel(context.getPackageManager()) : "";
        this.serviceName = ri != null && ri.serviceInfo != null ? ri.serviceInfo.name : "";
        this.serviceInfo = ri != null ? ri.serviceInfo : null;
    }
    public InputMethodInfo(String packageName, String className, CharSequence label, String settingsActivity) {
        this.id = packageName + "/" + className;
        this.packageName = packageName;
        this.label = label;
        this.serviceName = className;
        this.serviceInfo = null;
    }
    public String getId() { return id; }
    public String getPackageName() { return packageName; }
    public String getServiceName() { return serviceName; }
    public ServiceInfo getServiceInfo() { return serviceInfo; }
    public CharSequence loadLabel(android.content.pm.PackageManager pm) { return label; }
    public String getSettingsActivity() { return null; }
    public int getSubtypeCount() { return 0; }
    public InputMethodSubtype getSubtypeAt(int index) { return null; }
    public android.graphics.drawable.Drawable loadIcon(android.content.pm.PackageManager pm) { return null; }
    public android.content.ComponentName getComponent() { return new android.content.ComponentName(packageName, serviceName); }
    public boolean isDefault() { return false; }
    public int getIsDefaultResourceId() { return 0; }
    public boolean supportsSwitchingToNextInputMethod() { return false; }
    public String toString() { return id; }
}
