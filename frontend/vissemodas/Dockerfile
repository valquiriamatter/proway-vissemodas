# stage 1

FROM node:14 as angular1
WORKDIR /app
COPY package.json /app
RUN npm install --silent
COPY . .
RUN npm run build

# stage 2

FROM nginx:alpine
COPY --from=angular1 /app/dist/vissemodas /usr/share/nginx/html
COPY nginx.conf /etc/ngix/conf.d/default.conf 