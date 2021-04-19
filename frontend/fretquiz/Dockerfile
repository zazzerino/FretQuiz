FROM docker.io/library/node:14.16.1

WORKDIR /app/fretquiz

COPY package.json package-lock.json tsconfig.json /app/fretquiz/

RUN npm install

COPY src /app/fretquiz/src

COPY public /app/fretquiz/public

RUN npm run build

FROM docker.io/library/nginx:1.19.10

COPY --from=0 /app/fretquiz/build /usr/share/nginx/html/fretquiz

EXPOSE 80 443
