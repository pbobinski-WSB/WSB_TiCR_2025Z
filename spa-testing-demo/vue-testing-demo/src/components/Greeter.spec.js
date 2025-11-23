// src/components/Greeter.spec.js

import { describe, it, expect } from 'vitest';
import { mount } from '@vue/test-utils';
import Greeter from './Greeter.vue';

describe('Greeter.vue', () => {

  // Test 1: Sprawdzenie wartości domyślnej
  it('powinien witać "Gościa", gdy właściwość "name" nie jest podana', () => {
    // ARRANGE
    // "Montujemy" komponent w wirtualnym DOM bez przekazywania żadnych props.
    // `mount` zwraca obiekt "wrapper", który opakowuje nasz komponent.
    const wrapper = mount(Greeter);

    // ACT & ASSERT
    // Sprawdzamy, czy wyrenderowany tekst zawiera oczekiwaną frazę.
    // `wrapper.text()` zwraca cały tekst z wewnątrz komponentu.
    expect(wrapper.text()).toContain('Witaj, Gościu!');
  });

  // Test 2: Sprawdzenie przekazanej wartości
  it('powinien witać po imieniu, gdy właściwość "name" jest podana', async () => {
    // ARRANGE
    // Montujemy komponent i przekazujemy mu obiekt z `props`.
    const wrapper = mount(Greeter, {
      props: {
        name: 'Anna'
      }
    });

    // ACT & ASSERT
    expect(wrapper.text()).toContain('Witaj, Anna!');

    // (Opcjonalnie) Pokażmy, jak dynamicznie zmieniać propsy
    // `setProps` jest asynchroniczne, więc używamy `await`
    await wrapper.setProps({ name: 'Piotr' });

    expect(wrapper.text()).toContain('Witaj, Piotr!');
  });
});