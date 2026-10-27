package android.content.pm;

/** ResolveInfo fields. */
public class ResolveInfo implements android.os.Parcelable {
    public ActivityInfo activityInfo;
    public ServiceInfo serviceInfo;
    public ProviderInfo providerInfo;
    public android.content.IntentFilter filter;
    public int priority;
    public int preferredOrder;
    public int match;
    public int specificIndex = -1;
    public boolean isDefault;
    public int labelRes;
    public CharSequence nonLocalizedLabel;
    public int icon;
    public String resolvePackageName;

    public CharSequence loadLabel(PackageManager pm) { return nonLocalizedLabel != null ? nonLocalizedLabel : (activityInfo != null ? activityInfo.loadLabel(pm) : null); }
    public android.graphics.drawable.Drawable loadIcon(PackageManager pm) { return activityInfo != null ? activityInfo.loadIcon(pm) : null; }
    public int describeContents() { return 0; }
    public void writeToParcel(android.os.Parcel dest, int flags) {}
    public void dump(Object printer, String prefix) {}
    public String toString() { return "ResolveInfo"; }
}
