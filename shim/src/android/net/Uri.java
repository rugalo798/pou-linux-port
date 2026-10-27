package android.net;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/** URI parser. */
public class Uri implements Parcelable, Comparable<Uri> {
    public static final Uri EMPTY = new Uri("");
    private final String uri;

    private Uri(String uri) { this.uri = uri == null ? "" : uri; }

    public static Uri parse(String uriString) { return new Uri(uriString); }
    public static Uri fromFile(File file) { return new Uri("file://" + file.getAbsolutePath()); }
    public static Uri fromParts(String scheme, String ssp, String fragment) {
        return new Uri(scheme + ":" + ssp + (fragment != null ? "#" + fragment : ""));
    }
    public static Uri withAppendedPath(Uri baseUri, String pathSegment) {
        String base = baseUri.toString();
        return parse(base.endsWith("/") ? base + pathSegment : base + "/" + pathSegment);
    }
    public static String encode(String s) {
        try { return URLEncoder.encode(s, "UTF-8"); } catch (UnsupportedEncodingException e) { return s; }
    }
    public static String encode(String s, String allow) { return encode(s); }
    public static String decode(String s) {
        try { return URLDecoder.decode(s, "UTF-8"); } catch (Exception e) { return s; }
    }

    public String getScheme() {
        int i = uri.indexOf(':');
        return i < 0 ? null : uri.substring(0, i);
    }
    public String getSchemeSpecificPart() {
        int i = uri.indexOf(':');
        return i < 0 ? uri : uri.substring(i + 1);
    }
    public String getEncodedSchemeSpecificPart() { return getSchemeSpecificPart(); }
    public String getAuthority() {
        int start = uri.indexOf("://");
        if (start < 0) return null;
        start += 3;
        int end = uri.length();
        int slash = uri.indexOf('/', start);
        if (slash >= 0) end = slash;
        int q = uri.indexOf('?', start);
        if (q >= 0 && q < end) end = q;
        int h = uri.indexOf('#', start);
        if (h >= 0 && h < end) end = h;
        return start < end ? uri.substring(start, end) : null;
    }
    public String getHost() {
        String authority = getAuthority();
        if (authority == null) return null;
        int at = authority.indexOf('@');
        if (at >= 0) authority = authority.substring(at + 1);
        int colon = authority.indexOf(':');
        return colon >= 0 ? authority.substring(0, colon) : authority;
    }
    public int getPort() {
        String authority = getAuthority();
        if (authority == null) return -1;
        int colon = authority.lastIndexOf(':');
        if (colon < 0) return -1;
        try { return Integer.parseInt(authority.substring(colon + 1)); } catch (Exception e) { return -1; }
    }
    public String getPath() {
        String ssp = getSchemeSpecificPart();
        int start = ssp.indexOf('/');
        if (start < 0) return "";
        int end = ssp.length();
        int q = ssp.indexOf('?', start);
        if (q >= 0) end = q;
        int h = ssp.indexOf('#', start);
        if (h >= 0 && h < end) end = h;
        return ssp.substring(start, end);
    }
    public String getEncodedPath() { return getPath(); }
    public String getQuery() {
        int q = uri.indexOf('?');
        if (q < 0) return null;
        int h = uri.indexOf('#', q);
        return h < 0 ? uri.substring(q + 1) : uri.substring(q + 1, h);
    }
    public String getEncodedQuery() { return getQuery(); }
    public String getFragment() {
        int h = uri.indexOf('#');
        return h < 0 ? null : uri.substring(h + 1);
    }
    public String getEncodedFragment() { return getFragment(); }
    public String getLastPathSegment() {
        String path = getPath();
        if (path == null || path.isEmpty()) return null;
        if (path.endsWith("/")) path = path.substring(0, path.length() - 1);
        int slash = path.lastIndexOf('/');
        return slash < 0 ? path : path.substring(slash + 1);
    }
    public List<String> getPathSegments() {
        String path = getPath();
        List<String> out = new ArrayList<>();
        if (path == null) return out;
        for (String s : path.split("/")) if (!s.isEmpty()) out.add(s);
        return out;
    }
    public String getQueryParameter(String key) {
        String query = getQuery();
        if (query == null) return null;
        for (String part : query.split("&")) {
            int eq = part.indexOf('=');
            String k = eq < 0 ? part : part.substring(0, eq);
            if (decode(k).equals(key)) return eq < 0 ? "" : decode(part.substring(eq + 1));
        }
        return null;
    }
    public Set<String> getQueryParameterNames() {
        String query = getQuery();
        if (query == null) return Collections.emptySet();
        Set<String> out = new LinkedHashSet<>();
        for (String part : query.split("&")) {
            int eq = part.indexOf('=');
            out.add(decode(eq < 0 ? part : part.substring(0, eq)));
        }
        return out;
    }
    public List<String> getQueryParameters(String key) {
        List<String> out = new ArrayList<>();
        String v = getQueryParameter(key);
        if (v != null) out.add(v);
        return out;
    }
    public boolean isHierarchical() { return uri.contains("://") || uri.startsWith("/"); }
    public boolean isOpaque() { return !isHierarchical(); }
    public boolean isRelative() { return !isAbsolute(); }
    public boolean isAbsolute() { return getScheme() != null; }
    public Builder buildUpon() { return new Builder(uri); }
    public String toString() { return uri; }
    public boolean equals(Object o) { return o instanceof Uri && uri.equals(((Uri) o).uri); }
    public int hashCode() { return uri.hashCode(); }
    public int compareTo(Uri other) { return uri.compareTo(other.uri); }
    public int describeContents() { return 0; }
    public void writeToParcel(Parcel dest, int flags) { dest.writeString(uri); }

    public static final Parcelable.Creator<Uri> CREATOR = new Parcelable.Creator<Uri>() {
        public Uri createFromParcel(Parcel source) { return new Uri(source.readString()); }
        public Uri[] newArray(int size) { return new Uri[size]; }
    };

    public static final class Builder {
        private String value;
        public Builder() { value = ""; }
        Builder(String value) { this.value = value; }
        public Builder scheme(String scheme) { value = scheme + ":" + (value.contains(":") ? value.substring(value.indexOf(':') + 1) : value); return this; }
        public Builder authority(String authority) {
            String rest = value;
            int i = rest.indexOf("://");
            if (i >= 0) rest = rest.substring(i + 3);
            int slash = rest.indexOf('/');
            rest = slash >= 0 ? rest.substring(slash) : "";
            value = (value.contains(":") ? value.substring(0, value.indexOf(':') + 1) : "") + "//" + authority + rest;
            return this;
        }
        public Builder encodedAuthority(String authority) { return authority(authority); }
        public Builder path(String path) {
            String base = value;
            int q = base.indexOf('?');
            String suffix = q >= 0 ? base.substring(q) : "";
            if (q >= 0) base = base.substring(0, q);
            int schemeEnd = base.indexOf("://");
            if (schemeEnd >= 0) {
                int slash = base.indexOf('/', schemeEnd + 3);
                base = slash >= 0 ? base.substring(0, slash) : base;
            }
            value = base + path + suffix;
            return this;
        }
        public Builder encodedPath(String path) { return path(path); }
        public Builder appendPath(String newSegment) { return path(getPath() + "/" + newSegment); }
        public Builder appendEncodedPath(String newSegment) { return appendPath(newSegment); }
        public Builder query(String query) {
            int q = value.indexOf('?');
            if (q >= 0) value = value.substring(0, q);
            if (query != null) value += "?" + query;
            return this;
        }
        public Builder encodedQuery(String query) { return query(query); }
        public Builder appendQueryParameter(String key, String value) {
            String q = getQuery();
            String pair = encode(key) + "=" + encode(value);
            return query(q == null || q.isEmpty() ? pair : q + "&" + pair);
        }
        public Builder fragment(String fragment) {
            int h = value.indexOf('#');
            if (h >= 0) value = value.substring(0, h);
            if (fragment != null) value += "#" + fragment;
            return this;
        }
        public Builder encodedFragment(String fragment) { return fragment(fragment); }
        public Builder opaquePart(String opaquePart) { value = opaquePart; return this; }
        public Builder encodedOpaquePart(String opaquePart) { return opaquePart(opaquePart); }
        private String getPath() {
            int start = value.indexOf("://");
            if (start < 0) return "";
            int slash = value.indexOf('/', start + 3);
            if (slash < 0) return "";
            int end = value.length();
            int q = value.indexOf('?', slash);
            if (q >= 0) end = q;
            int h = value.indexOf('#', slash);
            if (h >= 0 && h < end) end = h;
            return value.substring(slash, end);
        }
        private String getQuery() {
            int q = value.indexOf('?');
            if (q < 0) return null;
            int h = value.indexOf('#', q);
            return h < 0 ? value.substring(q + 1) : value.substring(q + 1, h);
        }
        public Uri build() { return new Uri(value); }
        public String toString() { return value; }
    }
}
