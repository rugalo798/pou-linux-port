package android.os;

/** UserHandle stub. */
public final class UserHandle implements Parcelable {
    public static final UserHandle ALL = new UserHandle(-1);
    public static final UserHandle CURRENT = new UserHandle(-2);
    public static final UserHandle CURRENT_OR_SELF = new UserHandle(-3);
    public static final UserHandle OWNER = new UserHandle(0);
    public static final UserHandle SYSTEM = new UserHandle(0);
    private final int handle;

    public UserHandle(int h) { handle = h; }
    public int getIdentifier() { return handle; }
    public static UserHandle getUserHandleForUid(int uid) { return new UserHandle(0); }
    public static int myUserId() { return 0; }
    public static int getUserId(int uid) { return 0; }
    public static UserHandle of(int userId) { return new UserHandle(userId); }
    public boolean isSystem() { return handle == 0; }
    public boolean isOwner() { return handle == 0; }
    public int describeContents() { return 0; }
    public void writeToParcel(Parcel dest, int flags) {}
    public String toString() { return "UserHandle(" + handle + ")"; }
}
