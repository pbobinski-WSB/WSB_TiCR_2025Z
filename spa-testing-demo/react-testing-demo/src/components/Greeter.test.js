import { render, screen } from '@testing-library/react';
import { Greeter } from './Greeter';

test('powinien witać gościa, gdy nie ma imienia', () => {
  render(<Greeter />);
  expect(screen.getByText('Witaj, Gościu!')).toBeInTheDocument();
});

test('powinien witać po imieniu, gdy jest podane', () => {
  render(<Greeter name="Anna" />);
  expect(screen.getByText('Witaj, Anna!')).toBeInTheDocument();
});