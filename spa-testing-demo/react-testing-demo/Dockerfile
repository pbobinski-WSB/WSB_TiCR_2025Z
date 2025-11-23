# /moje-projekty-spa/react.Dockerfile
FROM node:20-alpine

# Ustaw katalog roboczy wewnątrz kontenera
WORKDIR /app

# Kopiuj resztę kodu źródłowego
# Używamy wolumenów w docker-compose, więc ten krok jest głównie dla budowania obrazu
COPY . .

# Zainstaluj wszystkie zależności
RUN npm install



# Uruchom serwer deweloperski
CMD ["npm", "start"]