# FROM node:14.16.1

# WORKDIR /usr/share/app

# COPY package.json package-lock.json tsconfig.json /usr/share/app/

# RUN npm install

# COPY ./src /usr/share/app/src
# COPY ./public /usr/share/app/public

# RUN npm run build

# FROM nginx
# COPY --from=build /usr/share/app/build /usr/share/nginx/html/fretquiz

# EXPOSE 80 443
