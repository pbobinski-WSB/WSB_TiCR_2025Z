<script setup>
import Greeter from './components/Greeter.vue';
import Counter from './components/Counter.vue';
import AsyncMessageFetcher from './components/AsyncMessageFetcher.vue';
</script>
<template>
  <header>
    <Greeter name="Studencie" />
    <hr />
    <Counter />
    <hr />
    <AsyncMessageFetcher />
  </header>
</template>