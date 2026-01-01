#!/bin/bash
set -euo pipefail

if [ $# -ne 1 ]; then
  echo "Usage: $0 <backup_file_path>" >&2
  exit 1
fi

DB_NAME="db_backend_bazaar"
BACKUP_PATH="$1"

mysqldump "$DB_NAME" > "$BACKUP_PATH" 2>/dev/null

echo "$BACKUP_PATH"
