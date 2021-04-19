#!/usr/bin/env bash
set -euo pipefail

echo "stopping podman containers"
podman stop fretquiz-frontend
podman stop fretquiz-backend
