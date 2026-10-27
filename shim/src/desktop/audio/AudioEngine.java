package desktop.audio;

import android.content.Context;
import com.jcraft.jorbis.Info;
import com.jcraft.jorbis.JOrbisPcm;
import com.jcraft.jorbis.VorbisFile;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Small software mixer for the desktop port.  All game sounds are decoded to
 * 44.1 kHz stereo PCM and mixed into one Java Sound output line.  This makes
 * overlapping SoundPool effects, looping background music, and per-stream
 * volume/rate changes behave like their Android counterparts.
 */
public final class AudioEngine {
    public static final int SAMPLE_RATE = 44100;

    private static AudioEngine instance;

    public static synchronized AudioEngine get() {
        if (instance == null) instance = new AudioEngine();
        return instance;
    }

    public static final class PcmData {
        public final short[] samples;
        public final int frames;

        PcmData(short[] samples) {
            this.samples = samples;
            this.frames = samples.length / 2;
        }
    }

    private static final class Voice {
        final int id;
        final PcmData data;
        double position;
        float left;
        float right;
        float rate;
        int loop;
        boolean paused;
        Runnable onEnd;

        Voice(int id, PcmData data, float left, float right, int loop, float rate) {
            this.id = id;
            this.data = data;
            this.left = left;
            this.right = right;
            this.loop = loop;
            this.rate = Math.max(0.1f, Math.min(4.0f, rate));
        }
    }

    private final Object lock = new Object();
    private final List<Voice> voices = new ArrayList<>();
    private final SourceDataLine line;
    private final Thread mixer;
    private volatile boolean running = true;
    private int nextId = 1;

    private AudioEngine() {
        SourceDataLine opened = null;
        try {
            AudioFormat format = new AudioFormat(SAMPLE_RATE, 16, 2, true, false);
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
            if (AudioSystem.isLineSupported(info)) {
                opened = (SourceDataLine) AudioSystem.getLine(info);
                opened.open(format, 4096 * 4);
                opened.start();
            } else {
                System.err.println("[pou-desktop] no 44.1 kHz stereo audio output available");
            }
        } catch (Exception e) {
            System.err.println("[pou-desktop] audio output unavailable: " + e);
            if (opened != null) {
                try { opened.close(); } catch (Exception ignored) {}
                opened = null;
            }
        }
        line = opened;
        mixer = new Thread(this::mixLoop, "PouAudioMixer");
        mixer.setDaemon(true);
        mixer.start();
    }

    public PcmData loadResource(Context context, int resId) {
        if (context == null || context.getResources() == null) return null;
        try (InputStream in = context.getResources().openRawResource(resId)) {
            return decode(in);
        } catch (Exception e) {
            System.err.println("[pou-desktop] cannot decode raw audio 0x"
                    + Integer.toHexString(resId) + ": " + e);
            return null;
        }
    }

    public PcmData loadFile(String path) {
        if (path == null) return null;
        File file = new File(path);
        if (!file.isAbsolute() && !file.isFile()) {
            file = new File(desktop.DesktopEnv.assetsDir, path);
        }
        return loadFile(file);
    }

    public PcmData loadFile(File file) {
        try (InputStream in = new FileInputStream(file)) {
            return decode(in);
        } catch (Exception e) {
            System.err.println("[pou-desktop] cannot decode audio " + file + ": " + e);
            return null;
        }
    }

    public PcmData decode(InputStream raw) throws Exception {
        BufferedInputStream input = new BufferedInputStream(raw);
        input.mark(4);
        byte[] magic = new byte[4];
        int magicRead = input.read(magic);
        input.reset();
        if (magicRead == 4 && magic[0] == 'O' && magic[1] == 'g'
                && magic[2] == 'g' && magic[3] == 'S') {
            return decodeOgg(input);
        }
        return decodeSampled(input);
    }

    private PcmData decodeOgg(InputStream input) throws Exception {
        VorbisFile file = new VorbisFile(input, null, 0);
        try {
            Info info = file.getInfo(-1);
            if (info == null) return new PcmData(new short[0]);
            int channels = Math.max(1, info.channels);
            float rate = info.rate > 0 ? info.rate : SAMPLE_RATE;

            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            byte[] buffer = new byte[8192];
            int[] bitstream = new int[1];
            int holes = 0;
            while (true) {
                int n = JOrbisPcm.read(file, buffer, buffer.length, bitstream);
                if (n == 0) break;
                if (n < 0) {
                    if (++holes > 8) break;
                    continue;
                }
                holes = 0;
                bytes.write(buffer, 0, n);
                if (bytes.size() > 256 * 1024 * 1024) break;
            }
            return pcmFromBytes(bytes.toByteArray(), channels, rate);
        } finally {
            try { file.close(); } catch (Exception ignored) {}
        }
    }

    private PcmData decodeSampled(InputStream input) throws Exception {
        try (AudioInputStream source = AudioSystem.getAudioInputStream(input)) {
            AudioFormat base = source.getFormat();
            int srcChannels = Math.max(1, base.getChannels());
            float srcRate = base.getSampleRate();
            if (srcRate <= 0) srcRate = SAMPLE_RATE;

            AudioFormat target = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                    srcRate, 16, srcChannels, srcChannels * 2, srcRate, false);
            AudioInputStream stream;
            try {
                stream = AudioSystem.getAudioInputStream(target, source);
            } catch (IllegalArgumentException e) {
                stream = source;
            }

            int channels = Math.max(1, stream.getFormat().getChannels());
            float rate = stream.getFormat().getSampleRate();
            if (rate <= 0) rate = SAMPLE_RATE;

            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            byte[] buffer = new byte[8192];
            int zeroReads = 0;
            int n;
            while ((n = stream.read(buffer)) >= 0) {
                if (n == 0) {
                    if (++zeroReads > 16) break;
                    continue;
                }
                zeroReads = 0;
                bytes.write(buffer, 0, n);
                if (bytes.size() > 256 * 1024 * 1024) break;
            }
            stream.close();
            return pcmFromBytes(bytes.toByteArray(), channels, rate);
        }
    }

    private PcmData pcmFromBytes(byte[] rawBytes, int channels, float rate) {
        int frameSize = channels * 2;
        int srcFrames = rawBytes.length / frameSize;
        if (srcFrames <= 0) return new PcmData(new short[0]);

        short[] src = new short[srcFrames * channels];
        for (int i = 0; i < src.length; i++) {
            int lo = rawBytes[i * 2] & 0xFF;
            int hi = rawBytes[i * 2 + 1];
            src[i] = (short) ((hi << 8) | lo);
        }

        double step = rate / (double) SAMPLE_RATE;
        int outFrames = Math.max(1, (int) Math.ceil(srcFrames / step));
        short[] out = new short[outFrames * 2];
        for (int i = 0; i < outFrames; i++) {
            double position = i * step;
            int i0 = (int) position;
            if (i0 >= srcFrames) i0 = srcFrames - 1;
            int i1 = Math.min(i0 + 1, srcFrames - 1);
            double fraction = position - i0;
            for (int channel = 0; channel < 2; channel++) {
                int sourceChannel = channels == 1 ? 0 : Math.min(channel, channels - 1);
                short s0 = src[i0 * channels + sourceChannel];
                short s1 = src[i1 * channels + sourceChannel];
                out[i * 2 + channel] = (short) Math.round(s0 + (s1 - s0) * fraction);
            }
        }
        return new PcmData(out);
    }

    public int play(PcmData data, float left, float right, int loop, float rate) {
        if (data == null || data.frames == 0) return 0;
        synchronized (lock) {
            int id = nextId++;
            voices.add(new Voice(id, data, clampVolume(left), clampVolume(right), loop, rate));
            return id;
        }
    }

    public boolean exists(int id) {
        synchronized (lock) {
            for (Voice voice : voices) if (voice.id == id) return true;
            return false;
        }
    }

    public boolean isPlaying(int id) {
        synchronized (lock) {
            for (Voice voice : voices) if (voice.id == id) return !voice.paused;
            return false;
        }
    }

    public void stop(int id) {
        synchronized (lock) {
            voices.removeIf(voice -> voice.id == id);
        }
    }

    public void pause(int id) {
        synchronized (lock) {
            for (Voice voice : voices) if (voice.id == id) voice.paused = true;
        }
    }

    public void resume(int id) {
        synchronized (lock) {
            for (Voice voice : voices) if (voice.id == id) voice.paused = false;
        }
    }

    public void setVolume(int id, float left, float right) {
        synchronized (lock) {
            for (Voice voice : voices) {
                if (voice.id == id) {
                    voice.left = clampVolume(left);
                    voice.right = clampVolume(right);
                }
            }
        }
    }

    public void setRate(int id, float rate) {
        synchronized (lock) {
            for (Voice voice : voices) {
                if (voice.id == id) voice.rate = Math.max(0.1f, Math.min(4.0f, rate));
            }
        }
    }

    public void setLoop(int id, int loop) {
        synchronized (lock) {
            for (Voice voice : voices) if (voice.id == id) voice.loop = loop;
        }
    }

    public void seek(int id, int frame) {
        synchronized (lock) {
            for (Voice voice : voices) {
                if (voice.id == id) voice.position = Math.max(0, Math.min(voice.data.frames - 1, frame));
            }
        }
    }

    public int getPosition(int id) {
        synchronized (lock) {
            for (Voice voice : voices) if (voice.id == id) return (int) voice.position;
            return 0;
        }
    }

    public void setOnEnd(int id, Runnable callback) {
        synchronized (lock) {
            for (Voice voice : voices) if (voice.id == id) voice.onEnd = callback;
        }
    }

    public void stopAll() {
        synchronized (lock) { voices.clear(); }
    }

    public void pauseAll() {
        synchronized (lock) { for (Voice voice : voices) voice.paused = true; }
    }

    public void resumeAll() {
        synchronized (lock) { for (Voice voice : voices) voice.paused = false; }
    }

    private static float clampVolume(float value) {
        return Math.max(0f, Math.min(2f, value));
    }

    private void mixLoop() {
        final int frames = 512;
        float[] mix = new float[frames * 2];
        byte[] out = new byte[frames * 4];

        while (running) {
            Arrays.fill(mix, 0f);
            List<Runnable> ended = new ArrayList<>();
            synchronized (lock) {
                Iterator<Voice> iterator = voices.iterator();
                while (iterator.hasNext()) {
                    Voice voice = iterator.next();
                    if (voice.paused) continue;
                    PcmData data = voice.data;
                    if (data.frames == 0) {
                        iterator.remove();
                        if (voice.onEnd != null) ended.add(voice.onEnd);
                        continue;
                    }
                    for (int i = 0; i < frames; i++) {
                        if (voice.position >= data.frames) {
                            if (voice.loop != 0) {
                                voice.position = 0;
                                if (voice.loop > 0) voice.loop--;
                            } else {
                                iterator.remove();
                                if (voice.onEnd != null) ended.add(voice.onEnd);
                                break;
                            }
                        }
                        int index = (int) voice.position;
                        int next = Math.min(index + 1, data.frames - 1);
                        double fraction = voice.position - index;
                        float left = (float) (data.samples[index * 2]
                                + (data.samples[next * 2] - data.samples[index * 2]) * fraction);
                        float right = (float) (data.samples[index * 2 + 1]
                                + (data.samples[next * 2 + 1] - data.samples[index * 2 + 1]) * fraction);
                        mix[i * 2] += left * voice.left;
                        mix[i * 2 + 1] += right * voice.right;
                        voice.position += voice.rate;
                    }
                }
            }

            for (int i = 0; i < mix.length; i++) {
                int sample = Math.round(mix[i]);
                if (sample > 32767) sample = 32767;
                if (sample < -32768) sample = -32768;
                out[i * 2] = (byte) (sample & 0xFF);
                out[i * 2 + 1] = (byte) ((sample >> 8) & 0xFF);
            }

            if (line != null) {
                try {
                    line.write(out, 0, out.length);
                } catch (Exception ignored) {
                    // Keep the mixer alive; the next write may succeed.
                }
            } else {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    return;
                }
            }

            for (Runnable callback : ended) {
                try { callback.run(); } catch (Exception ignored) {}
            }
        }
    }
}
