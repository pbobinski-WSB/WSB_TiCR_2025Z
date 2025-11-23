import { render, screen, fireEvent } from '@testing-library/react';
import { AsyncMessageFetcher } from './AsyncMessageFetcher';

// Mockujemy globalny fetch
global.fetch = jest.fn();

beforeEach(() => fetch.mockClear());

test('powinien wyświetlić wiadomość po udanym pobraniu', async () => {
  fetch.mockResolvedValueOnce({
    json: () => Promise.resolve({ title: 'Testowa wiadomość' }),
  });
  render(<AsyncMessageFetcher />);
  fireEvent.click(screen.getByText('Pobierz wiadomość'));
  
  const messageDisplay = await screen.findByTestId('message-display');
  expect(messageDisplay).toHaveTextContent('Testowa wiadomość');
});

test('powinien wyświetlić błąd, gdy pobieranie zawiedzie', async () => {
  fetch.mockRejectedValueOnce(new Error('Błąd API'));
  render(<AsyncMessageFetcher />);
  fireEvent.click(screen.getByText('Pobierz wiadomość'));
  
  const alert = await screen.findByRole('alert');
  expect(alert).toHaveTextContent('Nie udało się pobrać wiadomości');
});