#!/usr/bin/env python3
"""Extract raw resources from an APK using `aapt2 dump resources` output.

Usage:
    python3 tools/extract_raw.py Pou.apk res/resources_dump.txt

The generated files are written to assets/raw/<resource-name><extension>,
which is the path format used by res/gen_resources.py in raw.properties.
"""
import os
import re
import sys
import zipfile

APK = sys.argv[1] if len(sys.argv) > 1 else "Pou_1.4.139.apk"
DUMP = sys.argv[2] if len(sys.argv) > 2 else "res/resources_dump.txt"
OUT = "assets/raw"

res_re = re.compile(r"^\s+resource (0x[0-9a-fA-F]+) ([a-zA-Z0-9_]+)/(\S+)\s*$")
file_re = re.compile(r"^\s+\(\) \(file\) (\S+)\s*$")

entries = []
current = None
with open(DUMP, "r", encoding="utf-8", errors="replace") as f:
    for line in f:
        m = res_re.match(line)
        if m:
            current = (m.group(1), m.group(2), m.group(3))
            continue
        if current and line.strip().startswith("()"):
            fm = file_re.match(line)
            if fm and current[1] == "raw":
                entries.append((current[0], current[2], fm.group(1)))
            current = None

if not entries:
    raise SystemExit(f"no raw resources found in {DUMP}")

os.makedirs(OUT, exist_ok=True)
with zipfile.ZipFile(APK) as apk:
    for resource_id, name, source in entries:
        ext = os.path.splitext(source)[1]
        destination = os.path.join(OUT, name + ext)
        with apk.open(source) as src, open(destination, "wb") as dst:
            dst.write(src.read())
        print(f"{resource_id} {source} -> {destination}")

print(f"extracted {len(entries)} raw resources to {OUT}")
