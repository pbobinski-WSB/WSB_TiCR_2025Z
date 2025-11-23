import { describe, it, expect, vi } from 'vitest';
import { mount } from '@vue/test-utils';
import AsyncMessageFetcher from './AsyncMessageFetcher.vue';

// Mockujemy globalny fetch
global.fetch = vi.fn();

describe('AsyncMessageFetcher', () => {
  it('powinien wyświetlić wiadomość po udanym pobraniu', async () => {
    fetch.mockResolvedValue({
      json: () => Promise.resolve({ title: 'Testowa wiadomość' }),
    });
    const wrapper = mount(AsyncMessageFetcher);
    await wrapper.find('button').trigger('click');
    
    // Czekamy na następny cykl renderowania, aby DOM się zaktualizował
    await wrapper.vm.$nextTick(); 
    
    expect(wrapper.find('[data-testid="message-display"]').text()).toBe('Testowa wiadomość');
  });

  it('powinien wyświetlić błąd, gdy pobieranie zawiedzie', async () => {
    fetch.mockRejectedValue(new Error('Błąd API'));
    const wrapper = mount(AsyncMessageFetcher);
    await wrapper.find('button').trigger('click');
    
    await wrapper.vm.$nextTick();
    
    expect(wrapper.find('[role="alert"]').text()).toBe('Nie udało się pobrać wiadomości');
  });
});