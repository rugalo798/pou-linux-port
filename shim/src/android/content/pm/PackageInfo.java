package android.content.pm;

/** PackageInfo fields used by the game. */
public class PackageInfo implements android.os.Parcelable {
    public String packageName;
    public String[] splitNames;
    public int versionCode;
    public String versionName;
    public long firstInstallTime;
    public long lastUpdateTime;
    public ApplicationInfo applicationInfo;
    public ActivityInfo[] activities;
    public ServiceInfo[] services;
    public ProviderInfo[] providers;
    public ActivityInfo[] receivers;
    public InstrumentationInfo[] instrumentation;
    public String[] requestedPermissions;
    public int[] requestedPermissionsFlags;
    public Signature[] signatures;
    public SigningInfo signingInfo;
    public int[] gids;
    public String sharedUserId;
    public int sharedUserLabel;
    public int installLocation;
    public boolean isApex;
    public long longVersionCode;

    public int describeContents() { return 0; }
    public void writeToParcel(android.os.Parcel dest, int flags) {}
}
