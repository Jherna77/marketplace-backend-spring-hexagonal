#!/bin/bash
set -euo pipefail

if [ $# -ne 1 ]; then
  echo "Usage: $0 <backup_file_path>" >&2
  exit 1
fi

IMAGES_PATH="uploads/"
BACKUP_PATH="$1"

tar -czf "$BACKUP_PATH" "$IMAGES_PATH" 2>/dev/null

echo "$BACKUP_PATH"
