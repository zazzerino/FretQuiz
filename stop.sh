#!/usr/bin/env bash
set -euo pipefail

echo "stopping podman containers"
podman stop fretquiz-front
podman stop fretquiz-back
