<script lang="ts">
  import Habit from './Habit/Habit.svelte';

  export let habits = [];
</script>

<div class="habit-list-container">
  {#each habits as { icon, name, selected }}
    <Habit {icon} {name} {selected} />
  {/each}
</div>

<style>
  .habit-list-container {
    gap: 8px;
    display: flex;
    flex-direction: column;
  }
</style>
