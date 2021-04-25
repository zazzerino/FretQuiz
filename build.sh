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

echo "copying build to /var/www/fretquiz"
sudo cp -rT frontend/fretquiz/build /var/www/fretquiz

echo "copying nginx conf to /etc/nginx/conf.d"
sudo cp fretquiz.conf /etc/nginx/conf.d
