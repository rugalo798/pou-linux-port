package android.media;

import android.content.Context;
import android.net.Uri;
import desktop.audio.AudioEngine;

import java.io.File;
import java.io.FileInputStream;

/**
 * Minimal MediaPlayer backed by the desktop software mixer.
 *
 * The game only needs looping background music, volume control, and the
 * usual start/pause/stop lifecycle, but the remaining common methods are
 * present so third-party code paths do not fail with NoSuchMethodError.
 */
public class MediaPlayer {
    public static final int MEDIA_INFO_UNKNOWN = 1;
    public static final int MEDIA_INFO_VIDEO_RENDERING_START = 3;
    public static final int MEDIA_INFO_BUFFERING_START = 701;
    public static final int MEDIA_INFO_BUFFERING_END = 702;
    public static final int MEDIA_ERROR_UNKNOWN = 1;
    public static final int MEDIA_ERROR_SERVER_DIED = 100;
    public static final int MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK = 200;

    public interface OnCompletionListener {
        void onCompletion(MediaPlayer mp);
    }

    public interface OnErrorListener {
        boolean onError(MediaPlayer mp, int what, int extra);
    }

    public interface OnPreparedListener {
        void onPrepared(MediaPlayer mp);
    }

    public interface OnBufferingUpdateListener {
        void onBufferingUpdate(MediaPlayer mp, int percent);
    }

    public interface OnSeekCompleteListener {
        void onSeekComplete(MediaPlayer mp);
    }

    private AudioEngine.PcmData data;
    private int streamId;
    private boolean looping;
    private boolean playing;
    private float leftVolume = 1f;
    private float rightVolume = 1f;

    private OnCompletionListener onCompletionListener;
    private OnErrorListener onErrorListener;
    private OnPreparedListener onPreparedListener;
    private OnBufferingUpdateListener onBufferingUpdateListener;
    private OnSeekCompleteListener onSeekCompleteListener;

    public MediaPlayer() {}

    public static MediaPlayer create(Context context, int resId) {
        MediaPlayer player = new MediaPlayer();
        try {
            player.data = AudioEngine.get().loadResource(context, resId);
        } catch (Exception e) {
            player.data = null;
        }
        return player;
    }

    public static MediaPlayer create(Context context, Uri uri) {
        MediaPlayer player = new MediaPlayer();
        try {
            player.data = AudioEngine.get().loadFile(uri.getPath());
        } catch (Exception e) {
            player.data = null;
        }
        return player;
    }

    public void setDataSource(Context context, Uri uri) {
        data = AudioEngine.get().loadFile(uri.getPath());
    }

    public void setDataSource(String path) {
        data = AudioEngine.get().loadFile(path);
    }

    public void setDataSource(File file) {
        data = AudioEngine.get().loadFile(file);
    }

    public void setDataSource(java.io.FileDescriptor fd) {
        try {
            data = AudioEngine.get().decode(new FileInputStream(fd));
        } catch (Exception e) {
            data = null;
        }
    }

    public void setLooping(boolean looping) {
        this.looping = looping;
        if (streamId != 0) AudioEngine.get().setLoop(streamId, looping ? -1 : 0);
    }

    public boolean isLooping() {
        return looping;
    }

    public void setVolume(float leftVolume, float rightVolume) {
        this.leftVolume = Math.max(0f, Math.min(2f, leftVolume));
        this.rightVolume = Math.max(0f, Math.min(2f, rightVolume));
        if (streamId != 0) AudioEngine.get().setVolume(streamId, this.leftVolume, this.rightVolume);
    }

    public void start() {
        if (data == null || data.frames == 0) return;
        AudioEngine engine = AudioEngine.get();
        if (streamId != 0 && engine.exists(streamId)) {
            engine.resume(streamId);
        } else {
            streamId = engine.play(data, leftVolume, rightVolume, looping ? -1 : 0, 1f);
            if (streamId != 0) {
                engine.setOnEnd(streamId, () -> {
                    playing = false;
                    if (onCompletionListener != null) onCompletionListener.onCompletion(this);
                });
            }
        }
        playing = streamId != 0;
    }

    public void pause() {
        if (streamId != 0) AudioEngine.get().pause(streamId);
        playing = false;
    }

    public void stop() {
        if (streamId != 0) {
            AudioEngine.get().stop(streamId);
            streamId = 0;
        }
        playing = false;
    }

    public void release() {
        stop();
        data = null;
        onCompletionListener = null;
        onErrorListener = null;
        onPreparedListener = null;
    }

    public void reset() {
        stop();
        data = null;
    }

    public boolean isPlaying() {
        return playing && streamId != 0 && AudioEngine.get().isPlaying(streamId);
    }

    public int getDuration() {
        return data == null ? 0 : (int) ((long) data.frames * 1000L / AudioEngine.SAMPLE_RATE);
    }

    public int getCurrentPosition() {
        if (streamId == 0) return 0;
        return (int) ((long) AudioEngine.get().getPosition(streamId) * 1000L / AudioEngine.SAMPLE_RATE);
    }

    public void seekTo(int msec) {
        if (streamId == 0) return;
        int frame = (int) ((long) msec * AudioEngine.SAMPLE_RATE / 1000L);
        AudioEngine.get().seek(streamId, frame);
        if (onSeekCompleteListener != null) onSeekCompleteListener.onSeekComplete(this);
    }

    public void prepare() {
        if (onPreparedListener != null) onPreparedListener.onPrepared(this);
    }

    public void prepareAsync() {
        if (onPreparedListener != null) onPreparedListener.onPrepared(this);
    }

    public void setOnCompletionListener(OnCompletionListener listener) {
        onCompletionListener = listener;
    }

    public void setOnErrorListener(OnErrorListener listener) {
        onErrorListener = listener;
    }

    public void setOnPreparedListener(OnPreparedListener listener) {
        onPreparedListener = listener;
    }

    public void setOnBufferingUpdateListener(OnBufferingUpdateListener listener) {
        onBufferingUpdateListener = listener;
    }

    public void setOnSeekCompleteListener(OnSeekCompleteListener listener) {
        onSeekCompleteListener = listener;
    }

    public void setAudioStreamType(int streamType) {}
    public void setAudioAttributes(AudioAttributes attributes) {}
    public void setWakeMode(Context context, int mode) {}
    public void setScreenOnWhilePlaying(boolean screenOn) {}
    public void setLooping(boolean looping, int unused) { setLooping(looping); }
    public int getAudioSessionId() { return 1; }
    public int getVideoWidth() { return 0; }
    public int getVideoHeight() { return 0; }
    public TrackInfo[] getTrackInfo() { return null; }

    public static class TrackInfo {
        public int getTrackType() { return 0; }
    }
}
