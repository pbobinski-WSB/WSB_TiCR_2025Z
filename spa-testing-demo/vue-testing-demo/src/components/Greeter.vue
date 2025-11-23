<script setup>
defineProps({ name: { type: String, default: 'Gościu' } });
</script>
<template>
  <h1>Witaj, {{ name }}!</h1>
</template>