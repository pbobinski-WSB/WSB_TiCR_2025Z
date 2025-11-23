<script setup>
import { ref } from 'vue';
const count = ref(0);
</script>
<template>
  <div>
    <p>Wartość licznika: {{ count }}</p>
    <button @click="count++">Zwiększ</button>
  </div>
</template>