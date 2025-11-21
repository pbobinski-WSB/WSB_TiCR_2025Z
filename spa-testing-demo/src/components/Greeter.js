import React from 'react';
export function Greeter({ name }) {
  return <h1>Witaj, {name || 'Gościu'}!</h1>;
}