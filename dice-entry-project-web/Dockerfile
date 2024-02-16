# Stage 1: Build the Angular application
FROM node:14 as build

WORKDIR /app

COPY package*.json ./

RUN npm install

#COPY ../docker/frontend .

RUN npm run build -- --prod

# Stage 2: Serve the Angular application using Nginx
FROM nginx:alpine

COPY --from=build /app/dist/frontend /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
