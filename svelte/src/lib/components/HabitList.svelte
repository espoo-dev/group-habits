<script lang="ts">
  import Habit from "./Habit.svelte";

  export let habits = []
</script>

<div>
  {#each habits as { icon, name, selected }}
    <Habit icon={icon} name={name} selected={selected} />
  {/each}
</div>
