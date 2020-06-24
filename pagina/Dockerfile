#------STAGE 1
FROM node:12.18.0-alpine AS builder1
WORKDIR /usr/src/app
COPY package.json ./
RUN npm install
COPY . .
RUN npm run build
#----- STAGE 2
FROM nginx:1.17.1-alpine
COPY --from=builder1 /usr/src/app/dist/servershop /usr/share/nginx/html
COPY nginx-custom.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
