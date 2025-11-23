const { test, expect } = require('@playwright/test');

test.describe('SPA Testing Demo E2E', () => {
  test.beforeEach(async ({ page }) => {
    // Uruchom aplikację w osobnym terminalu: npm start
    await page.goto('http://localhost:3002');
  });

  test('powinien poprawnie wyświetlać komponenty po załadowaniu', async ({ page }) => {
    // Test Greeter
    await expect(page.locator('h1')).toHaveText('Witaj, Studencie!');
    // Test Counter
    await expect(page.getByText('Wartość licznika: 0')).toBeVisible();
  });

  test('licznik powinien zwiększać wartość po kliknięciu', async ({ page }) => {
    await page.getByRole('button', { name: 'Zwiększ' }).click();
    await expect(page.getByText('Wartość licznika: 1')).toBeVisible();
  });

  test('powinien pobrać i wyświetlić wiadomość z API', async ({ page }) => {
    // Ten test zadziała tylko dla scenariusza sukcesu
    await page.getByRole('button', { name: 'Pobierz wiadomość' }).click();
    
    const messageDisplay = page.getByTestId('message-display');
    // Czekamy na prawdziwą odpowiedź z API
    await expect(messageDisplay).toHaveText('delectus aut autem');
  });
});