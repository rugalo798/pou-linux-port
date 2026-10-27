package android.os;

/** ParcelUuid. */
public final class ParcelUuid implements Parcelable {
    private final java.util.UUID uuid;
    public ParcelUuid(java.util.UUID uuid) { this.uuid = uuid; }
    public java.util.UUID getUuid() { return uuid; }
    public static ParcelUuid fromString(String uuid) { return new ParcelUuid(java.util.UUID.fromString(uuid)); }
    public int describeContents() { return 0; }
    public void writeToParcel(Parcel dest, int flags) {}
    public String toString() { return uuid == null ? "null" : uuid.toString(); }
    public boolean equals(Object o) { return o instanceof ParcelUuid && java.util.Objects.equals(uuid, ((ParcelUuid) o).uuid); }
    public int hashCode() { return uuid == null ? 0 : uuid.hashCode(); }
    public static final Parcelable.Creator<ParcelUuid> CREATOR = new Parcelable.Creator<ParcelUuid>() {
        public ParcelUuid createFromParcel(Parcel source) { return null; }
        public ParcelUuid[] newArray(int size) { return new ParcelUuid[size]; }
    };
}
