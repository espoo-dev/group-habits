<script lang="ts">
  import Day from './lib/components/Day/Day.svelte';
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
    },
  ];
</script>

<main class="text-center page-container">
  <nav>Today</nav>

  <div class="list-container">
    <Day dayName="Seg" dayNumber={9} selected={false} />
    <Day dayName="Ter" dayNumber={10} selected={false} />
  </div>

  <HabitList {habits} />
</main>

<style>
  .list-container {
    display: flex;
    gap: 8px;
  }

  /*  */
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
