<script lang="ts">
  import { makeRemoteCategory } from '../../main/factories/usecases/remote-category-factory';

  let categories = [];
  const api = makeRemoteCategory();
  const loadCategories = async () => {
    categories = await api.list();
  };

  loadCategories();
</script>

<div class="page-container">
  <h1
    class="mb-4 text-3xl font-extrabold text-gray-900 dark:text-white md:text-5xl lg:text-6xl"
  >
    <span
      class="text-transparent bg-clip-text bg-gradient-to-r to-emerald-600 from-sky-400"
      >Categorias</span
    >
  </h1>

  <div class="relative overflow-x-auto shadow-md sm:rounded-lg">
    <table class="w-full text-sm text-left text-gray-500 dark:text-gray-400">
      <thead
        class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400"
      >
        <tr>
          <th scope="col" class="px-6 py-3"> Código </th>
          <th scope="col" class="px-6 py-3"> Nome </th>
        </tr>
      </thead>
      <tbody>
        {#each categories as category}
          <tr class="bg-white border-b dark:bg-gray-800 dark:border-gray-700">
            <th
              scope="row"
              class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white"
            >
              {category.id}
            </th>
            <td class="px-6 py-4"> {category.name} </td>
            <td class="px-6 py-4">
              <a
                href="#"
                class="font-medium text-red-600 dark:text-red-500 hover:underline"
                >Remover</a
              >
            </td>
          </tr>
        {/each}
      </tbody>
    </table>
  </div>
</div>

<style>
  h1 {
    margin-bottom: 46px;
  }

  .page-container {
    padding: 24px;
  }
</style>
