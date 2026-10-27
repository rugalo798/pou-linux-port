package android.content.pm;

/** InstrumentationInfo fields. */
public class InstrumentationInfo extends PackageItemInfo implements android.os.Parcelable {
    public String targetPackage;
    public String sourceDir;
    public String publicSourceDir;
    public String dataDir;
    public boolean functionalTest;
    public boolean handleProfiling;
    public int describeContents() { return 0; }
    public void writeToParcel(android.os.Parcel dest, int flags) {}
}
