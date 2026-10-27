package com.jcraft.jorbis;

/** Package-private bridge for JOrbis's PCM read method. */
public final class JOrbisPcm {
    private JOrbisPcm() {}

    public static int read(VorbisFile file, byte[] buffer, int length, int[] bitstream) {
        return file.read(buffer, length, 0, 2, 1, bitstream);
    }
}
