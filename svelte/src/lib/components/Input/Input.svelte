<script lang="ts">
  export let value: string | number = '';
  export let label: string;
  export let placeholder: string | null = null;
  export let type = 'text';

  function typeAction(node) {
    node.type = type;
  }
</script>

<div>
  <label
    for="name"
    class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
    >{label}</label
  >
  <input
    bind:value
    on:input
    use:typeAction
    data-testid={`input-${label}`}
    id="name"
    class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
    {placeholder}
    required
  />
</div>
