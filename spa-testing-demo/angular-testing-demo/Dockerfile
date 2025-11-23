# /moje-projekty-spa/angular.Dockerfile
FROM node:18-alpine

WORKDIR /app

# Angular CLI musi być zainstalowany globalnie w obrazie
RUN npm install -g @angular/cli

COPY . .

RUN npm install

# Uruchom serwer deweloperski Angulara, nasłuchując na wszystkich interfejsach
CMD ["ng", "serve", "--host", "0.0.0.0"]