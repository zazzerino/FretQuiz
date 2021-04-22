FROM docker.io/library/nginx:1.19.10

# COPY build /usr/share/nginx/html/fretquiz

COPY ./build /var/www

COPY nginx.conf /etc/nginx/nginx.conf

EXPOSE 80 443

ENTRYPOINT ["nginx", "-g", "daemon off;"]
