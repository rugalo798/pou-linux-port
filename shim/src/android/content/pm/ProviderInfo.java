package android.content.pm;

/** ProviderInfo fields. */
public class ProviderInfo extends PackageItemInfo implements android.os.Parcelable {
    public String authority;
    public String readPermission;
    public String writePermission;
    public String processName;
    public int initOrder;
    public int flags;
    public boolean exported;
    public boolean enabled = true;
    public boolean grantUriPermissions;
    public boolean multiprocess;
    public boolean syncable;
    public ApplicationInfo applicationInfo;
    public int describeContents() { return 0; }
    public void writeToParcel(android.os.Parcel dest, int flags) {}
}
