#!/usr/bin/env bash
set -euo pipefail

echo "running podman containers"
podman run --name fretquiz-back -p 8080:8080 -d fretquiz-back
podman run --name fretquiz-front -p 3000:80 -d fretquiz-front
