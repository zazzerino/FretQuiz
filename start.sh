#!/usr/bin/env bash
set -euo pipefail

echo "running podman containers"
podman run -d --name fretquiz-backend -p 3000:3000 fretquiz-backend
podman run -d --name fretquiz-frontend -p 8080:80 fretquiz-frontend
