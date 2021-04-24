#!/usr/bin/env bash
set -euo pipefail

cd backend/fretquiz

echo "creating uberjar"
mvn clean package

cd ../../frontend/fretquiz

echo "installing npm packages"
npm install

echo "building frontend package"
npm run build

cd ../..

sudo cp -r frontend/fretquiz/build /var/www/fretquiz
sudo cp fretquiz.conf /etc/nginx/conf.d
