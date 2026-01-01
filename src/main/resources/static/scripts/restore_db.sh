#!/bin/bash
set -euo pipefail

if [ $# -ne 1 ]; then
  echo "Usage: $0 <sql_file>" >&2
  exit 1
fi

DB_NAME="db_backend_bazaar"
SQL_FILE="$1"

if [ ! -f "$SQL_FILE" ]; then
  echo "ERROR: SQL file not found: $SQL_FILE" >&2
  exit 1
fi

mysql "$DB_NAME" < "$SQL_FILE"

echo "Restored: $SQL_FILE"
