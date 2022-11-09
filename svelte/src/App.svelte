<script lang="ts">
  import Habit from './lib/components/Habit.svelte';
</script>

<main class="text-center page-container">
  <nav>Today</nav>

  <Habit />
</main>

<style>
  :global(body) {
    background-color: #151515;
    color: #fff;
  }
  .page-container {
    padding: 8px;
  }
  nav {
    padding: 8px;
    color: #eaeaea;
    text-align: left;

    font-size: 22px;
    font-weight: bold;
  }
</style>
