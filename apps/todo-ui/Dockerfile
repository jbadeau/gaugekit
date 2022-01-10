FROM nginx:latest

USER root
RUN chmod -R 777 /etc/nginx && chmod -R 777 /var/log/nginx && chmod -R 777 /var/cache/nginx && chmod -R 777 /var/run

USER nginx

COPY ./build /var/www
COPY nginx.conf /etc/nginx/nginx.conf

EXPOSE 8080

CMD ["nginx-debug", "-g", "daemon off;"]