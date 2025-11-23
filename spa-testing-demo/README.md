docker compose up --build

docker-compose exec react-app npm test
docker-compose exec vue-app npm run test:unit
Bez dockera dla angular
ng test --no-watch --browsers=ChromeHeadless

npx playwright test


