#!/usr/bin/env python3
"""Generate a minimal android.R class from references found in decompiled sources."""
import os
import re
import sys

src_root = sys.argv[1]
out_file = sys.argv[2]

refs = {}
pat = re.compile(r"android\.R\.([a-zA-Z0-9_]+)\.([A-Za-z0-9_]+)")
for root, _, files in os.walk(src_root):
    for fn in files:
        if not fn.endswith(".java"):
            continue
        path = os.path.join(root, fn)
        try:
            with open(path, "r", encoding="utf-8", errors="replace") as f:
                text = f.read()
        except OSError:
            continue
        for m in pat.finditer(text):
            refs.setdefault(m.group(1), set()).add(m.group(2))

lines = ["package android;", "", "public final class R {"]
value = 0x01000000
for rtype in sorted(refs):
    lines.append(f"    public static final class {rtype} {{")
    for name in sorted(refs[rtype]):
        lines.append(f"        public static final int {name} = {value};")
        value += 1
    lines.append("    }")
lines.append("}")
os.makedirs(os.path.dirname(out_file), exist_ok=True)
with open(out_file, "w", encoding="utf-8") as f:
    f.write("\n".join(lines) + "\n")

print(f"types: {len(refs)}  fields: {sum(len(v) for v in refs.values())}")
for t in sorted(refs):
    print(f"  {t}: {len(refs[t])}")
