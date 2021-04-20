#!/usr/bin/env bash
set -euo pipefail

echo "stopping podman containers"
podman stop fq-front
podman stop fq-back
