<script lang="ts">
  export let name: string;
  export let icon: string;
  export let selected = false;
</script>

<div class="container-item">
  <div class="icon-habit">
    <div class="icon-box">
      <i class="fa-regular fa-{icon}" data-testid="icon-habit" style="color: purple;"></i>
    </div>
    <span class="habit-name">{name}</span>
  </div>

  <input type="checkbox" name="{name}" id="{name}" checked="{selected}">
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
