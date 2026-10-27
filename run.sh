#!/usr/bin/env bash
set -euo pipefail
cd "$(dirname "$0")"

ANDROID_JAR="${ANDROID_JAR:-$HOME/Android/Sdk/platforms/android-37.0/android.jar}"
if [[ ! -d shim/out || ! -d patched ]]; then
  echo "Build output missing. Run ./build.sh first." >&2
  exit 1
fi
if [[ ! -f "$ANDROID_JAR" ]]; then
  echo "android.jar not found: $ANDROID_JAR" >&2
  exit 1
fi
for required in game/pou-dex.jar res/strings.properties res/names.properties res/raw.properties; do
  if [[ ! -f "$required" ]]; then
    echo "missing game data: $required" >&2
    echo "This repository does not include the game data; see README.md." >&2
    exit 1
  fi
done
if [[ ! -d assets ]]; then
  echo "missing assets/ directory; see README.md." >&2
  exit 1
fi

CP="patched:shim/out:game/pou-dex.jar:lib/json.jar:lib/httpclient.jar:lib/httpcore.jar:lib/commons-logging.jar:lib/commons-codec.jar:lib/audio/*:$ANDROID_JAR"

exec java -noverify \
  -Dpou.home="$PWD" \
  -Dpou.width="${POU_WIDTH:-450}" \
  -Dpou.height="${POU_HEIGHT:-1000}" \
  "$@" \
  -cp "$CP" desktop.Main
