package android.media;

/** Audio manager stub. */
public class AudioManager {
    public static final int STREAM_VOICE_CALL = 0;
    public static final int STREAM_SYSTEM = 1;
    public static final int STREAM_RING = 2;
    public static final int STREAM_MUSIC = 3;
    public static final int STREAM_ALARM = 4;
    public static final int STREAM_NOTIFICATION = 5;
    public static final int STREAM_BLUETOOTH_SCO = 6;
    public static final int STREAM_SYSTEM_ENFORCED = 7;
    public static final int STREAM_DTMF = 8;
    public static final int STREAM_TTS = 9;
    public static final int STREAM_ACCESSIBILITY = 10;
    public static final int STREAM_ASSISTANT = 11;
    public static final int MODE_NORMAL = 0;
    public static final int MODE_RINGTONE = 1;
    public static final int MODE_IN_CALL = 2;
    public static final int MODE_IN_COMMUNICATION = 3;
    public static final int RINGER_MODE_SILENT = 0;
    public static final int RINGER_MODE_VIBRATE = 1;
    public static final int RINGER_MODE_NORMAL = 2;
    public static final int AUDIOFOCUS_NONE = 0;
    public static final int AUDIOFOCUS_GAIN = 1;
    public static final int AUDIOFOCUS_GAIN_TRANSIENT = 2;
    public static final int AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK = 3;
    public static final int AUDIOFOCUS_GAIN_TRANSIENT_EXCLUSIVE = 4;
    public static final int AUDIOFOCUS_LOSS = -1;
    public static final int AUDIOFOCUS_LOSS_TRANSIENT = -2;
    public static final int AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK = -3;
    public static final int AUDIOFOCUS_REQUEST_GRANTED = 1;
    public static final int AUDIOFOCUS_REQUEST_FAILED = 0;
    public static final int AUDIOFOCUS_REQUEST_DELAYED = 2;
    public static final int USE_DEFAULT_STREAM_TYPE = Integer.MIN_VALUE;
    public static final String ACTION_AUDIO_BECOMING_NOISY = "android.media.AUDIO_BECOMING_NOISY";
    public static final String ACTION_HEADSET_PLUG = "android.intent.action.HEADSET_PLUG";
    public static final String ACTION_SCO_AUDIO_STATE_UPDATED = "android.media.SCO_AUDIO_STATE_UPDATED";
    public static final String ACTION_MICROPHONE_MUTE_CHANGED = "android.media.action.MICROPHONE_MUTE_CHANGED";
    public static final String EXTRA_STATE = "state";
    public static final int SCO_AUDIO_STATE_DISCONNECTED = 0;
    public static final int SCO_AUDIO_STATE_CONNECTING = 1;
    public static final int SCO_AUDIO_STATE_CONNECTED = 2;
    public static final int SCO_AUDIO_STATE_ERROR = -1;

    public int getStreamVolume(int streamType) { return 0; }
    public int getStreamMaxVolume(int streamType) { return 15; }
    public int getStreamMinVolume(int streamType) { return 0; }
    public void setStreamVolume(int streamType, int index, int flags) {}
    public void adjustStreamVolume(int streamType, int direction, int flags) {}
    public void adjustVolume(int direction, int flags) {}
    public void setVolumeControlStream(int streamType) {}
    public int getVolumeControlStream() { return 0; }
    public boolean isStreamMute(int streamType) { return false; }
    public void setStreamMute(int streamType, boolean state) {}
    public boolean isMusicActive() { return false; }
    public boolean isMusicActive(boolean remote) { return false; }
    public int requestAudioFocus(OnAudioFocusChangeListener l, int streamType, int durationHint) { return AUDIOFOCUS_REQUEST_GRANTED; }
    public int requestAudioFocus(AudioFocusRequest request) { return AUDIOFOCUS_REQUEST_GRANTED; }
    public int abandonAudioFocus(OnAudioFocusChangeListener l) { return AUDIOFOCUS_REQUEST_GRANTED; }
    public int abandonAudioFocusRequest(AudioFocusRequest request) { return AUDIOFOCUS_REQUEST_GRANTED; }
    public int getMode() { return MODE_NORMAL; }
    public void setMode(int mode) {}
    public int getRingerMode() { return RINGER_MODE_NORMAL; }
    public void setRingerMode(int ringerMode) {}
    public boolean isSpeakerphoneOn() { return false; }
    public void setSpeakerphoneOn(boolean on) {}
    public boolean isBluetoothScoOn() { return false; }
    public void setBluetoothScoOn(boolean on) {}
    public void startBluetoothSco() {}
    public void stopBluetoothSco() {}
    public void setBluetoothScoOn(boolean on, boolean fromUser) {}
    public AudioDeviceInfo[] getDevices(int flags) { return new AudioDeviceInfo[0]; }
    public String getProperty(String key) { return null; }
    public String getParameters(String keys) { return ""; }
    public void setParameters(String keyValuePairs) {}
    public void playSoundEffect(int effectType) {}
    public void playSoundEffect(int effectType, float volume) {}
    public void loadSoundEffects() {}
    public void unloadSoundEffects() {}
    public boolean isVolumeFixed() { return true; }
    public void registerAudioDeviceCallback(AudioDeviceCallback callback, android.os.Handler handler) {}
    public void unregisterAudioDeviceCallback(AudioDeviceCallback callback) {}
    public void registerAudioRecordingCallback(AudioRecordingCallback callback, android.os.Handler handler) {}
    public void unregisterAudioRecordingCallback(AudioRecordingCallback callback) {}
    public int generateAudioSessionId() { return 1; }
    public int getAudioSessionId() { return 1; }
    public void setAudioSessionId(int sessionId) {}
    public boolean isWiredHeadsetOn() { return false; }
    public boolean isBluetoothA2dpOn() { return false; }
    public void setWiredHeadsetOn(boolean on) {}
    public void setBluetoothA2dpOn(boolean on) {}
    public void setMicrophoneMute(boolean on) {}
    public boolean isMicrophoneMute() { return false; }
    public int getOutputLatency() { return 0; }
    public int getInputLatency() { return 0; }

    public interface OnAudioFocusChangeListener {
        void onAudioFocusChange(int focusChange);
    }

    public static class AudioFocusRequest {
        public static class Builder {
            private final AudioAttributes attributes;
            public Builder(int focusGain) { this.attributes = new AudioAttributes(); }
            public Builder(int focusGain, AudioAttributes attributes) { this.attributes = attributes; }
            public Builder setAudioAttributes(AudioAttributes attributes) { return this; }
            public Builder setWillPauseWhenDucked(boolean pauseWhenDucked) { return this; }
            public Builder setOnAudioFocusChangeListener(OnAudioFocusChangeListener listener) { return this; }
            public Builder setOnAudioFocusChangeListener(OnAudioFocusChangeListener listener, android.os.Handler handler) { return this; }
            public Builder setAcceptsDelayedFocusGain(boolean acceptsDelayedFocusGain) { return this; }
            public AudioFocusRequest build() { return new AudioFocusRequest(); }
        }
    }

    public static class AudioDeviceCallback {
        public void onAudioDevicesAdded(AudioDeviceInfo[] addedDevices) {}
        public void onAudioDevicesRemoved(AudioDeviceInfo[] removedDevices) {}
    }

    public static class AudioRecordingCallback {
        public void onRecordingConfigChanged(java.util.List<AudioRecordingConfiguration> configs) {}
    }

    public static class AudioDeviceInfo {
        public int getId() { return 0; }
        public int getType() { return 0; }
        public String getProductName() { return ""; }
        public boolean isSource() { return false; }
        public boolean isSink() { return false; }
        public int[] getSampleRates() { return new int[0]; }
        public int[] getChannelCounts() { return new int[0]; }
        public int[] getChannelMasks() { return new int[0]; }
        public int[] getChannelIndexMasks() { return new int[0]; }
        public int[] getEncodings() { return new int[0]; }
    }

    public static class AudioRecordingConfiguration {
        public int getClientAudioSessionId() { return 0; }
        public int getClientAudioSource() { return 0; }
        public AudioDeviceInfo getAudioDevice() { return null; }
    }
}
