# Pou 1.4.139 — Linux desktop port

A desktop port of the Android game **Pou 1.4.139**, built by running the
original DEX bytecode on the JVM with a small Android compatibility layer
(Java2D/Swing). The current milestone is the initial **“When were you born?”**
age picker and the room behind it.

> **This repository contains code only.** It does not include the original
> Pou bytecode, APK, images, fonts, sounds, or other game assets. To run the
> port, provide your own legally obtained copy of the game and follow
> [Game data](#game-data-not-included). Pou and its assets belong to their
> respective owner; this project is not affiliated with or endorsed by the
> game's authors.

## Status

Implemented and matching the Android reference:

- Initial age/year-of-birth screen, including the scrollable year list,
  stroked text, Privacy Policy button, and bottom room controls.
- The animated room background and Pou ball behind the overlay.
- Mouse input for selecting a year.
- Sound effects and looping background music through a small software mixer
  backed by Java Sound.

Only this first screen is in scope so far. Selecting a year continues into the
original game, but Android services, ads, billing, and most later screens are
still stubbed and are not expected to be fully functional.

## Requirements

- Linux with a graphical desktop (X11 or XWayland).
- JDK 17 or newer.
- A working PulseAudio/PipeWire (or ALSA) playback device.
- An Android platform `android.jar`. The default is
  `$HOME/Android/Sdk/platforms/android-37.0/android.jar`; override it with
  `ANDROID_JAR=/path/to/android.jar`.
- For preparing game data: `unzip`, Android SDK build-tools (`aapt2`), and
  [dex2jar](https://github.com/pxb1988/dex2jar).

## Game data (not included)

The port expects these local files, all of which are ignored by Git:

```text
game/pou-dex.jar       original classes.dex converted with dex2jar
assets/                extracted APK assets (fonts, images, raw audio, …)
res/strings.properties generated Android string table
res/names.properties   generated Android resource-name table
res/raw.properties     generated raw-resource-to-asset table
```

Using a legally obtained Pou 1.4.139 APK, the setup is:

```bash
# 1. Extract the assets.
unzip -q Pou_1.4.139.apk 'assets/*' -d .

# 2. Convert the original classes.dex to a JVM jar.
unzip -p Pou_1.4.139.apk classes.dex > /tmp/pou-classes.dex
mkdir -p game
d2j-dex2jar.sh -f -o game/pou-dex.jar /tmp/pou-classes.dex

# 3. Dump the resource table and generate the shim's lookup files.
AAPT2="${AAPT2:-$HOME/Android/Sdk/build-tools/36.0.0/aapt2}"
"$AAPT2" dump resources Pou_1.4.139.apk > res/resources_dump.txt
python3 res/gen_resources.py res/resources_dump.txt res

# 4. Copy the APK's raw resources to assets/raw.
python3 tools/extract_raw.py Pou_1.4.139.apk res/resources_dump.txt
```

`tools/extract_raw.py` reads the `aapt2` dump and extracts every `raw/*`
resource into `assets/raw/`, using the same names that
`res/gen_resources.py` records in `raw.properties`.

## Build and run

```bash
./build.sh
./run.sh
```

`build.sh` compiles the shim, patches the original game classes with javassist,
and writes the patched classes to `patched/`. `run.sh` launches the app.

The window is 450×1000 by default (the same 9:20 aspect as the original
1080×2400 capture). Override the size with `POU_WIDTH` / `POU_HEIGHT`:

```bash
POU_WIDTH=540 POU_HEIGHT=1200 ./run.sh
```

## Options

| Property | Purpose |
| --- | --- |
| `-Dpou.noinput=true` | Disable Swing mouse input (useful for screenshots). |
| `-Dpou.screenshot=/path/out.png` | Save the app’s own render buffer to a PNG. |
| `-Dpou.screenshot.frame=1500` | Frame at which the screenshot is written (default 1500). |
| `-Dpou.apk=/path/to/Pou.apk` | Path reported by the stubbed `PackageManager`; defaults to `Pou_1.4.139.apk` in the project root. |

Example capture:

```bash
./run.sh -Dpou.noinput=true -Dpou.screenshot=/tmp/pou-age.png
```

`-Dpou.home` defaults to the current working directory. Use it if you launch
`desktop.Main` from somewhere else:

```bash
java ... -Dpou.home=/path/to/pou-port desktop.Main
```

To reset the first-run state and show the age picker again:

```bash
rm -f save/prefs-default.properties
```

## How it works

- `game/pou-dex.jar` is the original `classes.dex`, converted with dex2jar.
  Game classes keep their original default-package names (`m42`, `rb3`, …).
- `shim/src/android/**` implements the Android APIs used by the game:
  Activity/View/SurfaceView, Canvas/Paint/Path, text layout, resources,
  SharedPreferences, and enough service stubs to boot.
- `shim/src/desktop/audio/AudioEngine.java` is a small software mixer. It
  decodes all `SoundPool` effects and `MediaPlayer` music to 44.1 kHz stereo
  PCM and writes them to one Java Sound `SourceDataLine`, so overlapping
  effects, volume changes, rate changes, and looping work like Android.
- OGG and MP3 decoding is provided by the bundled JOrbis/JLayer/VorbisSPI/
  MP3SPI jars in `lib/audio/`; the game’s raw audio files live in
  `assets/raw/` and are mapped by `res/raw.properties`.
- `assets/` and the generated `res/*.properties` tables are local game data and
  are not committed. The committed Python scripts rebuild the tables from an
  APK.
- `tools/PatchGame.java` applies the small runtime patches:
  - no-ops ad/analytics/billing initialization in `me.pou.app.App`;
  - makes `App.V1(true)` reserve the same 335-unit bottom banner inset as the
    Android build (the age overlay avoids that area even though ads are off);
  - prevents the daily-reward dialog from replacing the age picker while it is
    open;
  - initializes sound-effect and music volumes in the audio helper
    constructors, because desktop service stubs can make `App.onResume()`
    return before its normal volume setup runs.
- `-noverify` is required because the dex2jar output uses older bytecode
  patterns that the modern JVM verifier rejects.

## Layout

```text
assets/          local only: extracted APK assets (fonts, images, raw audio)
game/            local only: original classes converted to pou-dex.jar
lib/             runtime jars needed by the original code
lib/audio/       JOrbis/JLayer/VorbisSPI/MP3SPI decoders
patched/         generated patched game classes
res/             resource tables (generated files are ignored)
save/            local desktop preferences
shim/            Android compatibility layer + desktop entry point
tools/           javassist patcher and raw-resource extractor
build.sh         build everything
run.sh           launch the app
```
