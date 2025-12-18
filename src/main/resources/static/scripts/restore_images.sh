#!/bin/bash
set -euo pipefail

if [ $# -ne 1 ]; then
  echo "Usage: $0 <tar_file>" >&2
  exit 1
fi

TAR_FILE="$1"
DEST_DIR="uploads/"

if [ ! -f "$TAR_FILE" ]; then
  echo "ERROR: Tar file not found: $TAR_FILE" >&2
  exit 1
fi

rm -rf "$DEST_DIR"/*

tar -xzf "$TAR_FILE" -C /

echo "Restored: $TAR_FILE"
