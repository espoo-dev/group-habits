<script lang="ts">
  import Popover from '../../lib/components/Popover/Popover.svelte';
  import Modal from '../../lib/components/Modal/Modal.svelte';
  import { makeRemoteCategory } from '../../main/factories/usecases/remote-category-factory';
  import type { CategoryModel } from 'src/domain/models/category-model';
  import { notifications } from '../../../src/infra/notification/notification';
  import Input from '../../lib/components/Input/Input.svelte';
  import Menu from '../../lib/components/Menu/Menu.svelte';

  let categories = [];
  let defaultCategory: CategoryModel = {
    id: 0,
    name: '',
  };

  let modal = {
    title: 'Nova Categoria',
    editTitle: 'Editar Categoria',
    model: defaultCategory,
    new: true,
    opened: false,
  };

  const apiCategory = makeRemoteCategory();
  const loadCategories = async () => {
    categories = await apiCategory.list();
    categories = categories.map((category) => {
      return {
        ...category,
        popRemove: false,
      };
    });
  };

  const closeModal = () => {
    modal.model = defaultCategory;
    modal.opened = false;
    modal.new = true;
  };

  const createCategory = async () => {
    try {
      await apiCategory.create(modal.model);
      notifications.success('Categoria criada com sucesso');
      closeModal();
      loadCategories();
    } catch (error) {
      notifications.danger(error);
    }
  };

  const editCategory = async () => {
    try {
      await apiCategory.edit(modal.model.id, modal.model);
      closeModal();
      loadCategories();
      notifications.success('Categoria editada com sucesso');
    } catch (error) {
      notifications.danger(error);
    }
  };

  const removeCategory = async (category) => {
    try {
      await apiCategory.delete(category.id);
      category.popRemove = false;
      notifications.success('Categoria removida com sucesso');
      loadCategories();
    } catch (error) {
      notifications.danger(error);
    }
  };

  const openModalToEdit = (category: CategoryModel) => {
    modal.opened = true;
    modal.new = false;
    modal.model = category;
  };

  loadCategories();
</script>

<Menu />
<div class="page-container">
  <div class="header-page">
    <h1
      class="mb-4 text-3xl font-extrabold text-gray-900 dark:text-white md:text-5xl lg:text-6xl"
    >
      <span class="text-blue-600 dark:text-blue-500">Categorias</span>
    </h1>

    <button
      on:click={() => (modal.opened = true)}
      data-modal-target="staticModal"
      data-modal-toggle="staticModal"
      class="block text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
      type="button"
    >
      Novo
    </button>
  </div>

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
              <button
                on:click={() => openModalToEdit(category)}
                type="button"
                class="text-blue-700 hover:text-white border border-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium md:font-medium rounded-lg text-sm md:px-5 px-2 py-2.5 text-center mr-2 mb-2 dark:border-blue-500 dark:text-blue-500 dark:hover:text-white dark:hover:bg-blue-600 dark:focus:ring-blue-800"
              >
                Editar
              </button>

              <button
                on:click={() => (category.popRemove = true)}
                type="button"
                class="text-red-700 hover:text-white border border-red-700 hover:bg-red-800 focus:ring-4 focus:outline-none focus:ring-red-300 font-medium rounded-lg text-sm md:px-5 py-2.5 text-center mr-2 mb-2 dark:border-red-500 dark:text-red-500 dark:hover:text-white dark:hover:bg-red-600 dark:focus:ring-red-900"
              >
                Remover
              </button>

              {#if category.popRemove}
                <Popover
                  message={`Tem certeza que deseja excluir a categoria ${category.name}`}
                  on:close={() => (category.popRemove = false)}
                  on:confirm={() => removeCategory(category)}
                />
              {/if}
            </td>
          </tr>
        {/each}
      </tbody>
    </table>
  </div>

  {#if modal.opened}
    <Modal
      title={modal.new ? modal.title : modal.editTitle}
      on:close={() => closeModal()}
      on:confirm={() => (modal.new ? createCategory() : editCategory())}
    >
      <form>
        <div class="grid gap-6 mb-6 md:grid-cols-1">
          <Input
            label="Nome"
            bind:value={modal.model.name}
            placeholder={'Ex.: Carros'}
          />
        </div>
      </form>
    </Modal>
  {/if}
</div>

<style>
  .page-container {
    padding: 24px;
  }

  .header-page {
    display: flex;
    justify-content: space-between;
    align-items: center;

    margin-bottom: 46px;
  }
</style>
