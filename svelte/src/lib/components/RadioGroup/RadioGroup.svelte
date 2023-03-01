<script lang="ts">
  import Radio from '../Radio/Radio.svelte';

  interface Option {
    label: string;
    value: string;
  }

  export let options: Option[];
  export let selectedValue = '';

  const handleChange = (option: Option) => {
    selectedValue = option.value;
  };
</script>

<ul
  class="items-center w-full text-sm font-medium text-gray-900 bg-white border border-gray-200 rounded-lg sm:flex dark:bg-gray-700 dark:border-gray-600 dark:text-white"
>
  {#each options as option}
    <li
      class="w-full border-b border-gray-200 sm:border-b-0 sm:border-r dark:border-gray-600"
    >
      <div class="flex items-center pl-3">
        <Radio
          {...option}
          checked={selectedValue === option.value}
          on:onChange={() => handleChange(option)}
        />
      </div>
    </li>
  {/each}
</ul>
