#!/usr/bin/env bash
set -euo pipefail
cd "$(dirname "$0")"

ANDROID_JAR="${ANDROID_JAR:-$HOME/Android/Sdk/platforms/android-37.0/android.jar}"
if [[ ! -f "$ANDROID_JAR" ]]; then
  echo "android.jar not found: $ANDROID_JAR" >&2
  echo "Set ANDROID_JAR to an Android platform android.jar." >&2
  exit 1
fi
if [[ ! -f game/pou-dex.jar ]]; then
  echo "game/pou-dex.jar not found." >&2
  echo "This repository does not include the game bytecode; see README.md." >&2
  exit 1
fi

mkdir -p shim/out tools/out
rm -rf shim/out/* patched
find shim/src -name '*.java' > shim/sources.txt
javac -cp 'lib/audio/*' -d shim/out @shim/sources.txt
javac -cp lib/javassist.jar -d tools/out tools/PatchGame.java
java -cp tools/out:lib/javassist.jar PatchGame \
  game/pou-dex.jar shim/out "$ANDROID_JAR" patched

echo "Build complete. Run ./run.sh"
