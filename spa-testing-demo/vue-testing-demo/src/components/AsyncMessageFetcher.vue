<script setup>
import { ref } from 'vue';
const message = ref('');
const error = ref('');
const fetchMessage = async () => {
  message.value = '';
  error.value = '';
  try {
    const res = await fetch('https://jsonplaceholder.typicode.com/todos/1');
    const data = await res.json();
    message.value = data.title;
  } catch (e) {
    error.value = 'Nie udało się pobrać wiadomości';
  }
};
</script>
<template>
  <div>
    <button @click="fetchMessage">Pobierz wiadomość</button>
    <p v-if="message" data-testid="message-display">{{ message }}</p>
    <p v-if="error" role="alert">{{ error }}</p>
  </div>
</template>