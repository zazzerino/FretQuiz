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

echo "copying uberjar to /opt/fretquiz"
sudo cp backend/fretquiz/target/FretQuiz.jar /opt/fretquiz/

echo "copying frontend build to /var/www/fretquiz"
sudo cp -rT frontend/fretquiz/build /var/www/fretquiz

echo "copying nginx conf to /etc/nginx/conf.d"
sudo cp fretquiz.nginx.conf /etc/nginx/conf.d

echo "copying backend service file to /etc/systemd/system"
sudo cp fretquiz-backend.service /etc/systemd/system

echo "starting backend systemd service"
sudo systemctl daemon-reload
sudo systemctl start fretquiz-backend.service

echo "restarting nginx"
sudo systemctl restart nginx
