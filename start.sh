#!/usr/bin/env bash
set -euo pipefail

echo "running podman containers"
# podman run -d --name fq-back -p 3000:3000 fretquiz-backend
# podman run -d --name fq-front -p 8080:80 fretquiz-frontend
podman run -d --network fq-net --name fq-back -p 3000:3000 fretquiz-backend
podman run -d --network fq-net --name fq-front -p 8080:80 fretquiz-frontend
