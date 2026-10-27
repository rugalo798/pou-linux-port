package android.content.pm;

/** PackageItemInfo base. */
public class PackageItemInfo {
    public String name;
    public String packageName;
    public int labelRes;
    public CharSequence nonLocalizedLabel;
    public int icon;
    public int banner;
    public int logo;
    public int descriptionRes;
    public boolean enabled = true;
    public android.os.Bundle metaData;
    public int showUserIcon;

    public CharSequence loadLabel(PackageManager pm) { return nonLocalizedLabel != null ? nonLocalizedLabel : name; }
    public android.graphics.drawable.Drawable loadIcon(PackageManager pm) { return null; }
    public android.graphics.drawable.Drawable loadUnbadgedIcon(PackageManager pm) { return null; }
    public android.graphics.drawable.Drawable loadLogo(PackageManager pm) { return null; }
    public android.graphics.drawable.Drawable loadBanner(PackageManager pm) { return null; }
    public CharSequence loadDescription(PackageManager pm) { return null; }
    public int describeContents() { return 0; }
    public void writeToParcel(android.os.Parcel dest, int parcelableFlags) {}
    public String toString() { return "PackageItemInfo(" + name + ")"; }
}
