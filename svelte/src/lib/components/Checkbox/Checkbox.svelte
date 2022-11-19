<script lang="ts">
  import { createEventDispatcher } from 'svelte';

  export let name: string;
  export let selected: boolean;

  const dispatch = createEventDispatcher();

  const changeState = () => {
    dispatch('change_selected', {
      selected: selected,
    });
  };
</script>

<input
  class="h-checkbox"
  class:selected
  type="checkbox"
  {name}
  id={name}
  bind:checked={selected}
  on:change={changeState}
/>

<style>
  .h-checkbox {
    appearance: none;
    border: 1px solid #3d8756;
    padding: 8px;
    border-radius: 50%;
    cursor: pointer;
  }

  .selected {
    background-color: #2f9150;
    border: 1px solid #2f9150;
  }
</style>
