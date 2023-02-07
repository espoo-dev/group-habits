<script>
  import { notifications } from "../../../../src/infra/notification/notification";
  import { flip } from "svelte/animate";
  import { fly } from "svelte/transition";

  export let themes = {
    danger: "#E26D69",
    success: "#84C991",
    warning: "#f0ad4e",
    info: "#5bc0de",
    default: "#aaaaaa",
  };
</script>

<div class="notifications">
  {#if $notifications}
    {#each $notifications as notification (notification.id)}
      <div
        animate:flip
        class="toast"
        style="background: {themes[notification.type]};"
        transition:fly={{ y: 30 }}
        >
        <div class="content">{notification.message}</div>
      </div>
    {/each}
  {/if}
</div>

<style>
  .notifications {
      position: fixed;
      top: 10px;
      left: 0;
      right: 0;
      margin: 0 auto;
      padding: 0;
      z-index: 9999;
      display: flex;
      flex-direction: column;
      justify-content: flex-start;
      align-items: center;
      pointer-events: none;
  }

  .toast {
      flex: 0 0 auto;
      margin-bottom: 10px;
  }

  .content {
      padding: 10px;
      display: block;
      color: white;
      font-weight: 500;
  }
</style>
