<script lang="ts">
  import { onMount } from "svelte";

  interface Option {
    name: string;
    value: string | number;
  }

  export let options: Option[] = [];
  export let label: string = '';
  export let selected: string | number;
  export let service: { factory: any } = null;
  export let valueKey: string = 'id';
  
  const onLoad = async () => {
    options = await service.factory.list()
  }

  onMount(() => {
    if (service) {
      onLoad()
    }
  })
</script>

<label for="select" class="block mb-2 text-sm font-medium text-gray-900"
  >{label || 'Selecione uma opção'}</label
>
<select
  data-testid={`${label}-select-id`}
  bind:value={selected}
  id="select"
  class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5"
>
  {#if options.length}
    {#each options as option}
      <option value={option[valueKey]} selected={option.value === selected}
        >{option.name}</option
      >
    {/each}
  {/if}
</select>
