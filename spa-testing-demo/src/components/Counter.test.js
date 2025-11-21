import { render, screen, fireEvent } from '@testing-library/react';
import { Counter } from './Counter';

test('licznik powinien zwiększać wartość po kliknięciu', () => {
  render(<Counter />);
  const button = screen.getByText('Zwiększ');
  fireEvent.click(button);
  expect(screen.getByText('Wartość licznika: 1')).toBeInTheDocument();
});