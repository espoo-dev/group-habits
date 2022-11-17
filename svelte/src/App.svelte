<script lang="ts">
  import HabitList from './lib/components/HabitList.svelte';

  const habits = [
    {
      name: 'Drink water',
      icon: 'heart',
      selected: false,
    },
    {
      name: 'Go to the gym',
      icon: 'heart',
      selected: true,
    }
  ];
</script>

<main class="text-center page-container">
  <nav>Today</nav>

  <HabitList habits={habits} />
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
