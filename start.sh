#!/usr/bin/env bash
set -euo pipefail

echo "running podman containers"
podman run -d --name fretquiz-backend -p 8080:8080 fretquiz-backend
podman run -d --name fretquiz-frontend -p 3000:80 fretquiz-frontend
