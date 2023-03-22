<script lang="ts">
  import Popover from '../../lib/components/Popover/Popover.svelte';
  import Modal from '../../lib/components/Modal/Modal.svelte';
  import { makeRemoteItem } from '../../main/factories/usecases/remote-item-factory';
  import { notifications } from '../../../src/infra/notification/notification';
  import Input from '../../lib/components/Input/Input.svelte';
  import Menu from '../../lib/components/Menu/Menu.svelte';
  import type { ItemModel } from 'src/domain/models/item-model';
  import Select from '../../lib/components/Select/Select.svelte';
  import { makeRemoteCategory } from '../../main/factories/usecases/remote-category-factory';

  let items = [];
  let categories = [];
  interface newItemModel extends ItemModel {
    category_id: number;
    sales_unit_id: number;
  }

  interface ItemToAdd extends Omit<newItemModel, 'category' | 'sales_unit'> {}

  let newItem: ItemToAdd = {
    id: 0,
    name: '',
    category_id: 0,
    purchase_price: 0,
    sale_price: 0,
    sales_unit_id: 0,
    extra_info: '',
    item_type: '',
  };

  let modal = {
    title: 'Novo Item',
    editTitle: 'Editar Item',
    model: newItem,
    new: true,
    opened: false,
  };

  const apiCategory = makeRemoteCategory();
  const loadCategories = async () => {
    categories = await apiCategory.list();
  };
  loadCategories();

  const api = makeRemoteItem();
  const loadItems = async () => {
    items = await api.list();
    items = items.map((item) => {
      return {
        ...item,
        popRemove: false,
      };
    });
  };

  const closeModal = () => {
    modal.model = newItem;
    modal.opened = false;
    modal.new = true;
  };

  const createItem = async () => {
    try {
      await api.create(modal.model);
      notifications.success('Item criado com sucesso');
      closeModal();
      loadItems();
    } catch (error) {
      notifications.danger(error);
    }
  };

  // const editCategory = async () => {
  //   try {
  //     await api.edit(modal.model.id, modal.model);
  //     closeModal();
  //     loadCategories();
  //     notifications.success('Categoria editada com sucesso');
  //   } catch (error) {
  //     notifications.danger(error);
  //   }
  // };

  // const removeCategory = async (category) => {
  //   try {
  //     await api.delete(category.id);
  //     category.popRemove = false;
  //     notifications.success('Categoria removida com sucesso');
  //     loadCategories();
  //   } catch (error) {
  //     notifications.danger(error);
  //   }
  // };

  const openModalToEdit = (category: ItemToAdd) => {
    modal.opened = true;
    modal.new = false;
    modal.model = category;
  };

  loadItems();
</script>

<Menu />
<div class="page-container">
  <div class="header-page">
    <h1
      class="mb-4 text-3xl font-extrabold text-gray-900 dark:text-white md:text-5xl lg:text-6xl"
    >
      <span class="text-blue-600 dark:text-blue-500">Itens</span>
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
        {#each items as category}
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

              <!-- {#if category.popRemove}
                <Popover
                  message={`Tem certeza que deseja excluir a categoria ${category.name}`}
                  on:close={() => (category.popRemove = false)}
                  on:confirm={() => removeCategory(category)}
                />
              {/if} -->
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
      on:confirm={() => createItem()}
    >
      <form>
        <div class="grid gap-6 mb-6 md:grid-cols-1">
          <Input
            label="Nome"
            bind:value={modal.model.name}
            placeholder={'Ex.: Ventilador'}
          />

          <Input
            label="Tipo do Item"
            bind:value={modal.model.item_type}
            placeholder={'Ex.: Eletrodomésticos'}
          />

          <Input
            label="Preço de Venda"
            bind:value={modal.model.sale_price}
            placeholder={'Ex.: 50.50'}
            type="number"
          />

          <Input
            label="Preço de Compra"
            bind:value={modal.model.purchase_price}
            placeholder={'Ex.: 30'}
            type="number"
          />

          <Input
            label="Informações"
            bind:value={modal.model.extra_info}
            placeholder={'Ex.: Ventilador com função turbo silence de 6 hélices'}
            type="area"
          />

          <Select
            label="Categoria"
            options={categories}
            bind:selected={modal.model.category_id}
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
