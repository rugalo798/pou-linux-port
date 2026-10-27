package android.content;

import android.os.Parcel;
import android.os.Parcelable;

/** ComponentName. */
public class ComponentName implements Parcelable, Cloneable, Comparable<ComponentName> {
    public final String packageName;
    public final String className;

    public ComponentName(String pkg, String cls) { packageName = pkg; className = cls; }
    public ComponentName(Context pkg, String cls) { packageName = pkg.getPackageName(); className = cls; }
    public ComponentName(Context pkg, Class<?> cls) { packageName = pkg.getPackageName(); className = cls.getName(); }
    public ComponentName(Parcel src) { packageName = src.readString(); className = src.readString(); }

    public String getPackageName() { return packageName; }
    public String getClassName() { return className; }
    public String getShortClassName() { return className != null && className.startsWith(packageName + ".") ? className.substring(packageName.length()) : className; }
    public String flattenToString() { return packageName + "/" + className; }
    public String flattenToShortString() { return packageName + "/" + getShortClassName(); }
    public static ComponentName unflattenFromString(String str) {
        int sep = str.indexOf('/');
        if (sep < 0) return null;
        String pkg = str.substring(0, sep);
        String cls = str.substring(sep + 1);
        if (cls.startsWith(".")) cls = pkg + cls;
        return new ComponentName(pkg, cls);
    }
    public static ComponentName createRelative(String pkg, String cls) { return new ComponentName(pkg, cls.startsWith(".") ? pkg + cls : cls); }
    public static ComponentName createRelative(Context pkg, String cls) { return createRelative(pkg.getPackageName(), cls); }
    public ComponentName clone() { return new ComponentName(packageName, className); }
    public boolean equals(Object obj) { if (!(obj instanceof ComponentName)) return false; ComponentName o = (ComponentName) obj; return eq(packageName, o.packageName) && eq(className, o.className); }
    private static boolean eq(String a, String b) { return a == null ? b == null : a.equals(b); }
    public int hashCode() { return (packageName != null ? packageName.hashCode() : 0) ^ (className != null ? className.hashCode() : 0); }
    public String toString() { return "ComponentInfo{" + packageName + "/" + className + "}"; }
    public int compareTo(ComponentName that) { return flattenToString().compareTo(that.flattenToString()); }
    public int describeContents() { return 0; }
    public void writeToParcel(android.os.Parcel dest, int flags) { dest.writeString(packageName); dest.writeString(className); }
    public static final Parcelable.Creator<ComponentName> CREATOR = new Parcelable.Creator<ComponentName>() {
        public ComponentName createFromParcel(android.os.Parcel in) { return new ComponentName(in); }
        public ComponentName[] newArray(int size) { return new ComponentName[size]; }
    };
}
