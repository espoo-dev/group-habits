<script lang="ts">
  const item = {
    label: "Item",
    icone: "a",
    selected: true
  }

  console.log('item -> ', item);
</script>

<div class="container-item">
  <div class="icon-habit">
    <div class="icon-box">
      <i class="fa-regular fa-heart" style="color: purple;"></i>
    </div>
    <span class="habit-name">Eat 2 fruits</span>
  </div>

  <input type="checkbox" name="item" id="item">
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
