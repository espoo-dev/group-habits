<script lang="ts">
  export let options = [];
</script>

<div class="menu-container">
  <div>
    {#each options as option}
      <div>
        <span>{option.name}</span>
      </div>
    {/each}
  </div>

  <div>
    <span>Sair</span>
  </div>
</div>

<style>
  .menu-container {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    height: 100vh;

    color: #fe5b00;
    background-color: #ffffff;
    padding: 8px;
  }
</style>
