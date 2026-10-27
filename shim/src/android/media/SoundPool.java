package android.media;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import desktop.audio.AudioEngine;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/** SoundPool implementation backed by the desktop software mixer. */
public class SoundPool {
    private final Map<Integer, AudioEngine.PcmData> sounds = new HashMap<>();
    private final Set<Integer> streams = new HashSet<>();
    private int nextSoundId = 1;
    private OnLoadCompleteListener loadCompleteListener;

    public SoundPool(int maxStreams, int streamType, int srcQuality) {}

    public int load(Context context, int resId, int priority) {
        return register(AudioEngine.get().loadResource(context, resId));
    }

    public int load(String path, int priority) {
        return register(AudioEngine.get().loadFile(path));
    }

    public int load(FileDescriptor fd, long offset, long length, int priority) {
        try (InputStream in = new FileInputStream(fd)) {
            return register(AudioEngine.get().decode(in));
        } catch (Exception e) {
            return 0;
        }
    }

    public int load(AssetFileDescriptor afd, int priority) {
        try (InputStream in = afd.createInputStream()) {
            return register(AudioEngine.get().decode(in));
        } catch (Exception e) {
            return 0;
        }
    }

    private int register(AudioEngine.PcmData data) {
        if (data == null || data.frames == 0) return 0;
        int id;
        synchronized (sounds) {
            id = nextSoundId++;
            sounds.put(id, data);
        }
        if (loadCompleteListener != null) {
            loadCompleteListener.onLoadComplete(this, id, 0);
        }
        return id;
    }

    public int play(int soundID, float leftVolume, float rightVolume, int priority, int loop, float rate) {
        AudioEngine.PcmData data;
        synchronized (sounds) {
            data = sounds.get(soundID);
        }
        if (data == null) return 0;
        int stream = AudioEngine.get().play(data, leftVolume, rightVolume, loop, rate);
        if (stream != 0) {
            synchronized (streams) { streams.add(stream); }
        }
        return stream;
    }

    public void stop(int streamID) {
        AudioEngine.get().stop(streamID);
        synchronized (streams) { streams.remove(streamID); }
    }

    public void pause(int streamID) {
        AudioEngine.get().pause(streamID);
    }

    public void resume(int streamID) {
        AudioEngine.get().resume(streamID);
    }

    public void autoPause() {
        synchronized (streams) {
            for (int stream : streams) AudioEngine.get().pause(stream);
        }
    }

    public void autoResume() {
        synchronized (streams) {
            for (int stream : streams) AudioEngine.get().resume(stream);
        }
    }

    public void setLoop(int streamID, int loop) {
        AudioEngine.get().setLoop(streamID, loop);
    }

    public void setVolume(int streamID, float leftVolume, float rightVolume) {
        AudioEngine.get().setVolume(streamID, leftVolume, rightVolume);
    }

    public void setPriority(int streamID, int priority) {
        // The desktop mixer does not implement stream priorities.
    }

    public void setRate(int streamID, float rate) {
        AudioEngine.get().setRate(streamID, rate);
    }

    public boolean unload(int soundID) {
        synchronized (sounds) {
            return sounds.remove(soundID) != null;
        }
    }

    public void release() {
        synchronized (streams) {
            for (int stream : streams) AudioEngine.get().stop(stream);
            streams.clear();
        }
        synchronized (sounds) {
            sounds.clear();
        }
    }

    public void setOnLoadCompleteListener(OnLoadCompleteListener listener) {
        loadCompleteListener = listener;
    }

    public interface OnLoadCompleteListener {
        void onLoadComplete(SoundPool soundPool, int sampleId, int status);
    }
}
