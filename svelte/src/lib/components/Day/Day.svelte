<script lang="ts">
  export let dayName: string;
  export let dayNumber: number;
  export let selected = false;

  const toggleSelect = () => {
    selected = !selected;
  };
</script>

<button
  class="day-container"
  class:selected
  data-testid={`day-${dayNumber}`}
  on:click={toggleSelect}
>
  <div class="day-square">
    <span>{dayName}</span>
  </div>
  <div class:selected-number={selected} class="number-square">
    <span>{dayNumber}</span>
  </div>
</button>

<style>
  .day-container {
    display: flex;
    margin: 20px 0px;
    flex-direction: column;
    background-color: #1c1c1c;
    border-radius: 8px;
    width: 42px;
  }

  .selected {
    background-color: #c63a61;
  }

  .selected-number {
    background-color: #a63354 !important;
  }

  .number-square {
    background-color: #232323;
    color: #c3c3c3;
    border-radius: 8px;
    font-weight: 400;
    padding: 4px;
    width: 100%;
  }

  .day-square {
    font-size: 12px;
    padding: 4px 8px;
    color: #c3c3c3;
    width: 100%;
  }
</style>
