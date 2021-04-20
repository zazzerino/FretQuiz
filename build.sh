#!/usr/bin/env bash
set -euo pipefail

cd backend/fretquiz

echo "creating uberjar"
mvn clean package

echo "creating backend podman image"
podman build -t fretquiz-backend .

cd ../../frontend/fretquiz

echo "installing npm packages"
npm install

echo "building frontend package"
npm run build

echo "creating frontend podman image"
podman build -t fretquiz-frontend .
