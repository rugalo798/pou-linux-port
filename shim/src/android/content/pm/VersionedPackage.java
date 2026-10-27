package android.content.pm;

/** VersionedPackage. */
public final class VersionedPackage implements android.os.Parcelable {
    private final String packageName;
    private final long versionCode;
    public VersionedPackage(String packageName, int versionCode) { this.packageName = packageName; this.versionCode = versionCode; }
    public VersionedPackage(String packageName, long versionCode) { this.packageName = packageName; this.versionCode = versionCode; }
    public String getPackageName() { return packageName; }
    public long getVersionCode() { return versionCode; }
    public int describeContents() { return 0; }
    public void writeToParcel(android.os.Parcel dest, int flags) {}
}
