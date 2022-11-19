<script lang="ts">
  import type { DispatchOptions } from 'svelte/internal';
  import Checkbox from '../Checkbox/Checkbox.svelte';

  export let name: string;
  export let icon: string;
  export let selected: boolean;

  const handleSelected = (event) => {
    // call api to toggle daily habit
  };
</script>

<div class="container-item">
  <div class="icon-habit">
    <div class="icon-box">
      <i
        class="fa-regular fa-{icon}"
        data-testid="icon-habit"
        style="color: purple;"
      />
    </div>
    <span class="habit-name">{name}</span>
  </div>

  <Checkbox {name} {selected} on:change_selected={handleSelected} />
</div>

<style>
  .habit-name {
    margin-left: 8px;
  }

  .icon-habit {
    display: flex;
    align-items: center;
  }

  .container-item {
    display: flex;
    align-items: center;
    justify-content: space-between;
  }

  .icon-box {
    background: #231f1d;
    padding: 8px;
    border-radius: 8px;
  }
</style>
