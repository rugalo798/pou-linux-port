#!/usr/bin/env python3
"""Parse `aapt2 dump resources` output into simple property maps for the shim."""
import os
import re
import sys

src = sys.argv[1] if len(sys.argv) > 1 else "resources_dump.txt"
out = sys.argv[2] if len(sys.argv) > 2 else "."

res_re = re.compile(r"^\s+resource (0x[0-9a-fA-F]+) ([a-zA-Z0-9_]+)/(\S+)\s*$")
val_re = re.compile(r'^\s+\(\) "(.*)"\s*$')
file_re = re.compile(r"^\s+\(\) \(file\) (\S+)\s*$")

def unescape(s: str) -> str:
    out = []
    i = 0
    while i < len(s):
        c = s[i]
        if c == "\\" and i + 1 < len(s):
            n = s[i + 1]
            mapping = {"n": "\n", "t": "\t", "r": "\r", '"': '"', "\\": "\\", "'": "'"}
            if n in mapping:
                out.append(mapping[n])
                i += 2
                continue
            if n == "u" and i + 5 < len(s):
                try:
                    out.append(chr(int(s[i + 2:i + 6], 16)))
                    i += 6
                    continue
                except ValueError:
                    pass
        out.append(c)
        i += 1
    return "".join(out)

def prop_escape(s: str) -> str:
    return (s.replace("\\", "\\\\")
             .replace("\n", "\\n")
             .replace("\r", "\\r")
             .replace("\t", "\\t")
             .replace("=", "\\=")
             .replace(":", "\\:"))

entries = []
cur = None
with open(src, "r", encoding="utf-8", errors="replace") as f:
    for line in f:
        m = res_re.match(line)
        if m:
            cur = (m.group(1), m.group(2), m.group(3))
            continue
        if cur and line.strip().startswith("()"):
            m = val_re.match(line)
            if m:
                entries.append((cur[0], cur[1], cur[2], unescape(m.group(1))))
            else:
                fm = file_re.match(line)
                if fm and cur[1] == "raw":
                    source = fm.group(1)
                    ext = os.path.splitext(source)[1]
                    entries.append((cur[0], cur[1], cur[2], f"raw/{cur[2]}{ext}"))
            cur = None

strings = {}
names = {}
raws = {}
allres = {}
for rid, rtype, name, value in entries:
    allres[(rtype, name)] = rid
    if rtype == "string":
        strings[rid] = value
    if rtype == "raw":
        raws[rid] = value
    names[f"{rtype}/{name}"] = rid

with open(f"{out}/strings.properties", "w", encoding="utf-8") as f:
    for rid, value in sorted(strings.items()):
        f.write(f"{rid}={prop_escape(value)}\n")

with open(f"{out}/names.properties", "w", encoding="utf-8") as f:
    for key, rid in sorted(names.items()):
        f.write(f"{key}={rid}\n")

with open(f"{out}/raw.properties", "w", encoding="utf-8") as f:
    for rid, value in sorted(raws.items()):
        f.write(f"{rid}={prop_escape(value)}\n")

with open(f"{out}/all_resources.properties", "w", encoding="utf-8") as f:
    for (rtype, name), rid in sorted(allres.items()):
        f.write(f"{rtype}/{name}={rid}\n")

print(f"resources parsed: {len(entries)}")
print(f"strings: {len(strings)}  names: {len(names)}  raw: {len(raws)}")
print("year_of_birth:", names.get("string/year_of_birth"))
print("when_born:", names.get("string/when_born"))
