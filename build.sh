#!/usr/bin/env bash
set -euo pipefail

cd backend/fretquiz

echo "creating uberjar"
mvn clean package

echo "creating backend docker image"
docker build -t fretquiz-back .

cd ../../frontend/fretquiz

echo "creating frontend docker image"
docker build -t fretquiz-front .

# docker run --rm -p 8080:8080 fretquiz-back
# docker run --rm -p 3000:80 fretquiz-front
