# /moje-projekty-spa/vue.Dockerfile
FROM node:18-alpine

WORKDIR /app

COPY . .

RUN npm install

# Uruchom serwer deweloperski Vite, nasłuchując na wszystkich interfejsach.
# Podwójny myślnik `--` jest potrzebny, aby przekazać flagę `--host` do skryptu `vite`, a nie do `npm`.
CMD ["npm", "run", "dev", "--", "--host"]