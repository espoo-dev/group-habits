<script lang="ts">
  export let label: string
  export let value: string
</script>

<input
  id={`${label}-radio-id`}
  data-testid={`${label}-radio-id`}
  type="radio"
  bind:value={value}
  class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-700 dark:focus:ring-offset-gray-700 focus:ring-2 dark:bg-gray-600 dark:border-gray-500"
>
<label
  for={`${label}-radio-id`}
  class="w-full py-3 ml-2 text-sm font-medium text-gray-900 dark:text-gray-300">
  {label}
</label>
