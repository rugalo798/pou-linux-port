package android.content.pm;

/** ServiceInfo fields. */
public class ServiceInfo extends PackageItemInfo implements android.os.Parcelable {
    public String permission;
    public String processName;
    public String descriptionRes;
    public int flags;
    public boolean exported;
    public boolean enabled = true;
    public ApplicationInfo applicationInfo;
    public int describeContents() { return 0; }
    public void writeToParcel(android.os.Parcel dest, int flags) {}
}
